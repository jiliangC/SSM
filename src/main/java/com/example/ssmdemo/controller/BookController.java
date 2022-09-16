package com.example.ssmdemo.controller;

import com.example.ssmdemo.bean.Book;
import com.example.ssmdemo.bean.Message;
import com.example.ssmdemo.bean.Type;
import com.example.ssmdemo.service.BookService;
import com.example.ssmdemo.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    
    @Autowired
    BookService bookService;
    @Autowired
    TypeService typeService;

//45649687496416


    @RequestMapping("bookList")
    public ModelAndView bookList(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size){
//        PageHelper.startPage(pn,size);
        List<Book> bookList = bookService.bookList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books",bookList);
        modelAndView.setViewName("booklist");
//        PageInfo<Book> pageInfo = new PageInfo<>(bookList,size);
//        return Message.success().add("bookList",pageInfo);
        return modelAndView;
    }

    @RequestMapping("bookListSearch")
    public ModelAndView bookListSearch(
            @RequestParam(value = "bookName", defaultValue="") String bookName,
            @RequestParam(value = "bookType", defaultValue="") String bookType) {
        List<Book> bookList = bookService.bookSearchList(bookName, bookType);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books", bookList);
        modelAndView.setViewName("booklist");
        return modelAndView;
    }




    @RequestMapping("bookAdd")
    public ModelAndView bookAdd(Book book){
        List<Type> types = typeService.typeList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("types",types);
        modelAndView.setViewName("bookadd");
        return modelAndView;
    }

    @RequestMapping("bookEdit")
    public ModelAndView bookEdit(Book book){
        ModelAndView modelAndView = new ModelAndView();
        List<Type> types = typeService.typeList();
        Book book1 = bookService.bookById(book.getId());
        modelAndView.addObject("types",types);
        modelAndView.addObject("book",book1);
        modelAndView.setViewName("bookedit");
        return modelAndView;
    }


    @RequestMapping("bookUpdate")
    public ModelAndView bookUpdate(Book book){
        ModelAndView modelAndView = new ModelAndView();
        bookService.bookEdit(book);
        modelAndView.setViewName("redirect:/bookList");
        return modelAndView;
    }

    @RequestMapping("bookDelete")
    public ModelAndView bookDelete(Book book){
        ModelAndView modelAndView = new ModelAndView();
        bookService.bookDelete(book.getId());
        modelAndView.setViewName("redirect:/bookList");
        return modelAndView;
    }


//上传文件
    @RequestMapping("bookSave")
    public ModelAndView bookSave(String name){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/bookList");
        return modelAndView;
    }


    @DeleteMapping("bookDelete")
    public Message bookDelete(Integer id){
        return Message.success().add("bookDelete",bookService.bookDelete(id));
    }

    @GetMapping("bookByAuthor")
    public Message bookByAuthor(String name){
        return Message.success().add("bookDelete",bookService.bookByAuthor(name));
    }

    @GetMapping("bookByID")
    public Message bookByID(Integer id){
        return Message.success().add("bookById",bookService.bookById(id));
    }


    
}
