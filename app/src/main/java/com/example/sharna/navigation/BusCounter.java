package com.example.sharna.navigation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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


public class BusCounter extends Search
{

    private ListView listView;

    private String JSON_STRING;


    String s1;
    String d1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_counter);

        listView = (ListView) findViewById(R.id.listView);
    //     listView.setOnItemClickListener(this);

        s1 = getIntent().getStringExtra("k1");
        d1 = getIntent().getStringExtra("k2");

        Log.d("val2 s1d1", s1+d1);



        getJSON();

        Log.d("val s1d1", s1+d1);


    }

    private void showEmployee(String json){
       // JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            Log.v("response is",json.toString());

            JSONObject jsonObject = new JSONObject(json);

            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            Log.i("tagconvertstr", "["+result+"]");

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String busName = jo.getString(Config.TAG_BUS);
               String name = jo.getString(Config.TAG_BUS_last);

                //   Log.d("my data...............", id+name);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_BUS,busName);
                employees.put(Config.TAG_BUS_last,name);
                list.add(employees);
                Log.d("my data...............", list.toString());


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                BusCounter.this, list, R.layout.list_item,
                new String[]{Config.TAG_BUS,Config.TAG_BUS_last},
                new int[]{R.id.src,R.id.src1});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i2 = new Intent(getApplicationContext(), CounterDetails.class);
                HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
                String bus = map.get(Config.TAG_BUS).toString();
                Log.d("val bus", bus);

                i2.putExtra(Config.bus,bus);
                i2.putExtra(Config.source,s1);
                i2.putExtra(Config.destination,d1);
                startActivity(i2);

            }
            });}

            private void getJSON() {
                class GetJSON extends AsyncTask<Void, Void, String> {

                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(BusCounter.this, "Fetching Data", "Wait...", false, false);
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
                            URL url = new URL(Config.URL_GET_ALL + "?source=" + s1 + "&destination=" + d1);
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


   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i2 = new Intent(this, CounterDetails.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String bus = map.get(Config.TAG_BUS).toString();
        i2.putExtra(Config.bus,bus);
        i2.putExtra(Config.source,s1);
        i2.putExtra(Config.destination,d1);
        startActivity(i2);
    }*/
        }
