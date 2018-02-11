package com.example.android.bluetit;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA;

    public void turnBluetoothOff(View view){
        BA.disable();
        if (BA.isEnabled()){
            Toast.makeText(getApplicationContext(), "Bluetooth couldn't be disabled", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), "Bluetooth turned off", Toast.LENGTH_LONG).show();
        }
    }



    public void findDiscoverableDevices(View view){
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(intent);
    }

    public void viewPairedDevices(View view){
        Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();
        ListView pairedDevicesListView = (ListView) findViewById(R.id.pairedDevicesListView);
        ArrayList pairedDevicesArrayList = new ArrayList();

        for (BluetoothDevice bluetoothDevice:pairedDevices){
            pairedDevicesArrayList.add(bluetoothDevice.getName());

        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,pairedDevicesArrayList);
        pairedDevicesListView.setAdapter(arrayAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (BA.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Bluetooth is on", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);
            if (BA.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Bluetooth is on", Toast.LENGTH_LONG).show();
            }
        }

    }
}
