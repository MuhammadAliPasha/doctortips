package com.ali.doctortipss;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TipsDetails extends Activity implements OnClickListener {
	ImageButton actionbarBack;
	TextView dTitle, dShortDesc, dDescription;
	DisplayImageOptions displayOptions;
	LinearLayout details_hs_ll;
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tips_details);

		actionbarBack = (ImageButton) findViewById(R.id.action_bar_menu_btn);
		dTitle = (TextView) findViewById(R.id.details_title);
		dShortDesc = (TextView) findViewById(R.id.details_short_desc);
		dDescription = (TextView) findViewById(R.id.details_description);

		details_hs_ll = (LinearLayout) findViewById(R.id.details_hs_ll);
		actionbarBack.setImageResource(R.drawable.back);
		displayOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();
		if (getIntent().getStringExtra("id") != null) {
			String id = getIntent().getStringExtra("id");
			String type_id = getIntent().getStringExtra("type_id");
			String category_id = getIntent().getStringExtra("category_id");
			String title = getIntent().getStringExtra("title");
			String short_description = getIntent().getStringExtra(
					"short_description");
			String description = getIntent().getStringExtra("description");
			ArrayList<String> image = getIntent().getStringArrayListExtra(
					"image");

			// setText
			dTitle.setText(title);
			dShortDesc.setText(short_description);
			dDescription.setText(description);

			for (int i = 0; i < image.size(); i++) {

				img = new ImageView(this);
				int dip = (int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_DIP, (float) 1, getResources()
								.getDisplayMetrics());
				img.setLayoutParams(new LayoutParams(220 * dip, 220 * dip));
				img.setScaleType(ImageView.ScaleType.CENTER_CROP);
				ImageLoader.getInstance().displayImage(image.get(i), img,
						displayOptions);
				details_hs_ll.addView(img);

			}
			img.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent openImageActivity = new Intent(TipsDetails.this,
							ImagePager.class);
					startActivity(openImageActivity);
				}
			});
		}

		actionbarBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.action_bar_menu_btn:
			// Intent openActivity = new Intent(TipsDetails.this,
			// DoctorTips.class);
			// startActivity(openActivity);
			finish();
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;
		}
	}
}
