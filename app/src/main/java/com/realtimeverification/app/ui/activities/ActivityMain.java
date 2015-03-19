package com.realtimeverification.app.ui.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.realtimeverification.app.R;
import com.realtimeverification.app.backend.DataRetriever;
import com.realtimeverification.app.custom.CustomFolderGrid;
import com.realtimeverification.app.custom.Data;
import com.realtimeverification.app.custom.File;

import java.util.ArrayList;

public class ActivityMain extends ActionBarActivity {

	private ArrayList<Data> data;
	private ArrayList<File> files;
	private DataRetriever dataRetriever;
	private ProgressDialog progressDialog;
	GridView gridView;
	TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dataRetriever = DataRetriever.get(getApplication());
		title = (TextView) findViewById(R.id.title);

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
			progressDialog.show();
		}

		@Override
		protected ArrayList<Data> doInBackground(String... params) {
			//TODO: Remove URL <<>> testing

			if (dataRetriever != null) {
				data = dataRetriever.getUserData
						(getString(R.string.data_test_url) + "?uid=31");
			} else {
				Log.d(" ##################### ", "NULL");
			}

			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Data> datas) {
			super.onPostExecute(datas);
			title.setText(data.get(0).getFolderName());


			final String[] fileNames = new String[data.size()];
			for (int i = 0; i < data.size(); i++) {
				fileNames[i] = data.get(0).getFiles().get(i).getDocName();
			}

			ArrayList<Data> myData = data;

			myData.remove(0);
			final String[] myFolders = new String[myData.size()];
			for (int i = 0; i < myData.size(); i++) {
				myFolders[i] = myData.get(i).getFolderName();
			}

			if (data != null && myFolders != null) {
				CustomFolderGrid adapter = new CustomFolderGrid(ActivityMain.this,myFolders,fileNames);
				gridView = (GridView) findViewById(R.id.grid);
				gridView.setAdapter(adapter);
				gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
					                        int position, long id) {
						Toast.makeText(ActivityMain.this, "You Clicked at " + position +
										myFolders[+position],
								Toast.LENGTH_SHORT).show();
					}
				});
			}

			progressDialog.dismiss();
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
