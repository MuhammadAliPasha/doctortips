package com.ali.doctortipss;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;

public class BaseActivity extends Activity{
	Typeface fontBold, fontRegular, fontSemiBold, fontLight;
	Helper helper;
	Context context;
	Display mDisplay;
	int deviceWidth;
	int deviceHeight;
	SharedPreferences pref;
	Editor editor;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		helper = new Helper();
		pref = context.getSharedPreferences("DOCTORTIPS_PREFS", MODE_PRIVATE);
		// Editor
		editor = pref.edit();
		fontBold = Typeface.createFromAsset(this.getAssets(),
				"proximanova-bold-webfont.ttf");
		fontRegular = Typeface.createFromAsset(this.getAssets(),
				"proximanova-regular-webfont.ttf");
		fontSemiBold = Typeface.createFromAsset(this.getAssets(),
				"proximanova-semibold-webfont-webfont.ttf");
		fontLight = Typeface.createFromAsset(this.getAssets(),
				"proximanova-light-webfont-webfont.ttf");
		 mDisplay= this.getWindowManager().getDefaultDisplay();
		 deviceWidth= mDisplay.getWidth();
		 deviceHeight= mDisplay.getHeight();
	}

		
		

}
