package com.esprit.bloodify;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.util.ArrayList;

import com.android.volley.toolbox.StringRequest;

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














public class postfini extends AppCompatActivity {



    private  SharedPreference prefconf ;
    String urladdress = "http://41.226.11.252:1180/bloodify/finishedposts.php";
    String urladdress2 = "http://41.226.11.252:1180/bloodify/displayprofilebyid.php";
    String[] name={};
    String[] salut={};
    String[] email={},number={};
    String[] imagepath={};
    ListView listView;
    BufferedInputStream is,is2;
    String line = null;
    String result = null;
    String result2 = null;
    String[] test, test2, test3;
    String getId;
    SessionManager sessionManager;
    private Spinner region;
    private Spinner grpsanguin;
    private Spinner slots;
    private ImageView mHeartRed;
    private ImageView  mHeartWhite;
    private Activity context;
    private static String URL_DONATE = "http://41.226.11.252:1180/bloodify/donate2.php";
    private GestureDetector mGestureDetector;
    private Heart mHeart;



    String[] title;
    String[] description;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<>();
    private Button donate;
    private static String URL_POST = "http://41.226.11.252:1180/bloodify/addpost.php";
    private static final String TAG = "HomeActivity ";
    FloatingActionButton floatingActionButton1,floatingActionButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




















        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_postfini);
        prefconf = new SharedPreference(getApplicationContext());



        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.grpsanguin1,
                android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();







        mHeart = new Heart(mHeartWhite, mHeartRed);


        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);

        listView = findViewById(R.id.lview);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListViewPostfini customListView = new CustomListViewPostfini(this, name, email, imagepath, number);
        listView.setAdapter(customListView);
















        donate = findViewById(R.id.donate);
        //  ok = findViewById(R.id.menu_item1);




















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








        BottomNavigationView bottomNavigationView =  findViewById(R.id.bottomNavView_Bar);
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent intent0 = new Intent(postfini.this, HomeActivity.class);
                        startActivity(intent0);
                        overridePendingTransition(0, 0);
                        break;


                    case R.id.nav_message:
                        Intent intent1 = new Intent(postfini.this, MessageActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_tip:
                        Intent intent2 = new Intent(postfini.this, TipActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_hospital:
                        Intent intent3 = new Intent(postfini.this, HospitalActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(postfini.this, ProfileActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        break;
                }


                return false;
            }
        });














    }















    private void collectData() {
//Connection
        try {

            URL url = new URL(urladdress);
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
                sb.append(line).append("\n");
            }
            is.close();
            result = sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace();

        }


        //ok


        try {

            URL url2 = new URL(urladdress2);
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
                sb2.append(line).append("\n");
            }
            is2.close();
            result2 = sb2.toString();

        } catch (Exception ex) {
            ex.printStackTrace();

        }


//JSON
        try {
            JSONArray ja = new JSONArray(result);

            JSONObject jo ;
            name = new String[ja.length()];
            email = new String[ja.length()];
            number = new String[ja.length()];
            test = new String[ja.length()];
            imagepath = new String[ja.length()];


            for (  int i = 0; i <= ja.length(); i++) {
                jo = ja.getJSONObject(i);


                test[i] = jo.getString("id_user");


                try {
                    JSONArray ja2 = new JSONArray(result2);

                    JSONObject jo2 ;

                    test2 = new String[ja2.length()];


                    for (int j = 0; j <= ja2.length(); j++) {
                        jo2 = ja2.getJSONObject(j);


                        test2[j] = jo2.getString("Id");

                        if (test2[j].equals(test[i])) {

                            name[i] = jo2.getString("nom") + " " + jo2.getString("prenom");


                            imagepath[i] = jo2.getString("photo");
                            email[i] = "a cherché   " + jo.getString("slots") + "  poches de    " + jo.getString("grpsanguin") + " à " + jo.getString("region");
                            number[i]=jo.getString("donors_number");



                        }


                    }
                } catch (Exception ex) {

                    ex.printStackTrace();
                }












            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }











    }

































    private void donate() {




        StringRequest StringRequest = new StringRequest(Request.Method.POST, URL_DONATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");


                } catch (JSONException e) {
                    e.printStackTrace();
                    //    Toast.makeText(getContext(), "Erreur !" + e.toString(), Toast.LENGTH_SHORT).show();

                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(getContext(), "Erreur !" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("id_user", getId);
                params.put("id_post", getId);
                params.put("etat", getId);


                return params;


            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(StringRequest);


    }










}




