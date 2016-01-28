package de.fhflensburg.pd.group008.hsedit.editors;

import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

import de.fhflensburg.pd.group008.hsedit.Activator;
	
/**
 * The editor class witch handles configuration, preferenceStore and bracket matching
 *
 */
public class HaskellEditor extends TextEditor {

	public HaskellEditor() {
		super();
		setSourceViewerConfiguration(new HaskellConfiguration());
		setDocumentProvider(new FileDocumentProvider());		
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	public void dispose() {
		super.dispose();
	}
	
	// for bracket matching
	@Override
	protected void configureSourceViewerDecorationSupport (SourceViewerDecorationSupport support) {
		super.configureSourceViewerDecorationSupport(support);			 
		char[] brackets = {'(', ')', '[', ']'};	
		ICharacterPairMatcher matcher = new DefaultCharacterPairMatcher(brackets, IDocumentExtension3.DEFAULT_PARTITIONING);
		support.setCharacterPairMatcher(matcher);
		support.setMatchingCharacterPainterPreferenceKeys(Activator.PREFERENCE_BRACKET_MATCHING, Activator.PREFERENCE_COLOR_BRACKET_MATCHING);
	}
}
