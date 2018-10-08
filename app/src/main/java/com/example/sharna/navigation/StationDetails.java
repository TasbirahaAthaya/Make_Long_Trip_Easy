package com.example.sharna.navigation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class StationDetails extends AppCompatActivity {
    private ListView lv;

    String s2;
    String d2, bn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_details);

        lv = (ListView) findViewById(R.id.listView1);

        Intent intent = getIntent();

        s2 = intent.getStringExtra(Config.source1);
        d2 = intent.getStringExtra(Config.destination1);
        bn = intent.getStringExtra(Config.train);

        getEmployee();


    }

    private void getEmployee() {
        class GetEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(StationDetails.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                StringBuilder sb = new StringBuilder();

                try {
                    URL url = new URL(Config.URL_GET_Station + "?source=" + s2 + "&destination=" + d2 + "&train_name=" + bn);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String ss;
                    while ((ss = bufferedReader.readLine()) != null) {
                        sb.append(ss + "\n");
                    }
                } catch (Exception e) {
                }


                return sb.toString();

            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }


    private void showEmployee(String json) {
        // JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();
        try {
            Log.v("response is", json.toString());

            JSONObject jsonObject = new JSONObject(json);

            JSONArray result1 = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY4);
            Log.i("tagconvertstr", "[" + result1 + "]");

            for (int i = 0; i < result1.length(); i++) {
                JSONObject jo = result1.getJSONObject(i);
                String busName = jo.getString(Config.TAG_TRAIN);
                String sch = jo.getString(Config.TAG_sch1);
                String dur = jo.getString(Config.TAG_dur1);
                String cn = jo.getString(Config.TAG_counter_name1);
                String cn1 = jo.getString(Config.TAG_counter_name2);
                String ca = jo.getString(Config.TAG_counter_add1);
                String ca1 = jo.getString(Config.TAG_counter_add2);
                String cc = jo.getString(Config.TAG_counter_contact1);
                String tc = jo.getString(Config.TAG_ticket_cost1);



                //   Log.d("my data...............", id+name);

                HashMap<String, String> employees1 = new HashMap<>();
                employees1.put(Config.TAG_TRAIN, busName);
                employees1.put(Config.TAG_sch1,sch);
                employees1.put(Config.TAG_dur1,dur);
                employees1.put(Config.TAG_counter_name1,cn);
                employees1.put(Config.TAG_counter_name2,cn1);
                employees1.put(Config.TAG_counter_add1,ca);
                employees1.put(Config.TAG_counter_add2,ca1);
                employees1.put(Config.TAG_counter_contact1,cc);
                employees1.put(Config.TAG_ticket_cost1,tc);
                list1.add(employees1);
                Log.d("my data...............", list1.toString());


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                StationDetails.this, list1, R.layout.list_td,
                new String[]{Config.TAG_TRAIN,Config.TAG_sch1,Config.TAG_dur1,Config.TAG_counter_name1,Config.TAG_counter_add1,
                        Config.TAG_counter_name2,Config.TAG_counter_add2, Config.TAG_counter_contact1,Config.TAG_ticket_cost1},
                new int[]{R.id.busName,R.id.sch,R.id.j_dur,R.id.c_name,R.id.c_add,R.id.c_name2,R.id.c_add2,R.id.c_con,R.id.tic_cost});

        lv.setAdapter(adapter);


    }
}