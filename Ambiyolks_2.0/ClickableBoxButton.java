import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Clickable button with styling options
 *
 * @author Amber M., PHLovejoy
 */

public class ClickableBoxButton {
   private int x, y, width, height;
   private String label;
   private Color currentColor;
   private Color defaultColor;
   private Color textColor;
   private Color pressedColor = Style.WHITE;
   private Color outlineColor = Style.MIDDARK;

   private boolean isPressed = false;
   public boolean wasClicked = false;
    
    // Instantiates button at possition with given size, label, and colors
   public ClickableBoxButton(int x, int y, int width, int height, 
         String label, Color currentColor, Color textColor) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.label = label;
      this.currentColor = currentColor;
      this.defaultColor = currentColor;
      this.textColor = textColor;
   }

   // Draws button to change color when pressed
   public void draw(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      // Fill color based on state
      Color fillColor = isPressed ? pressedColor : defaultColor;
      g.setColor(fillColor);
      g.fillRect(x, y, width, height);
        
      // Draw outline
      g.setColor(Style.MIDLIGHT);
      g.drawRect(x, y, width, height);
        
      // Set text color and font
      g.setColor(textColor);
      g.drawString(label, x + 10, y + height / 2 + 5);
   }

   public boolean contains(int mx, int my) {
      return new Rectangle(x, y, width, height).contains(mx, my);
   }

   public void setPressed(boolean pressed) {
      this.isPressed = pressed;
   }
    
   public boolean isPressed() {
      return isPressed;
   }

   public boolean wasClicked() {
      return wasClicked;
   }

   public void setClicked(boolean clicked) {
      this.wasClicked = clicked;
   }

   public void resetClick() {
      this.wasClicked = false;
   }
}
