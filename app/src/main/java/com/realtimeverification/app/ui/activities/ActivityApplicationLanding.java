package com.realtimeverification.app.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.realtimeverification.app.R;

/**
 * Created by vaal on 3/19/2015.
 */
public class ActivityApplicationLanding extends Activity {
	private Intent intenSignUp, intentSignIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitity_landing_page);
	}

	public void onClickAppLanding(View view) {
		if (view.getId() == R.id.btnToSignUp) {
			intenSignUp = new Intent(this, ActivitySignUp.class);
			startActivity(intenSignUp);
		} else if (view.getId() == R.id.btnToSignIn) {
			intentSignIn = new Intent(this, ActivitySignIn.class);
			startActivity(intentSignIn);
		}
	}
}
