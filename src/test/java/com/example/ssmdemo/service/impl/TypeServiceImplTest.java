package com.example.ssmdemo.service.impl;

import com.example.ssmdemo.bean.Type;
import com.example.ssmdemo.service.TypeService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TypeServiceImplTest extends TestCase {
    @Autowired
    TypeService typeServiceImpl;

    Integer id = 7;

    String name = "aaa";

    Type type = new Type(name);

    @Test
    public void testTypeList() {
        typeServiceImpl.typeList();
    }

    @Test
    public void testTypeById() {
        typeServiceImpl.typeById(id);
    }

    @Test
    public void testTypeEdit() {
        typeServiceImpl.typeEdit(type);
    }

    @Test
    public void testTypeAdd() {
        typeServiceImpl.typeAdd(type);
    }

    @Test
    public void testTypeDelete() {
        typeServiceImpl.typeDelete(7);
    }

    @Test
    public void testTypeListSearch() {
        typeServiceImpl.typeListSearch(name);
    }
}