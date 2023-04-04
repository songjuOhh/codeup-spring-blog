package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.models.Users;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {


    // Dependency Injection
    private final PostRepository postDao;
    private final UserRepository userDao;

    private final EmailService emailService;


    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }




    @GetMapping("posts/create/cat")
    public String createCat(){
        User user = userDao.findById(1);

        Post product = new Post("Cat","said Meow", user);
        postDao.save(product);
        return "redirect:/posts/index";
    }

//Form Model Binding
    @GetMapping("/posts/create")
    public String showCreate(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){

        User user = Users.randomUser(userDao);
        post.setUser(user);
        emailService.prepareAndSend(post);
        postDao.save(post);
        return "redirect:/posts/index"; // go to controller
//        return "<p>Post: "+name+"</p><p>Price: " +price+"</p>";
    }

//    @GetMapping("/posts/create")
//    public String postIndex(){
//        return "posts/create";
//    }

//
//    @PostMapping("/posts/create")
//    public String createPost(@RequestParam String title , @RequestParam String body){
//        System.out.println(title);
//        System.out.println(body);
//        User user = userDao.findById(1);
//        Post post = new Post(title,body,user);
//        postDao.save(post);
//        return "redirect:/posts/index"; // go to controller
////        return "<p>Post: "+name+"</p><p>Price: " +price+"</p>";
//    }


    @GetMapping("/posts/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model){
//        Post post = postDao.findById(id);
        model.addAttribute("post", postDao.findById(id).get());
        return "posts/edit";
    }


    @PostMapping("/posts/{id}/edit")
    public String editPost( @ModelAttribute Post post, @PathVariable Long id){
        Post editedPost = postDao.findById(id).get(); // Getting data from the database first
        editedPost.setTitle(post.getTitle());
        editedPost.setBody(post.getBody());

//        User user = userDao.findById(1L);
//        post.setUser(user);

//        Post post = new Post(id,"Dog","woof, woof, wooooof!!!",user);
        postDao.save(editedPost);
        return "redirect:/posts/index"; // go to controller
//        return "<p>Post: "+name+"</p><p>Price: " +price+"</p>";
    }


//    @GetMapping("/posts/edit")
//    public String editPost(){
//        User user = userDao.findById(2);
//        Post post = new Post(1L,"Dog","woof, woof, wooooof!!!",user);
//        postDao.save(post);
//        return "redirect:/posts/index"; // go to controller
////        return "<p>Post: "+name+"</p><p>Price: " +price+"</p>";
//    }


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
    public String findPostById(@PathVariable long id , Model model) {
//        Optional<Post> optionalPost = postDao.findById(id);
        Post post = postDao.findById(id).get();
        if (post.getId()==null) {
            return "posts/index";
        }

        model.addAttribute("post", post);
        return "posts/show";
    }


}



