package com.csye6225.demo.controllers;


import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.regions.*;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.csye6225.demo.repository.AttachmentRpository;
import com.csye6225.demo.repository.TaskRepository;
import com.csye6225.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.csye6225.demo.pojo.*;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

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
    public  @ResponseBody Attachment addFile(@RequestBody Attachment attachment1, @PathVariable(name="id")String id, HttpServletResponse response)throws Exception{

        AmazonS3 amazonS3=new AmazonS3Client();
        List<Bucket> buckets=amazonS3.listBuckets();
        String bucketName="s";
        int count=0;
        for (Bucket b:buckets) {
            bucketName=b.getName();
            count++;
            if(count==2){
                break;
            }
        }
        String key = "MyFile"+ UUID.randomUUID();

        String url="https://s3.amazonaws.com/"+bucketName+"/"+key;

        File file =new File(attachment1.getUrl());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=new User();
        Attachment attachment=new Attachment();
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
            attachment.setUrl(url);
            fileRpository.save(attachment);
            amazonS3.putObject(new PutObjectRequest(bucketName,key,file));

            return attachment;
        } else {
            response.addHeader("403 Forbidden","Basic realm=Forbidden");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @DeleteMapping("/attachments/{idAttachments}")
    public  void deleteFile(@PathVariable(name="idAttachments")String idAttachments,@PathVariable(name="id")String id,HttpServletResponse response)throws Exception{

        AmazonS3 amazonS3=new AmazonS3Client();
        List<Bucket> buckets=amazonS3.listBuckets();
        String bucketName="s";
        int count=0;
        for (Bucket b:buckets) {
            bucketName=b.getName();
            count++;
            if(count==2){
                break;
            }
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
        Task task=taskRepository.findOne(id);
        if(task.getUser().equals(user)) {
            String url=fileRpository.findOne(idAttachments).getUrl();
            String [] a=url.split("/");
            String key=a[a.length-1];
            amazonS3.deleteObject(bucketName,key);
            fileRpository.delete(idAttachments);

        }else{
            response.addHeader("403 Forbidden","Basic realm=Forbidden");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
