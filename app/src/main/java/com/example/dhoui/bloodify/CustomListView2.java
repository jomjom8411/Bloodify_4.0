package com.example.dhoui.bloodify;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Arrays;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.io.InputStream;
import java.util.Locale;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.Arrays;

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
import android.view.MenuItem;


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
import android.widget.TextView;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import android.widget.AdapterView;




/**
 * Created by jaiso on 13-02-2018.
 */

public class CustomListView2 extends ArrayAdapter<String>{

    private String[] profilename;
    private String[] email;
    private String[] imagepath;
    BufferedInputStream is,is2;
    String line = null;
    String result = null;
    String result2 = null;
    private Activity context;
    Bitmap bitmap;
    private static String URL_CONFIRM = "http://192.168.1.6/blood/confirmdonate.php?Id=";
    private static String URL_ADD_Donor = "http://192.168.1.6/blood/add_donor.php?Id=";
    String urladdress="http://192.168.1.6/blood/posts_that_a_user_wants_to_donate_on_but_not_confirmed.php?id_user=";
    String urladdress2="http://192.168.1.6/blood/getapostbyitsId.php?Id=";

    String getId;
    SessionManager sessionManager;



    public CustomListView2 (Activity context,String[] profilename,String[] email,String[] imagepath) {

        super(context, R.layout.layout2,profilename);
        this.context=context;
        this.profilename=profilename;
        this.email=email;
        this.imagepath=imagepath;



















    }

    @NonNull
    @Override

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){


        sessionManager = new SessionManager(getContext());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);




        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.layout2,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)r.getTag();

        }

        viewHolder.tvw1.setText(profilename[position]);
        viewHolder.tvw2.setText(email[position]);
        new GetImageFromURL(viewHolder.ivw).execute(imagepath[position]);
        viewHolder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirm(position);
                Toast.makeText(getContext(), "confirm donation" , Toast.LENGTH_SHORT).show();
                Intent registerIntent = new Intent(getContext(), HistoriqueConfirme.class);
                getContext().startActivity(registerIntent);

            }
        })
        ;







        return r;
    }

    class ViewHolder{

        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        Button confirm;

        ViewHolder(View v){
            tvw1=(TextView)v.findViewById(R.id.tvprofilename);
            tvw2=(TextView)v.findViewById(R.id.tvemail);
            ivw=(ImageView)v.findViewById(R.id.imageView);
            confirm =v.findViewById(R.id.confirm);
        }





















    }

    public class GetImageFromURL extends AsyncTask<String,Void,Bitmap>
    {

        ImageView imgView;
        public GetImageFromURL(ImageView imgv)
        {
            this.imgView=imgv;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay=url[0];
            bitmap=null;

            try{

                InputStream ist=new java.net.URL(urldisplay).openStream();
                bitmap= BitmapFactory.decodeStream(ist);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){

            super.onPostExecute(bitmap);
            imgView.setImageBitmap(bitmap);
        }
    }






























    private void confirm( final int position) {




        try {

            URL url = new URL(urladdress+getId);
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
            JSONArray ja2 = new JSONArray(result);

            JSONObject jo2 = null;
            //     testpost = new String[ja2.length()];
            for(int i=0;i<=ja2.length();i++) {

                jo2 = ja2.getJSONObject(position);
                String e = jo2.getString("id_post");
                String p = jo2.getString("Id");
                System.out.println("saslut jomjojjmmmmmmm" + position);








                try {

                    URL url2 = new URL(urladdress2+e);
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











                //add_+1donor



                StringRequest StringRequest2 = new StringRequest(Request.Method.POST, URL_ADD_Donor+e, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(getContext(), "donators numbers!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Erreur !" + e.toString(), Toast.LENGTH_SHORT).show();

                        }


                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "Erreur !" + error.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }) {


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();





                        try {
                            JSONArray ja3 = new JSONArray(result);

                            JSONObject jo3 = null;

                            for(int i=0;i<=ja3.length();i++) {

                                jo3 = ja3.getJSONObject(position);

                                String p = jo3.getString("id_post");





                                try {
                                    JSONArray ja33 = new JSONArray(result2);

                                    JSONObject jo33 = null;



                                    jo33 = ja33.getJSONObject(0);

                                    String number_donors = jo33.getString("donors_number");






























                                System.out.println("donarts jomjojjmmmmmmm" + p);
                                //  String c = jo3.getString("donors_number");
                                String tester;
                                params.put("Id", p);

                                tester =  Integer.toString(  Integer.valueOf(number_donors)+1);
                                params.put("donors_number",tester);



                                } catch (Exception ex) {

                                    ex.printStackTrace();
                                }



                            }
                        } catch (Exception ex) {

                            ex.printStackTrace();
                        }






                        return params;


                    }
                };

                RequestQueue requestQueue2 = Volley.newRequestQueue(getContext());
                requestQueue2.add(StringRequest2);





























//adddonorrrr



                StringRequest StringRequest = new StringRequest(Request.Method.POST, URL_CONFIRM+p, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(getContext(), "donation confirmée!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Erreur !" + e.toString(), Toast.LENGTH_SHORT).show();

                        }


                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "Erreur !" + error.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }) {


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();





                        try {
                            JSONArray ja2 = new JSONArray(result);

                            JSONObject jo2 = null;
                            //     testpost = new String[ja2.length()];
                            for(int i=0;i<=ja2.length();i++) {

                                jo2 = ja2.getJSONObject(position);
                                String e = jo2.getString("id_post");
                                String p = jo2.getString("Id");
                                System.out.println("saslut jomjojjmmmmmmm" + position);


                                params.put("Id", p);
                                params.put("id_user", getId);
                                params.put("id_post", e);
                                params.put("etat", "1");
                            }
                        } catch (Exception ex) {

                            ex.printStackTrace();
                        }


                        return params;


                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(StringRequest);






            }
} catch (Exception ex) {

        ex.printStackTrace();
        }


















        }













}