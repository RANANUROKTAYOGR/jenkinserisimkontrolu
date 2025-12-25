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
<<<<<<< HEAD
                echo 'Test Başarılı! Deploy işlemi başlatılıyor...'
                // Buraya gerçek deploy komutlarınız gelecek (örn: scp, docker push, vb.)
                sh 'echo "Deploy işlemi başarıyla tamamlandı."'
=======
                echo 'Test passed. Deploying application...'
                // Add deployment commands here
                echo 'Deployment successful for https://example.com'
                echo 'deneme '
>>>>>>> 0f93af915e743480d79d5dba3e7442e00b15e774
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
