package ru.gb.timesheet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class HelloController {

    //GET http://localhost:8080/hello?username=Igor
    @GetMapping("/hello")
    public String helloPage(@RequestParam(required = false, value = "username") String uname){  //required = false - запрос необязателен
       if(uname == null){
           uname = "world";
       }
        return "<h1>Hello, " + uname + "!</h1>";
    }

    //GET http://localhost:8080/hello/igor
    @GetMapping("/hello/{username}")
    public String helloPagePathVariable(@PathVariable(value = "username") String uname){  //required = false - запрос необязателен
        if(uname == null){
            uname = "world";
        }
        return "<h1>Hello, " + uname + "!</h1>";
    }
}
