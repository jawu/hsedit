package de.fhflensburg.pd.group008.hsedit.console;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;

import de.fhflensburg.pd.group008.hsedit.Activator;

/**
 * Starts the ghci as own process 
 * and handles communication between ghci and console in seperated threads *
 */
public class GHCIWrapper implements Runnable {

	@Override
	public void run() {
		try {

			ProcessBuilder process;
			final String GHCIPATH =  Activator.getDefault().getPreferenceStore().getString(Activator.PREFERENCE_GHCI_PATH);
			String filepath = Activator.getDefault().getActiveFilePath();
			
			// create console for ghci
			IConsoleManager conMan = ConsolePlugin.getDefault().getConsoleManager();
			IOConsole console = new IOConsole("GHCI Haskell Console - " + filepath, null);
	        conMan.addConsoles(new IConsole[]{console});
	        conMan.showConsoleView(console);
			
			if (filepath == null) {
				console.newOutputStream().write("No haskell file is opened in an active editor.");				
			} else {
				
				// start ghci
				process = new ProcessBuilder(GHCIPATH, filepath);
				Process ghci = process.start();		
				
				// connect ghci with console
				Thread output = new Thread(new GHCIOutput(console.newOutputStream(), ghci.getInputStream()));
				Thread input = new Thread(new GHCIInput(console.getInputStream(), ghci.getOutputStream()));
				
				output.start();
				input.start();	
				ghci.waitFor();
			}			
			
		} catch (Exception e) {}
	}
}
