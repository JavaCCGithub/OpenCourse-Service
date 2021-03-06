package com.cc.open.dao;

import com.cc.open.domain.Academy;
import com.cc.open.domain.AcademyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcademyMapper {
    long countByExample(AcademyExample example);

    int deleteByExample(AcademyExample example);

    int deleteByPrimaryKey(String academyUuid);

    int insert(Academy record);

    int insertSelective(Academy record);

    List<Academy> selectByExample(AcademyExample example);

    Academy selectByPrimaryKey(String academyUuid);

    int updateByExampleSelective(@Param("record") Academy record, @Param("example") AcademyExample example);

    int updateByExample(@Param("record") Academy record, @Param("example") AcademyExample example);

    int updateByPrimaryKeySelective(Academy record);

    int updateByPrimaryKey(Academy record);
}