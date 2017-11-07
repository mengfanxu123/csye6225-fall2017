package com.csye6225.demo.controllers;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        Properties prop =new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/application-aws.properties"));
            System.out.println(prop.getProperty("bucketName"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
