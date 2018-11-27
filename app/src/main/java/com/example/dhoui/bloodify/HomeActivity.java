package com.example.dhoui.bloodify;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;
import java.util.ArrayList;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    private Spinner region ;
   private Spinner grpsanguin ;
    private Spinner number ;
    ListView listView;
    ListViewAdapter adapter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        grpsanguin = findViewById(R.id.grpsanguin_Text);
        region = findViewById(R.id.region_Text);
        number= findViewById(R.id.number_Text);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.grpsanguin1,
                android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Regions,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.numbers,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        grpsanguin.setAdapter(adapter3);
        grpsanguin.getSelectedItem().toString();

        region.setAdapter(adapter1);
        region.getSelectedItem().toString();

        number.setAdapter(adapter4);
        number.getSelectedItem().toString();



        title = new String[]{"Sami", "Saleh", "Ali", "asma", "Béji"};
        description = new String[]{"je cherche 6 slots de sang O+ à Ariana", "Je cherche 10 slots de sang AB à Sousse", "je cherche max slots de tous type de sang à Sfax", "Je cherche 1 slot de sang A à Béja", "ok"};
        icon = new int[]{R.drawable.ic_message_black_24dp, R.drawable.ic_home_black_24dp, R.drawable.ic_account_circle_black_24dp, R.drawable.ic_local_hospital_black_24dp, R.drawable.ic_account_circle_black_24dp};

        listView = findViewById(R.id.listView);
        for (int i =0; i<title.length; i++){
            Model model = new Model(title[i], description[i], icon[i]);
            //bind all strings in an array
            arrayList.add(model);
        }

        //pass results to listViewAdapter class
        adapter = new ListViewAdapter(this, arrayList);

        //bind the adapter to the listview
        listView.setAdapter(adapter);



       /* Intent intent = getIntent();
        String name = intent.getStringExtra("nom");
        String username = intent.getStringExtra("Email");
        int age = intent.getIntExtra("age", -1);

        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etAge = (EditText) findViewById(R.id.etAge);

        // Display user details
        String message = name + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        etAge.setText(age + "");*/

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:

                        break;

                    case R.id.nav_message:
                        Intent intent1 = new Intent(HomeActivity.this, MessageActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.nav_tip:
                        Intent intent2 = new Intent(HomeActivity.this, TipActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.nav_hospital:
                        Intent intent3 = new Intent(HomeActivity.this, HospitalActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0,0);
                        break;
                }


                return false;
            }
        });
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            public boolean onQueryTextSubmit(String s) {
                return false;
            }


            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.action_settings){
            //do your functionality here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }










}
