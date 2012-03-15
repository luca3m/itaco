package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import compilatore.EccezioneSemantica;
import compilatore.JasminException;
import compilatore.JasminTarget;

import edu.tum.cup2.generator.exceptions.GeneratorException;
import edu.tum.cup2.parser.exceptions.LRParserException;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class FinestraPrincipale extends JFrame {

	private JPanel contentPane;
	String percorsoFile = "prova.ita";
	
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
		
		
		final JTextPane Panecodice = new JTextPane();
		Panecodice.setBounds(199, 55, 795, 454);
		contentPane.add(Panecodice);
	
		
		JButton btnNewButton = new JButton("Salva File");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = "";
				file = Panecodice.getText();
				try {
				      FileOutputStream file1 = new FileOutputStream(percorsoFile);
				      PrintStream Output = new PrintStream(file1);
				      Output.print(file);
				      file1.close();
				    } catch (IOException e1) {
				    	  System.out.println("Errore: " + e1);
				          System.exit(1);
				        }
			}
		});
		btnNewButton.setBounds(29, 250, 104, 35);
		contentPane.add(btnNewButton);
		
		JButton btnGeneraFile = new JButton("Open File");
		btnGeneraFile.setBounds(29, 208, 104, 41);
		contentPane.add(btnGeneraFile);
		
		JButton btnPulisci = new JButton("Compila");
		btnPulisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String file = "";
				file = Panecodice.getText();
				try {
				      FileOutputStream file1 = new FileOutputStream(percorsoFile);
				      PrintStream Output = new PrintStream(file1);
				      Output.print(file);
				      file1.close();
				    } catch (IOException e) {
				    	  System.out.println("Errore: " + e);
				          System.exit(1);
				        }}});
		try {
			JasminTarget.compilaFile(percorsoFile, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneratorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LRParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JasminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EccezioneSemantica e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnPulisci.setBounds(29, 55, 104, 104);
		contentPane.add(btnPulisci);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1024, 23);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem file_Nuovo = new JMenuItem("Nuovo");
		mnFile.add(file_Nuovo);
		
		JMenuItem file_Apri = new JMenuItem("Apri...");
		mnFile.add(file_Apri);
		
		JMenuItem file_SalvaConNome = new JMenuItem("Salva con nome...");
		mnFile.add(file_SalvaConNome);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem file_Esci = new JMenuItem("Esci");
		mnFile.add(file_Esci);
		
		JMenu mnCompila = new JMenu("Compila");
		menuBar.add(mnCompila);
		
		JMenuItem compila_compilaEsegui = new JMenuItem("Compila ed Esegui");
		mnCompila.add(compila_compilaEsegui);
		
		JMenuItem compila_compila = new JMenuItem("Compila");
		mnCompila.add(compila_compila);
		
		JSeparator separator = new JSeparator();
		mnCompila.add(separator);
		
		JMenuItem compila_esportaC = new JMenuItem("Esporta in C");
		mnCompila.add(compila_esportaC);
		
		JMenuItem compila_EsportaInRuby = new JMenuItem("Esporta in Ruby");
		mnCompila.add(compila_EsportaInRuby);
		
		JMenuItem Compila_esportaJasmin = new JMenuItem("Esporta in Jasmin");
		mnCompila.add(Compila_esportaJasmin);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem help_sintassi = new JMenuItem("Sintassi");
		mnHelp.add(help_sintassi);
		
		JMenuItem help_autori = new JMenuItem("Autori");
		mnHelp.add(help_autori);
		
		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(FinestraPrincipale.class.getResource("/img/22222.jpg")));
		lblImg.setBounds(19, 498, 168, 211);
		contentPane.add(lblImg);
		
		JButton btnNuovoFile = new JButton("Nuovo File");
		btnNuovoFile.setBounds(29, 171, 104, 35);
		contentPane.add(btnNuovoFile);
		
		final JTextPane logExecPane = new JTextPane();
		logExecPane.setEditable(false);
		logExecPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
		});
		logExecPane.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				logExecPane.setCaretPosition(logExecPane.getText().length());
			}
		});
		logExecPane.setBounds(209, 521, 560, 144);
		contentPane.add(logExecPane);
	}
}
