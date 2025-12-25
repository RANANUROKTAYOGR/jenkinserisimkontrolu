pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // In a real scenario, Jenkins handles checkout automatically if configured with SCM (Git)
                // However, explicitly ensuring we have the latest code:
                checkout scm
                echo 'Checkout completed.'
            }
        }

        stage('Test') {
            steps {
                echo 'Running Unit Tests...'
                // Run Maven tests
                // Using 'bat' for Windows environment, use 'sh' for Linux
                bat 'mvn test'
            }
        }

        stage('Deploy') {
            // This stage only runs if previous stages (Test) are successful
            steps {
                echo 'Test passed. Deploying application...'
                // Add deployment commands here
                echo 'Deployment successful for https://example.com'
            }
        }
    }

    post {
        failure {
            echo 'Pipeline failed! One or more tests failed.'
        }
        success {
            echo 'Pipeline executed successfully.'
        }
    }
}