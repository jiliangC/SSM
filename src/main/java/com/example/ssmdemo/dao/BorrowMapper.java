package com.example.ssmdemo.dao;

import com.example.ssmdemo.bean.Borrow;
import com.example.ssmdemo.bean.BorrowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BorrowMapper {
    int countByExample(BorrowExample example);

    int deleteByExample(BorrowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    List<Borrow> selectByExample(BorrowExample example);

    Borrow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Borrow record, @Param("example") BorrowExample example);

    int updateByExample(@Param("record") Borrow record, @Param("example") BorrowExample example);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);

    List<Borrow> selectByExampleWithBookCustomer(BorrowExample example);
}