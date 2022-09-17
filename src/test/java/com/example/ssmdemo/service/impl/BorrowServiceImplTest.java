package com.example.ssmdemo.service.impl;

import com.example.ssmdemo.bean.Borrow;
import com.example.ssmdemo.bean.BorrowExample;
import com.example.ssmdemo.dao.BorrowMapper;
import com.example.ssmdemo.service.BookService;
import com.example.ssmdemo.service.BorrowService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BorrowServiceImplTest extends TestCase {

    @Autowired
    BorrowService borrowService;

    @Test
    public void testBorrowList() {
        borrowService.borrowList();
    }

    @Test
    public void testBorrowSelectByBookID() {
        borrowService.borrowSelectByBookID(1);
    }

    @Test
    public void testBorrowSelectByCustomerID() {
        borrowService.borrowSelectByCustomerID(1);
    }

    @Test
    public void testBorrowInsert() {
        Borrow borrow = new Borrow();
        borrow.setBookid(2);
        borrow.setCustomerid(1);
        borrowService.borrowInsert(borrow);
    }

    @Test
    public void testBorrowReturn() {
        Borrow borrow = new Borrow();
        borrow.setId(1);
        borrowService.borrowReturn(borrow);
    }

    @Test
    public void testBorrowDelete() {
        borrowService.borrowDelete(4);
    }

    @Test
    public void testBorrowListSearch() {
        borrowService.borrowListSearch("ÕÅÈý",null);
    }
}