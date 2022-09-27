package id206572976_id209373695_controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import id206572976_id209373695_listeners.ModelEventsListener;
import id206572976_id209373695_listeners.ViewEventsListener;
import id206572976_id209373695_model.Manager;
import id206572976_id209373695_model.Multy_Choice_Questions;
import id206572976_id209373695_model.Open_Questions;
import id206572976_id209373695_view.AbstractManagerView;

public class Controller implements ViewEventsListener, ModelEventsListener {
	private Manager manager;
	private AbstractManagerView view;
	
	public Controller(Manager manager, AbstractManagerView view) {
		this.manager = manager;
		this.view = view;
		
		view.registerListener(this);
		manager.registerListener(this);
		
		view.loadQuestions(manager.toString());
		view.loadListQuestions(manager.getListNameQuestions());
	}

	@Override
	public void addOpenQuestionFromView(String content, String answer) {
		try {
			manager.addOpenQuetion(content, answer);
		} catch (Exception e) {
			view.dialog(e.getMessage());
		}
		
	}

	@Override
	public void questionAdded() {
		view.loadQuestions(manager.toString());
		view.loadListQuestions(manager.getListNameQuestions());
	}	

	@Override
	public void addMultiQuestionFromView(String content, ArrayList<String> answers, ArrayList<Boolean> isCorrect) {
		try {
			manager.addMultiChoiceQuestion(content, answers, isCorrect);
		} catch (Exception e) {
			view.dialog(e.getMessage());
		}
		
	}

	@Override
	public void updateQuestionContent(int id, String newContent) {
		try {
			manager.setQuestionContent(id, newContent);
		} catch (Exception e) {
			view.dialog(e.getMessage());
		}
		
	}

	@Override
	public void updateAnswersFromView(String newAnswer, boolean isCorrect, int id, int idOfAnswer) {
		try {
			manager.setAnswerToAQestion(newAnswer, isCorrect, id, idOfAnswer);
		} catch (Exception e) {
			view.dialog(e.getMessage());
		}
		
	}

	
	@Override
	public void deleteAnswerFromView(int qID, int numOfAnswer) {
		try {
			manager.deleteAnswerToMultiChoiceQuestion(qID, numOfAnswer);
		} catch (Exception e) {
			view.dialog(e.getMessage());
		}
	}

	@Override
	public int checkQTypeFromView(int qID) {
		return manager.isQOpen(qID);
	}

	@Override
	public void createManualTestFromView(Set<Integer> questionsForTest, HashSet<Integer>[] answersForQuestion) {
		
		for (int i = 0; i < answersForQuestion.length; i++) {
			if (!answersForQuestion[i].isEmpty()) {
				ArrayList<Integer> arr = new ArrayList<>(answersForQuestion[i]);
				for (int j = 0; j < arr.size(); j++) {
					arr.set(j, arr.get(j) - 1);
				}
				try {
					manager.ChooseAnsForTest(arr, manager.getQuestionByID(i));
				} catch (Exception e) {
					view.dialog(e.getMessage());
				}
			}
			
		}
		ArrayList<Integer> ques = new ArrayList<>(questionsForTest);
		try {
			manager.saveTest(manager.createTest(ques.size(), ques));
		} catch (Exception e) {
			view.dialog(e.getMessage());
		}
		
		
	}

	@Override
	public void createAutoTestFromView(Integer value) {
		try {
			manager.saveTest(manager.createAutomaticTest(value));
		} catch (Exception e) {
			view.dialog(e.getMessage());
		}
		
	}

	@Override
	public void copyTestFromView() {
		try {
			manager.copyATest();
		} catch (CloneNotSupportedException e) {
			view.dialog(e.getMessage());
		}
	}
	
	public void saveOnExit() {
		try {
			manager.saveQuestions();
		} catch (IOException e) {
			view.dialog(e.getMessage());
		}
		view.dialog("All Questions saved");
	}
}
