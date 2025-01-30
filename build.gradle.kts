plugins {
	// Deklarujemy pluginy, ale nie włączamy ich w projekcie głównym (apply false)
	id("org.springframework.boot") version "3.4.2" apply false
	id("io.spring.dependency-management") version "1.1.7" apply false

	// Kotlin DSL wymaga pluginu 'java' w cudzysłowie lub backtickach
	// ale można też go włączyć w subprojects:
	`java`
}

// Wspólna konfiguracja dla wszystkich subprojektów
subprojects {
	group = "com.psk"
	version = "0.0.1-SNAPSHOT"

	// Włączamy w subprojektach pluginy: java, spring-boot i dependency-management
	apply(plugin = "java")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	java {
		toolchain {
			languageVersion.set(JavaLanguageVersion.of(17))
		}
	}

	repositories {
		mavenCentral()
	}

	// Jeśli używasz Lomboka do adnotacji, pamiętaj o rozszerzeniu compileOnly -> annotationProcessor
	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}

	dependencies {
		// Spring Boot Web + Actuator
		implementation("org.springframework.boot:spring-boot-starter-actuator")
		implementation("org.springframework.boot:spring-boot-starter-web")

		// Lombok
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")

		// Micrometer Prometheus
		runtimeOnly("io.micrometer:micrometer-registry-prometheus")

		// Testy
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
