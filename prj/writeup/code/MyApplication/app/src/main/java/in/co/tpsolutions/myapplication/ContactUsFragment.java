package in.co.tpsolutions.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class ContactUsFragment extends Fragment {


    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    EditText pho;
    EditText sub;
    EditText message;
    TextView name;
    Button submit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Session manager
        session = new SessionManager(getActivity().getApplicationContext());

    }

    public ContactUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact_us, container, false);
        pho = (EditText) rootView.findViewById(R.id.phone_no_data);
        sub = (EditText) rootView.findViewById(R.id.subject_data);
        message = (EditText) rootView.findViewById(R.id.message_data);
        name = (TextView) rootView.findViewById(R.id.uname_data);
        submit = (Button) rootView.findViewById(R.id.btnSubmit);

        // SqLite database handler
        db = new SQLiteHandler(getActivity().getApplicationContext());

        // Session manager
        session = new SessionManager(getActivity().getApplicationContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phno1, msg1, sub1;
                phno1 = pho.getText().toString();
                msg1 = message.getText().toString();
                sub1 = sub.getText().toString();
                uploadData(phno1, sub1, msg1);
            }
        });
        return rootView;
    }

    private void uploadData(final String pno2, final String sub2, final String msg2){

        String tag_string_req = "req_sendData";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_CONTACT_US, new Response.Listener<String>() {

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
                params.put("phoneno", pno2);
                params.put("subject", sub2);
                params.put("message", msg2);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }



}
