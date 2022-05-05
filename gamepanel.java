import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class gamepanel extends JPanel implements Runnable{
  //screen settings
  final int screenwidth = 400;
  final int screenheight = 400;
  //fps
  int FPS = 60;
  
  KeyHandler keyH = new KeyHandler();
  Thread gameThread;

  public gamepanel(){
    this.setPreferredSize(new Dimension(screenwidth,screenheight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
  }
  @Override
  public void run(){
    while(gameThread != null){
        update();
        repaint();
        try{Thread.sleep(1000/FPS);}catch(Exception e){System.out.println(e);}
      }
  }
  int x=100;
  int y=100;
  public void update(){
    if(keyH.uppress == true){
      y-=3;
    }
    if(keyH.downpress == true){
      y+=3;
    }
    if(keyH.leftpress == true){
      x-=3;
    }
    if(keyH.rightpress == true){
      x+=3;
    }
  }
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.white);
    g2.fillRect(x,y,20,20);
    g2.dispose();
  }
}
