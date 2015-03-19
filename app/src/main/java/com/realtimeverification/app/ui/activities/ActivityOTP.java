package com.realtimeverification.app.ui.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.realtimeverification.app.R;
import com.realtimeverification.app.backend.HttpGetPost;
import com.realtimeverification.app.backend.NetworkConnectivity;
import com.realtimeverification.app.custom.CustomAlertDialog;
import com.realtimeverification.app.custom.GlobalVariables;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by vaal on 3/12/2015.
 */
public class ActivityOTP extends FragmentActivity implements DialogInterface.OnClickListener{

	private EditText editTextOTP;
	private ProgressDialog progressDialog;
	private String res;
	private Intent intentToSignIn;
	private Boolean isConnectedToInternet;
	private NetworkConnectivity networkConnectivity;
	private CustomAlertDialog alert = new CustomAlertDialog();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_otp);
		editTextOTP = (EditText) findViewById(R.id.edittext_otp);
		intentToSignIn = new Intent(this,ActivitySignIn.class);
	}

	private boolean setUpInternetConnection() {
		networkConnectivity = new NetworkConnectivity(getApplicationContext());
		isConnectedToInternet = networkConnectivity.isConnectedToInternet();
		if (!isConnectedToInternet) {
			alert.showAlertDialog(ActivityOTP.this, "Internet Connection error",
					"Please connect to a working Internet Connection", false);
			return false;
		} else {
			return true;
		}
	}

	public void onClickOTP(View view) {
		if (view.getId() == R.id.btnOTP) {
			String otp = editTextOTP.getText().toString();

			if (otp.equals(GlobalVariables.OTP)) {
				if (!setUpInternetConnection() == true) {
					return;
				} else {
					new CreateNewUser().execute();
				}
			} else {
				alert.showAlertDialog(ActivityOTP.this, getString(R.string.title_otp),
						getString(R.string.incorrect_otp), false);
			}
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch(which){
			case DialogInterface.BUTTON_POSITIVE: // OK
				startActivity(intentToSignIn);
				break;
			default:
				// nothing
				break;
		}
	}

	private class CreateNewUser extends AsyncTask<String, List<NameValuePair>, String> {

		@Override
		protected void onPreExecute() {

			progressDialog = new ProgressDialog(ActivityOTP.this);
			progressDialog.setMessage(Html.fromHtml("Registering user..."));
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			res = HttpGetPost.POST(getString(R.string.register_new_client), GlobalVariables.DATA);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			progressDialog.dismiss();
			if (res.equals("1")) {

				AlertDialog ad = new AlertDialog.Builder(ActivityOTP.this)
						.setMessage(getString(R.string.opt_success_message_1) + " " + GlobalVariables
								.SIGN_UP_EMAIL.getText().toString() + " " + getString(R.string
								.opt_success_message_2))
						.setIcon(R.drawable.logo)
						.setTitle(getString(R.string.title_otp))
						.setPositiveButton("OK", ActivityOTP.this)
						.setCancelable(false)
						.create();

				ad.show();
			}else if(res.equals("0")){
//				alert.showAlertDialog(ActivityOTP.this, getString(R.string.title_otp),
//						getString(R.string.incorrect_otp),false);
			}
		}
	}

}
