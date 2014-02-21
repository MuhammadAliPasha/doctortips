package com.ali.doctortipss;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Helper {
	ProgressDialog progress;
	
	/* Progress Dialogue Loading Spinner */
	public void AlertProgressDialogue(String type, Context context) {
		if (type == "show") {
			progress = new ProgressDialog(context);
			progress.setMessage("Please wait...");
			progress.setCancelable(false);
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.show();
		} else {
			progress.dismiss();
		}

	}
	
	// AlertDialogue
		public void AlertDialogue(String content, Context context, String title) {

			final AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle(title).setMessage(content).setCancelable(false)
					.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							builder.setCancelable(true);
						}
					}).show();

		}

}
