package com.example.ssmdemo.controller;

import com.example.ssmdemo.bean.Message;
import com.example.ssmdemo.bean.Customer;
import com.example.ssmdemo.service.CustomerService;
import com.example.ssmdemo.service.impl.CustomerServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CustomerController {


    @Autowired
    CustomerService customerService;


    /*@RequestMapping("customerList")
    public ModelAndView customerList(){
        List<Customer> customers = customerService.customerList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customers",customers);
        modelAndView.setViewName("customerlist");
        return modelAndView;
    }*/

    @RequestMapping("customerList")
    public ModelAndView customerList(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size,
            @RequestParam(value = "phone", defaultValue="") String phone,
            @RequestParam(value = "username", defaultValue="") String username,
            @RequestParam(value = "address", defaultValue="") String address,
            @RequestParam(value = "company", defaultValue="") String company) {

        PageHelper.startPage(pn,size);
        List<Customer> customerList = customerService.customerListSearch(phone, username, address, company);
        PageInfo<Customer> pageInfo = new PageInfo<>(customerList,size);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("customerlist");
        return modelAndView;
    }

    @RequestMapping("customerListSearch")
    public ModelAndView customerListSearch(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size,
            @RequestParam(value = "phone", defaultValue="") String phone,
            @RequestParam(value = "username", defaultValue="") String username,
            @RequestParam(value = "address", defaultValue="") String address,
            @RequestParam(value = "company", defaultValue="") String company) {

        PageHelper.startPage(pn,size);
        List<Customer> customerList = customerService.customerListSearch(phone, username, address, company);
        PageInfo<Customer> pageInfo = new PageInfo<>(customerList,size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("customerlist");
        return modelAndView;
    }

    @RequestMapping("customerAdd")
    public ModelAndView customerAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customeradd");
        return modelAndView;
    }

    @RequestMapping("customerSave")
    public ModelAndView customerSave(Customer customer){
        ModelAndView modelAndView = new ModelAndView();
        customerService.customerAdd(customer);
        modelAndView.setViewName("redirect:/customerList");
        return modelAndView;
    }

    @RequestMapping("customerDelete")
    public ModelAndView customerDelete(Customer customer){
        ModelAndView modelAndView = new ModelAndView();
        customerService.customerDelete(customer.getId());
        modelAndView.setViewName("redirect:/customerList");
        return modelAndView;
    }

    @RequestMapping("customerUpdate")
    public ModelAndView customerUpdate(Customer customer){
        ModelAndView modelAndView = new ModelAndView();
        customerService.customerEdit(customer);
        modelAndView.setViewName("redirect:/customerList");
        return modelAndView;
    }

    @RequestMapping("customerEdit")
    public ModelAndView customerEdit(Customer customer){
        ModelAndView modelAndView = new ModelAndView();
        Customer customer1 = customerService.customerById(customer.getId());
        modelAndView.addObject("customer",customer1);
        modelAndView.setViewName("customeredit");
        return modelAndView;
    }






    @GetMapping("CustomerList")
    public Message CustomerList(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size){
        PageHelper.startPage(pn,size);
        List<Customer> Customers = customerService.customerList();
        PageInfo<Customer> pageInfo = new PageInfo<>(Customers,size);
        return Message.success().add("CustomerList",pageInfo);
    }

    @PutMapping("CustomerAdd")
    public Message CustomerAdd(Customer Customer){
        return Message.success().add("CustomerAdd", customerService.customerAdd(Customer));
    }

    @PostMapping("CustomerEdit")
    public Message CustomerEdit(Customer Customer){
        return Message.success().add("CustomerEdit", customerService.customerEdit(Customer));
    }

    @GetMapping("CustomerDelete")
    public Message customerDelete(Integer id){
        return Message.success().add("delete",customerService.customerDelete(id));
    }

    @GetMapping("CustomerByName")
    public Message customerByName(String name){
        return Message.success().add("Name",customerService.customerListByName(name));
    }

    @GetMapping("CustomerByID")
    public Message customerByID(Integer id){
        return Message.success().add("ID",customerService.customerById(id));
    }

    
}
