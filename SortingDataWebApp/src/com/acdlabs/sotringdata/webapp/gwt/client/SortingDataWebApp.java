package com.acdlabs.sotringdata.webapp.gwt.client;

import java.util.*;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;


public class SortingDataWebApp implements EntryPoint {

	private HorizontalPanel horizontalPanel = new HorizontalPanel();
	private TextArea inputDataTextarea = new TextArea();
	private Button processButton = new Button("Process");
	private Grid grid = new Grid();
	
	
	
	/* 
	 * Entry point
	 */
	public void onModuleLoad() {
		
		drawElements();
		
		// initialization		
		inputDataTextarea.setText(
				"-2.2	2	3	4	329	2" + "\n" + 
				"2.2	12345q	69	-afg" + "\n" +
				"2.2	12345q	69	-asdf" + "\n" +
				"-22	1234234	asdfasf	asdgas" + "\n" +
				"-22	11	abc" + "\n" +
				"-22	-3	4" + "\n" +
				"" + "\n" +
				"-1.1" + "\n" +
				"" + "\n" +
				"qqqq	1.1");
		
		
		// Move cursor focus to the input box.
		inputDataTextarea.setFocus(true);
				
	
		
		// Listen for mouse events on the Process button.
		processButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				String inputData = inputDataTextarea.getText();
				
				String[] sortedData = Sorter.sort(inputData);
				
				insertDataToGrid(sortedData);
			}
		});
	
		
	}

	private void insertDataToGrid(String[] lines) {
		int max_row = lines.length;
		int max_column = 0;
		for(String line : lines){
			String[] words = line.split("\t");
			if(words.length > max_column )
				max_column = words.length;
		}
		
		grid.resize(max_row, max_column);
		for(int row = 0; row < grid.getRowCount(); row++)
			for(int column = 0; column < grid.getColumnCount() ;column++)
				grid.clearCell(row, column);

		
		for(int row = 0; row < lines.length ; row++){
			
			String[] words = lines[row].split("\t"); 
			
			grid.getRowFormatter().setStyleName(row, "td");
			
			for(int column = 0; column < words.length; column++){
				
				grid.setText(row, column, words[column]);
				grid.getColumnFormatter().addStyleName(column, "td");
				
			}
		}
	}
	
	private void drawElements() {
		// make an 40x20 text area
		inputDataTextarea.setCharacterWidth(40);
		inputDataTextarea.setVisibleLines(20);
		
		grid.addStyleName("grid");
		
		horizontalPanel.add(inputDataTextarea);
		horizontalPanel.add(processButton);
		horizontalPanel.add(grid);

		RootPanel.get("sortingwidget").add(horizontalPanel);		
	}
	
}
