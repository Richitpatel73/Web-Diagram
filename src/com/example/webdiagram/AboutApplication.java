package com.example.webdiagram;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class AboutApplication extends Activity {
	
	ImageButton btnCall;
	ImageButton btnEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_aboutapplication);
		btnCall = (ImageButton) findViewById(R.id.btn_Call);
		btnEmail= (ImageButton) findViewById(R.id.btn_email);
		
		
		btnCall.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent dialIntent = new Intent(Intent.ACTION_DIAL,
						Uri.parse("tel:+918971968306"));
				startActivity(dialIntent);
				
			}
		});
		btnEmail.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
					// TODO Auto-generated method stub
				sendEmail();
				}
			});
	}
	protected void sendEmail() {
	      Log.i("Send email", "");

	      String[] TO = {"meghana.shetty@oracle.com"};
	      
	      Intent emailIntent = new Intent(Intent.ACTION_SEND);
	      emailIntent.setData(Uri.parse("mailto:"));
	      emailIntent.setType("text/plain");


	      emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
	      emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Enter subject here");
	      emailIntent.putExtra(Intent.EXTRA_TEXT, "Enter email message here");

	      try {
	         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	         finish();
	         Log.i("Finished sending email...", "");
	      } catch (android.content.ActivityNotFoundException ex) {
	         Toast.makeText(AboutApplication.this, 
	         "There is no email client installed.", 2000).show();
	      }
	   }

}
