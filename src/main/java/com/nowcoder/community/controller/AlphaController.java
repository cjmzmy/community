package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.apache.catalina.util.ToStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello spring boot";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+"="+value);
        }
        System.out.println(request.getParameter("code"));
        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter();){
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //GET请求
    // /students?current=1&limit=20
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current",required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false,defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    // /student/123
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //POST请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //向浏览器响应html数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name","zhangsan");
        mv.addObject("age",30);
        mv.setViewName("/demo/view");
        return mv;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","合肥工业大学");
        model.addAttribute("age","76");
        return "/demo/view";
    }

    //响应JSON数据（异步请求）
    //Java对象 --> JSON字符串 --> JS对象
    @RequestMapping(path ="/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","zhangsan");
        emp.put("age",23);
        emp.put("salary",8000);
        return emp;
    }

    @RequestMapping(path ="/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","zhangsan");
        emp.put("age",23);
        emp.put("salary",8000);
        list.add(emp);
        emp = new HashMap<>();
        emp.put("name","lisi");
        emp.put("age",30);
        emp.put("salary",18000);
        list.add(emp);
        return list;
    }
}
