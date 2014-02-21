package com.ali.doctortipss;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class ImagePager extends Activity {
	ViewPager viewpager;
	private IndicatorLineView mLine;
	FullScreenImageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_view);
		viewpager = (ViewPager) findViewById(R.id.pager);
//		adapter = new FullScreenImageAdapter(ImagePager.this, imagePaths);
		viewpager.setAdapter(adapter);

		mLine = (IndicatorLineView) findViewById(R.id.viewpager_line);
		mLine.setViewPager(viewpager);
	}

}
