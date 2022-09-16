package com.example.ssmdemo.controller;

import com.example.ssmdemo.bean.Message;
import com.example.ssmdemo.bean.User;
import com.example.ssmdemo.service.UserService;
import com.example.ssmdemo.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("userLogin")
    public String userLogin(String phone, String pass, HttpSession session){
        User user = userService.isUser(phone,pass);
        if (user!=null){
            session.setAttribute("user",user);
            return "redirect:/borrowList";
        }else {
            return "index";
        }
    }

    @RequestMapping("userListSearchTest")
    public ModelAndView userListSearchTest(
            HttpSession session,
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size,
            @RequestParam(value = "phone", defaultValue="") String phone,
            @RequestParam(value = "username", defaultValue="") String username) {

        PageHelper.startPage(pn,size);
        List<User> userList = userService.userListSearch(phone, username);
        PageInfo<User> pageInfo = new PageInfo<>(userList,size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping("userListSearch")
    public ModelAndView userListSearch(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size,
            @RequestParam(value = "phone", defaultValue="") String phone,
            @RequestParam(value = "username", defaultValue="") String username) {

        PageHelper.startPage(pn,size);
        List<User> userList = userService.userListSearch(phone, username);
        PageInfo<User> pageInfo = new PageInfo<>(userList,size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @GetMapping("userList")
    public ModelAndView userList(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size,
            @RequestParam(value = "phone", defaultValue="") String phone,
            @RequestParam(value = "username", defaultValue="") String username) {

        PageHelper.startPage(pn,size);
        List<User> userList = userService.userListSearch(phone, username);
        PageInfo<User> pageInfo = new PageInfo<>(userList,size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    /*@GetMapping("userList")
    @ResponseBody
    public ModelAndView userList(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "8") Integer size){
        PageHelper.startPage(pn,size);
        List<User> users = userService.userList();
        PageInfo<User> pageInfo = new PageInfo<>(users,size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("userlist");
        return modelAndView;

//        List<User> users = userService.userList();
//        PageInfo<User> pageInfo = new PageInfo<>(users,size);
//        return Message.success().add("userList",pageInfo);
    }*/

    @RequestMapping("userDelete")
    public ModelAndView userDelete(User user){
        userService.userDelete(user.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/userList");
        return modelAndView;
    }

    @RequestMapping("userLoginout")
    public ModelAndView userLoginout(HttpSession session){
        session.removeAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("singin");
        return modelAndView;
    }

    @RequestMapping("userSave")
    public ModelAndView userSave(User user){
        ModelAndView modelAndView = new ModelAndView();
        userService.userAdd(user);
        modelAndView.setViewName("redirect:/userList");
        return modelAndView;
    }

    @RequestMapping("userUpdate")
    public ModelAndView userUpdate(User user){
        ModelAndView modelAndView = new ModelAndView();
        userService.userEdit(user);
        modelAndView.setViewName("redirect:/userList");
        return modelAndView;
    }

    @RequestMapping("userEdit")
    public ModelAndView userEdit(User user){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.userById(user.getId());
        User user1 = users.get(0);
        modelAndView.addObject("user",user1);
        modelAndView.setViewName("useredit");
        return modelAndView;
    }

    @RequestMapping("userAdd")
    public ModelAndView userAdd(User user){
        List<User> users = userService.userList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("useradd");
        return modelAndView;
    }

    @RequestMapping("pwEdit")
    public ModelAndView pwEdit(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pwedit");
        return modelAndView;
    }


//    @PutMapping("userAdd")
//    @ResponseBody
//    public Message userAdd(User user){
//        return Message.success().add("userAdd",userService.userAdd(user));
//    }

//    @PostMapping("userEdit")
//    @ResponseBody
//    public Message userEdit(User user){
//        return Message.success().add("userEdit",userService.userEdit(user));
//    }

    @GetMapping("userListByName")
    @ResponseBody
    public Message userByName(String name){
        return Message.success().add("userName",userService.userListByName(name));
    }

    @GetMapping("userById")
    @ResponseBody
    public Message userById(Integer id){
        return Message.success().add("userById",userService.userById(id));
    }

    @DeleteMapping("userDelete")
    @ResponseBody
    public Message userByDelete(Integer id){
        return Message.success().add("Delete",userService.userDelete(id));
    }


}
