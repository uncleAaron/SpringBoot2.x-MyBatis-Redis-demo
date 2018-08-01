package com.example.mapper;

import com.example.entity.sClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

public interface ClassMapper {

    @Select("select id, name from t_class where id = #{id}")
    sClass findClass(int id);

    @Select("select id, name from t_class;")
    Collection<sClass> findAllClasses();
}