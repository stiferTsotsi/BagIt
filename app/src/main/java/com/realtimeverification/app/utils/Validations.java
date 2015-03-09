package com.realtimeverification.app.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.realtimeverification.app.backend.HttpGetPost;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vaal on 3/4/2015.
 */
public class Validations {
	/**
	 * Check if value supplied is a valid Internet Email Address
	 *
	 * @param target email_address
	 * @return true or false
	 */
	public final static boolean isValidEmail(CharSequence target) {
		return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	}

	/**
	 * Check if value supplied is a valid South African Cellphone Number
	 *
	 * @param value contact_number
	 * @return true or false
	 */
	public static Boolean isValidContactNo(String value) {
		boolean validTen, validTwelve;


		if (value.startsWith("0")) {
			if (value.trim().length() == 10) {
				if (value.substring(1, 2) != "0") {
					validTen = true;
				} else {
					validTen = false;
				}
			} else {
				validTen = false;
			}
		} else {
			validTen = false;
		}

		if (value.startsWith("+27") && value.trim().length() == 12 && value
				.substring(3, 4) != "0") {

			validTwelve = true;
		} else {
			validTwelve = false;
		}

		if (validTen || validTwelve) {
			return true;
		} else {
			return false;
		}
	}
}
