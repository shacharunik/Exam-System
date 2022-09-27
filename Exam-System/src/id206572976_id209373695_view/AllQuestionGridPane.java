package id206572976_id209373695_view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class AllQuestionGridPane extends GridPane {
	private Label allQuestions;

	public AllQuestionGridPane(Label ques) {
		setPadding(new Insets(10));
		setHgap(10);
		setVgap(10);
		
		Label lblTitle = new Label("The Questions:");
		ques = new Label();
		ScrollPane scrlPane = new ScrollPane();
		scrlPane.setPrefSize(700, 700);
		scrlPane.setContent(ques);

		add(lblTitle, 0, 0);
		add(scrlPane, 0, 1);
		
		allQuestions = ques;
	}
	
	public void setAllQuestions(String str) {
		allQuestions.setText(str);
	}
	
}
