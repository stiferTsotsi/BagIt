package com.realtimeverification.app.backend;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * Created by vaal on 3/4/2015.
 */
public class HttpGetPost {

	public HttpGetPost() {
	}

	public static String GET(String url) {

		String result = "";

		HttpGet httpGet = new HttpGet(url);

		HttpClient httpClient = new DefaultHttpClient();

		HttpResponse httpResponse = null;
		try {

			httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				try {
					String resp_body = EntityUtils.toString(httpResponse.getEntity());
					result = resp_body;
					Log.d("Response : ***********", " "+resp_body);
				} catch (Exception e) {
					Log.e("sometag", e.getMessage());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// Handle response back from script.
		if (httpResponse != null) {


		} else { // Error, no response.

		}



		return result;
	}

	public static Integer POST() {
		int result = 0;

		return result;
	}
}
