pipeline {

    environment {
        registry = "dheerajdac/graphql"
        registryqa01 = "dheerajdac/graphql:qa01"
        registryCredential = 'dockerhub'
        registryUrl = 'https://registry.hub.docker.com'
        dockerImage = ''
        MONGODB_HOST = 'localhost'
        MONGODB_PORT = '27017'
        MONGODB_USERNAME = 'root'
        MONGODB_PASSWORD = 'example'
        MONGODB_NAME = 'admin'
    }

    agent {
        docker {
            image 'dheerajdac/ubuntu:14'
            args '-v $HOME/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    stages {

        stage('Build') {
            steps {
                sh 'mvn -B clean package'
                sh 'mv target/graphql*.jar graphql.jar'
            }
        }

        stage('Building Image') {
            steps{
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
             }
        }

        stage('Deploy Image') {
            steps{
                script {
                    docker.withRegistry(registryUrl, registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Building Image qa01') {
            steps{
                script {
                    dockerImage = docker.build registryqa01
                }
            }
        }

        stage('Deploy Image qa01') {
            steps{
                script {
                    docker.withRegistry(registryUrl, registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Remove Unused docker image') {
            steps{
                sh "docker rmi $registry:$BUILD_NUMBER"
                sh "docker rmi $registryqa01"
            }
        }
    }

    post{
        always{
            sh 'mvn clean'
        }
    }

}
