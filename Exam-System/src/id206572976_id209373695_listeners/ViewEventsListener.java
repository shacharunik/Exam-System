package id206572976_id209373695_listeners;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface ViewEventsListener {
	void addOpenQuestionFromView(String content, String answer);
	void addMultiQuestionFromView(String content, ArrayList<String> answers, ArrayList<Boolean> isCorrect);
	void updateQuestionContent(int id, String newContent);
	void updateAnswersFromView(String newAnswer, boolean isCorrect , int id, int idOfAnswer);
	void deleteAnswerFromView(int qID, int numOfAnswer);
	int checkQTypeFromView(int qID);
	void createManualTestFromView(Set<Integer> questionsForTest, HashSet<Integer>[] answersForQuestion);
	void createAutoTestFromView(Integer value);
	void copyTestFromView();
}
