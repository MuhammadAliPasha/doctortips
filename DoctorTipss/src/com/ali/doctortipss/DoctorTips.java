package com.ali.doctortipss;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DoctorTips extends Activity implements OnClickListener {
	String tipType;
	ListView listView;
	private Helper helper = new Helper();
	private TipsResponses tipsResponses = new TipsResponses();
	Tips tips;
	ArrayList<Tips> details = new ArrayList<Tips>();
	MainAdapter adapter;
	Context ctx;
	TextView tip_title, no_record;
	ImageView tipsCoverPhoto;
	int resId;
	ImageButton actionbarBack;
	SharedPreferences pref;
	Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_tips);
		ctx= this;
		tipType = getIntent().getStringExtra("tipType");
		// SharedPrferences
		pref = this.getSharedPreferences("DOCTOR_TIPS_PREFS", MODE_PRIVATE);
		editor = pref.edit();
		listView = (ListView) findViewById(R.id.listView1);
		// tip_title = (TextView) findViewById(R.id.tip_title);
		tipsCoverPhoto = (ImageView) findViewById(R.id.tips_cover_photo);
		actionbarBack = (ImageButton) findViewById(R.id.action_bar_menu_btn);
		actionbarBack.setImageResource(R.drawable.back);
		actionbarBack.setOnClickListener(this);
		no_record = (TextView) findViewById(R.id.no_record_today);
		no_record.setVisibility(View.GONE);
		if (!tipType.equals("")) {
			if (tipType.equals("1")) {
				// tipTitle = "HEALTH TIPS";
				resId = R.drawable.health_tips_cover;
			} else if (tipType.equals("2")) {
				// tipTitle = "BEAUTY TIPS";
				resId = R.drawable.beauty_tips_cover;
			} else if (tipType.equals("3")) {
				// tipTitle = "HAIR TIPS";
				resId = R.drawable.hair_tips_cover;
			} else if (tipType.equals("4")) {
				// tipTitle = "WEIGHT LOSS TIPS";
				resId = R.drawable.weight_tips_cover;
			} else if (tipType.equals("5")) {
				// tipTitle = "NAIL TIPS";
				resId = R.drawable.nail_tips_cover;
			} else if (tipType.equals("6")) {
				// tipTitle = "DIET TIPS";
				resId = R.drawable.diet_tips_cover;
			} else if (tipType.equals("7")) {
				// tipTitle = "FITNESS TIPS";
				resId = R.drawable.fitness_tips_cover;
			} else if (tipType.equals("8")) {
				// tipTitle = "MAKEUP TIPS";
				resId = R.drawable.makeup_tips_cover;
			}
			// tip_title.setText(tipTitle);
			tipsCoverPhoto.setImageResource(resId);
			new getTips().execute(tipType);
		}

	}

	private class getTips extends AsyncTask<String, Void, String> {
		String status, totalrecords,jTypeId,jCategoryId,jImage,jShortDescription,jDescription;
		JSONObject jsonobj, joObjectt, data,images,imageInfo;
		JSONArray jsonArr;
		int i;
		String jId, jTitle, jType;

		@Override
		protected void onPreExecute() {
			Log.d("", "testing onPreExecute");
			helper.AlertProgressDialogue("show", DoctorTips.this);
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			Log.d("", "testing doInBackground");
			int offset = 0;
			int limit = 30;
			String typeId = pref.getString("KEY_TIP_TYPE", "").equals("doctor") ? "2"
					: "1";
			String type = params[0];
			String postUrl = MySingleton.ApiUrl + "tip_description?offset="
					+ offset + "&limit=" + limit + "&typeid=" + typeId
					+ "&categoryid=" + type;
			String getTips = MySingleton.postFeatured(postUrl);
			if (getTips.equals("errorConnection")){
				status = "errorConnection";
			}else{
				try {
					jsonobj = new JSONObject(getTips);
					data = jsonobj.getJSONObject("data");
					status = data.get("status").toString();
					totalrecords = data.get("totalrecords").toString();

					if (status.equals("success")) {
						jsonArr = data.getJSONArray("records");
						if (jsonArr.length() > 0) {
							for (i = 0; i < jsonArr.length(); i++) {
								tips = new Tips();
								joObjectt = jsonArr.getJSONObject(i);
								
								jId = joObjectt.get("id").toString();
								jTypeId = joObjectt.get("type_id").toString();
								jCategoryId = joObjectt.get("tip_category_id").toString();
								jTitle = joObjectt.get("title").toString();
								jImage = joObjectt.get("image").toString();
								JSONObject images = joObjectt.getJSONObject("image");
								tips.ImagesArrayList();
								for (int x=1;x<=images.length();x++){
									Log.d("", "testing images arralist"+images.get("image"+x).toString());
									tips.setImagesArrayList(images.get("image"+x).toString());
								}
								jShortDescription = joObjectt.get("short_description").toString();
								jDescription = joObjectt.get("description").toString();
								
								tips.setTipId(jId);
								tips.setTipTitle(jTitle);
								tips.setTipTypeId(jTypeId);
								tips.setTipCategoryId(jCategoryId);
								tips.setTipImage(jImage);
								tips.setTipShortDescription(jShortDescription);
								tips.setTipDescription(jDescription);
								details.add(tips);

							}
						}
					}
					if (details.isEmpty()) {
						status = "error";
					} else {
						status = "success";
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			Log.d("", "testing status" + status);
			return status;
		}

		@Override
		protected void onPostExecute(String result) {
			Log.d("", "testing onPostExecute");
			helper.AlertProgressDialogue("dismiss", DoctorTips.this);
			Log.d("", "testing result" + result);
			if (result.equals("success")) {
				no_record.setVisibility(View.GONE);
				listView.setVisibility(View.VISIBLE);

				adapter = new MainAdapter(DoctorTips.this, details);
				adapter.notifyDataSetChanged();
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						
						Intent openActivity = new Intent(DoctorTips.this,
								TipsDetails.class);
						openActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						openActivity.putExtra("id", details.get(arg2).getTipId());
						openActivity.putExtra("type_id", details.get(arg2).getTipTypeId());
						openActivity.putExtra("category_id", details.get(arg2).getTipCategoryId());
						openActivity.putExtra("title", details.get(arg2).getTipTitle());
						openActivity.putExtra("short_description", details.get(arg2).getTipShortDescription());
						openActivity.putExtra("description", details.get(arg2).getTipDescription());
						openActivity.putStringArrayListExtra("image", details.get(arg2).getImagesArrayList());
						
						startActivity(openActivity);
						overridePendingTransition(R.anim.slide_in_right,
								R.anim.slide_out_left);
					}
				});

			} else if (result.equals("errorConnection")){
				Log.d("", "testing result" + result);
				helper.AlertDialogue("Problem with your network connection.", ctx, "Error");

			}else {
				listView.setVisibility(View.GONE);
				no_record.setVisibility(View.VISIBLE);

			}

		}

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.action_bar_menu_btn:
			// Intent openActivity = new Intent(DoctorTips.this,
			// MainActivity.class);
			// startActivity(openActivity);
			finish();
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;
		}
	}

}
