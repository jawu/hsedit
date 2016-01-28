package de.fhflensburg.pd.group008.hsedit.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Handles the Wizard for creating a new Haskell File and opens it after creation in editor
 */
public class HaskellFileWizard extends Wizard implements IWorkbenchWizard {
	
	protected HaskellFilePage page;
	
	public HaskellFileWizard() {
		super();
		ImageDescriptor image = AbstractUIPlugin.imageDescriptorFromPlugin("de.fhflensburg.pd.group008.hsedit", "icons/haskell_big.png");
	    setDefaultPageImageDescriptor(image);
	}
	
	public void addPages() {
		page = new HaskellFilePage();
		addPage(page);
	}

	@Override
	public boolean performFinish() {  
	    IFile newFile = page.createNewFile();
	    try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new FileEditorInput(newFile), "de.fhflensburg.pd.group008.hsedit.editors.HaskellEditor");
		} catch (PartInitException e) {}
	    return true;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {}
}
