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
        System.out.println("����testCustomerList��");
        List<Customer> customerList=customerService.customerList();
        for (Customer customer:customerList ) {
            System.out.println(customer);
        }
    }
    @Test
    public void testCustomerListByName() {
        System.out.println("����testCustomerListByName��");
        List<Customer> customerList=customerService.customerListByName("����");
        for (Customer customer:customerList ) {
            System.out.println(customer);
        }

    }
    @Test
    public void testCustomerById() {
        System.out.println("����testCustomerById��");
        Customer customer=customerService.customerById(1);
        System.out.println(customer);
    }
    @Test
    public void testCustomerEdit() {
        System.out.println("����testCustomerEdit��");

        Customer customer=customerService.customerById(1);
        System.out.println("�༭ǰidΪ1�Ŀͻ���Ϣ");
        System.out.println(customer);
        Customer customer2 =new Customer();//1,"",,,
        customer2.setId(1);customer2.setName("����");customer2.setPhone("10086");customer2.setAddress("����");customer2.setCompany("����");
        customerService.customerEdit(customer2);
        System.out.println("�༭��idΪ1�Ŀͻ���Ϣ");
        System.out.println(customerService.customerById(1));
    }
    @Test
    public void testCustomerAdd() {
        System.out.println("����testCustomerAdd��");
        Customer customer2 =new Customer();//1,"",,,
        customer2.setName("����");customer2.setPhone("12306");customer2.setAddress("����");customer2.setCompany("����");
        customerService.customerAdd(customer2);
        List<Customer> customerList=customerService.customerList();
        for (Customer customer:customerList ) {
            System.out.println(customer);
        }
    }
    @Test
    public void testCustomerDelete() {
        System.out.println("����testCustomerDelete��");
        customerService.customerDelete(1);
    }
    @Test
    public void testCustomerListSearch() {
        System.out.println("����testCustomerListSearch��");
        List<Customer> customers= customerService.customerListSearch("","����","","");
        System.out.println(customers);
    }
}