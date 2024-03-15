package com.example.quiz1.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.quiz1.demo.models.Category;
import com.example.quiz1.demo.models.Food;
import com.example.quiz1.demo.repositories.CategoryRepository;
import com.example.quiz1.demo.repositories.FoodRepository;

@RestController
@RequestMapping("/")
public class categoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("")
    public ModelAndView getCategories() {
        ModelAndView mav = new ModelAndView("list-category.html");
        List<Category> categories = this.categoryRepository.findAll();
        mav.addObject("categories", categories);
        return mav;
    }

    @GetMapping("addCategory")
    public ModelAndView addCategory() {
        ModelAndView mav = new ModelAndView("addCategory.html");
        Category newCategory = new Category();
        mav.addObject("category", newCategory);
        return mav;

    }

    @PostMapping("addCategory")
    public String saveCategoryt(@ModelAttribute Category category) {
        this.categoryRepository.save(category);
        return "Added";
    }

    @GetMapping("addFood")
    public ModelAndView addFood() {
        ModelAndView mav = new ModelAndView("addFood.html");
        List<Category> allCategories = this.categoryRepository.findAll();
        mav.addObject("allCategories", allCategories);
        Food newFood = new Food();
        mav.addObject("food", newFood);
        return mav;

    }

    @PostMapping("addFood")
    public String saveFood(@ModelAttribute Food food) {
        this.foodRepository.save(food);
        return "Added";
    }

    @GetMapping("/category/{id}")
    public ModelAndView getCategory(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("list-food.html");

        List<Food> foods = this.foodRepository.findAllByCategoryId(id);
        mav.addObject("foods", foods);

        return mav;
    }

}
