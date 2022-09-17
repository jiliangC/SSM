package com.example.ssmdemo.controller;

import com.example.ssmdemo.bean.Message;
import com.example.ssmdemo.bean.Type;
import com.example.ssmdemo.service.TypeService;
import com.example.ssmdemo.service.impl.TypeServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class TypeController {
    
    @Autowired
    TypeService typeService;

    /*@RequestMapping("typeList")
    public ModelAndView typeList(){
        List<Type> types = typeService.typeList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("types",types);
        modelAndView.setViewName("typelist");
        return modelAndView;
    }*/

    @RequestMapping("typeList")
    public ModelAndView typeList(
            Integer id,
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size,
            @RequestParam(value = "typeName", defaultValue="") String typeName) {

        PageHelper.startPage(pn,size);
        List<Type> typeList = typeService.typeListSearch(typeName);
        PageInfo<Type> pageInfo = new PageInfo<>(typeList,size);
        ModelAndView modelAndView = new ModelAndView();

        if (id!=null){
            Type type = typeService.typeById(id);
            modelAndView.addObject("type",type);
            modelAndView.addObject("opo","edit");
        }

        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("typelist");
        return modelAndView;
    }

    @RequestMapping("typeListSearch")
    public ModelAndView typeListSearch(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size,
            @RequestParam(value = "typeName", defaultValue="") String typeName) {

        PageHelper.startPage(pn,size);
        List<Type> typeList = typeService.typeListSearch(typeName);
        PageInfo<Type> pageInfo = new PageInfo<>(typeList,size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("typelist");
        return modelAndView;
    }

    @RequestMapping("typeDelete")
    public ModelAndView typeDelete(Type type){
        typeService.typeDelete(type.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/typeList");
        return modelAndView;
    }

    @RequestMapping("typeSave")
    public ModelAndView typeSave(Type type){
        ModelAndView modelAndView = new ModelAndView();
        typeService.typeAdd(type);
        modelAndView.setViewName("redirect:/typeList");
        return modelAndView;
    }

    @RequestMapping("typeUpdate")
    public ModelAndView typeUpdate(Type type){
        ModelAndView modelAndView = new ModelAndView();
        typeService.typeEdit(type);
        modelAndView.setViewName("redirect:/typeList");
        return modelAndView;
    }


    @PutMapping("typeAdd")
    @ResponseBody
    public Message typeAdd(Type type){
        return Message.success().add("typeAdd",typeService.typeAdd(type));
    }

    @PostMapping("typeEdit")
    @ResponseBody
    public Message typeEdit(Type type){
        return Message.success().add("typeEdit",typeService.typeEdit(type));
    }

    @DeleteMapping("typeDelete")
    @ResponseBody
    public Message typeByDelete(Integer id) {
        return Message.success().add("Delete", typeService.typeDelete(id));
    }

//    @GetMapping("typeList")
//    @ResponseBody
//    public Message typeList(){
//        List<Type> types = typeService.typeList();
//        return Message.success().add("typeList",types);
//    }

    @GetMapping("typeById")
    @ResponseBody
    public Message userById(Integer id){
        return Message.success().add("typeById",typeService.typeById(id));
    }
    
}
