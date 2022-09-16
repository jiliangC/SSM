package com.example.ssmdemo.service.impl;

import com.example.ssmdemo.bean.Borrow;
import com.example.ssmdemo.bean.BorrowExample;
import com.example.ssmdemo.dao.BorrowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class BorrowServiceImpl implements com.example.ssmdemo.service.BorrowService {

    @Autowired
    BorrowMapper borrowMapper;

    /*
    返回借书列表
     */
    @Override
    public List<Borrow> borrowList() {
        return borrowMapper.selectByExampleWithBookCustomer(null);
    }

    /*
    通过书名ID查询
    */
    @Override
    public List<Borrow> borrowSelectByBookID(Integer bookId) {
        BorrowExample example = new BorrowExample();
        example.createCriteria().andBookidEqualTo(bookId);
        return borrowMapper.selectByExampleWithBookCustomer(example);
    }

    /*
    通过客户ID查询
    */
    @Override
    public List<Borrow> borrowSelectByCustomerID(Integer customerId) {
        BorrowExample example = new BorrowExample();
        example.createCriteria().andCustomeridEqualTo(customerId);
        return borrowMapper.selectByExampleWithBookCustomer(example);
    }


    /*
    插入借书信息
    将插入的属性全部放到Borrow中再传进来
     */
    @Override
    public boolean borrowInsert(Borrow borrow) {
        try {
            borrow.setBdate(getDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int i = borrowMapper.insertSelective(borrow);
        return i == 1;
    }

    /*
    更新归还时间
    传入的对象中必须设置主键，需要更新的属性就设置，不需要更新的属性就留空
     */
    @Override
    public boolean borrowReturn(Borrow borrow) {
        try {
            borrow.setRdate(getDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int i = borrowMapper.updateByPrimaryKeySelective(borrow);
        return i == 1;
    }

    /*
    根据主键删除借书信
     */
    @Override
    public boolean borrowDelete(Integer id) {
        return borrowMapper.deleteByPrimaryKey(id) == 1;
    }

    /*
    获取当前时间
     */
    private java.sql.Date getDate() throws ParseException {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStringToParse = bartDateFormat.format(nowTime);
        Date date;
        date = bartDateFormat.parse(dateStringToParse);
        return new java.sql.Date(date.getTime());
    }

    @Override
    public List<Borrow> borrowListSearch(String bookName, String username) {
        BorrowExample borrowExample = new BorrowExample();
        borrowExample.createCriteria().andBookNameLike("%"+bookName+"%").andCustomerNameLike("%"+username+"%");
        return borrowMapper.selectByExampleWithBookCustomer(borrowExample);
    }
}
