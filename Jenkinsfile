pipeline {
    agent any

    tools {
        // Jenkins Global Tool Configuration'da tanımlı Maven adı (Örn: 'Maven 3')
        // Eğer tanımlı değilse bu bloğu kaldırıp sistemdeki mvn komutunu kullanabilirsiniz.
        maven 'Maven 3'
        jdk 'Java 11'
    }

    stages {
        stage('Checkout') {
            steps {
                // Kodları SCM'den (Git vb.) çeker
                checkout scm
            }
        }

        stage('Test & Access Check') {
            steps {
                script {
                    echo 'JUnit testi çalıştırılıyor ve example.com kontrol ediliyor...'
                    // Maven test komutu. Eğer test fail olursa pipeline burada durur.
                    sh 'mvn clean test'
                }
            }
            post {
                always {
                    // Test sonuçlarını Jenkins arayüzüne bas
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Deploy') {
            // Bu aşama SADECE önceki 'Test' aşaması başarılı olursa çalışır.
            steps {
                echo 'Test Başarılı! Deploy işlemi başlatılıyor...'
                // Buraya gerçek deploy komutlarınız gelecek (örn: scp, docker push, vb.)
                sh 'echo "Deploy işlemi başarıyla tamamlandı."'
            }
        }
    }

    post {
        failure {
            echo 'Pipeline FAIL oldu! Siteye erişilemediği için Deploy yapılmadı.'
        }
        success {
            echo 'Pipeline SUCCESS! Site erişilebilir ve Deploy yapıldı.'
        }
    }
}