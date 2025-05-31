import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Scanner;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

/**
 * JPannel to draw, handle inputs, listen for mouse events, and display messeges
 *
 * @authors Andrew, Amber, PHLovejoy
 */
public class ActivityPanel extends JPanel implements Runnable {
   GameManager gameManager = GameManager.getInstance();
   
   final int ogTileSize = 12;
   final int scale = 3;
   final int tileSize = ogTileSize * scale;
   
   // Panel display vars
   final int maxScreenCol = 16;
   final int maxScreenRow = 12;
   final int screenWidth = tileSize * maxScreenCol;
   final int screenHeight = tileSize * maxScreenRow;
   
   protected String message = null;

   public Thread activityThread;
   
   // Button vars
   public List<ClickableBoxButton> buttons = new ArrayList<>(); // ARRAY EXAMPLE: holds all buttons for clicking and draw
   public ClickableBoxButton playPetBtn, feedPetBtn, namePetBtn; // global for paintComponent() access
    
   /**
    * Instantiates buttons and listens for clicks
    *
    * @authors Amber, Andrew, PHLovejoy
    */
   public ActivityPanel() {
      this.setPreferredSize(new Dimension(screenWidth, screenHeight));
      this.setBackground(Style.BLACK);
      this.setDoubleBuffered(true);
      
      // For all buttons in buttons array, mouse listener checks press and release
      addMouseListener(new MouseAdapter() {
         private ClickableBoxButton pressedButton = null;
         
         // Sets buttons to pressed and repaints button
         @Override
         public void mousePressed(MouseEvent e) {
            for (ClickableBoxButton btn : buttons) { // checks through button array
               if (btn.contains(e.getX(), e.getY())) {
                  btn.setPressed(true);
               }
            }
            // After changing the state of any button, call repaint() to show changes.
            repaint();
         }
         
         // On mouse release, resets and repaints button
         @Override
         public void mouseReleased(MouseEvent e) {
            for (ClickableBoxButton btn : buttons) { // FOR LOOP EXAMPLE: checks through button array
               if (btn.isPressed() && btn.contains(e.getX(), e.getY())) {
                  btn.setClicked(true);
                  handleButtonClick(btn);
                  btn.resetClick(); // Reset the clicked state here
               }
               btn.setPressed(false); 
            }
            repaint();
         }
      });
    }
       
   protected void handleButtonClick(ClickableBoxButton btn) {
      // Intentionally blank.
      // Used by all panels.
   }

   public void startActivityThread() {
      activityThread = new Thread(this);
      activityThread.start();
   }
    
   @Override // overriding class JPanel
   public void run(){
      while(activityThread != null){
      update();
      repaint();
      }
   }
    
   public void update(){
      // Intentionally blank.
      // Used by all panels.
   }
   
   // Draws elements
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 =(Graphics2D)g; // Set graphics to 2D
      g2.setColor(Style.MIDLIGHT); // Set draw color
  
      // Draw all buttons in buttons array
      for (ClickableBoxButton btn : buttons) {
         g2.setFont(Style.body);
         btn.draw(g);
      }
   }
}