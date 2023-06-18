package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Evento;
import net.miginfocom.swing.MigLayout;
import javax.swing.Box;
import java.awt.Component;

public class TelaEvento extends JFrame {
	Evento event;
	
	public TelaEvento(Evento event) {
        setTitle(event.getNome());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[80px][grow]"));
        
        JPanel panel = new HeaderView();
        contentPane.add(panel, "cell 0 0,grow");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		contentPane.add(panel_1, "cell 0 1 1 2,grow");
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new EventoDetailView(event.getEmoji(), event.getNome(), event.getDataInicio(), "", event.getColor());
		panel_2.add(panel_3);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		panel_2.add(horizontalStrut);
		
		JPanel panel_4 = new InsumosDetailView(event);
		panel_2.add(panel_4);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut);
		
		JPanel panel_5 = new ParticipantesDetailView(event.getColor());
		panel_1.add(panel_5);
		
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public static void main(String[] args) {
//		TelaEvento tela = new TelaEvento("✨", "Rolê na Fac", "de 23 de maio às 22:00 a 24 de maio às 4:00", "Factory Antônio da Veiga", new Color(249, 236, 170));
//		tela.setVisible(true);
	}
}
