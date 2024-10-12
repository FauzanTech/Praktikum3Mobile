package com.example.praktikum3;

import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class home extends AppCompatActivity {


    private ImageView imageView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private Uri photoURI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView logoutBtn = findViewById(R.id.logout1);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, MainActivity.class));
            }
        });


        TextView nim = findViewById(R.id.textView6);
        Intent intent = getIntent();
        String pesan = intent.getStringExtra("nama");
        if (pesan == null || intent.getStringExtra("nama").equals("")) {
            Toast.makeText(home.this, "Tidak ada data NIM", Toast.LENGTH_LONG).show();
        } else {
            nim.setText(pesan);
        }

        Button web, share, kamera;
        web = findViewById(R.id.button2);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://simpel.ith.ac.id/login/index.php"));
                startActivity(intent_web);
            }
        });

        share = findViewById(R.id.button3);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent.getStringExtra("nim_user").equals("")) {
                    Toast.makeText(home.this, "Tidak ada data NIM", Toast.LENGTH_LONG).show();
                } else {
                    nim.setText(pesan);

                    Intent intent_share = new Intent(Intent.ACTION_SEND);
                    intent_share.setType("text/plain");
                    intent_share.putExtra(Intent.EXTRA_TEXT, intent.getStringExtra("nim_user"));
                    startActivity(Intent.createChooser(intent_share, "Share via"));
                }
            }
        });

        kamera = findViewById(R.id.button4);
        imageView = findViewById(R.id.foto);
        kamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(home.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(home.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                } else {
                    openCamera();
                }
            }
        });
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "Tidak ada aplikasi kamera yang tersedia", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 102){
//            Bitmap image = (Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(image);
//        }
//    }
}