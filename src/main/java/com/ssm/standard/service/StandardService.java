package com.ssm.standard.service;

import com.ssm.standard.entity.Standard;
import com.ssm.standard.util.PageSupport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StandardService {
    /**
     * 根据条件模糊分页查询所有信息
     * @param condition
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageSupport getByCondition(String condition, Integer pageNo,  Integer pageSize);

    /**
     * 添加对象
     * @param standard
     * @return
     */
    int addStandard(Standard standard);

    /**
     * 删除数组
     * @param ids
     * @return
     */
    int delStandard(Integer[] ids);

    int stdNumExists(String stdNum);

    Standard queryById(String id);
    public int modifyStandard(Standard standard);


}
