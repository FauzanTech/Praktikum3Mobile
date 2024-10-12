package com.example.praktikum3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class res extends AppCompatActivity {

    EditText edtNama, edtnim, edtEmail, edtPass, edtKonfPass;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_res);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Button btnDaftar = findViewById(R.id.daftar2);
        edtNama = findViewById(R.id.text1);
        edtnim = findViewById(R.id.text2);
        edtEmail = findViewById(R.id.text3);
        edtPass = findViewById(R.id.text4);
        edtKonfPass = findViewById(R.id.text5);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama, nim, email, pass, conf_pass;
                nama = String.valueOf(edtNama.getText());
                nim = String.valueOf(edtnim.getText());
                email = String.valueOf(edtEmail.getText());
                pass = String.valueOf(edtPass.getText());
                conf_pass = String.valueOf(edtKonfPass.getText());

                if (TextUtils.isEmpty(nama)){
                    edtNama.setError("Masukkan nama");
                    edtNama.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(nim)){
                    edtnim.setError("Masukkan nim");
                    edtnim.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    edtEmail.setError("Masukkan email");
                    edtEmail.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    edtPass.setError("Masukkan Password");
                    edtPass.requestFocus();
                    return;
                }

                if (!pass.equals(conf_pass)){
                    edtKonfPass.setError("Password berbeda");
                    edtKonfPass.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(conf_pass)){
                    edtKonfPass.setError("Masukkan Konfirmasi Password");
                    edtKonfPass.requestFocus();
                    return;
                }

                db = FirebaseDatabase.getInstance();
                ref = db.getReference("users");

                HelperClass helperClass = new HelperClass(nama, nim, email, pass, conf_pass);
                ref.child(nim).setValue(helperClass);

                Toast.makeText(res.this, "Registrasi berhasil!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(res.this, log.class));


//                Intent intent = new Intent(res.this, log.class);
//                String pesan = String.valueOf(edtnim.getText());
//                intent.putExtra("nim_user", pesan);
//                startActivity(intent);
            }
        });

        ImageButton btnBack = findViewById(R.id.imageButton);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(res.this, MainActivity.class));
            }
        });

        TextView txt = findViewById(R.id.masuk);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(res.this, log.class));
            }
        });


    }
}