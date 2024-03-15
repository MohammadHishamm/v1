package com.example.quiz1.demo.controllers;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.util.List;

import com.example.quiz1.demo.models.User;
import com.example.quiz1.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/User")
public class signupController {
     @Autowired
    private UserRepository userRespository;

    // @GetMapping("")
    // public ModelAndView getUsers() {
    //     ModelAndView mav = new ModelAndView("list-users.html");
    //     List<User> users = this.userRespository.findAll();
    //     mav.addObject("users", users);
    //     return mav;
    // }









    @GetMapping("Registration")
    public ModelAndView addUser() {
        ModelAndView mav = new ModelAndView("registration.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("Registration")
    public String saveFruit(@ModelAttribute User user) {
        String encoddedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(encoddedPassword);
        this.userRespository.save(user);
        return "Added";
    }

    @GetMapping("Login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("Login")
    public RedirectView loginProcess(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {



        User dbUser = this.userRespository.findByUsername(username);
        Boolean isPasswordMatched = BCrypt.checkpw(password, dbUser.getPassword());
        if (isPasswordMatched){
        // return new RedirectView("Login");
        session.setAttribute( "username", dbUser.getUsername());//session
        
        // return "Welcome " + session.getAttribute("username");

        return new RedirectView("Profile");
        }
        return null;
       
    }

    @GetMapping("Profile")
    public ModelAndView viewProfile(HttpSession session) {
    ModelAndView mav = new ModelAndView("Profile.html");
    mav.addObject("username", (String) session.getAttribute( "username"));
    return mav;
    
    }

    @GetMapping("signout")
    public RedirectView signout(HttpSession session) {
   session.invalidate();
  return new RedirectView("/");
}




}
