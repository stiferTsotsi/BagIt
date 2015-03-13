package com.realtimeverification.app.ui.activities;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.realtimeverification.app.R;
import com.realtimeverification.app.custom.GlobalVariables;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaal on 3/13/2015.
 */
public class ActivitySignIn extends FragmentActivity {

	private EditText txtUsername, txtPassword;
	private String response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		txtUsername = (EditText) findViewById(R.id.edit_sign_in_username);
		txtPassword = (EditText) findViewById(R.id.edit_sign_in_password);
	}

	public void onClickSignIn(View view) {
		if (view.getId() == R.id.btnSignIn) {
			String u = txtUsername.getText().toString();
			String p = txtPassword.getText().toString();

			List<NameValuePair> loginData = new ArrayList<NameValuePair>();
			loginData.add(new BasicNameValuePair(GlobalVariables.USERNAME, u));
			loginData.add(new BasicNameValuePair(GlobalVariables.PASSWORD, p));

			//TODO: Decode Password
		}
	}

	public String getLoginMessage(int count) {
		Resources res = getResources();
		String[] messages = res.getStringArray(R.array.loginMessage);
		return messages[count];
	}

	private class SignIn extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			return null;
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
		}
	}

}
