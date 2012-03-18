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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import main.ElencoLinguaggi;
import main.FileItaco;
import java.awt.Font;

@SuppressWarnings("serial")
public class FinestraPrincipale extends JFrame {

	private JPanel contentPane;
	FileItaco fileItaco = new FileItaco();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Se ci sono parametri a riga di comando allora non viene avviata
		// l'interfaccia grafica ma solo quella testuale, ovvero
		// si occupa soltanto di compilare il codice passato come primo
		// argomento
		// nel formato passato come secondo argomento. Se il secondo argomento
		// non è presente allora compila nel formato .class
		if (args.length > 0) {
			String fileDaCompilare = args[0];
			ElencoLinguaggi linguaggioTarget = ElencoLinguaggi.CLASS;
			if (args.length > 1) {
				linguaggioTarget = ElencoLinguaggi.valueOf(args[1].toUpperCase());
			}
			FileItaco file = new FileItaco(fileDaCompilare);
			file.compila(linguaggioTarget);
			return;
		}
		
		// Avvio l'interfaccia grafica
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
		}

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
		setResizable(false);
		setTitle("ITAco");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scroller = new JScrollPane();
		scroller.setBounds(199, 113, 795, 435);
		contentPane.add(scroller);
		final JTextArea Panecodice = new JTextArea();
		Panecodice.setFont(new Font("Monospaced", Panecodice.getFont()
				.getStyle(), 14));
		Panecodice.setText("scrivi \"Ciao Mondo!\".");
		// Panecodice.setBounds(199, 113, 795, 435);
		scroller.setViewportView(Panecodice);
		// contentPane.add(Panecodice);

		JButton btnNewButton = new JButton("Salva File");
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setIcon(new ImageIcon(FinestraPrincipale.class
				.getResource("/img/salva copia.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter(
							"Sorgente ITAco", "ita", "ITA"));
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
				}
			}
		});
		btnNewButton.setBounds(717, 33, 179, 69);
		contentPane.add(btnNewButton);

		JButton btnGeneraFile = new JButton("Apri File");
		btnGeneraFile.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnGeneraFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter(
						"Sorgente ITAco", "ita", "ITA"));
				chooser.showOpenDialog(null);
				if (chooser.getSelectedFile() != null) {
					java.io.File fileSelezionato = chooser.getSelectedFile();
					fileItaco = new FileItaco(fileSelezionato.getAbsolutePath());
					Panecodice.setText(fileItaco.getContenuto());
				}
			}
		});
		btnGeneraFile.setIcon(new ImageIcon(FinestraPrincipale.class
				.getResource("/img/opengiusto.png")));
		btnGeneraFile.setBorderPainted(false);
		btnGeneraFile.setVerifyInputWhenFocusTarget(false);
		btnGeneraFile.setBounds(457, 33, 179, 69);
		contentPane.add(btnGeneraFile);

		JButton btnPulisci = new JButton("");
		btnPulisci.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnPulisci.setToolTipText("Compila ed esegui");
		btnPulisci.setBorderPainted(false);
		btnPulisci.setIcon(new ImageIcon(FinestraPrincipale.class
				.getResource("/img/Play.png")));
		btnPulisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
					fileItaco.esegui();
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter(
							"Sorgente ITAco", "ita", "ITA"));
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
					fileItaco.esegui();
				}
			}
		});
		btnPulisci.setBounds(18, 23, 117, 97);
		contentPane.add(btnPulisci);

		JScrollPane scroller2 = new JScrollPane();
		scroller2.setBounds(199, 570, 795, 126);
		contentPane.add(scroller2);
		final JTextArea PaneLogger = new JTextArea();
		PaneLogger.setLineWrap(true);
		PaneLogger.setEditable(false);
		PaneLogger.setText("Log:");
		scroller2.setViewportView(PaneLogger);
		// PaneLogger.setBounds(199, 570, 795, 126);
		// contentPane.add(PaneLogger);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1024, 23);
		contentPane.add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem file_Nuovo = new JMenuItem("Nuovo");
		file_Nuovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileItaco = new FileItaco();
				Panecodice.setText("");
			}
		});
		mnFile.add(file_Nuovo);

		JMenuItem file_Apri = new JMenuItem("Apri");
		file_Apri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter(
						"Sorgente ITAco", "ita", "ITA"));
				chooser.showOpenDialog(null);
				if (chooser.getSelectedFile() != null) {
					java.io.File fileSelezionato = chooser.getSelectedFile();
					fileItaco = new FileItaco(fileSelezionato.getAbsolutePath());
					Panecodice.setText(fileItaco.getContenuto());
				}
			}
		});
		mnFile.add(file_Apri);

		JMenuItem file_SalvaConNome = new JMenuItem("Salva");
		file_SalvaConNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter(
							"Sorgente ITAco", "ita", "ITA"));
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
				}
			}
		});
		mnFile.add(file_SalvaConNome);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem file_Esci = new JMenuItem("Esci");
		file_Esci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		mnFile.add(file_Esci);

		JMenu mnCompila = new JMenu("Compila");
		menuBar.add(mnCompila);

		JMenuItem compila_compilaEsegui = new JMenuItem("Compila ed Esegui");
		compila_compilaEsegui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
					fileItaco.esegui();
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
					fileItaco.esegui();
				}
			}
		});
		mnCompila.add(compila_compilaEsegui);

		JMenuItem compila_compila = new JMenuItem("Compila");
		compila_compila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter(
							"Sorgente ITAco", "ita", "ITA"));
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.CLASS);
			}
		});
		mnCompila.add(compila_compila);

		JSeparator separator = new JSeparator();
		mnCompila.add(separator);

		JMenuItem compila_esportaC = new JMenuItem("Esporta in C");
		compila_esportaC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter(
							"Sorgente ITAco", "ita", "ITA"));
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.C);
			}
		});
		mnCompila.add(compila_esportaC);

		JMenuItem compila_EsportaInRuby = new JMenuItem("Esporta in Ruby");
		compila_EsportaInRuby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter(
							"Sorgente ITAco", "ita", "ITA"));
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.RUBY);
			}
		});
		mnCompila.add(compila_EsportaInRuby);

		JMenuItem Compila_esportaJasmin = new JMenuItem("Esporta in Jasmin");
		Compila_esportaJasmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter(
							"Sorgente ITAco", "ita", "ITA"));
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.JASMIN);
			}
		});
		mnCompila.add(Compila_esportaJasmin);

		JMenu mnHelp = new JMenu("Aiuto");
		menuBar.add(mnHelp);

		JMenuItem help_sintassi = new JMenuItem("Sintassi");
		help_sintassi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinestraSintassi sintassi = new FinestraSintassi();
				sintassi.setVisible(true);
			}
		});
		mnHelp.add(help_sintassi);

		JMenuItem help_autori = new JMenuItem("Autori");
		help_autori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogoAutori autori = new DialogoAutori();
				autori.setVisible(true);
			}
		});
		mnHelp.add(help_autori);

		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(FinestraPrincipale.class
				.getResource("/img/itacosenzasfondoblu.png")));
		lblImg.setBounds(18, 485, 168, 211);
		contentPane.add(lblImg);

		JButton btnNuovoFile = new JButton("Nuovo File");
		btnNuovoFile.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNuovoFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileItaco = new FileItaco();
				Panecodice.setText("");
			}
		});
		btnNuovoFile.setIcon(new ImageIcon(FinestraPrincipale.class
				.getResource("/img/newgiusto.png")));
		btnNuovoFile.setBorderPainted(false);
		btnNuovoFile.setBounds(200, 33, 179, 69);
		contentPane.add(btnNuovoFile);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter(
							"Sorgente ITAco", "ita", "ITA"));
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.JASMIN);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(FinestraPrincipale.class
				.getResource("/img/jasmin_icon.png")));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(18, 308, 117, 69);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter(
							"Sorgente ITAco", "ita", "ITA"));
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.CLASS);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(FinestraPrincipale.class
				.getResource("/img/javagiusto.png")));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBounds(18, 148, 117, 69);
		contentPane.add(btnNewButton_2);

		JButton button = new JButton("");
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter(
							"Sorgente ITAco", "ita", "ITA"));
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.C);
			}
		});
		button.setIcon(new ImageIcon(FinestraPrincipale.class
				.getResource("/img/linguaggioC.png")));
		button.setBorderPainted(false);
		button.setBounds(18, 229, 117, 67);
		contentPane.add(button);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileItaco.fileSalvato()) {
					fileItaco.salvaFile(Panecodice.getText());
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter(
							"Sorgente ITAco", "ita", "ITA"));
					chooser.showSaveDialog(null);
					if (chooser.getSelectedFile() != null) {
						fileItaco.salvaFile(Panecodice.getText(), chooser
								.getSelectedFile().getAbsolutePath());
					}
				}
				fileItaco.compila(ElencoLinguaggi.RUBY);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(FinestraPrincipale.class
				.getResource("/img/ruby.png")));
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setBounds(18, 389, 117, 84);
		contentPane.add(btnNewButton_3);

		JLabel lblNewLabel = new JLabel("Esporta in:");
		lblNewLabel.setBounds(41, 132, 103, 16);
		contentPane.add(lblNewLabel);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(199, 552, 795, 12);
		contentPane.add(separator_2);

		Logger.getLogger("Itaco").addHandler(new Handler() {

			@Override
			public void publish(final LogRecord record) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						PaneLogger.setText(PaneLogger.getText() + "\n"
								+ record.getMessage());
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
