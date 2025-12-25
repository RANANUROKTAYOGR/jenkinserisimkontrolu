pipeline {
    agent any

    tools {
        // Jenkins Global Tool Configuration'da tanımlı Maven adı (Örn: 'Maven 3')
        // Eğer tanımlı değilse bu bloğu kaldırıp sistemdeki mvn komutunu kullanabilirsiniz.
        maven 'M3'
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
                    echo 'JUnit testi çalıştırılıyorr ve example.com kontrol ediliyor...'
                    // Platforma göre Maven komutunu çalıştır
                    if (isUnix()) {
                        sh 'mvn -B -DskipTests=false test'
                    } else {
                        bat 'mvn -B -DskipTests=false test'
                    }
                }
            }
            post {
                always {
                    // Test sonuçlarını Jenkins arayüzüne bas (glob ile tüm target dizinlerine bak)
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Deploy') {
            // Bu aşama SADECE önceki 'Test' aşaması başarılı olursa çalışır.
            steps {
                echo 'Test Başarılı! Deploy işlemi başlatılıyor...'
                // Buraya gerçek deploy komutlarınız gelecek (örn: scp, docker push, vb.)
                if (isUnix()) {
                    sh 'echo "Deploy işlemi başarıyla tamamlandı."'
                } else {
                    bat 'echo Deploy işlemi başarıyla tamamlandı.'
                }
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
