pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out...'
            }
        }
        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -B -DskipTests=false test'
                    } else {
                        bat 'mvn -B -DskipTests=false test'
                    }
                }
            }
        }
        stage('Deploy') {
            when {
                // Deploy only when previous stages (tests) succeeded
                expression { return true }
            }
            steps {
                echo 'Tests passed — running Deploy step...'
                // Place actual deploy commands here (e.g., shell script, kubectl, scp).
                echo 'Deploy completed (placeholder)'
            }
        }
    }
    post {
        failure {
            echo 'Pipeline failed (tests or other stage).'
        }
    }
}

