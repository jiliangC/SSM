package com.example.ssmdemo.service;

import com.example.ssmdemo.bean.Customer;

import java.util.List;

public interface CustomerService {
    /*
        获取Customer列表
         */
    List<Customer> customerList();

    /*
        通过昵称查询
         */
    List<Customer> customerListByName(String name);

    /*
        通过主键查询
         */
    Customer customerById(Integer id);

    /*
        编辑客户信息
        传入的对象必须携带主键id
        需要修改的值设置在对象中
        不需要修改的值留空
         */
    boolean customerEdit(Customer customer);

    /*
        增加Customer信息
        不需要传输主键，主键属性为自增
        插入的信息放到对象中
         */
    boolean customerAdd(Customer customer);

    /*
        通过主键删除信息
         */
    boolean customerDelete(Integer id);

    List<Customer> customerListSearch(String phone, String username, String address, String company);
}
