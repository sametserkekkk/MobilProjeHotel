package com.example.mobilprojehotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobilprojehotel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        EdgeToEdge.enable(this);
        setContentView(view);

        binding.btnOdaRezerveEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rezerveOlustur = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(rezerveOlustur);
                finish();
            }
        });

        binding.btnRezerveGoruntule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rezerveGoruntule = new Intent(getApplicationContext(), MainActivity4.class);
                startActivity(rezerveGoruntule);
                finish();
            }
        });

        binding.btnRezerveIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rezerveiptal = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(rezerveiptal);
                finish();
            }
        });


    }
}