package com.realtimeverification.app.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.widget.Button;

import com.realtimeverification.app.R;
import com.realtimeverification.app.backend.HttpGetPost;
import com.realtimeverification.app.custom.PagerAdapter;
import com.realtimeverification.app.ui.fragments.FragmentSignUpEmail;
import com.realtimeverification.app.ui.fragments.FragmentSignUpPassword;

import java.util.List;
import java.util.Vector;

/**
 * Created by vaal on 3/4/2015.
 */
public class ActivitySignUp extends FragmentActivity {
	private ProgressDialog progressDialog;
	private PagerAdapter mPagerAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		this.initialisePaging();
	}

	/**
	 * Initialise the fragments to be paged
	 */
	private void initialisePaging() {

		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, FragmentSignUpEmail.class.getName()));
		fragments.add(Fragment.instantiate(this, FragmentSignUpPassword.class.getName()));
		this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);

		ViewPager pager = (ViewPager)super.findViewById(R.id.viewPager_activity_sign_up);
		pager.setAdapter(this.mPagerAdapter);
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
