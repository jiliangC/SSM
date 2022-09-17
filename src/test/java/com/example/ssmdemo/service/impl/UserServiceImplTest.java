package com.example.ssmdemo.service.impl;

import com.example.ssmdemo.bean.User;
import com.example.ssmdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceImplTest {
    @Autowired
    UserService userServiceImpl;

    String phone = "13212356787";

    String pass = "111";

    String name = "ÀîËÄ";

    Integer id = 12;

    User user = new User(name, phone, pass);

    @Test
    public void testIsUser() {
        userServiceImpl.isUser(phone, pass);
    }

    @Test
    public void testUserListByName() {
        userServiceImpl.userListByName(name);
    }

    @Test
    public void testUserList() {
        userServiceImpl.userList();
    }

    @Test
    public void testUserById() {
        userServiceImpl.userById(id);
    }

    @Test
    public void testUserEdit() {
        user.setId(12);
        userServiceImpl.userEdit(user);
    }

    @Test
    public void testUserAdd() {
        userServiceImpl.userAdd(user);
    }

    @Test
    public void testUserDelete() {
        userServiceImpl.userDelete(id);
    }

    @Test
    public void testUserListSearch() {
        userServiceImpl.userListSearch(phone, name);
    }
}
