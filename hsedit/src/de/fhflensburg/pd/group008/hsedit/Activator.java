package de.fhflensburg.pd.group008.hsedit;

import java.net.URI;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements IPropertyChangeListener, IPartListener {

	// The plug-in ID
	public static final String PLUGIN_ID = "HsEdit";
	
	// preference store keys
	public static final String PREFERENCE_BRACKET_MATCHING = "bracket_matching";
	public static final String PREFERENCE_COLOR_BRACKET_MATCHING = "bracket_matching_color";
	public static final String PREFERENCE_COLOR_KEYWORD = "keyword_color";
	public static final String PREFERENCE_COLOR_TYPE = "type_color";
	public static final String PREFERENCE_COLOR_OPERATOR = "operator_color";
	public static final String PREFERENCE_COLOR_VALUE = "value_color";
	public static final String PREFERENCE_COLOR_DEFAULT = "default_color";
	public static final String PREFERENCE_COLOR_COMMENT = "comment_color";
	public static final String PREFERENCE_GHCI_PATH = "ghci_path";

	// The shared instance
	private static Activator plugin;
	
	private String activeEditorFile = null;
	
	/**
	 * The constructor
	 */
	public Activator() {
		// get informed when new parts are opened / activated
		getWorkbench().getActiveWorkbenchWindow().getActivePage().addPartListener(this);
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;	
		getPreferenceStore().addPropertyChangeListener(this);
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
	
	/**
	 * sets the default values for all preferences in the preference store
	 */
	@Override
	protected void initializeDefaultPreferences(IPreferenceStore store) {
		store.setDefault(PREFERENCE_BRACKET_MATCHING, true);
		store.setDefault(PREFERENCE_COLOR_BRACKET_MATCHING, "128,128,128");
		store.setDefault(PREFERENCE_COLOR_KEYWORD, "50,180,50");
		store.setDefault(PREFERENCE_COLOR_TYPE, "180,50,50");
		store.setDefault(PREFERENCE_COLOR_OPERATOR, "180,180,0");
		store.setDefault(PREFERENCE_COLOR_VALUE, "50,50,180");
		store.setDefault(PREFERENCE_COLOR_DEFAULT, "0,0,0");
		store.setDefault(PREFERENCE_COLOR_COMMENT, "50,50,50");
		store.setDefault(PREFERENCE_GHCI_PATH, "C:\\Program Files\\Haskell Platform\\2014.2.0.0\\bin\\ghci.exe");
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	public IPreferenceStore getPreferenceStore() {
		return super.getPreferenceStore();
	}
		
	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * is called when a value in the preference store has changed
	 * saves the open file references, closes all editors and reopens the files to enforce new coloring
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		IWorkbenchPage page = getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] editors = page.getEditorReferences();
		IEditorInput activeInput = page.getActiveEditor().getEditorInput();	
		page.closeAllEditors(true);
		
		try {
			for (IEditorReference editor : editors) {
				page.openEditor(editor.getEditorInput(), "de.fhflensburg.pd.group008.hsedit.editors.HaskellEditor");
			}
			// activate the old active editor/file
			page.openEditor(activeInput, "de.fhflensburg.pd.group008.hsedit.editors.HaskellEditor");
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Returns the path of the file in the currently active editor
	 * @return the absolute path in double quotes or null
	 */
	public String getActiveFilePath() {
		return activeEditorFile;
	}

	/**
	 * is called when a new part is activated
	 * if the new active part is an Editor and the open file ends with .hs,
	 * then save that path for starting it in ghci on demand
	 */
	@Override
	public void partActivated(IWorkbenchPart part) {
		if (part instanceof IEditorPart) {
			IEditorPart editPart = (IEditorPart)part;
			if (editPart.getEditorInput() instanceof IURIEditorInput) {
				URI activeInput = ((IURIEditorInput) editPart.getEditorInput()).getURI();
				activeEditorFile = "\"" + activeInput.getPath().substring(1) + "\"";
				if (!activeEditorFile.endsWith(".hs\""))
						activeEditorFile = null;
			}
		}		
	}
	
	/**
	 * is called when a part is deactivated
	 * set activeEditorFile to null because no active haskell file is open
	 */	
	@Override
	public void partDeactivated(IWorkbenchPart part) {
		activeEditorFile = null;
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {}

	@Override
	public void partClosed(IWorkbenchPart part) {}

	@Override
	public void partOpened(IWorkbenchPart part) {}
}
