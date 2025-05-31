import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
/**
 * HungrySpecies subclass represents hungry pets.
 * From class PET.
 * 
 * @author Agnes, PHLovejoy
 */

public class HungrySpecies extends Pet {
    Random rand = new Random();
    private int width;
    private int height;
    private String color;

    public HungrySpecies(String name) {
        super(name);
        this.maxAge = 45 + rand.nextInt(16);
        this.width = width;
        this.height = height;
        this.color = color;
        this.portrait = new ImageIcon(ClassLoader.getSystemResource("Assets/dragon.png"));
    }
    
    // overload with picture and string description
    public HungrySpecies() {
        super();
        this.name = "Dragon";
        this.portrait = new ImageIcon(ClassLoader.getSystemResource("Assets/dragon.png"));
    }
    
    // Getters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    // Setters
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
   // Special behavior: Satisfaction decreases quickly if very hungry
   @Override
   protected void onTick() {
       super.onTick();
       if (hunger > 80) {
           satisfaction -= 5;
           System.out.println(getName() + " is starving and losing satisfaction!");
       } else {
           satisfaction--;
       }
   }
   
   // Special behavior: Extra satisfaction from eating
   @Override
   public void eat() {
       super.eat(); // still decreases hunger and increases satisfaction by 1
       satisfaction += 2; // extra satisfaction for hungry species
       System.out.println(getName() + " loves eating! Extra satisfaction gained.");
   }

    // Special behavior: playing makes this type of pet much hungrier.
    @Override
    public void play() {
        super.play();
        hunger += 8;
        System.out.println(getName() + " gets extra hungry after playing!");
    }


}