package com.esprit.bloodify;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.StrictMode;
        import android.support.annotation.NonNull;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.GestureDetector;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.Spinner;

        import com.github.clans.fab.FloatingActionButton;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.io.BufferedInputStream;
        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.HashMap;

public class details extends AppCompatActivity {

    private  SharedPreference prefconf ;
    String urladdress = "http://41.226.11.252:1180/bloodify/finishedposts.php";
    String urladdress2 = "http://41.226.11.252:1180/bloodify/getcolumsfromdonatebyidpost.php?id_post=";
    String urladdress3 = "http://41.226.11.252:1180/bloodify/profile_id.php?Id=";

    String[] name={};
    String[] salut;
    String[] email={},number={};
    String[] imagepath={};
    String[] test4;
    ListView listView;
    BufferedInputStream is,is2,is3;
    String line = null;
    String result = null;
    String result2 = null;
    String result3 = null;
    String chaine = "";
    String chaine1 = "or Id=";
    String[] test, test2, test3;
    String getId;
    SessionManager sessionManager;
    private Spinner region;
    private Spinner grpsanguin;
    private Spinner slots;
    private ImageView mHeartRed;
    private ImageView  mHeartWhite;
    private Activity context;
    private static String URL_DONATE = "http://41.226.11.252:1180/bloodify/donate2.php";
    private GestureDetector mGestureDetector;
    private Heart mHeart;



    String[] title;
    String[] description;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<>();
    private Button donate;
    private static String URL_POST = "http://41.226.11.252:1180/bloodify/addpost.php";
    private static final String TAG = "HomeActivity ";
    FloatingActionButton floatingActionButton1,floatingActionButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {









        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_details);
        prefconf = new SharedPreference(getApplicationContext());



        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.grpsanguin1,
                android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();



        Intent intent = getIntent();
        String value1 = intent.getStringExtra("position");



        mHeart = new Heart(mHeartWhite, mHeartRed);


        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);

        listView =  findViewById(R.id.lview);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListViewPostdetails customListView = new CustomListViewPostdetails(this, name, email, imagepath, number);
        listView.setAdapter(customListView);
















        donate = findViewById(R.id.donate);
        //  ok = findViewById(R.id.menu_item1);






















        //posts vieeeeeeew




































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




        BottomNavigationView bottomNavigationView =  findViewById(R.id.bottomNavView_Bar);
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent intent0 = new Intent(details.this, HomeActivity.class);
                        startActivity(intent0);
                        overridePendingTransition(0, 0);
                        break;


                    case R.id.nav_message:
                        Intent intent1 = new Intent(details.this, MessageActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_tip:
                        Intent intent2 = new Intent(details.this, TipActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_hospital:
                        Intent intent3 = new Intent(details.this, HospitalActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(details.this, ProfileActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        break;
                }


                return false;
            }
        });















    }














   /* @Override
    public boolean onCreateOptionsMenu(Menu menu)   {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            public boolean onQueryTextSubmit(String s) {
                return false;
            }


            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                   // customListView.filter("");
                    //listView.clearTextFilter();
                } else {
                  //  customListView.filter(s);
                }
                return true;
            }
        });
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            //do your functionality here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }   */







    private void collectData() {
//Connection
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
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
            is.close();
            result = sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace();

        }



        //ok



















//JSON
        try {
            JSONArray ja = new JSONArray(result);

            JSONObject jo ;
            Intent intent = getIntent();
            String value1 = intent.getStringExtra("position");
            int test = Integer.valueOf(value1);

            jo = ja.getJSONObject(test);
            String id_postt=jo.getString("Id");






            //Connection
            try {

                URL url2 = new URL(urladdress2+id_postt);
                HttpURLConnection con = (HttpURLConnection) url2.openConnection();
                con.setRequestMethod("GET");
                is2 = new BufferedInputStream(con.getInputStream());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //content
            try {
                BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
                StringBuilder sb2 = new StringBuilder();
                while ((line = br2.readLine()) != null) {
                    sb2.append(line).append("\n");
                }
                is2.close();
                result2 = sb2.toString();

            } catch (Exception ex) {
                ex.printStackTrace();

            }


            //ok






            try {
                JSONArray ja2 = new JSONArray(result2);

                JSONObject jo2 ;

                test2 = new String[ja2.length()];


                for (int j = 0; j <= ja2.length(); j++) {
                    jo2 = ja2.getJSONObject(j);


                    test2[j] = jo2.getString("id_user");



             chaine=chaine+test2[j]+" "+chaine1;




                    try {

                        chaine=chaine+"0";
                        URL url3 = new URL(urladdress3+chaine);
                        HttpURLConnection con = (HttpURLConnection) url3.openConnection();
                        con.setRequestMethod("GET");
                        is3 = new BufferedInputStream(con.getInputStream());



                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //content
                    try {
                        BufferedReader br3 = new BufferedReader(new InputStreamReader(is3));
                        StringBuilder sb3 = new StringBuilder();
                        while ((line = br3.readLine()) != null) {
                            sb3.append(line).append("\n");
                        }
                        is3.close();
                        result3 = sb3.toString();

                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
















                    try {
                        JSONArray ja3 = new JSONArray(result3);

                        JSONObject jo3 ;
                        name = new String[ja2.length()];
                        email = new String[ja2.length()];
                        number = new String[ja2.length()];
                        imagepath = new String[ja2.length()];
                        test4 = new String[ja2.length()];
                        for (int i = 0; i <= ja3.length(); i++) {

                            jo3 = ja3.getJSONObject(i);

                            test4[i] = jo3.getString("anonyme");

                            if(test4[i].equals("1"))
                            {
                                name[i] = "Anonyme";
                                email[i] = "Anonyme";
                                number[i] = "Anonyme";
                                imagepath[i] = "http://41.226.11.252:1180/bloodify/profile_image/anonyme_profile.png";


                            }

                            else {
                                name[i] = jo3.getString("nom") + " " + jo3.getString("prenom");
                                email[i] = jo3.getString("tel");
                                number[i] = jo3.getString("Email");
                                imagepath[i] = jo3.getString("photo");

                            }

                        }






                    } catch (Exception ex) {

                        ex.printStackTrace();
                    }

















                }











            } catch (Exception ex) {

                ex.printStackTrace();
            }



















        } catch (Exception ex) {

            ex.printStackTrace();
        }










    }


















}
