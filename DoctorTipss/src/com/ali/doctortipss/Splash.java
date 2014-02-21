package com.ali.doctortipss;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class Splash extends Activity {
	String email, password;
	ProgressBar spinner;
	ImageButton retry_button;
	Context context;
	SharedPreferences pref;
	Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		context = this;
		pref = context.getSharedPreferences("DOCTOR_TIPS_PREFS", MODE_PRIVATE);
		editor = pref.edit();
		if (pref.getString("KEY_TIP_TYPE", "").equals("")){
			editor.putString("KEY_TIP_TYPE", "individual");
			editor.commit();
		}
		
		gotoMainActivity();

	}

	private void gotoMainActivity() {

		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {

				moveToMainActivity();
			}
		}, 2000);

	}

	public void moveToMainActivity() {
		Intent i = new Intent(Splash.this, MainActivity.class);
		startActivity(i);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

}
