package com.csye6225.demo.controllers;


import com.csye6225.demo.pojo.Task;
import com.csye6225.demo.repository.*;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.csye6225.demo.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    private UserRepository userRepository;


    @GetMapping("/{id}")
    public  @ResponseBody String detailTask(@PathVariable(name="id")String id){


        Task task=taskRepository.findOne(id);
        return task.getId();
    }

    @GetMapping
    public  @ResponseBody Iterable<Task> allTask(){
        return taskRepository.findAll();
    }

    @PostMapping
    public @ResponseBody String addTask(@RequestBody Task task,HttpServletResponse response){
        taskRepository.save(task);
        return task.getId();
    }

    @PutMapping("/{id}")
    public  @ResponseBody String updateTask(@RequestBody Task task,@PathVariable(name="id")String id){
        Task uptask=new Task();
        uptask.setId(id);
        uptask.setDescription(task.getDescription());
        taskRepository.save(uptask);
        return task.getId();
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name="id")String id){
        taskRepository.delete(id);
    }
}
