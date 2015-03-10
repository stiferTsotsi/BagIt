package com.realtimeverification.app.custom;

import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by vaal on 3/4/2015.
 */
public class GlobalVariables {

	public static String EMAIL_ADDRESS ="";
	public static String CONTACT_NO="";
	public static String FULL_NAME="";
	public static String PASSWORD="";

	public static boolean VALID_SIGN_UP_EMAIL;

	public static EditText SIGN_UP_FULL_NAME;
	public static EditText SIGN_UP_EMAIL;
	public static EditText SIGN_UP_CONTACT_NO;
	public static EditText SIGN_UP_PASSWORD;
	public static EditText SIGN_UP_CONFIRM_PASSWORD;

	public static TextView SIGN_UP_VALID_EMAIL;
	public static TextView SIGN_UP_VALID_CONTACT;
	public static TextView SIGN_UP_VALID_PASSWORD;
	public static TextView SIGN_UP_VALID_CONFIRM_PASSWORD;
	public static TextView SIGN_UP_VALID_FULL_NAME;

	public static ViewPager VIEW_PAGER_SIGN_UP;
	public static Button SIGN_UP_BUTTON;

}
