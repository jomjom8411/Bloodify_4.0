package com.example.dhoui.bloodify;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HospitalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        TextView title = (TextView) findViewById(R.id.activityTitle3);
        title.setText("This is ActivityThree");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent0 = new Intent(HospitalActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_message:
                        Intent intent1 = new Intent(HospitalActivity.this, MessageActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_tip:
                        Intent intent2 = new Intent(HospitalActivity.this, TipActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_hospital:

                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(HospitalActivity.this, ProfileActivity.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });
    }
}
