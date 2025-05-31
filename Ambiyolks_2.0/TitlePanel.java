import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Game Title Screen panel to Begin Game
 * 
 * @author PHLovejoy
*/

public class TitlePanel extends ActivityPanel{
   ClickableBoxButton startBtn;
   
   private String title = null;
   private String heading = null;
   private String subheading = null;
   private String body = null;
   
   public TitlePanel() {
      super();
      
      // Instantiate button
      startBtn = new ClickableBoxButton(
            140, 225, 120, 60, "START!", Style.MIDLIGHT, Style.BLACK);
      // Add button to array
      buttons.add(startBtn);
      
      heading = "Welcome to";
      title = "AMBIYOLKS";
      subheading = "SPRING CSIS 1400 FINAL PROJECT";
      body = "Agnes Braz Franco - Andrew Gaston - Amber Mayer - Paige Lovejoy";
   }
   
   @Override
   public void handleButtonClick(ClickableBoxButton btn) {
      if (startBtn.wasClicked()) {
         gameManager.startGame();
      } else
      btn.setClicked(false);
   }
   
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g); // Ensures the panel is properly rendered
   
      Graphics2D g2 = (Graphics2D) g;
      
      // Draw Heading
      g2.setColor(Style.MIDLIGHT);
      g2.setFont(Style.heading);
      g2.drawString(heading, 140, 150);
     
      // Draw Title
      g2.setColor(Style.WHITE);
      g2.setFont(Style.title);
      g2.drawString(title, 140, 200);
     
      // Draw Subheading
      g2.setColor(Style.MIDDARK);
      g2.setFont(Style.subheading);
      g2.drawString(subheading, 20, 30);
     
      // Draw body
      g2.setColor(Style.MIDLIGHT);
      g2.setFont(Style.body);
      g2.drawString(body, 10, 50);
   }
}