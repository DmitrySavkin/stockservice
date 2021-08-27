package ru.savkin.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    public AmazonS3 s3(){
        AWSCredentials awsCredentials
                = new BasicAWSCredentials("AKIA5JWXBZMOIJGW2Y5Z",
                "NDyugjQHik9gUuc8z3ZEN2NhPnB40HDFhnZNuY95");

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
