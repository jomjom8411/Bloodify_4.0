package com.example.dhoui.bloodify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://192.168.1.3/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String nom, String prenom, String Email, int tel, String region, String grpsanguin, int age,String datedonation, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nom", nom);
        params.put("prenom", prenom);
        params.put("Email", Email);
        params.put("tel", tel + "");
        params.put("region", region);
        params.put("grpsanguin", grpsanguin);
        params.put("age", age + "");
        params.put("datedonation", datedonation);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> params = new HashMap<>();
        params.put("Content-Type","application/x-www-form-urlencoded");
        return params;
    }
}
