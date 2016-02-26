package com.example.kevinjiang.week2projectv2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private TextView text1;
    private EditText edit1;
    private boolean bold;

    public static final String SAVE = "savedText";

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getFragmentManager().beginTransaction().replace(android.R.id.content,new PreferencedScreenFragment()).commit();

        button1 = (Button) findViewById(R.id.button1);
        text1 = (TextView) findViewById(R.id.text1);
        edit1 = (EditText) findViewById(R.id.edit1);

        //text1.setText(preferences.getString(SAVE, "error"));

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if(preferences.getBoolean("bold",false)) {
            text1.setTypeface(Typeface.DEFAULT_BOLD);
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit1.getText().toString().equals("")) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setMessage("Error: Text field cannot be left blank.");

                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else {
                    text1.setVisibility(View.VISIBLE);
                    edit1.setVisibility(View.GONE);
                    button1.setVisibility(View.GONE);
                    String savedText = edit1.getText().toString();

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString(SAVE, savedText);
                    editor.commit();

                    text1.setText(preferences.getString(SAVE, "error"));
                    if(preferences.getBoolean("bold",false)) {
                        text1.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.reset:

                //http://www.tutorialspoint.com/android/android_alert_dialoges.htm
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you sure you want to erase saved text?");

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        text1.setVisibility(View.GONE);
                        edit1.setVisibility(View.VISIBLE);
                        button1.setVisibility(View.VISIBLE);
                        edit1.setText("");
                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
