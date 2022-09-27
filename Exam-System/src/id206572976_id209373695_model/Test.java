package id206572976_id209373695_model;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable, Cloneable{
	private ArrayList<Questions> allQuestionsInTest;

	public Test() {
		allQuestionsInTest = new ArrayList<Questions>();
	}
	
	public void addQuestion(Questions q) {
		allQuestionsInTest.add(q);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("welcome to the math test! :) \n");
		sb.append("the questions are: \n");
		for (int i = 0; i < allQuestionsInTest.size(); i++) {
			sb.append("\n");
			sb.append(allQuestionsInTest.get(i)).toString();

		}
		return sb.toString();
	}

	public ArrayList<Questions> getAllQuestionsInTest() {
		return allQuestionsInTest;
	}

	public int getNumOfQuestions() {
		return allQuestionsInTest.size();
	}
	public Test clone() throws CloneNotSupportedException {
		return (Test) super.clone();

	}


}
