pipeline {
   agent any
    tools {
        maven 'Maven 3.6.0'
        jdk 'JDK 8'
    }
    environment {
        ENV = "prod"
        gpg_secret = credentials("gpg-secret")
        gpg_trust = credentials("gpg-ownertrust")
        gpg_passphrase = credentials("gpg-passphrase")
    }
    stages {
        stage("Import GPG Keys") {
            steps {
                sh "gpg --batch --import  ${env.gpg_secret}"
                sh "gpg --import-ownertrust ${env.gpg_trust}"
                sh """
                     cd $WORKSPACE
                     git init
                     git-secret reveal -p "$gpg_passphrase"
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
                   echo 'Setting environment variable to production'
                 }
               }
        }
}