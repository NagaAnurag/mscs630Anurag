package in.co.tpsolutions.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request.Method;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment{

    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private static final String TAG = RegisterActivity.class.getSimpleName();

    //Your Post
    // Text Views Of Your Post Data
    TextView YourPostData, NameData, GenderData, PhnoData, CityData, Type_of_house_data, CountryData, NationalityData, RentTypeData,
            AgeData, m_statData, NoOfRoomsData, NoOfRoomatesData, SmokingData, DrinkingData, FoodHabitsData, MessageData, OccupancyData,
            URA_Data, OccupationData, CookingSkillsData, AddressData, tx_viewFooter;

    // Text Views Of Your Post Lable
    TextView AddressLable, RentTypeLable, NameLable, GenderLable, PhnoLable, CityLable, Type_of_house_Lable, CountryLable, NationalityLable,
            AgeLable, m_statLable, NoOfRoomsLable, NoOfRoomatesLable, SmokingLable, DrinkingLable, FoodHabitsLable, MessageLable,
            OccupancyLable, URA_Lable, OccupationLable, CookingSkillsLable;

    // ImageView of Your Post
    ImageView p_ImageView1, p_ImageView2, p_ImageView3, tv_ImageView, wm_ImageView, fridge_ImageView, internet_ImageView, geaser_ImageView,
            ac_ImageView;

    // Best Match Results
    //Creating a List of Best Match Results
    private List<bmResults> bmResultsList;

    //Creating Views for Best Match Results
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    //Volley Request Queue
    private RequestQueue requestQueue;

    //The request counter to send ?page=1, ?page=2  requests
    private int requestCount = 1;

    // Suggestions Results
    //Creating a List of Best Match Results
    private List<sResults> sResultsList;

    //Creating Views for Suggestion Results
    private RecyclerView s_recyclerView;
    private RecyclerView.LayoutManager s_layoutManager;
    private RecyclerView.Adapter s_adapter;

    //Volley Request Queue
    private RequestQueue s_requestQueue;

    //The request counter to send ?page=1, ?page=2  requests
    private int s_requestCount = 1;

    //Email Primary Key Variable
    String primary_key;

    int key;

    public DashboardFragment() {
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
        final View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);


        // Your Post Variable Initialization
        //text view data variables
        YourPostData = (TextView) rootView.findViewById(R.id.YourPostData);
        NameData = (TextView) rootView.findViewById(R.id.NameData);
        GenderData = (TextView) rootView.findViewById(R.id.GenderData);
        PhnoData = (TextView) rootView.findViewById(R.id.phnoData);
        CityData = (TextView) rootView.findViewById(R.id.CityData);
        Type_of_house_data = (TextView) rootView.findViewById(R.id.Type_of_house_Data);
        CountryData = (TextView) rootView.findViewById(R.id.CountryData);
        NationalityData = (TextView) rootView.findViewById(R.id.NationalityData);
        RentTypeData = (TextView) rootView.findViewById(R.id.RentTypeData);
        AgeData = (TextView) rootView.findViewById(R.id.AgeData);
        m_statData = (TextView) rootView.findViewById(R.id.m_statData);
        NoOfRoomsData = (TextView) rootView.findViewById(R.id.no_of_rooms_Data);
        NoOfRoomatesData = (TextView) rootView.findViewById(R.id.no_of_roommates_Data);
        SmokingData = (TextView) rootView.findViewById(R.id.Smoking_Data);
        DrinkingData = (TextView) rootView.findViewById(R.id.Alcohol_Data);
        FoodHabitsData = (TextView) rootView.findViewById(R.id.Food_habbits_Data);
        MessageData = (TextView) rootView.findViewById(R.id.Message_Data);
        OccupancyData = (TextView) rootView.findViewById(R.id.occupancy_Data);
        URA_Data = (TextView) rootView.findViewById(R.id.URA_Data);
        OccupationData = (TextView) rootView.findViewById(R.id.occupation_data);
        CookingSkillsData = (TextView) rootView.findViewById(R.id.cooking_skills_data);
        AddressData = (TextView) rootView.findViewById(R.id.Address_data);

        //text view lable variables
        AddressLable = (TextView) rootView.findViewById(R.id.Address_Lable);
        RentTypeLable = (TextView) rootView.findViewById(R.id.RentTypeLable);
        NameLable = (TextView) rootView.findViewById(R.id.NameLable);
        GenderLable = (TextView) rootView.findViewById(R.id.GenderLable);
        PhnoLable = (TextView) rootView.findViewById(R.id.phnoLable);
        CityLable = (TextView) rootView.findViewById(R.id.CityLable);
        Type_of_house_Lable = (TextView) rootView.findViewById(R.id.Type_of_house_Lable);
        CountryLable = (TextView) rootView.findViewById(R.id.CountryLable);
        NationalityLable = (TextView) rootView.findViewById(R.id.NationalityLable);
        AgeLable = (TextView) rootView.findViewById(R.id.AgeLable);
        m_statLable = (TextView) rootView.findViewById(R.id.m_statLable);
        NoOfRoomsLable = (TextView) rootView.findViewById(R.id.no_of_rooms_lable);
        NoOfRoomatesLable = (TextView) rootView.findViewById(R.id.no_of_roommates_lable);
        SmokingLable = (TextView) rootView.findViewById(R.id.Smoking_lable);
        DrinkingLable = (TextView) rootView.findViewById(R.id.Alcohol_lable);
        FoodHabitsLable = (TextView) rootView.findViewById(R.id.Food_habbits_lable);
        MessageLable = (TextView) rootView.findViewById(R.id.Message_lable);
        OccupancyLable = (TextView) rootView.findViewById(R.id.occupancy_lable);
        URA_Lable = (TextView) rootView.findViewById(R.id.URA_lable);
        OccupationLable = (TextView) rootView.findViewById(R.id.occupation_lable);
        CookingSkillsLable = (TextView) rootView.findViewById(R.id.cooking_skills_lable);



        //image view
        p_ImageView1 = (ImageView) rootView.findViewById(R.id.p_imagView1);
        p_ImageView2 = (ImageView) rootView.findViewById(R.id.p_imagView2);
        p_ImageView3 = (ImageView) rootView.findViewById(R.id.p_imagView3);
        tv_ImageView = (ImageView) rootView.findViewById(R.id.imageViewTV);
        wm_ImageView = (ImageView) rootView.findViewById(R.id.imageViewWm);
        fridge_ImageView = (ImageView) rootView.findViewById(R.id.imageViewFridge);
        internet_ImageView = (ImageView) rootView.findViewById(R.id.imageViewInternet);
        geaser_ImageView = (ImageView) rootView.findViewById(R.id.imageViewGeaser);
        ac_ImageView = (ImageView) rootView.findViewById(R.id.imageViewAC);

        // Recycler View For Best Match Results
        recyclerView = (RecyclerView) rootView.findViewById(R.id.bestMatchRecyclerView);

        // Recycler View For Suggestion Results
        //s_recyclerView = (RecyclerView) rootView.findViewById(R.id.suggestionRecyclerView);

        // SqLite database handler
        db = new SQLiteHandler(getActivity().getApplicationContext());

        // Session manager
        session = new SessionManager(getActivity().getApplicationContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");
        primary_key = email;

        if (!email.isEmpty()){
            getYourPost(primary_key);
            bmResults(primary_key);
            //sResults(primary_key);
        }

        return rootView;
    }

    // Suggestions Logic
    private void sResults(String primary_key) {
        //Suggestions
        //Initializing Views

        s_recyclerView.setHasFixedSize(true);
        s_layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        s_recyclerView.setLayoutManager(s_layoutManager);

        //Initializing our superheroes list
        sResultsList = new ArrayList<>();
        s_requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        //Sending primary key
        //sendPimaryKey(primary_key);

        //Calling method to get data to fetch data
        getSuggestionData();

        //Adding an scroll change listener to recyclerview
        // recyclerView.setOnScrollChangeListener();

        //initializing our adapter
        s_adapter = new sCardAdapter(sResultsList, getActivity());

        //Adding adapter to recyclerview
        s_recyclerView.setAdapter(s_adapter);
    }

    private void sendPimaryKey(final String primary_key) {
        // Tag used to cancel the request
        String tag_string_req = "req_primary_key";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_SUGESTIONRESULTS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

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
    }

    //This method will get data from the web api
    private void getSuggestionData() {
        //Adding the method to the queue by calling the method getDataFromServer
        s_requestQueue.add(getSuggestionDataFromServer(s_requestCount));
        //Incrementing the request counter
        s_requestCount++;
    }

    //Request to get json from server we are passing an integer here
    //This integer will used to specify the page number for the request ?page = requestcount
    //This method would return a JsonArrayRequest that will be added to the request queue

    private JsonArrayRequest getSuggestionDataFromServer(int requestCount) {

        //JsonArrayRequest of volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(sConfig.DATA_URL

                ,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Calling method parseData to parse the json response
                        parseSuggestionData(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        //Returning the request
        return jsonArrayRequest;
    }

    //This method will parse json data
    private void parseSuggestionData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            //Creating the superhero object
            sResults s_result = new sResults();
            JSONObject json = null;
            try{
                //Getting json
                json = array.getJSONObject(i);

                //Adding data to the bmresult object

                s_result.sets_Email(json.getString(sConfig.TAG_s_Email));
                s_result.sets_f1_post(json.getString(sConfig.TAG_s_f1_post));
                s_result.sets_f2_roommate(json.getString(sConfig.TAG_s_f2_roommate));
                s_result.sets_f3_roomo_rent(json.getString(sConfig.TAG_s_f3_roomo_rent));
                s_result.sets_Username(json.getString(sConfig.TAG_s_username));
                s_result.sets_gender(json.getString(sConfig.TAG_s_gender));
                s_result.sets_age(json.getString(sConfig.TAG_s_age));
                s_result.sets_PhoneNo(json.getString(sConfig.TAG_s_phonenumber));
                s_result.sets_occupation(json.getString(sConfig.TAG_s_occupation));
                s_result.sets_city(json.getString(sConfig.TAG_s_city));
                s_result.sets_country(json.getString(sConfig.TAG_s_country));
                s_result.sets_nationality(json.getString(sConfig.TAG_s_nationality));
                s_result.sets_m_stat(json.getString(sConfig.TAG_s_m_stat));
                s_result.sets_type_of_house(json.getString(sConfig.TAG_s_type_of_house));
                s_result.sets_no_of_rooms(json.getString(sConfig.TAG_s_no_of_rooms));
                s_result.sets_rentbudget(json.getString(sConfig.TAG_s_rentbudget));
                s_result.sets_owner_rent(json.getString(sConfig.TAG_s_owner_rent));
                s_result.sets_no_of_roommates(json.getString(sConfig.TAG_s_no_of_roommates));
                s_result.sets_cooking_skills(json.getString(sConfig.TAG_s_cooking_skills));
                s_result.sets_occupancy(json.getString(sConfig.TAG_s_occupancy));
                s_result.sets_owl(json.getString(sConfig.TAG_s_owl));
                s_result.sets_message(json.getString(sConfig.TAG_s_message));
                s_result.sets_alcoholic(json.getString(sConfig.TAG_s_alcoholic));
                s_result.sets_smoker(json.getString(sConfig.TAG_s_smoker));
                s_result.sets_address(json.getString(sConfig.TAG_s_address));
                s_result.sets_rent(json.getString(sConfig.TAG_s_rent));
                s_result.sets_tv(json.getString(sConfig.TAG_s_tv));
                s_result.sets_ac(json.getString(sConfig.TAG_s_ac));
                s_result.sets_fridge(json.getString(sConfig.TAG_s_fridge));
                s_result.sets_washing_machine(json.getString(sConfig.TAG_s_washing_machine));
                s_result.sets_internet(json.getString(sConfig.TAG_s_internet));
                s_result.sets_image(json.getString(sConfig.TAG_s_image));
                s_result.sets_geaser(json.getString(sConfig.TAG_s_geaser));
                s_result.sets_food(json.getString(sConfig.TAG_s_food));
                s_result.sets_p_image3(json.getString(sConfig.TAG_s_p_image3));
                s_result.sets_p_image2(json.getString(sConfig.TAG_s_p_image2));
                s_result.setPImage1(json.getString(sConfig.TAG_s_p_image1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Adding the superhero object to the list
            sResultsList.add(s_result);
        }

        //Notifying the adapter that data has been added or changed
        adapter.notifyDataSetChanged();
    }

    // Best Match Results Logic
    private void bmResults(String primary_key) {
        //BMR
        //Initializing Views

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //Initializing our superheroes list
        bmResultsList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        //Sending primary key
       // bm_sendPimaryKey(primary_key);

        //Calling method to get data to fetch data
        getData(primary_key);

        //Adding an scroll change listener to recyclerview
       //recyclerView.setOnScrollChangeListener((View.OnScrollChangeListener) getActivity().getApplicationContext());

        //initializing our adapter
        adapter = new bmCardAdapter(bmResultsList, getActivity());

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

        //Request to get json from server we are passing an integer here
        //This integer will used to specify the page number for the request ?page = requestcount
        //This method would return a JsonArrayRequest that will be added to the request queue

        private JsonArrayRequest getDataFromServer(String primary_key) {

            final String pri_keey = primary_key;
            JsonArrayRequest req = new JsonArrayRequest( bmConfig.DATA_URL,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            //Calling method parseData to parse the json response
                            parseData(response);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters to login url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", pri_keey);
                    return params;
                }

            };

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(req);


          //JsonArrayRequest of volley
          /*  JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(bmConfig.DATA_URL

                    ,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            //Calling method parseData to parse the json response
                            parseData(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                        }
                    });

            //Returning the request
            return jsonArrayRequest;*/
            return req;
        }

    private void bm_sendPimaryKey(final String primary_key) {
        // Tag used to cancel the request
        String tag_string_req = "req_primary_key";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_EMAIL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

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
    }

        //This method will get data from the web api
        private void getData(String primary_key) {
            //Adding the method to the queue by calling the method getDataFromServer
            requestQueue.add(getDataFromServer(primary_key));
           /* requestQueue.add(new JsonRequest<JSONArray>(Request.Method.POST, AppConfig.URL_BMRESULTS, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            //Calling method parseData to parse the json response
                            parseData(response);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", "we@e.com");

                    return params;
                }

                @Override
                protected Response<JSONArray> parseNetworkResponse(
                        NetworkResponse response) {
                    try {
                        String jsonString = new String(response.data,
                                HttpHeaderParser
                                        .parseCharset(response.headers));
                        return Response.success(new JSONArray(jsonString),
                                HttpHeaderParser
                                        .parseCacheHeaders(response));
                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));
                    } catch (JSONException je) {
                        return Response.error(new ParseError(je));
                    }
                }
            });
            //Incrementing the request counter
            requestCount++;*/
        }

        //This method will parse json data
        private void parseData(JSONArray array) {
            for (int i = 0; i < array.length(); i++) {
                //Creating the superhero object
                bmResults bm_result = new bmResults();
                JSONObject json = null;
                try {
                    //Getting json
                    json = array.getJSONObject(i);

                    //Adding data to the bmresult object

                    bm_result.setbm_Email(json.getString(bmConfig.TAG_BM_Email));
                    bm_result.setbm_f1_post(json.getString(bmConfig.TAG_BM_f1_post));
                    bm_result.setbm_f2_roommate(json.getString(bmConfig.TAG_BM_f2_roommate));
                    bm_result.setbm_f3_roomo_rent(json.getString(bmConfig.TAG_BM_f3_roomo_rent));
                    bm_result.setbm_Username(json.getString(bmConfig.TAG_BM_username));
                    bm_result.setBM_gender(json.getString(bmConfig.TAG_BM_gender));
                    bm_result.setBM_age(json.getString(bmConfig.TAG_BM_age));
                    bm_result.setBM_PhoneNo(json.getString(bmConfig.TAG_BM_phonenumber));
                    bm_result.setBM_occupation(json.getString(bmConfig.TAG_BM_occupation));
                    bm_result.setBM_city(json.getString(bmConfig.TAG_BM_city));
                    bm_result.setBM_country(json.getString(bmConfig.TAG_BM_country));
                    bm_result.setBM_nationality(json.getString(bmConfig.TAG_BM_nationality));
                    bm_result.setBM_m_stat(json.getString(bmConfig.TAG_BM_m_stat));
                    bm_result.setBM_type_of_house(json.getString(bmConfig.TAG_BM_type_of_house));
                    bm_result.setBM_no_of_rooms(json.getString(bmConfig.TAG_BM_no_of_rooms));
                    bm_result.setBM_rentbudget(json.getString(bmConfig.TAG_BM_rentbudget));
                    bm_result.setBM_owner_rent(json.getString(bmConfig.TAG_BM_owner_rent));
                    bm_result.setBM_no_of_roommates(json.getString(bmConfig.TAG_BM_no_of_roommates));
                    bm_result.setBM_cooking_skills(json.getString(bmConfig.TAG_BM_cooking_skills));
                    bm_result.setBM_occupancy(json.getString(bmConfig.TAG_BM_occupancy));
                    bm_result.setBM_owl(json.getString(bmConfig.TAG_BM_owl));
                    bm_result.setBM_message(json.getString(bmConfig.TAG_BM_message));
                    bm_result.setBM_alcoholic(json.getString(bmConfig.TAG_BM_alcoholic));
                    bm_result.setBM_smoker(json.getString(bmConfig.TAG_BM_smoker));
                    bm_result.setBM_address(json.getString(bmConfig.TAG_BM_address));
                    bm_result.setBM_rent(json.getString(bmConfig.TAG_BM_rent));
                    bm_result.setBM_tv(json.getString(bmConfig.TAG_BM_tv));
                    bm_result.setBM_ac(json.getString(bmConfig.TAG_BM_ac));
                    bm_result.setBM_fridge(json.getString(bmConfig.TAG_BM_fridge));
                    bm_result.setBM_washing_machine(json.getString(bmConfig.TAG_BM_washing_machine));
                    bm_result.setBM_internet(json.getString(bmConfig.TAG_BM_internet));
                    bm_result.setBM_image(json.getString(bmConfig.TAG_BM_image));
                    bm_result.setBM_geaser(json.getString(bmConfig.TAG_BM_geaser));
                    bm_result.setBM_food(json.getString(bmConfig.TAG_BM_food));
                    bm_result.setBM_p_image3(json.getString(bmConfig.TAG_BM_p_image3));
                    bm_result.setBM_p_image2(json.getString(bmConfig.TAG_BM_p_image2));
                    bm_result.setPImage1(json.getString(bmConfig.TAG_BM_p_image1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Adding the superhero object to the list
                bmResultsList.add(bm_result);
            }

            //Notifying the adapter that data has been added or changed
            adapter.notifyDataSetChanged();
        }

        //This method would check that the recyclerview scroll has reached the bottom or not
        private boolean isLastItemDisplaying(RecyclerView recyclerView) {
            if (recyclerView.getAdapter().getItemCount() != 0) {
                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                    return true;
            }
            return false;
        }


    // Your Post Logic
    private void getYourPost(final String primary_key) {
        final String pri_email = primary_key;
        // Tag used to cancel the request
        String tag_string_req = "req_yourPostData";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_SAMPLE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Your Post Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        JSONObject user = jObj.getJSONObject("user");

                        // Call all the data from server

                        // Control Variables
                        String f1_post = user.getString("f1_post");
                        String f2_roommate = user.getString("f2_roommate");
                        String f3_roomo_rent = user.getString("f3_roomo_rent");

                        //Remaining variables
                        String username = user.getString("username");
                        String email = user.getString("email");
                        String gender = user.getString("gender");
                        String age = user.getString("age");
                        String phonenumber = user.getString("phonenumber");
                        String occupation = user.getString("occupation");
                        String city = user.getString("city");
                        String country = user.getString("country");
                        String nationality = user.getString("nationality");
                        String m_stat = user.getString("m_stat");
                        String type_of_house = user.getString("toh");
                        String no_of_rooms = user.getString("no_of_rooms");
                        String rentbudget = user.getString("rentbudget");
                        String owner_rent = user.getString("o_rent");
                        String no_of_roommates = user.getString("nofrm");
                        String occupancy = user.getString("occupancy");
                        String cooking_skills = user.getString("cooking_skills");
                        String owl = user.getString("owl");
                        String message = user.getString("message");
                        String alcoholic = user.getString("alcoholic");
                        String smoker = user.getString("smoker");
                        String address = user.getString("address");
                        String rent = user.getString("rent");
                        String tv = user.getString("tv");
                        String ac = user.getString("ac");
                        String fridge = user.getString("fridge");
                        String washing_machine = user.getString("washing_machine");
                        String internet = user.getString("internet");
                        String geaser = user.getString("geaser");
                        //String image = user.getString("image");
                        String food = user.getString("food");
                        String p_imag1 = user.getString("p_image1");
                        String p_imag2 = user.getString("p_image2");
                        String p_imag3 = user.getString("p_image3");
                        String zero = "0";

                        if (f1_post.equals(zero)) {
                              // Your Post for Need a Room
                              // Pass Required Fragments
                              yourPostForm1(username, gender, age, phonenumber, city, country, occupation, nationality, no_of_rooms, rentbudget,
                                      type_of_house, m_stat, no_of_roommates, occupancy, food, owl, smoker, alcoholic, cooking_skills, message);
                          }

                        if (f2_roommate.equals(zero)){
                            // Your Post for Have a Room by Owner
                            // Pass Required Fragments
                            yourPostForm2(username, age, phonenumber, city, country, nationality, no_of_rooms, owner_rent,
                                    type_of_house, m_stat, no_of_roommates, occupancy, food, smoker, alcoholic, address, cooking_skills, message,
                                    ac, tv, fridge, washing_machine, geaser, internet, p_imag1, p_imag2, p_imag3);
                        }

                        if (f3_roomo_rent.equals(zero)){
                            // Your Post for Need a Roommate
                            // Pass Required Fragments
                            yourPostForm3(username, gender, age, phonenumber, city, country, occupation, nationality, no_of_rooms, rent,
                                    type_of_house, m_stat, no_of_roommates, occupancy, food, owl, smoker, alcoholic, address, cooking_skills, message,
                                    ac, tv, fridge, washing_machine, geaser, internet, p_imag1, p_imag2, p_imag3);
                        }


                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getActivity().getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Your Post Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", pri_email);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    void yourPostForm1(String username, String gender, String age, String phonenumber, String city,
                       String country, String occupation, String nationality, String no_of_rooms, String rentbudget,
                       String type_of_house, String m_stat, String no_of_roommates, String occupancy, String food, String owl,
                       String smoker, String alcoholic, String cooking_skills, String message) {

        // Displaying the user details on the screen
        // Display Lables
        NameLable.setText("Name: ");
        GenderLable.setText("Gender: ");
        PhnoLable.setText("Phone No: ");
        CityLable.setText("City: ");
        Type_of_house_Lable.setText("Type Of House: ");
        CountryLable.setText("Country");
        NationalityLable.setText("Nationality: ");
        AgeLable.setText("Age: ");
        m_statLable.setText("Martial Status:  ");
        NoOfRoomsLable.setText("No Of Rooms: ");
        NoOfRoomatesLable.setText("No Of Roommates: ");
        SmokingLable.setText("Smoking: ");
        DrinkingLable.setText("Drinking: ");
        FoodHabitsLable.setText("Food Habits: ");
        MessageLable.setText("Message: ");
        OccupancyLable.setText("Occupancy: ");
        URA_Lable.setText("You Are A?: ");
        OccupationLable.setText("Occupation: ");
        CookingSkillsLable.setText("Cooking Skills: ");

        // Display Data
        YourPostData.setText("Need A Room");
        NameData.setText(username);
        GenderData.setText(gender);
        PhnoData.setText(phonenumber);
        CityData.setText(city);
        Type_of_house_data.setText(type_of_house);
        CountryData.setText(country);
        NationalityData.setText(nationality);
        RentTypeLable.setText("Rent Budget");
        RentTypeData.setText(rentbudget);
        AgeData.setText(age);
        m_statData.setText(m_stat);
        NoOfRoomsData.setText(no_of_rooms);
        NoOfRoomatesData.setText(no_of_roommates);
        SmokingData.setText(smoker);
        DrinkingData.setText(alcoholic);
        FoodHabitsData.setText(food);
        MessageData.setText(message);
        OccupancyData.setText(occupancy);
        URA_Data.setText(owl);
        OccupationData.setText(occupation);
        CookingSkillsData.setText(cooking_skills);


    }

    void yourPostForm2(String username, String age, String phonenumber, String city, String country,
                       String nationality, String no_of_rooms, String owner_rent, String type_of_house, String m_stat,
                       String no_of_roommates, String occupancy, String food, String smoker, String alcoholic, String address,
                       String cooking_skills, String message, String ac, String tv, String fridge, String washing_machine,
                       String geaser, String internet, String p_imag1, String p_imag2, String p_imag3) {

        // Displaying the user details on the screen
        // Display Lables
        NameLable.setText("Name: ");
        GenderLable.setText("Gender: ");
        PhnoLable.setText("Phone No: ");
        CityLable.setText("City: ");
        Type_of_house_Lable.setText("Type Of House: ");
        CountryLable.setText("Country");
        NationalityLable.setText("Nationality: ");
        AgeLable.setText("Age: ");
        m_statLable.setText("Martial Status:  ");
        NoOfRoomsLable.setText("No Of Rooms: ");
        NoOfRoomatesLable.setText("No Of Roommates: ");
        SmokingLable.setText("Smoking: ");
        DrinkingLable.setText("Drinking: ");
        FoodHabitsLable.setText("Food Habits: ");
        MessageLable.setText("Message: ");
        OccupancyLable.setText("Occupancy: ");
        URA_Lable.setText("You Are A?: ");
        OccupationLable.setText("Occupation: ");
        CookingSkillsLable.setText("Cooking Skills: ");

        // Display Data
        YourPostData.setText("Need A Tenant");
        NameData.setText(username);
        PhnoData.setText(phonenumber);
        CityData.setText(city);
        Type_of_house_data.setText(type_of_house);
        CountryData.setText(country);
        NationalityData.setText(nationality);
        RentTypeLable.setText("Rent");
        RentTypeData.setText(owner_rent);
        AgeData.setText(age);
        m_statData.setText(m_stat);
        NoOfRoomsData.setText(no_of_rooms);
        NoOfRoomatesData.setText(no_of_roommates);
        SmokingData.setText(smoker);
        DrinkingData.setText(alcoholic);
        FoodHabitsData.setText(food);
        MessageData.setText(message);
        OccupancyData.setText(occupancy);
        CookingSkillsData.setText(cooking_skills);
        AddressLable.setText("Address:");
        AddressData.setText(address);
        decodeBase64_postImages(p_imag1, p_imag2, p_imag3);
        decodeBase64_Amenities(ac, tv, fridge, washing_machine, geaser, internet);
    }

    void yourPostForm3(String username, String gender, String age, String phonenumber, String city,
                       String country, String occupation, String nationality, String no_of_rooms, String rent, String type_of_house,
                       String m_stat, String no_of_roommates, String occupancy, String food, String owl, String smoker, String alcoholic,
                       String address, String cooking_skills, String message, String ac, String tv, String fridge, String washing_machine,
                       String geaser, String internet, String p_imag1, String p_imag2, String p_imag3) {

        // Displaying the user details on the screen
        // Display Lables
        NameLable.setText("Name: ");
        GenderLable.setText("Gender: ");
        PhnoLable.setText("Phone No: ");
        CityLable.setText("City: ");
        Type_of_house_Lable.setText("Type Of House: ");
        CountryLable.setText("Country");
        NationalityLable.setText("Nationality: ");
        AgeLable.setText("Age: ");
        m_statLable.setText("Martial Status:  ");
        NoOfRoomsLable.setText("No Of Rooms: ");
        NoOfRoomatesLable.setText("No Of Roommates: ");
        SmokingLable.setText("Smoking: ");
        DrinkingLable.setText("Drinking: ");
        FoodHabitsLable.setText("Food Habits: ");
        MessageLable.setText("Message: ");
        OccupancyLable.setText("Occupancy: ");
        URA_Lable.setText("You Are A?: ");
        OccupationLable.setText("Occupation: ");
        CookingSkillsLable.setText("Cooking Skills: ");

        // Display Data
        YourPostData.setText("Need A Tenant");
        NameData.setText(username);
        PhnoData.setText(phonenumber);
        CityData.setText(city);
        Type_of_house_data.setText(type_of_house);
        CountryData.setText(country);
        NationalityData.setText(nationality);
        RentTypeLable.setText("Rent");
        RentTypeData.setText(rent);
        GenderData.setText(gender);
        OccupationData.setText(occupation);
        URA_Data.setText(owl);
        AgeData.setText(age);
        m_statData.setText(m_stat);
        NoOfRoomsData.setText(no_of_rooms);
        NoOfRoomatesData.setText(no_of_roommates);
        SmokingData.setText(smoker);
        DrinkingData.setText(alcoholic);
        FoodHabitsData.setText(food);
        MessageData.setText(message);
        OccupancyData.setText(occupancy);
        CookingSkillsData.setText(cooking_skills);
        AddressLable.setText("Address:");
        AddressData.setText(address);
        decodeBase64_postImages(p_imag1, p_imag2, p_imag3);
        decodeBase64_Amenities(ac, tv, fridge, washing_machine, geaser, internet);

    }

    void decodeBase64_postImages(String p_imag1, String p_imag2, String p_imag3) {
        byte [] decodedBytes1 = Base64.decode(p_imag1, 0);
        byte [] decodedBytes2 = Base64.decode(p_imag2, 0);
        byte [] decodedBytes3 = Base64.decode(p_imag3, 0);
        Bitmap p_imageView1 = BitmapFactory.decodeByteArray(decodedBytes1, 0, decodedBytes1.length);
        Bitmap p_imageView2 = BitmapFactory.decodeByteArray(decodedBytes2, 0, decodedBytes2.length);
        Bitmap p_imageView3 = BitmapFactory.decodeByteArray(decodedBytes3, 0, decodedBytes3.length);
        p_ImageView1.setImageBitmap(p_imageView1);
        p_ImageView2.setImageBitmap(p_imageView2);
        p_ImageView3.setImageBitmap(p_imageView3);
    }

    private void decodeBase64_Amenities(String ac, String tv, String fridge, String washing_machine, String geaser, String internet) {


    }
}


