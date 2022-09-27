package id206572976_id209373695_application;
	
import id206572976_id209373695_controler.Controller;
import id206572976_id209373695_model.Manager;
import id206572976_id209373695_view.AbstractManagerView;
import id206572976_id209373695_view.ManagerView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	Controller controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Manager manager = new Manager();
		AbstractManagerView view = new ManagerView(primaryStage);
		controller = new Controller(manager, view);
	}
	
    @Override
    public void stop() {
    	controller.saveOnExit();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}