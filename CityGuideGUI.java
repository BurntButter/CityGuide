package application;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import google.Event;
import google.HTTPRequest;
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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
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
        
        startlbl1.setFont(Font.font("Verdana", 35));
        startlbl1.setTextAlignment(TextAlignment.CENTER);
        
        startlbl2.setFont(Font.font("Verdana", 16));
        startlbl2.setTextAlignment(TextAlignment.CENTER);
        
        startButton.setPadding(new Insets(10,10,10,10));
        
        GridPane startLayout = new GridPane();
        GridPane.setColumnIndex(startlbl1, 1);
        GridPane.setColumnIndex(startlbl2, 1);
        GridPane.setColumnIndex(startButton, 1);
        GridPane.setRowIndex(startlbl1, 1);
        GridPane.setRowIndex(startlbl2, 2);
        GridPane.setRowIndex(startButton, 3);
        GridPane.setMargin(startlbl1, new Insets(5, 10, 5, 10));
        GridPane.setMargin(startlbl2, new Insets(5, 10, 5, 10));
        GridPane.setMargin(startButton, new Insets(5, 10, 5, 10));

        startLayout.getChildren().addAll(startlbl1, startlbl2, startButton);
        startLayout.setPadding(new Insets(85,20,40,200));
        startScene = new Scene(startLayout, 600, 300);
        
        
        //Home Screen
        HBox searchHbox = new HBox();
        searchHbox.getChildren().addAll(searchField, searchButton);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				HTTPRequest.searchString = searchField.getText();
				try {
					HTTPRequest.sendGet();
					searchList.getItems().addAll("Location: " + Event.location, "Name: " + Event.name, "Address: " + Event.formatted_address, "Rating: " + Event.rating, "Hours: " + Event.opening_hours);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        });
        
        savedList = new ListView<>();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				HTTPRequest.saveFile = "1";
				savedList.getItems().addAll("");
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
        
        searchList = new ListView<>();
        GridPane searchGrid = new GridPane();
 
        GridPane.setRowIndex(searchHbox, 1);
        GridPane.setColumnIndex(searchHbox, 1);
        GridPane.setRowIndex(searchList, 2);
        GridPane.setColumnIndex(searchList, 1);
        GridPane.setMargin(searchHbox, new Insets(15, 10, 15, 10));
        GridPane.setColumnIndex(saveButton, 1);
        GridPane.setMargin(searchList, new Insets(5, 10, 5, 10));
        GridPane.setRowIndex(saveButton, 3);
        GridPane.setMargin(saveButton, new Insets(5, 10, 5, 10));
        saveButton.setPadding(new Insets(10,40,10,10));

        searchGrid.getColumnConstraints().add(new ColumnConstraints(75));
        searchGrid.getChildren().addAll(searchHbox, searchList, saveButton);
        
        
        homeLayout = new BorderPane();
        homeLayout.setTop(menuBar);
        homeLayout.setCenter(searchGrid);
        
        homeScene = new Scene(homeLayout, 600, 500);
        
        
        //Saved Screen
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