package id206572976_id209373695_model;
import java.io.Serializable;

public class Answer implements Cloneable, Serializable{
	private String content;
	private boolean isCorrect;
	
	public Answer(String content, boolean isTrue) {
		this.content = content;
		isCorrect = isTrue;
	}
	public Answer(String content) { // for open question
		this.content = content;
		isCorrect = true;
	}
	
	public Answer clone() throws CloneNotSupportedException{
		return (Answer)super.clone();
	}
	
	public boolean setAnswer(Answer newans) {
		this.content = newans.content;
		this.isCorrect = newans.isCorrect;
		return true;
	}
	
	public String getContent() {
		return content;
	}
	public boolean getCorrect() {
		return isCorrect;
	}
	public int getSize() {
		return content.length();
	}
	public String toString() {
		return content + " ----> this answer is: "+ isCorrect;
	}
	public boolean equals(Answer other) {
		if(!(other instanceof Answer))
			return false;
		if(other.content.equals(content))
			return true;
		return false;
	}
	
	
	
}
