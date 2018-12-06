package com.example.dhoui.bloodify;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dhoui.bloodify.LoginRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText email_text,password_text;
    private Button Signin_button,register_button;
    private ProgressBar progress;
    private static String URL_REGIST = "http://192.168.1.2/blood/Login.php";
    SessionManager sessionManager;
    String getId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sessionManager = new SessionManager(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


         email_text = findViewById(R.id.email_text);
       password_text =  findViewById(R.id.password_text);
        Signin_button =  findViewById(R.id.Signin_button);
         register_button =  findViewById(R.id.register_button);
         progress = findViewById(R.id.progress);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        Signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String email = email_text.getText().toString().trim();
                 String password = password_text.getText().toString().trim();


                if(!email.isEmpty() || !password.isEmpty()) {
                    Login(email,password);
                }else {
                    email_text.setError("please insert email");
                    password_text.setError("please insert password");


                }
            }
        });



        }




    private void Login(final String email_text, final String password_text) {
        progress.setVisibility(View.VISIBLE);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("Login");

                    if(success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String nom = object.getString("nom").trim();
                            String email = object.getString("Email").trim();
                            String id = object.getString("id").trim();

                            sessionManager.createSession(nom, email, id);


                            Intent registerIntent = new Intent(LoginActivity.this, HomeActivity.class);
                            LoginActivity.this.startActivity(registerIntent);
                            Toast.makeText(LoginActivity.this, "Login success. !"+nom+"mail"+email, Toast.LENGTH_LONG).show();


                        }

                    }
                } catch (JSONException e) {
                    Log.e(TAG, "onResponse: ", e);
                    Toast.makeText(LoginActivity.this, "Try again. !", Toast.LENGTH_LONG).show();
                    progress.setVisibility(View.VISIBLE);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Erreur !"+ error.toString(), Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.VISIBLE);

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Email",email_text);
                params.put("password",password_text);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}

