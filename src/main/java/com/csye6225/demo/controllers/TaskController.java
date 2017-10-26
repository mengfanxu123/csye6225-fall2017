package com.csye6225.demo.controllers;


import com.csye6225.demo.pojo.Task;
import com.csye6225.demo.repository.*;
import com.google.gson.JsonObject;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.csye6225.demo.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/{id}")
    public  @ResponseBody Task detailTask(@PathVariable(name="id")String id,HttpServletResponse response) throws Exception{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=new User();
        List<User> userList = (List<User>)userRepository.findAll();
        for (com.csye6225.demo.pojo.User u:userList) {
            if(username.equals(u.getEmail())){
                user=u;
                break;
            }
        }
        Task task=taskRepository.findOne(id);
        if(task.getUser().equals(user)) {
            return task;
        }else{
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @GetMapping
    public  @ResponseBody List<Task> allTask(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=new User();
        List<User> userList = (List<User>)userRepository.findAll();
        for (com.csye6225.demo.pojo.User u:userList) {
            if(username.equals(u.getEmail())){
                user=u;
                break;
            }
        }
        List<Task>taskList=(List<Task>) taskRepository.findAll();

        List<Task>task=new ArrayList<Task>() ;

        for (Task t:taskList) {
            if(t.getUser().equals(user)){
                task.add(t);
            }
        }
        return task;
    }

    @PostMapping
    public @ResponseBody Task addTask(@RequestBody Task task,HttpServletResponse response){

        if(task.getDescription().length()>20){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user=new User();
        List<User> userList = (List<User>)userRepository.findAll();
        for (com.csye6225.demo.pojo.User u:userList) {
            if(username.equals(u.getEmail())){
                user=u;
                break;
            }
        }
        task.setUser(user);
            taskRepository.save(task);
            return task;

    }

    @PutMapping("/{id}")
    public  @ResponseBody Task updateTask(@RequestBody Task task,@PathVariable(name="id")String id,HttpServletResponse response){
        if(task.getDescription().length()>4096){
            response.addHeader("403 Forbidden","Basic realm=Forbidden");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=new User();
        List<User> userList = (List<User>)userRepository.findAll();
        for (com.csye6225.demo.pojo.User u:userList) {
            if(username.equals(u.getEmail())){
                user=u;
                break;
            }
        }
        Task task1=taskRepository.findOne(id);
        if(task1.getUser().equals(user)) {
            Task uptask=new Task();
            uptask.setId(id);
            uptask.setDescription(task.getDescription());
            uptask.setUser(user);
            taskRepository.save(uptask);
            return task1;
        }else{
            response.addHeader("403 Forbidden","Basic realm=Forbidden");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }


    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name="id")String id,HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=new User();
        List<User> userList = (List<User>)userRepository.findAll();
        for (com.csye6225.demo.pojo.User u:userList) {
            if(username.equals(u.getEmail())){
                user=u;
                break;
            }
        }
        Task task=taskRepository.findOne(id);
        if(task.getUser().equals(user)) {
            taskRepository.delete(id);
        }else{
            response.addHeader("403 Forbidden","Basic realm=Forbidden");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

    }
}
