package com.realtimeverification.app.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.realtimeverification.app.R;
import com.realtimeverification.app.backend.HttpGetPost;
import com.realtimeverification.app.custom.GlobalVariables;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by vaal on 3/12/2015.
 */
public class ActivityOTP extends FragmentActivity {

	private EditText editTextOTP;
	private TextView textViewTitle;
	private ProgressDialog progressDialog;
	private String res;
	private Intent intentSignUp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_otp);
		setUpUI();
		intentSignUp = new Intent(this, ActivityMain.class);
	}

	private void setUpUI() {
		editTextOTP = (EditText) findViewById(R.id.edittext_otp);
		textViewTitle = (TextView) findViewById(R.id.textview_otp_title);

		String email = GlobalVariables.SIGN_UP_EMAIL.getText().toString();

		textViewTitle.setText(getString(R.string.text_otp_title_1) +" " + email + " " + getString(R
				.string.text_otp_title_2) );
	}

	public void onClickOTP(View view) {
		if (view.getId() == R.id.btnOTP) {
			String otp = editTextOTP.getText().toString();

			if (otp.equals(GlobalVariables.OTP)) {
				new CreateNewUser().execute();
			} else {
				Toast.makeText(getApplicationContext(), getString(R.string.incorrect_otp),
						Toast.LENGTH_LONG);
			}
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
				startActivity(intentSignUp);
			}
		}
	}

}
