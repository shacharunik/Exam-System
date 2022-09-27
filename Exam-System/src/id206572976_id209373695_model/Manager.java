package id206572976_id209373695_model;

import java.util.Random;
import java.util.Scanner;

import id206572976_id209373695_listeners.ModelEventsListener;
import id206572976_id209373695_listeners.ViewEventsListener;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Iterator;

public class Manager implements Serializable{
	private ArrayList<Questions> allQuestions;
	private Test tempLastTest;
	
	//MVC-Listeners
	private ArrayList<ModelEventsListener> listeners;

	public Manager() throws FileNotFoundException, ClassNotFoundException, IOException, InvocationTargetException {
		allQuestions = new ArrayList<Questions>();
		listeners = new ArrayList<ModelEventsListener>();
		loadQuestions();
	}
	
	public void saveTest(Test myTest) throws IOException {
		Date date = new Date();
		SimpleDateFormat myformat = new SimpleDateFormat("_yyyy_MM_dd"); 
		String fileName = myformat.format(date);
		File f = new File("exam"+fileName+".txt");
		PrintWriter pw = new PrintWriter(f);
		pw.println("welcome to the test! good luck"); 
		for (int i = 0; i < myTest.getNumOfQuestions(); i++) {
			pw.println(("\n"+(i+1)+ ") "+myTest.getAllQuestionsInTest().get(i).getContent()));
		}
		pw.close();
		Desktop desktop = Desktop.getDesktop();
		desktop.open(f);
		tempLastTest = myTest;
	}
	
	public Test copyATest() throws CloneNotSupportedException {
		Test t = tempLastTest;
		return t.clone();
	}
	
	public void saveSolution(Test myTest) throws FileNotFoundException {
		Date date = new Date();
		SimpleDateFormat myformat = new SimpleDateFormat("_yyyy_MM_dd"); 
		String fileName = myformat.format(date);
		File f = new File("solution"+fileName+".txt");
		PrintWriter pw = new PrintWriter(f);
		pw.println("the questions + solutions : "); 
		pw.println(myTest.toString());
		pw.close();
	}
	
