package com.csye6225.demo.controllers;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forget-password")
public class PasswordResetController {

    @PostMapping
    public void resetPassword(@RequestBody String email){
        AmazonSNSClient snsClient = new AmazonSNSClient(new ClientConfiguration());

//publish to an SNS topic
        String msg = email;
        String topicArn="arn:aws:sns:us-east-1:179107530530:password_reset";
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        PublishResult publishResult = snsClient.publish(publishRequest);

    }
}
