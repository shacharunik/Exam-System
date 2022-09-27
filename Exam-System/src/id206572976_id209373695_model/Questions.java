package id206572976_id209373695_model;

import java.io.Serializable;

public abstract class Questions implements Cloneable, Serializable,Comparable<Questions>{

	protected static int id_generator = 1;
	protected int id;
	protected String content;
	protected Answer answer; 

	public Questions(String content) {
		this.content = content;
		id = id_generator++;
	}

	public Questions(String content, int id) {
		this.content = content;
		this.id = id;
	}
	

	public String toString() {
		return id + ") " + content + "?";

	}

	public String getContent() {
		return content +"?";
	}

	public boolean SetContent(String newContent) {
		content = newContent;
		return true;
	}

	public int getId() {
		return id;
	}

	public boolean setId() {
		id_generator--;
		return true;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Questions))
			return false;
		Questions q = (Questions) other;
		return q.content.equals(content);
	}
	
	public Questions clone() throws CloneNotSupportedException{
		return (Questions) super.clone();
	}
	
}
