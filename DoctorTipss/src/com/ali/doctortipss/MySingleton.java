package com.ali.doctortipss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Application;
import android.util.Log;

public class MySingleton extends Application {

	private static MySingleton singleton;
	private String DeviceID;
	static int statusCode;
	public static String ApiUrl = "http://fahimahmed.com/ali/doctortips/public/api/";
	public String SongUrl = "http://fahimahmed.com/ali/doctortips/public/";
	public String storageUrl = "http://fahimahmed.com/ali/doctortips/public/";
	public boolean isRepeat = false;
	public boolean isShuffle = false;
	JSONArray jsonArrayy;
	JSONObject jObject, jObjectt, userObject, joObjectt, statistics,
			joObjectSlider;
	String[] colorArray = { "#3db4c8", "#b3d01e", "#5e5c5d", "#f9644e",
			"#117ab1", "#9c69ea", "#ebb424", "#424b5a", "#2ab9b3" };

	public String getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}

	public static MySingleton getInstance() {
		if (singleton == null)
			singleton = new MySingleton();
		return singleton;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		singleton = this;
	}

	/* get transaction history */
	public static String postFeatured(String postUrl) {
		Log.d("testing ", "testing singleton postUrl" + postUrl);
		StringBuilder builder = new StringBuilder();
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(postUrl);
		try {
			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httpget);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			Log.d("testting", "testing singleton status code" + statusCode);
//			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content), 8192);
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}

