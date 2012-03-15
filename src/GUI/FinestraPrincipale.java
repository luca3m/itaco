package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import sun.swing.SwingUtilities2;

import main.ElencoLinguaggi;
import main.FileItaco;

@SuppressWarnings("serial")
public class FinestraPrincipale extends JFrame {

	private JPanel contentPane;
	FileItaco fileItaco = new FileItaco();

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
		setBounds(100, 100, 1031, 861);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		final JTextPane Panecodice = new JTextPane();
		Panecodice.setBounds(200, 126, 795, 454);
		contentPane.add(Panecodice);
	
		
		JButton btnNewButton = new JButton("Salva File");
		btnNewButton.setIcon(new ImageIcon(FinestraPrincipale.class.getResource("/img/salva copia.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.isSaved()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser.getSelectedFile().getAbsolutePath());
					}
				}
			}
		});
		btnNewButton.setBounds(717, 45, 179, 69);
		contentPane.add(btnNewButton);
		
		JButton btnGeneraFile = new JButton("Apri File");
		btnGeneraFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				if (chooser.getSelectedFile() != null) {
					java.io.File fileSelezionato = chooser.getSelectedFile();
					fileItaco = new FileItaco(fileSelezionato.getAbsolutePath());
					Panecodice.setText(fileItaco.getContenuto());
				}
			}
		});
		btnGeneraFile.setIcon(new ImageIcon(FinestraPrincipale.class.getResource("/img/opengiusto.png")));
		btnGeneraFile.setBorderPainted(false);
		btnGeneraFile.setVerifyInputWhenFocusTarget(false);
		btnGeneraFile.setBounds(457, 45, 179, 69);
		contentPane.add(btnGeneraFile);
		
		JButton btnPulisci = new JButton("");
		btnPulisci.setBorderPainted(false);
		btnPulisci.setIcon(new ImageIcon(FinestraPrincipale.class.getResource("/img/tastoplay0.png")));
		btnPulisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fileItaco.isSaved()) {
					fileItaco.salvaFile(Panecodice.getText());
					fileItaco.esegui();
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser.getSelectedFile().getAbsolutePath());
					}
					fileItaco.esegui();
				}
			}
		});
		btnPulisci.setBounds(31, 35, 90, 97);
		contentPane.add(btnPulisci);
		
		JLabel lblLog = new JLabel("Log:");
		lblLog.setBounds(200, 600, 27, 16);
		contentPane.add(lblLog);
		
		final JTextPane PaneLogger = new JTextPane();
		PaneLogger.setBounds(199, 628, 795, 154);
		contentPane.add(PaneLogger);
		
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
		
		JMenu mnHelp = new JMenu("Aiuto");
		menuBar.add(mnHelp);
		
		JMenuItem help_sintassi = new JMenuItem("Sintassi");
		mnHelp.add(help_sintassi);
		
		JMenuItem help_autori = new JMenuItem("Autori");
		mnHelp.add(help_autori);
		
		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(FinestraPrincipale.class.getResource("/img/itacosenzasfondoblu.png")));
		lblImg.setBounds(18, 571, 168, 211);
		contentPane.add(lblImg);
		
		JButton btnNuovoFile = new JButton("Nuovo File");
		btnNuovoFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileItaco = new FileItaco();
				Panecodice.setText("");
			}
		});
		btnNuovoFile.setIcon(new ImageIcon(FinestraPrincipale.class.getResource("/img/newgiusto.png")));
		btnNuovoFile.setBorderPainted(false);
		btnNuovoFile.setBounds(200, 45, 179, 69);
		contentPane.add(btnNuovoFile);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.isSaved()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.JASMIN);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(FinestraPrincipale.class.getResource("/img/jasmin_icon.png")));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(18, 358, 117, 69);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.isSaved()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.CLASS);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(FinestraPrincipale.class.getResource("/img/javagiusto.png")));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBounds(18, 198, 117, 69);
		contentPane.add(btnNewButton_2);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.isSaved()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.C);
			}
		});
		button.setIcon(new ImageIcon(FinestraPrincipale.class.getResource("/img/linguaggioC.png")));
		button.setBorderPainted(false);
		button.setBounds(18, 279, 117, 67);
		contentPane.add(button);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.isSaved()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.RUBY);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(FinestraPrincipale.class.getResource("/img/ruby.png")));
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setBounds(18, 439, 117, 84);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Esporta in:");
		lblNewLabel.setBounds(31, 158, 103, 16);
		contentPane.add(lblNewLabel);
		
		Logger.getLogger("Itaco").addHandler(new Handler() {
			
			@Override
			public void publish(final LogRecord record) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						PaneLogger.setText(PaneLogger.getText() + record.getMessage());
					}
				});
			}
			
			@Override
			public void flush() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void close() throws SecurityException {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
