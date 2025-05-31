import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.Scanner;
import java.awt.BorderLayout;
/**
 * Runs game logic, switches between scenes, and updates frames
 *
 * @author: Andrew G., Amber M., PHLovejoy
 *
 */
public class GameManager{
   // GM FEILDS
   private static GameManager instance; // one version of GM running, refranced by other classes
   Scanner scan = new Scanner(System.in);
   private boolean running = true; // tracks if game is running
   
   // JFRAME AND PANEL FEILDS
   private JFrame window;
   private ActivityPanel currentPanel; // panel of game currently shown
   private MiniGamePanel miniGamePanel; // miniGame panel
   
   // GAME LOGIC FEIDLS
   Pet pet = null;
   
   /**
    * Provides public access to the GameManager
    * and ensures only one GameManager is running.
    */
   public static GameManager getInstance() {
      if (instance == null) {
         instance = new GameManager();
      }
      return instance;
   }
   
   // Main method run for this app
   public static void main(String[] args) {
      GameManager.getInstance().initializeGame();
   }
   
   // Sets up window and begins gameloop
   public void initializeGame() {
      // Initialize window and panels
      window = new JFrame("AMBIYOLKS");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setResizable(false);
      window.setLocationRelativeTo(null);
      
      window.setLayout(new BorderLayout());
      window.setVisible(true);
      
      // Start and add scene in switchScene()
      switchScene("Title");
      window.add(currentPanel);
      window.pack();
      currentPanel.startActivityThread();
      
      // Start the game loop
      gameLoop();
    }
    
    // Sets frame rate to update game while running
    private void gameLoop() {
      while (running) {
         update();
            try {
               Thread.sleep(16); // Approx. 60 updates per second
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
            }
         }
    }
    
    // Updates game's window for motion and color changes
    private void update() {
      window.revalidate();
      window.repaint();
    }
    
   // Load panels based on currentScene using switch
   public void switchScene(String currentScene){
      // Remove previous panel
      if (currentPanel != null) {
         window.remove(currentPanel);
      }
        
      // Remove minigame panel
      if (miniGamePanel != null) {
         window.remove(miniGamePanel);
      }
         
      // Switch scene bases on case scene name
      switch (currentScene) {
         case "Title":
            currentPanel = new TitlePanel();
            break;
         case "PetSelect":
            currentPanel = new PetSelectPanel();
            break;
         case "Level1": // also adds miniGamePanel
            currentPanel = new Level1Panel();                
            miniGamePanel = new MiniGamePanel();
            window.add(miniGamePanel, BorderLayout.CENTER); // adds miniGame under currentPanel
            miniGamePanel.startActivityThread();
            break;
         default:
            System.out.print("Unknown scene: " + currentScene);
         }
      window.add(currentPanel, BorderLayout.NORTH); // adds current scene at top of window
      
      // Pack the panels in the window
      window.pack();
      currentPanel.startActivityThread(); // start panel activities like draw, timer, and update
   }
   
   // Return current Pet object
   public Pet getPet(){
      return pet;
   }
   
   // Return to Title Screen and sets pet null
   public void returnToTitle(){
      pet = null;
      switchScene("Title");
   }
   
   // Begin the game
   public void startGame(){
      switchScene("PetSelect");
   }
   
   // Instantiate MiniGamePanel
   public MiniGamePanel getMiniGamePanel() {
      return miniGamePanel;
   }
   
   private String speciesName = null;
   // Instantiate chosen Pet species with given name and age
   // and progress to Level1
   public void createPet(String speciesName){
      String petName = JOptionPane.showInputDialog(window, "Enter AmbiYolk's name:");
      if (petName != null && petName.length() > 0 && petName.length() < 11 ) { 
         switch (speciesName) {
            case "Dragon":
               pet = new HungrySpecies(petName);
               break;
            case "Penguin":
               pet = new SocialSpecies(petName); // name, length of life in seconds
               break;
            case "Fox":
               pet = new ShortLivedSpecies(petName); // name, length of life in seconds
               break;
            default:
               System.out.print("Error with: " + speciesName);
         }
         switchScene("Level1");
      }
   }
}

