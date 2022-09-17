package com.example.ssmdemo.service.impl;

import com.example.ssmdemo.bean.Customer;
import com.example.ssmdemo.service.CustomerService;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

@Component
public class CustomerServiceImplTest extends TestCase {
    @Autowired
    CustomerService customerService;
    @Test
    public void testCustomerList() {
        System.out.println("测试testCustomerList：");
        List<Customer> customerList=customerService.customerList();
        for (Customer customer:customerList ) {
            System.out.println(customer);
        }
    }
    @Test
    public void testCustomerListByName() {
        System.out.println("测试testCustomerListByName：");
        List<Customer> customerList=customerService.customerListByName("李四");
        for (Customer customer:customerList ) {
            System.out.println(customer);
        }

    }
    @Test
    public void testCustomerById() {
        System.out.println("测试testCustomerById：");
        Customer customer=customerService.customerById(1);
        System.out.println(customer);
    }
    @Test
    public void testCustomerEdit() {
        System.out.println("测试testCustomerEdit：");

        Customer customer=customerService.customerById(1);
        System.out.println("编辑前id为1的客户信息");
        System.out.println(customer);
        Customer customer2 =new Customer();//1,"",,,
        customer2.setId(1);customer2.setName("张三");customer2.setPhone("10086");customer2.setAddress("南宁");customer2.setCompany("测试");
        customerService.customerEdit(customer2);
        System.out.println("编辑后id为1的客户信息");
        System.out.println(customerService.customerById(1));
    }
    @Test
    public void testCustomerAdd() {
        System.out.println("测试testCustomerAdd：");
        Customer customer2 =new Customer();//1,"",,,
        customer2.setName("王五");customer2.setPhone("12306");customer2.setAddress("北京");customer2.setCompany("测试");
        customerService.customerAdd(customer2);
        List<Customer> customerList=customerService.customerList();
        for (Customer customer:customerList ) {
            System.out.println(customer);
        }
    }
    @Test
    public void testCustomerDelete() {
        System.out.println("测试testCustomerDelete：");
        customerService.customerDelete(1);
    }
    @Test
    public void testCustomerListSearch() {
        System.out.println("测试testCustomerListSearch：");
        List<Customer> customers= customerService.customerListSearch("","李四","","");
        System.out.println(customers);
    }
}