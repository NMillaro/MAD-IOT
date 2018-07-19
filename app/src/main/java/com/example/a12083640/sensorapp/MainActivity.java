package com.example.a12083640.sensorapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String sensorServerURL = "http://10.0.2.2:8080/12083640NM/sensorToDB?";
    Button UDbutton;



    private static String FetchSensorData (String sensorName) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String fullURL = sensorServerURL + "getdata=true" + "&sensorname=" + sensorName;
        System.out.println("Requesting data from: "+fullURL);
        String line;
        String result = "";
        try {
            url = new URL(fullURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String SendSensorData (String sensorValue) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String fullURL = sensorServerURL + "sensorname=motor" + "&sensorvalue=" + sensorValue;
        System.out.println("Sending data to: "+fullURL);
        String line;
        String result = "";
        try {
            url = new URL(fullURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UDbutton = (Button) findViewById(R.id.UDbutton);
        UDbutton.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        String sensorValue = FetchSensorData("motor");
       //int IntValue = Integer.parseInt(sensorValue);

        TextView t;
        t = (TextView) findViewById(R.id.motorValue);
        t.setText(sensorValue);
        System.out.println(sensorValue);

    }

    public void onClick1(View v) {
        String sensorValue = FetchSensorData("distance");
        //int IntValue = Integer.parseInt(sensorValue);

        TextView t;
        t = (TextView) findViewById(R.id.distanceValue);
        t.setText(sensorValue);
        System.out.println(sensorValue);

    }

    public void onClick2(View v) {

        EditText t;
        t = (EditText) findViewById(R.id.sendMotorValue);
        String sensorValue = t.getText().toString();
        System.out.println(sensorValue);

        SendSensorData(sensorValue);
        //int IntValue = Integer.parseInt(sensorValue);



    }
}
