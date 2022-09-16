package com.example.ssmdemo.service;

import com.example.ssmdemo.bean.Borrow;

import java.util.List;

public interface BorrowService {
    /*
        返回借书列表
         */
    List<Borrow> borrowList();

    /*
        通过书名ID查询
        */
    List<Borrow> borrowSelectByBookID(Integer bookId);

    /*
        通过客户ID查询
        */
    List<Borrow> borrowSelectByCustomerID(Integer customerId);

    /*
        插入借书信息
        将插入的属性全部放到Borrow中再传进来
         */
    boolean borrowInsert(Borrow borrow);

    /*
        更新归还时间
        传入的对象中必须设置主键，需要更新的属性就设置，不需要更新的属性就留空
         */
    boolean borrowReturn(Borrow borrow);

    /*
        根据主键删除借书信
         */
    boolean borrowDelete(Integer id);

    List<Borrow> borrowListSearch(String bookName, String username);
}
