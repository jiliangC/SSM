package com.example.ssmdemo.service;

import com.example.ssmdemo.bean.Type;

import java.util.List;

public interface TypeService {
    /*
        查询Type列表
        */
    List<Type> typeList();

    /*
        通过主键查询Type
        */
    Type typeById(Integer id);

    /*
        编辑Type信息
        传入的对象必须携带主键id
        需要修改的值设置在对象中
        不需要修改的值留空
         */
    boolean typeEdit(Type type);

    /*
        增加Type信息
        不需要传输主键，主键属性为自增
        插入的信息放到对象中
         */
    boolean typeAdd(Type type);

    /*
        通过主键删除信息
         */
    boolean typeDelete(Integer id);

    List<Type> typeListSearch(String typeName);
}
