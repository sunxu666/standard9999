package com.ssm.standard.web;

import com.ssm.standard.entity.Standard;
import com.ssm.standard.service.StandardService;
import com.ssm.standard.util.Constants;
import com.ssm.standard.util.PageSupport;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
public class StandardController {
    @Autowired
    private StandardService service;
    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
    @RequestMapping("/index")
    public String getByCondition(@RequestParam(name = "condition", required = false) String condition, Integer pageNo, Integer pageSize, Model model){
        if(pageNo==null){
            pageNo = 1;
        }
        if(pageSize==null){
            pageSize = Constants.pageSize;
        }
        System.out.println("☺☺☺☺"+condition+"☺☺☺☺"+pageNo+"☺☺☺☺"+pageSize);
        PageSupport pageSupport = service.getByCondition(condition,pageNo,pageSize);
        model.addAttribute("pagination",pageSupport);
        if(condition != null){
            model.addAttribute("condition",condition);
        }
        return "index";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "add2";
    }
    @RequestMapping("/addStan")
    public String addStan(Standard standard, HttpServletRequest request,
                          @RequestParam(value = "fuJian", required = false) MultipartFile attach, Model model){
       String packgePath = null;
       if(!attach.isEmpty()){
           //文件上传路径
           String path =request.getSession().getServletContext().getRealPath("statics"+ File.separator+"uploadfiles");
           System.out.println("文件上传路径"+path);
           String oldFileName = attach.getOriginalFilename();
           System.out.println("源文件名"+oldFileName);
           String prefix = FilenameUtils.getExtension(oldFileName);
           System.out.println("源文件后缀"+prefix);
           int filesize = 500000;
           System.out.println("文件上传大小"+attach.getSize());
           if(attach.getSize() > filesize){
               model.addAttribute("msg","上传大小不得超过500k");
               return "add2";
           }else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                  ||prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg") ){
               //防止上传文件同名
               String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_Personal.jpg";
               System.out.println("新文件名：系统当前时间+1000000以内的随机数+_Personal.jpg"+fileName);
               //判断文件是否存在，不存在创建文件夹，并把附件上传到当前位置
               File targetFile1 = new File(path);
               if (!targetFile1.exists()) {
                   targetFile1.mkdirs();

               }
               File targetFile = new File(path,fileName);
               //保存
               try {
                   attach.transferTo(targetFile);
               } catch (IOException e) {
                   e.printStackTrace();
                   model.addAttribute("msg","上传失败！");
                   return "add2";
               }
               packgePath = path + File.separator +fileName;
           } else {
                model.addAttribute("msg","上传图片格式不对！");
                return "add2";
           }
       }

       standard.setPackagePath(packgePath);
       if(service.addStandard(standard) > 0){
           model.addAttribute("msg","上传成功！");
           return "redirect:/index";
       }else {
           model.addAttribute("msg","上传失败！");
           return "add2";
       }

    }
    @RequestMapping("deleteStan")
    public String deleteStan(Integer[] chk_value,Model model){
        if(service.delStandard(chk_value)>0){
            model.addAttribute("msg","删除成功！");
            return "redirect:/index";
        }else{
            model.addAttribute("msg","删除失败！");
            return "add2";
        }

    }

    @RequestMapping(value = "isExists")
    @ResponseBody
    public Object stuNumExists(@RequestParam String stdNum) {
        int count = service.stdNumExists(stdNum);
        if (count > 0) {
            return "err";
        } else {
            return "ok";
        }
    }
    @RequestMapping(value = "queryById")
    public String queryById(@RequestParam String id, Model model) {
        Standard standard = service.queryById(id);
        model.addAttribute("standard", standard);
        return "modify";
    }
    @RequestMapping(value = "modify")
    public String modify(Standard standard, HttpServletRequest request,
                         @RequestParam(value = "fuJian", required = false) MultipartFile attach, Model model) {
        if (!attach.isEmpty()) {
            String packagePath = null;
            // 文件上传路径
            String path = request.getSession().getServletContext()
                    .getRealPath("statics" + File.separator + "uploadfiles");
            System.out.println("文件上传的路径为:" + path);
            String oldFileName = attach.getOriginalFilename();// 原文件名
            System.out.println("源文件名:" + oldFileName);
            String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
            System.out.println("源文件后缀" + prefix);
            int filesize = 500000;
            System.out.println("文件大小为:" + attach.getSize());
            if (attach.getSize() > filesize) {// 上传大小不得超过 500k
                model.addAttribute("msg", "上传大小不得超过 500");
                return "add2";
            } else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
                // 防止上传的文件同名
                String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_Personal.jpg";
                System.out.println("新文件名:" + fileName);
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                // 保存
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("msg", "上传失败！");
                    return "add2";
                }
                packagePath = path + File.separator + fileName;
            } else {
                model.addAttribute("msg", "上传图片格式不正确");
                return "add2";
            }
            standard.setPackagePath(packagePath);
            if (service.modifyStandard(standard) > 0) {
                model.addAttribute("msg", "修改成功!");
                return "redirect:/index";
            } else {
                model.addAttribute("msg", "修改失败!");
                return "redirect:/queryById";
            }

        } else {
            standard.setPackagePath(null);
            if (service.modifyStandard(standard) > 0) {
                model.addAttribute("msg", "修改成功!");
                return "redirect:/index";
            } else {
                model.addAttribute("msg", "修改失败!");
                return "redirect:/queryById";
            }
        }
    }

    @RequestMapping(value = "download")
    public void downLoad(@RequestParam(value = "id") String id, HttpServletRequest request,
                         HttpServletResponse response) {
        Standard standard = service.queryById(id);
        System.out.println("文件路径为:" + standard.getPackagePath());
        String filename = standard.getPackagePath();
        ServletOutputStream out;
        // 得到要下载的文件
        File file = new File(filename);
        try {
            // 设置响应头，控制浏览器下载该文件
            response.setContentType("multipart/form-data");
            // 获得浏览器信息,并处理文件名
            String headerType = request.getHeader("User-Agent").toUpperCase();
            String fileName = null;
            if (headerType.indexOf("EDGE") > 0 || headerType.indexOf("MSIE") > 0 || headerType.indexOf("GECKO") > 0) {
                fileName = URLEncoder.encode(file.getName(), "UTF-8");
            } else {
                fileName = new String(file.getName().replaceAll(" ", "").getBytes("utf-8"), "iso8859-1");
            }
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Content-Length", "" + file.length());
            FileInputStream inputStream = new FileInputStream(file);

            out = response.getOutputStream();

            int b = 0;
            byte[] buffer = new byte[1024];
            while (b != -1) {
                b = inputStream.read(buffer);
                // 写到输出流(out)中
                if (b != -1) {
                    out.write(buffer, 0, b);
                }
            }
            inputStream.close();
            out.close();// 关闭输出流
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
