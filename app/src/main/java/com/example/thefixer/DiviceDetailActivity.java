package com.example.thefixer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

//import net.alhazmy13.mediapicker.Image.ImagePicker;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.khizar1556.mkvideoplayer.MKPlayerActivity;

import net.alhazmy13.mediapicker.Video.VideoPicker;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DiviceDetailActivity extends AppCompatActivity {

    private TextView txtDeviceDetailTitle, titleOther, txtSaveDeviceInfo;
    private EditText edtProblem, otherOption;
    private Spinner spinnerDevice;
    private LinearLayout llListDevice, llListProblem, llDevideInfo, llMediaContain;
    private String selected_sp, userDevice, userInfo, device, problem;
    private ProgressDialog myProgress;
    private int VIDEO_PICKER_REQUEST_CODE = 6670;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divice_detail_activity);
        setTitle("Describe your problem");
        Intent intent = this.getIntent();
        userInfo = intent.getStringExtra("USER_INFO");
        userDevice = intent.getStringExtra("USER_DEVICE");

        txtSaveDeviceInfo = findViewById(R.id.txtSaveDeviceInfo);
        llDevideInfo = findViewById(R.id.llDevideInfo);

        llMediaContain = findViewById(R.id.llMediaContain);


        txtDeviceDetailTitle = findViewById(R.id.txtDeviceDetailTitle);
        String username = userInfo.split("-")[1];
        txtDeviceDetailTitle.setText("Hello " + username + ", let describe your problem");

        llListDevice = findViewById(R.id.llListDevice);
        llListProblem = findViewById(R.id.llListProblem);

        otherOption = new EditText(this);
        otherOption.setTextSize(18);
        otherOption.setGravity(Gravity.CENTER);
        otherOption.setHint("Input device here...");

        titleOther = new TextView(this);
        titleOther.setText("Input your device");
        titleOther.setTextSize(18);
        titleOther.setGravity(Gravity.CENTER);

        spinnerDevice = findViewById(R.id.spinnerDevice);

        List<String> listDevice = new ArrayList<>();
        List<String> listDeviceProblem = new ArrayList<>();
        listDeviceProblem.add("--Choose Problem--");
        switch (userDevice) {
            case "Electronic":
                txtSaveDeviceInfo.setBackgroundTintList(getResources().getColorStateList(R.color.md_yellow_400));
                Drawable dEle = getResources().getDrawable(R.drawable.border_linearlayout_electronic);
                llDevideInfo.setBackgroundDrawable(dEle);

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
                txtSaveDeviceInfo.setBackgroundTintList(getResources().getColorStateList(R.color.md_blue_100));
                Drawable dVeh = getResources().getDrawable(R.drawable.border_linearlayout_vehicle);
                llDevideInfo.setBackgroundDrawable(dVeh);

                listDevice.add("Xe ôtô con");
                listDevice.add("Xe máy");
                listDevice.add("Xe đạp");
                listDevice.add("Khác");
                break;
            case "Computer":
                txtSaveDeviceInfo.setBackgroundTintList(getResources().getColorStateList(R.color.green_lighter));
                Drawable dCom = getResources().getDrawable(R.drawable.border_linearlayout_computer);
                llDevideInfo.setBackgroundDrawable(dCom);

                listDevice.add("Máy bàn");
                listDevice.add("Laptop");
                listDevice.add("Khác");
                break;
            case "Security":
                txtSaveDeviceInfo.setBackgroundTintList(getResources().getColorStateList(R.color.md_red_300));
                Drawable dSec = getResources().getDrawable(R.drawable.border_linearlayout_security);
                llDevideInfo.setBackgroundDrawable(dSec);

                listDevice.add("Camera");
                listDevice.add("Còi chống trộm");
                listDevice.add("Khác");
                break;
            case "Plumber":
                txtSaveDeviceInfo.setBackgroundTintList(getResources().getColorStateList(R.color.md_blue_400));
                Drawable dPlu = getResources().getDrawable(R.drawable.border_linearlayout_plumber);
                llDevideInfo.setBackgroundDrawable(dPlu);

                listDevice.add("Ống nước");
                listDevice.add("Nguồn nước");
                listDevice.add("Máy bơm");
                listDevice.add("Khác");
                break;
            case "Mobile":
                txtSaveDeviceInfo.setBackgroundTintList(getResources().getColorStateList(R.color.md_purple_200));
                Drawable dMob = getResources().getDrawable(R.drawable.border_linearlayout_mobile);
                llDevideInfo.setBackgroundDrawable(dMob);

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
                    listDeviceProblem.add("Khác");
                    SpinnerProblemUI(listDeviceProblem);
                } else {

                    switch (selected_sp) {
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
                            listDeviceProblem.add("Khác");
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
                            listDeviceProblem.add("Khác");
                            break;
                        case "Bình nóng lạnh":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Súc/ rửa Bình nước nóng");
                            listDeviceProblem.add("Rò nước từ bình");
                            listDeviceProblem.add("Bình không ra nước nóng");
                            listDeviceProblem.add("Rò điện từ bình nước nóng");
                            listDeviceProblem.add("Tháo/ Lắp bình nước nóng");
                            listDeviceProblem.add("Khác");
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
                            listDeviceProblem.add("Khác");
                            break;
                        case "Tủ lạnh":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Tủ không vào điện");
                            listDeviceProblem.add("Tủ chảy nước");
                            listDeviceProblem.add("Tủ không đủ lạnh");
                            listDeviceProblem.add("Có mùi khét, nghi chập điện");
                            listDeviceProblem.add("Tủ kêu to bất thường");
                            listDeviceProblem.add("Thay gioăng cảnh cửa tủ");
                            listDeviceProblem.add("Khác");
                            break;
                        case "Máy lọc nước":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Máy lọc không ra nước");
                            listDeviceProblem.add("Máy ra nước chậm");
                            listDeviceProblem.add("Nước thải ra liên tục");
                            listDeviceProblem.add("Thay lõi lọc");
                            listDeviceProblem.add("Thay lõi lọc RO");
                            listDeviceProblem.add("Khác");
                            break;
                        case "Lò vi sóng / Lò nướng":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Lò không vào điện");
                            listDeviceProblem.add("Lò không không hoạt động đúng");
                            listDeviceProblem.add("Khác");
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
                            listDeviceProblem.add("Khác");
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
                            listDeviceProblem.add("Khác");
                            break;
                        case "Xe đạp":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Thay bánh xe");
                            listDeviceProblem.add("Vá bánh xe");
                            listDeviceProblem.add("Cứu hộ tại chỗ");
                            listDeviceProblem.add("Sửa dây xích");
                            listDeviceProblem.add("Khác");
                            break;
                        case "Máy bàn":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Vệ sinh máy");
                            listDeviceProblem.add("Có mùi khét, nghi chập điện");
                            listDeviceProblem.add("Không lên nguồn");
                            listDeviceProblem.add("Thay linh kiện");
                            listDeviceProblem.add("Rò rỉ tản nhiệt nước");
                            listDeviceProblem.add("Tản nhiệt kêu to");
                            listDeviceProblem.add("Cài đặt hệ điều hành");
                            listDeviceProblem.add("Lỗi phần mềm");
                            listDeviceProblem.add("Khác");
                            break;
                        case "Laptop":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Vệ sinh máy");
                            listDeviceProblem.add("Có mùi khét, nghi chập điện");
                            listDeviceProblem.add("Không lên nguồn");
                            listDeviceProblem.add("Thay linh kiện");
                            listDeviceProblem.add("Sửa linh kiện (Màn hình, Camera, Loa)");
                            listDeviceProblem.add("Tản nhiệt kêu to");
                            listDeviceProblem.add("Bàn phím, touch pad không nhận");
                            listDeviceProblem.add("Cài đặt hệ điều hành");
                            listDeviceProblem.add("Lỗi phần mềm");
                            listDeviceProblem.add("Khác");
                            break;
                        case "Camera":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Mở Port");
                            listDeviceProblem.add("Mất kết nối");
                            listDeviceProblem.add("Lắp đặt camera");
                            listDeviceProblem.add("Thiết bị không hoạt đông");
                            listDeviceProblem.add("Khác");
                            break;
                        case "Còi chống trộm":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Mất âm thanh");
                            listDeviceProblem.add("Không nhận diện đối tượng");
                            listDeviceProblem.add("Mở Port");
                            listDeviceProblem.add("Lắp đặt thiết bị");
                            listDeviceProblem.add("Khác");
                            break;
                        case "Ông nước":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Rò rỉ đường ống");
                            listDeviceProblem.add("Thay, sửa đường ống");
                            listDeviceProblem.add("Vệ sinh đường ống");
                            listDeviceProblem.add("Mắc dị vật trong đường ống");
                            listDeviceProblem.add("Khác");
                            break;
                        case "Nguồn nước":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Kiểm tra nguồn nước");
                            listDeviceProblem.add("Khác");
                            break;
                        case "Máy bơm":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Có mùi khét, nghi ngờ cháy máy");
                            listDeviceProblem.add("Không lên nguồn");
                            listDeviceProblem.add("Không thể bơm nước");
                            listDeviceProblem.add("Lắp đặt máy bơm");
                            listDeviceProblem.add("Khác");
                            break;
                        case "Điện thoại":
                        case "Máy tính bảng":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Vỡ màn hình, mất cảm ứng");
                            listDeviceProblem.add("Loa không phát ra âm thanh");
                            listDeviceProblem.add("Không lên nguồn");
                            listDeviceProblem.add("Khôi phục dữ liệu");
                            listDeviceProblem.add("Gắn cường lực, chống trầy");
                            listDeviceProblem.add("Không nhận sim");
                            listDeviceProblem.add("Máy phình to");
                            listDeviceProblem.add("Khác");
                            break;
                        default:
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Khác");
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

    public void SpinnerProblemUI(List<String> listDeviceProblem) {
        llListProblem.removeAllViews();
        Spinner spinnerProblem = new Spinner(this);
        ArrayAdapter<String> dataAdapterProblem = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDeviceProblem);
        dataAdapterProblem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProblem.setAdapter(dataAdapterProblem);
        spinnerProblem.setGravity(Gravity.CENTER);
        spinnerProblem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                problem = spinnerProblem.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        llListProblem.addView(spinnerProblem);
    }

    public void clickToSaveDeviceInfo(View view) {
        myProgress = new ProgressDialog(DiviceDetailActivity.this);
        myProgress.setMessage("Đang lưu dữ liệu...");
        myProgress.setTitle("Vui lòng chờ xí...");
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
        intent.putExtra("PROBLEM", problem);
        intent.putExtra("SERVICES", userDevice);
        intent.putExtra("DEVICE", selected_sp);
        startActivity(intent);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            myProgress.incrementProgressBy(1);
        }
    };

    public void clickToUploadImage(View view) {
        ImagePicker.with(DiviceDetailActivity.this)
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start(VIDEO_PICKER_REQUEST_CODE);
    }

    public void clickToLoadVideo(View view) {
        new VideoPicker.Builder(this)
                .mode(VideoPicker.Mode.GALLERY)
                .directory(VideoPicker.Directory.DEFAULT)
                .extension(VideoPicker.Extension.MP4)
                .enableDebuggingMode(true)
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VideoPicker.VIDEO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths =  data.getStringArrayListExtra(VideoPicker.EXTRA_VIDEO_PATH);
            //Your Code
            VideoView vid1 = new VideoView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(300,300);
            lp.setMargins(5,0,5,0);
            vid1.setLayoutParams(lp);
            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) vid1.getLayoutParams();
            marginParams.setMargins(5, 0, 5, 0);
            vid1.setLayoutParams(marginParams);
//            String videoPath = "android.resource://com.example.thefixer/" + R.raw.test;
            String videoPath = "/storage/emulated/0/Download/sample-mp4-file.mp4";
//            Uri uri = Uri.parse(videoPath);
            Uri uri =Uri.fromFile(new File(videoPath));
            vid1.setVideoURI(uri);
//            vid1.setVideoPath(mPaths.get(0));
            TextView test = new TextView(this);
            test.setLayoutParams(lp);
            test.setText(videoPath);
            vid1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vid1.start();
                }
            });
            llMediaContain.addView(vid1);
//            llMediaContain.addView(test);
//            MKPlayerActivity.configPlayer(this).play(mPaths.get(0));
        }

        if (requestCode == VIDEO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            ImageView img1 = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(300,300);
            lp.setMargins(5,0,5,30);
            img1.setLayoutParams(lp);
            Uri uri1 = data.getData();
            img1.setImageURI(uri1);
            llMediaContain.addView(img1);
        }
    }
}