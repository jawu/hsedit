package de.fhflensburg.pd.group008.hsedit.console;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

/**
 * Handles the haskell.run command through starting the GHCIWrapper *
 */
public class HaskellLauncher implements IHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
        
		new Thread(new GHCIWrapper()).start();
		return null;
	}
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {}

	@Override
	public void dispose() {}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {}
}
