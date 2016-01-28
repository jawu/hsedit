package de.fhflensburg.pd.group008.hsedit.editors;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WordRule;

/**
 * creates a new rule for a scanner based on the given word list and the given return tokens
 */
public class WordListRule {
	public static WordRule getRule(String[] wordlist, IToken defaultToken, IToken token) {
		
		WordRule rule = new WordRule(new WordListDetector(wordlist), defaultToken);
		for (String keyword : wordlist) 
			rule.addWord(keyword, token);
		
		return rule;
	}
}
