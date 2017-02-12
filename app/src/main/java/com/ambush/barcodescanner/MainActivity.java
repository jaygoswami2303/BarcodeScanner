package com.ambush.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button scanButton;
    TextView scanFormatView;
    TextView scanContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanButton = (Button) findViewById(R.id.scan_button);
        scanFormatView = (TextView) findViewById(R.id.scan_format);
        scanContentView = (TextView) findViewById(R.id.scan_content);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setPrompt("Scan a barcode");
                integrator.setResultDisplayDuration(0);
                integrator.setWide();
                integrator.setCameraId(0);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(scanningResult!=null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            scanFormatView.setText("FORMAT: " + scanFormat);
            scanContentView.setText("CONTENT: " + scanContent);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
