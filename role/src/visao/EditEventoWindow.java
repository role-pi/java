package visao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.httprpc.sierra.VerticalAlignment;
import org.httprpc.sierra.DatePicker;
import org.httprpc.sierra.TimePicker;

import controle.EventoDAO;
import modelo.Evento;
import modelo.Insumo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EditEventoWindow extends JFrame implements ActionListener {
    private JTextField emojiTextField;
    private JTextField nomeTextField;
    private JTextField localTextField;
    private DatePicker dataInicioPicker;
    private TimePicker horaInicioPicker;
    private DatePicker dataFimPicker;
    private TimePicker horaFimPicker;
    
    Evento evento;
    EventoDetailView parentWindow;
    
    public EditEventoWindow(Evento evento, EventoDetailView parentWindow) {
    	this.evento = evento;
    	this.parentWindow = parentWindow;
    	
        setTitle("Editar Evento");
	    setSize(418, 371);
        setResizable(false);
	    setLocationRelativeTo(null);

        Dimension labelSize = new Dimension(90, 40);
	
	    JPanel contentPane = new JPanel();
	    setContentPane(contentPane);
	    contentPane.setBackground(Color.WHITE);
	    contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
	    
	    JPanel panel_4 = new RoundedPanel();
	    contentPane.add(panel_4);
	    panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
	    panel_4.setBorder(new EmptyBorder(20, 20, 20, 20));
	    
        panel_4.setSize(new Dimension(500, 100));
        panel_4.setMaximumSize(new Dimension(600, 250));
        panel_4.setFont(new Font("Inter", Font.BOLD, 13));
	    
	    JPanel panel_2 = new JPanel();
	    panel_4.add(panel_2);
	    panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
	    
        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setPreferredSize(labelSize);
        panel_2.add(nomeLabel);
        nomeTextField = new JTextField();
        nomeTextField.setText(evento.getNome());
        panel_2.add(nomeTextField);
        
        JPanel panel_7 = new JPanel();
	    panel_4.add(panel_7);
	    panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
	    
        JLabel emojiLabel = new JLabel("Emoji");
        emojiLabel.setPreferredSize(labelSize);
        panel_7.add(emojiLabel);
        emojiTextField = new JTextField();
        emojiTextField.setText(evento.getEmoji());
        panel_7.add(emojiTextField);
	    
	    JPanel panel_1 = new JPanel();
	    panel_4.add(panel_1);
	    panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
	
	    JLabel localLabel = new JLabel("Local");
	    localLabel.setPreferredSize(labelSize);
	    panel_1.add(localLabel);
	    localTextField = new JTextField();
        localTextField.setText(evento.getLocal());
	    panel_1.add(localTextField);
	    
	    JPanel panel_3 = new JPanel();
	    panel_4.add(panel_3);
	    panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
	
	    JLabel dataInicioLabel = new JLabel("Data de início");
	    dataInicioLabel.setPreferredSize(labelSize);
	    panel_3.add(dataInicioLabel);
        
        JPanel panel = new JPanel();
        panel_3.add(panel);
        
        var date = LocalDate.now();
        dataInicioPicker = new DatePicker();
        panel.add(dataInicioPicker);

        dataInicioPicker.setDate(date);
        dataInicioPicker.setPopupVerticalAlignment(VerticalAlignment.TOP);
        
        horaInicioPicker = new TimePicker();

        var time = LocalTime.now();
        horaInicioPicker.setTime(time);
        horaInicioPicker.setPopupVerticalAlignment(VerticalAlignment.TOP);

        panel.add(horaInicioPicker);
        
	    JPanel panel_5 = new JPanel();
	    panel_4.add(panel_5);
	    panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
	
	    JLabel dataFimLabel = new JLabel("Data de fim");
	    dataFimLabel.setPreferredSize(labelSize);
	    panel_5.add(dataFimLabel);
	    
	    JPanel panel_6 = new JPanel();
	    panel_5.add(panel_6);
	    
	    dataFimPicker = new DatePicker();
	    panel_6.add(dataFimPicker);

	    dataFimPicker.setDate(date);
	    dataFimPicker.setPopupVerticalAlignment(VerticalAlignment.TOP);
	        
	    horaFimPicker = new TimePicker();
	    
	    horaFimPicker.setTime(time);
	    horaFimPicker.setPopupVerticalAlignment(VerticalAlignment.TOP);

        panel_6.add(horaFimPicker);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        panel_4.add(verticalStrut);
    
        JButton cadastrarButton = new JButton("Atualizar");
        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cadastrarButton.addActionListener(this);
        panel_4.add(cadastrarButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (nomeTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nome não pode estar vazio."); 
//		} else if (emojiTextField.getText().length() > 1 || !isEmoji(emojiTextField.getText())) {
//			JOptionPane.showMessageDialog(this, "Valor para o campo emoji é inválido."); 
		} else {
			LocalDateTime dataInicio = dataInicioPicker.getDate().atTime(horaInicioPicker.getTime());
			LocalDateTime dataFim = dataFimPicker.getDate().atTime(horaFimPicker.getTime());

			if (dataInicio.isAfter(dataFim)) {
				JOptionPane.showMessageDialog(this, "Data de fim não pode anteceder a data de início."); 
			} else {
				evento.setEmoji(emojiTextField.getText());
				evento.setNome(nomeTextField.getText());
				evento.setColor(Evento.corEmoji(emojiTextField.getText()));
				evento.setLocal(localTextField.getText());
				evento.setDataInicio(dataInicio);
				evento.setDataFim(dataFim);
				parentWindow.update();
				dispose();
			}
		}
	}
	
//	private static boolean isEmoji(String message){
//	    return message.matches("(?:[\uD83C\uDF00-\uD83D\uDDFF]|[\uD83E\uDD00-\uD83E\uDDFF]|" +
//	            "[\uD83D\uDE00-\uD83D\uDE4F]|[\uD83D\uDE80-\uD83D\uDEFF]|" +
//	            "[\u2600-\u26FF]\uFE0F?|[\u2700-\u27BF]\uFE0F?|\u24C2\uFE0F?|" +
//	            "[\uD83C\uDDE6-\uD83C\uDDFF]{1,2}|" +
//	            "[\uD83C\uDD70\uD83C\uDD71\uD83C\uDD7E\uD83C\uDD7F\uD83C\uDD8E\uD83C\uDD91-\uD83C\uDD9A]\uFE0F?|" +
//	            "[\u0023\u002A\u0030-\u0039]\uFE0F?\u20E3|[\u2194-\u2199\u21A9-\u21AA]\uFE0F?|[\u2B05-\u2B07\u2B1B\u2B1C\u2B50\u2B55]\uFE0F?|" +
//	            "[\u2934\u2935]\uFE0F?|[\u3030\u303D]\uFE0F?|[\u3297\u3299]\uFE0F?|" +
//	            "[\uD83C\uDE01\uD83C\uDE02\uD83C\uDE1A\uD83C\uDE2F\uD83C\uDE32-\uD83C\uDE3A\uD83C\uDE50\uD83C\uDE51]\uFE0F?|" +
//	            "[\u203C\u2049]\uFE0F?|[\u25AA\u25AB\u25B6\u25C0\u25FB-\u25FE]\uFE0F?|" +
//	            "[\u00A9\u00AE]\uFE0F?|[\u2122\u2139]\uFE0F?|\uD83C\uDC04\uFE0F?|\uD83C\uDCCF\uFE0F?|" +
//	            "[\u231A\u231B\u2328\u23CF\u23E9-\u23F3\u23F8-\u23FA]\uFE0F?)+") || message.isEmpty();
//	}
}