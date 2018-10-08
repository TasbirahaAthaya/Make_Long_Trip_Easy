package com.example.sharna.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;

public class Search extends AppCompatActivity {
    String[] source_arr = { "Dhaka","Rajshahi","Chittagong"};
    String[] destination_arr = { "Dhaka","Rajshahi","Chittagong"};

    AutoCompleteTextView acTextView1,acTextView2;

    RequestParams params = new RequestParams();
    public String src1,des1;
    EditText es1, ed1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        es1 = (EditText) findViewById(R.id.source);
        ed1 = (EditText) findViewById(R.id.destination);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, source_arr);
        //Find TextView control
        acTextView1 = (AutoCompleteTextView) findViewById(R.id.source);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView1.setThreshold(0);
        //Set the adapterr
        acTextView1.setAdapter(adapter1);

       src1 =  acTextView1.getText().toString();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, destination_arr);
        //Find TextView control
        acTextView2 = (AutoCompleteTextView) findViewById(R.id.destination);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView2.setThreshold(0);
        //Set the adapter
        acTextView2.setAdapter(adapter2);
        des1=acTextView2.getText().toString();

        Log.d("vals",  acTextView1.getText().toString()+acTextView2.getText().toString());

    }
    public void search(View view)
    {
      Intent i= new Intent(Search.this,BusCounter.class);

        i.putExtra("k1", es1.getText().toString());
        i.putExtra("k2", ed1.getText().toString());

      startActivity(i);


    }

}
