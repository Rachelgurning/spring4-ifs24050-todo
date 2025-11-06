// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class AppBase64 {
   public AppBase64() {
   }

   public static void main(String[] var0) {
      Path var1;
      Path var2;
      byte[] var3;
      try {
         var1 = Paths.get("plain.txt");
         var2 = Paths.get("plain-ke-base64.txt");
         var3 = Files.readAllBytes(var1);
         String var4 = Base64.getEncoder().encodeToString(var3);
         Files.write(var2, var4.getBytes(), new OpenOption[0]);
         System.out.println("File berhasil dikonversi ke Base64 dan disimpan di plain-ke-base64.txt");
      } catch (IOException var6) {
         System.err.println("Terjadi kesalahan: " + var6.getMessage());
      }

      try {
         var1 = Paths.get("base64.txt");
         var2 = Paths.get("base64-ke-plain.txt");
         var3 = Files.readAllBytes(var1);
         byte[] var7 = Base64.getDecoder().decode(new String(var3));
         Files.write(var2, var7, new OpenOption[0]);
         System.out.println("File berhasil dikonversi dari Base64 dan disimpan di base64-ke-plain.txt");
      } catch (IOException var5) {
         System.err.println("Terjadi kesalahan: " + var5.getMessage());
      }

   }
}
