package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {


//    @GetMapping("/products/create")
//    public String postIndex(){
//        return "products";
//    }
//
//
//
//    @PostMapping("/products/create")
//    public String createPost(@RequestParam String name , @RequestParam int price){
//        System.out.println(name);
//        System.out.println(price);
////        return "<p>Product: "+name+"</p><p>Price: " +price+"</p>";
//        return "redirect:/";
//    }

    @GetMapping("/products/create")
    public String postIndex(){
        return "products/create";
    }



    @PostMapping("/products/create")
    public String createPost(@RequestParam String name , @RequestParam int price){
        System.out.println(name);
        System.out.println(price);
//        return "<p>Product: "+name+"</p><p>Price: " +price+"</p>";
        return "redirect:/";
    }


    @GetMapping("/products")
    public String getProductIndexPage(Model model){
        List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1L, "mouse",800 ),
            new Product(2L, "computer",50000 ),
            new Product(3L, "house",3000000 )

        ));
        model.addAttribute("products", products);
//        List<Product> filteredProductsList = products
//                .stream()
//                .filter(product -> product.getPriceInCents()<1000)
//                .collect(Collectors.toList());
//        model.addAttribute("products", filteredProductsList);


        return "products/index";
    }


}
