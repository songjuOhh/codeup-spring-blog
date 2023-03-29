package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PostController {


    // Dependency Injection
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }



    @GetMapping("/posts/create")
    public String postIndex(){
        return "posts/create";
    }

//    @GetMapping("posts/create")
//    public String createPost(){
//        Post product = new Post("Cat","said Meow");
//        postDao.save(product);
//        return "redirect:/posts";
//    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam String title , @RequestParam String body){
        System.out.println(title);
        System.out.println(body);
        Post post = new Post(title,body);
        postDao.save(post);
        return "redirect:/posts/index"; // go to controller
//        return "<p>Post: "+name+"</p><p>Price: " +price+"</p>";
    }


    @GetMapping("/posts/index")
    public String getPostIndexPage(Model model){
//        List<Post> posts = new ArrayList<>(Arrays.asList(
//            new Post(1L, "mouse",800 ),
//            new Post(2L, "computer",50000 ),
//            new Post(3L, "house",3000000 )
//
//        ));
        List<Post> posts = postDao.findAll();


        model.addAttribute("posts", posts);
//        List<Post> filteredPostsList = posts
//                .stream()
//                .filter(product -> product.getPriceInCents()<1000)
//                .collect(Collectors.toList());
//        model.addAttribute("posts", filteredPostsList);

        return "posts/index";
    }

    @GetMapping("posts/delete/{n}")
    public String deletePost(@PathVariable long n){
        postDao.deleteById(n);
        return "redirect:/posts/index";
    }





    @GetMapping("/posts/find/{id}")
    public String findPostById(@PathVariable long id , Model model){
        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);
//        if(post.isPresent()){
//            System.out.println(post.get());
//        }
        return "posts/show";
    }


}



