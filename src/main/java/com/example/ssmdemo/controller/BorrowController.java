package com.example.ssmdemo.controller;

import com.example.ssmdemo.bean.*;
import com.example.ssmdemo.service.BookService;
import com.example.ssmdemo.service.BorrowService;
import com.example.ssmdemo.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BorrowController {

    @Autowired
    BorrowService borrowService;

    @Autowired
    CustomerService customerService;

    @Autowired
    BookService bookService;

    @RequestMapping("/borrowList")
    public ModelAndView borrowList(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "8") Integer size){
        PageHelper.startPage(pn,size);
        List<Borrow> borrows = borrowService.borrowList();
        PageInfo<Borrow> pageInfo = new PageInfo<>(borrows,size);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("borrowlist");
        return mv;
    }

    @RequestMapping("/borrowListSearch")
    public ModelAndView borrowListSearch(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size,
            @RequestParam(value = "bookName", defaultValue="") String bookName,
            @RequestParam(value = "username", defaultValue="") String username) {

        PageHelper.startPage(pn,size);
        List<Borrow> borrowList = borrowService.borrowListSearch(bookName, username);
        PageInfo<Borrow> pageInfo = new PageInfo<>(borrowList,size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("borrowlist");
        return modelAndView;
    }

    @RequestMapping("/borrowSave")
    public ModelAndView borrowSave(Borrow borrow){
        borrowService.borrowInsert(borrow);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/borrowList");
        return mv;
    }

    @RequestMapping("/returnBook")
    public ModelAndView returnBook(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Borrow borrow = new Borrow();
        borrow.setId(id);
        borrowService.borrowReturn(borrow);
        modelAndView.setViewName("redirect:/borrowList");
        return modelAndView;
    }

    @RequestMapping("/borrow")
    public ModelAndView toBorrow(){
        ModelAndView modelAndView = new ModelAndView();
        List<Book> books = bookService.bookList();
        List<Customer> customers = customerService.customerList();
        modelAndView.addObject("books",books);
        modelAndView.addObject("customers",customers);
        modelAndView.setViewName("borrow");
        return modelAndView;
    }

    @RequestMapping("borrowDelete")
    public ModelAndView borrowDelete(Borrow borrow){
        borrowService.borrowDelete(borrow.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/borrowList");
        return modelAndView;
    }



    @GetMapping("testBorrowInsert")
    public Message testBorrowInsert(Borrow borrow){
        return Message.success().add("borrow",borrowService.borrowInsert(borrow));
    }

    @GetMapping("testBorrowSelectByBookID")
    public Message borrowSelectByBookID(Integer bookid){
        return Message.success().add("borrow",borrowService.borrowSelectByBookID(bookid));
    }

    @GetMapping("testBorrowSelectByCustomerID")
    public Message borrowSelectByCustomerID(Integer customerid){
        return Message.success().add("borrow",borrowService.borrowSelectByCustomerID(customerid));
    }

    @GetMapping("testBorrowDelete")
    public Message borrowDelete(Integer id){
        return Message.success().add("borrow",borrowService.borrowDelete(id));
    }

    @GetMapping("testReturn")
    public Message borrowReturn(Borrow borrow){
        return Message.success().add("borrow",borrowService.borrowReturn(borrow));
    }



}
