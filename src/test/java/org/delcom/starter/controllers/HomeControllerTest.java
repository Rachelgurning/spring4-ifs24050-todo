package org.delcom.starter.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Base64;

class HomeControllerTest {

    @Test
    @DisplayName("Mengembalikan pesan selamat datang yang benar")
    void hello_ShouldReturnWelcomeMessage() {
        HomeController controller = new HomeController();
        String result = controller.hello();
        assertEquals("Hay Abdullah, selamat datang di pengembangan aplikasi dengan Spring Boot!", result);
    }

    @Test
    @DisplayName("Mengembalikan pesan sapaan yang dipersonalisasi")
    void helloWithName_ShouldReturnPersonalizedGreeting() {
        HomeController controller = new HomeController();
        String result = controller.sayHello("Abdullah");
        assertEquals("Hello, Abdullah!", result);
    }

    // ============================
    // TEST: INFORMASI NIM VALID
    // ============================

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Informatika")
    void informasiNim_mengembalikanInformasiNimS1IF() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim("11S24050");
        assertEquals("Informasi NIM 11S24050: >> Program Studi: Sarjana Informatika >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Sistem Informasi")
    void informasiNim_mengembalikanInformasiNimS1SI() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim("12S24050");
        assertEquals("Informasi NIM 12S24050: >> Program Studi: Sarjana Sistem Informasi >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Teknik Elektro")
    void informasiNim_mengembalikanInformasiNimS1TE() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim("14S24050");
        assertEquals("Informasi NIM 14S24050: >> Program Studi: Sarjana Teknik Elektro >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Manajemen Rekayasa")
    void informasiNim_mengembalikanInformasiNimS1MR() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim("21S24050");
        assertEquals("Informasi NIM 21S24050: >> Program Studi: Sarjana Manajemen Rekayasa >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Teknik Metalurgi")
    void informasiNim_mengembalikanInformasiNimS1TM() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim("22S24050");
        assertEquals("Informasi NIM 22S24050: >> Program Studi: Sarjana Teknik Metalurgi >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Sarjana Teknik Bioproses")
    void informasiNim_mengembalikanInformasiNimS1BP() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim("31S24050");
        assertEquals("Informasi NIM 31S24050: >> Program Studi: Sarjana Teknik Bioproses >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Diploma 4 Teknologi Rekayasa Perangkat Lunak")
    void informasiNim_mengembalikanInformasiNimD4TRPL() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim("11424050");
        assertEquals("Informasi NIM 11424050: >> Program Studi: Diploma 4 Teknologi Rekayasa Perangkat Lunak >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Diploma 3 Teknologi Informasi")
    void informasiNim_mengembalikanInformasiNimD3TI() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim("11324050");
        assertEquals("Informasi NIM 11324050: >> Program Studi: Diploma 3 Teknologi Informasi >> Angkatan: 2024 >> Urutan: 50", result);
    }

    @Test
    @DisplayName("Mengembalikan informasi nim Diploma 3 Teknologi Komputer")
    void informasiNim_mengembalikanInformasiNimD3TK() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim("13324050");
        assertEquals("Informasi NIM 13324050: >> Program Studi: Diploma 3 Teknologi Komputer >> Angkatan: 2024 >> Urutan: 50", result);
    }

    // ============================
    // TEST: KONDISI KHUSUS / ERROR
    // ============================

    @Test
    @DisplayName("Menolak NIM yang kurang dari 8 karakter")
    void informasiNim_ditolakKarenaFormatTidakSesuai() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim("11S24");
        assertEquals("NIM tidak sesuai format!", result);
    }

    @Test
    @DisplayName("Menolak NIM dengan prefix tidak dikenali")
    void informasiNim_ditolakKarenaPrefixTidakDikenali() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim("99X24050");
        assertEquals("Prefix 99X tidak dikenali", result);
    }

    @Test
    @DisplayName("Menghapus spasi di awal/akhir NIM sebelum diproses")
    void informasiNim_menghapusSpasi() {
        HomeController controller = new HomeController();
        String result = controller.informasiNim(" 11S24050 ");
        assertEquals("Informasi NIM 11S24050: >> Program Studi: Sarjana Informatika >> Angkatan: 2024 >> Urutan: 50", result);
    }

   // Buat instance dari HomeController
    HomeController controller = new HomeController();

    // Fungsi bantu encode string ke Base64
    private String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    @Test
    @DisplayName("Perolehan nilai menghasilkan grade A")
    void testPerolehanNilai_A() {
        String data = encode("""
            T|90|90
            UAS|92|85
            PA|90|87
            ---
        """);
        String result = controller.perolehanNilai(data);
        assertTrue(result.contains("A"));
    }

    @Test
    @DisplayName("Perolehan nilai menghasilkan grade B")
    void testPerolehanNilai_B() {
        String data = encode("""
            T|80|70
            UAS|75|72
            PA|85|68
            ---
        """);
        String result = controller.perolehanNilai(data);
        assertTrue(result.contains("B"));
    }

    @Test
    @DisplayName("Perolehan nilai menghasilkan grade C")
    void testPerolehanNilai_C() {
        String data = encode("""
            T|70|55
            UAS|65|60
            PA|68|57
            ---
        """);
        String result = controller.perolehanNilai(data);
        assertTrue(result.contains("C"));
    }

    @Test
    @DisplayName("Perolehan nilai menghasilkan grade D")
    void testPerolehanNilai_D() {
        String data = encode("""
            T|60|40
            UAS|55|45
            PA|58|42
            ---
        """);
        String result = controller.perolehanNilai(data);
        assertTrue(result.contains("D"));
    }

    @Test
    @DisplayName("Perolehan nilai menghasilkan grade E")
    void testPerolehanNilai_E() {
        String data = encode("""
            T|30|20
            UAS|25|35
            PA|40|30
            ---
        """);
        String result = controller.perolehanNilai(data);
        assertTrue(result.contains("E"));
    }

    @Test
    @DisplayName("Baris tidak valid tidak menyebabkan error")
    void testPerolehanNilai_InvalidLines() {
        String data = encode("""
            T|90|90
            Invalid line here
            PA|85|abc
            UAS|80|75
            ---
        """);
        String result = controller.perolehanNilai(data);
        assertTrue(result.contains("Rata-rata"));
    }

        @Test
    @DisplayName("Perolehan nilai - memicu NumberFormatException")
    void testPerolehanNilai_NumberFormatException() {
        // Baris kedua bikin error parseDouble -> akan masuk ke blok catch
        String data = Base64.getEncoder().encodeToString("""
            T|90|90
            UAS|80|notanumber
            ---
        """.getBytes());

        String result = controller.perolehanNilai(data);
        assertTrue(result.contains("Rata-rata"));
    }

    @Test
    @DisplayName("Perolehan nilai - tidak ada baris valid (count == 0)")
    void testPerolehanNilai_NoValidLines() {
        // Semua baris tidak valid → count tetap 0
        String data = Base64.getEncoder().encodeToString("""
            invalid line
            data tanpa pemisah
            ---
        """.getBytes());

        String result = controller.perolehanNilai(data);
        assertTrue(result.contains("Rata-rata: 0.0"));
    }

    @Test
