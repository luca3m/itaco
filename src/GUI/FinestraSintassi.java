package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

@SuppressWarnings("serial")
public class FinestraSintassi extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public FinestraSintassi() {
		setTitle("Sintassi ITAco");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 497, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scroller = new JScrollPane();
		
		JTextPane txtpnDefinireUna = new JTextPane();
		txtpnDefinireUna.setFont(new Font("Monospaced", txtpnDefinireUna.getFont().getStyle(), 14));
		txtpnDefinireUna.setEditable(false);
		txtpnDefinireUna.setText("Tutte le istruzioni terminano con una virgola, l'ultima invece termina con punto\n\n# Definire una variabile:\nintero nome,\n\n# Definire un vettore:\nvettore nome[dimensione],\n\n# Assegnamento di una variabile o di un elemento vettore:\n3+1 -> nome, \n4+2 -> nome[0],\n\n# Istruzione condizionale se:\nse variabile > 0:\n   scrivi \"Positiva\";\naltrimenti:\n  scrivi \"Negativa\".\n\n# Istruzione iterativa finché:\nfinche i > 0:\n   scrivi i,\n   i-1 -> i.\n\n# Definire una funzione:\nfunzione somma(intero addendo1|intero addendo2) -> intero risultato:\n    addendo1 + addendo2 -> risultato.\n\n# Eseguire una funzione:\n   somma(3|4) -> risultato,\n\n# Prendere un numero da tastiera e lo salva in una variabile\nleggi variabile\n# o in un elemento di un vettore\nleggi vettore[indice]\n\n# Scrive una stringa su schermo o una variabile\nscrivi \"Ciao\"\nscrivi variabile");
		
		contentPane.add(scroller, BorderLayout.CENTER);
		scroller.setViewportView(txtpnDefinireUna);
	}

}
