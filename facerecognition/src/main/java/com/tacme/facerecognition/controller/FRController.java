/**
 * 
 */
package com.tacme.facerecognition.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tacme.facerecognition.model.PersonInfo;
import com.tacme.facerecognition.service.FDService;

/**
 * @author akhilajay
 *
 */
@CrossOrigin
@RestController
public class FRController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FDService fdService;

	@GetMapping("/identify")
	public PersonInfo identifyPerson() throws IOException {
		LOGGER.info("prosessing.........");
		PersonInfo personInfo =  fdService.execute();
		LOGGER.info("prosessing completed.........");
		return personInfo;
	}

	@GetMapping("/test")
	public String test() {	
		return "API Working ";
	}

}
