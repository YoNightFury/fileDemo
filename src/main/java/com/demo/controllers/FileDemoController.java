package com.demo.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileDemoController {

	@PostMapping("/")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file){
		String path = System.getProperty("user.home")+"/"+file.getOriginalFilename();
		File f = new File(path);
		try {
		file.transferTo(f);
		return ResponseEntity.ok("file saved");
		}
		catch(IOException ioex){
			return ResponseEntity.internalServerError().body(ioex.getMessage());
		}
	}
	
	
	@GetMapping("{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName){
		String path = System.getProperty("user.home")+"/"+fileName;
		Path p = Paths.get(path);
		try {
			byte[] fileByteArr = Files.readAllBytes(p);
			return ResponseEntity
					.status(HttpStatus.OK) // set status as OK
					.contentType(MediaType.IMAGE_JPEG) // response Content-Type = image/jpeg
					.body(fileByteArr); // body is the image byte array
		}catch(IOException ioex) {
			return ResponseEntity.internalServerError().body(ioex.getMessage());
		}
	}
}
