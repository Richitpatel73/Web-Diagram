package com.example.webdiagram;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText ET_CustomerId;
	EditText ET_CustomerName;
	EditText ET_CustomerProject;
	EditText ET_CustomerDiagramStage;
	EditText ET_CustomerDiscussionDate;

	EditText ET_CustomerContact;
	EditText ET_ContactTitle;
	EditText ET_ContactRep;
	EditText ET_ContactSC;
	EditText ET_ContactDiscussion;
	EditText ET_OppId;

	EditText ET_AppName;
	EditText ET_AppOwner;
	EditText ET_LeadArchitect;
	EditText ET_Project;
	EditText ET_Problem;
	EditText ET_MiddleWare;
	EditText ET_Database;
	EditText ET_HwOs;
	EditText ET_Solution;
	EditText ET_ODA;

	String CustomerID;
	String CustomerName;
	String CustomerProject;
	String CustomerDiagramStage;
	String CustomerDiscussionDate;

	String CustomerContact;
	String ContactTitle;
	String ContactRep;
	String ContactSC;
	String ContactDiscussion;
	String OppId;

	String AppName;
	String AppOwner;
	String LeadArchitect;
	String Project;
	String Problem;
	String MiddleWare;
	String Database;
	String HwOs;
	String Solution;
	String ODA;

	ImageButton BTNSubmit;
	ImageButton BTNClear;

	JSONParser jsonParser = new JSONParser();
	Vibrator vibrator;

	// url to create new product
	private static String url_create_record = "http://webdiagram.host56.com/create_record.php";

	// Progress Dialog
	private ProgressDialog pDialog;

	/*------------flags for checking internet conection-------*/
	// flag for Internet connection status
	Boolean isInternetPresent = false;
	// Connection detector class
	ConnectionDetector cd;

	/*--------------------------------------------------------*/

	private void resetFields() {
		ET_CustomerId.setText(null);
		ET_CustomerName.setText(null);
		ET_CustomerProject.setText(null);
		ET_CustomerDiagramStage.setText(null);
		ET_CustomerDiscussionDate.setText(null);

		ET_CustomerContact.setText(null);
		ET_ContactTitle.setText(null);
		ET_ContactRep.setText(null);
		ET_ContactSC.setText(null);
		ET_ContactDiscussion.setText(null);
		ET_OppId.setText(null);

		ET_AppName.setText(null);
		ET_AppOwner.setText(null);
		ET_LeadArchitect.setText(null);
		ET_Project.setText(null);
		ET_Problem.setText(null);
		ET_MiddleWare.setText(null);
		ET_Database.setText(null);
		ET_HwOs.setText(null);
		ET_Solution.setText(null);
		ET_ODA.setText(null);
	}

	public void getData() {
		CustomerID = ET_CustomerId.getText().toString();
		CustomerName = ET_CustomerName.getText().toString();
		CustomerProject = ET_CustomerProject.getText().toString();
		CustomerDiagramStage = ET_CustomerDiagramStage.getText().toString();
		CustomerDiscussionDate = ET_CustomerDiscussionDate.getText().toString();

		CustomerContact = ET_CustomerContact.getText().toString();
		ContactTitle = ET_ContactTitle.getText().toString();
		ContactRep = ET_ContactRep.getText().toString();
		ContactSC = ET_ContactSC.getText().toString();
		ContactDiscussion = ET_ContactDiscussion.getText().toString();
		OppId = ET_OppId.getText().toString();

		AppName = ET_AppName.getText().toString();
		AppOwner = ET_AppOwner.getText().toString();
		LeadArchitect = ET_LeadArchitect.getText().toString();
		Project = ET_Project.getText().toString();
		Problem = ET_Problem.getText().toString();
		MiddleWare = ET_MiddleWare.getText().toString();
		Database = ET_Database.getText().toString();
		HwOs = ET_HwOs.getText().toString();
		Solution = ET_Solution.getText().toString();
		ODA = ET_ODA.getText().toString();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anotherlayout);

		// creating connection detector class instance
		cd = new ConnectionDetector(getApplicationContext());
		// get Internet status
		isInternetPresent = cd.isConnectingToInternet();
		// check for Internet status
		if (!isInternetPresent) {
			// Internet connection is not present
			// Ask user to connect to Internet
			showAlertDialog(MainActivity.this, "No Internet Connection",
					"You don't have internet connection. " + "\n"
							+ "Enable it & restart the app.", false);
		}

		ET_CustomerId = (EditText) findViewById(R.id.et_customerId);
		ET_CustomerName = (EditText) findViewById(R.id.et_customerName);
		ET_CustomerProject = (EditText) findViewById(R.id.et_customerProject);
		ET_CustomerDiagramStage = (EditText) findViewById(R.id.et_customerDiagramStage);
		ET_CustomerDiscussionDate = (EditText) findViewById(R.id.et_customerDiscussionDate);

		ET_CustomerContact = (EditText) findViewById(R.id.et_customerContact);
		ET_ContactTitle = (EditText) findViewById(R.id.et_ContactTitle);
		ET_ContactRep = (EditText) findViewById(R.id.et_ContactRep);
		ET_ContactSC = (EditText) findViewById(R.id.et_ContactSC);
		ET_ContactDiscussion = (EditText) findViewById(R.id.et_ContactDiscussion);
		ET_OppId = (EditText) findViewById(R.id.et_OppId);

		ET_AppName = (EditText) findViewById(R.id.et_appName);
		ET_AppOwner = (EditText) findViewById(R.id.et_appOwner);
		ET_LeadArchitect = (EditText) findViewById(R.id.et_leadArchitect);
		ET_Project = (EditText) findViewById(R.id.et_project);
		ET_Problem = (EditText) findViewById(R.id.et_problem);
		ET_MiddleWare = (EditText) findViewById(R.id.et_middleWare);
		ET_Database = (EditText) findViewById(R.id.et_database);
		ET_HwOs = (EditText) findViewById(R.id.et_hwos);
		ET_Solution = (EditText) findViewById(R.id.et_solution);
		ET_ODA = (EditText) findViewById(R.id.et_ODA);
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		BTNSubmit = (ImageButton) findViewById(R.id.BTN_Submit);
		BTNClear = (ImageButton) findViewById(R.id.BTN_clear);

		final long[] pattern = { 50, 10 };

		BTNClear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				vibrator.vibrate(pattern, -1);
				resetFields();

			}
		});

		BTNSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				vibrator.vibrate(pattern, -1);
				getData();
				if (isEmpty(CustomerID) || isEmpty(CustomerName)
						|| isEmpty(CustomerProject)
						|| isEmpty(CustomerDiagramStage)
						|| isEmpty(CustomerDiagramStage)) {
					Toast.makeText(
							getBaseContext(),
							"Please fill all the \"CUSTOMER\" details properly.",
							1000).show();
				} else if (isEmpty(CustomerContact) || isEmpty(ContactTitle)
						|| isEmpty(ContactRep) || isEmpty(ContactSC)
						|| isEmpty(ContactDiscussion) || isEmpty(OppId)) {
					Toast.makeText(
							getBaseContext(),
							"Please fill all the \"CONTACT\" details properly.",
							1000).show();
				} else if (isEmpty(AppName) || isEmpty(AppOwner)
						|| isEmpty(LeadArchitect) || isEmpty(Project)
						|| isEmpty(Problem) || isEmpty(MiddleWare)
						|| isEmpty(Database) || isEmpty(HwOs)
						|| isEmpty(Solution) || isEmpty(ODA)) {
					Toast.makeText(
							getBaseContext(),
							"Please fill all the \"SITUATION\" details properly.",
							1000).show();
				} else {
					new CreateNewProduct().execute();
				}
			}
		});

	}

	protected boolean isEmpty(String text) {
		// TODO Auto-generated method stub
		if (text.equals("")) {
			return true;
		} else {
			return false;
		}

	}

	class CreateNewProduct extends AsyncTask<String, String, Integer> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Creating Record..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected Integer doInBackground(String... args) {

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("CustomerID", CustomerID));
			params.add(new BasicNameValuePair("CustomerName", CustomerName));
			params.add(new BasicNameValuePair("CustomerProject",
					CustomerProject));
			params.add(new BasicNameValuePair("CustomerDiagramStage",
					CustomerDiagramStage));
			params.add(new BasicNameValuePair("CustomerDiscussionDate",
					CustomerDiscussionDate));

			params.add(new BasicNameValuePair("CustomerContact",
					CustomerContact));
			params.add(new BasicNameValuePair("ContactTitle", ContactTitle));
			params.add(new BasicNameValuePair("ContactRep", ContactRep));
			params.add(new BasicNameValuePair("ContactSC", ContactSC));
			params.add(new BasicNameValuePair("ContactDiscussion",
					ContactDiscussion));
			params.add(new BasicNameValuePair("OppId", OppId));

			params.add(new BasicNameValuePair("AppName", AppName));
			params.add(new BasicNameValuePair("AppOwner", AppOwner));
			params.add(new BasicNameValuePair("LeadArchitect", LeadArchitect));
			params.add(new BasicNameValuePair("Project", Project));
			params.add(new BasicNameValuePair("Problem", Problem));
			params.add(new BasicNameValuePair("MiddleWare", MiddleWare));
			params.add(new BasicNameValuePair("Database", Database));
			params.add(new BasicNameValuePair("HwOs", HwOs));
			params.add(new BasicNameValuePair("Solution", Solution));
			params.add(new BasicNameValuePair("ODA", ODA));

			return jsonParser
					.makeHttpRequest(url_create_record, "POST", params);
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(Integer result) {
			// dismiss the dialog once done
			pDialog.dismiss();
			
			
			if (result == 1) {
				Toast toast = Toast.makeText(getBaseContext(), "Record successfully created",
						1000);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				resetFields();
			} else {
				Toast toast = Toast.makeText(getBaseContext(),"oops! an error occured. Try again..",
						1000);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i = new Intent(getBaseContext(), AboutApplication.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		// Setting Dialog Title
		alertDialog.setTitle(title);
		// Setting Dialog Message
		alertDialog.setMessage(message);
		// Setting alert dialog icon
		alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
		// Setting OK Button
		alertDialog.setButton("Exit", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

}
