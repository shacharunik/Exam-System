package id206572976_id209373695_model;

import java.io.Serializable;

public class Open_Questions extends Questions implements Cloneable, Serializable,Comparable<Questions>{
	private Answer answer;
	public Open_Questions(String content, Answer answer) throws CloneNotSupportedException {
		super(content);
		cloneAnswer(answer);
		}

	public Open_Questions(Open_Questions o) {
		super(o.content, o.id);
		this.answer = o.answer;
	}
	
	public Open_Questions clone() throws CloneNotSupportedException {
		Open_Questions temp = (Open_Questions) super.clone();
		temp.answer = answer.clone();
		return temp;
	}

	public String toString() {
		return super.toString() + "\n " + " answer:" + answer;
	}
	public Answer getAnswer() {
		return answer;
	}
	public String getAnswerContent() {
		return answer.getContent();
	}
	
	public void cloneAnswer(Answer answer) throws CloneNotSupportedException {
		this.answer = answer.clone(); 
	}

	public boolean setAnswer(String newanswer) {
		answer.setAnswer(new Answer(newanswer));
		return true;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Open_Questions))
			return false;
		return super.equals(other);
	}


	@Override
	public int compareTo(Questions o) {
		int size =0;
		if(o instanceof Open_Questions)
			 size = ((Open_Questions)o).getAnswer().getSize();
		else size = ((Multy_Choice_Questions)o).getAllAnsLength();
		if(answer.getSize()>size)
			return 1;
		if(answer.getSize()<size)
			return -1;
		return 0;
	}
}
