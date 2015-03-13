package com.realtimeverification.app.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.realtimeverification.app.R;
import com.realtimeverification.app.backend.HttpGetPost;
import com.realtimeverification.app.backend.NetworkConnectivity;
import com.realtimeverification.app.custom.CustomAlertDialog;
import com.realtimeverification.app.custom.GlobalVariables;
import com.realtimeverification.app.utils.Validations;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaal on 3/4/2015.
 */
public class ActivitySignUp extends FragmentActivity {
	private ProgressDialog progressDialog;
	private String response, input, res;
	private String email, contactNo, name, password, confirmPassword;
	private Intent intentSignUp;
	private List<NameValuePair> otpData;
	private Boolean isConnectedToInternet;
	private NetworkConnectivity networkConnectivity;
	private CustomAlertDialog alert = new CustomAlertDialog();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		setUpUI();
		intentSignUp = new Intent(this, ActivityOTP.class);
		setUpInternetConnection();
	}

	private boolean setUpInternetConnection() {
		networkConnectivity = new NetworkConnectivity(getApplicationContext());
		isConnectedToInternet = networkConnectivity.isConnectedToInternet();
		if (!isConnectedToInternet) {
			alert.showAletrDialog(ActivitySignUp.this, "Internet Connection error",
					"Please connect to a working Internet Connection", false);
			return false;
		} else {
			return true;
		}
	}

	public void setUpUI() {

		GlobalVariables.SIGN_UP_FULL_NAME = (EditText) findViewById(R.id.edittext_register_full_name);
		GlobalVariables.SIGN_UP_EMAIL = (EditText) findViewById(R.id.edittext_register_email);
		GlobalVariables.SIGN_UP_CONTACT_NO = (EditText) findViewById(R.id.edittext_register_contact_no);
		GlobalVariables.SIGN_UP_PASSWORD = (EditText) findViewById(R.id.edittext_register_password);
		GlobalVariables.SIGN_UP_CONFIRM_PASSWORD = (EditText) findViewById(R.id
				.edittext_register_confirm);

		GlobalVariables.SIGN_UP_VALID_EMAIL = (TextView) findViewById(R.id
				.textview_validate_email);
		GlobalVariables.SIGN_UP_VALID_CONTACT = (TextView) findViewById(R.id
				.textview_validate_contact_no);
		GlobalVariables.SIGN_UP_VALID_PASSWORD = (TextView) findViewById(R.id
				.valid_password);
		GlobalVariables.SIGN_UP_VALID_CONFIRM_PASSWORD = (TextView) findViewById(R.id
				.valid_confirm_password);
		GlobalVariables.SIGN_UP_VALID_FULL_NAME = (TextView) findViewById(R.id.valid_full_name);

		setUpTextWatcher();
	}

	private void setUpTextWatcher() {

		//Full Name Validation
		GlobalVariables.SIGN_UP_FULL_NAME.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				name = GlobalVariables.SIGN_UP_FULL_NAME.getText().toString();

				if (name.trim().length() > 2) {
					GlobalVariables.SIGN_UP_VALID_FULL_NAME.setText("");
				} else {
					GlobalVariables.SIGN_UP_VALID_FULL_NAME.setText("*");
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		//Email Address Validation
		GlobalVariables.SIGN_UP_EMAIL.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (Validations.isValidEmail(GlobalVariables.SIGN_UP_EMAIL.getText().toString())) {
					GlobalVariables.SIGN_UP_VALID_EMAIL.setText("");

				} else {
					GlobalVariables.SIGN_UP_VALID_EMAIL.setText(R.string.invalid_email);
					return;
				}

				email = GlobalVariables.SIGN_UP_EMAIL.getText().toString();

				new HttpGetResponse().execute(getString(R.string.check_email) + email, "Email");
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (Validations.isValidEmail(GlobalVariables.SIGN_UP_EMAIL.getText().toString())) {
					GlobalVariables.SIGN_UP_VALID_EMAIL.setText("");

				} else {
					GlobalVariables.SIGN_UP_VALID_EMAIL.setText(R.string.invalid_email);
					GlobalVariables.SIGN_UP_VALID_EMAIL.setTextColor(Color.RED);
				}
			}
		});

		//Contact Number Validation
		GlobalVariables.SIGN_UP_CONTACT_NO.addTextChangedListener(new TextWatcher() {

			//Contact Number Validation
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

				InputFilter[] fArray = new InputFilter[1];

				contactNo = GlobalVariables.SIGN_UP_CONTACT_NO.getText().toString();
				if (contactNo.startsWith("0")) {
					fArray[0] = new InputFilter.LengthFilter(10);
					GlobalVariables.SIGN_UP_CONTACT_NO.setFilters(fArray);
				} else if (contactNo.startsWith("+27")) {
					fArray[0] = new InputFilter.LengthFilter(12);
					GlobalVariables.SIGN_UP_CONTACT_NO.setFilters(fArray);
				} else {
					Toast.makeText(getApplicationContext(), "Contact number can only" +
							" start with '0' or '+27'", Toast.LENGTH_SHORT).show();
				}

				if (Validations.isValidContactNo(GlobalVariables.SIGN_UP_CONTACT_NO.getText().toString())) {
					GlobalVariables.SIGN_UP_VALID_CONTACT.setText("");

				} else {
					GlobalVariables.SIGN_UP_VALID_CONTACT.setText(R.string.invalid_contact_no);
					GlobalVariables.SIGN_UP_VALID_CONTACT.setTextColor(Color.RED);
					return;
				}

				new HttpGetResponse().execute(getString(R.string.check_contact_no) + contactNo, "Contact");
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		//Password Validation
		GlobalVariables.SIGN_UP_PASSWORD.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

				password = GlobalVariables.SIGN_UP_PASSWORD.getText().toString();

				if (Validations.isValidPassword(password)) {
					GlobalVariables.SIGN_UP_VALID_PASSWORD.setText("");
				} else {
					GlobalVariables.SIGN_UP_VALID_PASSWORD.setText(R.string.invalid_password);
					GlobalVariables.SIGN_UP_VALID_PASSWORD.setTextColor(Color.RED);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		//Confirm Password Validation
		GlobalVariables.SIGN_UP_CONFIRM_PASSWORD.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				password = GlobalVariables.SIGN_UP_PASSWORD.getText().toString();
				confirmPassword = GlobalVariables.SIGN_UP_CONFIRM_PASSWORD.getText().toString();
				if (Validations.isMatchPasswords(password, confirmPassword)) {
					GlobalVariables.SIGN_UP_VALID_CONFIRM_PASSWORD.setText("");
				} else {
					GlobalVariables.SIGN_UP_VALID_CONFIRM_PASSWORD.setText(R.string.passwords_not_match);
					GlobalVariables.SIGN_UP_VALID_CONFIRM_PASSWORD.setTextColor(Color.RED);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	public void onClickSignUp(View view) {

		if (view.getId() == R.id.btnSignUp) {

			name = GlobalVariables.SIGN_UP_FULL_NAME.getText().toString();
			email = GlobalVariables.SIGN_UP_EMAIL.getText().toString();
			contactNo = GlobalVariables.SIGN_UP_CONTACT_NO.getText().toString();
			password = GlobalVariables.SIGN_UP_PASSWORD.getText().toString();
			confirmPassword = GlobalVariables.SIGN_UP_CONFIRM_PASSWORD.getText().toString();
			//Encrypt password
			password= Base64.encodeToString(password.getBytes(),Base64.DEFAULT);

				//TODO: validations - JUST SENT PASSWORD

			//Generate OTP
			int x = (int) (Math.random() * 9);
			x = x + 1;
			String otp = (x + "") + (((int) (Math.random() * 10000)) + "");

			if (otp.length() == 5) {
			} else if (otp.length() == 4) {
				otp = otp + contactNo.substring(9, 10);
			} else if (otp.length() == 3) {
				otp = otp + contactNo.substring(9, 10) + contactNo.substring(2, 3);
			} else if (otp.length() == 2) {
				otp = otp + "9" + contactNo.substring(9, 10) + contactNo.substring(2, 3);
			} //OTP

			List<NameValuePair> data = new ArrayList<NameValuePair>();
			data.add(new BasicNameValuePair(GlobalVariables.FULL_NAME, name));
			data.add(new BasicNameValuePair(GlobalVariables.EMAIL_ADDRESS, email));
			data.add(new BasicNameValuePair(GlobalVariables.CONTACT_NO, contactNo));
			data.add(new BasicNameValuePair(GlobalVariables.PASSWORD, password));

			otpData = new ArrayList<NameValuePair>();
			otpData.add(new BasicNameValuePair("otp", otp));//NameValuePair for SendOTP/RegisterClient
			otpData.add(new BasicNameValuePair("contactNo", contactNo));
			GlobalVariables.DATA = data;
			GlobalVariables.OTP = otp; // SetUp NaveValuePair for SentOTP/CreateUser

			new SendOTP().execute();
		}
	}

	private class HttpGetResponse extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			response = HttpGetPost.GET(params[0]);
			input = params[1];
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			boolean validEmail = false, validContact = false;

			if (input.equals("Email")) {
				if (response.equals("0")) {
					GlobalVariables.SIGN_UP_VALID_EMAIL.setText(R.string.available_email);
					GlobalVariables.SIGN_UP_VALID_EMAIL.setTextColor(Color.GREEN);
					validEmail = true;
				} else {

					GlobalVariables.SIGN_UP_VALID_EMAIL.setText(R.string.unavailable_email);
					GlobalVariables.SIGN_UP_VALID_EMAIL.setTextColor(Color.RED);
					validEmail = false;
				}
			} else if (input.equals("Contact")) {
				if (response.equals("0")) {
					GlobalVariables.SIGN_UP_VALID_CONTACT.setText(R.string.available_contact_no);
					GlobalVariables.SIGN_UP_VALID_CONTACT.setTextColor(Color.GREEN);
					validContact = true;
				} else {
					GlobalVariables.SIGN_UP_VALID_CONTACT.setText(R.string.unavailable_contact_no);
					GlobalVariables.SIGN_UP_VALID_CONTACT.setTextColor(Color.RED);
					validContact = false;
				}
			}
			if (validEmail && validContact) {
				GlobalVariables.VALID_SIGN_UP_EMAIL = true;
			}
		}
	}

	private class SendOTP extends AsyncTask<String, String, String>{

		@Override
		protected void onPreExecute(){
			progressDialog = new ProgressDialog(ActivitySignUp.this);
			progressDialog.setMessage(Html.fromHtml("Registering user..."));
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			res = HttpGetPost.POST(getString(R.string.send_otp),otpData);
			return null;
		}

		@Override
		protected void onPostExecute(String result){
			progressDialog.dismiss();
			if(res.equals("1")){
				startActivity(intentSignUp);
			}else{
				//TODO: appropriate action
			}
		}
	}
}
