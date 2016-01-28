package de.fhflensburg.pd.group008.hsedit.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

/**
 * Configuration for the Haskell editor
 * defines the scanner class and the reconciler
 *
 */
public class HaskellConfiguration extends SourceViewerConfiguration {
	
	private HaskellScanner scanner;
	 
	protected HaskellScanner getHaskellScanner() {
		if (scanner == null)
			scanner = new HaskellScanner();
		return scanner;
	}
	
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {	
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr =	new DefaultDamagerRepairer(getHaskellScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);		
		return reconciler;
	}
}