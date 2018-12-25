package com.example.dhoui.bloodify;

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


/**
 * Created by jaiso on 13-02-2018.
 */



    public class CustomListView3 extends ArrayAdapter<String>  {

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

        private static String URL_DONATE = "http://192.168.1.6/blood/donate2.php";
    String urladdress = "http://192.168.1.6/blood/displayposts.php";

        public CustomListView3(Activity context, final String[] profilename, String[] email, String[] imagepath) {
            super(context, R.layout.layout3, profilename);
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
                r = layoutInflater.inflate(R.layout.layout3, null, true);
                viewHolder = new ViewHolder(r);


                r.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) r.getTag();

            }

            viewHolder.tvw1.setText(profilename[position]);
            viewHolder.tvw2.setText(email[position]);


            new GetImageFromURL(viewHolder.ivw).execute(imagepath[position]);
            final ViewHolder finalViewHolder = viewHolder;



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
