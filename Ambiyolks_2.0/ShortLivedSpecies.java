import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
/**
 * Subclasse ShortLivedSpecies for pets with shortlive.
 * From class PET.
 * 
 * @author Agnes
 */

public class ShortLivedSpecies extends Pet {
    Random rand = new Random();


    private String color; //Representing the hex value (ex: "#FFAA00")

    public ShortLivedSpecies(String name) {
        super(name); // Call constructor from class Pet
        this.maxAge = 10 + rand.nextInt(20);
        this.portrait = new ImageIcon(ClassLoader.getSystemResource("Assets/fox.png"));
    }
    
    // overload with picture and string description
    public ShortLivedSpecies() {
        super();
        this.name = "Fox";
        this.portrait = new ImageIcon(ClassLoader.getSystemResource("Assets/fox.png"));    
    }
    
    // Getters

    public String getColor() {
        return color;
    }

    // Setters

    public void setColor(String color) {
        this.color = color;
    }
}