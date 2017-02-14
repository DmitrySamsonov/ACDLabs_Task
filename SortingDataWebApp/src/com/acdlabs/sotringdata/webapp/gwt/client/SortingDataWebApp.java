package com.acdlabs.sotringdata.webapp.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Button;


public class SortingDataWebApp implements EntryPoint {

	private HorizontalPanel horizontalPanel = new HorizontalPanel();
	private TextArea inputDataTextarea = new TextArea();
	private FlexTable displayResultTable = new FlexTable();
	private Button processButton = new Button("Process");
	
	
	/*
	 * Entry point
	 */
	public void onModuleLoad() {
		
		drawElements();
		
		getDataFromTextArea();
		  
	    sortData();
		  
		insertDataToTable();
		
	}

	private void drawElements() {
		
		// Let's make an 80x50 text area to go along with the other two.
		inputDataTextarea.setCharacterWidth(80);
		inputDataTextarea.setVisibleLines(50);
						  
		horizontalPanel.add(inputDataTextarea);
		horizontalPanel.add(processButton);
		horizontalPanel.add(displayResultTable);

		RootPanel.get("textAreaForInputData").add(horizontalPanel);		
		
		// Move cursor focus to the input box.
		inputDataTextarea.setFocus(true);
		
	}

	private void getDataFromTextArea() {
		
	}

	private void sortData() {
		
	}
	
	private void insertDataToTable() {
		
		
	}
	
}
