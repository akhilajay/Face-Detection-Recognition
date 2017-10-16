package com.tacme.facerecognition.model;

import java.io.Serializable;

public class DetectFace implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2504307990460060391L;

	private String faceId;

	private FaceRectangle faceRectangle;

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public FaceRectangle getFaceRectangle() {
		return faceRectangle;
	}

	public void setFaceRectangle(FaceRectangle faceRectangle) {
		this.faceRectangle = faceRectangle;
	}

	@Override
	public String toString() {
		return "ClassPojo [faceId = " + faceId + ", faceRectangle = "
				+ faceRectangle + "]";
	}
}