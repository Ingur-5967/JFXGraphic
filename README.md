# Library-Wrapper for JavaFX

The library provides a convenient set of tools for developing desktop applications. For example, tracking various events, creating your own elements/events and much more.

<h1>Maven/Gradle</h1>

### Maven

```xml

<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>

<dependencies>
	<dependency>
	    <groupId>com.github.Ingur-5967</groupId>
	    <artifactId>JFXGraphic</artifactId>
	    <version>1.1.0</version>
	</dependency>
</dependencies>
```

### Gradle

```groovy
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}

dependencies {
    implementation 'com.github.Ingur-5967:JFXGraphic:1.1.0'
}
```


