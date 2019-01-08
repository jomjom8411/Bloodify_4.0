package com.example.dhoui.bloodify;

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
import io.ghyeok.stickyswitch.widget.StickySwitch;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

public class ProfileActivity extends AppCompatActivity  {

    private SharedPreference prefconf ;

    private static final String TAG = "ProfileActivity";
    private static String URL_READ = "http://192.168.1.6/Blood/read_detail.php";
    private static String URL_EDIT = "http://192.168.1.6/Blood/editprofile.php";
    private static String URL_UPLOAD = "http://192.168.1.6/Blood/upload.php";
    private static String URL_anonyme = "http://192.168.1.6/blood/anonyme.php?Id=";
    private TextView name, email,prename,age,tel;
    private ImageView rank,logout2;
    SessionManager sessionManager;
    String getId;
    private Button logout,btn_photo_upload,historique;
    private  TextView points;
    private Menu action;
    private Bitmap bitmap;
    CircleImageView profile_image;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        prefconf = new SharedPreference(getApplicationContext());
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
         points = (TextView) findViewById(R.id.id);
        prename = findViewById(R.id.prename);
        age = findViewById(R.id.age);
        tel = findViewById(R.id.tel);


        btn_photo_upload = findViewById(R.id.btn_photo);
        profile_image = findViewById(R.id.profile_image);
        historique = findViewById(R.id.activity);
           uri=Uri.parse("https://192.168.1.6/blood/profile_image/male.png");
        profile_image.setImageURI(null);
        profile_image.setImageURI(uri);


