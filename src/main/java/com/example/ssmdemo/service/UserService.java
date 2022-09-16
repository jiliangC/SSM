package com.example.ssmdemo.service;

import com.example.ssmdemo.bean.User;

import java.util.List;

public interface UserService {
    /*
        用户登录
        登录成功返回携带用户信息的user对象
        登录失败返回null
         */
    User isUser(String phone, String pwd);

    /*
        通过昵称查询
         */
    List<User> userListByName(String name);

    /*
        获取User列表
         */
    List<User> userList();

    /*
        通过主键查询
         */
    List<User> userById(Integer id);

    /*
        编辑User信息
        传入的对象必须携带主键id
        需要修改的值设置在对象中
        不需要修改的值留空
     */
    boolean userEdit(User user);

    /*
        增加User信息
        不需要传输主键，主键属性为自增
        插入的信息放到对象中
     */
    boolean userAdd(User user);

    /*
        通过主键删除信息
         */
    boolean userDelete(Integer id);

    List<User> userListSearch(String phone, String username);
}
