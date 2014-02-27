package com.ali.doctortipss;

import java.util.ArrayList;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ali.doctortipss.fancycoverflow.FancyCoverFlow;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class TipsDetails extends BaseActivity implements OnClickListener {
	ImageButton actionbarBack;
	TextView dTitle, dShortDesc, dDescription;
	DisplayImageOptions displayOptions;
//	LinearLayout details_hs_ll;
	ImageView img;
	private FancyCoverFlow fancyCoverFlow, dialogFancyCoverFlow;
	Dialog dialog;
	ArrayList<String> image;
	TextView zoom,actionbarTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tips_details);

		actionbarBack = (ImageButton) findViewById(R.id.action_bar_menu_btn);
		actionbarTitle = (TextView) findViewById(R.id.action_bar_text);
		dTitle = (TextView) findViewById(R.id.details_title);
		dShortDesc = (TextView) findViewById(R.id.details_short_desc);
		dDescription = (TextView) findViewById(R.id.details_description);
		actionbarTitle.setText("Tip Detail");
		actionbarTitle.setTypeface(fontBold);
		dTitle.setTypeface(fontBold);
		dShortDesc.setTypeface(fontSemiBold);
		dDescription.setTypeface(fontRegular);
		actionbarBack.setImageResource(R.drawable.back);
		displayOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.placeholder_grey)
				.showImageForEmptyUri(R.drawable.placeholder_grey)
				.showImageOnFail(R.drawable.placeholder_grey).cacheInMemory(true)
				.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();
		if (getIntent().getStringExtra("id") != null) {
			String id = getIntent().getStringExtra("id");
			String type_id = getIntent().getStringExtra("type_id");
			String category_id = getIntent().getStringExtra("category_id");
			String title = getIntent().getStringExtra("title");
			String short_description = getIntent().getStringExtra(
					"short_description");
			String description = getIntent().getStringExtra("description");
			image = getIntent().getStringArrayListExtra("image");

			// setText
			dTitle.setText(title);
			dShortDesc.setText(short_description);
			dDescription.setText(description);

			// for (int i = 0; i < image.size(); i++) {
			//
			// img = new ImageView(this);
			// int dip = (int) TypedValue.applyDimension(
			// TypedValue.COMPLEX_UNIT_DIP, (float) 1, getResources()
			// .getDisplayMetrics());
			// img.setLayoutParams(new LayoutParams(220 * dip, 220 * dip));
			// img.setScaleType(ImageView.ScaleType.CENTER_CROP);
			// ImageLoader.getInstance().displayImage(image.get(i), img,
			// displayOptions);
			// details_hs_ll.addView(img);
			//
			// }
			// img.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View arg0) {
			// Intent openImageActivity = new Intent(TipsDetails.this,
			// ImagePager.class);
			// startActivity(openImageActivity);
			// }
			// });
			this.fancyCoverFlow = (FancyCoverFlow) this
					.findViewById(R.id.fancyCoverFlow);

			this.fancyCoverFlow.setAdapter(new FancyCoverFlowSampleAdapter(
					this, image));
			this.fancyCoverFlow.setUnselectedAlpha(1.0f);
			this.fancyCoverFlow.setUnselectedSaturation(0.0f);
			this.fancyCoverFlow.setUnselectedScale(0.5f);
			this.fancyCoverFlow.setSpacing(50);
			this.fancyCoverFlow.setMaxRotation(0);
			this.fancyCoverFlow.setScaleDownGravity(0.2f);
			this.fancyCoverFlow
					.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
			zoom = (TextView) findViewById(R.id.zoom);
			zoom.setTypeface(fontSemiBold);
			zoom.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					imageGallery();
				}
			});
		}

		actionbarBack.setOnClickListener(this);
	}

	protected void imageGallery() {
		Log.d("", "testing imageGallery");
		dialog = new Dialog(TipsDetails.this);
		dialog.setContentView(R.layout.dialog_image_gallery);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		Button cross = (Button) dialog.findViewById(R.id.cross);
		this.dialogFancyCoverFlow = (FancyCoverFlow) dialog
				.findViewById(R.id.fancyCoverFlowDialog);

		this.dialogFancyCoverFlow.setAdapter(new FancyCoverFlowSampleAdapter(
				this, image));
		this.dialogFancyCoverFlow.setUnselectedAlpha(1.0f);
		this.dialogFancyCoverFlow.setUnselectedSaturation(0.0f);
		this.dialogFancyCoverFlow.setUnselectedScale(0.5f);
		this.dialogFancyCoverFlow.setSpacing(50);
		this.dialogFancyCoverFlow.setMaxRotation(0);
		this.dialogFancyCoverFlow.setScaleDownGravity(0.2f);
		this.dialogFancyCoverFlow
				.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
		cross.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();

			}
		});
		dialog.show();
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
