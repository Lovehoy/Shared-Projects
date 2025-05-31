import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
/**
 * Provides styling options like Hex color varibles
 *
 * @author PHLovejoy 
 */

public class Style{
   /***************************************************************************
    *  Colors
    ***************************************************************************/
    /**
     *  The color black (0, 0, 0).
     */
    public static final Color WHITE = Color.WHITE;
    
    /**
     *  The color light grey (0xb5b4a8).
     */
    public static final Color MIDLIGHT = new Color(0xb5b4a8);

    /**
     *  The color dark grey (0xe2e1d2). #4D4B42
     */
    public static final Color MIDDARK = new Color(0x717169);

    /**
     *  The color black (0, 0, 0).
     */
    public static final Color BLACK = Color.BLACK;
    
   /***************************************************************************
    *  Fonts
    ***************************************************************************/
 public static Font title;
    static {
      int titleSize = 58;
        try {
            InputStream is = Style.class.getResourceAsStream("/Assets/NovaFlat-Regular.ttf");
            title = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float)titleSize);
        } catch (FontFormatException | IOException e) {
            title = new Font("Monospace", Font.BOLD, titleSize); // fallback
            System.err.println("Could not load custom font: " + e.getMessage());
        }
    }
    
 public static Font heading;
    static {
      int headingSize = 42;
        try {
            InputStream is = Style.class.getResourceAsStream("/Assets/Jersey20-Regular.ttf");
            heading = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float)headingSize);
        } catch (FontFormatException | IOException e) {
            heading = new Font("Monospace", Font.PLAIN, headingSize); // fallback
            System.err.println("Could not load custom font: " + e.getMessage());
        }
    }
    
 public static Font subheading;
    static {
      int subheadingSize = 32;
        try {
            InputStream is = Style.class.getResourceAsStream("/Assets/NovaFlat-Regular.ttf");
            subheading = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float)subheadingSize);
        } catch (FontFormatException | IOException e) {
            subheading = new Font("Monospace", Font.PLAIN, subheadingSize); // fallback
            System.err.println("Could not load custom font: " + e.getMessage());
        }
    }
    
 public static Font body;
    static {
      int bodySize = 22;
        try {
            InputStream is = Style.class.getResourceAsStream("/Assets/Jersey20-Regular.ttf");
            body = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float)bodySize);
        } catch (FontFormatException | IOException e) {
            body = new Font("Monospace", Font.PLAIN, bodySize); // fallback
            System.err.println("Could not load custom font: " + e.getMessage());
        }
    }

}