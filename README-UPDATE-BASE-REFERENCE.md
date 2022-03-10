#EBUSINESS-API - ACTULIACIZACION DE JDK 11 / GRADLE 7.0.1

A continuación se realiza las descripción de pasos a seguir para realizar la actualización de jdk  11 y actualización de Gradle versión 7.0.1., en la líneas base de api´s regulares.


Nota: Es obligatorio realizar estas actualización si el proyecto lo soporta 

# Actualizar Versión de Gradle 
1.	Ingresar a la carpeta gradle del proyecto e identificar el archivo gradle-wrapper.properties 
 

![](/config/image/Captura-1.png)

2.	Realizar la actualización de la variable distributionUrl=https\://services.gradle.org/distributions/gradle-4.10.3-bin.zip  por distributionUrl=https\://services.gradle.org/distributions/gradle-7.0.1-bin.zip


![](/config/image/Captura-2.png)


# Actualizar Versión de JDK

 1.	Ingresar al archivo build.gradle  y buscar la variable sourceCompatibility = 1.8 , se debe actualizar por el valor sourceCompatibility = 11
 
 
# Actualizar Archivo build.gradle

Para que el proyecto trabaje con estas nuevas versiones hay que realizar algunos ajustes en el build.gradle , los cuales, se describen a continuación:
1.	Eliminar de la sesión buildscript la definición de repositories y dependencias, como se indica en la imagen.

![](/config/image/Captura-3.png)

2.  Adicionar, actualizar y eliminar las siguientes variables definidas en la sesión buildscript como se indica en  codigo.


```
buildscript {

	ext {
	
	    //dependencies service
	   springVersion = '2.0.5.RELEASE' // Eliminar 
		springBootVersion ='2.3.1.RELEASE'  // actualizar 
		springDependencyManagement = '1.0.9.RELEASE' // adicionar 
		postgresVersion = '42.2.5'
		lombokVersion = '1.18.4'
		log4jVersion = '2.11.1'
		commonsLangVersion ='3.9'
		mockitoVersion = '2.8.47'
		powerMockitoVersion = '1.7.4'
		commonFrameworkVersion = '1.0-local-SNAPSHOT'
		swaggerVersion = '2.9.2'
		
		// clean code
		sonarQubeVersion ='3.2.0'  // actualizar 
		spotBugs ='4.4.4'		// adicionar  	
		
		
		// component version settings
		releaseNumber = '1'
		sprintNumber = '0'
		buildNumber = System.getenv('BUILD_NUMBER') == null ? 'local' : System.getenv('BUILD_NUMBER')
		sonarQubeSite = ''
		nexusRepository = ''
		nexusUser = System.getenv('NEXUS_USER')
		nexusPass = System.getenv('NEXUS_PASS')
		commonsRepository = 'http://10.25.10.193:8081/repository/ebusiness-snapshot/'
	}

}


```

Debe quedar de la siguiente manera:

```
buildscript {

	ext {
	
	    //dependencies service
		springBootVersion ='2.3.1.RELEASE'
		springDependencyManagement = '1.0.9.RELEASE'
		postgresVersion = '42.2.5'
		lombokVersion = '1.18.4'
		log4jVersion = '2.11.1'
		commonsLangVersion ='3.9'
		mockitoVersion = '2.8.47'
		powerMockitoVersion = '1.7.4'
		commonFrameworkVersion = '1.0-local-SNAPSHOT'
		swaggerVersion = '2.9.2'
		
		// clean code
		sonarQubeVersion ='3.2.0'
		spotBugs ='4.4.4'
		
		
		// component version settings
		releaseNumber = '1'
		sprintNumber = '0'
		buildNumber = System.getenv('BUILD_NUMBER') == null ? 'local' : System.getenv('BUILD_NUMBER')
		sonarQubeSite = ''
		nexusRepository = ''
		nexusUser = System.getenv('NEXUS_USER')
		nexusPass = System.getenv('NEXUS_PASS')
		commonsRepository = 'http://10.25.10.193:8081/repository/ebusiness-snapshot/'
	}

}

```
3.	Realizar la actualización de la sesión de Plugins:


