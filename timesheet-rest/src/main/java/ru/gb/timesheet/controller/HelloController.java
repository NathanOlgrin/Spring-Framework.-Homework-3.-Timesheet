package ru.gb.timesheet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@Tag(name = "Hello", description = "API для старта")
public class HelloController {

    //GET http://localhost:8080/hello?username=Igor
    @Operation(summary = "Hello", description = "Поздороваться с пользователем")
    @GetMapping("/hello")
    public String helloPage(@RequestParam(required = false, value = "username") @Parameter(description = "Имя пользователя") String uname){  //required = false - запрос необязателен
       if(uname == null){
           uname = "world";
       }
        return "<h1>Hello, " + uname + "!</h1>";
    }

    //GET http://localhost:8080/hello/igor
    @Operation(summary = "Hello", description = "Поздороваться с пользователем")
    @GetMapping("/hello/{username}")
    public String helloPagePathVariable(@PathVariable(value = "username") @Parameter(description = "Имя пользователя") String uname){  //required = false - запрос необязателен
        if(uname == null){
            uname = "world";
        }
        return "<h1>Hello, " + uname + "!</h1>";
    }
}
