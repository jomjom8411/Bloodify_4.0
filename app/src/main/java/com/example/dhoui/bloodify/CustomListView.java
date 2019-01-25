package com.example.dhoui.bloodify;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.GestureDetector;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.InputStream;


import java.io.BufferedInputStream;

import com.android.volley.toolbox.StringRequest;



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


import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;







    public class CustomListView extends ArrayAdapter<String>  {

        private String[] profilename;
        private String[] email;
    private String[] number;
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

        private static String URL_DONATE = "http://192.168.1.3/blood/donate2.php";
    String urladdress = "http://192.168.1.3/blood/displaypostshomepage_notfinished.php";

        public CustomListView(Activity context, final String[] profilename, String[] email, String[] imagepath, String[] number ) {
            super(context, R.layout.layout, profilename);
            this.context = context;
            this.profilename = profilename;
            this.email = email;
            this.number = number;
            this.imagepath = imagepath;









        }

        @SuppressLint("InflateParams")
        @NonNull
        @Override

        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            sessionManager = new SessionManager(getContext());
            sessionManager.checkLogin();
            HashMap<String, String> user = sessionManager.getUserDetail();
            getId = user.get(SessionManager.ID);



            View r = convertView;
            ViewHolder viewHolder ;
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
            viewHolder.tvw3.setText(number[position]);


            new GetImageFromURL(viewHolder.ivw).execute(imagepath[position]);
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.donate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                       donate(position);
                finalViewHolder.donate.setEnabled(false);
                    Toast.makeText(getContext(), "  Veuillez verifier votre historique de donnation non confirmé" , Toast.LENGTH_SHORT).show();

                }
            })
            ;


            return r;
        }

        class ViewHolder {

            TextView tvw1;
            TextView tvw2;
            TextView tvw3;
            ImageView ivw;
            ImageView ivw2;
            Button donate;


            ViewHolder(View v) {
                tvw1 =  v.findViewById(R.id.tvprofilename);
                tvw2 =  v.findViewById(R.id.tvemail);
                tvw3 =  v.findViewById(R.id.number);
                ivw =  v.findViewById(R.id.imageView);
                ivw2 =  v.findViewById(R.id.imageView3);
                donate =v.findViewById(R.id.donate);
                donate.setVisibility(View.VISIBLE);
                tvw3.setVisibility(View.VISIBLE);
                ivw2.setVisibility(View.VISIBLE);

            }

        }

        @SuppressLint("StaticFieldLeak")
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
                sb.append(line).append("\n");
            }
            is.close();
            result = sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace();

        }




        StringRequest StringRequest = new StringRequest(Request.Method.POST, URL_DONATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                } catch (JSONException e) {
                    e.printStackTrace();


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

                    JSONObject jo2 ;
                    testpost = new String[ja2.length()];

                    jo2 = ja2.getJSONObject(position);
                    String e = jo2.getString("Id");

                    System.out.println("saslut jomjojjmmmmmmm"+e);

                    params.put("id_user", getId);
                    params.put("id_post", e);
                } catch (Exception ex) {

                    ex.printStackTrace();
                }


                return params;


            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(StringRequest);



    }











}
