package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postIndex(){
        return "Viewing posts";
    }
//
//
//    @GetMapping("/posts/{id}")
//    @ResponseBody
//    public String postId(@PathVariable long id){
//        return "post#: "+ id;
//    }
//
//
//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String viewCreatedPost(){
//        return "<form method=\"post\">"+
//                    "<div class=\"form-group\">"+
//                        "<label for=\"username\">Username</label>"+
//                        "<input id=\"username\" name=\"username\" class=\"form-control\" type=\"text\">"+
//                    "</div>"+
//                    "<div class=\"form-group\">"+
//                        "<label for=\"password\">Password</label>"+
//                        "<input id=\"password\" name=\"password\" class=\"form-control\" type=\"text\">"+
//                    "</div>"+
//                    "<input type=\"submit\" class=\"btn btn-primary btn-block\" value=\"Log In\">"+
//                "</form>";
//    }
//
//
//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String createPost(@RequestParam String username , @RequestParam String password){
//       return "<p>username: "+username+"</p><p>password: " +password+"</p>";
//    }

    @GetMapping("/posts/show/{n}")
    public String showProduct(@PathVariable int n, Model model){
    List<Post> posts = new ArrayList<>(Arrays.asList(
        new Post( 1L,"car","i am selling a car" ),
        new Post( 2L,"candy","i am selling a candy" ),
        new Post( 3L,"toy","i am selling a toy" )

    ));
//    model.addAttribute("index",n);
    model.addAttribute("posts", posts);


//    List<Post> filteredProductsList = posts
//    .stream()
//    .filter(post -> post.getId()==n)
//    .collect(Collectors.toList());
//    model.addAttribute("index", filteredProductsList);

    return "posts/show";
    }


    @GetMapping("/posts/index")
    public String getProductIndexPage(Model model){
        List<Post> posts = new ArrayList<>(Arrays.asList(
                new Post( "mouse","i am selling a mouse" ),
                new Post( "computer","i am selling a computer" ),
                new Post( "house","i am selling a house" )

        ));
        model.addAttribute("posts", posts);
//        List<Product> filteredProductsList = products
//                .stream()
//                .filter(product -> product.getPriceInCents()<1000)
//                .collect(Collectors.toList());
//        model.addAttribute("products", filteredProductsList);


        return "posts/index";
    }





}
