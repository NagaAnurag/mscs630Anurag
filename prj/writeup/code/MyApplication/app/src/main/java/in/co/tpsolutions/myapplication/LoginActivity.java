package in.co.tpsolutions.myapplication;

/**
 * Created by Anurag on 2/15/2016.
 */


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import in.co.tpsolutions.myapplication.R;
import in.co.tpsolutions.myapplication.AppConfig;
import in.co.tpsolutions.myapplication.AppController;
import in.co.tpsolutions.myapplication.SQLiteHandler;
import in.co.tpsolutions.myapplication.SessionManager;

public class LoginActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private Button btntempnav;
    double latitude = 0, longitude = 0;
    public String PriKey;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, PostHome.class);
            startActivity(intent);
            finish();
        }

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(email, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    /**
     * function to verify login details in mysql db
     * */
    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");
                        //String abc = "Anurag";                              // Edit PHP code so that u get username as a jason response
                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("username");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");
                        PriKey = email;
                        //Set Up for Accessing location of the user
                        // Launch main activity inside getMyLocationAddress();
                        getMyLocationAddress(email);
                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at);
                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this,
                                PostHome.class);
                        startActivity(intent);
                        finish();

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private void getMyLocationAddress(String email) {

        //Gather GPS data at a certain time interval.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        MyListener listener = new MyListener();
        /* Use the LocationManager class to obtain GPS locations */
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1f, listener);
        //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        //Check to see if GPS is on
        boolean isGPS = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //If its off, request to turn it on.
        if (isGPS == false)
        {
            //Enable GPS pop-up notification.
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            // set title
            adb.setTitle("Enable GPS?");

            // set dialog message
            adb.setMessage("Enable GPS to get full function from the app.");
            adb.setCancelable(false);

            // set title
            adb.setTitle("Enable GPS?");

            // set dialog message
            adb.setMessage("Enable GPS to get full function from the app.");
            adb.setCancelable(false);

            //Yes Button
            adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivityForResult(
                            new Intent(
                                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                            0);
                }
            });

            //No Button
            adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

            // create GPS Alert [Pop-up]
            AlertDialog alertDialog = adb.create();

            // show it
            alertDialog.show();
        }
        else
        {
            //Added this so when the app gets refreshed it will show the true GPS info status.
            getAddress(email);
        }
    }

    public class MyListener extends LoginActivity implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // Messed Up here!!
            longitude = 17.9689;
            latitude = 79.5941;
            //getAddress(super.PriKey);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public                                                                                                                                                            void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    public void getAddress(String email){



        Geocoder gc = new Geocoder(LoginActivity.this, Locale.getDefault());
        List<Address> addressList;

            try {
                addressList = gc.getFromLocation(latitude, longitude, 2);
                if (addressList != null && addressList.size() > 0) {
                    Address adr = addressList.get(0);
                    String city = adr.getLocality();
                    String country = adr.getCountryName();
                    SendCityAndCountry(city, country, email);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void SendCityAndCountry(final String city, final String country, final String email) {
        // Tag used to cancel the request
        String tag_string_req = "req_city";

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.URL_CITYCOUNTRY, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "City And Country Updated " );
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {



                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Location Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("city", city);
                params.put("country", country);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}



