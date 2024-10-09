package com.example.praktikum3;

import static com.example.praktikum3.R.id.back;

import android.content.Intent;
import android.os.Bundle;
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

public class log extends AppCompatActivity {

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

        EditText nim = findViewById(R.id.editnim);
        Intent intent = getIntent();
        String pesan = intent.getStringExtra("nim_user");
        if (pesan == null || intent.getStringExtra("nim_user").equals("")){
            Toast.makeText(log.this, "Selamat Datang!", Toast.LENGTH_LONG).show();
        } else {
            nim.setText(pesan);
        }
        Button login = findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(log.this, home.class);
                String pesan = String.valueOf(nim.getText());
                intent.putExtra("nim_user", pesan);
                startActivity(intent);
            }
        });
    }
}