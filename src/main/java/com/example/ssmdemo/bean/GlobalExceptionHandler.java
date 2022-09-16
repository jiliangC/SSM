package com.example.ssmdemo.bean;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice将当前类标识为异常处理的组件
@ControllerAdvice
public class GlobalExceptionHandler {

    //@ExceptionHandler用于设置所标识方法处理的异常
    @ExceptionHandler(Exception.class)
    //ex表示当前请求处理中出现的异常对象
    public String handleArithmeticException(Exception ex, Model model){
        String unLogin = ex.getMessage();
//        System.out.println("=========异常处理=============");
        if (unLogin.equals("未登录")){
            return "redirect:/userIndex";
        }
        model.addAttribute("ex", ex);
        return "error";
    }

}
