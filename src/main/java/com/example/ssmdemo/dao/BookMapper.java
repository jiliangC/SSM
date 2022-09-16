package com.example.ssmdemo.dao;

import com.example.ssmdemo.bean.Book;
import com.example.ssmdemo.bean.BookExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BookMapper {
    int countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);

    List<Book> selectByExampleWithTypeName(BookExample example);

    Book selectByPrimaryKey(Integer id);

    Book selectByPrimaryKeyWithName(Integer id);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> bookSearchList(Map<String, String> map);
}