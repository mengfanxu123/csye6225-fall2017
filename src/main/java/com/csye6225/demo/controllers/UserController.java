package com.csye6225.demo.controllers;

/*
 *  Shuangshuang Xu  001239511 xu.shua@husky.neu.edu
 *  Mengfei Zhang    001115548 zhang.mengf@husky.neu.edu
 *  Mengfan Xu       001238833 xu.mengf@husky.neu.edu
 *  YeHui Rong       001957596 rong.ye@husky.neu.edu
 */

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.csye6225.demo.auth.BCryptPasswordEncoderBean;
import com.csye6225.demo.pojo.User;
import com.csye6225.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/register")
    public @ResponseBody String register (@RequestBody User user){

        boolean flag=false;
        List<User> userList = (List<User>) userRepository.findAll();

        for (User a: userList) {
            if (a.getEmail().equals(user.getEmail())) {
                flag = true;
                break;
            }
        }

        if(flag){
            return "The account already exists";
        } else {
            User newuser = new User();
            BCryptPasswordEncoderBean bCryptPasswordEncoder=new BCryptPasswordEncoderBean();

            AmazonSimpleEmailService client = new AmazonSimpleEmailServiceClient();
            VerifyEmailAddressRequest verifyEmailAddressRequest=new VerifyEmailAddressRequest();
            verifyEmailAddressRequest.setEmailAddress(user.getEmail());
            client.verifyEmailAddress(verifyEmailAddressRequest);

            String passwordSafe= bCryptPasswordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
            newuser.setEmail(user.getEmail());
            newuser.setPassword(passwordSafe);
            userRepository.save(newuser);
            return "Saved";
        }

    }

    @GetMapping(path = "/{id}")
    public @ResponseBody User getAccount(@PathVariable(name = "id") Long id){
        User user=userRepository.findOne(id);
        return user;

    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllAccount(){
        return userRepository.findAll();
    }

    @PostMapping(path="/login")
    public @ResponseBody String login (@RequestBody User user){

        boolean flag=false;
        List<User> userList = (List<User>)userRepository.findAll();
        BCryptPasswordEncoderBean bCryptPasswordEncoder=new BCryptPasswordEncoderBean();
        for (User a: userList) {
            if (a.getEmail().equals(user.getEmail())&&bCryptPasswordEncoder.bCryptPasswordEncoder().matches(user.getPassword(),a.getPassword())) {
                flag = true;
                break;
            }
        }
       if(flag) {
            return "welcome "+ user.getEmail();

       }else {

            return "Sorry,You entered the wrong email or password";
        }

    }

}