Eliminar los siquientes Plugins

```
/** ########### RUNTIME PLUGINS  **/

apply plugin: 'java'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'
apply plugin: 'maven'

/** ########### CODE QUALITY PLUGINS  **/

//apply plugin: 'findbugs'
apply plugin: 'pmd'
apply plugin: 'jacoco'
apply plugin: 'checkstyle'
apply plugin: "org.sonarqube"

```

Adicionar la nueva sesión de especificación de Plugins, como se muestra en el código.

```
plugins {
	id 'org.springframework.boot' version "${springBootVersion}"
	id 'io.spring.dependency-management' version "${springDependencyManagement}"
	id 'java'
	id 'checkstyle'
	id "com.github.spotbugs" version "${spotBugs}"
	id 'pmd'
	id 'jacoco'
	id 'org.sonarqube' version "${sonarQubeVersion}"
}

```



4.	 Adicionar una nueva configuración para soportar lombok, como se muestra en el código.

```
configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

```


5.	Adicionar la siguiente configuración de Maven  en las sesión de  repositories, como se muestra en el código.

 ```
 repositories {
	mavenLocal()
	mavenCentral()
	maven { url "https://repo.spring.io/milestone" }
	//Adicionar
	maven { 
	  url commonsRepository
	  allowInsecureProtocol = true
	}
}
 
 ```
 
6.	Actualizar la especificación de dependencias por la siguiente, como se muestra en el código.

```
 dependencies {
    annotationProcessor LOMBOK
	implementation SPRING_BOOT_RUNTIME 
	implementation LOMBOK, LOG4J2, COMMONS_LANG, SWAGGER, COMMON_FRAMEWORK, JPA_PERSISTENCE
	testImplementation SPRING_BOOT_UT, MOCKING
}
```

7.	Realizar la actualización de los siguientes archivos:


+ code-quality-config.gradle

```
/** ########### PMD CONFIGURATION  **/

pmd {
    toolVersion = "6.25.0"
    ruleSets = ["${rootProject.projectDir}/config/pmd/pmd-ruleset.xml"]
}	


/** ########### CHECKSTYLE CONFIGURATION  **/

checkstyle {
    toolVersion '7.8.1'
    configFile file("config/checkstyle/checkstyle.xml")
}
checkstyleMain {
    source ='src/main/java'
}
checkstyleTest {
    source ='src/test/java'
}


/** ########### SPOTBUGS CONFIGURATION  **/

spotbugs {
    excludeFilter = new File("config/spotbugs/spotbugs-exclude.xml")
}

spotbugsMain {
    reports {
        html {
            enabled = true
            destination = file("$buildDir/reports/spotbugs/main/spotbugs.html")
            stylesheet = 'fancy-hist.xsl'
        }
    }
}

/** ########### JACOCO CONFIGURATION  **/

ext['basePackage'] = 'co/com/carvajal/platform'

test {
    jacoco {
        enabled = true
    }
}

jacocoTestReport {
    reports {
        xml.enabled false
        csv.enabled false
    }
        afterEvaluate {
        classDirectories.from = files(classDirectories.files.collect {
            fileTree(
                dir: it, 
                exclude: [ "${basePackage}/crosscutting/messages/**",
        		   "${basePackage}/infrastructure/main/**",
				   "${basePackage}/crosscutting/domain/**",
				   "${basePackage}/crosscutting/persistence/entity/**",
				   "${basePackage}/crosscutting/persistence/repository/**",
				   "${basePackage}/infrastructure/configuration/**",
				   "${basePackage}/modules/configurations/gateway/model/pki/**" ]                 
                )
        })
    }
}


/** ########### SONARQUBE CONFIGURATION  **/

ext['sonarBasePackage'] = 'src/main/java/co/com/carvajal/ecd'

ext['sonarExcludesPackages'] = [
				   "${sonarBasePackage}/crosscutting/messages/**",
                   "${sonarBasePackage}/crosscutting/domain/**",
				   "${sonarBasePackage}/infrastructure/main/**",
				   "${sonarBasePackage}/crosscutting/persistence/entity/**",
				   "${sonarBasePackage}/crosscutting/persistence/repository/**",
				   "${sonarBasePackage}/infrastructure/configuration/**",
                   "${sonarBasePackage}/modules/configurations/gateway/model/pki/**",
]

sonarqube {
    properties {
        property "sonar.projectName", "bindingresult-api"
        property "sonar.projectKey", "bindingresult-api"
        property "sonar.language", "java"
        property "sonar.jacoco.reportPath", "${buildDir}/jacoco/test.exec"
		property "sonar.host.url", "https://sonar.carvajal.com"
		property "sonar.coverage.exclusions", sonarExcludesPackages
		property "sonar.exclusions", sonarExcludesPackages										   
    }
    
}

```

