package id206572976_id209373695_view;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import id206572976_id209373695_listeners.ViewEventsListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ManagerView implements AbstractManagerView {
	
	//MVC-Listeners//
	private ArrayList<ViewEventsListener> allListeners = new ArrayList<ViewEventsListener>();
	
	//Panes
	private TabPane tabPane;

	private GridPane mainGridPane;
	
	//GridPanes
	//AllQues
	private Label allQuestions;
	AllQuestionGridPane quesGP = new AllQuestionGridPane(allQuestions);
	
	//AddOpenQues
	AddOpenQuestionGridPane addOpenGP = new AddOpenQuestionGridPane(allListeners);
	
	//AddMultiQues
	AddMultiChoiceGridPane addMultiGP = new AddMultiChoiceGridPane(allListeners);
	
	//UpdataQuesContent
	UpdateQuestionGridPane updateContentQuestion = new UpdateQuestionGridPane(allListeners);
	
	//UpdataAnsContent
	UpdateAnswersGridPane updateAnswersQuestion = new UpdateAnswersGridPane(allListeners);
	
	//DeleteAns
	DeleteAnswerGridPane deleteAnswerQuestion = new DeleteAnswerGridPane(allListeners);
	
	//AddManualTest
	AddManualTestGridPane addManualTest = new AddManualTestGridPane(allListeners);
	
	//AddAutoTest
	AddAutoTestGridPane addAutoTest = new AddAutoTestGridPane(allListeners);
	
	//AddAutoTest
	CopyTestGridPane copyTest = new CopyTestGridPane(allListeners);
	//Labels

	
	
	
	public ManagerView(Stage stage) throws Exception {
		stage.setTitle("Test Creating Menu");
		
		mainGridPane = new GridPane();
		mainGridPane.setPadding(new Insets(10));
		mainGridPane.setHgap(20);
		mainGridPane.setVgap(20);
//		mainGridPane.setGridLinesVisible(true);
		
		tabPane = new TabPane();
		tabPane.setPrefWidth(1350);
		
		
		//TABS
		Tab showQuestionsTab = new Tab("All questions");
		showQuestionsTab.setClosable(false);
		showQuestionsTab.setContent(quesGP);
		
		Tab addOpenQTab = new Tab("Add an open question");
		addOpenQTab.setClosable(false);
		addOpenQTab.setContent(addOpenGP);
		
		Tab addMultiQTab = new Tab("Add an Multi Choice question");
		addMultiQTab.setClosable(false);
		addMultiQTab.setContent(addMultiGP);
		
		Tab updateContQTab = new Tab("Update Content of question");
		updateContQTab.setClosable(false);
		updateContQTab.setContent(updateContentQuestion);
		
		Tab updateAnsTab = new Tab("Update Answers of question");
		updateAnsTab.setClosable(false);
		updateAnsTab.setContent(updateAnswersQuestion);
		
		Tab deleteAnsTab = new Tab("Delete Answer of multi choice question");
		deleteAnsTab.setClosable(false);
		deleteAnsTab.setContent(deleteAnswerQuestion);
		
		Tab addManualTestTab = new Tab("Create Manual Test");
		addManualTestTab.setClosable(false);
		addManualTestTab.setContent(addManualTest);
		
		Tab addAutoTestTab = new Tab("Create Auto Test");
		addAutoTestTab.setClosable(false);
		addAutoTestTab.setContent(addAutoTest);
		
		Tab copyTestTab = new Tab("Copy the last Test");
		copyTestTab.setClosable(false);
		copyTestTab.setContent(copyTest);
		
		//Add tabs to tabs pane
		tabPane.getTabs().add(showQuestionsTab);
		tabPane.getTabs().add(addOpenQTab);
		tabPane.getTabs().add(addMultiQTab);
		tabPane.getTabs().add(updateContQTab);
		tabPane.getTabs().add(updateAnsTab);
		tabPane.getTabs().add(deleteAnsTab);
		tabPane.getTabs().add(addManualTestTab);
		tabPane.getTabs().add(addAutoTestTab);
		tabPane.getTabs().add(copyTestTab);
		
		//adding items to main grid pane
		mainGridPane.add(tabPane, 0, 0, 10, 10);
		stage.setScene(new Scene(mainGridPane, 1350, 850));
		mainGridPane.setStyle("-fx-font: 14 arial;");
		stage.getScene().getStylesheets().add(getClass().getResource("dark_theme.css").toString());
		stage.show();
	}

	@Override
	public void registerListener(ViewEventsListener listener) {
		allListeners.add(listener);
	}

	@Override
	public void loadQuestions(String questions) {
		quesGP.setAllQuestions(questions);
		dialog("The question database was updated");
	}
	
	public void dialog(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	@Override
	public void loadListQuestions(ArrayList<String> listNameQuestions) {
		updateContentQuestion.updateListQuestions(listNameQuestions);
		updateAnswersQuestion.updateListQuestions(listNameQuestions);
		deleteAnswerQuestion.updateListQuestions(listNameQuestions);
		addManualTest.updateListQuestions(listNameQuestions);
	}
}
