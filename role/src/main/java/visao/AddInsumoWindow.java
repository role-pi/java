package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.InsumoDAO;
import modelo.Evento;
import modelo.Insumo;
import modelo.Transacao;

public class AddInsumoWindow extends JFrame implements ActionListener {
    private JButton cadastrarButton;
    private JTextField nomeTextField;
    private JTextField descricaoTextField;
    private JTextField valorTextField;
    private JComboBox<String> tipoComboBox;
    
    private Evento event;
    private UpdatableView parentView;

    public AddInsumoWindow(Evento event, UpdatableView parentWindow) {
    	setResizable(false);
    	this.event = event;
    	this.parentView = parentWindow;
    	
        setTitle("Adicionar Insumo");
        setSize(632, 329);
        setLocationRelativeTo(null);
        
        Dimension labelSize = new Dimension(70, 40);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel panel_4 = new RoundedPanel();
        contentPane.add(panel_4);
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
        panel_4.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel_4.setMaximumSize(new Dimension(600, 250));
        panel_4.setFont(new Font("Inter", Font.BOLD, 13));

        JPanel panel_2 = new JPanel();
        panel_4.add(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setPreferredSize(labelSize);
        panel_2.add(nomeLabel);
        nomeTextField = new JTextField();
        panel_2.add(nomeTextField);

        JPanel panel_3 = new JPanel();
        panel_4.add(panel_3);
        panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

        JLabel tipoLabel = new JLabel("Tipo");
        tipoLabel.setPreferredSize(labelSize);
        panel_3.add(tipoLabel);
        tipoComboBox = new JComboBox<>(Insumo.allTipos());
        panel_3.add(tipoComboBox);

        JPanel panel_1 = new JPanel();
        panel_4.add(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

        JLabel descricaoLabel = new JLabel("Descrição");
        descricaoLabel.setPreferredSize(labelSize);
        panel_1.add(descricaoLabel);
        descricaoTextField = new JTextField();
        panel_1.add(descricaoTextField);

        JPanel panel = new JPanel();
        panel_4.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JLabel valorLabel = new JLabel("Valor");
        valorLabel.setPreferredSize(labelSize);
        panel.add(valorLabel);
        valorTextField = new JTextField();
        panel.add(valorTextField);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        panel_4.add(verticalStrut);

        cadastrarButton = new JButton("Adicionar");
        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_4.add(cadastrarButton);

        cadastrarButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == cadastrarButton) {
	        if (nomeTextField.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Nome não pode estar vazio.");
	        } else if (descricaoTextField.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Descrição não pode estar vazia.");
	        } else if (valorTextField.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Valor não pode estar vazio.");
	        } else {
	            cadastrarInsumo();
	            parentView.update();
	            dispose();
	        }
    	}
    }

    private void cadastrarInsumo() {
    	int tipo = Arrays.asList(Insumo.allTipos()).indexOf(String.valueOf(tipoComboBox.getSelectedItem()));
        String nome = nomeTextField.getText();
        String descricao = descricaoTextField.getText();
        double valor = Double.parseDouble(valorTextField.getText());

        Insumo insumo = new Insumo();
        insumo.setTipo(tipo);
        insumo.setNome(nome);
        insumo.setDescricao(descricao);
        insumo.setEvento(event);
        
        Transacao transacao = new Transacao();
        transacao.setValor(valor);
        insumo.setTransacao(transacao);
        
        InsumoDAO.getInstance().insert(insumo);
    }
}
