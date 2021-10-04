package com.example.thefixer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class DiviceDetailActivity extends AppCompatActivity {

    TextView txtDeviceDetailTitle;
    ImageView imgDevice1, imgCameraDevice1, imgDevice2, imgCameraDevice2, imgDevice3, imgCameraDevice3;
    EditText edtProblem;
    private ProgressDialog myProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divice_detail_activity);
        setTitle("Describe your problem");
        Intent intent = this.getIntent();
        String userInfo = intent.getStringExtra("USER_INFO");
        String userDevice = intent.getStringExtra("USER_DEVICE");

        txtDeviceDetailTitle = findViewById(R.id.txtDeviceDetailTitle);
        String username = userInfo.split("-")[1];
        txtDeviceDetailTitle.setText("Hello " +  username+", let describe your " + userDevice.toLowerCase(Locale.ROOT) + "'s problem");


        imgDevice1 = findViewById(R.id.imgDevice1);
        imgCameraDevice1 = findViewById(R.id.imgCameraDevice1);
        imgDevice2 = findViewById(R.id.imgDevice2);
        imgCameraDevice2 = findViewById(R.id.imgCameraDevice2);
        imgDevice3 = findViewById(R.id.imgDevice3);
        imgCameraDevice3 = findViewById(R.id.imgCameraDevice3);

    }

    public void clickToUploadImage(View view) {
        int imageNum = Integer.parseInt(view.getTag().toString());
        ImagePicker.with(DiviceDetailActivity.this)
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start(imageNum);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                Uri uri1 = data.getData();
                imgDevice1.setImageURI(uri1);
                break;
            case 2:
                Uri uri2 = data.getData();
                imgDevice2.setImageURI(uri2);
                break;
            default:
                Uri uri3 = data.getData();
                imgDevice3.setImageURI(uri3);
                break;
        }
    }

    public void clickToSaveDeviceInfo(View view) {
        myProgress = new ProgressDialog(DiviceDetailActivity.this);
        myProgress.setMessage("Loading...");
        myProgress.setTitle("Please wait...");
        myProgress.setProgressStyle(myProgress.STYLE_HORIZONTAL);
        myProgress.setProgress(0);
        myProgress.setMax(10);
        myProgress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (myProgress.getProgress() < myProgress.getMax()) {
                        Thread.sleep(1000);
                        System.out.println("runningggg");
                        handler.sendMessage(handler.obtainMessage());
                    }

                    if (myProgress.getProgress() >= myProgress.getMax()) {
                        myProgress.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Intent intent = new Intent(this, MapActivity.class);
        edtProblem = findViewById(R.id.edtProblem);
        intent.putExtra("Problem", edtProblem.getText().toString());
        startActivity(intent);
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            myProgress.incrementProgressBy(1);
        }
    };
}