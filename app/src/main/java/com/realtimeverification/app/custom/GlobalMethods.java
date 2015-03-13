package com.realtimeverification.app.custom;

import android.util.Base64;

/**
 * Created by vaal on 3/10/2015.
 */
public class GlobalMethods {

	public static String ENCODE(String pin){
		return Base64.encodeToString(pin.getBytes(), Base64.DEFAULT);
	}

	public static String DECODE(String pin){
		return new String(Base64.decode(pin, Base64.DEFAULT));
	}

}
