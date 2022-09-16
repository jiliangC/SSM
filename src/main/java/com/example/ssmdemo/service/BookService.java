package com.example.ssmdemo.service;

import com.example.ssmdemo.bean.Book;

import java.util.List;

public interface BookService {
    List<Book> bookSearchList(String bookName, String bookType);

    /*
        查询书本列表
         */
    List<Book> bookList();

    /*
        通过主键查询书本
        */
    Book bookById(Integer id);

    /*
        通过作者查询书本
         */
    List<Book> bookByAuthor(String author);

    /*
        编辑书本信息
        传入的对象必须携带主键id
        需要修改的值设置在对象中
        不需要修改的值留空
         */
    boolean bookEdit(Book book);

    /*
        增加书本信息
        不需要传输主键，主键属性为自增
        插入的信息放到对象中
         */
    boolean bookAdd(Book book);

    /*
        通过主键删除信息
         */
    boolean bookDelete(Integer id);
}
