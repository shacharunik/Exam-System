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

public class CopyTestGridPane extends GridPane {
	
	public CopyTestGridPane(ArrayList<ViewEventsListener> listeners) {

		setPadding(new Insets(10));
		setHgap(15);
		setVgap(15);

		Label lblTitle = new Label("Copy the last test: ");
		Button btnCopyTest = new Button("Copy the last test");
		btnCopyTest.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				for (ViewEventsListener l : listeners) {
					l.copyTestFromView();
				}
			}

		});
		
		add(lblTitle, 0, 0);
		add(btnCopyTest, 0, 1);
	}

}
