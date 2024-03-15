package com.example.quiz1.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.quiz1.demo.models.User;
import com.example.quiz1.demo.models.fruit;
import com.example.quiz1.demo.repositories.FruitRepository;



@RestController
@RequestMapping("/fruits")
public class addlistController {
    @Autowired
    private FruitRepository fruitrepository;

    @GetMapping("/")
    public ModelAndView getfruits() {
        ModelAndView mav = new ModelAndView("list.html");
        List<fruit> fruits = this.fruitrepository.findAll();
        mav.addObject("fruits", fruits);

        return mav;

    }

    @GetMapping("add-fruits")
    public ModelAndView addfruits() {
        ModelAndView mav = new ModelAndView("add-form.html");
        fruit newfruits = new fruit();
        mav.addObject("fruits", newfruits);

        return mav;

    }

    @PostMapping("save-fruit") // 34an yb3t w ya5od mn el form
    public String savefruit(@ModelAttribute fruit fruits) {
        this.fruitrepository.save(fruits);
        return "Added";

    }

    @GetMapping("update-fruit/{fruitId}") // Assuming you pass the fruitId as a path variable
    public ModelAndView updateFruit(@PathVariable int fruitId) {
        // 1. Retrieve the existing fruit object that you want to update (you'll need to implement this logic)
        fruit existingFruit = fruitrepository.findById(fruitId); // Assuming you have a service method to find a fruit by ID
    
        // 2. Check if the existing fruit exists
        if (existingFruit == null) {
            // Handle case where fruit with the provided ID doesn't exist
            return new ModelAndView("error-page.html");
        }
    
        // 3. Populate the form with the existing fruit data
        ModelAndView mav = new ModelAndView("update-form.html");
        mav.addObject("fruit", existingFruit);
    
        return mav;
    }
    
    @PostMapping("update-fruit")
    public RedirectView processUpdateFruit( @ModelAttribute("fruit") fruit updatedFruit,@RequestParam("id") int ID) {
        // 1. Retrieve the existing fruit object that you want to update (you'll need to implement this logic)
        fruit existingFruit = fruitrepository.findById(ID); // Assuming you have a service method to find a fruit by ID
    
        // 2. Check if the existing fruit exists
       
    
        // 3. Update the existing fruit object with the new data
        existingFruit.setName(updatedFruit.getName());
        existingFruit.setColor(updatedFruit.getColor());
        // Update other properties as needed
    
        // 4. Save the updated fruit (you'll need to implement this logic)
        fruitrepository.save(existingFruit); // Assuming you have a service method to save/update a fruit
    
        // Redirect to a success page or back to the list of fruits
        return new RedirectView("");  // Assuming you have a mapping for listing fruits
    }

@PostMapping("delete")
    public RedirectView deleteFruit(@RequestParam("id") int ID) {
        fruit fruits = fruitrepository.findById(ID);
        
        
            fruitrepository.deleteById(fruits.getID());
            return new RedirectView(""); // Redirect to the list of fruits after deletion
       
    }




}