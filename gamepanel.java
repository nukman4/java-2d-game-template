import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
public class gamepanel extends JPanel implements Runnable{
  //variables
  Image player;
  Image playerfront;
  final int screenwidth = 400;
  final int screenheight = 400;
  KeyHandler keyH = new KeyHandler();
  Thread gameThread;
  void loadimg(){
    try{
    playerfront = ImageIO.read(new File("sprites/playerfront.png"));
    } catch(Exception e){
      System.out.println(e);
    };
  }
  
  //game panel set up
  public gamepanel(){
    this.setPreferredSize(new Dimension(screenwidth,screenheight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  
  //gameloop stuff
  public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
    loadimg();
  }
  @Override
  public void run(){
    while(gameThread != null){
        update();
        repaint();
        try{Thread.sleep(1000/60);}catch(Exception e){System.out.println(e);}
      }
  }

  
  //update stuff
  public void update(){
    if(keyH.uppress == true){
      player.y-=3;
    }
    if(keyH.downpress == true){
      player.y+=3;
    }
    if(keyH.leftpress == true){
      player.x-=3;
    }
    if(keyH.rightpress == true){
      player.x+=3;
    }
  }

  
  //what to paint
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.white);
    g2.drawImage(playerfront, player.x, player.y, null);
    g2.dispose();
  }
}
