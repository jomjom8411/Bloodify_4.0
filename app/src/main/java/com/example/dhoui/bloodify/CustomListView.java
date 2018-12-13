package com.example.dhoui.bloodify;
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



    public class CustomListView extends ArrayAdapter<String>  {

        private String[] profilename;
        private String[] email;
        private String[] imagepath;
        private Activity context;
        Bitmap bitmap;
        private ImageView mHeartRed;
        private ImageView mHeartWhite;
    String getId;
    String pos;
    SessionManager sessionManager;
    BufferedInputStream is;
    String line = null;
    String result = null;
    String[] testpost;
    String ok ;

        private GestureDetector mGestureDetector;
        private Heart mHeart;
        private Button donate;
        private static String URL_DONATE = "http://192.168.1.3/blood/donate2.php";
        String urladdress = "http://192.168.1.3/displayposts.php";

        public CustomListView(Activity context, final String[] profilename, String[] email, String[] imagepath) {
            super(context, R.layout.layout, profilename);
            this.context = context;
            this.profilename = profilename;
            this.email = email;
            this.imagepath = imagepath;









        }

        @NonNull
        @Override

        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            sessionManager = new SessionManager(getContext());
            sessionManager.checkLogin();
            HashMap<String, String> user = sessionManager.getUserDetail();
            getId = user.get(sessionManager.ID);



            View r = convertView;
            ViewHolder viewHolder = null;
            if (r == null) {
                LayoutInflater layoutInflater = context.getLayoutInflater();
                r = layoutInflater.inflate(R.layout.layout, null, true);
                viewHolder = new ViewHolder(r);


                r.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) r.getTag();

            }

            viewHolder.tvw1.setText(profilename[position]);
            viewHolder.tvw2.setText(email[position]);


            new GetImageFromURL(viewHolder.ivw).execute(imagepath[position]);
            viewHolder.donate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                       donate(position);

                    Toast.makeText(getContext(), "u have donated" , Toast.LENGTH_SHORT).show();                    System.out.println(Arrays.toString(email));
                }
            })
            ;


            return r;
        }

        class ViewHolder {

            TextView tvw1;
            TextView tvw2;
            ImageView ivw;
            ImageView mHeartRed;
            ImageView mHeartWhite;
            Button donate;


            ViewHolder(View v) {
                tvw1 = (TextView) v.findViewById(R.id.tvprofilename);
                tvw2 = (TextView) v.findViewById(R.id.tvemail);
                ivw = (ImageView) v.findViewById(R.id.imageView);
                donate =v.findViewById(R.id.donate);




            }

        }

        public class GetImageFromURL extends AsyncTask<String, Void, Bitmap> {

            ImageView imgView;

            public GetImageFromURL(ImageView imgv) {
                this.imgView = imgv;
            }

            @Override
            protected Bitmap doInBackground(String... url) {
                String urldisplay = url[0];
                bitmap = null;

                try {

                    InputStream ist = new java.net.URL(urldisplay).openStream();
                    bitmap = BitmapFactory.decodeStream(ist);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {

                super.onPostExecute(bitmap);
                imgView.setImageBitmap(bitmap);
            }
        }






    private void donate( final int position) {




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
            JSONArray ja2 = new JSONArray(result);

            JSONObject jo2 = null;
            testpost = new String[ja2.length()];


            testpost[position] = jo2.getString("id_post");



        } catch (Exception ex) {

            ex.printStackTrace();
        }



















        StringRequest StringRequest = new StringRequest(Request.Method.POST, URL_DONATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if (success.equals("1")) {
                        Toast.makeText(getContext(), "Compte crée !", Toast.LENGTH_SHORT).show();
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

                params.put("id_user", getId);
                params.put("id_post", getId);



                return params;


            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(StringRequest);



    }











}
