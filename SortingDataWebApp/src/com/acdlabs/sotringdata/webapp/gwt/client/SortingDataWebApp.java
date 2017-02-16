package com.acdlabs.sotringdata.webapp.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.storage.client.Storage;

public class SortingDataWebApp implements EntryPoint {

	private HorizontalPanel horizontalPanel = new HorizontalPanel();
	private TextArea inputDataTextarea = new TextArea();
	private Button processButton = new Button("Process");
	private Button initButton = new Button("Set some data");
	private Grid grid = new Grid();
	
	private Storage stockStore = null;
	
	/* 
	 * Entry point
	 */
	public void onModuleLoad() {
		
		drawElements();
		
		
		stockStore = Storage.getLocalStorageIfSupported();
		if (stockStore != null){
		  for (int i = 0; i < stockStore.getLength(); i++){
		    String key = stockStore.key(i);
		    
		    String tmp = inputDataTextarea.getText();
			tmp += stockStore.getItem(key);

			inputDataTextarea.setText(tmp);
		  }
		}
	
		
		// Move cursor focus to the input box.
		inputDataTextarea.setFocus(true);
				
		
		
		// Listen for mouse events on the Set some data button.
		initButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
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
				
			}
		});
		
		
		
		// Listen for mouse events on the Process button.
		processButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				// clear Storage
				stockStore = Storage.getLocalStorageIfSupported();
				 if (stockStore !=null) {
				      stockStore.clear();
				 }
				 
				// Save new inputData to Storage  
				final String symbol = inputDataTextarea.getText();
				stockStore = Storage.getLocalStorageIfSupported();
				if (stockStore != null) {
				  int numStocks = stockStore.getLength();
				  stockStore.setItem("Stock."+numStocks, symbol);
				}
				
				
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

		// Insert data to Grid
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
		
		inputDataTextarea.addStyleName("textarea");
		grid.addStyleName("grid");
		processButton.addStyleName("button");
		initButton.addStyleName("button");

		horizontalPanel.add(initButton);
		horizontalPanel.add(inputDataTextarea);
		horizontalPanel.add(processButton);
		horizontalPanel.add(grid);
		

		RootPanel.get("sortingwidget").add(horizontalPanel);
	}
	
}
