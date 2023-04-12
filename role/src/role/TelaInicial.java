package role;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class TelaInicial extends JFrame {
	public TelaInicial() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel);
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
		
		//setUndecorated(true);
	    //getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		System.setProperty( "apple.awt.application.appearance", "NSAppearanceNameDarkAqua" );
		//if( SystemInfo.isMacFullWindowContentSupported )
		    getRootPane().putClientProperty( "apple.awt.transparentTitleBar", true );
		
		UIDefaults uiDefaults = UIManager.getDefaults();
		uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.black));
		uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.white));
		setDefaultLookAndFeelDecorated(true);
		
		JLabel lblNewLabel = new JLabel("Rolê");
		lblNewLabel.setForeground(Color.WHITE);
		panel.add(lblNewLabel);
//		ImageIcon icon = new ImageIcon(TelaInicial.class.getResource("/role/Logo.png"));
//		lblNewLabel.setIcon(new ImageIcon(getScaledImage(icon.getImage(), 450/3, 228/3)));
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 13));
	}

	public static void main(String[] args) {
		TelaInicial tela = new TelaInicial();
		tela.setVisible(true);
        tela.setBounds(40,40,700,600); 
	}

	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	
	    return resizedImg;
	}
}