package com.csye6225.demo.controllers;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;
import java.util.UUID;

public class test {
    public static void main(String[] args){
        AmazonS3 amazonS3=new AmazonS3Client();
        List<Bucket> bucketList=amazonS3.listBuckets();
        for (Bucket b:bucketList
             ) {
            System.out.println(b.getName());
        }
    }
}
