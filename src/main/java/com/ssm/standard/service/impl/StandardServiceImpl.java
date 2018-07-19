package com.ssm.standard.service.impl;

import com.ssm.standard.dao.StandardMapper;
import com.ssm.standard.entity.Standard;
import com.ssm.standard.service.StandardService;
import com.ssm.standard.util.PageSupport;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StandardServiceImpl implements StandardService {

    @Autowired
    private StandardMapper mapper;
    @Override
    public PageSupport getByCondition(String condition, Integer pageNo, Integer pageSize) {
        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(pageSize);
        pageSupport.setCurrPageNo(pageNo);
        pageSupport.setTotalCount(mapper.getCountByCondition(condition));
        /**
         * 根据总条数得到总页数
         */
       int totalPageCount = pageSupport.getTotalPageCount();
       if(pageNo<1){
           pageSupport.setCurrPageNo(1);
       }else if(pageNo>totalPageCount){
           pageSupport.setCurrPageNo(totalPageCount);
       }
       pageSupport.setStandards(mapper.getByCondition(condition,pageSupport.getStart(),pageSize));
        return pageSupport;
    }

    @Override
    public int addStandard(Standard standard) {
        return mapper.addStandard(standard);
    }

    @Override
    public int delStandard(Integer[] ids) {
        int i = mapper.delStandard(ids);
        return i;
    }

    @Override
    public int stdNumExists(String stdNum) {
        int result = mapper.stdNumExists(stdNum);
        return result;
    }

    @Override
    public Standard queryById(String id) {
        Standard standard = mapper.queryById(id);
        return standard;
    }

    @Override
    public int modifyStandard(Standard standard) {
        int i = mapper.modifyStandard(standard);
        return i;
    }
}
