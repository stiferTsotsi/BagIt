package com.realtimeverification.app.backend;

import android.app.Activity;
import android.content.Context;
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
public class Get {

	public Get() {
	}

	public static Integer getResponseResult(String url, Context context) {

		int result = 0;

		HttpGet httpGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();

		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String response = EntityUtils.toString(httpResponse.getEntity());
				result = Integer.parseInt(response);
			}

		} catch (IOException ex) {
			Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

		return result;
	}
}
