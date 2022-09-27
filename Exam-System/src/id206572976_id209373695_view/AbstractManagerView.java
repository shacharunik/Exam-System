package id206572976_id209373695_view;

import java.util.ArrayList;

import id206572976_id209373695_listeners.ViewEventsListener;

public interface AbstractManagerView {
	void registerListener(ViewEventsListener listener);
	void loadQuestions(String questions);
	void dialog(String msg);
	void loadListQuestions(ArrayList<String> listNameQuestions);
}
