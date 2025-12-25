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

## Branch ve Push Kuralları

Bu repo iki branch kullanır: `main` ve `test`. Güvenlik/iş akışı gereği, sadece `main` branch'ine push yapılmalıdır. `test` branch'ine doğrudan push yapılması engellenmelidir.

Yerel olarak push'ları engellemek için repo kökünde bir `githooks` dizini sağlanmıştır. Hook'ları etkinleştirmek için:

Windows PowerShell:

```powershell
# Proje kökünde çalıştırın
git config core.hooksPath .githooks
```

Linux/macOS:

```bash
# Proje kökünde çalıştırın
git config core.hooksPath .githooks
```

Bu, `.githooks/pre-push` ve `.githooks/pre-push.ps1` dosyalarını kullanarak `test` branch'ine yapılacak push'ları engelleyecektir.

Not: Git hooks localdur — uzak (remote) push'ları tamamen engellemek için uzak depo (ör. GitHub/GitLab) üzerinde branch koruma (branch protection) kuralları oluşturmanız gerekir.

## Jenkins davranışı

- `Test` aşaması her branch için çalışır (unit test'leri çalıştırır).
- `Deploy` aşaması yalnızca `main` branch'indeyken çalışır. Jenkinsfile içinde `when { branch 'main' }` koşulu ile kısıtlanmıştır.

Bu sayede pipeline, `test` branch'indeki commit/PR'lerde testleri çalıştırır ancak deploy adımını atlar; ayrıca local hook'lar `test` branch'e doğrudan push yapılmasını engeller.