+ cv-framework-config.gradle

```

/** ########### JACOCO OFFLINE CONFIG  **/

//project.ext.jacocoOfflineSourceSets = [ 'main' ]
//task doJacocoOfflineInstrumentation(dependsOn: [ classes, project.configurations.jacocoAnt ]) {
//    inputs.files classes.outputs.files
//    File outputDir = new File(project.buildDir, 'instrumentedClasses')
//    outputs.dir outputDir
//    doFirst {
//        project.delete(outputDir)
//        ant.taskdef(
//            resource: 'org/jacoco/ant/antlib.xml',
//            classpath: project.configurations.jacocoAnt.asPath,
//            uri: 'jacoco'
//        )
//        def instrumented = false
//        jacocoOfflineSourceSets.each { sourceSetName ->
//            if (file(sourceSets[sourceSetName].output.classesDir).exists()) {
//                def instrumentedClassedDir = "${outputDir}/${sourceSetName}"
//                ant.'jacoco:instrument'(destdir: instrumentedClassedDir) {
//                    fileset(dir: sourceSets[sourceSetName].output.classesDir, includes: '**/*.class')
//                }
//                sourceSets.test.runtimeClasspath -= files(sourceSets[sourceSetName].output.classesDir)
//                sourceSets.test.runtimeClasspath += files(instrumentedClassedDir)
//                instrumented = true
//            }
//        }
//        if (instrumented) {
//            test.jvmArgs += '-noverify'
//        }
//    }
//}
//test.dependsOn doJacocoOfflineInstrumentation


```

8.	Actualizar archivo de Dockerfile por el siguiente contenido, teniendo en cuenta las personalizaciones de el componente, si no existe por crearlos, como se indica a continuación.

```
# Ebusiness for bindingresult-api-1.0-local-SNAPSHOT
# ./gradlew clean build
# Build image with:  docker build -t bindingresult-api .

### Cambiar la imagen linux para despligue en produccion
FROM amazoncorretto:11
LABEL key="Carvajal"

ADD build/libs /opt/spring-boot
ADD src/main/resources/config  /opt/config

### Set Environment
ENV SERVER_HOME /opt/spring-boot

WORKDIR /opt/spring-boot

### Open Ports
EXPOSE 9000

### Start instance
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "-Dfile.encoding=UTF-8", "/opt/spring-boot/bindingresult-api-1.0-local-SNAPSHOT.jar"]

```

Si no existe , por favor crearlo en la raiz del proyecto

![](/config/image/Captura-4.png)

9.	Actualizar y adicionar estructura de folder para config, como se muestra en la imagen, y tomar los archivos de referencia de la presente línea base.

![](/config/image/Captura-5.png)

La estructura debe quedar de la siguiente manera:

```

    └── config
        └── checkstyle
            └── checkstyle.xml
        └── k8s
            └── bindingresult-api.yml
        └── pmd
            └── pmd-ruleset.xml
        └── spotbugs
            └── spotbogs-exclude.xml        
```

10. Eliminar del archivo. gitignore las siguientes líneas, si no existen , omitir este paso.

```
/gradle/
/gradlew/

```

