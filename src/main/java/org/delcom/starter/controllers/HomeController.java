package org.delcom.starter.controllers;

import org.springframework.web.bind.annotation.*;
import java.util.Base64;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @GetMapping("/")
    public String hello() {
        return "Hay Abdullah, selamat datang di pengembangan aplikasi dengan Spring Boot!";
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

     // 1. Informasi NIM (migrasi StudiKasus1)
    @GetMapping("/informasiNim/{nim}")
    public String informasiNim(@PathVariable String nim) {
        nim = nim.trim();
        StringBuilder sb = new StringBuilder();

        if (nim.length() < 8) {
            sb.append("NIM tidak sesuai format!");
            return sb.toString();
        }

        String awal = nim.substring(0, 3);
        String thn = nim.substring(3, 5);
        String urut = nim.substring(5);

        String jurusan;
        switch (awal) {
            case "11S": jurusan = "Sarjana Informatika"; break;
            case "12S": jurusan = "Sarjana Sistem Informasi"; break;
            case "14S": jurusan = "Sarjana Teknik Elektro"; break;
            case "21S": jurusan = "Sarjana Manajemen Rekayasa"; break;
            case "22S": jurusan = "Sarjana Teknik Metalurgi"; break;
            case "31S": jurusan = "Sarjana Teknik Bioproses"; break;
            case "114": jurusan = "Diploma 4 Teknologi Rekayasa Perangkat Lunak"; break;
            case "113": jurusan = "Diploma 3 Teknologi Informasi"; break;
            case "133": jurusan = "Diploma 3 Teknologi Komputer"; break;
            default: jurusan = null;
        }

        if (jurusan == null) {
            sb.append("Prefix ").append(awal).append(" tidak dikenali");
        } else {
            int tahunMasuk = Integer.parseInt("20" + thn);
            int no = Integer.parseInt(urut);

            sb.append("Informasi NIM ").append(nim).append(":").append(" ");
            sb.append(">> Program Studi: ").append(jurusan).append(" ");
            sb.append(">> Angkatan: ").append(tahunMasuk).append(" ");
            sb.append(">> Urutan: ").append(no);
        }

        return sb.toString();
    }

       @PostMapping("/perolehanNilai")
    public String perolehanNilai(@RequestBody String strBase64) {
        // Decode Base64
        String decoded = new String(Base64.getDecoder().decode(strBase64));
        String[] lines = decoded.split("\\n");

        double totalNilai = 0;
        int count = 0;

        for (String line : lines) {
            if (line.contains("|")) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    try {
                        double nilai = Double.parseDouble(parts[2]);
                        totalNilai += nilai;
                        count++;
                    } catch (NumberFormatException e) {
                        // skip invalid line
                    }
                }
            }
        }

        double rataRata = (count > 0) ? totalNilai / count : 0;

        String grade;
        if (rataRata >= 85) grade = "A";
        else if (rataRata >= 70) grade = "B";
        else if (rataRata >= 55) grade = "C";
        else if (rataRata >= 40) grade = "D";
        else grade = "E";

        return "Rata-rata: " + rataRata + " → Nilai: " + grade;
    }

// METHOD 4 (perbedaanL)
// ==============
@GetMapping("/perbedaanL/{strBase64}")
public String perbedaanL(@PathVariable String strBase64) {
    try {
        // 1. Dekode Base64 → dapatkan angka asli dalam bentuk string
        // Menggunakan java.util.Base64;
        String inputAsli = new String(Base64.getDecoder().decode(strBase64));
        String angkaStr = inputAsli.trim(); // Hapus spasi di depan/belakang

        // 2. Ubah menjadi integer
        int angka = Integer.parseInt(angkaStr);
        
        // 3. Balikkan string angka dan ubah kembali menjadi integer
        int angkaTerbalik = Integer.parseInt(new StringBuilder(angkaStr).reverse().toString());
        
        // 4. Hitung selisih absolut
        int selisih = Math.abs(angka - angkaTerbalik);

        // 5. Kembalikan hasil
        return "Angka Asli: " + angka + "<br>" +
               "Angka Terbalik: " + angkaTerbalik + "<br>" +
               "Selisih: " + selisih;

    } catch (NumberFormatException e) {
        // Menangani jika string hasil dekode bukan merupakan angka
        return "Error: Input bukan angka yang valid.";
    } catch (IllegalArgumentException e) {
        // Menangani jika string Base64 tidak valid
        return "Error: Format Base64 tidak valid.";
    }
}

/// Method 4: Paling Ter (KODE LENGKAP)
@GetMapping("/palingTer/{strBase64}")
public String palingTer(@PathVariable String strBase64) {
    // 1. DECODE Base64 untuk mendapatkan input asli
    byte[] decodedBytes = Base64.getDecoder().decode(strBase64);
    String inputAsli = new String(decodedBytes);

    // --- LOGIKA PROGRAM: Mencari Angka Terbesar ---
    String[] baris = inputAsli.split("\\R");
    int palingBesar = Integer.MIN_VALUE; 

    for (String b : baris) {
        try {
            int angka = Integer.parseInt(b.trim()); 
            if (angka > palingBesar) {
                palingBesar = angka;
            }
        } catch (NumberFormatException e) {
            // Abaikan baris yang bukan angka
        }
    }

    // --- RETURN SEBAGAI SATU STRING ---
    if (palingBesar == Integer.MIN_VALUE) {
        return "Tidak ada angka yang valid ditemukan.";
    } else {
        return "Angka Paling Ter (besar): " + palingBesar;
    }
}
}
