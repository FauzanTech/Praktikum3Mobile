package com.example.praktikum3;

import static com.example.praktikum3.R.id.back;

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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class log extends AppCompatActivity {

    EditText edtnim, edtpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(log.this, MainActivity.class));
            }
        });

        TextView txt = findViewById(R.id.textView4);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(log.this, res.class));
            }
        });




//        Intent intent = getIntent();
//        String pesan = intent.getStringExtra("nim_user");
//        if (pesan == null || intent.getStringExtra("nim_user").equals("")){
//            Toast.makeText(log.this, "Selamat Datang!", Toast.LENGTH_LONG).show();
//        } else {
//            nim.setText(pesan);
//        }
        edtnim = findViewById(R.id.editnim);
        edtpass = findViewById(R.id.editpassword);
        Button login = findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(log.this, home.class);
//                String pesan = String.valueOf(nim.getText());
//                intent.putExtra("nim_user", pesan);
//                startActivity(intent);

                String nim, pass;
                nim = String.valueOf(edtnim.getText());
                pass = String.valueOf(edtpass.getText());

                if (TextUtils.isEmpty(nim)){
                    Toast.makeText(log.this, "Masukkan NIM", Toast.LENGTH_LONG).show();
                }

                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(log.this, "Masukkan kata sandi", Toast.LENGTH_LONG).show();
                }

                checkUser();
            }
        });
    }

    private void checkUser() {
        String nimUser, passUser;
        nimUser = String.valueOf(edtnim.getText());
        passUser = String.valueOf(edtpass.getText());

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDb = ref.orderByChild("nim").equalTo(nimUser);

        checkUserDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    edtnim.setError(null);
                    String passDb = snapshot.child(nimUser).child("pass").getValue(String.class);

                    if (passDb.equals(passUser)) {
                        edtnim.setError(null);

                        String namaDb, emailDb, nimDb;
                        namaDb = snapshot.child(nimUser).child("nama").getValue(String.class);
                        emailDb = snapshot.child(nimUser).child("email").getValue(String.class);
                        nimDb = snapshot.child(nimUser).child("nim").getValue(String.class);
                        Intent intent = new Intent(log.this, home.class);
                        intent.putExtra("nama", namaDb);
                        intent.putExtra("nim_user", nimDb);
                        intent.putExtra("email", emailDb);
                        intent.putExtra("sandi", passDb);
                        startActivity(intent);
                    } else {
                        edtpass.setError("Kata sandi salah");
                        edtpass.requestFocus();
                    }
                } else {
                    edtnim.setError("NIM salah");
                    edtnim.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
