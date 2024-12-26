package com.example.mobilprojehotel;

public class Kisiler {
    String adSoyad;
    String tckn;
    String gsm;
    private String odaNo;

    public Kisiler(String adSoyad, String tckn, String gsm, String odaNo) {
        this.adSoyad = adSoyad;
        this.tckn = tckn;
        this.gsm = gsm;
        this.odaNo = odaNo;
    }

    public String toString() {
        return adSoyad + "," + tckn + "," + gsm + "," + odaNo;
    }

    public static Kisiler fromString(String str) {
        String[] parts = str.split(",");
        return new Kisiler(parts[0], parts[1], parts[2], parts[3]) ;
    }
}
