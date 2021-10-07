package com.example.thefixer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DiviceDetailActivity extends AppCompatActivity {

    private TextView txtDeviceDetailTitle, titleOther;
    private ImageView imgDevice1, imgCameraDevice1, imgDevice2, imgCameraDevice2, imgDevice3, imgCameraDevice3;
    private EditText edtProblem, otherOption;
    private Spinner spinnerDevice, spinnerProblem;
    private LinearLayout llListDevice, llListProblem;
    private String selected_sp, userDevice, userInfo;
    private ProgressDialog myProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divice_detail_activity);
        setTitle("Describe your problem");
        Intent intent = this.getIntent();
        userInfo = intent.getStringExtra("USER_INFO");
        userDevice = intent.getStringExtra("USER_DEVICE");

        txtDeviceDetailTitle = findViewById(R.id.txtDeviceDetailTitle);
        String username = userInfo.split("-")[1];
        txtDeviceDetailTitle.setText("Hello " + username + ", let describe your problem");

        llListDevice = findViewById(R.id.llListDevice);
        llListProblem = findViewById(R.id.llListProblem);

        otherOption = new EditText(this);
        otherOption.setTextSize(18);
        otherOption.setGravity(Gravity.CENTER);

        titleOther = new TextView(this);
        titleOther.setText("Input your device");
        titleOther.setTextSize(18);
        titleOther.setGravity(Gravity.CENTER);

        imgDevice1 = findViewById(R.id.imgDevice1);
        imgCameraDevice1 = findViewById(R.id.imgCameraDevice1);
        imgDevice2 = findViewById(R.id.imgDevice2);
        imgCameraDevice2 = findViewById(R.id.imgCameraDevice2);
        imgDevice3 = findViewById(R.id.imgDevice3);
        imgCameraDevice3 = findViewById(R.id.imgCameraDevice3);

        spinnerDevice = findViewById(R.id.spinnerDevice);

        List<String> listDevice = new ArrayList<>();
        List<String> listDeviceProblem = new ArrayList<>();
        listDeviceProblem.add("--Choose Problem--");
        switch (userDevice) {
            case "Electronic":
                listDevice.add("Điều hòa");
                listDevice.add("Tivi");
                listDevice.add("Bình nóng lạnh");
                listDevice.add("Máy giặt");
                listDevice.add("Tủ lạnh");
                listDevice.add("Máy lọc nước");
                listDevice.add("Lò vi sóng / Lò nướng");
                listDevice.add("Khác");
                break;
            case "Vehicle":
                listDevice.add("Xe ôtô con");
                listDevice.add("Xe máy");
                listDevice.add("Xe đạp");
                listDevice.add("Khác");
                break;
            case "Computer":
                listDevice.add("Máy bàn");
                listDevice.add("Laptop");
                listDevice.add("Khác");
                break;
            case "Security":
                listDevice.add("Camera");
                listDevice.add("Còi chống trộm");
                listDevice.add("Khác");
                break;
            case "Plumber":
                listDevice.add("Ống nước");
                listDevice.add("Nguồn nước");
                listDevice.add("Máy bơm");
                listDevice.add("Khác");
                break;
            case "Mobile":
                listDevice.add("Điện thoại");
                listDevice.add("Máy tính bảng");
                listDevice.add("Khác");
                break;
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDevice);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDevice.setAdapter(dataAdapter);
        spinnerDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selected_sp = spinnerDevice.getSelectedItem().toString();
                if (selected_sp.equals("Khác")) {
                    llListDevice.addView(titleOther);
                    llListDevice.addView(otherOption);
                    listDeviceProblem.clear();
                }
                else {

                    switch (selected_sp){
                        case "Điều hòa":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Bảo dưỡng & Vệ sinh máy");
                            listDeviceProblem.add("Nạp ga điều hòa");
                            listDeviceProblem.add("Tháo, lắp, di chuyển điều hòa");
                            listDeviceProblem.add("Bật không lên");
                            listDeviceProblem.add("Điều hòa kêu to");
                            listDeviceProblem.add("Chập điện, có mùi khét");
                            listDeviceProblem.add("Lắp chuyển hướng gió cục nóng");
                            listDeviceProblem.add("Điều hòa không mát");
                            listDeviceProblem.add("Điều hòa chảy nước");
                            break;
                        case "Tivi":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Thay màn hình tivi bị vỡ");
                            listDeviceProblem.add("Sọc màn hình Tivi");
                            listDeviceProblem.add("Bóng mây/ Bóng mờ trên màn hình");
                            listDeviceProblem.add("Không vào điện");
                            listDeviceProblem.add("Nhiễu, mất tín hiệu");
                            listDeviceProblem.add("Có mùi khét, nghi chập điện");
                            listDeviceProblem.add("Tivi kêu to bất thường");
                            break;
                        case "Bình nóng lạnh":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Súc/ rửa Bình nước nóng");
                            listDeviceProblem.add("Rò nước từ bình");
                            listDeviceProblem.add("Bình không ra nước nóng");
                            listDeviceProblem.add("Rò điện từ bình nước nóng");
                            listDeviceProblem.add("Tháo/ Lắp bình nước nóng");
                            break;
                        case "Máy giặt":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Bảo dưỡng/ Vệ sinh máy");
                            listDeviceProblem.add("Máy báo lỗi");
                            listDeviceProblem.add("Có mùi khét, nghi chập điện");
                            listDeviceProblem.add("Máy kêu to bất thường");
                            listDeviceProblem.add("Không vào điện");
                            listDeviceProblem.add("Máy giặt chảy nước");
                            listDeviceProblem.add("Thủng/ rách gioăng cửa máy");
                            break;
                        case "Tủ lạnh":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Tủ không vào điện");
                            listDeviceProblem.add("Tủ chảy nước");
                            listDeviceProblem.add("Tủ không đủ lạnh");
                            listDeviceProblem.add("Có mùi khét, nghi chập điện");
                            listDeviceProblem.add("Tủ kêu to bất thường");
                            listDeviceProblem.add("Thay gioăng cảnh cửa tủ");
                            break;
                        case "Máy lọc nước":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Máy lọc không ra nước");
                            listDeviceProblem.add("Máy ra nước chậm");
                            listDeviceProblem.add("Nước thải ra liên tục");
                            listDeviceProblem.add("Thay lõi lọc");
                            listDeviceProblem.add("Thay lõi lọc RO");
                            break;
                        case "Lò vi sóng / Lò nướng":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Lò không vào điện");
                            listDeviceProblem.add("Lò không không hoạt động đúng");
                            break;
                        case "Xe ôtô con":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Thay bánh xe");
                            listDeviceProblem.add("Vá bánh xe");
                            listDeviceProblem.add("Rửa xe");
                            listDeviceProblem.add("Thay nhớt");
                            listDeviceProblem.add("Thay bình");
                            listDeviceProblem.add("Sạc bình");
                            listDeviceProblem.add("Cứu hộ tại chỗ");
                            break;
                        case "Xe máy":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Thay bánh xe");
                            listDeviceProblem.add("Rửa xe");
                            listDeviceProblem.add("Vá bánh xe");
                            listDeviceProblem.add("Thay nhớt");
                            listDeviceProblem.add("Sạc bình");
                            listDeviceProblem.add("Thay bình");
                            listDeviceProblem.add("Cứu hộ tại chỗ");
                            break;
                        case "Xe đạp":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Thay bánh xe");
                            listDeviceProblem.add("Vá bánh xe");
                            listDeviceProblem.add("Cứu hộ tại chỗ");
                            listDeviceProblem.add("Sửa dây xích");
                            break;
                    }
                    SpinnerProblemUI(listDeviceProblem);
                    llListDevice.removeView(titleOther);
                    llListDevice.removeView(otherOption);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void SpinnerProblemUI(List<String> listDeviceProblem){
        llListProblem.removeAllViews();
        Spinner spinnerProblem = new Spinner(this);
        ArrayAdapter<String> dataAdapterProblem = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDeviceProblem);
        dataAdapterProblem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProblem.setAdapter(dataAdapterProblem);
        spinnerProblem.setGravity(Gravity.CENTER);
        llListProblem.addView(spinnerProblem);
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
        switch (requestCode) {
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
        intent.putExtra("USER_DEVICE", userDevice);
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