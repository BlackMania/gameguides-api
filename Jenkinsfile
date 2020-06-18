pipeline {
    agent any
    tools {
        maven 'Maven 3.6.0'
        jdk 'JDK 8'
    }
    environment {
        ENV = "test"
        gpg_secret = credentials("gpg-secret")
        gpg_trust = credentials("gpg-ownertrust")
        gpg_passphrase = credentials("gpg-passphrase")
    }
    stages {
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                sh "docker build -t vimuens/ggapi:${BUILD_NUMBER} ."
                sh "docker tag vimuens/ggapi:${BUILD_NUMBER} vimuens/ggapi:latest "
            }
        }
    }
}