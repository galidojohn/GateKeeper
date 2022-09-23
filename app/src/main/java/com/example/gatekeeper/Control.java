package com.example.gatekeeper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;
import android.os.Handler;


import com.google.android.material.button.MaterialButton;

public class Control extends AppCompatActivity {

    private final String DEVICE_ADRESS = "";
    private final UUID PORT_UUID = UUID.fromString("");

    private BluetoothDevice device;
    private BluetoothSocket socket;

    private OutputStream outputStream;
    private InputStream inputStream;

    Thread thread;
    byte buffer[];

    boolean stopThread;
    boolean connected = false;
    String command;

    Button lockbtn, bluetooth_connect_btn;
    TextView lockstate;
    ImageView lockstate_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        bluetooth_connect_btn = (MaterialButton) findViewById(R.id.bluetooth_connect_btn);
        lockbtn = (MaterialButton) findViewById(R.id.lockbtn);
        lockstate = (TextView) findViewById(R.id.lockstate);
        //lockstate_img = (ImageView) findViewById(R.id.lockstate_img);

        bluetooth_connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (BTinit()) {
                    BTconnect();
                    beginListenForData();
                    command = "3";

                    try {
                        outputStream.write(command.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        lockbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connected == false) {
                    Toast.makeText(getApplicationContext(), "Please connect to the doorlock first", Toast.LENGTH_SHORT).show();
                } else {
                    command = "1";
                }
                try {
                    outputStream.write(command.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    void beginListenForData(){
        final Handler handler = new Handler();
        stopThread = false;
        buffer = new byte[1024];

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (Thread.currentThread().isInterrupted() && stopThread) {
                }
                try {
                    int byteCount = inputStream.available();

                    if (byteCount > 0) {
                        byte[] rawBytes = new byte[byteCount];
                        inputStream.read(rawBytes);
                        final String string = new String(rawBytes, "UTF-8");

                        boolean post = handler.post(new Runnable() {
                            @Override
                            public void run() {
                            }
                            if(string.)

                            {
                                lockstate.setText("Lock State: Door is Locked");
                                //lockstate_img.setImageResource(R.drawable.locked_icon);
                            }
                            else if(string.equals("4"))

                            {
                                lockstate.setText("Lock State: Door is Unlocked");
                                //lockstate_img.setImageResource(R.drawable.unlocked_icon);
                            }
                        });
                    }
                } catch (IOException e) {
                    stopThread = true;
                }
            }
        });
        thread.start();
    }
    public boolean BTinit(){
        boolean found = false;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter == null){
            Toast.makeText(getApplicationContext(),"Device doesn't support bluetooth",Toast.LENGTH_SHORT).show();
        }
        if(bluetoothAdapter.isEnabled()){
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter,0);

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Set<BluetoothDevice>bondedDevices = bluetoothAdapter.getBondedDevices();

        if(bondedDevices.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please pair to the device via Bluetooth", Toast.LENGTH_SHORT).show();
        }
        else{
            for (BluetoothDevice iteraror: bondedDevices){
                if (iteraror.getAddress().equals(DEVICE_ADRESS)){
                    device = iteraror;
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
    public boolean BTconnect(){
        try {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID);
            socket.connect();
            Toast.makeText(getApplicationContext(), "Connection success", Toast.LENGTH_SHORT).show();
            connected = true;
        }
        catch (IOException e){
            e.printStackTrace();
            connected = false;
        }
        if (connected){
            try {
                outputStream = socket.getOutputStream();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            try {
                inputStream = socket.getInputStream();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return connected;
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
}