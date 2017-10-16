/**
 * 
 */
package com.tacme.facerecognition.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author akhilajay
 *
 */
@Component
public class FileUtils {
	

	@Value("${faceImgePath}")
	private String faceImgePath;
	
	public File readFile(){
		return new File (faceImgePath);
	}
	
	public boolean isFileExits(){
		return readFile().exists();
	}
	
	public boolean deleteFile(){
		return readFile().delete();
	}

}
