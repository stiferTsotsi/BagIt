package com.realtimeverification.app.custom;

import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by vaal on 3/12/2015.
 */
public class CustomAlertDialog {
	/**
	 * Function to display simple Alert Dialog
	 *
	 * @param context - application context
	 * @param title   - alert dialog title
	 * @param message - alert message
	 * @param status  - success/failure (used to set icon)
	 *                - pass null if you don't want icon
	 */
	public void showAletrDialog(Context context, String title, String message, Boolean status) {
		android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(context).create();

		//set title
		alertDialog.setTitle(title);
		//set message
		alertDialog.setMessage(message);
		if (status != null) {
//            alertDialog.setIcon(status) ? android.support.v7.appcompat.R.drawable.success : android.support.v7.appcompat.R.drawable.fail);
		}

		//setting ok button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		//show alert
		alertDialog.show();
	}


}