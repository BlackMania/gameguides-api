pipeline {
   agent any
    tools {
        maven 'Maven 3.6.0'
        jdk 'JDK 8'
    }
    environment {
        ENV = "test"
        SECRET_PASSPHRASE = ${ secrets.PRODUCTION_PROPS }
    }


   stages {
      stage('Build') {
        steps {
          echo 'Building...'
          sh 'ls -a'
          echo ${SECRET_PASSPHRASE}
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