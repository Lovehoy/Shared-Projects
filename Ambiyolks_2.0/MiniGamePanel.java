import java.awt.*;
 import java.awt.event.*;
 import java.util.ArrayList;
 import java.util.Random;
 import java.util.Iterator;
 import javax.swing.*;
 
 public class MiniGamePanel extends JPanel implements Runnable, KeyListener {
    Random rand = new Random();
    GameManager gameManager = GameManager.getInstance();
    Pet pet = gameManager.getPet();
    
    final int ogTileSize = 16;
    final int scale = 3;
    final int tileSize = ogTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
 
    int panelHeight = screenHeight / 3;
    int panelY = screenHeight - panelHeight;
 
    Thread activityThread;
 
    enum Minigame {FEED, PLAY}
    Minigame currentActivity = Minigame.FEED;
 
    int playerVelocityY = 3;
    int playPoints = 0;
    int pelletSpawnTimer = 0;
    int obstacleTimer = 0;
    int groundLine = panelHeight - tileSize;
    int playerX = 100;
    int playerY = groundLine - tileSize;
    ArrayList<Rectangle> pellets = new ArrayList<>();
    ArrayList<Rectangle> obstacles = new ArrayList<>();
    boolean gameOver = false;
    boolean isJumping = false;
 
    JButton switchButton;
 
    public MiniGamePanel() {
       this.setPreferredSize(new Dimension(screenWidth, panelHeight));
       this.setBackground(Style.BLACK);
       this.setDoubleBuffered(true);
       this.setFocusable(true);
       this.addKeyListener(this);
       this.setLayout(null);
 
       switchButton = new JButton("Switch Minigame");
       switchButton.setBounds(screenWidth - 180, 10, 160, 30);
       switchButton.setBackground(Style.MIDDARK);
       switchButton.addActionListener(e -> toggleScene());
       switchButton.setText("Switch to " + (currentActivity == Minigame.FEED ? "Platformer" : "Feeding") + " Mode");
       this.add(switchButton);
 
       SwingUtilities.invokeLater(() -> this.requestFocusInWindow());
    }
 
    private void toggleScene() {
       currentActivity = (currentActivity == Minigame.FEED) ? Minigame.PLAY : Minigame.FEED;
       resetGameState();
       this.requestFocusInWindow();
       switchButton.setText("Switch to " + (currentActivity == Minigame.FEED ? "Platformer" : "Feeding") + " Mode");
    }
     
    public void forceFeedMode() {
       currentActivity = Minigame.FEED;
       resetGameState();
    }
     
    public void forcePlayMode() {
       currentActivity = Minigame.PLAY;
       resetGameState();
    }
 
    public void resetGameState() {
       playerX = 100;
       playerY = groundLine - tileSize;
       gameOver = false;
       playPoints = 0;
       pellets.clear();
       obstacles.clear();
 
       if (currentActivity == Minigame.FEED) {
          for (int i = 0; i < 5; i++) {
             int pelletX = rand.nextInt(screenWidth - tileSize);
             int pelletY = rand.nextInt(panelHeight - tileSize);
             pellets.add(new Rectangle(pelletX, pelletY, tileSize, tileSize));
             }
       }
     }
 
    public void startActivityThread() {
       this.requestFocusInWindow();
       activityThread = new Thread(this);
       activityThread.start();
     }
 
     @Override
    public void run() {
       while (activityThread != null) {
          update();
          repaint();
          try {
             Thread.sleep(16); // ~60 FPS
          }catch (InterruptedException e) {
             e.printStackTrace();
          }            
       }
    }
 
    public void update() {
       if (currentActivity == Minigame.PLAY && !gameOver) {
          if (isJumping) {
             playerY += playerVelocityY;
             playerVelocityY += 1;
             if (playerY >= groundLine - tileSize) {
                playerY = groundLine - tileSize;
                isJumping = false;
                }
           }
 
           synchronized (obstacles) {
             Iterator<Rectangle> iteration = obstacles.iterator();
             while (iteration.hasNext()) {
                Rectangle obs = iteration.next();
                obs.x -= 5;
                if (obs.x + obs.width < 0) {
                   iteration.remove();
                   playPoints++;
                   pet.playMiniGame();
                }else if (obs.intersects(new Rectangle(playerX, playerY, tileSize, tileSize))) {
                   gameOver = true;
                  }
                }
             }
 
             obstacleTimer++;
             if (obstacleTimer > 45) {
                obstacleTimer = 0;
                int height = tileSize / 2;
                obstacles.add(new Rectangle(screenWidth, groundLine - height, tileSize, height));
             }
 
         }else if (currentActivity == Minigame.FEED) {
             Rectangle player = new Rectangle(playerX, playerY, tileSize, tileSize);
 
             synchronized (pellets) {
                Iterator<Rectangle> iter = pellets.iterator();
                while (iter.hasNext()) {
                   Rectangle pellet = iter.next();
                   if (player.intersects(pellet)) {
                      iter.remove();
                      playPoints++;
                      pet.eatMiniGame();
                   }
                }
             }
 
             if (pellets.size() < 3) {
                pelletSpawnTimer++;
                if (pelletSpawnTimer > 70) {
                   pelletSpawnTimer = 0;
                   for (int i = 0; i < 3 - pellets.size(); i++) {
                      int pelletX = rand.nextInt(screenWidth - tileSize);
                      int pelletY = rand.nextInt(groundLine - tileSize);
                         pellets.add(new Rectangle(pelletX, pelletY, tileSize, tileSize));
                   }
                 }
             }
         }
     }
 
     @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2 = (Graphics2D) g;
 
       g2.setColor(Style.WHITE);
       g2.fillRect(0, 0, screenWidth, panelHeight);
 
       if (currentActivity == Minigame.FEED) {
          drawFeedScene(g2);
       }else if (currentActivity == Minigame.PLAY) {
          drawPlayScene(g2);
        }
         
       g2.setColor(Style.MIDDARK);
       g2.drawString("Points: " + playPoints, 20, 20);
 
       if (gameOver) {
          g2.setColor(Style.BLACK);
          g2.setFont(new Font("Arial", Font.BOLD, 24));
          g2.drawString("GAME OVER", screenWidth / 2 - 80, 50);
       }
       g2.dispose();
    }
 
    private void drawFeedScene(Graphics2D g2) {
       g2.setColor(Style.MIDDARK);
       g2.fillRect(playerX, playerY, tileSize, tileSize);
 
       g2.setColor(Style.BLACK);
       for (Rectangle pellet : pellets) {
          g2.fillOval(pellet.x, pellet.y, pellet.width, pellet.height);
       }
    }
 
    private void drawPlayScene(Graphics2D g2) {
       g2.setColor(Style.MIDLIGHT);
       g2.fillRect(0, groundLine, screenWidth, tileSize);
 
       g2.setColor(Style.BLACK);
       for (Rectangle obs : obstacles) {
          g2.fillRect(obs.x, obs.y, obs.width, obs.height);
       }
 
       g2.setColor(Style.MIDDARK);
       g2.fillRect(playerX, playerY, tileSize, tileSize);
    }
 
     // KEY CONTROLS
    @Override
    public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
    
    if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
       playerX -= 10;
    }
    if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
       playerX += 10;
    }
    if (currentActivity == Minigame.FEED) {
       if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
          playerY = Math.max(0, playerY - 10);
       }
       if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
          playerY = Math.min(panelHeight - tileSize, playerY + 10);
       }
    }
       if ((code == KeyEvent.VK_SPACE || code == KeyEvent.VK_W) && !isJumping && currentActivity == Minigame.PLAY) {
          isJumping = true;
          playerVelocityY = -12;
          playerY -= 1;
       }
    }
 
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
 }