package com.example.sushantpaygude.finalproject.Activities;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.sushantpaygude.finalproject.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Intekhab Naser on 5/10/2018.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    public MyFirebaseInstanceIDService() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("onTokenRefresh", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        new UploadTokenAsync().execute("Intekhab", token);
    }

    private class UploadTokenAsync extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Success")) {
                Log.i("UploadTokenAsync", "Successfully updated Token");
                Toast.makeText(getApplicationContext(), "Successfully updated Token!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Log.i("UploadTokenAsync", "Failed to update Token");
                Toast.makeText(getApplicationContext(), "Failed to update Token!",
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String ip = getResources().getString(R.string.ip_address);
            String page = "mc_token.php";
            StringBuilder url_builder = new StringBuilder();
            url_builder.append(ip);
            url_builder.append(page);
            String login_url = url_builder.toString();
            HttpURLConnection conn = null;
            String response = "";

            try {
                URL url = new URL(login_url);
                conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                String urlparameters = "";

                urlparameters += "Device_ID=" + params[0] + "&Token=" + params[1];


                OutputStream outputpost = new BufferedOutputStream(conn.getOutputStream());
                outputpost.write(urlparameters.getBytes());
                outputpost.flush();
                outputpost.close();

                int responseCode = conn.getResponseCode();
                String line = "";
                StringBuilder builder = new StringBuilder();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader b_reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = b_reader.readLine()) != null) {
                        builder.append(line);
                    }
                    b_reader.close();
                    JSONObject jsonObject = new JSONObject(builder.toString());
                    Log.i("Login", "sent" + jsonObject);
                    int result = Integer.parseInt(jsonObject.getString("Result"));
                    if (result == 1) {
                        response = "Success";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return response;
        }
    }
}
