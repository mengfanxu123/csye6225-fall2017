package com.csye6225.demo.controllers;


import com.csye6225.demo.pojo.Account;
import com.csye6225.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/user")
public class AccountController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/register")
    public @ResponseBody String register (@RequestParam String email,@RequestParam String password){

        boolean flag=false;
        List<Account>accountList= (List<Account>)userRepository.findAll();

        for (Account a:accountList) {
            if (a.getEmail().equals(email)) {
                flag = true;
                break;
            }
        }

        if(flag){
            return "The account already exists";
        }
        else {
            Account account = new Account();
            String passwordSafe= BCrypt.hashpw(password,BCrypt.gensalt());
            account.setEmail(email);
            account.setPassword(passwordSafe);
            userRepository.save(account);
            return "Saved";
        }

    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Account> getAllAccount(){
        return userRepository.findAll();
    }

    @GetMapping(path="/login")
    public @ResponseBody String login (@RequestParam String email,@RequestParam String password){

        boolean flag=false;
        List<Account>accountList= (List<Account>)userRepository.findAll();

        for (Account a:accountList) {
            if (a.getEmail().equals(email)&&BCrypt.checkpw(password ,a.getPassword())) {
                flag = true;
                break;
            }
        }
       if(flag) {
            return "welcome "+ email;

       }else {

            return "Sorry,You entered the wrong email or password";
        }

    }


}
