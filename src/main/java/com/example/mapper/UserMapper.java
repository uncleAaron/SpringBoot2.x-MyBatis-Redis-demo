package com.example.mapper;

import com.example.entity.User;
import com.example.entity.sClass;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("SELECT id, name, class_id from user WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "aSClass", column = "class_id", javaType = sClass.class,
                    one = @One(select = "com.example.mapper.ClassMapper.findClass"))
    })
    User findUser(int id);

    @Insert("INSERT INTO user(id, name, class_id) VALUES (#{id}, #{name}, #{aSClass.id})")
    void addUser(User user);

    @Update("UPDATE user SET name=#{name}, class_id=#{aSClass.id} WHERE id=#{id}")
    void updateUser(User user);

    @Delete("DELETE FROM user where id=#{id}")
    void deleteUser(Integer id);

//    //这个还要搞分页
//    @Select("SELECT id, name, class_id FROM user")
//    @Results({
//            @Result(id = true, property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "aSClass", column = "class_id", javaType = sClass.class,
//                    one = @One(select = "com.example.mapper.ClassMapper.findClass"))
//    })
    List<User> findAllUser();
}
