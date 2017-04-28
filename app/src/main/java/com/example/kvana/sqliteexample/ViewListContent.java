package com.example.kvana.sqliteexample;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kvana on 4/27/17.
 */

public class ViewListContent extends AppCompatActivity {
    DataHelper myData;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_row);
        ListView listView= (ListView) findViewById(R.id.listview);

        myData = new DataHelper(this);
        ArrayList<String> theList = new ArrayList();
        Cursor data = myData.getAllData();
        if(data.getCount()==0){
            Toast.makeText(ViewListContent.this,"Database is empty",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(0));
                theList.add(data.getString(1));
                theList.add(data.getString(3));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
