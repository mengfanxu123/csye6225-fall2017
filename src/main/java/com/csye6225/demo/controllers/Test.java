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

        AmazonS3 amazonS3=new AmazonS3Client();
        List<Bucket> buckets=amazonS3.listBuckets();
        String name="s";
        int count=0;
        for (Bucket b:buckets) {
            name=b.getName();
            count++;
            if(count==2){

                break;
            }
        }
        System.out.print(name);
    }
}
