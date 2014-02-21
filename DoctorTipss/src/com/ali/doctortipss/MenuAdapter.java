package com.ali.doctortipss;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class MenuAdapter extends PagerAdapter {
	Context _c;

	View myView;
	private static final int VIEWONE = 0;
	private static final int VIEWTWO = 1;
	private static final int VIEW_MAX_COUNT = VIEWTWO + 1;

	public MenuAdapter(Context c) {
		this._c = c;

	}

	@Override
	public int getCount() {
		return VIEW_MAX_COUNT;
	}

	@Override
	public Object instantiateItem(View view, int position) {
		LayoutInflater inflater = (LayoutInflater) _c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// myView = inflater.inflate(R.layout.view2, null);
		switch (position) {
		case VIEWONE:
			myView = inflater.inflate(R.layout.view, null);
			break;
		case VIEWTWO:
			myView = inflater.inflate(R.layout.view2, null);
			break;
		}

		((ViewPager) view).addView(myView, 0);
		return myView;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View) arg1);
	}

	@Override
	public Parcelable saveState() {
		return null;
	}
}