@DisplayName("Perolehan nilai - baris tidak memiliki 3 bagian (parts.length != 3)")
void testPerolehanNilai_PartsLengthNotThree() {
    HomeController controller = new HomeController();

    // Baris pertama valid, baris kedua invalid karena tidak ada cukup tanda '|'
    String data = Base64.getEncoder().encodeToString("""
        T|90|21
        UAS|92
        ---
    """.getBytes());

    String result = controller.perolehanNilai(data);

    // Pastikan masih menghasilkan rata-rata dari baris valid saja
    assertTrue(result.contains("Rata-rata"));
}

    // ============================
    // TEST: METHOD perbedaanL
    // ============================

    @Test
    @DisplayName("perbedaanL - menghitung selisih antara angka dan kebalikannya")
    void testPerbedaanL_ValidInput() {
        HomeController controller = new HomeController();

        // angka 123 → terbalik 321 → selisih |123 - 321| = 198
        String base64Input = Base64.getEncoder().encodeToString("123".getBytes());
        String result = controller.perbedaanL(base64Input);

        assertTrue(result.contains("Angka Asli: 123"));
        assertTrue(result.contains("Angka Terbalik: 321"));
        assertTrue(result.contains("Selisih: 198"));
    }

    @Test
    @DisplayName("perbedaanL - input memiliki spasi di depan dan belakang")
    void testPerbedaanL_InputDenganSpasi() {
        HomeController controller = new HomeController();

        // angka " 45 " → trim → "45" → terbalik 54 → selisih |45 - 54| = 9
        String base64Input = Base64.getEncoder().encodeToString(" 45 ".getBytes());
        String result = controller.perbedaanL(base64Input);

        assertTrue(result.contains("Angka Asli: 45"));
        assertTrue(result.contains("Angka Terbalik: 54"));
        assertTrue(result.contains("Selisih: 9"));
    }

    @Test
    @DisplayName("perbedaanL - input bukan angka valid (NumberFormatException)")
    void testPerbedaanL_InputTidakValid() {
        HomeController controller = new HomeController();

        // input "abc" akan menyebabkan NumberFormatException
        String base64Input = Base64.getEncoder().encodeToString("abc".getBytes());
        String result = controller.perbedaanL(base64Input);

        assertEquals("Error: Input bukan angka yang valid.", result);
    }

    @Test
    @DisplayName("perbedaanL - angka palindrom menghasilkan selisih 0")
    void testPerbedaanL_Palindrom() {
        HomeController controller = new HomeController();

        // angka 1221 → terbalik 1221 → selisih 0
        String base64Input = Base64.getEncoder().encodeToString("1221".getBytes());
        String result = controller.perbedaanL(base64Input);

        assertTrue(result.contains("Angka Asli: 1221"));
        assertTrue(result.contains("Angka Terbalik: 1221"));
        assertTrue(result.contains("Selisih: 0"));
    }

    @Test
    @DisplayName("Perbedaan L - Input bukan angka yang valid")
    void perbedaanL_ShouldHandleNonNumericInput() {
        HomeController controller = new HomeController();
        
        // Input: Base64 dari string "ABC" (bukan angka)
        String base64Input = Base64.getEncoder().encodeToString("ABC".getBytes());
        
        // Ekspektasi: Program harus me-return pesan error NumberFormatException
        String expectedResult = "Error: Input bukan angka yang valid.";
        
        String result = controller.perbedaanL(base64Input);
        
        assertEquals(expectedResult.trim(), result.trim());
    }

    @Test
    @DisplayName("Perbedaan L - Input Base64 tidak valid")
    void perbedaanL_ShouldHandleInvalidBase64() {
        HomeController controller = new HomeController();
        
        // Input: String yang sengaja dirusak (bukan Base64 valid)
        String invalidBase64 = "MTIz*"; 
        
        // Ekspektasi: Program harus me-return pesan error IllegalArgumentException
        String expectedResult = "Error: Format Base64 tidak valid.";
        
        String result = controller.perbedaanL(invalidBase64);
        
        assertEquals(expectedResult.trim(), result.trim());
    }
    
   @Test
@DisplayName("Tes method palingTer")
void palingTer_ShouldProcessBase64() {
    // Arrange
    HomeController controller = new HomeController();

    // STRING BASE64 UNTUK INPUT "10\n5\n30\n-2"
    String base64Input = "MTANCjUNCjMwDQotMg0K"; 

    // JAWABAN YANG BENAR 
    String expectedResult = "Angka Paling Ter (besar): 30"; 

    // Act
    String result = controller.palingTer(base64Input);

    // Assert
    assertEquals(expectedResult.trim(), result.trim());
}

@Test
    @DisplayName("Paling Ter - Tidak ada angka valid ditemukan")
    void palingTer_ShouldHandleNoValidNumbers() {
        HomeController controller = new HomeController();
        
        // Input: Base64 dari string yang hanya berisi teks atau data yang tidak valid
        String data = """
            abc
            ---
            """;
        String base64Input = Base64.getEncoder().encodeToString(data.getBytes());

        // Ekspektasi: Program harus me-return pesan jika tidak ada angka yang valid ditemukan
        String expectedResult = "Tidak ada angka yang valid ditemukan.";
        
        String result = controller.palingTer(base64Input);
        
        assertEquals(expectedResult.trim(), result.trim());
    }
}