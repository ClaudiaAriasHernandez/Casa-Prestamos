pipeline {
  //Donde se va a ejecutar el Pipeline
  agent {
    label 'Slave_Induccion'
  }
	
	tools {
    jdk 'JDK8_Centos' //Preinstalada en la Configuración del Master
    gradle 'Gradle5.6_Centos' //Preinstalada en la Configuración del   Master
  }
	
	//Aquí comienzan los “items” del Pipeline
  stages{
    stage('Checkout') {
      steps{
        echo "------------>Checkout<------------"
		checkout([
			$class: 'GitSCM', 
			branches: [[name: '*/master']], 
			doGenerateSubmoduleConfigurations: false, 
			extensions: [], 
			gitTool: 'Default', 
			submoduleCfg: [], 
			userRemoteConfigs: [[
			credentialsId: 'GitHub_cah', 
			url:'https://github.com/ClaudiaAriasHernandez/Casa-Prestamos'
			]]
		])	
      }
    }
    
	//pendiente
    stage('Compile & Unit Tests') {
      steps{
        echo "------------>Unit Tests<------------"
		sh 'gradle --b ./casa-prestamos-backend/microservicio/build.gradle clean'
					
		echo "------------>Unit Test<------------"
		sh 'gradle --b ./casa-prestamos-backend/microservicio/build.gradle test jacocoTestReport'
		
      }
    }
	
	//funciona
    stage('Static Code Analysis') {
      steps{
        echo '------------>Análisis de código estático<------------'
        withSonarQubeEnv('Sonar') {
		sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
        }
      }
    }

	//funciona
    stage('Build') {
      steps {
        echo "------------>Build<------------"
		
		sh 'gradle --b ./casa-prestamos-backend/microservicio/build.gradle build -x test'
      }
    }  
  }

  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
    }
    failure {
      echo 'This will run only if failed'
    }
    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}
