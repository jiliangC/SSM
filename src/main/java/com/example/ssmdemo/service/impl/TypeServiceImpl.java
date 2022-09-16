package com.example.ssmdemo.service.impl;

import com.example.ssmdemo.bean.Type;
import com.example.ssmdemo.bean.TypeExample;
import com.example.ssmdemo.dao.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements com.example.ssmdemo.service.TypeService {
    
    @Autowired
    TypeMapper  typeMapper;

    /*
    查询Type列表
    */
    @Override
    public List<Type> typeList(){
        return typeMapper.selectByExample(null);
    }

    /*
    通过主键查询Type
    */
    @Override
    public Type typeById(Integer id){
        return typeMapper.selectByPrimaryKey(id);
    }

    

    /*
    编辑Type信息
    传入的对象必须携带主键id
    需要修改的值设置在对象中
    不需要修改的值留空
     */
    @Override
    public boolean typeEdit(Type type){
        return typeMapper.updateByPrimaryKeySelective(type)==1;
    }


    /*
    增加Type信息
    不需要传输主键，主键属性为自增
    插入的信息放到对象中
     */
    @Override
    public boolean typeAdd(Type type){
        return typeMapper.insertSelective(type)==1;
    }

    /*
    通过主键删除信息
     */
    @Override
    public boolean typeDelete(Integer id){
        return typeMapper.deleteByPrimaryKey(id)==1;
    }

    @Override
    public List<Type> typeListSearch(String typeName) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andTypenameLike("%"+typeName+"%");
        return typeMapper.selectByExample(typeExample);
    }
}
