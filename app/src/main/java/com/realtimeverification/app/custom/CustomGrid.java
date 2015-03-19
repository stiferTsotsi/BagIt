package com.realtimeverification.app.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
public class CustomGrid extends BaseAdapter {
	private Context context;
	private final String[] folders;


	public CustomGrid(Context c, String[] folders){
		context = c;
		this.folders = folders;
	}

	@Override
	public int getCount() {
		return folders.length;
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

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context
				.LAYOUT_INFLATER_SERVICE);
		if(convertView == null){
			grid = new View(context);
			grid = inflater.inflate(R.layout.grid_folders,null);
			ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
			TextView textView = (TextView)grid.findViewById(R.id.grid_text);

			textView.setText(folders[position]);
			Drawable d = context.getResources().getDrawable(R.drawable.icon_folder);
			imageView.setImageDrawable(d);



		}else{
			grid = (View)convertView;
		}

		return grid;
	}
}
