pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = 'jerrywise97/periodapp'
        BUILD_NUMBER = "latest"
        DOCKER_REGISTRY_CREDENTIALS = credentials('docker')
        EC2_USER = 'ec2-user'
        SSH_KEY = credentials('196f3506-9419-495c-83d5-f60c1d8c4771')
        EC2_HOST = '54.90.24.221'
    }
    
    stages {
        stage('Clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/jerrywise97/period-app-api.git'

            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    docker.withRegistry('', $(DOCKER_REGISTRY_CREDENTIALS)) {
                        def dockerImage = docker.build("${DOCKER_IMAGE_NAME}:${BUILD_NUMBER}", '.')
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                script {
                    sshagent(credentials: [$(SSH_KEY)]) {
                        def remoteCommand = "ssh -o StrictHostKeyChecking=no -i ${SSH_KEY} ${EC2_USER}@${EC2_HOST}"
                        remoteCommand += " docker pull ${DOCKER_IMAGE_NAME}:${BUILD_NUMBER}"
                        remoteCommand += " docker stop your-app-container || true"
                        remoteCommand += " docker rm your-app-container || true"
                        remoteCommand += " docker run -d --name your-app-container -p 8891:8891 ${DOCKER_IMAGE_NAME}:${BUILD_NUMBER}"
                        
                        sh remoteCommand
                    }
                }
            }
        }
    }
}
