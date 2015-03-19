package com.realtimeverification.app.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.realtimeverification.app.R;

/**
 * Created by vaal on 3/19/2015.
 */
public class CustomFolderGrid extends BaseAdapter {
	private Context context;
	private final String[] folders;
	private final String[] files;

	public CustomFolderGrid(Context c, String[] folders, String[] files) {
		context = c;
		this.folders = folders;
		this.files = files;
		Log.d("Counter -------- ", " " +getCount() + folders.length);
	}

	@Override
	public int getCount() {
		return folders.length + files.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View grid;

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
				.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			grid = new View(context);
			grid = inflater.inflate(R.layout.grid_folders, null);
			ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
			TextView textView = (TextView) grid.findViewById(R.id.grid_text);


			Log.d(" POSITION ", " ******** " + position);


//			if(position > (position+1)- folders.length){
				textView.setText(folders[position]);
				Drawable d;
				d = context.getResources().getDrawable(R.drawable.icon_folder);
				imageView.setImageDrawable(d);
//			}


		} else {
			grid = convertView;
		}

		return grid;
	}
}
