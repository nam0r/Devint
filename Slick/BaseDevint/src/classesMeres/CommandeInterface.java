/**
 * 
 * Cette classe doit etre modifie(declarer les nouvelles actions).
 * 
 * Cette classe repertorie toutes les actions affectees aux differentes touches possibles.
 * Si vous voulez rajouter des actions dans le jeu, vous devez les declarer ici.
 * Le keyListener a implementer en consequence se trouve dans la classe mainFrame.
 */

package classesMeres;

public interface CommandeInterface {
	public void upPerformed(); // touche haut
	public void downPerformed(); // touche bas
	public void stopPerformed(); // touche echap
	public void enterPerformed(); // touche entrer
	public void spacePerformed(); // touche espace
	public void backPerformed(); // touche backspace
	public void helpPerformed(); // touche F1
	public void reglesPerformed(); // touche F2
	public void scorePerformed(); // touche F3
	public void touchesPerformed(); // touche F4
	public void infoPerformed(); // touche F5
	public void ctrlPerformed(); // touche ctrl
	public void touche1Performed(); // touche 1
	public void touche2Performed(); // touche 2
	public void touche3Performed(); // touche 3
	public void touche4Performed(); // touche 4
}
