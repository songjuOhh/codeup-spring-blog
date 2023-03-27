package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {


    @GetMapping("/products/create")
    public String postIndex(){
        return "products";
    }



    @PostMapping("/products/create")
    public String createPost(@RequestParam String name , @RequestParam int price){
        System.out.println(name);
        System.out.println(price);
//        return "<p>Product: "+name+"</p><p>Price: " +price+"</p>";
        return "redirect:/";
    }


}
