package id206572976_id209373695_model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Multy_Choice_Questions extends Questions implements Cloneable, Serializable ,Comparable<Questions>{

	private Set<Answer> allAnswers;
	private int numOfAnswers;

	public Multy_Choice_Questions(String content, Set<Answer> answers) throws CloneNotSupportedException {
		super(content);
		setAllAnswers(answers);
		numOfAnswers = allAnswers.length();
	}
	
	public String getContent() {
		return super.getContent() +"\n"+getStringAllContentOfAns(); 	
	}

	// creates a multi choice question with content only. used for comparing.
	public Multy_Choice_Questions(String content) {
		super(content);
	}

	public Multy_Choice_Questions clone() throws CloneNotSupportedException{
		Multy_Choice_Questions temp = (Multy_Choice_Questions) super.clone();
		temp.allAnswers = allAnswers.clone();
		return temp;
	}

	// adds two options to the answers of the question. no answer is correct, and
	// more than one answer is correct
	public boolean setOptionsForTest() {
		int count = count_correct();
		allAnswers.add(new Answer("no answer is correct", count < 1));
		allAnswers.add(new Answer("more than one answer is correct", count > 1));
		numOfAnswers += 2;
		return true;
	}
	
	public int count_correct() {
		int count = 0;
		for (int i = 0; i < allAnswers.length(); i++) {
			if (allAnswers.get(i).getCorrect())
				count++;
		}
		return count;
	}

	public String toString() {
		StringBuffer s = new StringBuffer(super.toString());
		s.append("\n the possible answers:");
		for (int i = 0; i < numOfAnswers; i++) {
			s.append("\n " + (i + 1) + ")" + allAnswers.get(i).toString());
		}
		return s.toString();
	}


	public Set<Answer> getAllAnswers() { //content + boolean
		return allAnswers;
	}
	
	public String getStringAllContentOfAns(){
		StringBuffer contents = new StringBuffer("the possible answers are: \n");
		for(int i =0; i<allAnswers.length(); i++) {
			contents.append("  "+(i+1)+ ") ");
			contents.append(allAnswers.get(i).getContent()+".\n");
		}
		return contents.toString();
	}
	
	public ArrayList<String> getAllContentOfAns(){
		ArrayList<String> contents = new ArrayList<String>();
		for(int i =0; i<allAnswers.length(); i++) {
			contents.add(allAnswers.get(i).getContent());
		}
		return contents;
	}

	public boolean setAllAnswers(Set<Answer> allAnswers) throws CloneNotSupportedException {
		this.allAnswers = allAnswers.clone();
		return true;
	}

	public int getNumOfAnswers() {
		return numOfAnswers;
	}

	// edits a specific answer from the array of answers
	// gets the index of the answer, the new content of the answer and if it's
	// correct or not
	public boolean setSpecificAnswer(int index, Answer newAns) throws IndexOutOfBoundsException {
		if (allAnswers.contain(newAns))
			return false;
		allAnswers.get(index).setAnswer(newAns);
		return true;

	}

	// deletes a specific answer from the array of questions.
	// gets the index of the answer
	public void deleteSpecificAnswer(int index) throws Exception {
		if (numOfAnswers == 1)
			throw new Exception("you cant leave the question without an asnwer! please enter a new answer:");

		if (index >= numOfAnswers)
			throw new IndexOutOfBoundsException("invalid id, try again");
		else
			allAnswers.remove(index);

		numOfAnswers--;

	}

	public boolean equals(Object other) {
		if (!(other instanceof Multy_Choice_Questions))
			return false;
		return super.equals(other);

	}
	
	public int getAllAnsLength() {
		int sum = 0;
		for (int i = 0; i < numOfAnswers; i++) {
			sum+=allAnswers.get(i).getSize();
		}
		return sum;
	}
	
	public int compareTo(Questions o) {
		int size =0;
		if(o instanceof Open_Questions)
			 size = ((Open_Questions)o).getAnswer().getSize();
		else size = ((Multy_Choice_Questions)o).getAllAnsLength();
		if(getAllAnsLength()>size)
			return 1;
		if(getAllAnsLength()<size)
			return -1;
		return 0;
	}

}
