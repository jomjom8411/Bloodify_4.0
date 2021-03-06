package com.esprit.bloodify;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.util.ArrayList;

import com.android.volley.toolbox.StringRequest;

import android.app.AlertDialog;
import android.widget.Toast;

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














public class HomeActivity extends AppCompatActivity {



    private  SharedPreference prefconf ;
    String urladdress = "http://41.226.11.252:1180/bloodify/displaypostshomepage_notfinished.php";
    String urladdress2 = "http://41.226.11.252:1180/bloodify/displayprofilebyid.php";
    String[] name = {};
    String[] salut;
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
    private Button pub,ok;
    private Button donate;
    private static String URL_POST = "http://41.226.11.252:1180/bloodify/addpost.php";
    private static final String TAG = "HomeActivity ";
    FloatingActionButton floatingActionButton1,floatingActionButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {









        super.onCreate(savedInstanceState);



        if(!isConnected(HomeActivity.this)) setContentView(R.layout.activity_home);
        else {

            setContentView(R.layout.activity_home);
        }



      //  setContentView(R.layout.activity_home);
        prefconf = new SharedPreference(getApplicationContext());


        grpsanguin = findViewById(R.id.grpsanguin_Text);
        region = findViewById(R.id.region_Text);
        slots = findViewById(R.id.number_Text);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.grpsanguin1,
                android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();







        mHeart = new Heart(mHeartWhite, mHeartRed);


        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);

        listView =  findViewById(R.id.lview);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListView customListView = new CustomListView(this, name, email, imagepath, number);
        listView.setAdapter(customListView);









        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Regions,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.numbers,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        grpsanguin.setAdapter(adapter3);
        grpsanguin.getSelectedItem().toString();

        region.setAdapter(adapter1);
        region.getSelectedItem().toString();

        slots.setAdapter(adapter4);
        slots.getSelectedItem().toString();

        pub = findViewById(R.id.btnPostdemande);
        donate = findViewById(R.id.donate);
      //  ok = findViewById(R.id.menu_item1);




        floatingActionButton1 =
                findViewById(R.id.menu_item1);

        floatingActionButton2 =
                findViewById(R.id.menu_item2);



        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent registerIntent = new Intent(HomeActivity.this, classement.class);
                HomeActivity.this.startActivity(registerIntent);
            }
        });



        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent registerIntent = new Intent(HomeActivity.this, postfini.class);
                HomeActivity.this.startActivity(registerIntent);
            }
        });



        pub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                post();
                Intent registerIntent = new Intent(HomeActivity.this, HomeActivity.class);
                HomeActivity.this.startActivity(registerIntent);
            }
        });





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

                        break;

                    case R.id.nav_message:
                        Intent intent1 = new Intent(HomeActivity.this, MessageActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_tip:
                        Intent intent2 = new Intent(HomeActivity.this, TipActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_hospital:
                        Intent intent3 = new Intent(HomeActivity.this, HospitalActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(HomeActivity.this, ProfileActivity.class);
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
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
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
                            email[i] = "je  cherche  " + jo.getString("slots") + "  poches de    " + jo.getString("grpsanguin") + " à " + jo.getString("region");
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








    private void post() {


        final String region = this.region.getSelectedItem().toString().trim();
        final String grpsanguin = this.grpsanguin.getSelectedItem().toString().trim();
        final String slots = this.slots.getSelectedItem().toString().trim();


        StringRequest StringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(HomeActivity.this, "Erreur !" + e.toString(), Toast.LENGTH_SHORT).show();

                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeActivity.this, "Erreur !" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("id_user", getId);
                params.put("region", region);
                params.put("grpsanguin", grpsanguin);
                params.put("slots", slots);

                return params;


            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(StringRequest);


    }





























    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
        } else
            return false;
    }




    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }








}




