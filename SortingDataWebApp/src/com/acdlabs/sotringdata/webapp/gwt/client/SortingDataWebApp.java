package com.acdlabs.sotringdata.webapp.gwt.client;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;


public class SortingDataWebApp implements EntryPoint {

	private HorizontalPanel horizontalPanel = new HorizontalPanel();
	private TextArea inputDataTextarea = new TextArea();
	private FlexTable displayResultTable = new FlexTable();
	private Button processButton = new Button("Process");
	
	private TextArea inputDataTextarea2 = new TextArea();
	
	private ArrayList<String> stocks = new ArrayList<String>();
	
	
	/*
	 * Entry point
	 */
	public void onModuleLoad() {
		
		drawElements();
		
		// Listen for mouse events on the Process button.
		processButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getDataFromTextArea();
			
			}
		});
		
		
		  
	    sortData();
		  
		insertDataToTable();
		
	}

	private void drawElements() {
		
		// Let's make an 80x50 text area to go along with the other two.
		inputDataTextarea.setCharacterWidth(40);
		inputDataTextarea.setVisibleLines(50);
		
			inputDataTextarea2.setCharacterWidth(40);
			inputDataTextarea2.setVisibleLines(50);
						  
		horizontalPanel.add(inputDataTextarea);
		horizontalPanel.add(inputDataTextarea2);
		horizontalPanel.add(processButton);
		horizontalPanel.add(displayResultTable);

		RootPanel.get("textAreaForInputData").add(horizontalPanel);		
		
		// Move cursor focus to the input box.
		inputDataTextarea.setFocus(true);
		
	}

	private void getDataFromTextArea() {
		final String lines = inputDataTextarea.getText();
		inputDataTextarea.setFocus(true);
		
		
		String[] words = lines.split("\n");
		
		Arrays.sort(words);
		
		String result = "";
		for(String elem : words)
		{
			result += elem + "\n";
		}
		inputDataTextarea2.setText(result);	
	    
	}

	private void sortData() {
		
	}
	
	private void insertDataToTable() {
		
		
	}
	
}
