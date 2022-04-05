package com.example.awsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

     //make available to inject this class with other
    @Bean
    public AmazonS3 amazonS3(){
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "AKIAXTLTRJ2N4N3G5ZZK", "Dl++hx2ROUy6ixZZdbKMzI6jYZOGSzsx0oh+khHK"
        );

        return AmazonS3ClientBuilder.standard().withRegion("us-east-1") // its important to add with region
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
