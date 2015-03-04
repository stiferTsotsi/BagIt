package com.realtimeverification.app.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.realtimeverification.app.R;
import com.realtimeverification.app.custom.GlobalVariables;
import com.realtimeverification.app.ui.fragments.FragmentSignUpEmail;
import com.realtimeverification.app.ui.fragments.FragmentSignUpPassword;

/**
 * Created by vaal on 3/4/2015.
 */
public class ActivitySignUp extends FragmentActivity {
	private Fragment f1;
	private Fragment f2;
	private ViewPager pager_sign_up;
	private Adapter adapter;
	private Button button_sign_up;
	private ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		f1 = new FragmentSignUpEmail();
		f2 = new FragmentSignUpPassword();

		pager_sign_up = (ViewPager) findViewById(R.id.viewPager_activity_sign_up);
		button_sign_up = (Button) findViewById(R.id.button_register_activity_email);

		adapter = new Adapter(getSupportFragmentManager());
		pager_sign_up.setAdapter(adapter);

		setListeners(pager_sign_up, button_sign_up);
	}

	private class Adapter extends FragmentPagerAdapter {

		public Adapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {

				case 0:
					return f1;
				case 1:
					return f2;
				default:
					return null;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
	}

	private void setListeners(ViewPager page, Button button) {
		page.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int i, float v, int i2) {

			}

			@Override
			public void onPageSelected(int i) {

				if (i == 0) {
					button_sign_up.setText(R.string.button_sign_up_next);

				} else if (i == 1) {
					if (validate(0) == true) {
						//TODO: validate for f1
					} else {
						pager_sign_up.setCurrentItem(0);
						Toast.makeText(getApplicationContext(), "Please enter valid info", Toast.LENGTH_SHORT).show();
					}
				} else {
					button_sign_up.setText(R.string.button_sign_up);
				}
			}

			@Override
			public void onPageScrollStateChanged(int i) {


			}
		});

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (pager_sign_up.getCurrentItem() == 0) {
					if (validate(0)) { // TODO: Remove ! when validation logic has been correctly implemented.
						pager_sign_up.setCurrentItem(1, true);
					} else {
						// TODO: Display toast to inform user about invalid/incomplete info.
					}
				} else if (pager_sign_up.getCurrentItem() == 1) {
					if (!validate(1)) { // TODO: Remove ! when validation logic has been correctly implemented.
						new CreateNewUser().execute();
						Intent intent1 = new Intent(getApplicationContext(), ActivitySignUp.class);
						startActivity(intent1);
					} else {
						// TODO: Display toast to inform user about invalid/incomplete info.
					}
				}
			}
		});
	}

	// Used to validate user input before sign up.
	public boolean validate(int i) {
		boolean validEmail, validContactNo;

		switch (i) {

			case 0:
				String name = GlobalVariables.EDIT_TEXT_FULL_NAME.getText().toString();
				String email = GlobalVariables.EDIT_TEXT_EMAIL.getText().toString();
				String contactNo = GlobalVariables.EDIT_TEXT_CONTACT_NO.getText().toString();
//			case 1:
//				String country = GlobalVariables.SIGN_UP_COUNTRY.getText().toString();
//				String tel_nr = GlobalVariables.SIGN_UP_TEL_NR.getText().toString();
//				String dob = GlobalVariables.SIGN_UP_DOB.getText().toString();
//				String gender = GlobalVariables.SIGN_UP_GENDER.getText().toString();
//
//				// TODO: Still to validate DOB/GENDER
//				if (!country.trim().isEmpty()) {
////                    valid = false;
//				}
//
//				if (!Validations.isValidContact(tel_nr)) {
////                    valid = false;
//				}
//
//				return false;
//
//			case 2:
//				String password = GlobalVariables.SIGN_UP_PASS.getText().toString();
//				String confirmPassword = GlobalVariables.SIGN_UP_CONFIRM_PASS.getText().toString();
//
//				if (Validations.isValidPassword(password)) {
//					GlobalVariables.SIGN_UP_PASS.setError(null);
//					validPass = true;
//				} else {
//					GlobalVariables.SIGN_UP_PASS.setError("Password is too short");
//					validPass = false;
//				}
//
//				if (Validations.isMatchPasswords(password, confirmPassword)) {
//					GlobalVariables.SIGN_UP_CONFIRM_PASS.setError(null);
//					validMatchPasswords = true;
//				} else {
//					GlobalVariables.SIGN_UP_CONFIRM_PASS.setError("Does not much password");
//					validMatchPasswords = false;
//				}
//
//				if (GlobalVariables.SIGN_UP_TICK == false) {
//					Toast.makeText(getApplicationContext(), "Agree to terms and conditions", Toast.LENGTH_SHORT).show();
//				}
//
//				if (validPass == true && validMatchPasswords == true && GlobalVariables.SIGN_UP_TICK == true) {
//					return true;
//				} else {
//					return false;
//				}

			default:
				return false;
		}
	}

	private class CreateNewUser extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(ActivitySignUp.this);
			progressDialog.setMessage(Html.fromHtml("Registering user..."));
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			progressDialog.dismiss();
		}
	}


}
