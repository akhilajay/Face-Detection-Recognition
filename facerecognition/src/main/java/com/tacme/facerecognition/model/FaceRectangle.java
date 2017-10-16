package com.tacme.facerecognition.model;

import java.io.Serializable;

public class FaceRectangle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5532112778589327534L;

	private String height;

	private String width;

	private String left;

	private String top;

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	@Override
	public String toString() {
		return "ClassPojo [height = " + height + ", width = " + width
				+ ", left = " + left + ", top = " + top + "]";
	}
}
