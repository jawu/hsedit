package de.fhflensburg.pd.group008.hsedit.console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Connects the Input of the GHCI with the input from the console
 *
 */
public class GHCIInput implements Runnable {
	
	private BufferedReader console;
	private BufferedWriter ghci;
	
	public GHCIInput(InputStream consoleinput, OutputStream ghcioutput) {
		console = new BufferedReader(new InputStreamReader(consoleinput));
		ghci = new BufferedWriter(new OutputStreamWriter(ghcioutput));
	}

	@Override
	public void run() {
		String line;
		try {
			while((line = console.readLine()) != null) {
				ghci.write(line + "\n");
				ghci.flush();
			}
		} catch (IOException e) {
		}
		
	}

}
