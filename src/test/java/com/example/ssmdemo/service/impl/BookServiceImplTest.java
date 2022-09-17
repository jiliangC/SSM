package com.example.ssmdemo.service.impl;

import com.example.ssmdemo.bean.Book;
import com.example.ssmdemo.bean.BookExample;
import com.example.ssmdemo.dao.BookMapper;
import com.example.ssmdemo.service.BookService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Test
    public void bookSearchList() {
        List<Book> books = bookService.bookSearchList(null,null);
        books.forEach(System.out::println);
    }

    @Test
    public void bookList() {
        List<Book> books = bookService.bookList();
        books.forEach(System.out::println);
    }

    @Test
    public void bookById() {
        Integer id = 1;
        Book book = bookService.bookById(id);
        System.out.println(book);
    }

    @Test
    public void bookByAuthor() {
        String author = "ÕÅÈý";
        List<Book> books = bookService.bookByAuthor(author);
        books.forEach(System.out::println);
    }

    @Test
    public void bookEdit() {
        Book book = new Book();
        book.setId(1);
        book.setName("123d4");
        boolean b = bookService.bookEdit(book);
        System.out.println(b);
    }

    @Test
    public void bookAdd() {
        Book book = new Book();
        book.setName("test");
        boolean b = bookService.bookAdd(book);
        System.out.println(b);
    }

    @Test
    public void bookDelete() {
        Integer id = 3;
        boolean i = bookService.bookDelete(id);
        System.out.println(i);
    }
}