//			} else {
//				Log.e(MySingleton.class.toString(), "Failed to download file");
//				return "error";
//			}

		} catch (ClientProtocolException e) {
		} catch (IOException e) {
			return "errorConnection";
		}
		Log.d("testting", "testingbuilder" + builder.toString());
		return builder.toString();
	}

	/* add favourite,playlist */
	public static String postFavourite(String ref_id, String uid) {
		// String statusCode = null;
		StringBuilder builder = new StringBuilder();
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(MySingleton.ApiUrl + "favorite");

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("ref_id", ref_id));
			nameValuePairs.add(new BasicNameValuePair("type", "song"));
			nameValuePairs.add(new BasicNameValuePair("uid", uid));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);
			StatusLine statusLine = response.getStatusLine();

			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(MySingleton.class.toString(), "Failed to download file");
				return "error";
			}

		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		// String all_songs = readTwitterFeed();
		// songs = all_songs;
		return builder.toString();
	}

	// postGenericCall
	public static String postPlaylist(String type, String param1,
			String param2, String param3, String value1, String value2,
			String value3, String DeviceId, String facebookEmail) {
		// String statusCode = null;
		// Log.d("USER LOGIN", "testing params" + type + " " + param1 + " "
		// + param2 + " " + param3 + " " + value1 + " " + value2 + " "
		// + value3 + " " + DeviceId+"facebookEmail "+facebookEmail);
		StringBuilder builder = new StringBuilder();
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(MySingleton.ApiUrl + type);

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair(param1, value1));
			nameValuePairs.add(new BasicNameValuePair(param2, value2));

			nameValuePairs
					.add(new BasicNameValuePair("device_token", DeviceId));
			if (type.equals("playlist")) {
				nameValuePairs.add(new BasicNameValuePair("cover_photo", ""));
			}
			if (type.equals("playlist_songs")) {
				// String[] trimReferenceId = value3.split(",");
				List<String> items = Arrays.asList(value3.split(","));
				for (int i = 0; i < items.size(); i++) {
					nameValuePairs.add(new BasicNameValuePair(param3, items
							.get(i)));
				}

			} else {
				nameValuePairs.add(new BasicNameValuePair(param3, value3));
			}
			if (value3.equals("facebook")) {
				nameValuePairs.add(new BasicNameValuePair("email",
						facebookEmail));
			}

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);
			StatusLine statusLine = response.getStatusLine();

			int statusCode = statusLine.getStatusCode();
			Log.d("USER LOGIN", "testing status code" + statusCode);
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(MySingleton.class.toString(), "Failed to download file");
				return "error";
			}

		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		// String all_songs = readTwitterFeed();
		// songs = all_songs;
		Log.d("USER LOGIN", "testing builder.toString()" + builder.toString());
		return builder.toString();

	}

	// postGenericCall
	public static String userRegestration(String userName,
			String registerEmail, String registerPassword, String type,
			String age, String gender, String country, String is_fb_user,
			String facebook_id, String DeviceId, String accountType,
			String gPhoto) {
		// String statusCode = null;
		// Log.d("testing", "testing params" + userName + " " + registerEmail
		// + " " + registerPassword + " " + type + " " + age + " "
		// + gender + " " + country + " " + is_fb_user + " " + facebook_id
		// + " " + DeviceId+" "+gPhoto);
		StringBuilder builder = new StringBuilder();
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(MySingleton.ApiUrl + "login");
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("name", userName));
			nameValuePairs.add(new BasicNameValuePair("email", registerEmail));
			nameValuePairs.add(new BasicNameValuePair("password",
					registerPassword));
			nameValuePairs.add(new BasicNameValuePair("type", type));
			nameValuePairs.add(new BasicNameValuePair("age", age));
			nameValuePairs.add(new BasicNameValuePair("gender", gender));
			nameValuePairs.add(new BasicNameValuePair("country", country));
			if (accountType.equals("google")) {
				nameValuePairs.add(new BasicNameValuePair("is_google_user",
						is_fb_user));
				nameValuePairs.add(new BasicNameValuePair("google_id",
						facebook_id));
				nameValuePairs.add(new BasicNameValuePair("google_photo",
						gPhoto));
				nameValuePairs.add(new BasicNameValuePair("facebook_id", "0"));

			} else {
				nameValuePairs.add(new BasicNameValuePair("is_fb_user",
						is_fb_user));
				nameValuePairs.add(new BasicNameValuePair("facebook_id",
						facebook_id));

			}
			nameValuePairs
					.add(new BasicNameValuePair("device_token", DeviceId));
			nameValuePairs.add(new BasicNameValuePair("is_mobile", "1"));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);
			StatusLine statusLine = response.getStatusLine();

			int statusCode = statusLine.getStatusCode();
			Log.d("USER LOGIN",
					"testing singleton userRegestration status code"
							+ statusCode);
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(MySingleton.class.toString(), "Failed to download file");
				return "error";
			}

		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		// String all_songs = readTwitterFeed();
		// songs = all_songs;
		Log.d("USER LOGIN",
				"testing singleton userRegestration builder.toString()"
						+ builder.toString());
		return builder.toString();

	}

	// postGenericCall
	public static String removeFavourite(String type, String param1,
			String param2, String param3, String value1, String value2,
			String value3, String DeviceId) {
		// String statusCode = null;
		// Log.d("USER LOGIN", "testing params" + type + " " + param1 + " "
		// + param2 + " " + param3 + " " + value1 + " " + value2 + " "
		// + value3 + " " + DeviceId);
		StringBuilder builder = new StringBuilder();
		HttpClient httpclient = new DefaultHttpClient();
		HttpDelete httppost = new HttpDelete(MySingleton.ApiUrl + type + "?"
				+ param1 + "=" + value1 + "&" + param2 + "=" + value2 + "&"
				+ param3 + "=" + value3 + "&device_token=" + DeviceId);

		try {
			// // Add your data
			// List<NameValuePair> nameValuePairs = new
			// ArrayList<NameValuePair>(2);
			// nameValuePairs.add(new BasicNameValuePair(param1, value1));
			// nameValuePairs.add(new BasicNameValuePair(param2, value2));
			// nameValuePairs.add(new BasicNameValuePair(param3, value3));
			// nameValuePairs
			// .add(new BasicNameValuePair("device_token", DeviceId));
			// if (type.equals("playlist")) {
			// nameValuePairs.add(new BasicNameValuePair("cover_photo", ""));
			// }
			// ((HttpResponse) httppost).setEntity(new
			// UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);
			StatusLine statusLine = response.getStatusLine();

			int statusCode = statusLine.getStatusCode();
			// Log.d("USER LOGIN", "testing status code" + statusCode);
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(MySingleton.class.toString(), "Failed to download file");
				return "error";
			}

		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		// String all_songs = readTwitterFeed();
		// songs = all_songs;
		// Log.d("USER LOGIN", "testing builder.toString()" +
		// builder.toString());
		return builder.toString();

	}

	/* get transaction history */
	public static String postActivate(String postUrl) {

		// Log.d("testing ", "testing singleton postUrl" + postUrl);
		StringBuilder builder = new StringBuilder();
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(postUrl);
		try {
			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httpget);
			StatusLine statusLine = response.getStatusLine();
			statusCode = statusLine.getStatusCode();
			// Log.d("testting", "testing singleton status code" + statusCode);
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content), 8192);
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}

			} else {
				Log.e(MySingleton.class.toString(), "Failed to download file");
				return "error";
			}

		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		// Log.d("testting", "testingbuilder" + builder.toString());
		return String.valueOf(statusCode);
	}

	// postCall whenever song play
	public String postSongLog(String uid, String ref_id) {
		// String statusCode = null;
		StringBuilder builder = new StringBuilder();
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(MySingleton.ApiUrl + "log");

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("uid", uid));
			nameValuePairs.add(new BasicNameValuePair("type", "song"));
			nameValuePairs.add(new BasicNameValuePair("ref_id", ref_id));
			nameValuePairs.add(new BasicNameValuePair("action_type",
					"song_played"));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);
			StatusLine statusLine = response.getStatusLine();

			int statusCode = statusLine.getStatusCode();
			Log.d("USER LOGIN", "testing status code" + statusCode);
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(MySingleton.class.toString(), "Failed to download file");
				return "error";
			}

		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		// String all_songs = readTwitterFeed();
		// songs = all_songs;
		Log.d("USER LOGIN", "testing builder.toString()" + builder.toString());
		return builder.toString();

	}
}