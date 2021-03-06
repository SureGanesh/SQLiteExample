package com.example.kvana.sqliteexample;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView;
import java.util.ArrayList;
import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    DataHelper myData;
    SQLiteDatabase sqLiteDatabase;
    EditText name, surname, marks, id;
    Button adddata;
    Button viewall;
    Button update;
    Button delete;
    Button listdata;

    private String TABLE_NAME = DataHelper.TABLE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myData = new DataHelper(this);

        name = (EditText) findViewById(R.id.name_et);
        surname = (EditText) findViewById(R.id.surname_et);
        marks = (EditText) findViewById(R.id.marks_et);
        id = (EditText) findViewById(R.id.id_et);

        adddata = (Button) findViewById(R.id.adddata_btn);
        AddData();

        viewall = (Button) findViewById(R.id.viewall_btn);
        viewAll();

        update = (Button) findViewById(R.id.update_btn);
        UpdateData();

        delete = (Button) findViewById(R.id.delete_btn);
        DeleteData();

        listdata = (Button) findViewById(R.id.list_view_btn);
        listdata.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(MainActivity.this, ViewListContent.class);
                startActivity(intent);
            }
        });



    }


    public void AddData(){
        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myData.insertData(name.getText().toString(),surname.getText().toString(),marks.getText().toString());
                if(isInserted == true){
                    Toast.makeText(MainActivity.this,"Data is Inserted", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data is not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void viewAll(){
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=myData.getAllData();
                if(res.getCount() == 0){
                    //showMessage
                    showMessage("ERROR","Data not found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("NAME :"+res.getString(1)+"\n");
                    buffer.append("SURNAME :"+res.getString(2)+"\n");
                    buffer.append("MARKS:"+res.getString(3)+"\n\n");
                }
                // show all data
                showMessage("Student Details",buffer.toString());
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void UpdateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myData.updateData(id.getText().toString(),name.getText().toString(),surname.getText().toString(),marks.getText().toString());
                if(isUpdate == true){
                    Toast.makeText(MainActivity.this,"Data is Updated",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data is Not Updated",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = myData.deleteData(id.getText().toString());
                if(deleteRows > 0){
                    Toast.makeText(MainActivity.this,"Data is Deleted",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data is Not Deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }




  /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      TextView temp = (TextView) view;
      Cursor res = myData.getAllData();
      if (res.getCount() == 0) {
          //showMessage
          // showMessage("ERROR","Data not found");
          return;
      }
      StringBuffer buffer = new StringBuffer();
      while (res.moveToNext()) {
          buffer.append("ID :" + res.getString(0) + "\n");
          buffer.append("NAME :" + res.getString(1) + "\n");
          buffer.append("SURNAME :" + res.getString(2) + "\n");
          buffer.append("MARKS:" + res.getString(3) + "\n\n");
      }
  }
*/

}
