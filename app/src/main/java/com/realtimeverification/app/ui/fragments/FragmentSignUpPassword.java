package com.realtimeverification.app.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.realtimeverification.app.R;
import com.realtimeverification.app.custom.GlobalVariables;

/**
 * Created by vaal on 3/4/2015.
 */
public class FragmentSignUpPassword extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_register_password, parent, false);

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		GlobalVariables.SIGN_UP_PASSWORD = (EditText) view.findViewById(R.id.edittext_register_full_name);
		GlobalVariables.SIGN_UP_CONFIRM_PASSWORD = (EditText) view.findViewById(R.id.edittext_register_email);

	}

}
