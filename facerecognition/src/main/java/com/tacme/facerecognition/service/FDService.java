/**
 * 
 */
package com.tacme.facerecognition.service;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tacme.facerecognition.model.DetectFace;
import com.tacme.facerecognition.model.IdetifyFace;
import com.tacme.facerecognition.model.PersonInfo;

/**
 * @author akhilajay
 *
 */

@Service("fdService")
public class FDService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FileUtils fileUtils;
	private final String subscriptionKey = "822ca3063f504f3d914344f7b2b2c229";
	private final String detectURI = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect";
	private final String identifyURI = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/identify";
	private String personDetailsURI = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups/{personGropId}/persons/{personId}";

	CloseableHttpClient httpclient = HttpClients.createDefault();

	@Value("${faceImgePath}")
	private String faceImgePath;

	@Value("${persongourpId}")
	private String persongourpId;

	public PersonInfo execute() {
		PersonInfo personInfo = new PersonInfo();

		if (fileUtils.isFileExits()) {
			String faceId = detectFace();
			if (faceId.trim().length() > 0) {
				String personId = detectFacesFromlist(faceId);
				if (personId.trim().length() > 0) {
					personInfo = detectNameFromPersonList(personId);
				} else {
					personInfo.setName("Anonymous");
				}
			}
			fileUtils.deleteFile();
		}
		try {
		} catch (Exception ex) {
			LOGGER.error("error while closing http connection ::"
					+ ex.getMessage());
		}
		return personInfo;
	}

	private String detectFace() {
		LOGGER.info("detecting face.....");

		String detectResponse = "";

		try {
			URIBuilder builder = new URIBuilder(detectURI);
			// Prepare the URI for the REST API call.
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);

			// Request headers.
			request.setHeader("Content-Type", "application/octet-stream");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
			request.setEntity(new FileEntity(fileUtils.readFile()));
			HttpResponse response = httpclient.execute(request);
			LOGGER.info("response is :"
					+ response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				detectResponse = EntityUtils.toString(entity).trim();
				if (detectResponse.charAt(0) == '[') {
					LOGGER.info("face detected..");
					Gson gson = new Gson();
					Type collectionType = new TypeToken<Collection<DetectFace>>() {
					}.getType();
					Collection<DetectFace> detectFace = gson.fromJson(
							detectResponse, collectionType);
					if (detectFace.size() >= 1) {
						String fid = detectFace.iterator().next().getFaceId();
						LOGGER.info("face id is ::" + fid);
						return fid;
					}
				} else {
					LOGGER.error("unexpected response from detect service "
							+ detectResponse);
				}
			}

		} catch (Exception ex) {
			LOGGER.error("exception from detect service " + ex.getMessage());
		}

		return "";
	}

	private String detectFacesFromlist(String faceId) {
		LOGGER.info("Identifying Person.......");
		String identifyResponse = "";

		String requestParam = "{" + "\"personGroupId\"" + ":" + "\""
				+ persongourpId + "\"" + "," + "\"faceIds\"" + ":" + "[" + "\""
				+ faceId + "\"" + "]}";
		try {
			URIBuilder builder = new URIBuilder(identifyURI);
			// Prepare the URI for the REST API call.
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
			StringEntity requestEntity = new StringEntity(requestParam);
			request.setEntity(requestEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				identifyResponse = EntityUtils.toString(entity).trim();
				if (identifyResponse.charAt(0) == '[') {
					// LOGGER.info("Person identified..");
					Gson gson = new Gson();
					Type collectionType = new TypeToken<Collection<IdetifyFace>>() {
					}.getType();
					Collection<IdetifyFace> idetifyFace = gson.fromJson(
							identifyResponse, collectionType);
					if (idetifyFace.size() >= 1) {
						if (idetifyFace.iterator().next().getCandidates().length > 0) {
							LOGGER.info("Person identified..");
							String pid = idetifyFace.iterator().next()
									.getCandidates()[0].getPersonId();
							LOGGER.info("Person id is ::" + pid);
							return pid;
						} else {
							LOGGER.info("Person not identified");
						}

					}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("exception from idetify service " + ex.getMessage());
		}

		return "";
	}

	private PersonInfo detectNameFromPersonList(String personId) {
		LOGGER.info("Fetching Person Info.......");
		String personDetails = "";

		try {
			URIBuilder builder = new URIBuilder(personDetailsURI.replace(
					"{personGropId}", persongourpId).replace("{personId}",
					personId));
			// Prepare the URI for the REST API call.
			URI uri = builder.build();
			HttpGet request = new HttpGet(uri);
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				personDetails = EntityUtils.toString(entity).trim();
				LOGGER.info("personDetails ::" + personDetails);
				if (personDetails.trim().length() > 0) {
					Gson gson = new Gson();
					PersonInfo personeInfo = gson.fromJson(personDetails,
							PersonInfo.class);
					return personeInfo;
				}
			}

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}

		return new PersonInfo();
	}

}
