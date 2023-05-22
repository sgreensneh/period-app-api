pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package -Dprod DskipTests'
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          docker.withRegistry('https://hub.docker.com/', 'docker-hub-id') {
            def dockerImage = docker.build('period-app-api:latest', '.')
            dockerImage.push()
          }
        }
      }
    }

    stage('Deploy to EC2') {
      steps {
        script {
          sh 'ssh -i ~/Downloads/raheem-jenkins-one-note-keypair.pem ec2-user@ec2-52-90-67-9.compute-1.amazonaws.com "docker-compose down && docker-compose up -d"'
        }
      }
    }
  }
}
