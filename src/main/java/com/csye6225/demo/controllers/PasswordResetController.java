package com.csye6225.demo.controllers;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forget-password")
public class PasswordResetController {

    @PostMapping
    public  void resetPassword(@RequestBody String email){
        AmazonSNSClient snsClient = new AmazonSNSClient(new ClientConfiguration());

//publish to an SNS topicc
        String msg = email;


        String topicArn=snsClient.createTopic("password_reset").getTopicArn();

        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        PublishResult publishResult = snsClient.publish(publishRequest);

    }
}
