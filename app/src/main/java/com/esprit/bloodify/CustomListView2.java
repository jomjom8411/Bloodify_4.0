package com.esprit.bloodify;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;

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

import android.content.Intent;


import java.io.BufferedInputStream;


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

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;


import static com.esprit.bloodify.App.CHANNEL_1_ID;



/**
 * Created by jaiso on 13-02-2018.
 */

public class CustomListView2 extends ArrayAdapter<String>{
    private NotificationManagerCompat notificationManager;
    private String[] profilename;
    private String[] email;
    private String[] imagepath;
    BufferedInputStream is,is2,is4,is3,is23;
    String line = null;
    String result = null;
    String result2,result4 = null;
    String result3 = null;
    private Activity context;
    Bitmap bitmap;
    private static String URL_CONFIRM = "http://41.226.11.252:1180/bloodify/confirmdonate.php?Id=";
    private static String URL_ADD_Donor = "http://41.226.11.252:1180/bloodify/add_donor.php?Id=";
    String urladdress="http://41.226.11.252:1180/bloodify/posts_that_a_user_wants_to_donate_on_but_not_confirmed.php?id_user=";
    String urladdress2="http://41.226.11.252:1180/bloodify/getapostbyitsId.php?Id=";
    String urladdress3="http://41.226.11.252:1180/bloodify/showprofile.php?Id=";
    private static String URL_ADD_point = "http://41.226.11.252:1180/bloodify/add_points.php?Id=";

    String getId;
    SessionManager sessionManager;



    public CustomListView2 (Activity context,String[] profilename,String[] email,String[] imagepath) {

        super(context, R.layout.layout2,profilename);
        this.context=context;
        this.profilename=profilename;
        this.email=email;
        this.imagepath=imagepath;



















    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){


        sessionManager = new SessionManager(getContext());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);



        View r=convertView;
        ViewHolder viewHolder;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.layout2,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else viewHolder = (ViewHolder) r.getTag();
        notificationManager = NotificationManagerCompat.from(getContext());
        viewHolder.tvw1.setText(profilename[position]);
        viewHolder.tvw2.setText(email[position]);
        new GetImageFromURL(viewHolder.ivw).execute(imagepath[position]);

        final ViewHolder finalViewHolder = viewHolder;


        viewHolder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points();
                confirm(position);

                Notification notification = new NotificationCompat.Builder(getContext(), CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("toutes nos félicitations")
                        .setContentText("Vous avez reçu 1 point de plus dans votre score :) ")

                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();

                notificationManager.notify(1, notification);
                Toast.makeText(getContext(), "Donation confirmée" , Toast.LENGTH_SHORT).show();
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
            tvw1=v.findViewById(R.id.tvprofilename);
            tvw2=v.findViewById(R.id.tvemail);
            ivw=v.findViewById(R.id.imageView);
            confirm =v.findViewById(R.id.confirm);
            confirm.setVisibility(View.VISIBLE);
        }





















    }

    @SuppressLint("StaticFieldLeak")
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
                sb.append(line).append("\n");
            }
            is.close();
            result = sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace();

        }






        try {
            JSONArray ja2 = new JSONArray(result);

            JSONObject jo2 ;
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
                        sb2.append(line).append("\n");
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
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();





                        try {
                            JSONArray ja3 = new JSONArray(result);

                            JSONObject jo3;

                            for(int i=0;i<=ja3.length();i++) {

                                jo3 = ja3.getJSONObject(position);

                                String p = jo3.getString("id_post");





                                try {
                                    JSONArray ja33 = new JSONArray(result2);

                                    JSONObject jo33 ;



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
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();





                        try {
                            JSONArray ja2 = new JSONArray(result);

                            JSONObject jo2  ;
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







private  void  points() {





    try {

        URL url23 = new URL(urladdress3+getId);
        HttpURLConnection con = (HttpURLConnection) url23.openConnection();
        con.setRequestMethod("GET");
        is23 = new BufferedInputStream(con.getInputStream());

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    //content
    try {
        BufferedReader br23 = new BufferedReader(new InputStreamReader(is23));
        StringBuilder sb23 = new StringBuilder();
        while ((line = br23.readLine()) != null) {
            sb23.append(line).append("\n");
        }
        is23.close();
        result3 = sb23.toString();

    } catch (Exception ex) {
        ex.printStackTrace();

    }

















    StringRequest stringRequest3 = new StringRequest(Request.Method.POST, URL_ADD_point+getId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {

                        System.out.println(response);
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");

                        if (success.equals("1")){
                            Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();

                        }

                    } catch (JSONException e) {

                        e.printStackTrace();

                        Toast.makeText(getContext(), "Error "+ e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(), "Error "+ error.toString(), Toast.LENGTH_SHORT).show();
                }
            })
    {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();




            try {
                JSONArray ja334 = new JSONArray(result3);

                JSONObject jo334 ;



                jo334 = ja334.getJSONObject(0);

                String points = jo334.getString("points");



                System.out.println("donarts jomjom : " + points);
                String tester;
                tester =  Integer.toString(  Integer.valueOf(points)+1);


                params.put("Id", getId);
                params.put("points", tester);




            } catch (Exception ex) {

                ex.printStackTrace();
            }
























            return params;
        }
    };

    RequestQueue requestQueue3 = Volley.newRequestQueue(getContext());
    requestQueue3.add(stringRequest3);

}





}
