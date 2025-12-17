pipeline {
    agent {
        node {
            label 'agent-windows'
        }
    }

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Sarra3005/studentprojet.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

    }

    post {
        success {
            echo 'Build SUCCESS'
        }
        failure {
            echo 'Build FAILED'
        }
    }
}
