import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Pet with timer dependent stats and age
 * 
 * @author Agnes and PHLovejoy
 */

public class Pet {
   //fields
   private Timer timer = new Timer();
   protected String name;
   protected int age;
   protected int maxAge;
   protected int mood;
   protected int hunger;
   protected int satisfaction;
   protected boolean isDead;
   public static Pet[] species = new Pet[]{
         new HungrySpecies(),
         new SocialSpecies(),
         new ShortLivedSpecies(),
   };
   protected ImageIcon portrait = null;



   //CONSTRUCTORS
   public Pet(String name){
      this.name = name;
      this.isDead = false;
      this.maxAge = maxAge;
      satisfaction = 30;
      startAging();
      hunger = 75;
      mood = 50;
   }
   
   // overloaded for Pet Select
   public Pet(){
      this.name = name;
   }
   
   //METHODS
   
   /**
    * Start aging process using maxAge
    * 
    * @author PHLovejoy
    * demo video for Timer: https://www.youtube.com/watch?v=QEF62Fm81h4&ab
    */ 
   protected void startAging() {
      TimerTask ageTask = new TimerTask(){
         @Override
             public void run() {
                age++;
                onTick();
                System.out.printf("%s's Age: %d \n",name, age, satisfaction);
                System.out.printf("Satisfaction: %d \n", satisfaction);
                if (age >= maxAge) {
                    isDead = true;
                    System.out.println(name + " has died.");
                    timer.cancel(); // Stop the timer
                }
            }
         };
      timer.scheduleAtFixedRate(ageTask, 1000, 1000); // ageTask runs after 1 sec (1000) every 1 sec
    }
    
    // allows each child to override for different timer interactions
    protected void onTick(){
      satisfaction --;
      hunger += 3;
      mood -= 1;
    }
   
   // Getters
   public String getName(){
      return name;
   }
   
   public int getAge(){
      return age;
   }
   
   public int getHunger() {
      return hunger;
   }
   
   public int getMood() {
      return mood;
   }
   
   public int getSatisfaction() {
      return satisfaction;
   }      
   
   public boolean isDead() {
      return false;
   }    
   
   public ImageIcon getPortrait(){
      return portrait;
   }
   
   // Boolean
   public boolean getIsDead(){
      return age >= maxAge; //In case the Pet die after a maximum age.
   }  
   
   // Pet Behavior
   public void play() {
      if (!isDead){
        mood += 2; 
        satisfaction += 1;
      }
   }
   
   public void playMiniGame() {
      if (!isDead){
        mood += 20; 
        satisfaction += 6;
      }
   }
   
    public void eat() {
      if (!isDead){
        hunger -= 3; 
        satisfaction += 2;
      }
    }     
    
   public void eatMiniGame() {
      if (!isDead){
        hunger -= 10;
        satisfaction += 4;
      }
   }  
}