package com.ali.doctortipss;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class MainActivity extends Activity implements OnClickListener {
	MenuAdapter adapter;
	ViewPager viewPager;
	ImageButton healthBtn, beautyBtn, fitnessBtn, nailBtn, dietBtn, makeupBtn,
			hairBtn, weightBtn;
	Button doctorTipsBtn, IndividualTipsBtn;
	SharedPreferences pref;
	Editor editor;
	Context context;
	AdView ad;
	private IndicatorLineView mLine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		Log.d("", "testing aa");
		ad = (AdView) findViewById(R.id.ad);
		ad.loadAd(new AdRequest());
		//SharedPrferences
		pref = context.getSharedPreferences("DOCTOR_TIPS_PREFS", MODE_PRIVATE);
		editor = pref.edit();
		
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		doctorTipsBtn = (Button) findViewById(R.id.main_doctor_tips);
		IndividualTipsBtn = (Button) findViewById(R.id.main_individual_tips);
		if (pref.getString("KEY_TIP_TYPE", "").equals("doctor")){
			doctorTipsBtn.setBackgroundResource(R.drawable.tip_type_doctor);
			IndividualTipsBtn.setBackgroundResource(R.drawable.tip_type_ind_hover);
		}else if (pref.getString("KEY_TIP_TYPE", "").equals("individual")){
			IndividualTipsBtn.setBackgroundResource(R.drawable.tip_type_ind);
			doctorTipsBtn.setBackgroundResource(R.drawable.tip_type_doctor_hover);
		}
		doctorTipsBtn.setOnClickListener(this);
		IndividualTipsBtn.setOnClickListener(this);
		Log.d("", "testing bb");
		adapter = new MenuAdapter(MainActivity.this);
		viewPager.setAdapter(adapter);
		
		mLine = (IndicatorLineView) findViewById(R.id.viewpager_line);
		mLine.setViewPager(viewPager);
		// declare buttons
		// healthBtn = (ImageButton) findViewById(R.id.view_health);
		// beautyBtn = (ImageButton) findViewById(R.id.view_beauty);
		// fitnessBtn = (ImageButton) findViewById(R.id.view_fitness);
		// nailBtn = (ImageButton) findViewById(R.id.view_nail);
		// dietBtn = (ImageButton) findViewById(R.id.view_diet);
		// makeupBtn = (ImageButton) findViewById(R.id.view_makeup);
		// hairBtn = (ImageButton) findViewById(R.id.view_hair);
		// weightBtn = (ImageButton) findViewById(R.id.view_weight);
		// // set setOnClickListeners
		// healthBtn.setOnClickListener(this);
		// beautyBtn.setOnClickListener(this);
		// fitnessBtn.setOnClickListener(this);
		// nailBtn.setOnClickListener(this);
		// dietBtn.setOnClickListener(this);
		// makeupBtn.setOnClickListener(this);
		// hairBtn.setOnClickListener(this);
		// weightBtn.setOnClickListener(this);

	}

	// declare tips methods
	public void getHealthTips(View health) {
		Intent openActivity = new Intent(MainActivity.this, DoctorTips.class);
		openActivity.putExtra("tipType", "1");
		openActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		MainActivity.this.startActivity(openActivity);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	public void getBeautyTips(View beauty) {
		Intent openActivity = new Intent(MainActivity.this, DoctorTips.class);
		openActivity.putExtra("tipType", "2");
		openActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		MainActivity.this.startActivity(openActivity);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	public void getHairTips(View hair) {
		Intent openActivity = new Intent(MainActivity.this, DoctorTips.class);
		openActivity.putExtra("tipType", "3");
		openActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		MainActivity.this.startActivity(openActivity);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	public void getWeightTips(View weight) {
		Intent openActivity = new Intent(MainActivity.this, DoctorTips.class);
		openActivity.putExtra("tipType", "4");
		openActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		MainActivity.this.startActivity(openActivity);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	public void getNailTips(View nail) {
		Intent openActivity = new Intent(MainActivity.this, DoctorTips.class);
		openActivity.putExtra("tipType", "5");
		openActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		MainActivity.this.startActivity(openActivity);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	public void getDietTips(View diet) {
		Intent openActivity = new Intent(MainActivity.this, DoctorTips.class);
		openActivity.putExtra("tipType", "6");
		openActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		MainActivity.this.startActivity(openActivity);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	public void getFitnessTips(View fitness) {
		Intent openActivity = new Intent(MainActivity.this, DoctorTips.class);
		openActivity.putExtra("tipType", "7");
		openActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		MainActivity.this.startActivity(openActivity);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	public void getMakeupTips(View makeup) {
		Intent openActivity = new Intent(MainActivity.this, DoctorTips.class);
		openActivity.putExtra("tipType", "8");
		openActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		MainActivity.this.startActivity(openActivity);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		
		switch (id) {
		case R.id.main_doctor_tips:
			editor.putString("KEY_TIP_TYPE", "doctor");
	   	 	editor.commit();
	   	 doctorTipsBtn.setBackgroundResource(R.drawable.tip_type_doctor);
	   	IndividualTipsBtn.setBackgroundResource(R.drawable.tip_type_ind_hover);
			break;
		case R.id.main_individual_tips:
			editor.putString("KEY_TIP_TYPE", "individual");
	   	 	editor.commit();
	   	 doctorTipsBtn.setBackgroundResource(R.drawable.tip_type_doctor_hover);
	   	 IndividualTipsBtn.setBackgroundResource(R.drawable.tip_type_ind);
			break;
		}
		// Intent openActivity = new Intent(MainActivity.this,
		// DoctorTips.class);
		// int id = v.getId();
		// switch (id) {
		// case R.id.view_health:
		// openActivity.putExtra("tipType", "health");
		// break;
		// case R.id.view_beauty:
		// openActivity.putExtra("tipType", "beauty");
		// break;
		// case R.id.view_diet:
		// openActivity.putExtra("tipType", "diet");
		// break;
		// case R.id.view_fitness:
		// openActivity.putExtra("tipType", "fitness");
		// break;
		// case R.id.view_hair:
		// openActivity.putExtra("tipType", "hair");
		// break;
		// case R.id.view_nail:
		// openActivity.putExtra("tipType", "nail");
		// break;
		// case R.id.view_makeup:
		// openActivity.putExtra("tipType", "makeup");
		// break;
		// case R.id.view_weight:
		// openActivity.putExtra("tipType", "weight");
		// break;
		//
		// }
		//
		// MainActivity.this.startActivity(openActivity);
		// overridePendingTransition(R.anim.slide_in_right,
		// R.anim.slide_out_left);
	}

}
