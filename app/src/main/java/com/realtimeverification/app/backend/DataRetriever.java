package com.realtimeverification.app.backend;

import android.content.Context;

import com.realtimeverification.app.custom.Content;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by vaal on 3/17/2015.
 */
public class DataRetriever {

	private ArrayList<Content> contents;
	private static DataRetriever sDataRetriever;

	private DataRetriever(Context appContext) {
	}

	public static DataRetriever get(Context c) {
		if (sDataRetriever == null) {
			sDataRetriever = new DataRetriever(c.getApplicationContext());
		}
		return sDataRetriever;
	}

	private String getData(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();

			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;

				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				//TODO: log -- failed to download file
			}

		} catch (ClientProtocolException e) {
			//TODO: log
		} catch (IOException e) {
			//TODO: log
		}

		return builder.toString();
	}

	public ArrayList<Content> getContents(String url) {
		contents = new ArrayList<Content>();
		Content content;
		JSONObject jsonObject;
		String folerId, parentId, folderName;
		File[] files;

		try{
			jsonObject = new JSONObject(getData(url));

			JSONArray folders = (JSONArray)jsonObject.get("Folders");
			for(int i =0; i < folders.length(); i++){
				folerId = (String)((JSONObject)folders.get(i)).get("folder_id");
				parentId = (String)((JSONObject)folders.get(i)).get("parent_id");
				folderName = (String)((JSONObject)folders.get(i)).get("folder_name");
				files = (File[]) ((JSONObject)folders.get(i)).get("folder_id");
			}

		}catch (Exception e){
			//TODO: log
		}
		return null;
	}

	public Content getContent(String folderId){
		for(int i =0; i < contents.size(); i++){
			if(contents.get(i).getContents().equals(folderId)){
				return contents.get(i);
			}
		}
		return null;
	}

}
