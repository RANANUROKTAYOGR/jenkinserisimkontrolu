# Jenkins erişim kontrolü (JUnit testi)

Bu proje, Jenkins pipeline içinde çalıştırılacak bir JUnit testi ile `https://example.com` adresine erişimi kontrol eder.

Davranış:
- Eğer `https://example.com` erişilebilir ise test geçer ve Jenkins pipeline'da `Deploy` adımı çalışır.
- Eğer erişim sağlanamazsa test başarısız olur ve pipeline FAIL olur.

Dosyalar:
- `pom.xml` : Maven yapılandırması (JUnit 5)
- `src/test/java/com/example/AccessTest.java` : Erişim testi
- `Jenkinsfile` : Örnek pipeline (Test -> Deploy)

Yerel olarak çalıştırma (gereksinimler: JDK 11+, Maven):

Windows PowerShell:

```powershell
mvn -q test
```

Linux/macOS:

```bash
mvn -q test
```

Not: Jenkinsfile içindeki `Deploy` adımı şu an placeholder mesajı yazdırır. Gerçek deploy komutlarınızı oraya ekleyebilirsiniz.

