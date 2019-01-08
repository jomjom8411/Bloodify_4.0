package com.example.dhoui.bloodify;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Historique_activity extends AppCompatActivity {


    String urladdress2 = "http://192.168.1.6/blood/displayposts.php";
    String[] name;
    String[] email,email1;
    String[] imagepath;
    ListView listView;
    BufferedInputStream is;
    String line=null;
    String result=null;
    String result2 = null;
    String[] test, test2, test3;
    String getId,getName;
    SessionManager sessionManager;
    String urladdress="http://192.168.1.6/blood/posts_that_a_user_wants_to_donate_on_but_not_confirmed.php?id_user=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_activity);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        getName=user.get(sessionManager.NAME);


        listView=(ListView)findViewById(R.id.lview);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();




        CustomListView2 customListView=new CustomListView2(this,name,email,imagepath);
        listView.setAdapter(customListView);









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
                        Intent intent0 = new Intent(Historique_activity.this, HomeActivity.class);
                        startActivity(intent0);
                        overridePendingTransition(0, 0);
                        break;


                    case R.id.nav_message:
                        Intent intent1 = new Intent(Historique_activity.this, MessageActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_tip:
                        Intent intent2 = new Intent(Historique_activity.this, TipActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_hospital:
                        Intent intent3 = new Intent(Historique_activity.this, HospitalActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(Historique_activity.this, ProfileActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
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

            URL url=new URL(urladdress+getId);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }
        catch (Exception ex){
            ex.printStackTrace
        ();
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



        //Connection





        try{



            URL url2=new URL(urladdress2);
            HttpURLConnection con=(HttpURLConnection)url2.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }
        catch (Exception ex){
            ex.printStackTrace
                    ();
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
            imagepath=new String[ja.length()];
            test=new String[ja.length()];
            test2=new String[ja.length()];
            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);

                test[i] = jo.getString("id_user");

                test2[i] = jo.getString("id_post");


                if (test[i].equals(getId)) {








                    try{
                        JSONArray ja2=new JSONArray(result2);
                        JSONObject jo2=null;


                        test3=new String[ja2.length()];

                        for(int j=0;j<=ja2.length();j++){
                            jo2=ja2.getJSONObject(j);
                            test3[j] = jo2.getString("Id");

                            if (test2[i].equals(test3[j])) {

                                email[i] = "Vous allez repondre à la demande : " + jo2.getString("slots") + " poches de " + jo2.getString("grpsanguin") + " à " + jo2.getString("region");



                                name[i] = getName;
                                //email[i]=jo.getString("id_post");
                                imagepath[i] = "http://192.168.1.6/blood/local/waiting.jpg";
                            }



                        }













                    }

                    catch (Exception ex)
                    {

                        ex.printStackTrace();
                    }


                                     }}





        }



        catch (Exception ex)
        {

            ex.printStackTrace();
        }


    }




















}
