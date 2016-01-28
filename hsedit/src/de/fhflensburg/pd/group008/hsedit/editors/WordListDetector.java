package de.fhflensburg.pd.group008.hsedit.editors;

import org.eclipse.jface.text.rules.IWordDetector;


/**
 * WordDetector for detecting words in a given word list
 */
public class WordListDetector implements IWordDetector {
	
	private String[] wordlist;
	
	public WordListDetector (String[] wordList) {
		this.wordlist = wordList;
	}

	@Override
	public boolean isWordStart(char c) {
		for(String str : wordlist) {
  		  if (str.charAt(0) == c) return true;
  	  	}		    		  
  	  return false;
	}

	@Override
	public boolean isWordPart(char c) {
		for(String str : wordlist) {
  		  if (str.indexOf(c) > 0) return true;
		}
		return false;
	}
}
