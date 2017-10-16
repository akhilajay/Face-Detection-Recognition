package com.tacme.facerecognition.model;

import java.io.Serializable;

public class PersonInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2854587298388916875L;

	private String name;

	private String personId;

	private String userData;

	private String[] persistedFaceIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public String[] getPersistedFaceIds() {
		return persistedFaceIds;
	}

	public void setPersistedFaceIds(String[] persistedFaceIds) {
		this.persistedFaceIds = persistedFaceIds;
	}

	@Override
	public String toString() {
		return "ClassPojo [name = " + name + ", personId = " + personId
				+ ", userData = " + userData + ", persistedFaceIds = "
				+ persistedFaceIds + "]";
	}
}