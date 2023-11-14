package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import controle.EventoDAO;
import controle.UsuarioDAO;
import modelo.Evento;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame implements ActionListener, UpdatableView {
	private JTextField textField;
	private JPanel contentPanel;
	private JPanel eventsPanel;

	private JButton newEventButton;

	ArrayList<Evento> eventos = new ArrayList<Evento>();

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("rolê");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[80px][grow]"));

		JPanel headerPanel = new HeaderView();
		contentPane.add(headerPanel, "cell 0 0,grow");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, "cell 0 1,grow");
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		contentPanel = new JPanel();
		scrollPane.setViewportView(contentPanel);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel titlePanel = new JPanel();
		contentPanel.add(titlePanel);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		titlePanel.setBackground(new Color(0, 0, 0, 0));

		Usuario usuario = UsuarioDAO.getInstance().getUsuarioSelecionado();
		if (usuario != null) {
			JLabel lblNewLabel = new JLabel(usuario.getNome() + ", esses são seus próximos rolês");
			lblNewLabel.setFont(new Font("Inter", Font.BOLD, 24));
			titlePanel.add(lblNewLabel);
		}
		Component horizontalGlue = Box.createHorizontalGlue();
		titlePanel.add(horizontalGlue);
		JButton gerarRelatorio = new JButton("gerar relatório");
		titlePanel.add(gerarRelatorio);
		gerarRelatorio.setFont(new Font("Inter", Font.PLAIN, 15));
		gerarRelatorio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Document document = new Document();
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int userSelection = fileChooser.showSaveDialog(contentPane);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				 File fileToSave = fileChooser.getSelectedFile();
					try {
						PdfWriter.getInstance(document, new FileOutputStream(fileToSave.getAbsolutePath()+"Relatório.pdf"));
						document.open();
	
						eventos = EventoDAO.getInstance().list();
						
						int numEventos = eventos.size();
						document.add(new Paragraph("Você já participou de " + numEventos + " eventos."));
						
						double gastos = 24.9;
						document.add(new Paragraph("Você gastou R$" + gastos + " em insumos."));
						document.close();
						
						JOptionPane.showMessageDialog(null, "Relatório em PDF gerado com sucesso!");
					} catch (DocumentException | FileNotFoundException dex) {
						dex.printStackTrace();
					}
				}
			}
		});
		Component verticalStrut = Box.createVerticalStrut(10);
		contentPanel.add(verticalStrut);
		eventsPanel = new JPanel();
		eventsPanel.setBackground(Color.WHITE);
		eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
		update();
		contentPanel.add(eventsPanel);
		Component verticalStrut_3 = Box.createVerticalStrut(5);
		contentPanel.add(verticalStrut_3);
		JPanel newEvent = new RoundedPanel();
		contentPanel.add(newEvent);
		newEvent.setLayout(new BoxLayout(newEvent, BoxLayout.X_AXIS));
		textField = new JTextField();
		textField.setToolTipText("...digite um evento");
		newEvent.add(textField);
		textField.setColumns(10);
		newEventButton = new JButton("adicionar");
		newEventButton.addActionListener(this);
		newEventButton.setEnabled(!textField.getText().isEmpty());
		textField.addCaretListener(e -> {
			newEventButton.setEnabled(!textField.getText().isEmpty());
		});
		newEvent.add(newEventButton);
		newEvent.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		newEvent.setBorder(new EmptyBorder(10, 10, 10, 10));
		setVisible(true);
	}
	public static void main(String[] args) {
		MainWindow tela = new MainWindow();
	}
	public void update() {
		eventos = EventoDAO.getInstance().list();
		eventsPanel.removeAll();
		for (Evento event : eventos) {
			JPanel eventListItem = new EventoItemView(event, this);
			eventsPanel.add(eventListItem);
			Component verticalStrut = Box.createVerticalStrut(5);
			eventsPanel.add(verticalStrut);
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newEventButton) {
			if (textField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Por favor, digite um nome para o evento.");
			} else {
				Evento event = new Evento("", textField.getText(), "", null, null);
				EventoDAO.getInstance().insert(event);
				textField.setText("");
				update();
			}
		}
	}
}

