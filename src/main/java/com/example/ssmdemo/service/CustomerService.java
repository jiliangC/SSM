package com.example.ssmdemo.service;

import com.example.ssmdemo.bean.Customer;
import com.example.ssmdemo.bean.CustomerExample;
import com.example.ssmdemo.dao.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;


    /*
    获取Customer列表
     */
    public List<Customer> customerList(){
        return customerMapper.selectByExample(null);
    }

    /*
    通过昵称查询
     */
    public List<Customer> customerListByName(String name){
        CustomerExample example = new CustomerExample();
        example.createCriteria().andNameEqualTo(name);
        return customerMapper.selectByExample(example);
    }

    /*
    通过主键查询
     */
    public Customer customerById(Integer id){
        return customerMapper.selectByPrimaryKey(id);
    }

    /*
    编辑客户信息
    传入的对象必须携带主键id
    需要修改的值设置在对象中
    不需要修改的值留空
     */
    public boolean customerEdit(Customer customer){
        return customerMapper.updateByPrimaryKeySelective(customer)==1;
    }

    /*
    增加Customer信息
    不需要传输主键，主键属性为自增
    插入的信息放到对象中
     */
    public boolean customerAdd(Customer customer){
        return customerMapper.insertSelective(customer)==1;
    }

    /*
    通过主键删除信息
     */
    public boolean customerDelete(Integer id){
        return customerMapper.deleteByPrimaryKey(id)==1;
    }
    
}
