# Library-Wrapper for JavaFX

The library provides a convenient set of tools for developing desktop applications. For example, tracking various events, creating your own elements/events and much more.

<h1>Simple window code</h1>

```java
public class App extends JFXGraphic {

    @Override
    public void onEnable(Stage stage) {
        LinkedPane pane = new LinkedPane(900, 600)
                .initStyle(new CssStyle(CssStyle.Properties.BACKGROUND_COLOR.getProperty("white")));

        SceneEntry sceneEntry = new SceneEntry(pane);
        sceneEntry.setScene(sceneEntry.initScene(pane.getBaseRegion()));

        this.setSceneEntry(sceneEntry);
        stage.setScene(sceneEntry.getScene());
    }

    @Override
    public void onDisable() {}

    public static void main(String[] args) {
        launch(args);
    }
}
```
----
<h1>Templates</h1>

```java
public class MainSceneTemplate extends InitialSceneTemplate {

    public MainSceneTemplate() {
        super("main", "main-template");
    }

    @Override
    public void load(SceneEntry entry) {
        SingleLabel label = new SingleLabel("Hello world", 10);

        label.setRootElement(
                entry.getMainLayout(),
                100, 100
        );
    }
}
```
```java
public class ProfileSceneTemplate extends OrderlySceneTemplate {
    public ProfileSceneTemplate() {
        super("profile-scene", "profile", new MainSceneTemplate());
    }

    @Override
    public void load(SceneEntry entry) {
        super.loadTemplateParent(entry);

        SingleLabel label = new SingleLabel("Profile template", 12);

        label.setRootElement(
                entry.getMainLayout(),
                200, 200
        );
    }
}
```
<h2>Usage:</h2>

```java
    public void testLoad() {
        LinkedPane pane = new LinkedPane(900, 600)
                .initStyle(new CssStyle(CssStyle.Properties.BACKGROUND_COLOR.getProperty("white")));

        SceneEntry sceneEntry = new SceneEntry(pane);
        sceneEntry.setScene(sceneEntry.initScene(pane.getBaseRegion()));

        this.setSceneEntry(sceneEntry);

        TemplateContainer container = getTemplateContainer();
        container.getOrDefault("profile", new LinkedGroup(new ProfileSceneTemplate())).loadTemplates(sceneEntry);

        stage.setScene(sceneEntry.getScene());
    }
```
<h2>Template:</h2>

```java
 TemplateContainer container = getTemplateContainer();
 container.getOrDefault("profile", new LinkedGroup(new ProfileSceneTemplate())).loadTemplates(sceneEntry);
```
----
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


