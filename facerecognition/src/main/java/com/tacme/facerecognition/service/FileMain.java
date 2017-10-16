/**
 * 
 */
package com.tacme.facerecognition.service;

import java.io.File;

/**
 * @author TACME
 *
 */
public class FileMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath ="C:/Users/TACME/Desktop/python-object-detection/faces/face.jpg";
		File f = new File(filePath);
		System.out.println(f.exists());
		f.delete();
		System.out.println(f.exists());

	}

}
