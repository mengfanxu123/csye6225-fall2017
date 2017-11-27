package com.csye6225.demo.controllers;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.*;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        String msg = "xushua@husky.neu.edu";
        AmazonSNSClient snsClient = new AmazonSNSClient(new ClientConfiguration());


        String topicArn=snsClient.createTopic("password_reset").getTopicArn();
       // PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        //PublishResult publishResult = snsClient.publish(publishRequest);
        System.out.print(topicArn);
    }
}
