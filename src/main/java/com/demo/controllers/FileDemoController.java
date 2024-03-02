package com.demo.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
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
}
