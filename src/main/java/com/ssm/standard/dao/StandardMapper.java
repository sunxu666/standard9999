package com.ssm.standard.dao;

import com.ssm.standard.entity.StandardExample;
import com.ssm.standard.entity.Standard;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StandardMapper {
    /**
     *
     * @param example
     * @return
     */
    long countByExample(StandardExample example);

    int deleteByExample(StandardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Standard record);

    int insertSelective(Standard record);

    List<Standard> selectByExample(StandardExample example);

    Standard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Standard record, @Param("example") StandardExample example);

    int updateByExample(@Param("record") Standard record, @Param("example") StandardExample example);

    int updateByPrimaryKeySelective(Standard record);

    int updateByPrimaryKey(Standard record);

    /**
     * 根据条件模糊分页查询所有信息
     * @param condition
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Standard> getByCondition(@Param(value = "condition")String condition,@Param("pageNo")int pageNo,@Param("pageSize") int pageSize);

    /**
     * 根据条件查询总条数
     * @param condition
     * @return
     */
    Integer getCountByCondition(@Param("condition") String condition);

    /**
     * 添加standard对象
     * @param standard
     * @return
     */
    int addStandard(Standard standard);

    /**
     * 根据数组删除
     * @param ids
     * @return
     */
    int delStandard(Integer[] ids);

    /**
     * 查询stdNum是否存在
     * @param stdNum
     * @return
     */
    int stdNumExists(@Param("stdNum")String stdNum);

    Standard queryById(String id);

    int modifyStandard(Standard standard);

}