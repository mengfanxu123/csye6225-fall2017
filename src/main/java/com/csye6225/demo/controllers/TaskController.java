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
    public  @ResponseBody Task detailTask(@PathVariable(name="id")String id){


        Task task=taskRepository.findOne(id);
        return task;
    }

    @GetMapping
    public  @ResponseBody Iterable<Task> allTask(){
        return taskRepository.findAll();
    }

    @PostMapping
    public @ResponseBody Task addTask(@RequestBody Task task){


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
    public  @ResponseBody Task updateTask(@RequestBody Task task,@PathVariable(name="id")String id){
        Task uptask=new Task();
        uptask.setId(id);
        uptask.setDescription(task.getDescription());
        taskRepository.save(uptask);
        return task;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name="id")String id){
        taskRepository.delete(id);
    }
}
