package de.fhflensburg.pd.group008.hsedit.console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Connects the Output of the GHCI with the output from the console
 *
 */
public class GHCIOutput implements Runnable {
	
	private BufferedWriter console;
	private BufferedReader ghci;
	
	public GHCIOutput(OutputStream consoleoutput, InputStream ghciinput) {
		console = new BufferedWriter(new OutputStreamWriter(consoleoutput));
		ghci = new BufferedReader(new InputStreamReader(ghciinput));
	}

	@Override
	public void run() {
		String line;
		try {
			while((line = ghci.readLine()) != null) {
				console.write(line + "\n");
				console.flush();
			}
		} catch (IOException e) {
		}
		
	}

}
