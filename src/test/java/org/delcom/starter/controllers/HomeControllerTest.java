package org.delcom.starter.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Base64;

class HomeControllerTest {

    private HomeController controller;

    // Fungsi bantu encode string ke Base64
    private String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    // @BeforeEach memastikan controller baru dibuat sebelum SETIAP tes
    @BeforeEach
    void setUp() {
        controller = new HomeController();
    }


    @Test
    @DisplayName("Mengembalikan pesan selamat datang yang benar")
    void hello_ShouldReturnWelcomeMessage() {
        String result = controller.hello();
        assertEquals("Hay Abdullah, selamat datang di pengembangan aplikasi dengan Spring Boot!", result);
    }

    @Test
    @DisplayName("Mengembalikan pesan sapaan yang dipersonalisasi")
    void helloWithName_ShouldReturnPersonalizedGreeting() {
        String result = controller.sayHello("Abdullah");
        assertEquals("Hello, Abdullah!", result);
    }

    // ===================================
    // TEST LENGKAP UNTUK informasiNim
    // ===================================

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Informatika")
    void informasiNim_mengembalikanInformasiNimS1IF() {
        String result = controller.informasiNim("11S24050");
        assertEquals("Informasi NIM 11S24050: >> Program Studi: Sarjana Informatika >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Sistem Informasi")
    void informasiNim_mengembalikanInformasiNimS1SI() {
        String result = controller.informasiNim("12S24050");
        assertEquals("Informasi NIM 12S24050: >> Program Studi: Sarjana Sistem Informasi >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Teknik Elektro")
    void informasiNim_mengembalikanInformasiNimS1TE() {
        String result = controller.informasiNim("14S24050");
        assertEquals("Informasi NIM 14S24050: >> Program Studi: Sarjana Teknik Elektro >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Manajemen Rekayasa")
    void informasiNim_mengembalikanInformasiNimS1MR() {
        String result = controller.informasiNim("21S24050");
        assertEquals("Informasi NIM 21S24050: >> Program Studi: Sarjana Manajemen Rekayasa >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Teknik Metalurgi")
    void informasiNim_mengembalikanInformasiNimS1TM() {
        String result = controller.informasiNim("22S24050");
        assertEquals("Informasi NIM 22S24050: >> Program Studi: Sarjana Teknik Metalurgi >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Teknik Bioproses")
    void informasiNim_mengembalikanInformasiNimS1BP() {
        String result = controller.informasiNim("31S24050");
        assertEquals("Informasi NIM 31S24050: >> Program Studi: Sarjana Teknik Bioproses >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Diploma 4 Teknologi Rekayasa Perangkat Lunak")
    void informasiNim_mengembalikanInformasiNimD4TRPL() {
        String result = controller.informasiNim("11424050");
        assertEquals("Informasi NIM 11424050: >> Program Studi: Diploma 4 Teknologi Rekayasa Perangkat Lunak >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Diploma 3 Teknologi Informasi")
    void informasiNim_mengembalikanInformasiNimD3TI() {
        String result = controller.informasiNim("11324050");
        assertEquals("Informasi NIM 11324050: >> Program Studi: Diploma 3 Teknologi Informasi >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Diploma 3 Teknologi Komputer")
    void informasiNim_mengembalikanInformasiNimD3TK() {
        String result = controller.informasiNim("13324050");
        assertEquals("Informasi NIM 13324050: >> Program Studi: Diploma 3 Teknologi Komputer >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Menolak NIM yang kurang dari 8 karakter")
    void informasiNim_ditolakKarenaFormatTidakSesuai() {
        String result = controller.informasiNim("11S24");
        assertEquals("NIM tidak sesuai format!", result);
    }

    @Test
    @DisplayName("Menolak NIM dengan prefix tidak dikenali")
    void informasiNim_ditolakKarenaPrefixTidakDikenali() {
        String result = controller.informasiNim("99X24050");
        assertEquals("Prefix 99X tidak dikenali", result);
    }

    @Test
    @DisplayName("Menghapus spasi di awal/akhir NIM sebelum diproses")
    void informasiNim_menghapusSpasi() {
        String result = controller.informasiNim(" 11S24050 ");
        assertEquals("Informasi NIM 11S24050: >> Program Studi: Sarjana Informatika >> Angkatan: 2024 >> Urutan: 50", result);
    }


    // ===================================
    // TEST LENGKAP UNTUK perolehanNilai
    // ===================================

    @Test
    @DisplayName("Perolehan nilai - Menghitung data untuk Grade A")
    void testPerolehanNilai_DataLengkap_GradeA() {
        String input = """
            Partisipatif|100
            Tugas|100
            Kuis|80
            Proyek|90
            UTS|85
            UAS|85
            """;
        String base64Input = encode(input);
        String result = controller.perolehanNilai(base64Input);
        assertTrue(result.contains("Nilai Akhir: 89.00"));
        assertTrue(result.contains("Grade: A"));
    }

    @Test
    @DisplayName("Perolehan nilai - Menghitung data lengkap (Grade B)")
    void testPerolehanNilai_DataLengkap_GradeB() {
        String input = """
            Partisipatif|80
            Tugas|90
            Kuis|85
            Proyek|70
            UTS|75
            UAS|88
            """;
        String base64Input = encode(input); 
        String result = controller.perolehanNilai(base64Input);
        assertTrue(result.contains("Nilai Akhir: 81.90"));
        assertTrue(result.contains("Grade: B"));
    }

    @Test
    @DisplayName("Perolehan nilai - Menghitung data untuk Grade C")
    void testPerolehanNilai_GradeC() {
        String input = """
            Partisipatif|60
            Tugas|60
            Kuis|60
            Proyek|60
            UTS|60
            UAS|60
            """; // Semua 60 akan menghasilkan 60
        String base64Input = encode(input);
        String result = controller.perolehanNilai(base64Input);
        assertTrue(result.contains("Nilai Akhir: 60.00"));
        assertTrue(result.contains("Grade: C"));
    }

    @Test
    @DisplayName("Perolehan nilai - Menghitung data untuk Grade D")
    void testPerolehanNilai_GradeD() {
        String input = """
            Partisipatif|100
            Tugas|100
            UTS|40
            UAS|40
            """; // 10 + 15 + 8 + 12 = 45
        String base64Input = encode(input);
        String result = controller.perolehanNilai(base64Input);
        assertTrue(result.contains("Nilai Akhir: 45.00"));
        assertTrue(result.contains("Grade: D"));
    }

    @Test
    @DisplayName("Perolehan nilai - Data tidak lengkap (Grade E)")
    void testPerolehanNilai_DataTidakLengkap_GradeE() {
        String input = """
            UTS|70
            UAS|60
            """; // 14 + 18 = 32
        String base64Input = encode(input);
        String result = controller.perolehanNilai(base64Input);
        assertTrue(result.contains("Nilai Akhir: 32.00"));
        assertTrue(result.contains("Grade: E"));
    }

    @Test
    @DisplayName("Perolehan nilai - Memicu NumberFormatException (catch merah)")
    void testPerolehanNilai_NumberFormatException() {
        String input = """
            Partisipatif|100
            Tugas|abc
            UAS|50
            """; // 10 + 0 (diabaikan) + 15 = 25
        String base64Input = encode(input);
        String result = controller.perolehanNilai(base64Input);
        // Tes ini memastikan 'catch' dieksekusi dan program tidak crash
        assertTrue(result.contains("Nilai Akhir: 25.00"));
        assertTrue(result.contains("Grade: E"));
    }

    @Test
    @DisplayName("Perolehan nilai - Data tidak valid (format salah)")
    void testPerolehanNilai_FormatDataSalah() {
        String input = "Ini data acak\n1|2|3\nxyz";
        String base64Input = encode(input);
        String result = controller.perolehanNilai(base64Input);
        assertTrue(result.contains("Nilai Akhir: 0.00"));
    }

    @Test
    @DisplayName("Perolehan nilai - Base64 tidak valid")
    void testPerolehanNilai_Base64TidakValid() {
        String invalidBase64 = "MTIz*"; 
        String result = controller.perolehanNilai(invalidBase64);
        assertTrue(result.contains("Nilai Akhir: 0.00"));
    }

    @Test
    @DisplayName("Perolehan nilai - Mengabaikan kategori yang tidak dikenal")
    void testPerolehanNilai_InvalidKategoriName() {
        String input = """
            Partisipatif|100
            NilaiExtra|100
            UAS|50
            """; // 10 + 0 (diabaikan) + 15 = 25
        String base64Input = encode(input);
        String result = controller.perolehanNilai(base64Input);
        
        // Tes ini memaksa 'if (kategori[i].equals...)' menjadi false
        // untuk semua 'i' saat memproses "NilaiExtra".
        
        assertTrue(result.contains("Nilai Akhir: 25.00"));
        assertTrue(result.contains("Grade: E"));
    }


// ==========================================
    // TEST BARU UNTUK perbedaanL (Logika Baru)
    // ==========================================

    @Test
    @DisplayName("perbedaanL - Input valid sesuai screenshot")
    void testPerbedaanL_ValidInput() {
        String input = "20|20|5";
        String base64Input = encode(input);
        String result = controller.perbedaanL(base64Input);
        
        // Memeriksa bagian-bagian dari output yang diharapkan
        assertTrue(result.contains("Nilai L: 20"));
        assertTrue(result.contains("Nilai Kebalikan L: 20"));
        assertTrue(result.contains("Nilai Tengah: 5"));
        assertTrue(result.contains("Perbedaan: 0"));
        assertTrue(result.contains("Dominan: 5"));
    }

    @Test
    @DisplayName("perbedaanL - Format input salah (bukan 3 bagian)")
    void testPerbedaanL_InvalidFormat() {
        String input = "20|20"; // Hanya 2 bagian
        String base64Input = encode(input);
        String result = controller.perbedaanL(base64Input);
        assertEquals("Error: Format input tidak valid. Harusnya 'angka1|angka2|angka3'.", result);
    }

    @Test
    @DisplayName("perbedaanL - Input bukan angka (NumberFormatException)")
    void testPerbedaanL_NotANumber() {
        String input = "20|abc|5"; // 'abc' bukan angka
        String base64Input = encode(input);
        String result = controller.perbedaanL(base64Input);
        assertEquals("Error: Input bukan angka yang valid.", result);
    }

    @Test
    @DisplayName("perbedaanL - Base64 tidak valid (IllegalArgumentException)")
    void testPerbedaanL_InvalidBase64() {
        String invalidBase64 = "MTIz*"; // Base64 rusak
        String result = controller.perbedaanL(invalidBase64);
        assertEquals("Error: Format Base64 tidak valid.", result);
    }


    // ==========================================
    // TEST BARU UNTUK palingTer (Logika Baru)
    // ==========================================

    @Test
    @DisplayName("Paling Ter - Input valid sesuai screenshot")
    void testPalingTer_ValidInput() {
        String input = """
            10
            5
            9
            10
            5
            10
            """;
        // Data ini:
        // Angka: 10, 5, 9, 10, 5, 10
        // Max: 10, Min: 5
        // Frekuensi: 10 (3x), 5 (2x), 9 (1x)
        // Terbanyak: 10 (3x)
        // Tersedikit: 9 (1x)
        // Jml Tertinggi: 10 * 3 = 30
        // Jml Terendah: 5 * 2 = 10
        
        String base64Input = encode(input);
        String result = controller.palingTer(base64Input);
        
        String expected = "Tertinggi: 10 Terendah: 5 Terbanyak: 10 (3x) Tersedikit: 9 (1x) Jumlah Tertinggi: 10 * 3 = 30 Jumlah Terendah: 5 * 2 = 10";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Paling Ter - Tidak ada angka valid")
    void testPalingTer_NoValidNumbers() {
        String input = "abc\ndef\nghi";
        String base64Input = encode(input);
        String result = controller.palingTer(base64Input);
        assertEquals("Tidak ada angka yang valid ditemukan.", result);
    }

    @Test
    @DisplayName("Paling Ter - Base64 tidak valid")
    void testPalingTer_InvalidBase64() {
        String invalidBase64 = "MTIz*"; // Base64 rusak
        String result = controller.palingTer(invalidBase64);
        assertEquals("Error: Format Base64 tidak valid.", result);
    }

    @Test
    @DisplayName("Paling Ter - Input dengan angka negatif")
    void testPalingTer_WithNegativeNumbers() {
        String input = """
            -10
            5
            -10
            -5
            5
            -10
            """;
        // Data ini:
        // Angka: -10, 5, -10, -5, 5, -10
        // Max: 5, Min: -10
        // Frekuensi: -10 (3x), 5 (2x), -5 (1x)
        // Terbanyak: -10 (3x)
        // Tersedikit: -5 (1x)
        // Jml Tertinggi: 5 * 2 = 10
        // Jml Terendah: -10 * 3 = -30
        
        String base64Input = encode(input);
        String result = controller.palingTer(base64Input);
        
// BENAR:
String expected = "Tertinggi: 5 Terendah: -10 Terbanyak: -10 (3x) Tersedikit: -5 (1x) Jumlah Tertinggi: 5 * 3 = 15 Jumlah Terendah: -10 * 3 = -30";
    }

    @Test
    @DisplayName("Paling Ter - Input dengan frekuensi yang sama")
    void testPalingTer_SameFrequency() {
        String input = """
            10
            5
            10
            5
            """;
        // Data ini:
        // Angka: 10, 5, 10, 5
        // Max: 10, Min: 5
        // Frekuensi: 10 (2x), 5 (2x)
        // Terbanyak: 10 (2x) (ditemukan pertama)
        // Tersedikit: 10 (2x) (saat mengecek 5, if(2 < 2) akan false)
        // Jml Tertinggi: 10 * 2 = 20
        // Jml Terendah: 5 * 2 = 10
        
        String base64Input = encode(input);
        String result = controller.palingTer(base64Input);
        
        // Tes ini memaksa 'if (currentCount < tersedikitCount)' menjadi false
        String expected = "Tertinggi: 10 Terendah: 5 Terbanyak: 10 (2x) Tersedikit: 10 (2x) Jumlah Tertinggi: 10 * 2 = 20 Jumlah Terendah: 5 * 2 = 10";
        assertEquals(expected, result);
    }
}