import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * Pet Selection Panel
 * 
 * @author PHLovejoy
 */

public class PetSelectPanel extends ActivityPanel{
   private ClickableBoxButton choosePetBtn, backBtn, nextBtn;
   
   // Feilds for pet choices
   private Pet[] species = Pet.species;
   private String speciesName = null;
   private ImageIcon portrait = null;
   
   int selectedIndex = 0;
   
   public PetSelectPanel() {
      super();
      // Display selected species name and portrait
      speciesName = species[selectedIndex].getName();
      portrait = species[selectedIndex].getPortrait();
      
      // Instantiate buttons
      backBtn = new ClickableBoxButton(
            40, 200, 75, 30, "<-back", Style.MIDLIGHT, Style.BLACK);
      nextBtn = new ClickableBoxButton(
            450, 200, 75, 30, "next->", Style.MIDLIGHT, Style.BLACK);
      choosePetBtn = new ClickableBoxButton(
            240, 340, 90, 50, "Get Pet!", Style.MIDLIGHT, Style.BLACK);
      
      // Add buttons to array
      buttons.add(backBtn);
      buttons.add(nextBtn);
      buttons.add(choosePetBtn);
   }
   
   @Override
   protected void handleButtonClick(ClickableBoxButton btn){      
      if (choosePetBtn.wasClicked()) {
         gameManager.createPet(speciesName);
      }else if(nextBtn.wasClicked()){
         selectedIndex = (selectedIndex + 1) % species.length; // forwards and loops list
         speciesName = species[selectedIndex].getName(); 
         portrait = species[selectedIndex].getPortrait();
         System.out.println(speciesName);
         repaint();
      }else if(backBtn.wasClicked()){
         selectedIndex = (selectedIndex - 1 + species.length) % species.length; // backwards and loops list
         speciesName = species[selectedIndex].getName();
         portrait = species[selectedIndex].getPortrait();
         System.out.println(speciesName);
         repaint();
      }
      else
      btn.setClicked(false);
   }   

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g); // Ensures the panel is properly rendered
   
      Graphics2D g2 = (Graphics2D) g;
      
      // Draw Heading: speciesName
      g2.setColor(Style.MIDLIGHT);
      g2.setFont(Style.heading);
      g2.drawString(speciesName, 170, 320);
     
      // Draw pet portrait Frame
      g2.setColor(Style.WHITE);
      g2.fillRect(175, 40, tileSize * 6, tileSize * 7); 
     
      // Draw portrait
      ImageIcon portraitIcon = species[selectedIndex].getPortrait();
      if (portraitIcon != null) {
         Image portrait = portraitIcon.getImage();
         g2.drawImage(portrait, 180, 45, tileSize * 6 - 10, tileSize * 7 - 10, this);
      }
   }
}

   /***************************************************************************
    *  Leftovers
    ***************************************************************************/
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        // Custom painting code
//    }