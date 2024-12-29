# Library-Wrapper for JavaFX

The library provides a convenient set of tools for developing desktop applications. For example, tracking various events, creating your own elements/events and much more.

<h1>Simple window code</h1>

```java
public class App extends JFXGraphic {

   @Override
    public void onEnable(Stage stage) {
        LinkedPane pane = new LinkedPane(450, 600)
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
public class Template extends OrderlySceneTemplate {

   public Template() {
        super("order-template", new SecondTemplate());
    }

    @Override
    public void load(SceneEntry entry) {
        super.loadTemplateParent(entry);

        System.out.println("I'm loaded 2!");

        this.setLoaded(true);
    }
}
```
```java
public class Template extends InitialSceneTemplate {

    public Template() {
        super("default-template");
    }

    @Override
    public void load(SceneEntry entry) {
        LinkedPane container = (LinkedPane) entry.getMainLayout();
        SingleLabel singleLabel = new SingleLabel("Login", 25);
        singleLabel.initStyle(singleLabel.getLineObject(0), new CssStyle(CssStyle.Properties.TEXT_FILL_COLOR.getProperty("blue")))

        singleLabel.setRootElement(
                container,
                100, 100
        );
    }
}
```
<h2>Usage:</h2>

```java
    @Override
    public void onEnable(Stage stage) {
        LinkedPane pane = new LinkedPane(450, 600)
                .initStyle(new CssStyle(CssStyle.Properties.BACKGROUND_COLOR.getProperty("white")));

        SceneEntry sceneEntry = new SceneEntry(pane);
        sceneEntry.setScene(sceneEntry.initScene(pane.getBaseRegion()));

        this.setSceneEntry(sceneEntry);

        TemplateContainer container = getTemplateContainer();

        container.add("order", new LinkedGroup(new TempOrderTwo(), new TempOrderOne()));
        ((LinkedGroup) container.get("order")).getPreLoadedTemplates().forEach(template -> template.load(sceneEntry));

        stage.setScene(sceneEntry.getScene());
    }
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


