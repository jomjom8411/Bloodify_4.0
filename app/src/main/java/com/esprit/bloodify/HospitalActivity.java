package com.esprit.bloodify;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class HospitalActivity extends AppCompatActivity {
    List<Book> lstBook ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);







        lstBook = new ArrayList<>();
        lstBook.add(new Book("Centre National de Transfusion Sanguine", "Tunisie","13, Rue Djebel Lakhdhar Bab Sâadoun Tunis ",R.drawable.le1));
        lstBook.add(new Book("Banque Du Sang Militaire","Tunis","Ave des Etats Unis, Tunis 1002",R.drawable.le2));
        lstBook.add(new Book("Banque du sang","Sfax","croisé de chemin avenue majida boullila et، Route El Ain, Sfax",R.drawable.le3));
        lstBook.add(new Book("Hopital de Nefta","Nefta"," Place de l'independance Nafta ",R.drawable.le4));
        lstBook.add(new Book("Hôpital Universitaire Sahloul (CHU Sahloul)","Sousse"," Route Ceinture Cité Sahloul 4054 Sousse ",R.drawable.le5));
        lstBook.add(new Book("CHU Farhat Hached","Sousse","Rue Ibn Jazzar 4031, Sousse Ezzouhour",R.drawable.le6));
        lstBook.add(new Book("Regional Hospital of Gabes Mohammed Ben Sassi","Sousse","نهج إبن خلدون، Mtorrech 6014",R.drawable.bb));
        lstBook.add(new Book("Hospital Habib Bourguiba","Sousse","Rue Al Firdaws, Sfax 3089",R.drawable.cc));
        lstBook.add(new Book("Hôpital Charles Nicolle de Tunis","Sousse","Boulevard du 9 avril 1938 Bab Saâdoun 1007 Tunis",R.drawable.dd));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstBook);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);


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
