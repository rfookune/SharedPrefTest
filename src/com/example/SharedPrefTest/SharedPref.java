package com.example.SharedPrefTest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPref extends Activity implements View.OnClickListener {

    private EditText name;
    private EditText phone;
    private TextView nameResult;
    private TextView phoneResult;

    //    Where to save the data
    public static String filename = "MyName";
    public static String phoneFile = "MyPhoneNumber";

    SharedPreferences nameData;
    SharedPreferences phoneData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupVariables();

        nameData = getSharedPreferences(filename, 0);
        phoneData = getSharedPreferences(phoneFile, 0);

    }

    //  Introduce all the variables used in the activity and link it to the layout button
    public void setupVariables(){

        Button save = (Button) findViewById(R.id.saveButton);
        Button load = (Button) findViewById(R.id.loadButton);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.number);
        nameResult = (TextView) findViewById(R.id.displayName);
        phoneResult = (TextView) findViewById(R.id.displayNumber);

        save.setOnClickListener(this);
        load.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch ( (view.getId())){
            case R.id.saveButton:
                save();
                break;

            case R.id.loadButton:
                load();
                break;

        }
    }

    public void save(){

        //  Save name
        String stringData1 = name.getText().toString();
        SharedPreferences.Editor nameEditor = nameData.edit();
        nameEditor.putString("sharedName", stringData1);
        nameEditor.commit();
        //  Save phone number
        String stringData2 = phone.getText().toString();
        SharedPreferences.Editor phoneEditor = phoneData.edit();
        phoneEditor.putString("sharedNumber", stringData2);
        phoneEditor.commit();
    }

    public void load(){

        //  Load Name and display
        nameData = getSharedPreferences(filename, 0);
        String nameReturned = nameData.getString("sharedName", "Couldn't load data");
        nameResult.setText("Your name is: " + nameReturned);
        //  Load Phone Number and display
        phoneData = getSharedPreferences(phoneFile, 0);
        String numberReturned = phoneData.getString("sharedNumber", "Couldn't load data");
        phoneResult.setText("Your phone number is: " + numberReturned);
    }

}
