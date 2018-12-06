package com.example.dhoui.bloodify;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import java.util.Calendar;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeActivity extends AppCompatActivity {

    String urladdress="http://192.168.1.2/displayposts.php";
    String urladdress2="http://192.168.1.2/displayprofilebyid.php";
    String[] name;
    String[] salut;
    String[] email;
    String[] imagepath;
    ListView listView;
    BufferedInputStream is;
    String line=null;
    String result=null;
    String result2=null;
    String[] test,test2,test3;

    private Spinner region ;
   private Spinner grpsanguin ;
    private Spinner slots ;


    ListViewAdapter adapter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();
    private Button pub ;
    private static String URL_POST = "http://192.168.1.2/blood/addpost.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        grpsanguin = findViewById(R.id.grpsanguin_Text);
        region = findViewById(R.id.region_Text);
        slots= findViewById(R.id.number_Text);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.grpsanguin1,
                android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        listView=(ListView)findViewById(R.id.lview);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListView customListView=new CustomListView(this,name,email,imagepath);
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




        pub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //post();
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

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:

                        break;

                    case R.id.nav_message:
                        Intent intent1 = new Intent(HomeActivity.this, MessageActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.nav_tip:
                        Intent intent2 = new Intent(HomeActivity.this, TipActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.nav_hospital:
                        Intent intent3 = new Intent(HomeActivity.this, HospitalActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0,0);
                        break;
                }


                return false;
            }
        });
    }















    private void collectData()
    {
//Connection
        try{

            URL url=new URL(urladdress);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //content
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }


        //ok


        try{

            URL url2=new URL(urladdress2);
            HttpURLConnection con=(HttpURLConnection)url2.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //content
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            is.close();
            result2=sb.toString();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }

















//JSON
        try{
            JSONArray ja=new JSONArray(result);

            JSONObject jo=null;
            name=new String[ja.length()];
            email=new String[ja.length()];
            test=new String[ja.length()];

            imagepath=new String[ja.length()];

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);




                test[i]=jo.getString("id_user");



                try{
                    JSONArray ja2=new JSONArray(result2);

                    JSONObject jo2=null;
                    test2=new String[ja2.length()];
                    test3=new String[ja2.length()];

                    for(int j=0;j<=ja2.length();j++){
                        jo2=ja2.getJSONObject(j);


                        test2[j]=jo2.getString("Id");

                        if (test2[j].equals(test[i]))
                        {

                            name[i]=jo2.getString("nom")+" " +jo2.getString("prenom") ;;
                            imagepath[i]=jo.getString("image");

                        }



                    }
                }
                catch (Exception ex)
                {

                    ex.printStackTrace();
                }






                email[i]= "je cherche "+jo.getString("slots")+" slots de "+jo.getString("grpsanguin")+" à "+jo.getString("region");

            }
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }


    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            public boolean onQueryTextSubmit(String s) {
                return false;
            }


            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.action_settings){
            //do your functionality here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }










}
