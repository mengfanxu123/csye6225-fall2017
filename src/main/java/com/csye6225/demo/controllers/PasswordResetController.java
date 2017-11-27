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

//publish to an SNS topic
        String msg = email;
<<<<<<< HEAD
        String topicArn="arn:aws:sns:us-east-1:631563744355:password_reset";
=======

        String topicArn=snsClient.createTopic("password_reset").getTopicArn();
>>>>>>> a3213df597f34d799c47386e2af8f190c1353688
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        PublishResult publishResult = snsClient.publish(publishRequest);

    }
}
