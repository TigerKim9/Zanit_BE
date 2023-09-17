package io.cloudtype.Demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
//    @Value("${cloud.aws.credentials.accessKey}")
    @Value("AKIA2FGBVWFCC7ISEQVK")
    private String accessKey;
//    @Value("${cloud.aws.credentials.secretKey}")
    @Value("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")
    private String secretKey;
    @Value("zanit")
    private String bucketName;
    @Value("ap-northeast-2")
    private String region;

    @Bean
    public AmazonS3 s3Builder() {
        AWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(region).build();
    }
}