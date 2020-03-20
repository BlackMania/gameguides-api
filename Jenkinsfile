pipeline {
   agent any
    tools {
        maven 'Maven 3.6.0'
        jdk 'JDK 8'
    }
    environment {
        ENV = "test"
    }
    environment {
        gpg_secret = credentials("gpg-secret")
        gpg_trust = credentials("gpg-trust")
        gpg_passphrase = credentials("gpg-passphrase")
    }
    stages {
        stage("Import GPG Keys") {
            steps {
                sh """
                    gpg --batch --import $gpg_secret
                    gpg --import-ownertrust $gpg_trust
                """
            }
        }
         stage('Build') {
                    steps {
                      echo 'Building...'
                      echo "Running ${env.BUILD_ID} ${env.BUILD_DISPLAY_NAME} on ${env.NODE_NAME} and JOB ${env.JOB_NAME}"
                      sh 'mvn clean verify'
                    }
               }
               stage('Test') {
                 steps {
                    echo 'Testing...'
                    sh 'mvn test'
                 }
               }
               stage('Deploy') {
                 steps {
                   echo 'Deploying...'
                 }
               }
        }
}