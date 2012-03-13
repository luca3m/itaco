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
import javax.swing.ImageIcon;

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
		setBounds(100, 100, 1024, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane Panecodice = new JTextPane();
		Panecodice.setBounds(199, 55, 795, 454);
		contentPane.add(Panecodice);
		
		JButton btnNewButton = new JButton("Compila ed Esegui");
		btnNewButton.setBounds(29, 289, 104, 104);
		contentPane.add(btnNewButton);
		
		JButton btnGeneraFile = new JButton("Compila");
		btnGeneraFile.setBounds(29, 171, 104, 104);
		contentPane.add(btnGeneraFile);
		
		JButton btnPulisci = new JButton("Pulisci");
		btnPulisci.setBounds(29, 55, 104, 104);
		contentPane.add(btnPulisci);
		
		JCheckBox chckbxFileJasmin = new JCheckBox("Genera file Jasmin");
		chckbxFileJasmin.setBounds(19, 430, 225, 23);
		contentPane.add(chckbxFileJasmin);
		
		JCheckBox chckbxGeneraFilePython = new JCheckBox("Genera file Python");
		chckbxGeneraFilePython.setBounds(19, 458, 225, 23);
		contentPane.add(chckbxGeneraFilePython);
		
		JCheckBox chckbxGeneraFileJava = new JCheckBox("Genera file Java");
		chckbxGeneraFileJava.setBounds(19, 486, 225, 23);
		contentPane.add(chckbxGeneraFileJava);
		
		JLabel lblLog = new JLabel("Log:");
		lblLog.setBounds(199, 532, 27, 16);
		contentPane.add(lblLog);
		
		JTextPane PaneLogger = new JTextPane();
		PaneLogger.setBounds(199, 555, 795, 154);
		contentPane.add(PaneLogger);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1024, 23);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNewMenu = new JMenu("Compila");
		menuBar.add(mnNewMenu);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(FinestraPrincipale.class.getResource("/img/itaco2trasp.png")));
		lblImg.setBounds(19, 555, 173, 138);
		contentPane.add(lblImg);
	}
}