        StickySwitch stickySwitch = findViewById(R.id.stickySwitch);
        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(StickySwitch.Direction direction, String s) {


                Toast.makeText(ProfileActivity.this, "Selected " + s, Toast.LENGTH_SHORT).show();

                if(s.equals("Anon")) {
                    anonyme();
                    Toast.makeText(ProfileActivity.this, "yo maan " , Toast.LENGTH_SHORT).show();}



                if(s.equals("Connue")) {
                    Connue();
                    Toast.makeText(ProfileActivity.this, "yo maan " , Toast.LENGTH_SHORT).show();}
            }
        });





        rank = findViewById(R.id.rank);

        int rank_id = getResources().getIdentifier("@drawable/bronze", null, this.getPackageName());
        rank.setImageResource(rank_id);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        //   BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);



        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

        logout2= findViewById(R.id.btnlogout);
        logout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
                prefconf.writeLoginStatus(false);
            }
        });





        historique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog("Historique de vos Activités "," Visiter vos activités", "cancelMethod1","okMethod1");
            }
        });








       /* historique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this ,Historique_activity.class);
                startActivity(intent);
            }
        });   */


        btn_photo_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent0 = new Intent(ProfileActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_message:
                        Intent intent1 = new Intent(ProfileActivity.this, MessageActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_tip:
                        Intent intent2 = new Intent(ProfileActivity.this, TipActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_hospital:
                        Intent intent3 = new Intent(ProfileActivity.this, HospitalActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_profile:
                        break;
                }


                return false;
            }
        });
    }













    private void getUserDetail(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();


                        try {
                            JSONObject jsonObject = new JSONObject(response);




                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")){

                                for (int i =0; i < jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strName = object.getString("name").trim();
                                    String strprename = object.getString("prename").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strtel = object.getString("tel").trim();
                                    String strage = object.getString("age").trim();
                                    String strpoints = object.getString("points").trim();
                                    System.out.println("jomjom points :  "+strpoints);
                                    points.setText(strpoints);
                                    name.setText(strName);
                                    email.setText(strEmail);
                                    prename.setText(strprename);
                                    tel.setText(strtel);
                                    age.setText(strage);


                                   int tester =  (  Integer.valueOf(strpoints));

                                  if (tester> 10 && tester<=30){

                                        rank.setImageDrawable(getResources().getDrawable(R.drawable.silver));

                                  }


                                    if (tester> 30 && tester<=50){

                                        rank.setImageDrawable(getResources().getDrawable(R.drawable.gold));

                                    }

                                    if (tester> 50 && tester <=70){

                                        rank.setImageDrawable(getResources().getDrawable(R.drawable.plat));

                                    }



                                    if (tester> 70 && tester<=100){

                                        rank.setImageDrawable(getResources().getDrawable(R.drawable.diam));

                                    }


                                    if (tester>100){

                                        rank.setImageDrawable(getResources().getDrawable(R.drawable.chall));

                                    }




                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(ProfileActivity.this, "Error Reading Detail "+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileActivity.this, "Error Reading Detail "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > params = new HashMap<>();
                params.put("id", getId);
                params.put("nom", getId);
                params.put("prenom", getId);
                params.put("Email", getId);
                params.put("tel", getId);
                params.put("region", getId);
                params.put("grpsanguin", getId);
                params.put("age", getId);
                params.put("datedonation", getId);
                params.put("password", getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_action, menu);

        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_edit:

                name.setFocusableInTouchMode(true);
                email.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                SaveEditDetail();

                action.findItem(R.id.menu_edit).setVisible(true);
                action.findItem(R.id.menu_save).setVisible(false);

                name.setFocusableInTouchMode(false);
                email.setFocusableInTouchMode(false);
                name.setFocusable(false);
                email.setFocusable(false);

                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }



    private void SaveEditDetail() {

        final String name = this.name.getText().toString().trim();
        final String prename = this.prename.getText().toString().trim();
        final String tel = this.tel.getText().toString().trim();
        final String age = this.age.getText().toString().trim();

        final String email = this.email.getText().toString().trim();
        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {

                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(ProfileActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(name, email, id);
                            }

                        } catch (JSONException e) {
                            Log.e(TAG, "onResponse: ", e);
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(ProfileActivity.this, "Error "+ e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileActivity.this, "Error "+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nom", name);
                params.put("prenom", prename);
                params.put("Email", email);
                params.put("tel", tel);
                params.put("region", getId);
                params.put("grpsanguin", getId);
                params.put("age", age);
                params.put("datedonation", getId);
                params.put("password", getId);
                params.put("Id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



    private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profile_image.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            UploadPicture(getId, getStringImage(bitmap));

        }
    }

    private void UploadPicture(final String id, final String photo) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPLOAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(ProfileActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(ProfileActivity.this, "Try Again!"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileActivity.this, "Try Again!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", id);
                params.put("photo", photo);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    public String getStringImage(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }














    public void customDialog(String title, String message, final String cancelMethod, final String okMethod){
        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(this);
        builderSingle.setIcon(R.mipmap.ic_launcher);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.setNegativeButton(
                "confirmées",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: Cancel Called.");
                        if(cancelMethod.equals("cancelMethod1")){
                            cancelMethod1();
                        }


                    }
                });

        builderSingle.setPositiveButton(
                "non confirmées",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: OK Called.");
                        if(okMethod.equals("okMethod1")){
                            okMethod1();
                        }

                    }
                });


        builderSingle.show();
    }



    private void okMethod1(){

        Intent intent = new Intent(ProfileActivity.this ,Historique_activity.class);
        startActivity(intent);


        Log.d(TAG, "okMethod1: Called.");
        toastMessage("Les demandes non Confirmées :(.");
    }



    private void cancelMethod1(){

        Intent intent = new Intent(ProfileActivity.this ,HistoriqueConfirme.class);
        startActivity(intent);

        Log.d(TAG, "cancelMethod1: Called.");
        toastMessage("Les demandes Confirmées :D .");
    }


    public void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }














    private void anonyme() {





        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_anonyme+getId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(ProfileActivity.this, "vous etes anonyme!", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            Log.e(TAG, "onResponse: ", e);
                            e.printStackTrace();

                            Toast.makeText(ProfileActivity.this, "Error "+ e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(ProfileActivity.this, "Error "+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("Id", getId);
                params.put("anonyme", "1");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }






    private void Connue() {





        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_anonyme+getId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(ProfileActivity.this, "votre identité est révélé !", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            Log.e(TAG, "onResponse: ", e);
                            e.printStackTrace();

                            Toast.makeText(ProfileActivity.this, "Error "+ e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(ProfileActivity.this, "Error "+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("Id", getId);
                params.put("anonyme", "0");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }









}
