package com.realtimeverification.app.utils;

import android.app.Activity;
import android.content.Context;

import com.realtimeverification.app.backend.Get;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vaal on 3/4/2015.
 */
public class Validations {

	/**
	 * Check if email is not contained in the database
	 *
	 * @param value email_address
	 * @param value activity
	 * @return 0 or 1
	 */
	public static int isAvailableEmail(String value, Context context) {
		String url = "www.realtimeverificationc.co.za/loading/appCheckEmail.php?email=" + value;
		return Get.getResponseResult(url, context);
	}

	/**
	 * Check if contact number is not contained in the database
	 *
	 * @param value contact_number
	 * @param value activity
	 * @return 0 or 1
	 */
	public static int isAvailableContactNo(String value, Context context) {
		String url = "www.realtimeverificationc.co.za/loading/appCheckCell.php?cellno=" + value;
		return Get.getResponseResult(url, context);
	}

	/**
	 * Check if value supplied is a valid Internet Email Address
	 *
	 * @param value email_address
	 * @return true or false
	 */
	public static Boolean isValidEmail(String value) {
		boolean valid;
		Pattern pattern;
		Matcher matcher;
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(value);
		if (matcher.matches()) {
			valid = true;
		} else {
			valid = false;
		}
		return valid;
	}

	/**
	 * Check if value supplied is a valid South African Cellphone Number
	 *
	 * @param value contact_number
	 * @return true or false
	 */
	public static Boolean isValidContactNo(String value) {

		return null;
	}

}
