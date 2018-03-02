package com.example.devar.bustracking;

import android.Manifest;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;
class ExampleTask extends TimerTask {

    @Override
    public void run() {

        assert true;


    }
}

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    String bus_node;
    Button btnShowLocation;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    int tempCount = 0;
    GPSTracker gps;
    TextView location;
    TextView count;
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_18:
                if (checked)
                    // Pirates are the best
                    bus_node = "bus_18";
                    break;
            case R.id.radio_17:
                if (checked)
                    // Ninjas rule
                    bus_node = "bus_17";
                    break;
        }
    }
    public void sendLocation(final View view)
    {

        gps = new GPSTracker(MainActivity.this);
        location = (TextView) findViewById(R.id.locationText);
        count = (TextView) findViewById(R.id.countText);



        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            database = FirebaseDatabase.getInstance();
            location.setText(latitude + "::" + longitude);
            count.setText(" " + tempCount);
            myRef = database.getReference(bus_node);
            myRef.setValue(latitude+", "+ longitude);
            tempCount++;



        }
        else {
            gps.showSettingsAlert();
        }
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                // This method will be executed in the future
                location.setText("Done with 5 seconds");
                sendLocation(view);
                //assert true;
            }
        };
        Handler handler = new Handler();

// Execute the Runnable in 500 milliseconds
        handler.postDelayed(runnable, 1000);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            if(ActivityCompat.checkSelfPermission(this, mPermission)!= MockPackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{mPermission}, REQUEST_CODE_PERMISSION);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        btnShowLocation = (Button) findViewById(R.id.button);

        /*
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //final View v = view;

                gps = new GPSTracker(MainActivity.this);
                location = (TextView) findViewById(R.id.locationText);

                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    database = FirebaseDatabase.getInstance();
                    location.setText(latitude + ":" + longitude);
                    myRef = database.getReference("Location");
                    myRef.setValue(latitude+", "+ longitude);

                }
                else
                    gps.showSettingsAlert();

                   // Timer timer = new Timer();
                    //ExampleTask task = new ExampleTask();

// Executes the task in 500 milliseconds
                    //timer.schedule(task, 3000);

                    //onClick(view);
            }
        });
        */

    }

}
