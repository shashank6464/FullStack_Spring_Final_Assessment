package com.shop.service.shopService.controller;


import com.shop.service.shopService.model.Category;
import com.shop.service.shopService.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("shop")
public class ShopController {

    @Autowired
    CategoryConsumer categoryConsumer;

    @Autowired
    ProductConsumer productConsumer;


    @GetMapping("/check")
    public String check(){
        return categoryConsumer.check();
    }


    //get all products
    @GetMapping("/products")
    public List<Product> getProducts(){
        return productConsumer.getProducts();
    }

    //get all categories
    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryConsumer.getCategories();
    }


    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProductByField(@RequestBody Map<String, Object> map)  {

        try{
            return productConsumer.getProductByField(map).toString();
        }

        catch (Exception e){
            String errorResponse = e.getMessage();
            int index = errorResponse.indexOf("{\"errorCode");
            System.out.println(e.getMessage());
            return errorResponse.substring(index, errorResponse.length()-1);
        }

    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCategoriesByField(@RequestBody Map<String, Object> map)  {

        try{
            return categoryConsumer.getCategoriesByField(map).toString();
        }
        catch (Exception e){
            String errorResponse = e.getMessage();
            int index = errorResponse.indexOf("{\"errorCode");
            System.out.println(e.getMessage());
            return errorResponse.substring(index, errorResponse.length()-1);
        }


    }

}
