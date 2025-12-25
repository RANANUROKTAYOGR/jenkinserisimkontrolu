pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                echo "Checking out branch: ${env.BRANCH_NAME}"
            }
        }
        stage('Build & Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh './mvnw clean test'
                    } else {
                        // Windows agents typically use the mvnw.cmd wrapper
                        bat '.\\mvnw.cmd clean test'
                    }
                }
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
                branch 'main'
            }
            steps {
                echo 'Tests passed — running Deploy step on main branch...'
                // Place actual deploy commands here (e.g., shell script, kubectl, scp).
                echo 'Deploy completed (placeholder)'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
        failure {
            echo 'Pipeline failed (tests or other stage).'
        }
        aborted {
            echo 'Pipeline was aborted.'
        }
    }
}
