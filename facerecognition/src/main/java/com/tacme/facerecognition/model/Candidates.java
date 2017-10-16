package com.tacme.facerecognition.model;

import java.io.Serializable;

public class Candidates implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2043219103015671026L;

	private String personId;

	private String confidence;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	@Override
	public String toString() {
		return "ClassPojo [personId = " + personId + ", confidence = "
				+ confidence + "]";
	}
}