	public void saveQuestions() throws IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("allQuestions.dat"));
		outFile.writeObject(allQuestions);
		outFile.close();

	}

	public void loadQuestions() throws FileNotFoundException, IOException, ClassNotFoundException, InvocationTargetException {
		try {
			ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("allQuestions.dat"));
			allQuestions = (ArrayList<Questions>)inFile.readObject();
			inFile.close();
			setIdCount();
		} catch (Exception e) {
			System.out.println("File not found");
		}
	}
	
	public boolean setIdCount(){
		Questions.id_generator = getNumOfQuestions()+1;
		return true;
		
	}

	// prints all the questions in the database
	public String toString() {
		StringBuffer sb = new StringBuffer("the questions are:");
		for (int i = 0; i < allQuestions.size(); i++) {
			sb.append("\n" + allQuestions.get(i).toString());
		}
		return sb.toString();

	}
	
	public ArrayList<String> getListNameQuestions() {
		ArrayList<String> questionsNames = new ArrayList<String>();
		for (Questions ques : allQuestions) {
			questionsNames.add(ques.getContent());
		}
		return questionsNames;
	}

	// prints all the questions from the database that are available to use in an
	// automatic test
	public String printAvailbleQ() {
		StringBuffer sb = new StringBuffer("\n");
		for (int i = 0; i < allQuestions.size(); i++) {
			if (isQok(allQuestions.get(i)))
				sb.append(allQuestions.get(i).toString() + "\n");

		}
		return sb.toString();
	}

	// returns the number of questions in the database
	public int getNumOfQuestions() {
		return allQuestions.size();
	}

	// returns the database
	public ArrayList<Questions> getAllQuestions() {
		return allQuestions;
	}

	// adds a new open question to the database
	public void addOpenQuetion(String content, String answer) throws Exception { 
		Answer opAns = new Answer(answer);
		Open_Questions o = new Open_Questions(content, opAns);
		if (!allQuestions.contains(o)) {
			allQuestions.add(o);
			for (ModelEventsListener l : listeners) {
				l.questionAdded();
			}
		}
		else throw new Exception("this question already exist!");
	}

	// adds a new multi choice question to the database
	public void addMultiChoiceQuestion(String content, ArrayList<String> answers, ArrayList<Boolean> isCorrect) throws Exception {
		Set<Answer> multiAns = new Set<Answer>();
		for(int i=0; i<answers.size(); i++) {
			multiAns.add(new Answer(answers.get(i), isCorrect.get(i)));
		}
		Multy_Choice_Questions m = new Multy_Choice_Questions(content,multiAns);
		if (!allQuestions.contains(m)) {
			allQuestions.add(m);
			for (ModelEventsListener l : listeners) {
				l.questionAdded();
			}
		}
		else throw new Exception("this question already exist!");
	
		
	}

	// edits the content of a question in the database
	public boolean setQuestionContent(int id, String newContent) throws Exception {
		if (allQuestions.get(id) instanceof Open_Questions) {
			Open_Questions o = new Open_Questions(newContent, ((Open_Questions) allQuestions.get(id)).getAnswer());
			if (!allQuestions.contains(o)) {
				allQuestions.get(id).SetContent(newContent);
				for (ModelEventsListener l : listeners) {
					l.questionAdded();
				}
				return true;
			}
		} else {
			Multy_Choice_Questions m = new Multy_Choice_Questions(newContent);
			if (!allQuestions.contains(m)) {
				allQuestions.get(id).SetContent(newContent);
				for (ModelEventsListener l : listeners) {
					l.questionAdded();
				}
				return true;
			}
		}
		throw new Exception("\n there is another question with this content");
	}

	// edits the answer of a question in the database
	public boolean setAnswerToAQestion(String newAnswer, boolean isit, int id, int numOfAnswer) throws Exception {
		Answer temp = new Answer(newAnswer,isit);
		if (allQuestions.get(id) instanceof Multy_Choice_Questions) {
			Multy_Choice_Questions m = (Multy_Choice_Questions) allQuestions.get(id);
			if (numOfAnswer >= m.getNumOfAnswers())
				throw new Exception("Index unavailiable");
			if (isAnsOk(newAnswer, m.getAllContentOfAns()));
				m.setSpecificAnswer(numOfAnswer, temp);
				for (ModelEventsListener l : listeners) {
					l.questionAdded();
				}
		}
			else {
				((Open_Questions) allQuestions.get(id)).setAnswer(newAnswer);
				for (ModelEventsListener l : listeners) {
					l.questionAdded();
				}
			}
		return true;
	}

	public boolean deleteAnswerToMultiChoiceQuestion(int id, int numOfAnswer) throws Exception {
		if (allQuestions.get(id) instanceof Multy_Choice_Questions) {
			Multy_Choice_Questions m = (Multy_Choice_Questions) allQuestions.get(id);
			m.deleteSpecificAnswer(numOfAnswer);
				for (ModelEventsListener l : listeners) {
					l.questionAdded();
				}
		}
			else {
				throw new Exception("Can't delete answer for open question");
			}
		return true;
	}
	
	// checks that the answer being edited doesn't already exists in a multi choice question
	public boolean isAnsOk(String pAnswer, ArrayList<String> possibleAnswers) throws Exception{
		if (possibleAnswers.size() > 10)
			throw new Exception("maximum 10 answers! try again.");
		if (possibleAnswers.contains(pAnswer))
				throw new Exception("this answer is already exist, please enter another answer");
		return true;
}

	// returns the number of answers a specific question has, by it's id number
	public int getNumOfAnsById(int id) throws IndexOutOfBoundsException {
		return ((Multy_Choice_Questions) allQuestions.get(id)).getNumOfAnswers();
	}

	// returns a question by it's id number
	public Questions getQuestionByID(int id) throws IndexOutOfBoundsException {
		return allQuestions.get(id);
	}

	// creates a new test, and updates the questions that has been selected for it
	public Test createTest(int numOfQuestions, ArrayList<Integer> index) throws ArrayIndexOutOfBoundsException, Exception {
		Test myTest = new Test();
		for (int i = 0; i < numOfQuestions; i++) {
				myTest.addQuestion(allQuestions.get(index.get(i)).clone());   
		}
		return myTest;
	}

	// edits a question in a test to have specific answers
	public void ChooseAnsForTest(ArrayList<Integer> index, Questions q) throws Exception, IndexOutOfBoundsException {
		Multy_Choice_Questions m = (Multy_Choice_Questions)q;
		int originalLen = m.getNumOfAnswers();
		boolean degel = false;
		for (int j = originalLen - 1; j >= 0; j--) {
			degel = false;
			for (int i = 0; i < index.size(); i++) {
				if (index.get(i) >= originalLen || index.get(i) < 0) {
					System.out.println(index.get(i) + " " + originalLen);
					throw new Exception("Index unavailable");
				}
				if (index.get(i) == j)
					degel = true;
			}
			if (!degel) {
				m.deleteSpecificAnswer(j);
			}
		}
		m.setOptionsForTest();
		
	}

	// creates a new automatic test, draws the questions for it randomly
	public Test createAutomaticTest(int num) throws Exception { /// come back to me please!
		if (num > countAvailbeQ()) {
			throw new Exception("you can only choose up to " + countAvailbeQ());
		}
		if(num<0) {
			throw new Exception("positive only!");
		}
		Random r = new Random();
		ArrayList<Integer> indexOfQuestion = new ArrayList<Integer>();
		int rand = 0;
		int loop = 0;
		while (loop < num) {
			boolean flag = true;
			rand = r.nextInt(allQuestions.size());
			if(indexOfQuestion.contains(rand)) 
					flag = false;					
			if (flag && isQok(allQuestions.get(rand))) {
				indexOfQuestion.add(rand);
				loop++;
			}
		}
		
		Test autoTest = createTest(num, indexOfQuestion);
		setAnswersForAutoTest(autoTest);
		return autoTest;
	}

	// edits the multi choice questions of an automatic test to have only 4 answers
	public boolean setAnswersForAutoTest(Test autoTest) throws Exception {
		for (int i = 0; i < autoTest.getNumOfQuestions(); i++) {
		ArrayList<Integer> index = new ArrayList<Integer>();
			if (autoTest.getAllQuestionsInTest().get(i) instanceof Multy_Choice_Questions) {
				Multy_Choice_Questions m = ((Multy_Choice_Questions) autoTest.getAllQuestionsInTest().get(i));
				for (int j = 0, count = 0; j < m.getNumOfAnswers() && index.size()< 4; j++) {
					if (m.getAllAnswers().get(j).getCorrect())
						count++;
					if (count == 1 || !m.getAllAnswers().get(j).getCorrect())
						index.add(j);
				}
				ChooseAnsForTest(index, autoTest.getAllQuestionsInTest().get(i));
			}
		}
		return true;

	}

	// checks if a specific question is available to use in an automatic test
	public boolean isQok(Questions q) {
		if (q instanceof Open_Questions)
			return true;
		else {
			Multy_Choice_Questions m = (Multy_Choice_Questions) q;
			if (m.getNumOfAnswers() < 4)
				return false;

			if ((m.getNumOfAnswers() - m.count_correct()) <3) // num of all answers - num of correct answers = num of incorrect ans
				return false;
		}
		return true;
	}

	// returns the number of questions available to use in an automatic test
	public int countAvailbeQ() {
		int count = 0;
		for (int i = 0; i < allQuestions.size(); i++) {
			if (isQok(allQuestions.get(i)))
				count++;
		}
		return count;

	}

	// checks that the indexes are being chosen no more than once
	public boolean isInputOk(ArrayList<Integer> index, int length) throws Exception ,IndexOutOfBoundsException {
		for (int i = 0; i < index.size(); i++) {
			if(index.get(i)>length || index.get(i)<0)
				throw new Exception("invlid index");
			for (int j = 0; j < index.size(); j++) {
				if (index.get(i) == index.get(j) && i != j) {
					throw new Exception("cant choose the same index more the once");
				}
			}
		}

		return true;
	}

	// checks that the number being selected is smaller/equals to the number of
	// questions in the database
	public boolean isValueOk(int num) throws Exception {
		if (allQuestions.size()< num || num<0) {
			throw new Exception("you can choose up to " + allQuestions.size() + " question only");
		}
		return true;
	}
	
	public void registerListener(ModelEventsListener listener) {
		listeners.add(listener);
	}

	public int isQOpen(int id) {
		
		if (getQuestionByID(id) instanceof Open_Questions)
			return -1;
		else
			return ((Multy_Choice_Questions) getQuestionByID(id)).getNumOfAnswers();
	}
	
	

}
