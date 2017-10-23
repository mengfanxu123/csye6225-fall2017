package com.csye6225.demo.controllers;

import com.csye6225.demo.repository.AttachmentRpository;
import com.csye6225.demo.repository.TaskRepository;
import com.csye6225.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.csye6225.demo.pojo.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tasks/{id}")
public class AttachmentController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AttachmentRpository fileRpository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/attachments")
    public  @ResponseBody Set<Attachment> detailFile(@PathVariable(name="id")String id,HttpServletResponse response)throws Exception{

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
            Set<Attachment> file = new HashSet<>();
            file = task.getFile();
            return file;
        }else{
            response.addHeader("403 Forbidden","Basic realm=Forbidden");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }



    @PostMapping(value = "/attachments")
    public  @ResponseBody Attachment addFile(@RequestBody Attachment attachment, @PathVariable(name="id")String id,HttpServletResponse response)throws Exception{



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
            attachment.setTask(task);
            fileRpository.save(attachment);
            return attachment;
        } else {
            response.addHeader("403 Forbidden","Basic realm=Forbidden");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @DeleteMapping("/attachments/{idAttachments}")
    public  void deleteFile(@PathVariable(name="idAttachments")String idAttachments,@PathVariable(name="id")String id,HttpServletResponse response)throws Exception{
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
            fileRpository.delete(idAttachments);
        }else{
            response.addHeader("403 Forbidden","Basic realm=Forbidden");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
