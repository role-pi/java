package visao;

import java.awt.*;

import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
	
	protected Color gradient = null;
    /** Sets if it has an High Quality view */
    protected boolean highQuality = true;
    /** Double values for Horizontal and Vertical radius of corner arcs */
    protected Dimension arcs = new Dimension(16, 16);
    /** Distance between shadow border and opaque panel border */
    protected int shadowGap = 0;
    
	//FOLLOWING CODES GOES HERE
    
    public RoundedPanel() {
        super(null);
        setOpaque(false);
    }
    
    public RoundedPanel(Color gradient) {
        super();
        this.gradient = gradient;
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;

        //Sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //Draws the rounded opaque panel with borders.
        if (gradient != null) {
	        GradientPaint gp = new GradientPaint(0, 0,
	        		gradient, 40, getHeight(),
	                getBackground());
	        graphics.setPaint(gp);
        } else {
        	graphics.setColor(getBackground());
        }
        graphics.fillRoundRect(shadowGap, shadowGap, width - shadowGap,
        height - shadowGap, arcs.width, arcs.height);
//        graphics.setColor(getForeground());
//        graphics.setStroke(new BasicStroke(strokeSize));
//        graphics.drawRoundRect(0, 0, width - shadowGap,
//        height - shadowGap, arcs.width, arcs.height);

        //Sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
    }
} 