package test.java.com.example;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AccessTest {

    private static final String TARGET_URL = "https://example.com";

    @Test
    public void testWebsiteIsAccessible() {
        System.out.println("Test Başlıyor: " + TARGET_URL + " adresine erişim kontrol ediliyor...");

        try {
            URL url = new URL(TARGET_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Timeout ayarları (örneğin 5 saniye)
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            System.out.println("Sunucu Yanıt Kodu: " + responseCode);

            // Eğer yanıt kodu 200 ise test geçer, değilse kalır.
            assertEquals(String.valueOf(200), responseCode, "URL erişilebilir değil! Beklenen: 200, Gelen: " + responseCode);

        } catch (IOException e) {
            // Bağlantı hatası veya DNS sorunu olursa test fail eder.
            fail("Web sitesine bağlanırken hata oluştu: " + e.getMessage());
        }
    }
}