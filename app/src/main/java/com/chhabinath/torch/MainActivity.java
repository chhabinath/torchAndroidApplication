package com.chhabinath.torch;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch aSwitch;
    TextView textView;
    CameraManager cameraManager;
    String cameraId;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = findViewById(R.id.mySwitchId);
        textView = findViewById(R.id.myTextViewId);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //flash light code

                torch(isChecked);
            }
        });
    }

    private void torch(boolean isChecked)  {

        try {
            cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId,isChecked);
            result = isChecked?"ON":"OFF";
            textView.setText(result);
        }
        catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }
}