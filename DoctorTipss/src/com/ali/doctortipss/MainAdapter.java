package com.ali.doctortipss;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {
	private ArrayList<Tips> _data;
	Context _c;
	
	public MainAdapter(Context c, ArrayList<Tips> data) {
		_data = data;
		_c = c;
	}

	@Override
	public int getCount() {
		return _data.size();

	}

	
	@Override
	public Object getItem(int position) {
		return _data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			Log.d("", "testing mainadapter v");
			LayoutInflater vi = (LayoutInflater) _c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.tips_nav_bar, null);
		}else{
			Log.d("", "testing mainadapter not v");
		}
		
		Log.d("", "testing mainadapter position" + position);
		TextView title = (TextView) v.findViewById(R.id.tips_title);
		TextView shortDesc = (TextView) v.findViewById(R.id.tips_short_description);
		Tips tips = _data.get(position);
		TextView strip = (TextView) v.findViewById(R.id.view1);
		strip.setBackgroundColor(Color.parseColor(MySingleton.getInstance().colorArray[position]));
		Log.d("", "testing mainadapter tips()" + tips);
		Log.d("", "testing mainadapter tips.getTipTitle()" + tips.getTipTitle());
		title.setText(tips.getTipTitle());
		shortDesc.setText(tips.getTipShortDescription());

		return v;
	}
	
}
