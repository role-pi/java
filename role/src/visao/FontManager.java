package visao;

public class FontManager {
	static Font inter {
		try {
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("A.ttf")));
		} catch (IOException|FontFormatException e) {
		     //Handle exception
		}
	}
}
