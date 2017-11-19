package com.ralap.chat.mapper;


import com.ralap.chat.domain.UserModel;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * UserMapper
 *
 * @author: ralap
 * @date: created at 2017/11/12 11:48
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where name = #{name}")
    UserModel getUserByName(String name);

    @Insert("insert into user(name,password,age,address,roles) "
            + "values(#{name},#{pasword},#{age},#{address},#{roles})")
    boolean insertUser(UserModel user);
    @Select("select * from user")
    List<UserModel> getAll();
}
