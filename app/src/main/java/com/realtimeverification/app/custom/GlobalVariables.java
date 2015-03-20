package com.realtimeverification.app.custom;

import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by vaal on 3/4/2015.
 */
public class GlobalVariables {


	//JSONObject
	public static String FOLDER_ID ="folder_id";
	public static String PARENT_ID ="parent_id";
	public static String FOLDER_NAME ="folder_name";
	public static String FILE_ID = "file_id";
	public static String DOC_NAME = "doc_name";
	public static String FILE_NAME = "file_name";
	public static String FILE_URL ="file_url";
	public static String FILE_LOCATION ="file_location";
	public static String FOLDER ="Folders";
	public static String FILE = "files";

	public static String EMAIL_ADDRESS ="email";
	public static String CONTACT_NO="contactNo";
	public static String FULL_NAME="fullName";
	public static String PASSWORD="password";
	public static String USERNAME="username";
	public static String OTP;

	public static List<NameValuePair> DATA;

	public static boolean VALID_SIGN_UP_EMAIL;
	public static String RESULT;

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

	public static byte[] RAW_KEY;
}
