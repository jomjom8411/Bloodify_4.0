package com.example.dhoui.bloodify;

import android.app.Activity;
import android.content.Intent;
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import  android.widget.*;
import android.content.*;


/**
 * Created by jaiso on 13-02-2018.
 */



    public class CustomListViewPoints extends ArrayAdapter<String>  {

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

        private static String URL_DONATE = "http://192.168.1.6/blood/donate2.php";
    String urladdress = "http://192.168.1.6/blood/displayposts.php";

        public CustomListViewPoints(Activity context, final String[] profilename, String[] email, String[] imagepath, String[] number ) {
            super(context, R.layout.layoutpoints, profilename);
            this.context = context;
            this.profilename = profilename;
            this.email = email;
            this.number = number;
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
                r = layoutInflater.inflate(R.layout.layoutpoints, null, true);
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
            viewHolder.voirprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   Intent intent = new Intent(getContext(),profile_user.class);


                   String test = Integer.toString(position);
                     intent.putExtra("position",test);





                    Toast.makeText(getContext(), "voir profile : " + position , Toast.LENGTH_SHORT).show();

                    getContext().startActivity(intent);

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
            Button voirprofile;


            ViewHolder(View v) {
                tvw1 = (TextView) v.findViewById(R.id.tvprofilename);
                tvw2 = (TextView) v.findViewById(R.id.tvemail);
                tvw3 = (TextView) v.findViewById(R.id.number);
                ivw = (ImageView) v.findViewById(R.id.imageView);
                ivw2 = (ImageView) v.findViewById(R.id.imageView3);
                voirprofile =v.findViewById(R.id.voirprofile);




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

                    InputStream ist = new URL(urldisplay).openStream();
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


















}
