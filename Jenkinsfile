pipeline {
    agent any

    stages {
        stage('pull code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[credentialsId: '44c5565b-eb81-42b7-8cbf-06c687cf001f', url: 'https://github.com/HFUTwaibaoxiaodui/back_end']]])
            }
        }
        stage('build project') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('publish application') {
            steps {
              sshPublisher(publishers: [sshPublisherDesc(configName: 'aliyun-ssh', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'cd /home/jar && sh restart.sh restart', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/jar', remoteDirectorySDF: false, removePrefix: 'target/', sourceFiles: 'target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
    }
}
