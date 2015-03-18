package com.realtimeverification.app.backend;

import android.content.Context;
import android.util.Log;

import com.realtimeverification.app.custom.Data;
import com.realtimeverification.app.custom.File;
import com.realtimeverification.app.custom.GlobalVariables;

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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by vaal on 3/17/2015.
 */
public class DataRetriever {

	private ArrayList<Data> data;
	private static DataRetriever sDataRetriever;
	private static final String TAG ="DATA RETRIEVER ";

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
				Log.e(TAG," Failed to download file");
			}

		} catch (ClientProtocolException e) {
			Log.e(TAG + "ClientProtocolException ",e.getMessage());
		} catch (IOException e) {
			Log.e(TAG +" IOException ",e.getMessage());
		}

		Log.d("String Builder ", " ----------  " + builder.toString());

		return builder.toString();
	}

	public ArrayList<Data> getUserData(String url) {

		data = new ArrayList<Data>();

		Data content;
		JSONObject jsonObject;
		String folderId, parentId, folderName;
		String fileId, fileName, fileUrl, docName, fileLocation;

		try {
			jsonObject = new JSONObject(getData(url));

			JSONArray folders = (JSONArray) jsonObject.get(GlobalVariables.FOLDER);
			for (int i = 0; i < folders.length(); i++) {

				folderId = (String) ((JSONObject) folders.get(i)).get(GlobalVariables.FOLDER_ID);


				parentId = (String) ((JSONObject) folders.get(i)).get(GlobalVariables.PARENT_ID);
				folderName = (String) ((JSONObject) folders.get(i)).get(GlobalVariables.FOLDER_NAME);

				ArrayList<File> fileList = new ArrayList<File>();
				JSONArray fileObject = (JSONArray) (((JSONObject) folders.get(i)).get
						(GlobalVariables.FILE));
				for (int j = 0; j < fileObject.length(); j++) {
					fileId = (String) ((JSONObject) (fileObject.get(j))).get(GlobalVariables.FILE_ID);
					docName = (String) ((JSONObject) (fileObject.get(j))).get(GlobalVariables.DOC_NAME);
					fileName = (String) ((JSONObject) (fileObject.get(j))).get(GlobalVariables
							.FILE_NAME);
					fileUrl = (String) ((JSONObject) (fileObject.get(j))).get(GlobalVariables.FILE_URL);
					fileLocation = (String) ((JSONObject) (fileObject.get(j))).get(GlobalVariables
							.FILE_LOCATION);

					fileList.add(new File(fileId, fileName, fileUrl, docName, fileLocation));
				}
				content = new Data(folderId, parentId, folderName, fileList);
				data.add(content);
			}

		} catch (Exception e) {
			Log.e(TAG + " Exception ",e.getMessage());
		}
		return data;
	}

//	public Data getContent(String folderId){
//		for(int i =0; i < data.size(); i++){
//			if(data.get(i).getData().equals(folderId)){
//				return data.get(i);
//			}
//		}
//		return null;
//	}

}
