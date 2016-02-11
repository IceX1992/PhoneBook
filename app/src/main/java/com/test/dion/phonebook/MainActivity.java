package com.test.dion.phonebook;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import database.PhoneBookDatabase;
import entities.PhoneBook;


public class MainActivity extends AppCompatActivity {

    private PhoneBookDatabase db;

   // public PhoneBookDatabase db = new PhoneBookDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new PhoneBookDatabase(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    String notification = "";

    public void insertData(View view) {
        EditText name = (EditText) findViewById(R.id.naam);
        String nameValue = String.valueOf(name.getText());

        EditText number = (EditText) findViewById(R.id.number);
        String numberValue = String.valueOf(number.getText());
        Integer numberValue2 = numberValue.isEmpty() ? -1 : Integer.parseInt(numberValue);

        if (!nameValue.isEmpty() && !numberValue.isEmpty()){
            //insert relevant data
            ContentValues contact = new ContentValues();
            contact.put(PhoneBookDatabase.PB_NAAM,nameValue);
            contact.put(PhoneBookDatabase.PB_NUMBER, numberValue2);
            long recordId = db.insertContact(nameValue, contact);
            notification = recordId > 0 ? "Data inserted" : "No data inserted";
            Toast.makeText(this, notification, Toast.LENGTH_LONG).show();

        }else{
            notification = "Please insert name and number";
        }
        Toast.makeText(this,notification, Toast.LENGTH_LONG).show();
    }

    public void updateData(View view) {
        EditText name = (EditText) findViewById(R.id.naam);
        String nameValue = String.valueOf(name.getText());

        EditText number = (EditText) findViewById(R.id.number);
        String numberValue = String.valueOf(number.getText());
        Integer numberValue2 = Integer.parseInt(numberValue);

        if (!nameValue.isEmpty() && !numberValue.isEmpty()){
            //update relevant data
            notification = " Data updated";
        }else{
            notification = "Please insert name and number";
        }
        Toast.makeText(this,notification, Toast.LENGTH_LONG).show();
    }

    public void deleteData(View view) {
        EditText name = (EditText) findViewById(R.id.naam);
        String nameValue = String.valueOf(name.getText());

        EditText number = (EditText) findViewById(R.id.number);
        String numberValue = String.valueOf(number.getText());
        Integer numberValue2 = Integer.parseInt(numberValue);

        if (!nameValue.isEmpty() && !numberValue.isEmpty()){
            //delete relevant data
            notification = "Data deleted";
        }else{
            notification = "Please insert name and number";
        }
        Toast.makeText(this,notification, Toast.LENGTH_LONG).show();
    }

    public void findData(View view) {
        EditText name = (EditText) findViewById(R.id.naam);
        String nameValue = String.valueOf(name.getText());

        EditText number = (EditText) findViewById(R.id.number);
        String numberValue = String.valueOf(number.getText());
        Integer numberValue2 = numberValue.isEmpty() ? -1: Integer.parseInt(numberValue);

        PhoneBook phonebook1 = null;

        if (!nameValue.isEmpty()){
            //search db for relevant name
            db.findContactViaName(nameValue);
            EditText number3 = (EditText) findViewById(R.id.number);
            String numberText = String.format(PhoneBook.getTelefoonNummer());
            number3.setText(numberText);

        }else if (!numberValue.isEmpty()){
            //search db for relevant number   findContactViaNumber
            db.findContactViaNumber(numberValue2);
            EditText naam = (EditText) findViewById(R.id.naam);
            String naamText = String.format(PhoneBook.getNaam());
            naam.setText(naamText);


        }else{
            //notification
            notification = "Please insert a name or a number";
            Toast.makeText(this,notification, Toast.LENGTH_LONG).show();
        }
    }
}
