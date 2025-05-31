import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
/**
 * SocialSpecies subclass represents sociable pets (Example: they like to play).
 * From class PET.
 * 
 * @author Agnes
 */

public class SocialSpecies extends Pet {
    Random rand = new Random();
    private int width;
    private int height;
    private String color;

    public SocialSpecies(String name) {
        super(name);
        this.maxAge = 30 + rand.nextInt(10);
        this.width = width;
        this.height = height;
        this.color = color;
        this.portrait = new ImageIcon(ClassLoader.getSystemResource("Assets/penguin.png"));
    }
    
    // overload with picture and string description
    public SocialSpecies() {
        super();
        this.name = "Penguin";
        this.portrait = new ImageIcon(ClassLoader.getSystemResource("Assets/penguin.png"));
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

    // Special behavior: playing improves mood
    @Override
    public void play() {
        super.play();
        mood += 7;
        System.out.println(getName() + " is very social and loves to play!");
    }
}