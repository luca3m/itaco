package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class FinestraPrincipale extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinestraPrincipale frame = new FinestraPrincipale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FinestraPrincipale() {
		setTitle("ITAco");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 753);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodiceDaEseguire = new JLabel("Codice da compilare:");
		lblCodiceDaEseguire.setBounds(48, 315, 132, 16);
		contentPane.add(lblCodiceDaEseguire);
		
		JTextPane Panecodice = new JTextPane();
		Panecodice.setBounds(48, 336, 461, 196);
		contentPane.add(Panecodice);
		
		JButton btnNewButton = new JButton("Compila ed Esegui");
		btnNewButton.setBounds(552, 336, 225, 34);
		contentPane.add(btnNewButton);
		
		JButton btnGeneraFile = new JButton("Compila");
		btnGeneraFile.setBounds(552, 375, 225, 34);
		contentPane.add(btnGeneraFile);
		
		JButton btnPulisci = new JButton("Pulisci");
		btnPulisci.setBounds(552, 414, 225, 34);
		contentPane.add(btnPulisci);
		
		JCheckBox chckbxFileJasmin = new JCheckBox("Genera file Jasmin");
		chckbxFileJasmin.setBounds(552, 453, 225, 23);
		contentPane.add(chckbxFileJasmin);
		
		JCheckBox chckbxGeneraFilePython = new JCheckBox("Genera file Python");
		chckbxGeneraFilePython.setBounds(552, 481, 225, 23);
		contentPane.add(chckbxGeneraFilePython);
		
		JCheckBox chckbxGeneraFileJava = new JCheckBox("Genera file Java");
		chckbxGeneraFileJava.setBounds(552, 509, 225, 23);
		contentPane.add(chckbxGeneraFileJava);
		
		JLabel lblLog = new JLabel("Log:");
		lblLog.setBounds(48, 537, 27, 16);
		contentPane.add(lblLog);
		
		JTextPane PaneLogger = new JTextPane();
		PaneLogger.setBounds(48, 558, 729, 104);
		contentPane.add(PaneLogger);
		
		JButton btnClear = new JButton("Pulisci");
		btnClear.setBounds(48, 667, 357, 44);
		contentPane.add(btnClear);
		
		JButton btnCompila = new JButton("Salva Log su File");
		btnCompila.setBounds(410, 667, 367, 44);
		contentPane.add(btnCompila);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(28, 39, 129, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		mnNewMenu.setBounds(28, 41, 95, 19);
		contentPane.add(mnNewMenu);
	}
}
