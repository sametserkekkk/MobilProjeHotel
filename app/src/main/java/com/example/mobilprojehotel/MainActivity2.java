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

import com.example.mobilprojehotel.databinding.ActivityMain2Binding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    private HashMap<String, Kisiler> kisiMap = new HashMap<>();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        kisileriYukle();

        binding.geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geri = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(geri);
                finish();
            }
        });

        binding.btnOdaRezerveEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int odaNo = Integer.parseInt(binding.etOdaNo.getText().toString().trim());
                if (odaNo >= 11 || odaNo <= 0) {
                    Toast.makeText(MainActivity2.this, "Odalar sadece 1 ila 10 arasında seçilmelidir.", Toast.LENGTH_SHORT).show();
                } else if (kisiMap.containsKey(binding.etOdaNo.getText().toString())) {
                    Toast.makeText(MainActivity2.this, "Seçtiğiniz oda dolu. Lütfen başka bir oda seçin.", Toast.LENGTH_SHORT).show();
                } else {
                    Kisiler kisi = new Kisiler(binding.etAdSoyad.getText().toString(), binding.etTCKN.getText().toString(), binding.etGSM.getText().toString(), binding.etOdaNo.getText().toString());
                    kisiMap.put(binding.etOdaNo.getText().toString(), kisi);
                    kisileriKaydet(); // Verileri kaydet
                    Toast.makeText(MainActivity2.this, "Başarıyla kaydedildi.", Toast.LENGTH_SHORT).show();
                    binding.etAdSoyad.setText("");
                    binding.etTCKN.setText("");
                    binding.etGSM.setText("");
                    binding.etOdaNo.setText("");
                }
            }
        });
    }

    private void kisileriKaydet() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
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