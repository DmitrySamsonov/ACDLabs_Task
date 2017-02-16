package com.acdlabs.sotringdata.webapp.gwt.client;

import java.util.Arrays;
import java.util.Comparator;

public class Sorter {
	
	static String[] sort(String inputString){
	    String[] lines = inputString.split("\n");
	    Arrays.sort(lines, new Comparator<String>() {
	        @Override
	        public int compare(String o1, String o2) {
	            int resultInv = 1;
	            String[] words1 = o1.split("\t");
	            String[] words2 = o2.split("\t");

	            if (words1.length > words2.length) {
	                String[] tmp = words2;
	                words2 = words1;
	                words1 = tmp;
	                resultInv = -1;
	            }

	            for (int i = 0; i < words1.length; i++) {
	                if (isNumeric(words1[i])) {
	                    if (isNumeric(words2[i])) {
	                    	Double word1 = Double.valueOf(words1[i]);
	                    	Double word2 = Double.valueOf(words2[i]);
	                    	int result = word1.compareTo(word2);
	                        if (result == 0)
	                            continue;
	                        else
	                            return resultInv*result;
	                    }
	                    else {
	                        return -1*resultInv;
	                    }
	                }
	                else {
	                    if (isNumeric(words2[i])) {
	                        return 1*resultInv;
	                    } else {
	                        int result = words1[i].compareTo(words2[i]);
	                        if (result == 0)
	                            continue;
	                        else
	                            return result*resultInv;
	                    }
	                }
	            }
	            return -1*resultInv;
	        }
	    });
	    
	    return lines;
	}
	
	private static boolean isNumeric(String word){
		return word.matches("-?\\d+(\\.\\d+)?");
	}

}
