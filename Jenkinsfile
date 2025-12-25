pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                echo "Checking out branch: ${env.BRANCH_NAME}"
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
        failure {
            echo 'Pipeline failed (tests or other stage).'
        }
        aborted {
            echo 'Pipeline was aborted.'
        }
    }
}
