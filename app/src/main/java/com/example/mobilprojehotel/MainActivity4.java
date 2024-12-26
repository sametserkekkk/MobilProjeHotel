package com.example.mobilprojehotel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobilprojehotel.databinding.ActivityMain4Binding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity {

    private ActivityMain4Binding binding;
    private HashMap<String, Kisiler> kisiMap;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        EdgeToEdge.enable(this);
        setContentView(view);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        kisiMap = new HashMap<>();
        kisileriYukle();

        binding.geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geri = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(geri);
                finish();
            }
        });

        odayiGuncelle();
    }

    private void odayiGuncelle() {
        for (int i = 1; i <= 10; i++) {
            String odaNo = String.valueOf(i);
            if (kisiMap.containsKey(odaNo)) {
                switch (i) {
                    case 1:
                        binding.tvoda1.setText("Dolu");
                        break;
                    case 2:
                        binding.tvoda2.setText("Dolu");
                        break;
                    case 3:
                        binding.tvoda3.setText("Dolu");
                        break;
                    case 4:
                        binding.tvoda4.setText("Dolu");
                        break;
                    case 5:
                        binding.tvoda5.setText("Dolu");
                        break;
                    case 6:
                        binding.tvoda6.setText("Dolu");
                        break;
                    case 7:
                        binding.tvoda7.setText("Dolu");
                        break;
                    case 8:
                        binding.tvoda8.setText("Dolu");
                        break;
                    case 9:
                        binding.tvoda9.setText("Dolu");
                        break;
                    case 10:
                        binding.tvoda10.setText("Dolu");
                        break;
                }
            } else {
                switch (i) {
                    case 1:
                        binding.tvoda1.setText("Boş");
                        break;
                    case 2:
                        binding.tvoda2.setText("Boş");
                        break;
                    case 3:
                        binding.tvoda3.setText("Boş");
                        break;
                    case 4:
                        binding.tvoda4.setText("Boş");
                        break;
                    case 5:
                        binding.tvoda5.setText("Boş");
                        break;
                    case 6:
                        binding.tvoda6.setText("Boş");
                        break;
                    case 7:
                        binding.tvoda7.setText("Boş");
                        break;
                    case 8:
                        binding.tvoda8.setText("Boş");
                        break;
                    case 9:
                        binding.tvoda9.setText("Boş");
                        break;
                    case 10:
                        binding.tvoda10.setText("Boş");
                        break;
                }
            }
        }
    }

    private void kisileriYukle() {
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String odaNo = entry.getKey();
            String kisiStr = (String) entry.getValue();
            kisiMap.put(odaNo, Kisiler.fromString(kisiStr));
        }
    }
}