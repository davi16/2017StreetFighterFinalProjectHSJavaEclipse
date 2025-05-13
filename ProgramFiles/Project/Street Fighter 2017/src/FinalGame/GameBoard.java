package FinalGame;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
public class GameBoard extends JPanel implements KeyListener,Runnable{
	private int in=0,hefresh;
	private long helper,time;
	private ArrayList<Integer> keysDown;
	public SpriteFighter playerOne;
	public SpriteFighter playerTwo;
	public static boolean gameOver=false;
	public static boolean playerOneWalkRight=false;
	public static boolean playerTwoWalkRight=false;
	public static boolean playerOneWalkLeft=false;
	public static boolean playerTwoWalkLeft=false;
	public static boolean playerOneJump=false;
	public static boolean playerTwoJump=false;
	public static boolean playerOnePunch=false;
	public static boolean playerTwoPunch=false;
	public static boolean playerOneKick=false;
	public static boolean playerTwoKick=false;
	public static boolean playerOneFlipKick=false;
	public static boolean playerTwoFlipKick=false;
	public static boolean playerOneCombo=false;
	public static boolean playerTwoCombo=false;
	public static boolean playerOneFall=false;
	public static boolean playerTwoFall=false;
	public static boolean playerOneJumpToRight=false;
	public static boolean playerTwoJumpToRight=false;
	public static boolean playerOneJumpToLeft=false;
	public static boolean playerTwoJumpToLeft=false;
	public static boolean playerOneHitByPunch=false;
	public static boolean playerTwoHitByPunch=false;
	public static boolean playerOneHitByKick=false;
	public static boolean playerTwoHitByKick=false;
	public static boolean playerOneHitByCombo=false;
	public static boolean playerTwoHitByCombo=false;
	public static boolean playerOneHitByFlipKick=false;
	public static boolean playerTwoHitByFlipKick=false;
	public static boolean playerOnePowerUpFromKick=false;
	public static boolean playerTwoPowerUpFromKick=false;
	public static boolean playerOnePowerUpFromPunch=false;
	public static boolean playerTwoPowerUpFromPunch=false;
	public static boolean playerOnePowerUpFromFlipKick=false;
	public static boolean playerTwoPowerUpFromFlipKick=false;
	public static boolean playerOnePowerUpFromCombo=false;
	public static boolean playerTwoPowerUpFromCombo=false;
	public static boolean playerOnePowerDownFromFlipKick=false;
	public static boolean playerTwoPowerDownFromFlipKick=false;
	public static boolean playerOnePowerDownFromCombo=false;
	public static boolean playerTwoPowerDownFromCombo=false;
	public Image p1CurSprite;
	public Image p2CurSprite;
	public static int p1x;
	public static int p1y;
	public static int p2x;
	public static int p2y;
	private static String winner=null;
	public static String name1;
	public static String name2;
	AudioInputStream audioInputStream;
	Clip clip;
	public static JFrame frame=new JFrame("Game");
	private ImageIcon background=(shrinkIcon(1920,1200,  new ImageIcon("fighter blue movement sprites/fighting game background.png")));
	public static GameBoard board;
	static Scanner it=new Scanner(System.in);
	Thread Thread=new Thread(this);
	public GameBoard(String Name1,String Name2){
		name1=Name1;
		name2=Name2;
		playerOne=new SpriteFighter(1500,600,1,1);
		playerTwo=new SpriteFighter(275,600,0,2);
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("SoundsForGame/Little Fighter 2 theme song.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
		addKeyListener(this);
		addMouseListener(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						requestFocus();
					}
				}
				);
		Thread.start();
		keysDown = new ArrayList<Integer>();
		setBackground(Color.gray);
	}
	int counterMusic=0;
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		background.paintIcon(this, g, 0, 0);
		g.setFont(g.getFont().deriveFont(50f));
		Color color = new Color(204,0,0);
		g.setColor(color);
		g.drawString("Hitpoints:"+playerOne.hitpoints, 1500, 120);
		g.drawString("Hitpoints:"+playerTwo.hitpoints, 30, 120);
		color = new Color(255,255,0);
		g.setColor(color);
		g.drawString("Power:"+playerOne.power, 1500, 160);
		g.drawString("Power:"+playerTwo.power, 30, 160);
		color = new Color(0,102,102);
		g.setColor(color);
		g.drawString(""+name1, 1500, 80);
		g.drawString(""+name2, 30, 80);
		g.drawImage(playerOne.currentSprite, p1x, p1y, null);
		g.drawImage(playerTwo.currentSprite, p2x, p2y, null);
		counterMusic++;
		if(counterMusic==1){
			new Thread(

					new Runnable() {

						@Override
						public void run() {
							clip.loop(Clip.LOOP_CONTINUOUSLY);
							SpriteFighter.fight();
						}
					}
					).start();
		}
		if(gameOver){
			g.setFont(g.getFont().deriveFont(70f));
			g.setColor(Color.CYAN);
			g.drawString("Game Over!",700,400);
			g.drawString(winner+" is the winner!",500,500);
		}

	}
	public void refresh() {
		this.gameOver=false;
		this.winner=null;
		System.out.println("Enter your players name:");
		String Name1 = it.next();
		System.out.println("Enter your players name:");
		String Name2 = it.next();
		board=new GameBoard(Name1,Name2);
		frame=new JFrame("Game");
		frame.setBounds(0,0,1920,1200);
		frame.getContentPane().add(board);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	public static ImageIcon shrinkIcon(int width, int hight, ImageIcon i){
		return new ImageIcon(i.getImage().getScaledInstance(width, hight, Image.SCALE_SMOOTH)); 
	}
	public static void main(String [] args){
		System.out.println("Enter your players name:");
		String Name1 = it.next();
		System.out.println("Enter your players name:");
		String Name2 = it.next();
		GameBoard board = new GameBoard(Name1,Name2);
		frame.setBounds(0,0,1920,1200);
		frame.getContentPane().add(board);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	int num=0;
	public void hit(int fighterNumber, int HPLoose){
		if(fighterNumber==1){
			if(num==HPLoose || playerOne.hitpoints-1<0){
				playerOneHitByPunch=false;
				playerOneHitByKick=false;
				playerOneHitByCombo=false;
				playerOneHitByFlipKick=false;
				num=0;
			}
			else{
				num++;
				playerOne.hitpoints-=1;
			}
		}
		if(fighterNumber==2){
			if(num==HPLoose || playerTwo.hitpoints-1<0){
				playerTwoHitByPunch=false;
				playerTwoHitByKick=false;
				playerTwoHitByCombo=false;
				playerTwoHitByFlipKick=false;
				num=0;
			}
			else{
				num++;
				playerTwo.hitpoints-=1;
			}
		}
	}
	int num1=0;
	public void powerUp(int fighterNumber, int amountOfPower){
		if(fighterNumber==1){
			if(num1==amountOfPower || playerOne.power+1>900){
				playerOnePowerUpFromKick=false;
				playerOnePowerUpFromPunch=false;
				playerOnePowerUpFromFlipKick=false;
				playerOnePowerUpFromCombo=false;

				num1=0;
			}
			else{
				num1++;
				playerOne.power+=1;
			}
		}
		if(fighterNumber==2){
			if(num1==amountOfPower || playerTwo.power+1>900){
				playerTwoPowerUpFromKick=false;
				playerTwoPowerUpFromPunch=false;
				playerTwoPowerUpFromFlipKick=false;
				playerTwoPowerUpFromCombo=false;
				num1=0;
			}
			else{
				num1++;
				playerTwo.power+=1;
			}
		}
	}
	public void powerDown(int fighterNumber, int amountOfPower){
		if(fighterNumber==1){
			playerOne.power-=amountOfPower;
			playerOnePowerDownFromFlipKick=false;
			playerOnePowerDownFromCombo=false;
		}
		if(fighterNumber==2){
			playerTwo.power-=amountOfPower;
			playerTwoPowerDownFromFlipKick=false;
			playerTwoPowerDownFromCombo=false;
		}
	}
	public void gameOver(){
		if(playerOne.hitpoints==0){
			winner=name2;
			gameOver=true;
			playerOneFall=true;
			new Thread(

					new Runnable() {

						@Override
						public void run() {
							SpriteFighter.gameOver();
							SpriteFighter.painFalling();
							SpriteFighter.falling();
						}
					}
					).start();
		}
		if(playerTwo.hitpoints==0){
			winner=name2;
			gameOver=true;
			playerTwoFall=true;
			new Thread(

					new Runnable() {

						@Override
						public void run() {
							SpriteFighter.gameOver();
							SpriteFighter.painFalling();
							SpriteFighter.falling();
						}
					}
					).start();
		}
	}
	@Override
	public void keyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		int keycode=(evt.getKeyCode());
		if(!keysDown.contains(evt.getKeyCode()))
			keysDown.add(new Integer(evt.getKeyCode()));
		if(!gameOver){
			if(!playerOneJump && !playerOnePunch && !playerOneKick && !playerOneFlipKick && !playerOneCombo && !playerOneFall){
				if(keysDown.contains(KeyEvent.VK_LEFT)){
					if(!playerOneJumpToRight){
						playerOneWalkLeft=true;
					}
				}
				if(keysDown.contains(KeyEvent.VK_RIGHT)){
					if(!playerOneJumpToLeft){
						playerOneWalkRight=true;
					}
				}
			}
			if(!playerTwoJump && !playerTwoPunch && !playerTwoKick && !playerTwoFlipKick && !playerTwoCombo && !playerTwoFall){
				if(keysDown.contains(KeyEvent.VK_A)){
					if(!playerTwoJumpToRight){
						playerTwoWalkLeft=true;
					}
				}
				if(keysDown.contains(KeyEvent.VK_D)){
					if(!playerTwoJumpToLeft){
						playerTwoWalkRight=true;
					}
				}
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		keysDown.remove(new Integer(evt.getKeyCode()));
		int keycode=(evt.getKeyCode());
		switch(keycode){
		case KeyEvent.VK_R:this.frame.dispose();this.refresh();this.playerOne=new SpriteFighter();this.playerTwo= new SpriteFighter();clip.loop(-1);clip.stop();clip.flush();
		}
		if(!gameOver){
			switch(keycode){
			case KeyEvent.VK_LEFT:playerOne.setCol(4);playerOneWalkLeft=false;this.playerOne.counterOfSteps=0;/*left=false;*/
			case KeyEvent.VK_RIGHT:playerOne.setCol(1);playerOneWalkRight=false;this.playerOne.counterOfSteps=0;/*right=false;*/
			}
			switch(keycode){
			case KeyEvent.VK_A:playerTwo.setCol(4);playerTwoWalkLeft=false;this.playerTwo.counterOfSteps=0;/*a=false;*/
			case KeyEvent.VK_D:playerTwo.setCol(1);playerTwoWalkRight=false;this.playerTwo.counterOfSteps=0;/*d=false;*/
			}
			if(!playerOneFall && !playerOneJumpToRight && !playerOneJumpToLeft  && !playerOneWalkLeft && !playerOneWalkRight && !playerOnePunch && !playerOneKick && !playerOneFlipKick && !playerOneCombo){
				switch(keycode){
				case KeyEvent.VK_UP:playerOneJump=true;
				}
			}
			if(!playerOneFall && playerOneWalkLeft && !playerOneWalkRight && !playerOneJumpToRight && !playerOneJumpToLeft && !playerOnePunch && !playerOneKick && !playerOneFlipKick && !playerOneCombo){
				switch(keycode){
				case KeyEvent.VK_UP:playerOneJumpToLeft=true;
				}
			}
			if(!playerOneFall && !playerOneWalkLeft && playerOneWalkRight && !playerOneJumpToRight && !playerOneJumpToLeft && !playerOnePunch && !playerOneKick && !playerOneFlipKick && !playerOneCombo){
				switch(keycode){
				case KeyEvent.VK_UP:playerOneJumpToRight=true;
				}
			}
			if(!playerTwoFall && !playerTwoJumpToRight && !playerTwoJumpToLeft && !playerTwoWalkRight && !playerTwoWalkLeft && !playerTwoKick && !playerTwoFlipKick && !playerTwoCombo){
				switch(keycode){
				case KeyEvent.VK_W:playerTwoJump=true;
				} 
			}
			if(!playerTwoFall && !playerTwoPunch && playerTwoWalkLeft && !playerTwoJumpToRight && !playerTwoJumpToLeft && !playerTwoWalkRight && !playerTwoKick && !playerTwoFlipKick && !playerTwoCombo){
				switch(keycode){
				case KeyEvent.VK_W:playerTwoJumpToLeft=true;
				}
			}
			if(!playerTwoFall && !playerTwoPunch && !playerTwoWalkLeft && !playerTwoJumpToRight && !playerTwoJumpToLeft && playerTwoWalkRight && !playerTwoKick && !playerTwoFlipKick && !playerTwoCombo){
				switch(keycode){
				case KeyEvent.VK_W:playerTwoJumpToRight=true;
				}
			}
			if(!playerTwoFall && !playerOneFall && !playerOneJump && !playerOneJumpToRight && !playerOneJumpToLeft && !playerOneKick && !playerOneFlipKick && !playerOneCombo){
				switch(keycode){
				case KeyEvent.VK_CONTROL:playerOnePunch=true;
				}
			}
			if(!playerOneFall && !playerTwoFall && !playerTwoJump && !playerTwoJumpToRight && !playerTwoJumpToLeft && !playerTwoKick && !playerTwoFlipKick && !playerTwoCombo){
				switch(keycode){
				case KeyEvent.VK_Q:playerTwoPunch=true;
				}
			}
			if(!playerTwoFall && !playerOneFall && !playerOneJump && !playerOneJumpToRight && !playerOneJumpToLeft && !playerOnePunch && !playerOneFlipKick && !playerOneCombo){
				switch(keycode){
				case KeyEvent.VK_SHIFT:playerOneKick=true;
				}
			}
			if(!playerOneFall && !playerTwoFall && !playerTwoJump && !playerTwoJumpToRight && !playerTwoJumpToLeft && !playerTwoPunch && !playerTwoFlipKick && !playerTwoCombo){
				switch(keycode){
				case KeyEvent.VK_E:playerTwoKick=true;
				}
			}
			if(playerOne.power>=200){
				if(!playerTwoFall && !playerOneFall && !playerOneJump && !playerOneJumpToRight && !playerOneJumpToLeft && !playerOneKick && !playerOnePunch && !playerOneCombo){
					switch(keycode){
					case KeyEvent.VK_ENTER:playerOneFlipKick=true;
					}
				}
			}
			if(playerTwo.power>=200){
				if(!playerOneFall && !playerTwoFall && !playerTwoJump && !playerTwoJumpToRight && !playerTwoJumpToLeft && !playerTwoKick && !playerTwoPunch && !playerTwoCombo){
					switch(keycode){
					case KeyEvent.VK_Z:playerTwoFlipKick=true;
					}
				}
			}

			if(playerOne.power>=400){
				if(!playerTwoFall && !playerOneFall && !playerOneJump && !playerOneJumpToRight && !playerOneJumpToLeft && !playerOneKick && !playerOneFlipKick && !playerOnePunch){
					switch(keycode){
					case KeyEvent.VK_ALT:playerOneCombo=true;
					}
				}
			}

			if(playerTwo.power>=400){
				if(!playerOneFall && !playerTwoFall && !playerTwoJump && !playerTwoJumpToRight && !playerTwoJumpToLeft && !playerTwoKick && !playerTwoFlipKick && !playerTwoPunch){
					switch(keycode){
					case KeyEvent.VK_X:playerTwoCombo=true;
					}
				}
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent evt) {
		// TODO Auto-generated method stub

	}
	@Override 
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(playerOneJump){
				playerOne.jump();
			}
			if(playerTwoJump){
				playerTwo.jump();
			}
			if(playerOnePunch){
				playerOne.punch();
			}
			if(playerTwoPunch){
				playerTwo.punch();
			}
			if(playerOneKick){
				playerOne.kick();
			}
			if(playerTwoKick){
				playerTwo.kick();
			}
			if(playerOneFlipKick){
				playerOne.flipKick();
			}
			if(playerTwoFlipKick){
				playerTwo.flipKick();
			}
			if(playerOneCombo){
				playerOne.combo();
			}
			if(playerTwoCombo){
				playerTwo.combo();
			}
			if(playerOneWalkRight){
				playerOne.walk("Right");
			}
			if(playerOneWalkLeft){
				playerOne.walk("Left");
			}
			if(playerTwoWalkRight){
				playerTwo.walk("Right");
			}
			if(playerTwoWalkLeft){
				playerTwo.walk("Left");
			}
			if(playerOneFall){
				playerOne.fall();
			}
			if(playerTwoFall){
				playerTwo.fall();
			}
			if(playerOneJumpToRight){
				playerOne.jumpSideways("Right");
			}
			if(playerTwoJumpToRight){
				playerTwo.jumpSideways("Right");
			}
			if(playerOneJumpToLeft){
				playerOne.jumpSideways("Left");
			}
			if(playerTwoJumpToLeft){
				playerTwo.jumpSideways("Left");
			}
			if(playerTwoHitByPunch){
				this.hit(2, 15);
			}
			if(playerOneHitByPunch){
				this.hit(1, 15);
			}
			if(playerTwoHitByKick){
				this.hit(2, 15);
			}
			if(playerOneHitByKick){
				this.hit(1, 15);
			}
			if(playerTwoHitByCombo){
				this.hit(2, 60);
			}
			if(playerOneHitByCombo){
				this.hit(1, 60);
			}
			if(playerTwoHitByFlipKick){
				this.hit(2, 40);
			}
			if(playerOneHitByFlipKick){
				this.hit(1, 40);
			}
			if(playerOnePowerUpFromKick){
				this.powerUp(1,9);
			}
			if(playerTwoPowerUpFromKick){
				this.powerUp(2,9);
			}
			if(playerOnePowerUpFromPunch){
				this.powerUp(1,9);
			}
			if(playerTwoPowerUpFromPunch){
				this.powerUp(2,9);
			}
			if(playerOnePowerUpFromFlipKick){
				this.powerUp(1,27);
			}
			if(playerTwoPowerUpFromFlipKick){
				this.powerUp(2,27);
			}
			if(playerOnePowerUpFromCombo){
				this.powerUp(1,40);
			}
			if(playerTwoPowerUpFromCombo){
				this.powerUp(2,40);
			}
			if(playerOnePowerDownFromFlipKick){
				this.powerDown(1,200);
			}
			if(playerTwoPowerDownFromFlipKick){
				this.powerDown(2,200);
			}
			if(playerOnePowerDownFromCombo){
				this.powerDown(1,400);
			}
			if(playerTwoPowerDownFromCombo){
				this.powerDown(2,400);
			}
			if(!gameOver)
				this.gameOver();
			if(!playerOneJump && !playerOneWalkRight && !playerOneWalkLeft && !playerOneJumpToRight && !playerOneJumpToLeft && !playerOnePunch && !playerOneCombo && !playerOneFlipKick && !playerOneKick && !playerOneFall){
				playerOne.always();
			}
			if(!playerTwoJump && !playerTwoWalkRight && !playerTwoWalkLeft && !playerTwoJumpToRight && !playerTwoJumpToLeft && !playerTwoPunch && !playerTwoCombo && !playerTwoFlipKick && !playerTwoKick && !playerTwoFall){
				playerTwo.always();
			}
			repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}