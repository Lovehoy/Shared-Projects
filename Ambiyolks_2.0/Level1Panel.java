import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Main gameplay panel
 * 
 * @author Andrew G., Amber M., PHLovejoy
 */

public class Level1Panel extends ActivityPanel{
   ClickableBoxButton feedPetBtn, playPetBtn, feastGameBtn, raceGameBtn, playAgainBtn;
   Pet pet = gameManager.getPet();
   PetChat petChat = new PetChat(pet);
   MiniGamePanel miniGamePanel = GameManager.getInstance().getMiniGamePanel(); // must get instance!
   
   private boolean playAgainBtnAdded = false;
   
   // Pet vars
   private ImageIcon portrait = null;
   private String petName;
   private int hungerStat;
   private int moodStat;
   private int ageStat;
   private int satiStat;
   
   public Level1Panel() {
      super();
      setLayout(null);
      
      // Instatiate Buttons
      feedPetBtn = new ClickableBoxButton(
            300, 350, 50, 35, "Eat", Style.MIDDARK, Style.WHITE);
      playPetBtn = new ClickableBoxButton(
            170, 350, 70, 35, "Play", Style.MIDDARK, Style.WHITE);
      feastGameBtn = new ClickableBoxButton(
            300, 390, 75, 50, "FEAST", Style.MIDLIGHT, Style.BLACK);
      raceGameBtn = new ClickableBoxButton(
            170, 390, 70, 50, "RACE", Style.MIDLIGHT, Style.BLACK);
      
      // Add buttons to button array
      buttons.add(feedPetBtn);
      buttons.add(playPetBtn);
      buttons.add(feastGameBtn);
      buttons.add(raceGameBtn);      
             
      // Instantiate PetChat message
      message = "WELCOME TO AMBIYOLKS";
      
      // Name, Species, Hunger, Mood, and Age
      petName = pet.getName();
      hungerStat = pet.getHunger();
      moodStat = pet.getMood();
      ageStat = pet.getAge();
      satiStat = pet.getSatisfaction();
      
      // Portrait
      portrait = pet.getPortrait();

   }
   
   // Handles button interactions
   @Override
   public void handleButtonClick(ClickableBoxButton btn) {
      if (!pet.getIsDead()){
         if (btn == feedPetBtn) {
            pet.eat();
            message = petChat.petEat;
         } else if (btn == playPetBtn) {
            pet.play();
            message = petChat.petPlay;
         } else if (btn == feastGameBtn){
            GameManager.getInstance().getMiniGamePanel().forceFeedMode(); // gets MiniGamePanel from GM
            message = petChat.petFeast;
         } else if (btn == raceGameBtn){
            GameManager.getInstance().getMiniGamePanel().forcePlayMode(); // gets MiniGamePanel from GM
            message = petChat.petRace;
         }
      }
      if (btn == playAgainBtn) {
         gameManager.returnToTitle();
      }
   }
   
   @Override
   public void update(){
      super.update();
      
      // get stats from pet to be redrawn each frame
      hungerStat = pet.getHunger();
      moodStat = pet.getMood();
      ageStat = pet.getAge();
      satiStat = pet.getSatisfaction();
     
      // Check for Play Again button and if pet is dead
      if (pet.getIsDead() && !playAgainBtnAdded) { // must check !playBtn, or else a crazy execeptions!
         showDeathScreen();
      }
   }  
   
   // Pause all game play when pet isDead
   private void showDeathScreen(){
      message = petChat.petDied;
      playAgainBtn = new ClickableBoxButton(
            270, screenHeight / 2, tileSize * 6, tileSize * 2, "Play Again?", Style.BLACK, Style.MIDLIGHT);
      buttons.remove(feedPetBtn);
      buttons.remove(playPetBtn);
      buttons.add(playAgainBtn);
      playAgainBtnAdded = true;
      setBackground(Style.MIDLIGHT);
      repaint();
   }
   
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g); // Ensures the panel is properly rendered
   
      Graphics2D g2 = (Graphics2D) g;
       
      // Pet Name Styling
      g2.setColor(Style.MIDLIGHT);
      g2.drawRect(40, 30, tileSize * 10, tileSize * 2); 
      g2.setColor(Style.WHITE);
      g2.setFont(Style.title);
      g2.drawString(petName, 60, 90);
      
      // Stat Label Styling
      g2.setColor(Style.MIDLIGHT);
      g2.setFont(Style.body);
      g2.drawString("mood", 180, 310);
      g2.drawString("hunger", 310, 310);
      g2.drawString("age", 55, 310);
      g2.drawString("satisfaction", 55, 390);
       
      // Stat Styling
      g2.setColor(Style.WHITE);
      g2.setFont(Style.subheading);
      g2.drawString("" + moodStat + "%", 180, 340);
      g2.drawString("" + hungerStat + "%", 310, 340);
      g2.drawString("" + ageStat + " sec", 55, 340);
      g2.drawString("" + satiStat + "%", 55, 420);
   
      // Draw PetChat box and message
      g2.setColor(Style.MIDLIGHT);
      g2.fillRect(55, 140, tileSize * 9 + 10, tileSize * 3 + 30); 
      g2.setColor(Style.BLACK);
      g2.setFont(Style.body);
      g2.drawString(message, 70, 180);
      
      // Draw pet portrait Frame
      g2.setColor(Style.WHITE);
      g2.fillRect(420, 80, tileSize * 8, tileSize * 9);  
           
      // Draw portrait
      Image image = portrait.getImage();
      g2.drawImage(image, 440, 95, tileSize * 7, tileSize * 8, this);
      
      // Draw buttons on top
      for (ClickableBoxButton btn : buttons) {
          g2.setFont(Style.body);
          btn.draw(g);
      }
   }
}