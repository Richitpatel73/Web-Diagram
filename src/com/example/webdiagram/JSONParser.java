package com.example.webdiagram;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	// constructor
	public JSONParser() {

	}

	// function get json from url
	// by making HTTP POST or GET mehtod
	public int makeHttpRequest(String url, String method,
			List<NameValuePair> params) {
		String text = null;
		int success = 0;
		String message = null;
		// Making HTTP request
		try {

			// request method is POST
			// defaultHttpClient
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpEntity httpentity = httpClient.execute(httpPost).getEntity();

			// adding further code is some error occurs then remove this code
			// and revert back.
			if (httpentity != null) {

				text = EntityUtils.toString(httpentity);
				httpentity.consumeContent();
				httpClient.getConnectionManager().shutdown();

				JSONObject jsonArray = new JSONObject(text);

				success = jsonArray.getInt("success");
				message = jsonArray.getString("message");

			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		} catch (ClientProtocolException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;

	}
}
