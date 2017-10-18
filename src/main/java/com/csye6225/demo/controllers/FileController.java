package com.csye6225.demo.controllers;

import com.csye6225.demo.repository.FileRpository;
import com.csye6225.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.csye6225.demo.pojo.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tasks/{id}")
public class FileController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FileRpository fileRpository;

    @GetMapping("/attachments")
    public  @ResponseBody Set<File> detailFile(@PathVariable(name="id")String id){
        List<Task> taskList=(List<Task>) taskRepository.findAll();
        Set<File> file =new HashSet<>();


        for (Task t:taskList) {
            if (t.getId().equals(id)) {
                file=t.getFile();
            }
        }
        return file;
    }

    @PostMapping("/attachments")
    public  @ResponseBody File addFile(@RequestBody File file,@PathVariable(name="id")String id){
        List<Task> taskList=(List<Task>) taskRepository.findAll();
        Task task=new Task();
        for (Task t:taskList) {
            if(t.getId().equals(id)){
                task=t;
                break;
            }
        }
        file.setTask(task);
        fileRpository.save(file);

        return file;
    }

    @DeleteMapping("/attachments/{idAttachments}")
    public  void deleteFile(@PathVariable(name="idAttachments")String idAttachments){
        fileRpository.delete(idAttachments);
    }
}
