package de.fhflensburg.pd.group008.hsedit.editors;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import de.fhflensburg.pd.group008.hsedit.Activator;

/**
 * The scanner class checks the source files for rules to be applied for syntax highlighting
 *
 */
public class HaskellScanner extends RuleBasedScanner {

	public HaskellScanner() {
		
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		// tokens for different regions in the source file
		IToken defaultToken  = new Token(new TextAttribute(new Color(Display.getCurrent(), PreferenceConverter.getColor(store, Activator.PREFERENCE_COLOR_DEFAULT))));
		IToken commentToken  = new Token(new TextAttribute(new Color(Display.getCurrent(), PreferenceConverter.getColor(store, Activator.PREFERENCE_COLOR_COMMENT))));
		IToken keywordToken  = new Token(new TextAttribute(new Color(Display.getCurrent(), PreferenceConverter.getColor(store, Activator.PREFERENCE_COLOR_KEYWORD))));
		IToken typeToken 	 = new Token(new TextAttribute(new Color(Display.getCurrent(), PreferenceConverter.getColor(store, Activator.PREFERENCE_COLOR_TYPE))));
		IToken valueToken 	 = new Token(new TextAttribute(new Color(Display.getCurrent(), PreferenceConverter.getColor(store, Activator.PREFERENCE_COLOR_VALUE))));
		IToken operatorToken = new Token(new TextAttribute(new Color(Display.getCurrent(), PreferenceConverter.getColor(store, Activator.PREFERENCE_COLOR_OPERATOR))));
		
		setDefaultReturnToken(defaultToken);
		
		// add rules
		IRule[] rules = {	
			new EndOfLineRule("-- ", commentToken),
			WordListRule.getRule(IHaskellLanguage.keywords, defaultToken, keywordToken),
			WordListRule.getRule(IHaskellLanguage.types, defaultToken, typeToken),
			WordListRule.getRule(IHaskellLanguage.operators, defaultToken, operatorToken),
			new SingleLineRule("\"", "\"", valueToken),
			new SingleLineRule("'", "'", valueToken),
			new NumberRule(valueToken)				
		};
		
		setRules(rules);		
	}
}
	
	
	
