package in.co.tpsolutions.myapplication;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostForRoommateFragment extends Fragment implements View.OnClickListener {


    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    int key;

    Bitmap bitmap1, bitmap2, bitmap3;

    int PICK_IMAGE_REQUEST = 1;

    ImageView imageView1, imageView2, imageView3;

    TextView txtName;
    TextView txtEmail;

    //Email Primary Key Variable
    String primary_key;

    Spinner spinner_type_of_occu, spinner_type_of_house, spinner_no_of_rooms, spinner_age, spinner_no_of_roommates, spinner_rent_budget;


    RadioGroup radio_gender, radio_m_stat, radio_food_habit, radio_cooking_skills, radio_u_r_a, radio_smoker, radio_alcho, radio_tv,
            radio_fridge, radio_geaser, radio_ac, radio_wm, radio_internet;
    private RadioButton radio_gender_b, radio_m_stat_b, radio_food_habit_b, radio_cooking_skills_b, radio_u_r_a_b, radio_smoker_b, radio_alcho_b,
            radio_tv_b, radio_fridge_b, radio_geaser_b, radio_ac_b, radio_wm_b, radio_internet_b;
    EditText phone_no, occupation, city, country, nationality, message, address;
    Button submit;


    public PostForRoommateFragment() {
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
        final View rootView = inflater.inflate(R.layout.fragment_post_for_roommate, container, false);

        submit = (Button) rootView.findViewById(R.id.post_for_room);

        txtName = (TextView) rootView.findViewById(R.id.uname_data);
        txtEmail = (TextView) rootView.findViewById(R.id.email_data);

        // Edit Text
        phone_no =(EditText) rootView.findViewById(R.id.phone_no_data);
        occupation =(EditText) rootView.findViewById(R.id.occupation_data);
        city =(EditText) rootView.findViewById(R.id.city_data);
        country =(EditText) rootView.findViewById(R.id.country_data);
        nationality =(EditText) rootView.findViewById(R.id.nationality_data);
        message =(EditText) rootView.findViewById(R.id.additional_pref_data);
        address =(EditText) rootView.findViewById(R.id.address_data);


        //Radio Buttons
        radio_gender = (RadioGroup) rootView.findViewById(R.id.radio_gender);
        radio_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selected_gender_id = radio_gender.getCheckedRadioButtonId();
                radio_gender_b = (RadioButton) rootView.findViewById(selected_gender_id);
            }
        });


        radio_m_stat = (RadioGroup) rootView.findViewById(R.id.radio_mstat);
        radio_m_stat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selected_m_stat_id = radio_m_stat.getCheckedRadioButtonId();
                radio_m_stat_b = (RadioButton) rootView.findViewById(selected_m_stat_id);
            }
        });

        radio_cooking_skills = (RadioGroup) rootView.findViewById(R.id.radio_cooking);
        radio_cooking_skills.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selected_cooking_id = radio_cooking_skills.getCheckedRadioButtonId();
                radio_cooking_skills_b = (RadioButton) rootView.findViewById(selected_cooking_id);
            }
        });

        radio_food_habit = (RadioGroup) rootView.findViewById(R.id.radio_food);
        radio_food_habit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selected_food_habbit_id = radio_food_habit.getCheckedRadioButtonId();
                radio_food_habit_b = (RadioButton) rootView.findViewById(selected_food_habbit_id);
            }
        });

        radio_u_r_a = (RadioGroup) rootView.findViewById(R.id.radio_ura);
        radio_u_r_a.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selected_ura_id = radio_u_r_a.getCheckedRadioButtonId();
                radio_u_r_a_b = (RadioButton) rootView.findViewById(selected_ura_id);
            }
        });

        radio_smoker = (RadioGroup) rootView.findViewById(R.id.radio_smoker);
        radio_smoker.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selected_smoker_id = radio_smoker.getCheckedRadioButtonId();
                radio_smoker_b = (RadioButton) rootView.findViewById(selected_smoker_id);
            }
        });

        radio_alcho = (RadioGroup) rootView.findViewById(R.id.radio_alcohol);
        radio_alcho.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selected_alcho_id = radio_alcho.getCheckedRadioButtonId();
                radio_alcho_b = (RadioButton) rootView.findViewById(selected_alcho_id);

            }
        });



        radio_internet = (RadioGroup) rootView.findViewById(R.id.radio_internet);
        radio_internet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selected_internet_id = radio_internet.getCheckedRadioButtonId();
                radio_internet_b = (RadioButton) rootView.findViewById(selected_internet_id);

            }
        });

        radio_wm = (RadioGroup) rootView.findViewById(R.id.radio_washingmac);
        radio_wm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selected_wm_id = radio_wm.getCheckedRadioButtonId();
                radio_wm_b = (RadioButton) rootView.findViewById(selected_wm_id);

            }
        });

        radio_ac = (RadioGroup) rootView.findViewById(R.id.radio_ac);
        radio_ac.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selected_ac_id = radio_ac.getCheckedRadioButtonId();
                radio_ac_b = (RadioButton) rootView.findViewById(selected_ac_id);

            }
        });

        radio_geaser = (RadioGroup) rootView.findViewById(R.id.radio_geaser);
        radio_geaser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                int selected_geaser_id = radio_geaser.getCheckedRadioButtonId();
                radio_geaser_b = (RadioButton) rootView.findViewById(selected_geaser_id);

            }
        });

        radio_fridge = (RadioGroup) rootView.findViewById(R.id.radio_fridge);
        radio_fridge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selected_fridge_id = radio_fridge.getCheckedRadioButtonId();
                radio_fridge_b = (RadioButton) rootView.findViewById(selected_fridge_id);

            }
        });

        radio_tv = (RadioGroup) rootView.findViewById(R.id.radio_tv);
        radio_tv.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                int selected_tv_id = radio_tv.getCheckedRadioButtonId();
                radio_tv_b = (RadioButton) rootView.findViewById(selected_tv_id);

            }
        });


        //Spinner
        spinner_type_of_occu = (Spinner) rootView.findViewById(R.id.spinner_type_of_occu);
        spinner_type_of_house = (Spinner) rootView.findViewById(R.id.spinner_type_of_house);
        spinner_no_of_rooms = (Spinner) rootView.findViewById(R.id.spinner_no_of_rooms);
        spinner_rent_budget = (Spinner) rootView.findViewById(R.id.spinner_rent_budget);
        spinner_age = (Spinner) rootView.findViewById(R.id.spinner_age);
        spinner_no_of_roommates = (Spinner) rootView.findViewById(R.id.spinner_no_of_roommates);

        //Image Views
        imageView1 = (ImageView) rootView.findViewById(R.id.image1);
        imageView2 = (ImageView) rootView.findViewById(R.id.image2);
        imageView3 = (ImageView) rootView.findViewById(R.id.image3);

        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);


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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // formal parameters
                String phone_no1, occupation1, city1, gender1, country1, nationality1, message1, m_stat1, cooking1, foodhab1, ura1, smoker1,
                        alcho1,type_of_occu1, type_of_house1, no_of_rooms1, age1, no_of_roommates1, rent_budget1, address1, tv1, fridge1,
                        geaser1, ac1, wm1, internet1;

                //radio buttn data
                gender1 = "Male";//radio_gender_b.getText().toString();
                m_stat1 = "Single";//radio_m_stat_b.getText().toString();
                cooking1 = "Non Vegetarian";//radio_cooking_skills_b.getText().toString();
                foodhab1 = "Yes";//radio_food_habit_b.getText().toString();
                ura1 = "Night Owl";//radio_u_r_a_b.getText().toString();
                smoker1 = "NO";//radio_smoker_b.getText().toString();
                alcho1 = "No";//radio_alcho_b.getText().toString();
                tv1 ="Yes";// radio_tv_b.getText().toString();
                fridge1 ="Yes";// radio_fridge_b.getText().toString();
                geaser1 ="No";// radio_geaser_b.getText().toString();
                ac1 = "No";//radio_ac_b.getText().toString();
                wm1 ="No";// radio_wm_b.getText().toString();
                internet1 ="Yes";// radio_internet_b.getText().toString();
                //Spinner
                type_of_occu1 = spinner_type_of_occu.getSelectedItem().toString();
                type_of_house1 = spinner_type_of_house.getSelectedItem().toString();
                no_of_rooms1 = spinner_no_of_rooms.getSelectedItem().toString();
                age1 = spinner_age.getSelectedItem().toString();
                no_of_roommates1 = spinner_no_of_roommates.getSelectedItem().toString();
                rent_budget1 = spinner_rent_budget.getSelectedItem().toString();


                phone_no1 = phone_no.getText().toString();
                occupation1 = occupation.getText().toString();
                city1 = city.getText().toString();
                country1 = country.getText().toString();
                nationality1 = nationality.getText().toString();
                message1 = message.getText().toString();
                address1 = address.getText().toString();


                uploadData(primary_key, phone_no1,gender1, occupation1, city1, country1, nationality1, message1, m_stat1, cooking1,
                        foodhab1, ura1, smoker1, alcho1, type_of_occu1, type_of_house1, no_of_rooms1, age1, no_of_roommates1, rent_budget1,
                         address1, tv1, fridge1, geaser1, ac1, wm1, internet1);
            }
        });


        return rootView;
    }


    private void uploadData(final String primary_key1,final String phone_no2,final String gender2, final String occupation2,
                            final String city2, final String country2, final String nationality2, final String message2,
                            final String m_stat2, final String cooking2, final String foodhab2, final String ura2,
                            final String smoker2, final String alcho2, final String type_of_occu2, final String type_of_house2,
                            final String no_of_rooms2, final String age2, final String no_of_roommates2, final String rent_budget2,
                            final String address2, final String tv2, final String fridge2, final String geaser2, final String ac2,
                            final String wm2, final String internet2){

        String tag_string_req = "req_sendData";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_POST_FOR_ROOMMATE, new Response.Listener<String>() {

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

                //Converting Bitmap to String
                String image1 = getStringImage(bitmap1);
                String image2 = getStringImage(bitmap2);
                String image3 = getStringImage(bitmap3);


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
                params.put("address", address2);
                params.put("tv", tv2);
                params.put("fridge", fridge2);
                params.put("geaser", geaser2);
                params.put("ac", ac2);
                params.put("wm", wm2);
                params.put("int", internet2);
                params.put("image1", image1);
                params.put("image2", image2);
                params.put("image3", image3);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    @Override
    public void onClick(View v) {

        if (v == imageView1){
            key=1;
            showFileChooser1();
        }

        if (v == imageView2){
            key=2;
            showFileChooser2();
        }

        if (v == imageView3){
            key=3;
            showFileChooser3();
        }
    }

    private void showFileChooser1() {
        Intent intent1 = new Intent();
        intent1.setType("image/*");
        intent1.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent1, "Select Pic 1"), PICK_IMAGE_REQUEST);


    }

    private void showFileChooser2() {
        Intent intent2 = new Intent();
        intent2.setType("image/*");
        intent2.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent2, "Select Pic 2"), PICK_IMAGE_REQUEST);
    }

    private void showFileChooser3() {
        Intent intent3 = new Intent();
        intent3.setType("image/*");
        intent3.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent3, "Select Pic 3"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (key == 1) {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
                Uri filePath = data.getData();
                try {
                    bitmap1 = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                    //Setting the Bitmap to ImageView
                    imageView1.setImageBitmap(bitmap1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        if (key == 2) {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
                Uri filePath = data.getData();
                try {
                    bitmap2 = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                    //Setting the Bitmap to ImageView
                    imageView2.setImageBitmap(bitmap2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (key == 3) {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
                Uri filePath = data.getData();
                try {
                    bitmap3 = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                    //Setting the Bitmap to ImageView
                    imageView3.setImageBitmap(bitmap3);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }

        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        // bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

}
