package com.example.ssmdemo.controller;

import com.example.ssmdemo.bean.Book;
import com.example.ssmdemo.bean.Message;
import com.example.ssmdemo.bean.Type;
import com.example.ssmdemo.service.BookService;
import com.example.ssmdemo.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;


@RestController
public class BookController {
    
    @Autowired
    BookService bookService;
    @Autowired
    TypeService typeService;

//45649687496416

    @RequestMapping("bookListSearch")
    public ModelAndView bookListSearch(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size,
            @RequestParam(value = "bookName", defaultValue="") String bookName,
            @RequestParam(value = "bookType", defaultValue="") String bookType) {

        PageHelper.startPage(pn,size);
        List<Book> bookList = bookService.bookSearchList(bookName, bookType);
        PageInfo<Book> pageInfo = new PageInfo<>(bookList,size);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("booklist");
        return modelAndView;
    }

//    @RequestMapping("bookList")
//    public ModelAndView bookList(
//            @RequestParam(value = "pn", defaultValue="1") Integer pn,
//            @RequestParam(value="size",defaultValue = "5") Integer size,
//            @RequestParam(value = "bookName", defaultValue="") String bookName,
//            @RequestParam(value = "bookType", defaultValue="") String bookType) {
//
//        PageHelper.startPage(pn,size);
//        List<Book> bookList = bookService.bookSearchList(bookName, bookType);
//        PageInfo<Book> pageInfo = new PageInfo<>(bookList,size);
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("pageInfo", pageInfo);
//        modelAndView.setViewName("booklist");
//        return modelAndView;
//    }

    @RequestMapping("bookList")
    public ModelAndView bookList(
            @RequestParam(value = "pn", defaultValue="1") Integer pn,
            @RequestParam(value="size",defaultValue = "5") Integer size){
        PageHelper.startPage(pn,size);
        List<Book> bookList = bookService.bookList();
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Book> pageInfo = new PageInfo<>(bookList,size);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("booklist");

//        return Message.success().add("bookList",pageInfo);
        return modelAndView;
    }

    @RequestMapping("bookAdd")
    public ModelAndView bookAdd(Book book){
        List<Type> types = typeService.typeList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("types",types);
        modelAndView.setViewName("bookadd");
        return modelAndView;
    }

    @RequestMapping("bookEdit")
    public ModelAndView bookEdit(Book book){
        ModelAndView modelAndView = new ModelAndView();
        List<Type> types = typeService.typeList();
        Book book1 = bookService.bookById(book.getId());
        modelAndView.addObject("types",types);
        modelAndView.addObject("book",book1);
        modelAndView.setViewName("bookedit");
        return modelAndView;
    }


    @RequestMapping("bookUpdate")
    public ModelAndView bookUpdate(Book book){
        ModelAndView modelAndView = new ModelAndView();
        bookService.bookEdit(book);
        modelAndView.setViewName("redirect:/bookList");
        return modelAndView;
    }

    @RequestMapping("bookDelete")
    public ModelAndView bookDelete(Book book){
        ModelAndView modelAndView = new ModelAndView();
        bookService.bookDelete(book.getId());
        modelAndView.setViewName("redirect:/bookList");
        return modelAndView;
    }


    //上传文件
    @PostMapping("bookSave")
    public ModelAndView bookSave(MultipartFile image, String name,String ename,
                                 String author,String publisher,Integer price,
                                 String pdate, String isbn, String address,
                                 String brief,HttpSession session,Integer typeid) throws IOException, ParseException {
        Book book = new Book();
        book.setName(name);
        book.setAddress(address);
        book.setBrief(brief);
        book.setEname(ename);
        book.setIsbn(isbn);
        book.setPublisher(publisher);
        book.setAuthor(author);
        book.setPrice(price);
        book.setTypeid(typeid);
        book.setPdate(pdate);

        //获取上传的文件的文件名
        String fileName = image.getOriginalFilename();
        //处理文件重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + hzName;

        book.setImage(fileName);
        bookService.bookAdd(book);
        //获取服务器中photo目录的路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("upload");
        File file = new File(photoPath);
        if(!file.exists()){
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        //实现上传功能
        image.transferTo(new File(finalPath));


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/bookList");
        return modelAndView;
    }


    @DeleteMapping("bookDelete")
    public Message bookDelete(Integer id){
        return Message.success().add("bookDelete",bookService.bookDelete(id));
    }

    @GetMapping("bookByAuthor")
    public Message bookByAuthor(String name){
        return Message.success().add("bookDelete",bookService.bookByAuthor(name));
    }

    @GetMapping("bookByID")
    public Message bookByID(Integer id){
        return Message.success().add("bookById",bookService.bookById(id));
    }


    
}
