/**
 * Contains Pet Chat box dialog options
 *
 * @author PHLovejoy
 */

public class PetChat{
   // Fields
   GameManager gameManager = GameManager.getInstance();
   Pet pet = gameManager.getPet();
   private String petName = pet.getName();
   
   /***************************************************************************
    *  Interactions
    ***************************************************************************/
   String petEat = petName + " is eating!";
   String petPlay = petName + " plays intensely!";
   String petDied = petName + " has died!";
   String petFeast = petName + " feasts!";
   String petRace = petName + " races!";
   
   public PetChat(Pet pet){
      this.pet = pet;
   }
   
}