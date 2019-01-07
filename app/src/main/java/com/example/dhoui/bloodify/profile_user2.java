package com.example.dhoui.bloodify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.widget.Toast;


public class profile_user2 extends AppCompatActivity {

    private TextView ok,tel,nom,prenom,email;
    private  ImageView profile,rank;
    String urladdress = "http://192.168.1.6/Blood/rankpoints.php";
    BufferedInputStream is,is2;
    String line = null;
    String result = null;


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


       profile=findViewById(R.id.profile_image);
         ok.setText("salut");


        //String value2 = intent.getStringExtra("position2");

       // Toast.makeText(profile_user2.this, "dans le profile 2 : " + value1 , Toast.LENGTH_SHORT).show();

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
            Intent intent = getIntent();
            String value1 = intent.getStringExtra("position");
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

            if (tester> 10 && tester<30){

                rank.setImageDrawable(getResources().getDrawable(R.drawable.silver));

            }


            if (tester> 30 && tester<50){

                rank.setImageDrawable(getResources().getDrawable(R.drawable.gold));

            }

            if (tester> 50 && tester<70){

                rank.setImageDrawable(getResources().getDrawable(R.drawable.plat));

            }



            if (tester> 70 && tester<100){

                rank.setImageDrawable(getResources().getDrawable(R.drawable.diam));

            }


            if (tester>100){

                rank.setImageDrawable(getResources().getDrawable(R.drawable.chall));

            }





















        } catch (Exception ex) {

            ex.printStackTrace();
        }

















    }




}
