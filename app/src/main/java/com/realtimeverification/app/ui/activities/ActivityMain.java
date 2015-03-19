package com.realtimeverification.app.ui.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.realtimeverification.app.R;
import com.realtimeverification.app.backend.DataRetriever;
import com.realtimeverification.app.custom.Data;
import com.realtimeverification.app.custom.File;

import java.util.ArrayList;

public class ActivityMain extends ActionBarActivity {

	private ArrayList<Data> data;
	private ArrayList<File> files;
	private DataRetriever dataRetriever;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dataRetriever = DataRetriever.get(getApplication());
		new RetrieveData().execute();
	}

	private class RetrieveData extends AsyncTask<String, String, ArrayList<Data>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(ActivityMain.this);
			progressDialog.setMessage(Html.fromHtml("Loading..."));
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
//			progressDialog.show();
		}

		@Override
		protected ArrayList<Data> doInBackground(String... params) {
			//TODO: Remove URL <<>> testing
			try {
				data = dataRetriever.getUserData(getString(R.string.data_test_url));
				for (int i = 0; i < data.size(); i++) {
					files = data.get(i).getFiles();
				}

			} catch (Exception e) {
				Log.e("ASYNC TASK ", " " + e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Data> datas) {
			super.onPostExecute(datas);
//			progressDialog.dismiss();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
