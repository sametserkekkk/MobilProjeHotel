package com.example.mobilprojehotel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobilprojehotel.databinding.ActivityMain3Binding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    private HashMap<String, Kisiler> kisiMap;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
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

        binding.btnBilgileriGetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String odaNo = binding.etOdaNo.getText().toString();
                if (kisiMap.containsKey(odaNo)) {
                    Kisiler kisi = kisiMap.get(odaNo);
                    binding.etAdSoyad.setText(kisi.adSoyad);
                    binding.etTCKN.setText(kisi.tckn);
                    binding.etGSM.setText(kisi.gsm);
                } else {
                    Toast.makeText(MainActivity3.this, "Oda da kayıtlı kullanıcı bulunamadı.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnRezerveIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String odaNo = binding.etOdaNo.getText().toString();
                if (kisiMap.containsKey(odaNo)) {
                    kisiMap.remove(odaNo);
                    kisileriKaydet();
                    Toast.makeText(MainActivity3.this, "Kullanıcı başarıyla silindi.", Toast.LENGTH_SHORT).show();
                    binding.etAdSoyad.setText("");
                    binding.etTCKN.setText("");
                    binding.etGSM.setText("");
                    binding.etOdaNo.setText("");
                } else {
                    Toast.makeText(MainActivity3.this, "Oda da kayıtlı kullanıcı bulunamadı.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void kisileriKaydet() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        for (String odaNo : kisiMap.keySet()) {
            editor.putString(odaNo, kisiMap.get(odaNo).toString());
        }
        editor.apply();
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