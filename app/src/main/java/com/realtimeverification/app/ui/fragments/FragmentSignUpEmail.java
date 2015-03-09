package com.realtimeverification.app.ui.fragments;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.realtimeverification.app.R;
import com.realtimeverification.app.backend.HttpGetPost;
import com.realtimeverification.app.custom.GlobalVariables;
import com.realtimeverification.app.utils.Validations;

/**
 * Created by vaal on 3/4/2015.
 */
public class FragmentSignUpEmail extends Fragment {

	private String response, input;
	private String email, contactNo, name;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_register_email, parent, false);

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		GlobalVariables.SIGN_UP_FULL_NAME = (EditText) view.findViewById(R.id.edittext_register_full_name);
		GlobalVariables.SIGN_UP_EMAIL = (EditText) view.findViewById(R.id.edittext_register_email);
		GlobalVariables.SIGN_UP_CONTACT_NO = (EditText) view.findViewById(R.id.edittext_register_contact_no);
		GlobalVariables.SIGN_UP_VALID_EMAIL = (TextView) view.findViewById(R.id
				.textview_validate_email);
		GlobalVariables.SIGN_UP_VALID_CONTACT = (TextView) view.findViewById(R.id
				.textview_validate_contact_no);
		setUpTextWatcher();
	}

	private void setUpTextWatcher() {

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

				new HttpGetResponse().execute("https://www.realtimeverification.co" +
						".za/loading/appCheckEmail" +
						".php?email=" + email, "Email");

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (Validations.isValidEmail(GlobalVariables.SIGN_UP_EMAIL.getText().toString())) {
					GlobalVariables.SIGN_UP_VALID_EMAIL.setText("");

				} else {
					GlobalVariables.SIGN_UP_VALID_EMAIL.setText(R.string.invalid_email);
				}
			}
		});

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
					Toast.makeText(getActivity().getApplicationContext(), "Contact number can only" +
							" start with '0' or '+27'", Toast.LENGTH_SHORT).show();
				}

				if (Validations.isValidContactNo(GlobalVariables.SIGN_UP_CONTACT_NO.getText().toString())) {
					GlobalVariables.SIGN_UP_VALID_CONTACT.setText("");

				} else {
					GlobalVariables.SIGN_UP_VALID_CONTACT.setText(R.string.invalid_contact_no);
					GlobalVariables.SIGN_UP_VALID_CONTACT.setTextColor(Color.RED);
					return;
				}


				new HttpGetResponse().execute("https://www.realtimeverification.co" +
						".za/loading/appCheckCell.php?cellno=" + contactNo, "Contact");

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

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
			if (input.equals("Email")) {
				if (response.equals("0")) {
					GlobalVariables.SIGN_UP_VALID_EMAIL.setText(R.string.available_email);
					GlobalVariables.SIGN_UP_VALID_EMAIL.setTextColor(Color.GREEN);
				} else {
					GlobalVariables.SIGN_UP_VALID_EMAIL.setText(R.string.unavailable_email);
					GlobalVariables.SIGN_UP_VALID_EMAIL.setTextColor(Color.RED);
				}
			} else if (input.equals("Contact")) {
				if (response.equals("0")) {
					GlobalVariables.SIGN_UP_VALID_CONTACT.setText(R.string.available_contact_no);
					GlobalVariables.SIGN_UP_VALID_CONTACT.setTextColor(Color.GREEN);
				} else {
					GlobalVariables.SIGN_UP_VALID_CONTACT.setText(R.string.unavailable_contact_no);
					GlobalVariables.SIGN_UP_VALID_CONTACT.setTextColor(Color.RED);
				}
			}
		}
	}
}
