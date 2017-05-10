package in.co.tpsolutions.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PostHome extends AppCompatActivity {

    Toolbar toolbar;
    Boolean exit = false;
    String abc;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    String zero="0";

    String dashboardKey, frndDashboardKey;
    String dashboardKey1;

    // Navigation Drawer Header Variables
    ImageView userImage;
    TextView userName, userEmail;
    String UserNameText;

    private static final String TAG = PostHome.class.getSimpleName();

    private SessionManager session;
    private SQLiteHandler db;
    String primary_key, DashboardKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_home);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        // Navigation Drawer Header Variables
        userImage = (ImageView) findViewById(R.id.User_Image) ;
        userName = (TextView) findViewById(R.id.nav_username) ;
        userEmail  = (TextView) findViewById(R.id.nav_user_email) ;

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        primary_key = user.get("email");
        UserNameText = user.get("username");

        // Tag used to cancel the request
        String tag_string_req = "req_yourPostData";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_DASHBOARDKEY, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Your Post Response: " + response.toString());
                String a;
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        JSONObject user = jObj.getJSONObject("user");

                        // Call all the data from server

                        // Control Variables
                        String bc = user.getString("first_post_variable");
                        if (bc.equals("1")) {
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.add(R.id.postHome_Container, new PostForRoomFragment());
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("Welcome");
                        }
                        else {
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.add(R.id.postHome_Container, new DashboardFragment());
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("Welcome");
                        }


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
                Log.e(TAG, "Your Post Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", primary_key);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);



        //DashboardKey =
        //getDashboardVariables(primary_key);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView)findViewById(R.id.design_navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.dashboard_id:
                        // Tag used to cancel the request
                        String tag_string_req = "req_yourPostData";


                        StringRequest strReq = new StringRequest(Request.Method.POST,
                                AppConfig.URL_DASHBOARDKEY, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "Your Post Response: " + response.toString());
                                String a;
                                try {
                                    JSONObject jObj = new JSONObject(response);
                                    boolean error = jObj.getBoolean("error");

                                    // Check for error node in json
                                    if (!error) {

                                        JSONObject user = jObj.getJSONObject("user");

                                        // Call all the data from server

                                        // Control Variables
                                        String bc = user.getString("first_post_variable");
                                        if (bc.equals("1")) {
                                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                            fragmentTransaction.replace(R.id.postHome_Container, new PostForRoomFragment());
                                            fragmentTransaction.commit();
                                            getSupportActionBar().setTitle("Post Activity");
                                        }
                                        else {
                                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                            fragmentTransaction.replace(R.id.postHome_Container, new DashboardFragment());
                                            fragmentTransaction.commit();
                                            getSupportActionBar().setTitle("Dashboard Activity");
                                        }


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
                                Log.e(TAG, "Your Post Error: " + error.getMessage());
                                Toast.makeText(getApplicationContext(),
                                        error.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }){

                            @Override
                            protected Map<String, String> getParams() {
                                // Posting parameters to login url
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("email", primary_key);
                                return params;
                            }

                        };

                        // Adding request to request queue
                        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

                            item.setChecked(true);
                            drawerLayout.closeDrawers();
                            break;


                    case R.id.postForRoomFrag_id:
                        fragmentTransaction =getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.postHome_Container, new PostForRoomFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Post For Room");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.postForRoommate_id:
                        fragmentTransaction =getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.postHome_Container, new PostForRoommateFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Post For Roommate");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.postForTenant_id:
                        fragmentTransaction =getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.postHome_Container, new PostForTenantFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Post For Tenant");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.editProfile_id:
                        fragmentTransaction =getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.postHome_Container, new EditProfileFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Edit Profile");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                }

                return false;
            }
        });

    }


    /* The Handler here handles accidental back presses, it simply shows a Toast, and if there is
    another back press within 3 seconds, it closes the application. */
    @Override
    public void onBackPressed() {
        if (exit){
            finish();
        } else {
            Toast.makeText(this, "Press Back Again To Exit.", Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            },3*1000);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    // Options Menu Code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.oprions_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.aboutUs_id:
                fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.postHome_Container, new AboutUsFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("About Us Activity");
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.contactUS_id:
                fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.postHome_Container, new ContactUsFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("Contact Us Activity");
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.advertiseWithUs_id:
                fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.postHome_Container, new AdvertiseWithUsFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("Advertise With Us Activity");
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.termsPrivacyFAQ_id:
                fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.postHome_Container, new TermsPrivacyFAQFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("Terms, Privacy FAQ Activity");
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;

        }

        return super.onOptionsItemSelected(item);
    }



}
