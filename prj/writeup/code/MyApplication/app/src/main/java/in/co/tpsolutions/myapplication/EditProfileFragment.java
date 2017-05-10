package in.co.tpsolutions.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private Button btnLogout;

    TextView txtName;
    TextView txtEmail;

    //Email Primary Key Variable
    String primary_key;

    Spinner spinner_age;


    RadioGroup radio_gender, radio_m_stat;
    private RadioButton radio_gender_b, radio_m_stat_b;
    EditText phone_no, occupation, city, country, nationality, message;
    Button submit;


    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Session manager
        session = new SessionManager(getActivity().getApplicationContext());

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        submit = (Button) rootView.findViewById(R.id.post_for_room);

        txtName = (TextView) rootView.findViewById(R.id.uname_data);
        txtEmail = (TextView) rootView.findViewById(R.id.email_data);
        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);

        phone_no =(EditText) rootView.findViewById(R.id.phone_no_data);
        occupation =(EditText) rootView.findViewById(R.id.occupation_data);
        city =(EditText) rootView.findViewById(R.id.city_data);
        country =(EditText) rootView.findViewById(R.id.country_data);
        nationality =(EditText) rootView.findViewById(R.id.nationality_data);

        //Radio Buttons
        radio_gender = (RadioGroup) rootView.findViewById(R.id.radio_gender);
        int selected_gender_id = radio_gender.getCheckedRadioButtonId();
        radio_gender_b = (RadioButton) rootView.findViewById(selected_gender_id);

        radio_m_stat = (RadioGroup) rootView.findViewById(R.id.radio_mstat);
        int selected_m_stat_id = radio_m_stat.getCheckedRadioButtonId();
        radio_m_stat_b = (RadioButton) rootView.findViewById(selected_m_stat_id);

        //Spinner
        spinner_age = (Spinner) rootView.findViewById(R.id.spinner_age);

        // SqLite database handler
        db = new SQLiteHandler(getActivity().getApplicationContext());

        // Session manager
        session = new SessionManager(getActivity().getApplicationContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("username");
        String email = user.get("email");
        primary_key = email;

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // formal parameters
                String phone_no1, occupation1, city1, gender1, country1, nationality1,  m_stat1, age1;

                //radio buttn data
                gender1 = radio_gender_b.getText().toString();
                m_stat1 = radio_m_stat_b.getText().toString();

                //Spinner
                age1 = spinner_age.getSelectedItem().toString();

                phone_no1 = phone_no.getText().toString();
                occupation1 = occupation.getText().toString();
                city1 = city.getText().toString();
                country1 = country.getText().toString();
                nationality1 = nationality.getText().toString();
                uploadData(primary_key, phone_no1,gender1, occupation1, city1, country1, nationality1, m_stat1, age1);
            }
        });

        return rootView;
    }

    private void uploadData(final String primary_key1,final String phone_no2,final String gender2, final String occupation2,
                            final String city2, final String country2, final String nationality2,
                            final String m_stat2, final String age2){

        String tag_string_req = "req_sendData";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_EDIT_PROFILE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity().getApplicationContext(), "Data Uploaded", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to advrt_us url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", primary_key1);
                params.put("phonenumber", phone_no2);
                params.put("nationality", nationality2);
                params.put("country", country2);
                params.put("city", city2);
                params.put("age", age2);
                params.put("m_stat", m_stat2);
                params.put("gender", gender2);
                params.put("occupation", occupation2);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent();
        intent.setClass(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
    }

}
