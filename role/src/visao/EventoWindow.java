package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Evento;
import net.miginfocom.swing.MigLayout;
import javax.swing.Box;
import java.awt.Component;

public class EventoWindow extends JFrame {
	Evento evento;
	MainWindow parentWindow;
	
	public EventoWindow(Evento evento, MainWindow parentWindow) {
        setTitle(evento.getNome());
        
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
		
		JPanel panel_3 = new EventoDetailView(evento);
		panel_2.add(panel_3);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		panel_2.add(horizontalStrut);
		
		JPanel panel_4 = new InsumosDetailView(evento);
		panel_2.add(panel_4);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut);
		
		JPanel panel_5 = new ParticipantesDetailView(evento);
		panel_1.add(panel_5);
		
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                parentWindow.update();
            }
        });
	}
	
	public static void main(String[] args) {
		
	}
}
