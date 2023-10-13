pipeline {
    agent any
    tools {
        maven "MAVEN35"
        jdk "OracleJDK17"
    }
    stages {
        stage('Fetch Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Allwinkennethj/React_Spring_Project.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
            post {
                success {
                    echo 'Archiving artifacts now'
                    archiveArtifacts artifacts: '**/*.jar';
                }
            }
        }
        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Checkstyle Analysis') {
            steps {
                sh 'mvn checkstyle:checkstyle'
            }
        }
        stage('Docker build') {
            steps {
                sh "docker buildx build -t ${env.BUILD_ID}my-app ."
            }
        }
        stage('Docker tag') {
            steps {
                sh "docker tag my-app allwinkennethj/${env.BUILD_ID}my-app"
            }
        }
        stage('Docker image push') {
            steps {
                sh "docker push allwinkennethj/${env.BUILD_ID}my-app"
            }
        }
        stage('SonarQube Analysis') {
            steps {
                    sh """
                        mvn sonar:sonar \
                          -Dsonar.projectKey=crudproject \
                          -Dsonar.host.url=http://3.87.65.127 \
                          -Dsonar.login=3ce4f49c643fcf9992f491a0d5c73881a1fe488f
                    """
            }
        }
    }
}
