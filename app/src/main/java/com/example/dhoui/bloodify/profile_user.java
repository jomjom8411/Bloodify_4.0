package com.example.dhoui.bloodify;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



import de.hdodenhof.circleimageview.CircleImageView;



public class profile_user extends AppCompatActivity {

    private TextView ok,tel,nom,prenom,email;
    private  ImageView profile,rank;
    String urladdress = "http://192.168.1.6/Blood/rankpoints.php";
    BufferedInputStream is,is2;
    String line = null;
    String result = null;
    private static final int REQUEST_CALL = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        ok = findViewById(R.id.textView);
        tel = findViewById(R.id.tel);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        email = findViewById(R.id.email);

        rank = findViewById(R.id.rank);
        ImageView call = findViewById(R.id.call);
        ImageView message = findViewById(R.id.message);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });


        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse("smsto:"+"55232623"));
                startActivity(intent2);
            }
        });

       profile=findViewById(R.id.profile_image);
         ok.setText("salut");
        Intent intent = getIntent();
         String value1 = intent.getStringExtra("position");

       // Toast.makeText(profile_user.this, "dans le profile : " + value1 , Toast.LENGTH_SHORT).show();

       // ok.setText(value1);















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





        try {
            JSONArray ja = new JSONArray(result);

            JSONObject jo = null;

         int test = Integer.valueOf(value1);

                jo = ja.getJSONObject(test);
                String nom2=jo.getString("nom");
            String prenom2=jo.getString("prenom");
            String te2=jo.getString("tel");
            String email2=jo.getString("Email");

           String points=jo.getString("points");
            nom.setText(nom2);

            prenom.setText(prenom2);









            tel.setText(te2);
            email.setText(email2);
            ok.setText(points);















            int tester =  (  Integer.valueOf(points));

            if (tester>= 10 && tester<30){

                rank.setImageDrawable(getResources().getDrawable(R.drawable.silver));

            }


            if (tester>= 30 && tester<50){

                rank.setImageDrawable(getResources().getDrawable(R.drawable.gold));

            }

            if (tester>= 50 && tester<70){

                rank.setImageDrawable(getResources().getDrawable(R.drawable.plat));

            }



            if (tester>= 70 && tester<100){

                rank.setImageDrawable(getResources().getDrawable(R.drawable.diam));

            }


            if (tester>=101){

                rank.setImageDrawable(getResources().getDrawable(R.drawable.chall));

            }





















        } catch (Exception ex) {

            ex.printStackTrace();
        }













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
                        Intent intent0 = new Intent(profile_user.this, HomeActivity.class);
                        startActivity(intent0);
                        overridePendingTransition(0, 0);
                        break;


                    case R.id.nav_message:
                        Intent intent1 = new Intent(profile_user.this, MessageActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_tip:
                        Intent intent2 = new Intent(profile_user.this, TipActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_hospital:
                        Intent intent3 = new Intent(profile_user.this, HospitalActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(profile_user.this, ProfileActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        break;
                }


                return false;
            }
        });










    }










    private void makePhoneCall() {



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





        try {
            JSONArray ja = new JSONArray(result);

            JSONObject jo = null;
            Intent intent = getIntent();
            String value1 = intent.getStringExtra("position");
            int test = Integer.valueOf(value1);

            jo = ja.getJSONObject(test);
            String nom2=jo.getString("nom");
            String prenom2=jo.getString("prenom");
            String te2=jo.getString("tel");
            String email2=jo.getString("Email");

            String points=jo.getString("points");





            String number = te2;
            if (number.trim().length() > 0) {

                if (ContextCompat.checkSelfPermission(profile_user.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(profile_user.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                } else {
                    String dial = "tel:" + number;
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }

            } else {
            }
































        } catch (Exception ex) {

            ex.printStackTrace();
        }










    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

