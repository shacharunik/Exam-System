package id206572976_id209373695_view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import id206572976_id209373695_listeners.ViewEventsListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddAutoTestGridPane extends GridPane {
	
	public AddAutoTestGridPane(ArrayList<ViewEventsListener> listeners) {

		setPadding(new Insets(10));
		setHgap(15);
		setVgap(15);

		Label lblTitle = new Label("Number of questions do you want that will be on the test: ");
		Spinner<Integer> spnIdAnswer = new Spinner<>(1, 20, 1);
		Button btnCreateTest = new Button("Create the test");
		btnCreateTest.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				for (ViewEventsListener l : listeners) {
					l.createAutoTestFromView(spnIdAnswer.getValue());
				}
			}

		});
		
		add(lblTitle, 0, 0);
		add(spnIdAnswer, 0, 1);
		add(btnCreateTest, 0, 2);
	}

}
