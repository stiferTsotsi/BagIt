package com.realtimeverification.app.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import com.realtimeverification.app.R;
import com.realtimeverification.app.backend.HttpGetPost;
import com.realtimeverification.app.custom.GlobalVariables;
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
//		this.initialisePaging();
		addFragment(false,new FragmentSignUpEmail(),"Sign UP",R.id.container,FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
	}



	public void addFragment(boolean addToBackStack, Fragment fragment, String id, int layoutReplaceId, int transitionId) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.replace(layoutReplaceId, fragment);
		fragmentTransaction.setTransition(transitionId);

		if (addToBackStack == true) {
			fragmentTransaction.addToBackStack(id);
		}
		fragmentTransaction.commit();
	}

	/**
	 * Initialise the fragments to be paged
	 */
//	private void initialisePaging() {
//
//		List<Fragment> fragments = new Vector<Fragment>();
//		fragments.add(Fragment.instantiate(this, FragmentSignUpEmail.class.getName()));
//		fragments.add(Fragment.instantiate(this, FragmentSignUpPassword.class.getName()));
//		this.mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
//
//		GlobalVariables.VIEW_PAGER_SIGN_UP = (ViewPager) super.findViewById(R.id.viewPager_activity_sign_up);
//		GlobalVariables.VIEW_PAGER_SIGN_UP.setAdapter(this.mPagerAdapter);
//	}

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
