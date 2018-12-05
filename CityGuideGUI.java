package application;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import google.Event;
import google.HTTPRequest;
import google.JSONReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author brent
 */
public class CityGuideGUI extends Application{
    
    Stage window;
    BorderPane homeLayout, savedLayout, settingsLayout;
    Scene startScene, homeScene, savedScene, settingsScene;
    ListView<String> savedList, searchList;
    Button savedScreenButton;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Welcome to City Guide!");
        
        //Text Fields
        TextField searchField = new TextField();
        searchField.setPrefWidth(400);
        
        //Buttons
        Button searchButton = new Button("Search");
        
        //Labels
        Label startlbl1 = new Label("City Guide");
        Label startlbl2 = new Label("Find out more!");
        Label settingslbl1 = new Label("Settings Page");
        
        //Menus 
        //Search Menu
        Menu searchMenu = new Menu("Search");
         
        MenuItem citiesSearch = new MenuItem("Cities...");
        citiesSearch.setOnAction(e -> window.setScene(homeScene));
        MenuItem restSearch = new MenuItem("Restaurants...");
        MenuItem eventSearch = new MenuItem("Events...");
        MenuItem otherSearch = new MenuItem("Other...");
        searchMenu.getItems().addAll(citiesSearch, restSearch, eventSearch, otherSearch);

        //Saved Menu
        Menu savedMenu = new Menu("Saved Events");
        
        MenuItem viewSaved = new MenuItem("View...");
        viewSaved.setOnAction(e -> window.setScene(savedScene));
        MenuItem editSaved = new MenuItem("Edit...");
        MenuItem clearSaved = new MenuItem("Clear All...");
        savedMenu.getItems().addAll(viewSaved, editSaved, clearSaved);
        
        //Settings Menu
        Menu settingsMenu = new Menu("Settings");
        MenuItem tagSettings = new MenuItem("Change tags...");
        tagSettings.setOnAction(e -> window.setScene(settingsScene));
        settingsMenu.getItems().addAll(tagSettings);
        
        //Exit Menu
        Menu exitMenu = new Menu("Exit");
        
        MenuItem exitButton = new MenuItem("Exit...");
        exitButton.setOnAction(e -> window.close());
        exitMenu.getItems().addAll(exitButton);
        
        MenuBar menuBar = new MenuBar();
        MenuBar menuBar2 = new MenuBar();
        MenuBar menuBar3 = new MenuBar();
        menuBar.getMenus().addAll(searchMenu, savedMenu, settingsMenu, exitMenu);
        menuBar2.getMenus().addAll(searchMenu, savedMenu, settingsMenu, exitMenu);
        menuBar3.getMenus().addAll(searchMenu, savedMenu, settingsMenu, exitMenu);
        
        //Scenes
        //Start Screen
        Button startButton = new Button("Get Started");
        startButton.setOnAction(e -> window.setScene(homeScene));
        
        VBox startLayout = new VBox(20);
        startLayout.getChildren().addAll(startlbl1, startlbl2, startButton);
        startLayout.setPadding(new Insets(85,20,40,200));
        startScene = new Scene(startLayout, 600, 300);
        
        
        //Home Screen
        searchList = new ListView<>();
        searchList.getItems().addAll(Event.country, Event.name, Event.formatted_address, Event.rating, Event.opening_hours);
        HBox searchHbox = new HBox();
        searchHbox.getChildren().addAll(searchField, searchButton);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				HTTPRequest.searchString = searchField.getText();
				try {
					HTTPRequest.sendGet();
			
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
        });
        GridPane searchGrid = new GridPane();
        GridPane.setRowIndex(searchHbox, 1);
        GridPane.setColumnIndex(searchHbox, 1);
        GridPane.setRowIndex(searchList, 3);
        GridPane.setColumnIndex(searchList, 1);
        searchGrid.getColumnConstraints().add(new ColumnConstraints(75));
        searchGrid.getChildren().addAll(searchHbox, searchList);
        homeLayout = new BorderPane();
        homeLayout.setTop(menuBar);
        homeLayout.setCenter(searchGrid);
        
        homeScene = new Scene(homeLayout, 600, 500);
        
        
        //Saved Screen
        savedList = new ListView<>();
        savedList.getItems().addAll("City1", "City 2", "City 3");
        savedList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        savedScreenButton = new Button("Submit");
        savedScreenButton.setOnAction(e -> System.out.println("test"));
          
        savedLayout = new BorderPane();
        savedLayout.setTop(menuBar2);
        savedLayout.setCenter(savedList);
        savedLayout.setBottom(savedScreenButton);
        
        savedScene = new Scene(savedLayout, 600, 500);
        
        //Settings Screen
        settingsLayout = new BorderPane();
        settingsLayout.setTop(menuBar3);
        settingsLayout.setCenter(settingslbl1);
        
        settingsScene = new Scene(settingsLayout, 600, 500);
        
        
        ////////
        window.setScene(startScene);
        window.show();
    }
    
   
}