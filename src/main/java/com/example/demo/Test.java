package com.example.demo;

import java.io.File;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

//http://jamesabrannan.com/2019/04/19/amazons-aws-s3-java-api-2-0-using-spring-boot-as-client/
public class Test {
	public static void main(String[] args) {
		String acessKey = "user-access-key";
		String secretKey = "user-secret-key";
		String bucketName = "bucket-name";

		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(acessKey, secretKey);
		S3Client s3Client = S3Client.builder().credentialsProvider(StaticCredentialsProvider.create(awsCreds))
				.region(Region.AP_SOUTH_1).build();

		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key("test-file-key")
				.acl(ObjectCannedACL.PUBLIC_READ).build();

		File file = new File("C:\\D_Drive\\Test123.txt");

		s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
	}

}
