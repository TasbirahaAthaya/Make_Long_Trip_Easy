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

public class CounterDetails extends BusCounter {


    private ListView lv;

    private String JSON_STRING;


    String s2;
    String d2, bn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_details);

        lv = (ListView) findViewById(R.id.listView1);

        Intent intent = getIntent();

        s2 = intent.getStringExtra(Config.source);
        d2 = intent.getStringExtra(Config.destination);
        bn = intent.getStringExtra(Config.bus);

        getEmployee();


    }

    private void getEmployee() {
        class GetEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(CounterDetails.this, "Fetching...", "Wait...", false, false);
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
                    URL url = new URL(Config.URL_GET_Counter + "?source=" + s2 + "&destination=" + d2 + "&bus_name=" + bn);
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

    /*private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY2);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_BUS);
            String desg = c.getString(Config.TAG_sch);
            String sal = c.getString(Config.TAG_dur);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    private void showEmployee(String json) {
        // JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();
        try {
            Log.v("response is", json.toString());

            JSONObject jsonObject = new JSONObject(json);

            JSONArray result1 = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY2);
            Log.i("tagconvertstr", "[" + result1 + "]");

            for (int i = 0; i < result1.length(); i++) {
                JSONObject jo = result1.getJSONObject(i);
                String busName = jo.getString(Config.TAG_BUS);
                //  String sch = jo.getString(Config.TAG_sch);
                String dur = jo.getString(Config.TAG_dur);
                String cn = jo.getString(Config.TAG_counter_name);
                String ca = jo.getString(Config.TAG_counter_add);
                String cc = jo.getString(Config.TAG_counter_contact);
                String tc = jo.getString(Config.TAG_ticket_cost);
                String t1 = jo.getString(Config.TAG_durb1);
                String t2 = jo.getString(Config.TAG_durb2);
                String t3 = jo.getString(Config.TAG_durb3);
                String r = jo.getString(Config.TAG_route);
                String ac = jo.getString(Config.TAG_ac);
                String non = jo.getString(Config.TAG_nonac);


                //   Log.d("my data...............", id+name);

                HashMap<String, String> employees1 = new HashMap<>();
                employees1.put(Config.TAG_BUS, busName);
                //employees1.put(Config.TAG_sch,sch);
                employees1.put(Config.TAG_dur,dur);
                employees1.put(Config.TAG_counter_name,cn);
                employees1.put(Config.TAG_counter_add,ca);
                employees1.put(Config.TAG_counter_contact,cc);
                employees1.put(Config.TAG_ticket_cost,tc);
                employees1.put(Config.TAG_durb1,t1);
                employees1.put(Config.TAG_durb2,t2);
                employees1.put(Config.TAG_durb3,t3);
                employees1.put(Config.TAG_route,r);
                employees1.put(Config.TAG_ac,ac);
                employees1.put(Config.TAG_nonac,non);
                list1.add(employees1);
                Log.d("my data...............", list1.toString());


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                CounterDetails.this, list1, R.layout.list_item1,
                new String[]{Config.TAG_BUS,
                        Config.TAG_durb1,Config.TAG_durb2,Config.TAG_durb3,
                        Config.TAG_dur,Config.TAG_counter_name,Config.TAG_counter_add,
                        Config.TAG_counter_contact,Config.TAG_ticket_cost,
                        Config.TAG_ac,Config.TAG_nonac,Config.TAG_route},
                new int[]{R.id.busName,R.id.sch1,R.id.sch2,R.id.sch3,R.id.j_dur,
                        R.id.c_name,R.id.c_add,R.id.c_con,R.id.tic_cost,R.id.ac,R.id.non,R.id.r});

        lv.setAdapter(adapter);


    }
}