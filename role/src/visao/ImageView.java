package visao;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageView extends JPanel{

    private BufferedImage image;
    
    Dimension size;
    Double alignment;

    public ImageView(String src, Dimension size, Double alignment) {
       try {                
          image = ImageIO.read(new File(src));
          this.size = size;
          this.alignment = alignment;
       } catch (IOException ex) {
            // handle exception...
    	   System.out.println(ex);
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, (int)((getWidth()-size.width)*alignment), (getHeight()-size.height)/2, this); // see javadoc for more info on the parameters            
    }

}