package com.example.ssmdemo.service.impl;

import com.example.ssmdemo.bean.*;
import com.example.ssmdemo.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements com.example.ssmdemo.service.UserService {

    @Autowired
    private UserMapper userMapper;

    /*
    用户登录
    登录成功返回携带用户信息的user对象
    登录失败返回null
     */
    @Override
    public User isUser(String phone, String pwd){
        UserExample example = new UserExample();
        example.createCriteria().andPhoneEqualTo(phone);
        List<User> users = userMapper.selectByExample(example);
        for (User user:users){
            if (user.getPhone().equals(phone) && user.getPass().equals(pwd)){
                return user;
            }
        }
        return null;
    }

    /*
    通过昵称查询
     */
    @Override
    public List<User> userListByName(String name){
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        return userMapper.selectByExample(example);
    }

    /*
    获取User列表
     */
    @Override
    public List<User> userList(){
        return userMapper.selectByExample(null);
    }

    /*
    通过主键查询
     */
    @Override
    public List<User> userById(Integer id){
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);
        return userMapper.selectByExample(example);
    }


    /*
    编辑User信息
    传入的对象必须携带主键id
    需要修改的值设置在对象中
    不需要修改的值留空
 */
    @Override
    public boolean userEdit(User user){
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(user.getId());
        return userMapper.updateByExampleSelective(user, example)==1;
    }

    /*
    增加User信息
    不需要传输主键，主键属性为自增
    插入的信息放到对象中
 */
    @Override
    public boolean userAdd(User user){
        return userMapper.insertSelective(user)==1;
    }

    /*
    通过主键删除信息
     */
    @Override
    public boolean userDelete(Integer id){
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);
        return userMapper.deleteByExample(example)==1;
    }


    @Override
    public List<User> userListSearch(String phone, String username) {
        UserExample example = new UserExample();
        example.createCriteria().andPhoneLike("%"+phone+"%").andNameLike("%"+username+"%");
        return userMapper.selectByExample(example);
    }
}
