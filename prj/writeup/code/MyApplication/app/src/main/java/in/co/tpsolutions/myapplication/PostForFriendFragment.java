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
public class PostForFriendFragment extends Fragment {

    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;


    //Email Primary Key Variable
    String primary_key;

    Spinner spinner_type_of_occu, spinner_type_of_house, spinner_no_of_rooms, spinner_age, spinner_no_of_roommates, spinner_rent_budget;


    RadioGroup radio_gender, radio_m_stat, radio_food_habit, radio_cooking_skills, radio_u_r_a, radio_smoker, radio_alcho;
    private RadioButton radio_gender_b, radio_m_stat_b, radio_food_habit_b, radio_cooking_skills_b, radio_u_r_a_b, radio_smoker_b, radio_alcho_b;
    EditText frnd_name, frnd_email, phone_no, occupation, city, country, nationality, message;
    Button submit;



    public PostForFriendFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_post_for_friend, container, false);

        submit = (Button) rootView.findViewById(R.id.post_for_room);
        //frnd_name = (EditText) rootView.findViewById(R.id.frnd_name_data);
        //frnd_email = (EditText) rootView.findViewById(R.id.frnd_email_data);
        phone_no =(EditText) rootView.findViewById(R.id.phone_no_data);
        occupation =(EditText) rootView.findViewById(R.id.occupation_data);
        city =(EditText) rootView.findViewById(R.id.city_data);
        country =(EditText) rootView.findViewById(R.id.country_data);
        nationality =(EditText) rootView.findViewById(R.id.nationality_data);
        message =(EditText) rootView.findViewById(R.id.additional_pref_data);


        //Radio Buttons
        radio_gender = (RadioGroup) rootView.findViewById(R.id.radio_gender);
        int selected_gender_id = radio_gender.getCheckedRadioButtonId();
        radio_gender_b = (RadioButton) rootView.findViewById(selected_gender_id);

        radio_m_stat = (RadioGroup) rootView.findViewById(R.id.radio_mstat);
        int selected_m_stat_id = radio_m_stat.getCheckedRadioButtonId();
        radio_m_stat_b = (RadioButton) rootView.findViewById(selected_m_stat_id);

        radio_cooking_skills = (RadioGroup) rootView.findViewById(R.id.radio_cooking);
        int selected_cooking_id = radio_cooking_skills.getCheckedRadioButtonId();
        radio_cooking_skills_b = (RadioButton) rootView.findViewById(selected_cooking_id);

        radio_food_habit = (RadioGroup) rootView.findViewById(R.id.radio_food);
        int selected_food_habbit_id = radio_food_habit.getCheckedRadioButtonId();
        radio_food_habit_b = (RadioButton) rootView.findViewById(selected_food_habbit_id);

        radio_u_r_a = (RadioGroup) rootView.findViewById(R.id.radio_ura);
        int selected_ura_id = radio_u_r_a.getCheckedRadioButtonId();
        radio_u_r_a_b = (RadioButton) rootView.findViewById(selected_ura_id);

        radio_smoker = (RadioGroup) rootView.findViewById(R.id.radio_smoker);
        int selected_smoker_id = radio_smoker.getCheckedRadioButtonId();
        radio_smoker_b = (RadioButton) rootView.findViewById(selected_smoker_id);

        radio_alcho = (RadioGroup) rootView.findViewById(R.id.radio_alcohol);
        int selected_alcho_id = radio_alcho.getCheckedRadioButtonId();
        radio_alcho_b = (RadioButton) rootView.findViewById(selected_alcho_id);


        //Spinner
        spinner_type_of_occu = (Spinner) rootView.findViewById(R.id.spinner_type_of_occu);
        spinner_type_of_house = (Spinner) rootView.findViewById(R.id.spinner_type_of_house);
        spinner_no_of_rooms = (Spinner) rootView.findViewById(R.id.spinner_no_of_rooms);
        spinner_rent_budget = (Spinner) rootView.findViewById(R.id.spinner_rent_budget);
        spinner_age = (Spinner) rootView.findViewById(R.id.spinner_age);
        spinner_no_of_roommates = (Spinner) rootView.findViewById(R.id.spinner_no_of_roommates);


        // SqLite database handler
        db = new SQLiteHandler(getActivity().getApplicationContext());

        // Session manager
        session = new SessionManager(getActivity().getApplicationContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("username");
        String email = user.get("email");
        primary_key = email;



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // formal parameters
                String phone_no1, occupation1, city1, gender1, country1, nationality1, message1, m_stat1, cooking1, foodhab1, ura1, smoker1,
                        alcho1,type_of_occu1, type_of_house1, no_of_rooms1, age1, no_of_roommates1, rent_budget1, frnd_name1, frnd_email1;

                //radio buttn data
                gender1 = radio_gender_b.getText().toString();
                m_stat1 = radio_m_stat_b.getText().toString();
                cooking1 = radio_cooking_skills_b.getText().toString();
                foodhab1 = radio_food_habit_b.getText().toString();
                ura1 = radio_u_r_a_b.getText().toString();
                smoker1 = radio_smoker_b.getText().toString();
                alcho1 = radio_alcho_b.getText().toString();

                //Spinner
                type_of_occu1 = spinner_type_of_occu.getSelectedItem().toString();
                type_of_house1 = spinner_type_of_house.getSelectedItem().toString();
                no_of_rooms1 = spinner_no_of_rooms.getSelectedItem().toString();
                age1 = spinner_age.getSelectedItem().toString();
                no_of_roommates1 = spinner_no_of_roommates.getSelectedItem().toString();
                rent_budget1 = spinner_rent_budget.getSelectedItem().toString();

                // Edit Text
                frnd_name1 = frnd_name.getText().toString();
                frnd_email1 = frnd_email.getText().toString();
                phone_no1 = phone_no.getText().toString();
                occupation1 = occupation.getText().toString();
                city1 = city.getText().toString();
                country1 = country.getText().toString();
                nationality1 = nationality.getText().toString();
                message1 = message.getText().toString();
                uploadData(primary_key, phone_no1,gender1, occupation1, city1, country1, nationality1, message1, m_stat1, cooking1,
                        foodhab1, ura1, smoker1, alcho1, type_of_occu1, type_of_house1, no_of_rooms1, age1, no_of_roommates1, rent_budget1,
                        frnd_name1, frnd_email1);
            }
        });


        return rootView;
    }

    private void uploadData(final String primary_key1,final String phone_no2,final String gender2, final String occupation2,
                            final String city2, final String country2, final String nationality2, final String message2,
                            final String m_stat2, final String cooking2, final String foodhab2, final String ura2,
                            final String smoker2, final String alcho2, final String type_of_occu2, final String type_of_house2,
                            final String no_of_rooms2, final String age2, final String no_of_roommates2, final String rent_budget2,
                            final String frnd_name2, final String frnd_email2){

        String tag_string_req = "req_sendData";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_POST_FOR_FRND, new Response.Listener<String>() {

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
                params.put("food", foodhab2);
                params.put("rentbudget", rent_budget2);
                params.put("phonenumber", phone_no2);
                params.put("nationality", nationality2);
                params.put("country", country2);
                params.put("city", city2);
                params.put("age", age2);
                params.put("m_stat", m_stat2);
                params.put("gender", gender2);
                params.put("smoker", smoker2);
                params.put("alcoholic", alcho2);
                params.put("nofrm", no_of_roommates2);
                params.put("type_of_house", type_of_house2);
                params.put("message", message2);
                params.put("number_of_rooms", no_of_rooms2);
                params.put("occupancy", type_of_occu2);
                params.put("occupation", occupation2);
                params.put("cooking_skills", cooking2);
                params.put("ura", ura2);
                params.put("frnd_name", frnd_name2);
                params.put("frnd_email", frnd_email2);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

}
