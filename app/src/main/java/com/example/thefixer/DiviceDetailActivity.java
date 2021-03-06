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

                listDevice.add("??i???u h??a");
                listDevice.add("Tivi");
                listDevice.add("B??nh n??ng l???nh");
                listDevice.add("M??y gi???t");
                listDevice.add("T??? l???nh");
                listDevice.add("M??y l???c n?????c");
                listDevice.add("L?? vi s??ng / L?? n?????ng");
                listDevice.add("Kh??c");
                break;
            case "Vehicle":
                txtSaveDeviceInfo.setBackgroundTintList(getResources().getColorStateList(R.color.md_blue_100));
                Drawable dVeh = getResources().getDrawable(R.drawable.border_linearlayout_vehicle);
                llDevideInfo.setBackgroundDrawable(dVeh);

                listDevice.add("Xe ??t?? con");
                listDevice.add("Xe m??y");
                listDevice.add("Xe ?????p");
                listDevice.add("Kh??c");
                break;
            case "Computer":
                txtSaveDeviceInfo.setBackgroundTintList(getResources().getColorStateList(R.color.green_lighter));
                Drawable dCom = getResources().getDrawable(R.drawable.border_linearlayout_computer);
                llDevideInfo.setBackgroundDrawable(dCom);

                listDevice.add("M??y b??n");
                listDevice.add("Laptop");
                listDevice.add("Kh??c");
                break;
            case "Security":
                txtSaveDeviceInfo.setBackgroundTintList(getResources().getColorStateList(R.color.md_red_300));
                Drawable dSec = getResources().getDrawable(R.drawable.border_linearlayout_security);
                llDevideInfo.setBackgroundDrawable(dSec);

                listDevice.add("Camera");
                listDevice.add("C??i ch???ng tr???m");
                listDevice.add("Kh??c");
                break;
            case "Plumber":
                txtSaveDeviceInfo.setBackgroundTintList(getResources().getColorStateList(R.color.md_blue_400));
                Drawable dPlu = getResources().getDrawable(R.drawable.border_linearlayout_plumber);
                llDevideInfo.setBackgroundDrawable(dPlu);

                listDevice.add("???ng n?????c");
                listDevice.add("Ngu???n n?????c");
                listDevice.add("M??y b??m");
                listDevice.add("Kh??c");
                break;
            case "Mobile":
                txtSaveDeviceInfo.setBackgroundTintList(getResources().getColorStateList(R.color.md_purple_200));
                Drawable dMob = getResources().getDrawable(R.drawable.border_linearlayout_mobile);
                llDevideInfo.setBackgroundDrawable(dMob);

                listDevice.add("??i???n tho???i");
                listDevice.add("M??y t??nh b???ng");
                listDevice.add("Kh??c");
                break;
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDevice);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDevice.setAdapter(dataAdapter);
        spinnerDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selected_sp = spinnerDevice.getSelectedItem().toString();
                if (selected_sp.equals("Kh??c")) {
                    llListDevice.addView(titleOther);
                    llListDevice.addView(otherOption);
                    listDeviceProblem.clear();
                    listDeviceProblem.add("Kh??c");
                    SpinnerProblemUI(listDeviceProblem);
                } else {

                    switch (selected_sp) {
                        case "??i???u h??a":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("B???o d?????ng & V??? sinh m??y");
                            listDeviceProblem.add("N???p ga ??i???u h??a");
                            listDeviceProblem.add("Th??o, l???p, di chuy???n ??i???u h??a");
                            listDeviceProblem.add("B???t kh??ng l??n");
                            listDeviceProblem.add("??i???u h??a k??u to");
                            listDeviceProblem.add("Ch???p ??i???n, c?? m??i kh??t");
                            listDeviceProblem.add("L???p chuy???n h?????ng gi?? c???c n??ng");
                            listDeviceProblem.add("??i???u h??a kh??ng m??t");
                            listDeviceProblem.add("??i???u h??a ch???y n?????c");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "Tivi":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Thay m??n h??nh tivi b??? v???");
                            listDeviceProblem.add("S???c m??n h??nh Tivi");
                            listDeviceProblem.add("B??ng m??y/ B??ng m??? tr??n m??n h??nh");
                            listDeviceProblem.add("Kh??ng v??o ??i???n");
                            listDeviceProblem.add("Nhi???u, m???t t??n hi???u");
                            listDeviceProblem.add("C?? m??i kh??t, nghi ch???p ??i???n");
                            listDeviceProblem.add("Tivi k??u to b???t th?????ng");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "B??nh n??ng l???nh":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("S??c/ r???a B??nh n?????c n??ng");
                            listDeviceProblem.add("R?? n?????c t??? b??nh");
                            listDeviceProblem.add("B??nh kh??ng ra n?????c n??ng");
                            listDeviceProblem.add("R?? ??i???n t??? b??nh n?????c n??ng");
                            listDeviceProblem.add("Th??o/ L???p b??nh n?????c n??ng");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "M??y gi???t":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("B???o d?????ng/ V??? sinh m??y");
                            listDeviceProblem.add("M??y b??o l???i");
                            listDeviceProblem.add("C?? m??i kh??t, nghi ch???p ??i???n");
                            listDeviceProblem.add("M??y k??u to b???t th?????ng");
                            listDeviceProblem.add("Kh??ng v??o ??i???n");
                            listDeviceProblem.add("M??y gi???t ch???y n?????c");
                            listDeviceProblem.add("Th???ng/ r??ch gio??ng c???a m??y");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "T??? l???nh":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("T??? kh??ng v??o ??i???n");
                            listDeviceProblem.add("T??? ch???y n?????c");
                            listDeviceProblem.add("T??? kh??ng ????? l???nh");
                            listDeviceProblem.add("C?? m??i kh??t, nghi ch???p ??i???n");
                            listDeviceProblem.add("T??? k??u to b???t th?????ng");
                            listDeviceProblem.add("Thay gio??ng c???nh c???a t???");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "M??y l???c n?????c":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("M??y l???c kh??ng ra n?????c");
                            listDeviceProblem.add("M??y ra n?????c ch???m");
                            listDeviceProblem.add("N?????c th???i ra li??n t???c");
                            listDeviceProblem.add("Thay l??i l???c");
                            listDeviceProblem.add("Thay l??i l???c RO");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "L?? vi s??ng / L?? n?????ng":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("L?? kh??ng v??o ??i???n");
                            listDeviceProblem.add("L?? kh??ng kh??ng ho???t ?????ng ????ng");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "Xe ??t?? con":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Thay b??nh xe");
                            listDeviceProblem.add("V?? b??nh xe");
                            listDeviceProblem.add("R???a xe");
                            listDeviceProblem.add("Thay nh???t");
                            listDeviceProblem.add("Thay b??nh");
                            listDeviceProblem.add("S???c b??nh");
                            listDeviceProblem.add("C???u h??? t???i ch???");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "Xe m??y":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Thay b??nh xe");
                            listDeviceProblem.add("R???a xe");
                            listDeviceProblem.add("V?? b??nh xe");
                            listDeviceProblem.add("Thay nh???t");
                            listDeviceProblem.add("S???c b??nh");
                            listDeviceProblem.add("Thay b??nh");
                            listDeviceProblem.add("C???u h??? t???i ch???");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "Xe ?????p":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Thay b??nh xe");
                            listDeviceProblem.add("V?? b??nh xe");
                            listDeviceProblem.add("C???u h??? t???i ch???");
                            listDeviceProblem.add("S???a d??y x??ch");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "M??y b??n":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("V??? sinh m??y");
                            listDeviceProblem.add("C?? m??i kh??t, nghi ch???p ??i???n");
                            listDeviceProblem.add("Kh??ng l??n ngu???n");
                            listDeviceProblem.add("Thay linh ki???n");
                            listDeviceProblem.add("R?? r??? t???n nhi???t n?????c");
                            listDeviceProblem.add("T???n nhi???t k??u to");
                            listDeviceProblem.add("C??i ?????t h??? ??i???u h??nh");
                            listDeviceProblem.add("L???i ph???n m???m");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "Laptop":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("V??? sinh m??y");
                            listDeviceProblem.add("C?? m??i kh??t, nghi ch???p ??i???n");
                            listDeviceProblem.add("Kh??ng l??n ngu???n");
                            listDeviceProblem.add("Thay linh ki???n");
                            listDeviceProblem.add("S???a linh ki???n (M??n h??nh, Camera, Loa)");
                            listDeviceProblem.add("T???n nhi???t k??u to");
                            listDeviceProblem.add("B??n ph??m, touch pad kh??ng nh???n");
                            listDeviceProblem.add("C??i ?????t h??? ??i???u h??nh");
                            listDeviceProblem.add("L???i ph???n m???m");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "Camera":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("M??? Port");
                            listDeviceProblem.add("M???t k???t n???i");
                            listDeviceProblem.add("L???p ?????t camera");
                            listDeviceProblem.add("Thi???t b??? kh??ng ho???t ????ng");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "C??i ch???ng tr???m":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("M???t ??m thanh");
                            listDeviceProblem.add("Kh??ng nh???n di???n ?????i t?????ng");
                            listDeviceProblem.add("M??? Port");
                            listDeviceProblem.add("L???p ?????t thi???t b???");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "??ng n?????c":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("R?? r??? ???????ng ???ng");
                            listDeviceProblem.add("Thay, s???a ???????ng ???ng");
                            listDeviceProblem.add("V??? sinh ???????ng ???ng");
                            listDeviceProblem.add("M???c d??? v???t trong ???????ng ???ng");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "Ngu???n n?????c":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Ki???m tra ngu???n n?????c");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "M??y b??m":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("C?? m??i kh??t, nghi ng??? ch??y m??y");
                            listDeviceProblem.add("Kh??ng l??n ngu???n");
                            listDeviceProblem.add("Kh??ng th??? b??m n?????c");
                            listDeviceProblem.add("L???p ?????t m??y b??m");
                            listDeviceProblem.add("Kh??c");
                            break;
                        case "??i???n tho???i":
                        case "M??y t??nh b???ng":
                            listDeviceProblem.clear();
                            listDeviceProblem.add("V??? m??n h??nh, m???t c???m ???ng");
                            listDeviceProblem.add("Loa kh??ng ph??t ra ??m thanh");
                            listDeviceProblem.add("Kh??ng l??n ngu???n");
                            listDeviceProblem.add("Kh??i ph???c d??? li???u");
                            listDeviceProblem.add("G???n c?????ng l???c, ch???ng tr???y");
                            listDeviceProblem.add("Kh??ng nh???n sim");
                            listDeviceProblem.add("M??y ph??nh to");
                            listDeviceProblem.add("Kh??c");
                            break;
                        default:
                            listDeviceProblem.clear();
                            listDeviceProblem.add("Kh??c");
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
        myProgress.setMessage("??ang l??u d??? li???u...");
        myProgress.setTitle("Vui l??ng ch??? x??...");
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