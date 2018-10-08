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

public class SearchTrain2 extends AppCompatActivity {
    String[] source_arr2 = { "Dhaka","Rajshahi","Chittagong"};
    String[] destination_arr2 = { "Dhaka","Rajshahi","Chittagong"};

    AutoCompleteTextView acTextView1,acTextView2;

    RequestParams params = new RequestParams();
    public String src2,des2;
    EditText es2, ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_train2);

        es2 = (EditText) findViewById(R.id.source2);
        ed2 = (EditText) findViewById(R.id.destination2);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, source_arr2);
        //Find TextView control
        acTextView1 = (AutoCompleteTextView) findViewById(R.id.source2);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView1.setThreshold(0);
        //Set the adapterr
        acTextView1.setAdapter(adapter1);

        src2 =  acTextView1.getText().toString();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, destination_arr2);
        //Find TextView control
        acTextView2 = (AutoCompleteTextView) findViewById(R.id.destination2);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView2.setThreshold(0);
        //Set the adapter
        acTextView2.setAdapter(adapter2);
        des2=acTextView2.getText().toString();

        Log.d("vals", acTextView1.getText().toString() + acTextView2.getText().toString());

    }

    public void searchtrain(View view)
    {
        Intent it= new Intent(SearchTrain2.this,TrainStation.class);

        it.putExtra("kk1", es2.getText().toString());
        it.putExtra("kk2", ed2.getText().toString());

        startActivity(it);


    }

}
