package de.fhflensburg.pd.group008.hsedit.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.fhflensburg.pd.group008.hsedit.Activator;

/**
 * This class displays the reference page for the Haskell editor
 */
public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	public PreferencePage() {
		super(GRID);
	}

	@Override
	protected void createFieldEditors() {
		
		// colors
		addField(new ColorFieldEditor(Activator.PREFERENCE_COLOR_DEFAULT, "Font Color Text", getFieldEditorParent()));
		addField(new ColorFieldEditor(Activator.PREFERENCE_COLOR_KEYWORD, "Font Color Keywords", getFieldEditorParent()));
		addField(new ColorFieldEditor(Activator.PREFERENCE_COLOR_TYPE, "Font Color Data Types", getFieldEditorParent()));
		addField(new ColorFieldEditor(Activator.PREFERENCE_COLOR_OPERATOR, "Font Color Operators", getFieldEditorParent()));
		addField(new ColorFieldEditor(Activator.PREFERENCE_COLOR_VALUE, "Font Color Values", getFieldEditorParent()));
		addField(new ColorFieldEditor(Activator.PREFERENCE_COLOR_COMMENT, "Font Color Comments", getFieldEditorParent()));
		
		// bracket matching
		addField(new BooleanFieldEditor(Activator.PREFERENCE_BRACKET_MATCHING, "Bracket Matching", getFieldEditorParent()));
		addField(new ColorFieldEditor(Activator.PREFERENCE_COLOR_BRACKET_MATCHING, "Color Bracket Matching", getFieldEditorParent()));
		
		// ghci path
		addField(new FileFieldEditor(Activator.PREFERENCE_GHCI_PATH, "Path to GHCI", true, getFieldEditorParent()));
	}
	
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

}
