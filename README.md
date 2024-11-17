# Library-Wrapper for JavaFX

The library provides a convenient set of tools for developing desktop applications. For example, tracking various events, creating your own elements/events and much more.

<h1>Example code</h1>

```java
public class App extends JFXGraphic {

    @Override
    public void onEnable(Stage stage) {
        setGraphicInstance(this);

        SceneEntry sceneEntry = new SceneEntry(new SizeProperties(450, 550));
        this.setScene(sceneEntry);

        LinkedPane pane = new LinkedPane(450, 600).initStyle(new CssStyle(CssStyle.Properties.BACKGROUND_COLOR.getProperty("white")));

        Scene scene = CssStyle.getTransparentWindow(stage, sceneEntry.initScene(pane.getElement()));

        SingleLabel singleLabel = new SingleLabel("Login", 25);
        singleLabel.setRootElement(pane, WindowCalcHelper.getCentreX(pane.getElement(), singleLabel.getElement()), WindowCalcHelper.getCentreY(pane.getElement(), singleLabel.getElement()) - 155);
        singleLabel.getLineObject(0).setStyle("-fx-text-fill: black");

        stage.setScene(scene);
        setPrimaryStage(stage);
    }

    @Override
    public void onDisable() {}

    public static void main(String[] args) {
        launch(args);
    }
}
```
![image](https://github.com/user-attachments/assets/eb312378-e77d-4d7b-b65e-ff6874101368)


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


