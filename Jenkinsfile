pipeline {
  agent  { label 'master' }
  tools {
      maven 'Maven 3.6.0'
      jdk 'jdk8'
  }
  stages {
  stage ('Initialize') {
              steps {
                  sh '''
                      echo "PATH = ${PATH}"
                      echo "M2_HOME = ${M2_HOME}"
                  '''
              }
          }
  stage('Checkout') {
          agent { label 'master' }
          steps {
               git  url:'https://github.com/NeotysLab/DevOpsDemo.git',
               branch :'master'
          }
        }
    stage('Build') {
            agent { label 'master' }
            steps {
                   sh 'mvn install'
                  }
          }
  stage('Start NeoLoad infrastructure') {
        agent { label 'master' }
        steps {
             sh  'docker-compose -f infrastructure/infrastructure/neoload/lg/docker-compose.yml up -d'
             stash includes: 'infrastructure/infrastructure/neoload/lg/lg.yaml', name: 'LG'
             stash includes: 'infrastructure/infrastructure/neoload/test/scenario.yaml', name: 'scenario'
        }
      }

    stage('Component Tests') {
      agent {
        dockerfile {
          args '--user root -v /tmp:/tmp --network cpv'
          dir 'infrastructure/infrastructure/neoload/controller'
        }
      }
      steps {
        script {
          neoloadRun executable: '/home/neoload/neoload/bin/NeoLoadCmd',
            project: "$WORKSPACE/target/neoload/ApiLoad/ApiLoad.nlp",
            testName: 'Pipeline API Limit Test (build ${BUILD_NUMBER})',
            testDescription: 'WeatherCrisis API Limit Testing ( Rest API) through pipeline',
            commandLineOption: "-variables sampledemo-host=35.180.30.230 -nlweb -loadGenerators $WORKSPACE/infrastructure/infrastructure/neoload/lg/lg.yaml -nlwebToken 15304f743f34ca33c458927a40945b7424a2066b95563774",
            scenario: 'API test', sharedLicense: [server: 'NeoLoad Demo License', duration: 2, vuCount: 50],
            trendGraphs: [
                [name: 'API Response time', curve: ['CreateReportAPI>Actions>Api'], statistic: 'average']
                ]

          }

      }
    }
    stage('Post Test') {
      agent {
        dockerfile {
          args '--user root -v /tmp:/tmp --network cpv'
          dir 'infrastructure/infrastructure/neoload/controller'
        }
      }
      steps {
        git(branch: 'develop',
            url: 'https://git-codecommit.eu-west-1.amazonaws.com/v1/repos/CPVWeatherCrisis',
            credentialsId: 'CodeCommit')
        unstash 'LG'
        unstash 'scenario'
        script {
          neoloadRun executable: '/home/neoload/neoload/bin/NeoLoadCmd',
            project: "$WORKSPACE/CPVWeatherCrisis.nlp",
            testName: 'Pipeline Post Test (build ${BUILD_NUMBER})',
            testDescription: 'WeatherCrisis API Testing (Mysql + Rest API) through pipeline',
            commandLineOption: "-nlweb -loadGenerators $WORKSPACE/infrastructure/infrastructure/neoload/lg/lg.yaml -nlwebToken 15304f743f34ca33c458927a40945b7424a2066b95563774",
            scenario: 'post', sharedLicense: [server: 'NeoLoad Demo License', duration: 2, vuCount: 50],
            trendGraphs: [
                'ErrorRate'
                ]
          }
      }
    }
    stage('Stop NeoLoad infrastructure') {
      agent { label 'master' }
      steps {
        sh 'docker-compose -f infrastructure/infrastructure/neoload/lg/docker-compose.yml down'
      }
    }
  }
}
