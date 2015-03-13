package com.realtimeverification.app.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.widget.EditText;

import com.realtimeverification.app.R;
import com.realtimeverification.app.backend.HttpGetPost;
import com.realtimeverification.app.backend.NetworkConnectivity;
import com.realtimeverification.app.custom.CustomAlertDialog;
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
	private ProgressDialog progressDialog;
	private List<NameValuePair> loginData;
	private Intent intentLogIn;
	private Boolean isConnectedToInternet;
	private NetworkConnectivity networkConnectivity;
	private CustomAlertDialog alert = new CustomAlertDialog();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		txtUsername = (EditText) findViewById(R.id.edit_sign_in_username);
		txtPassword = (EditText) findViewById(R.id.edit_sign_in_password);
		intentLogIn = new Intent(this, ActivityMain.class);
	}

	private boolean setUpInternetConnection() {
		networkConnectivity = new NetworkConnectivity(getApplicationContext());
		isConnectedToInternet = networkConnectivity.isConnectedToInternet();
		if (!isConnectedToInternet) {
			alert.showAletrDialog(ActivitySignIn.this, "Internet Connection error",
					"Please connect to a working Internet Connection", false);
			return false;
		} else {
			return true;
		}
	}

	public void onClickSignIn(View view) {
		if (view.getId() == R.id.btnSignIn) {
			String u = txtUsername.getText().toString();
			String p = txtPassword.getText().toString();

			loginData = new ArrayList<NameValuePair>();
			loginData.add(new BasicNameValuePair(GlobalVariables.USERNAME, u));
			loginData.add(new BasicNameValuePair(GlobalVariables.PASSWORD, p));

			//TODO: Decode Password
		}
	}

	public String getLoginMessage(int count) {
		Resources res = getResources();

		String[] messages = res.getStringArray(R.array.loginMessage);
		String msg = "";
		try {
			msg = messages[count];
		} catch (Exception e) {

		}
		return msg;
	}

	private class SignIn extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (!setUpInternetConnection()) {
				return;
			}
			progressDialog = new ProgressDialog(ActivitySignIn.this);
			progressDialog.setMessage(Html.fromHtml("Registering user..."));
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			response = HttpGetPost.POST(getString(R.string.login), loginData);

			return null;
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			int cnt = Integer.parseInt(response);
			progressDialog.dismiss();

			if (response.equals("3")) {
				startActivity(intentLogIn);
			} else {
				alert.showAletrDialog(ActivitySignIn.this, getString(R.string.alert_login),
						getLoginMessage(cnt), false);
			}
		}
	}
}
