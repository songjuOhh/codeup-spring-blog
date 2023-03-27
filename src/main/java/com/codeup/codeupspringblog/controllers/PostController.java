package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postIndex(){
        return "Viewing posts";
    }


    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postId(@PathVariable long id){
        return "post#: "+ id;
    }


    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreatedPost(){
        return "<form method=\"post\">"+
                    "<div class=\"form-group\">"+
                        "<label for=\"username\">Username</label>"+
                        "<input id=\"username\" name=\"username\" class=\"form-control\" type=\"text\">"+
                    "</div>"+
                    "<div class=\"form-group\">"+
                        "<label for=\"password\">Password</label>"+
                        "<input id=\"password\" name=\"password\" class=\"form-control\" type=\"text\">"+
                    "</div>"+
                    "<input type=\"submit\" class=\"btn btn-primary btn-block\" value=\"Log In\">"+
                "</form>";
    }


    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(@RequestParam String username , @RequestParam String password){
       return "<p>username: "+username+"</p><p>password: " +password+"</p>";
    }





}
