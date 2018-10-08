package com.example.sharna.navigation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class TrainStation extends AppCompatActivity {
    private ListView listView;



    String ts1;
    String td1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_station);

        listView = (ListView) findViewById(R.id.listView);
        //     listView.setOnItemClickListener(this);

        ts1 = getIntent().getStringExtra("kk1");
        td1 = getIntent().getStringExtra("kk2");

        Log.d("val2 ts1td1", ts1 + td1);



        getJSON();

        Log.d("val ts1td1", ts1+td1);


    }

    private void showEmployee(String json){
        // JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            Log.v("response is",json.toString());

            JSONObject jsonObject = new JSONObject(json);

            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY3);
            Log.i("tagconvertstr", "["+result+"]");

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String busName = jo.getString(Config.TAG_TRAIN);
                String name = jo.getString(Config.TAG_TRAIN_last);

                //   Log.d("my data...............", id+name);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_TRAIN,busName);
                employees.put(Config.TAG_TRAIN_last,name);
                list.add(employees);
                Log.d("my data...............", list.toString());


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TrainStation.this, list, R.layout.list_station,
                new String[]{Config.TAG_TRAIN,Config.TAG_TRAIN_last},
                new int[]{R.id.des,R.id.des1});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i2 = new Intent(getApplicationContext(), StationDetails.class);
                HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
                String train = map.get(Config.TAG_TRAIN).toString();
                Log.d("val train", train);

                i2.putExtra(Config.train,train);
                i2.putExtra(Config.source1,ts1);
                i2.putExtra(Config.destination1,td1);
                startActivity(i2);

            }
        });}

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TrainStation.this, "Fetching Data", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                // JSON_STRING = s; //    Log.d("final url", s);

                Log.d("final1 url", s);


                showEmployee(s);

                Log.d("final2 url", s);

            }

            @Override
            protected String doInBackground(Void... params) {
             /*   RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ALL+"?source="+"Dhaka"+"&destination="+"Rajshahi"+"","Dhaka", "Rajshahi");
*/
                StringBuilder sb = new StringBuilder();

                try {
                    URL url = new URL(Config.URL_GET_Train + "?source=" + ts1 + "&destination=" + td1);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String s;
                    while ((s = bufferedReader.readLine()) != null) {
                        sb.append(s + "\n");
                    }
                } catch (Exception e) {
                }


                return sb.toString();


            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }



}

