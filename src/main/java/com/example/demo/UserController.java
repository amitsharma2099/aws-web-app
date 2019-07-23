package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Controller
public class UserController {

	@Autowired
    private UserRepository userRepo;
	
	@GetMapping("")
	public ModelAndView index() {
		List<User> users = userRepo.findAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user");
		mv.addObject("allUsers", users);
		return mv;
	}

	@PostMapping("/users")
//	public ModelAndView addUser(@RequestParam("file") MultipartFile file, @RequestParam String firstName) {
	public ModelAndView addUser(@ModelAttribute UserDataWithFile userDataWithFile) throws IOException, ClassNotFoundException {

		//Store User detail in RDS
		User user = new User();
		user.setAddress(userDataWithFile.getAddress());
		user.setAge(Integer.parseInt(userDataWithFile.getAge()));
		user.setDepartment(userDataWithFile.getDepartment());
		user.setFirstName(userDataWithFile.getFirstName());
		user.setLastName(userDataWithFile.getLastName());
		user.setGender(userDataWithFile.getGender());
		
		userRepo.save(user);
		System.out.println("User data stored in database successfully.");
		
		//Store attachment in S3 bucket
		File file = new File(userDataWithFile.getFile().getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(userDataWithFile.getFile().getBytes());
		fos.close();
		
		System.out.println("File Name: "+file.getName() +", File Canonical Path: "+file.getCanonicalPath()+", File Length: "+file.length());
		System.out.println("Trying to upload file to S3 bucket...");
		uploadToAWSS3Bucket(file);
		System.out.println("File uploaded to S3 bucket successfully.");

		//Return all users to view
		List<User> users = userRepo.findAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user");
		mv.addObject("allUsers", users);
		mv.addObject("msg", "User added successfully.");

		return mv;
	}

	private void uploadToAWSS3Bucket(File file) {
		String acessKey = "user-access-key";
		String secretKey = "user-secret-key";
		String bucketName = "bucket-name";

		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(acessKey, secretKey);
		S3Client s3Client = S3Client.builder()
									.credentialsProvider(StaticCredentialsProvider.create(awsCreds))
									.region(Region.AP_SOUTH_1)
									.build();

		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
															.bucket(bucketName).key(file.getName())
															.acl(ObjectCannedACL.PUBLIC_READ).build();

		s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
	}
	
}
