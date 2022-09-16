package com.example.ssmdemo.service;

import com.example.ssmdemo.bean.Book;
import com.example.ssmdemo.bean.BookExample;
import com.example.ssmdemo.dao.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookMapper bookMapper;


    /*
    查询书本列表
     */
    public List<Book> bookList(){
        return bookMapper.selectByExampleWithTypeName(null);
    }

    /*
    通过主键查询书本
    */
    public Book bookById(Integer id){
        return bookMapper.selectByPrimaryKey(id);
    }

    /*
    通过作者查询书本
     */
    public List<Book> bookByAuthor(String author){
        BookExample example = new BookExample();
        example.createCriteria().andAuthorEqualTo(author);
        return bookMapper.selectByExample(example);
    }



    /*
    编辑书本信息
    传入的对象必须携带主键id
    需要修改的值设置在对象中
    不需要修改的值留空
     */
    public boolean bookEdit(Book book){
        return bookMapper.updateByPrimaryKeySelective(book)==1;
    }


    /*
    增加书本信息
    不需要传输主键，主键属性为自增
    插入的信息放到对象中
     */
    public boolean bookAdd(Book book){
        try {
            book.setPdate(getDate());
            return bookMapper.insertSelective(book)==1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /*
    通过主键删除信息
     */
    public boolean bookDelete(Integer id){
        return bookMapper.deleteByPrimaryKey(id)==1;
    }

    private java.sql.Date getDate() throws ParseException {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStringToParse = bartDateFormat.format(nowTime);
        Date date;
        date = bartDateFormat.parse(dateStringToParse);
        return new java.sql.Date(date.getTime());
    }
}
