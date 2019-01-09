package com.example.dhoui.bloodify;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;
import java.io.BufferedInputStream;

import org.json.JSONObject;

import java.util.HashMap;


import android.os.StrictMode;




import org.json.JSONArray;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;

import com.github.clans.fab.FloatingActionButton;














public class classement extends AppCompatActivity {



    private  SharedPreference prefconf ;
    String urladdress = "http://192.168.1.6/Blood/rankpoints.php";
    String urladdress2 = "http://192.168.1.6/Blood/displayprofilebyid.php";
    String[] name;
    String[] salut;
    String[] email,number;
    String[] imagepath;
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
    private static String URL_DONATE = "http://192.168.1.6/blood/donate2.php";
    private GestureDetector mGestureDetector;
    private Heart mHeart;



    String[] title;
    String[] description;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();
    private Button donate;
    private static String URL_POST = "http://192.168.1.6/blood/addpost.php";
    private static final String TAG = "HomeActivity ";
    FloatingActionButton floatingActionButton1,floatingActionButton2;
    CustomListViewPoints adapter;
    CustomListViewPoints customListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        prefconf = new SharedPreference(getApplicationContext());
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();


        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

        listView = (ListView) findViewById(R.id.lview);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListViewPoints customListView = new CustomListViewPoints(this, name, email, imagepath, number);
        listView.setAdapter(customListView);














        donate = findViewById(R.id.donate);
        //  ok = findViewById(R.id.menu_item1);




        floatingActionButton1 = (FloatingActionButton)
                findViewById(R.id.menu_item1);

        floatingActionButton2 = (FloatingActionButton)
                findViewById(R.id.menu_item2);

















        //posts vieeeeeeew














































        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent intent0 = new Intent(classement.this, HomeActivity.class);
                        startActivity(intent0);
                        overridePendingTransition(0, 0);
                        break;


                    case R.id.nav_message:
                        Intent intent1 = new Intent(classement.this, MessageActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_tip:
                        Intent intent2 = new Intent(classement.this, TipActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_hospital:
                        Intent intent3 = new Intent(classement.this, HospitalActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(classement.this, ProfileActivity.class);
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
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace();

        }


        //ok





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


                name[i]= jo.getString("nom") + " " +jo.getString("prenom");
                imagepath[i] = jo.getString("photo");
                email[i]= "Rang : " + (i+1);
                number[i]=jo.getString("points");















            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }














    }














































}




