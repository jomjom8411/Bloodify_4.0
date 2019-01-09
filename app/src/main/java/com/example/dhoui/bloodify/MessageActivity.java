package com.example.dhoui.bloodify;



import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.util.ArrayList;

import android.support.v7.widget.SearchView;
import android.text.TextUtils;

import com.android.volley.toolbox.StringRequest;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import android.os.StrictMode;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;

import com.github.clans.fab.FloatingActionButton;


public class MessageActivity extends AppCompatActivity {



    private  SharedPreference prefconf ;
    String urladdress = "http://192.168.1.6/Blood/showmessageforuser.php?email_receiver=";
    String urladdress2 = "http://192.168.1.6/Blood/profile_id.php?Id=";
    String[] name={};
    String[] salut;
    String[] email={},number={};
    String[] imagepath={};
    ListView listView;
    BufferedInputStream is,is2,is3;
    String line = null;
    String result = null;
    String result2 = null;
    String result3 = null;
    String[] test, test2, test3;
    String getId;
    SessionManager sessionManager;
    private Spinner region;
    private Spinner grpsanguin;
    private Spinner slots;
    private ImageView mHeartRed;
    private ImageView  mHeartWhite;
    private Activity context;
    private static String URL_DONATE = "http://192.168.1.6/blood/donate2.php";
    private GestureDetector mGestureDetector;
    private Heart mHeart;
    private static String URL_MESSAGE = "http://192.168.1.6/blood/sendmessage.php";



    String[] title;
    String[] description;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();
    private Button donate;
    private static String URL_POST = "http://192.168.1.6/blood/addpost.php";
    private static final String TAG = "HomeActivity ";
    FloatingActionButton floatingActionButton1,floatingActionButton2;
    CustomListViewMessages adapter;
    CustomListViewMessages customListView;
    private EditText messagetosend;
    private EditText emailtosendto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {





        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_message);
        prefconf = new SharedPreference(getApplicationContext());

        messagetosend = (EditText) findViewById(R.id.messagetext);
        emailtosendto = (EditText) findViewById(R.id.emailtext);


        String messagetext = messagetosend.getText().toString();
        String emailmmessage =emailtosendto.getText().toString();







      Button   btnmess = findViewById(R.id.btnsend);


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.grpsanguin1,
                android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();







        mHeart = new Heart(mHeartWhite, mHeartRed);


        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

        listView = (ListView) findViewById(R.id.lview);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListViewMessages customListView = new CustomListViewMessages(this, name, email, imagepath, number);
        listView.setAdapter(customListView);





        btnmess.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               message();
               Intent registerIntent = new Intent(MessageActivity.this, MessageActivity.class);
               MessageActivity.this.startActivity(registerIntent);

           }
       });










        donate = findViewById(R.id.donate);
        //  ok = findViewById(R.id.menu_item1);




        floatingActionButton1 = (FloatingActionButton)
                findViewById(R.id.menu_item1);

        floatingActionButton2 = (FloatingActionButton)
                findViewById(R.id.menu_item2);

















        //posts vieeeeeeew




































       /* Intent intent = getIntent();
        String name = intent.getStringExtra("nom");
        String username = intent.getStringExtra("Email");
        int age = intent.getIntExtra("age", -1);

        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etAge = (EditText) findViewById(R.id.etAge);

        // Display user details
        String message = name + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        etAge.setText(age + "");*/











        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent intent0 = new Intent(MessageActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        overridePendingTransition(0, 0);
                        break;


                    case R.id.nav_message:
                        Intent intent1 = new Intent(MessageActivity.this, MessageActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_tip:
                        Intent intent2 = new Intent(MessageActivity.this, TipActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_hospital:
                        Intent intent3 = new Intent(MessageActivity.this, HospitalActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(MessageActivity.this, ProfileActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        break;
                }


                return false;
            }
        });













    }














   /* @Override
    public boolean onCreateOptionsMenu(Menu menu)   {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            public boolean onQueryTextSubmit(String s) {
                return false;
            }


            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                   // customListView.filter("");
                    //listView.clearTextFilter();
                } else {
                  //  customListView.filter(s);
                }
                return true;
            }
        });
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            //do your functionality here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }   */







    private void collectData() {



        try {

            URL url2 = new URL(urladdress2+getId);
            HttpURLConnection con = (HttpURLConnection) url2.openConnection();
            con.setRequestMethod("GET");
            is2 = new BufferedInputStream(con.getInputStream());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //content
        try {
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
            StringBuilder sb2 = new StringBuilder();
            while ((line = br2.readLine()) != null) {
                sb2.append(line + "\n");
            }
            is2.close();
            result2 = sb2.toString();

        } catch (Exception ex) {
            ex.printStackTrace();

        }






        try {
            JSONArray ja2 = new JSONArray(result2);

            JSONObject jo2 = null;
            jo2 = ja2.getJSONObject(0);

            String email_user=jo2.getString("Email");






        //Connection
        try {

            URL url = new URL(urladdress+email_user);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //content
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace();

        }


        //ok


        //Connection


















//JSON
        try {
            JSONArray ja = new JSONArray(result);

            JSONObject jo = null;
            name = new String[ja.length()];
            email = new String[ja.length()];
            number = new String[ja.length()];
            test = new String[ja.length()];
            imagepath = new String[ja.length()];


            for (  int i = 0; i <= ja.length(); i++) {

                jo = ja.getJSONObject(i);


                test[i]= jo.getString("id_user_sending") ;
                email[i]= jo.getString("message");


            try {

                URL url3 = new URL(urladdress2+test[i]);
                HttpURLConnection con = (HttpURLConnection) url3.openConnection();
                con.setRequestMethod("GET");
                is3 = new BufferedInputStream(con.getInputStream());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //content
            try {
                BufferedReader br3 = new BufferedReader(new InputStreamReader(is3));
                StringBuilder sb3 = new StringBuilder();
                while ((line = br3.readLine()) != null) {
                    sb3.append(line + "\n");
                }
                is3.close();
                result3 = sb3.toString();

            } catch (Exception ex) {
                ex.printStackTrace();

            }



                try {
                    JSONArray ja3 = new JSONArray(result3);

                    JSONObject jo3 = null;


                    jo3 = ja3.getJSONObject(0);

                    String name2=jo3.getString("nom")+" "+jo3.getString("prenom")+" vous a envoyé un message : ";
                    String test2=jo3.getString("photo");
                    String test3=jo3.getString("Email");
                    imagepath[i]=test2;
                    name[i]=name2;
                    number[i]="date-time: "+jo.getString("date") + "                                                      "+" à partir de L'Email : "+test3;
                } catch (Exception ex) {

                    ex.printStackTrace();
                }

























            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }



        } catch (Exception ex) {

            ex.printStackTrace();
        }











    }







































    private  void message(){


      final String messageget = this.messagetosend.getText().toString();
     final String emailget = this.emailtosendto.getText().toString();




    StringRequest StringRequest = new StringRequest(Request.Method.POST, URL_MESSAGE, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String success = jsonObject.getString("success");

                if (success.equals("1")) {

                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MessageActivity.this, "Erreur !" + e.toString(), Toast.LENGTH_SHORT).show();

            }


        }
    },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MessageActivity.this, "Erreur !" + error.toString(), Toast.LENGTH_SHORT).show();

                }
            }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();


            params.put("email_receiver", emailget);
            params.put("id_user_sending", getId);
            params.put("message", messageget);

            return params;


        }
    };

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(StringRequest);
























}





}




