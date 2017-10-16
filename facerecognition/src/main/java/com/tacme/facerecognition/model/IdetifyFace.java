package com.tacme.facerecognition.model;

import java.io.Serializable;

public class IdetifyFace implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5466719944441639447L;

	private String faceId;

	private Candidates[] candidates;

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public Candidates[] getCandidates() {
		return candidates;
	}

	public void setCandidates(Candidates[] candidates) {
		this.candidates = candidates;
	}

	@Override
	public String toString() {
		return "ClassPojo [faceId = " + faceId + ", candidates = " + candidates
				+ "]";
	}
}
