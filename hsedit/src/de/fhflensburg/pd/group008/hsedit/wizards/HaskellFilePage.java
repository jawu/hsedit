package de.fhflensburg.pd.group008.hsedit.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * The default New File Wizard Page fitted to .hs-Files
 */
public class HaskellFilePage extends WizardNewFileCreationPage {

	public HaskellFilePage() {
		super("New Haskell File", (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection() );
		setFileExtension("hs");
		setDescription("Create a new Haskell file.");
	}
	
}
