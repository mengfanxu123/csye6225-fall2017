package com.csye6225.demo.controllers;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        AmazonS3 amazonS3=new AmazonS3Client(new BasicAWSCredentials("AKIAJZEYT3624WSNBEGA","uQpsuS7IQHXqO0AgBxeH8KVGgs2HhC9O4JwgAku6"));
        List<Bucket>buckets=amazonS3.listBuckets();
        for (Bucket b:buckets
             ) {
            System.out.println(b.getName());
        }
    }
}
