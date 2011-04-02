package t2s.ihm;

import org.eclipse.swt.widgets.*;

/**
 * Classe de Base des Interface Vocalse et Singer
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class InterfaceBase {

	protected final static Display ecran = new Display();
	
	public Display getDisplay()
	{
		return(ecran);
	}
}
