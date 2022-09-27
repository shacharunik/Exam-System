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

public class AddManualTestGridPane extends GridPane {
	private ComboBox<String> questions = new ComboBox<>();
//	ArrayList<Integer> questionsForTest;
	Set<Integer> questionsForTest = new HashSet<Integer>();
	Label lblQuestions = new Label("Questions indices: ");
//	ArrayList<HashSet<Integer>> answersForQuestion = new ArrayList<HashSet<Integer>>();
//	HashSet<Integer>[] answersForQuestion = new HashSet<Integer>[questions.getItems().size()];
	HashSet<?>[] answersForQuestion;

	
	Label lblAnswers = new Label("");
	int count = 1;

	public AddManualTestGridPane(ArrayList<ViewEventsListener> listeners) {

		
		setPadding(new Insets(10));
		setHgap(15);
		setVgap(15);

		Label lblTitle = new Label("Choose one of the question:");
		Label lblSpn = new Label("Choose the number of answer (number start from 1): ");
		Spinner<Integer> spnIdAnswer = new Spinner<>(1, 10, 1);
		Label lblTitleAnswers = new Label("Answers for question: ");
		Button btnAddAnswer = new Button("Add Answer");
		btnAddAnswer.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				int ans = -1;
				String answerSet = new String("");
				int indexQuestion = questions.getSelectionModel().getSelectedIndex();
				int answerSelect = spnIdAnswer.getValue();
				for (ViewEventsListener l : listeners) {
					ans = l.checkQTypeFromView(questions.getSelectionModel().getSelectedIndex());
				}
				if (ans != -1 && answerSelect <= ans) {
					if (!questionsForTest.contains(indexQuestion)) {
						ViewMethods.dialog("You need to add the question before adding answers");
					} else {
						if (questions.getSelectionModel().getSelectedIndex() == -1) {
							ViewMethods.dialog("Do not leave empty fields");
						} else {
							((HashSet<Integer>) answersForQuestion[indexQuestion]).add(answerSelect);
							for (int i = 0; i < answersForQuestion.length; i++) {
								if (!answersForQuestion[i].isEmpty()) {
									answerSet += i + ": " + answersForQuestion[i].toString() + "\n";
								}
							}
							lblAnswers.setText(answerSet);
							answerSet = new String("");
						}
					}
				} else if (ans == -1)
					ViewMethods.dialog("Can't add answers to open question");
				else if (answerSelect >= ans) {
					ViewMethods.dialog("Index not availiable");
				}

			}

		});
		

		Button btnAddToTest = new Button("Add question to test");
		btnAddToTest.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				if (questions.getSelectionModel().getSelectedIndex() == -1) {
					ViewMethods.dialog("Do not leave empty fields");
				} else {
					questionsForTest.add(questions.getSelectionModel().getSelectedIndex());
					lblQuestions.setText("Questions indices: " + questionsForTest.toString());
					
				}
			}

		});

		Button btnCreateTest = new Button("Create the test");
		btnCreateTest.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				for (ViewEventsListener l : listeners) {
					l.createManualTestFromView(questionsForTest, (HashSet<Integer>[])answersForQuestion);
				}
			}

		});
		
		add(lblTitle, 0, 0);
		add(questions, 0, 1);
		add(lblSpn, 0, 3);
		add(spnIdAnswer, 0, 4);
		add(btnAddAnswer, 0, 5);
		add(btnAddToTest, 0, 2);
		add(lblQuestions, 0, 6);
		add(lblAnswers, 0, 8);
		add(lblTitleAnswers, 0, 7);
		add(btnCreateTest, 0, 9);
	}

	public void updateListQuestions(ArrayList<String> ques) {
		questions.getItems().clear();
		questions.getItems().addAll(ques);
		answersForQuestion = new HashSet<?>[questions.getItems().size()];
		for (int i = 0; i < answersForQuestion.length; ++i)
			answersForQuestion[i] = new HashSet<Integer>();
	}

}
