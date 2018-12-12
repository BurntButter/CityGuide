package application;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import google.Event;
import google.HTTPRequest;
import google.JSONReader;
import google.WriteToFile;
import google.YelpEvent;

import org.json.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    BorderPane homeLayout, savedLayout;
    Scene startScene, homeScene, savedScene;
    ListView<String> savedList, searchList;
    Button savedScreenButton;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception, JSONException{
        window = primaryStage;
        window.setTitle("Welcome to City Guide!");
        
        //Text Fields
        TextField searchField = new TextField();
        searchField.setPrefWidth(400);
        
        //Buttons
        Button searchButton = new Button("Search Google");
        Button searchYelp = new Button("Search Yelp");
        
        //Labels
        Label startlbl1 = new Label("City Guide");
        Label startlbl2 = new Label("Find out more!");
        
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
        MenuItem clearSaved = new MenuItem("Clear All...");
        savedMenu.getItems().addAll(viewSaved, clearSaved);
        
        
        //Exit Menu
        Menu exitMenu = new Menu("Exit");
        
        MenuItem exitButton = new MenuItem("Exit...");
        exitButton.setOnAction(e -> window.close());
        exitMenu.getItems().addAll(exitButton);
        
        MenuBar menuBar = new MenuBar();
        MenuBar menuBar2 = new MenuBar();
        menuBar.getMenus().addAll(searchMenu, savedMenu, exitMenu);
        menuBar2.getMenus().addAll(searchMenu, savedMenu, exitMenu);
        
        //Scenes
        //Start Screen
        
        VBox startBox = new VBox(15);
        Button startButton = new Button("Get Started");
        
        startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				window.setScene(homeScene);
			}
        	
        });
        
        startlbl1.setFont(Font.font("Verdana", 35));
        startlbl1.setTextAlignment(TextAlignment.CENTER);
        
        startlbl2.setFont(Font.font("Verdana", 16));
        startlbl2.setTextAlignment(TextAlignment.CENTER);
        
        startButton.setPadding(new Insets(10,10,10,10));
        
        startBox.getChildren().addAll(startlbl1, startlbl2, startButton);
        startBox.setAlignment(Pos.CENTER);
        
        GridPane startLayout = new GridPane();
        startLayout.setBackground(new Background(new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, Insets.EMPTY)));
        
        startLayout.getChildren().addAll(startBox);
        startLayout.setPadding(new Insets(85,20,40,200));
        startScene = new Scene(startLayout, 600, 300);
        
        
        //Home Screen
        HBox searchHbox = new HBox(8);
        searchHbox.getChildren().addAll(searchField, searchButton, searchYelp);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				searchList.getItems().clear();
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
				
				try {
					JSONReader.run(1);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				
				searchList.getItems().addAll("Location: " + Event.location, "Name: " + Event.name, "Address: " + Event.formatted_address, "Rating: " + Event.rating, "Hours: " + Event.opening_hours);
				
			}
        });
        
        searchYelp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				searchList.getItems().clear();
				HTTPRequest.searchString = searchField.getText();
				
				try {
					HTTPRequest.sendGetYelp();
					} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					JSONReader.run(0);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				
				searchList.getItems().addAll("Name:" + YelpEvent.name);
			}
        });
        
        savedList = new ListView<>();
 
        Button saveButton = new Button("Save");
        
        HBox homeHBox = new HBox(saveButton);

        saveButton.setPadding(new Insets(10,50,10,10));
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					WriteToFile.write();
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
        
        searchGrid.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
 
        GridPane.setRowIndex(searchHbox, 1);
        GridPane.setColumnIndex(searchHbox, 1);
        GridPane.setRowIndex(searchList, 2);
        GridPane.setColumnIndex(searchList, 1);
        GridPane.setMargin(searchHbox, new Insets(15, 10, 15, 10));
        GridPane.setColumnIndex(homeHBox, 1);
        GridPane.setMargin(searchList, new Insets(5, 10, 5, 10));
        GridPane.setRowIndex(homeHBox, 3);
        GridPane.setMargin(homeHBox, new Insets(5, 10, 5, 10));
        saveButton.setPadding(new Insets(10,40,10,10));

        searchGrid.getColumnConstraints().add(new ColumnConstraints(65));
        searchGrid.getChildren().addAll(searchHbox, searchList, homeHBox);
        
        
        homeLayout = new BorderPane();
        homeLayout.setTop(menuBar);
        homeLayout.setCenter(searchGrid);
        
        homeScene = new Scene(homeLayout, 775, 500);
        
        
        //Saved Screen
        savedList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        savedScreenButton = new Button("Clear All");
        
          
        savedLayout = new BorderPane();
        savedLayout.setTop(menuBar2);
        savedLayout.setCenter(savedList);
        savedLayout.setBottom(savedScreenButton);
        
        savedScene = new Scene(savedLayout, 775, 500);

        
        ////////
        window.setScene(startScene);
        window.show();
    }
    
   
}