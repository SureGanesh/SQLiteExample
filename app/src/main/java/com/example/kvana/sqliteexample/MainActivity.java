package com.example.kvana.sqliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    DataHelper myData;
    EditText name,surname,marks;
    Button adddata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myData = new DataHelper(this);

        name= (EditText) findViewById(R.id.name_et);
        surname= (EditText) findViewById(R.id.surname_et);
        marks= (EditText) findViewById(R.id.marks_et);
        adddata= (Button) findViewById(R.id.adddata_btn);
        AddData();

    }

    public void AddData(){
        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myData.insertData(name.getText().toString(),surname.getText().toString(),marks.getText().toString());
                if(isInserted = true){
                    Toast.makeText(MainActivity.this,"Data is Inserted", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data is not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
