package FinalGame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
public class SpriteFighter{
	public int fighterNumber;
	public int rowForEveryoneButFalling=1;
	public int rowForFalling=0;
	public int columnWalking=4;
	public int columnJumping=0;
	public int columnPunching=0;
	public int columnKicking=0;
	public int columnFlipKicking=0;
	public int columnCombo=0;
	public int columnFalling=0;
	public int columnJumpingSideways=0;
	public int columnAlways=0;
	public static int walkingRows=2;
	public static int walkingColumns=6;
	public static int jumpingRows=2;
	public static int jumpingColumns=6;
	public static int punchingrowForEveryoneButFallings=2;
	public static int punchingColumns=7;
	public static int kickingRows=2;
	public static int kickingColumns=5;
	public static int flipKickingRows=2;
	public static int flipKickingColumns=11;
	public static int comboRows=2;
	public static int comboColumns=14;
	public static int fallingRows=2;
	public static int fallingColumns=8;
	public static int jumpingSidewaysRows=2;
	public static int jumpingSidewaysColumns=10;
	public static int alwaysRows=2;
	public static int alwaysColumns=3;
	public int hitpoints=1500; 
	public int power=400;
	public static Image [][] walkingSprites =new Image[2][6];
	public static Image [][] jumpingSprites =new Image[2][6];
	public static Image [][] punchingSprites =new Image[2][7];
	public static Image [][] kickingSprites =new Image[2][5];
	public static Image [][] flipKickingSprites =new Image[2][11];
	public static Image [][] comboSprites =new Image[2][14];
	public static Image [][] fallingSprites =new Image[2][8];
	public static Image [][] jumpingSidewaysSprites =new Image[2][10];
	public static Image [][] alwaysSprites =new Image[2][3];
	public Image currentSprite;
	public double walkingX;
	public double walkingY;
	public double jumpingX;
	public double jumpingY;
	public double punchingX;
	public double punchingY;
	public double kickingX;
	public double kickingY;
	public double flipKickingX;
	public double flipKickingY;
	public double comboX;
	public double comboY;
	public double fallingX;
	public double fallingY;
	public double jumpingSidewaysX;
	public double jumpingSidewaysY;
	public double alwaysX;
	public double alwaysY;
	public boolean playerFall=false;
	public boolean playerPunch=false;
	public boolean playerKick=false;
	public boolean playerCombo=false;
	public boolean playerFlipKick=false;
	public static String color;
	public int [] colors = new int[10];
	static Scanner in=new Scanner(System.in);
	public static ImageIcon shrinkIcon(int width, int hight, ImageIcon i){
		return new ImageIcon(i.getImage().getScaledInstance(width, hight, Image.SCALE_SMOOTH)); 
	}
	public SpriteFighter(double x, double y,int rowForEveryoneButFalling,int fighterNumber) {
		this.walkingX=x;
		this.walkingY=y;
		if(fighterNumber!=2){
			this.jumpingX=x-2;
			this.jumpingY=y-43;
			this.punchingX=x-53;
			this.punchingY=y+2;
			this.kickingX=x-11;
			this.kickingY=y-29;
			this.flipKickingX=x-76;
			this.flipKickingY=y-29;
			this.comboX=x-98;
			this.comboY=y-129;
			this.fallingX=x+37;
			this.fallingY=y-25;
			this.jumpingSidewaysX=x-60;
			this.jumpingSidewaysY=y;
			this.alwaysX=x-17;
			this.alwaysY=y-5;
			this.rowForFalling=0;
		}
		else{
			this.rowForFalling=1;
			this.jumpingX=this.walkingX+4;
			this.punchingX=this.walkingX+39;
			this.kickingX=this.walkingX+38;
			this.flipKickingX=this.walkingX+33;
			this.comboX=this.walkingX+34;
			this.fallingX=this.walkingX-73;
			this.jumpingSidewaysX=this.walkingX+36;
			this.jumpingY=y-43;
			this.punchingY=y+2;
			this.kickingY=y-29;
			this.flipKickingY=y-29;
			this.comboY=y-129;
			this.fallingY=y-25;
			this.jumpingSidewaysY=y;
			this.alwaysX=this.walkingX-15;
			this.alwaysY=y-5;
		}
		this.rowForEveryoneButFalling=rowForEveryoneButFalling;
		this.fighterNumber=fighterNumber;
		if(fighterNumber==1){
			GameBoard.p1x=(int)x;
			GameBoard.p1y=(int)walkingY;
			currentSprite=walkingSprites[1][1];
		}
		if(fighterNumber==2){
			GameBoard.p2x=(int)x;
			GameBoard.p2y=(int)walkingY;
			currentSprite=walkingSprites[0][1];
		}
	}
	public SpriteFighter(String color){
		this.color=color;
		System.out.println(color);
	}
	public SpriteFighter(){
	}
	static{
//		System.out.println("Enter a color from this colors for the player:");
//		System.out.println("red");
//		System.out.println("white");
//		System.out.println("yellow");
//		System.out.println("light blue");
//		System.out.println("blue");
//		System.out.println("black");
//		System.out.println("green");
//		System.out.println("orange");
//		System.out.println("purple");
//		System.out.println("gray");
//		color=in.next();
		color="blue";
		ImageIcon ic = (shrinkIcon(720, 400,  new ImageIcon("fighter "+color+" movement sprites/fighter "+color+" walk.png")));
		if(color.equals("light"))
			ic = (shrinkIcon(720, 400,  new ImageIcon("fighter "+color+" blue movement sprites/fighter "+color+" blue walk.png")));
		int imageHeight = ic.getIconHeight();
		int imageWidth  = ic.getIconWidth();
		BufferedImage bimg = new BufferedImage(imageWidth ,imageHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics gr = bimg.getGraphics();
		gr.drawImage(ic.getImage(),0,0,null);
		int px1=0,pwalkingY=0,w1=imageWidth/walkingColumns, h1=imageHeight/walkingRows;
		System.out.println(h1+", "+w1);
		for(int i=0;i<walkingRows;i++){
			px1=0;
			for(int j=0;j<walkingColumns;j++){

				walkingSprites[i][j]=bimg.getSubimage(px1, pwalkingY, w1, h1);
				px1+=w1;

			}
			pwalkingY+=h1;
		}		
		ic = (shrinkIcon(720, 489,  new ImageIcon("fighter "+color+" movement sprites/fighter "+color+" straight jump.png")));
		if(color.equals("light"))
			ic = (shrinkIcon(720, 489,  new ImageIcon("fighter "+color+" blue movement sprites/fighter "+color+" blue straight jump.png")));
		imageHeight = ic.getIconHeight();
		imageWidth  = ic.getIconWidth();
		bimg = new BufferedImage(imageWidth ,imageHeight, BufferedImage.TYPE_INT_ARGB);
		gr = bimg.getGraphics();
		gr.drawImage(ic.getImage(),0,0,null);
		int pjumpingX=0,pjumpingY=0,w2=imageWidth/jumpingColumns, h2=imageHeight/jumpingRows;
		System.out.println(h2+", "+w2);
		for(int i=0;i<jumpingRows;i++){
			pjumpingX=0;
			for(int j=0;j<jumpingColumns;j++){

				jumpingSprites[i][j]=bimg.getSubimage(pjumpingX, pjumpingY, w2, h2);
				pjumpingX+=w2;

			}
			pjumpingY+=h2;
		}	
		ic = (shrinkIcon(950, 400,  new ImageIcon("fighter "+color+" movement sprites/fighter "+color+" punch.png")));
		if(color.equals("light"))
			ic = (shrinkIcon(950, 400,  new ImageIcon("fighter "+color+" blue movement sprites/fighter "+color+" blue punch.png")));
		imageHeight = ic.getIconHeight();
		imageWidth  = ic.getIconWidth();
		bimg = new BufferedImage(imageWidth ,imageHeight, BufferedImage.TYPE_INT_ARGB);
		gr = bimg.getGraphics();
		gr.drawImage(ic.getImage(),0,0,null);
		int ppunchingX=0,ppunchingY=0,w3=imageWidth/punchingColumns, h3=imageHeight/punchingrowForEveryoneButFallings;
		System.out.println(h3+", "+w3);
		for(int i=0;i<punchingrowForEveryoneButFallings;i++){
			ppunchingX=0;
			for(int j=0;j<punchingColumns;j++){

				punchingSprites[i][j]=bimg.getSubimage(ppunchingX, ppunchingY, w3, h3);
				ppunchingX+=w3;

			}
			ppunchingY+=h3;
		}		
		ic = (shrinkIcon(480, 525,  new ImageIcon("fighter "+color+" movement sprites/fighter "+color+" kick.png")));
		if(color.equals("light"))
			ic = (shrinkIcon(480, 525,  new ImageIcon("fighter "+color+" blue movement sprites/fighter "+color+" blue kick.png")));
		imageHeight = ic.getIconHeight();
		imageWidth  = ic.getIconWidth();
		bimg = new BufferedImage(imageWidth ,imageHeight, BufferedImage.TYPE_INT_ARGB);
		gr = bimg.getGraphics();
		gr.drawImage(ic.getImage(),0,0,null);
		int pkickingX=0,pkickingY=0,w4=imageWidth/kickingColumns, h4=imageHeight/kickingRows;
		System.out.println(h4+", "+w4);
		for(int i=0;i<kickingRows;i++){
			pkickingX=0;
			for(int j=0;j<kickingColumns;j++){

				kickingSprites[i][j]=bimg.getSubimage(pkickingX, pkickingY, w4, h4);
				pkickingX+=w4;

			}
			pkickingY+=h4;
		}		
		ic = (shrinkIcon(1800,488,  new ImageIcon("fighter "+color+" movement sprites/fighter "+color+" flip kick.png")));
		if(color.equals("light"))
			ic = (shrinkIcon(1800, 488,  new ImageIcon("fighter "+color+" blue movement sprites/fighter "+color+" blue flip kick.png")));
		imageHeight = ic.getIconHeight();
		imageWidth  = ic.getIconWidth();
		bimg = new BufferedImage(imageWidth ,imageHeight, BufferedImage.TYPE_INT_ARGB);
		gr = bimg.getGraphics();
		gr.drawImage(ic.getImage(),0,0,null);
		int pflipKickingX=0,pflipKickingY=0,w5=imageWidth/flipKickingColumns, h5=imageHeight/flipKickingRows;
		System.out.println(h5+", "+w5);
		for(int i=0;i<flipKickingRows;i++){
			pflipKickingX=0;
			for(int j=0;j<flipKickingColumns;j++){

				flipKickingSprites[i][j]=bimg.getSubimage(pflipKickingX, pflipKickingY, w5, h5);
				pflipKickingX+=w5;

			}
			pflipKickingY+=h5;
		}		
		ic = (shrinkIcon(2600,880,  new ImageIcon("fighter "+color+" movement sprites/fighter "+color+" high punch combo.png")));
		if(color.equals("light"))
			ic = (shrinkIcon(2600,880,  new ImageIcon("fighter "+color+" blue movement sprites/fighter "+color+" blue high punch combo.png")));
		imageHeight = ic.getIconHeight();
		imageWidth  = ic.getIconWidth();
		bimg = new BufferedImage(imageWidth ,imageHeight, BufferedImage.TYPE_INT_ARGB);
		gr = bimg.getGraphics();
		gr.drawImage(ic.getImage(),0,0,null);
		int pcomboX=0,pcomboY=0,w6=imageWidth/comboColumns, h6=imageHeight/comboRows;
		System.out.println(h6+", "+w6);
		for(int i=0;i<comboRows;i++){
			pcomboX=0;
			for(int j=0;j<comboColumns;j++){

				comboSprites[i][j]=bimg.getSubimage(pcomboX, pcomboY, w6, h6);
				pcomboX+=w6;

			}
			pcomboY+=h6;
		}
		ic = (shrinkIcon(1300,490,  new ImageIcon("fighter "+color+" movement sprites/fighter "+color+" fall.png")));
		if(color.equals("light"))
			ic = (shrinkIcon(1300,490,  new ImageIcon("fighter "+color+" blue movement sprites/fighter "+color+" blue fall.png")));
		imageHeight = ic.getIconHeight();
		imageWidth  = ic.getIconWidth();
		bimg = new BufferedImage(imageWidth ,imageHeight, BufferedImage.TYPE_INT_ARGB);
		gr = bimg.getGraphics();
		gr.drawImage(ic.getImage(),0,0,null);
		int pfallingX=0,pfallingY=0,w7=imageWidth/fallingColumns, h7=imageHeight/fallingRows;
		System.out.println(h7+", "+w7);
		for(int i=0;i<fallingRows;i++){
			pfallingX=0;
			for(int j=0;j<fallingColumns;j++){

				fallingSprites[i][j]=bimg.getSubimage(pfallingX, pfallingY, w7, h7);
				pfallingX+=w7;

			}
			pfallingY+=h7;
		}		
		ic = (shrinkIcon(1500,400,  new ImageIcon("fighter "+color+" movement sprites/fighter "+color+" jump sideways.png")));
		if(color.equals("light"))
			ic = (shrinkIcon(1500,400,  new ImageIcon("fighter "+color+" blue movement sprites/fighter "+color+" blue jump sideways.png")));
		imageHeight = ic.getIconHeight();
		imageWidth  = ic.getIconWidth();
		bimg = new BufferedImage(imageWidth ,imageHeight, BufferedImage.TYPE_INT_ARGB);
		gr = bimg.getGraphics();
		gr.drawImage(ic.getImage(),0,0,null);
		int pjumpingSidewaysX=0,pjumpingSidewaysY=0,w8=imageWidth/jumpingSidewaysColumns, h8=imageHeight/jumpingSidewaysRows;
		System.out.println(h8+", "+w8);
		for(int i=0;i<jumpingSidewaysRows;i++){
			pjumpingSidewaysX=0;
			for(int j=0;j<jumpingSidewaysColumns;j++){

				jumpingSidewaysSprites[i][j]=bimg.getSubimage(pjumpingSidewaysX, pjumpingSidewaysY, w8, h8);
				pjumpingSidewaysX+=w8;

			}
			pjumpingSidewaysY+=h8;
		}		
		ic = (shrinkIcon(474,423,  new ImageIcon("fighter "+color+" movement sprites/fighter "+color+" always.png")));
		if(color.equals("light"))
			ic = (shrinkIcon(474,423,  new ImageIcon("fighter "+color+" blue movement sprites/fighter "+color+" blue always.png")));
		imageHeight = ic.getIconHeight();
		imageWidth  = ic.getIconWidth();
		bimg = new BufferedImage(imageWidth ,imageHeight, BufferedImage.TYPE_INT_ARGB);
		gr = bimg.getGraphics();
		gr.drawImage(ic.getImage(),0,0,null);
		int alwaysX=0,alwaysY=0,w9=imageWidth/alwaysColumns, h9=imageHeight/alwaysRows;
		System.out.println(h9+", "+w9);
		for(int i=0;i<alwaysRows;i++){
			alwaysX=0;
			for(int j=0;j<alwaysColumns;j++){

				alwaysSprites[i][j]=bimg.getSubimage(alwaysX, alwaysY, w9, h9);
				alwaysX+=w9;

			}
			alwaysY+=h9;
		}		
	}
	public boolean inBoundsWalk(int x, int y, int rowForEveryoneButFalling){
		if(fighterNumber==1){
			if(rowForEveryoneButFalling==0){
				if(!GameBoard.playerTwoPunch && !GameBoard.playerTwoKick && !GameBoard.playerTwoFlipKick && !GameBoard.playerTwoCombo && !GameBoard.playerTwoJumpToLeft && !GameBoard.playerTwoJumpToRight){
					if(x<GameBoard.p2x){
						if(GameBoard.p1x+50>=GameBoard.p2x){
							return true;
						}
					}
				}
				else if(GameBoard.playerTwoPunch){
					if(x<GameBoard.p2x+48){
						if(GameBoard.p1x+50>=GameBoard.p2x+48){
							return true;
						}
					}
				}
				else if(GameBoard.playerTwoKick){
					if(x<GameBoard.p2x+6){
						if(GameBoard.p1x+50>=GameBoard.p2x+6){
							return true;
						}
					}
				}
				else if(GameBoard.playerTwoFlipKick){
					if(x<GameBoard.p2x+70){
						if(GameBoard.p1x+50>=GameBoard.p2x+70){
							return true;
						}
					}
				}
				else if(GameBoard.playerTwoCombo){
					if(x<GameBoard.p2x+94){
						if(GameBoard.p1x+50>=GameBoard.p2x+94){
							return true;
						}
					}
				}
				if(GameBoard.p1x+100>=1920){
					return true;
				}
			}
			if(rowForEveryoneButFalling==1){
				if(!GameBoard.playerTwoPunch && !GameBoard.playerTwoKick && !GameBoard.playerTwoFlipKick && !GameBoard.playerTwoCombo && !GameBoard.playerTwoJumpToLeft && !GameBoard.playerTwoJumpToRight){
					if(x>GameBoard.p2x){
						if(GameBoard.p1x<=GameBoard.p2x+50){
							return true;
						}
					}
				}
				else if(GameBoard.playerTwoPunch){
					if(x>GameBoard.p2x-39){
						if(GameBoard.p1x<=GameBoard.p2x+11){
							return true;
						}
					}
				}
				else if(GameBoard.playerTwoKick){
					if(x>GameBoard.p2x-38){
						if(GameBoard.p1x<=GameBoard.p2x+12){
							return true;
						}
					}
				}
				else if(GameBoard.playerTwoFlipKick){
					if(x>GameBoard.p2x-33){
						if(GameBoard.p1x<=GameBoard.p2x+17){
							return true;
						}
					}
				}
				else if(GameBoard.playerTwoCombo){
					if(x>GameBoard.p2x-34){
						if(GameBoard.p1x<=GameBoard.p2x+18){
							return true;
						}
					}
				}
				if(GameBoard.p1x<=0){
					return true;
				}
			}
		}
		if(fighterNumber==2){
			if(rowForEveryoneButFalling==0){
				if(!GameBoard.playerOnePunch && !GameBoard.playerOneKick && !GameBoard.playerOneFlipKick && !GameBoard.playerOneCombo && !GameBoard.playerOneJumpToLeft && !GameBoard.playerOneJumpToRight){
					if(x<GameBoard.p1x){
						if(GameBoard.p2x+50>=GameBoard.p1x){
							return true;
						}
					}
				}
				else if(GameBoard.playerOnePunch){
					if(x<GameBoard.p1x+48){
						if(GameBoard.p2x+50>=GameBoard.p1x+48){
							return true;
						}
					}
				}
				else if(GameBoard.playerOneKick){
					if(x<GameBoard.p1x+6){
						if(GameBoard.p2x+50>=GameBoard.p1x+6){
							return true;
						}
					}
				}
				else if(GameBoard.playerOneFlipKick){
					if(x<GameBoard.p1x+70){
						if(GameBoard.p2x+50>=GameBoard.p1x+70){
							return true;
						}
					}
				}
				else if(GameBoard.playerOneCombo){
					if(x<GameBoard.p1x+94){
						if(GameBoard.p2x+50>=GameBoard.p1x+94){
							return true;
						}
					}
				}
				if(GameBoard.p2x+100>=1920){
					return true;
				}
			}
			if(rowForEveryoneButFalling==1){
				if(!GameBoard.playerOnePunch && !GameBoard.playerOneKick && !GameBoard.playerOneFlipKick && !GameBoard.playerOneCombo && !GameBoard.playerOneJumpToLeft && !GameBoard.playerOneJumpToRight){
					if(x>GameBoard.p1x){
						if(GameBoard.p2x<=GameBoard.p1x+50){
							return true;
						}
					}
				}
				else if(GameBoard.playerOnePunch){
					if(x>GameBoard.p1x-39){
						if(GameBoard.p2x<=GameBoard.p1x+11){
							return true;
						}
					}
				}
				else if(GameBoard.playerOneKick){
					if(x>GameBoard.p1x-38){
						if(GameBoard.p2x<=GameBoard.p1x+12){
							return true;
						}
					}
				}
				else if(GameBoard.playerOneFlipKick){
					if(x>GameBoard.p1x-33){
						if(GameBoard.p2x<=GameBoard.p1x+17){
							return true;
						}
					}
				}
				else if(GameBoard.playerOneCombo){
					if(x>GameBoard.p1x-34){
						if(GameBoard.p2x<=GameBoard.p1x+18){
							return true;
						}
					}
				}
				if(GameBoard.p2x<=0){
					return true;
				}
			}
		}
		return false;
	}
	public boolean inBoundsFlipKick(int x, int y, int rowForEveryoneButFalling){
		if(fighterNumber==1){
			if(rowForEveryoneButFalling==0){
				if(x<GameBoard.p2x){
					if(GameBoard.p1x+120>=GameBoard.p2x){
						return true;
					}
				}
			}
			if(rowForEveryoneButFalling==1){
				if(x>GameBoard.p2x){
					if(GameBoard.p1x<=GameBoard.p2x+100){
						return true;
					}
				}
			}
		}
		if(fighterNumber==2){
			if(rowForEveryoneButFalling==0){
				if(x<GameBoard.p1x){
					if(GameBoard.p2x+120>=GameBoard.p1x){
						return true;
					}
				}
			}
			if(rowForEveryoneButFalling==1){
				if(x>GameBoard.p1x){
					if(GameBoard.p2x<=GameBoard.p1x+100){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean inBoundsCombo(int x, int y, int rowForEveryoneButFalling){
		if(fighterNumber==1){
			if(rowForEveryoneButFalling==0){
				if(x<GameBoard.p2x){
					if(GameBoard.p1x+90>=GameBoard.p2x){
						return true;
					}
				}
			}
			if(rowForEveryoneButFalling==1){
				if(x>GameBoard.p2x){
					if(GameBoard.p1x<=GameBoard.p2x+40){
						return true;
					}
				}
			}
		}
		if(fighterNumber==2){
			if(rowForEveryoneButFalling==0){
				if(x<GameBoard.p1x){
					if(GameBoard.p2x+90>=GameBoard.p1x){
						return true;
					}
				}
			}
			if(rowForEveryoneButFalling==1){
				if(x>GameBoard.p1x){
					if(GameBoard.p2x<=GameBoard.p1x+65){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean inBoundsJumpSideways(int x, int y, int rowForEveryoneButFalling){
		if(fighterNumber==1){
			if(rowForEveryoneButFalling==0){
				if(GameBoard.p1x+100>=1920){
					return true;
				}
			}
			if(rowForEveryoneButFalling==1){
				if(GameBoard.p1x<=0){
					return true;
				}
			}
		}
		if(fighterNumber==2){
			if(rowForEveryoneButFalling==0){
				if(GameBoard.p2x+100>=1920){
					return true;
				}
			}
			if(rowForEveryoneButFalling==1){
				if(GameBoard.p2x<=0){
					return true;
				}
			}
		}
		return false;
	}
	public boolean inBoundsPunch(int x, int y, int rowForEveryoneButFalling){
		if(fighterNumber==1){
			if(rowForEveryoneButFalling==0){
				if(x<GameBoard.p2x){
					if(GameBoard.p1x+90>=GameBoard.p2x){
						return true;
					}
				}
			}
			if(rowForEveryoneButFalling==1){
				if(x>GameBoard.p2x){
					if(GameBoard.p1x<=GameBoard.p2x+70){
						return true;
					}
				}
			}
		}
		if(fighterNumber==2){
			if(rowForEveryoneButFalling==0){
				if(x<GameBoard.p1x){
					if(GameBoard.p2x+90>=GameBoard.p1x){
						return true;
					}
				}
			}
			if(rowForEveryoneButFalling==1){
				if(x>GameBoard.p1x){
					if(GameBoard.p2x<=GameBoard.p1x+70){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean inBoundsKick(int x, int y, int rowForEveryoneButFalling){
		if(fighterNumber==1){
			if(rowForEveryoneButFalling==0){
				if(x<GameBoard.p2x){
					if(GameBoard.p1x+40>=GameBoard.p2x){
						return true;
					}
				}
			}
			if(rowForEveryoneButFalling==1){
				if(x>GameBoard.p2x){
					if(GameBoard.p1x<=GameBoard.p2x+70){
						return true;
					}
				}
			}
		}
		if(fighterNumber==2){
			if(rowForEveryoneButFalling==0){
				if(x<GameBoard.p1x){
					if(GameBoard.p2x+40>=GameBoard.p1x){
						return true;
					}
				}
			}
			if(rowForEveryoneButFalling==1){
				if(x>GameBoard.p1x){
					if(GameBoard.p2x<=GameBoard.p1x+70){
						return true;
					}
				}
			}
		}
		return false;
	}
	public static void punchHitting() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SoundsForGame/Batman Punch-SoundBible.com-456755860.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void comboHitting() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("NewSoundsForGame/Double Hit.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void kickHitting() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("NewSoundsForGame/Jump1.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void flipKickHitting() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("NewSoundsForGame/Spin Kick.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void missing() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SoundsForGame/woosh sound effect.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			Thread.sleep(clip.getMicrosecondLength());
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void pain() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SoundsForGame/uh.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/100);
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void jumping() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("NewSoundsForGame/jump2.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			//			FloatControl gainControl = 
			//					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			//			gainControl.setValue(-25.0f);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void jumpingSideways() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("NewSoundsForGame/jump3.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			//			FloatControl gainControl = 
			//					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			//			gainControl.setValue(-10.0f);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void painFalling() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SoundsForGame/Man Shouting Sound Effect.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = 
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-5.0f);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void falling() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("NewSoundsForGame/Falling.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			//			FloatControl gainControl = 
			//					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			//			gainControl.setValue(-5.0f);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void gameOver() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SoundsForGame/Game Over sound effect.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void fight() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SoundsForGame/Fight Sound Effect - Free Download.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = 
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void stepOne() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("NewSoundsForGame/Step1.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public static void stepTwo() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("NewSoundsForGame/Step2.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	int num1=0;
	int counter8=0;
	int counterOfSteps=0;
	public void walk(String move) {
		if(fighterNumber==1){
			GameBoard.p1x=(int)walkingX;
			GameBoard.p1y=(int)walkingY;			
			if(!GameBoard.playerOneJumpToLeft && !GameBoard.playerOnePunch && !GameBoard.playerOneCombo && !GameBoard.playerOneFlipKick && !GameBoard.playerOneKick && !GameBoard.playerOneFall && !GameBoard.gameOver){
				if(move.equals("Left")){
					this.rowForEveryoneButFalling=1;
					this.rowForFalling=0;
					if(num1%6==0){
						currentSprite=walkingSprites[rowForEveryoneButFalling][columnWalking];
						columnWalking++;
						columnWalking=columnWalking%walkingColumns;
						counterOfSteps++;
						if(counterOfSteps%6==0){
							System.out.println("stepTwo");
							new Thread(

									new Runnable() {

										@Override
										public void run() {
											stepTwo();
										}
									}
									).start();
						}
						else if(counterOfSteps%3==0){
							System.out.println("stepOne");
							new Thread(

									new Runnable() {

										@Override
										public void run() {
											stepOne();
										}
									}
									).start();
						}
					}
					num1++;
					if(!this.inBoundsWalk((int)this.walkingX,GameBoard.p2y,this.rowForEveryoneButFalling)){
						this.walkingX-=5;
						this.jumpingX=this.walkingX+2;
						this.punchingX=this.walkingX-48;
						this.kickingX=this.walkingX-6;
						this.flipKickingX=this.walkingX-70;
						this.comboX=this.walkingX-94;
						this.fallingX=this.walkingX+36;
						this.jumpingSidewaysX=this.walkingX-60;
						this.alwaysX=this.walkingX-14;
					}
				}
			}
			if(GameBoard.playerOneJumpToLeft){
				if(!this.inBoundsJumpSideways((int)this.walkingX,GameBoard.p2y,this.rowForEveryoneButFalling)){
					this.walkingX-=8;
					this.jumpingX=this.walkingX+2;
					this.punchingX=this.walkingX-48;
					this.kickingX=this.walkingX-6;
					this.flipKickingX=this.walkingX-70;
					this.comboX=this.walkingX-94;
					this.fallingX=this.walkingX+36;
					this.jumpingSidewaysX=this.walkingX-60;
					this.alwaysX=this.walkingX-14;
				}
			}
			if(!GameBoard.playerOneJumpToRight && !GameBoard.playerOnePunch && !GameBoard.playerOneCombo && !GameBoard.playerOneFlipKick && !GameBoard.playerOneKick && !GameBoard.playerOneFall && !GameBoard.gameOver){
				if(move.equals("Right")){
					this.rowForEveryoneButFalling=0;
					this.rowForFalling=1;
					if(num1%6==0){
						currentSprite=walkingSprites[rowForEveryoneButFalling][columnWalking];
						columnWalking++;
						columnWalking=columnWalking%walkingColumns;
						counterOfSteps++;
						if(counterOfSteps%6==0){
							System.out.println("stepTwo");
							new Thread(

									new Runnable() {

										@Override
										public void run() {
											stepTwo();
										}
									}
									).start();
						}
						else if(counterOfSteps%3==0){
							System.out.println("stepOne");
							new Thread(

									new Runnable() {

										@Override
										public void run() {
											stepOne();
										}
									}
									).start();
						}
					}
					num1++;
					if(!this.inBoundsWalk((int)this.walkingX,GameBoard.p2y,this.rowForEveryoneButFalling)){
						this.walkingX+=5;
						this.jumpingX=this.walkingX+4;
						this.punchingX=this.walkingX+39;
						this.kickingX=this.walkingX+38;
						this.flipKickingX=this.walkingX+33;
						this.comboX=this.walkingX+34;
						this.fallingX=this.walkingX-73;
						this.jumpingSidewaysX=this.walkingX+36;
						this.alwaysX=this.walkingX-15;
					}
				}
			}
			if(GameBoard.playerOneJumpToRight){
				if(!this.inBoundsJumpSideways((int)this.walkingX,GameBoard.p2y,this.rowForEveryoneButFalling)){
					this.walkingX+=8;
					this.jumpingX=this.walkingX+4;
					this.punchingX=this.walkingX+39;
					this.kickingX=this.walkingX+38;
					this.flipKickingX=this.walkingX+33;
					this.comboX=this.walkingX+34;
					this.fallingX=this.walkingX-73;
					this.jumpingSidewaysX=this.walkingX+36;
					this.alwaysX=this.walkingX-15;
				}
			}
		}
		if(fighterNumber==2){
			GameBoard.p2x=(int)walkingX;
			GameBoard.p2y=(int)walkingY;
			if(!GameBoard.playerTwoJumpToLeft && !GameBoard.playerTwoPunch && !GameBoard.playerTwoCombo && !GameBoard.playerTwoFlipKick && !GameBoard.playerTwoKick && !GameBoard.playerTwoFall && !GameBoard.gameOver){
				if(move.equals("Left")){
					this.rowForEveryoneButFalling=1;
					this.rowForFalling=0;
					if(num1%6==0){
						currentSprite=walkingSprites[rowForEveryoneButFalling][columnWalking];
						columnWalking++;
						columnWalking=columnWalking%walkingColumns;
						counterOfSteps++;
						if(counterOfSteps%6==0){
							System.out.println("stepTwo");
							new Thread(

									new Runnable() {

										@Override
										public void run() {
											stepTwo();
										}
									}
									).start();
						}
						else if(counterOfSteps%3==0){
							System.out.println("stepOne");
							new Thread(

									new Runnable() {

										@Override
										public void run() {
											stepOne();
										}
									}
									).start();
						}
					}
					num1++;
					if(!this.inBoundsWalk((int)this.walkingX,GameBoard.p1y,this.rowForEveryoneButFalling)){
						this.walkingX-=5;
						this.jumpingX=this.walkingX+2;
						this.punchingX=this.walkingX-48;
						this.kickingX=this.walkingX-6;
						this.flipKickingX=this.walkingX-70;
						this.comboX=this.walkingX-94;
						this.fallingX=this.walkingX+36;
						this.jumpingSidewaysX=this.walkingX-60;
						this.alwaysX=this.walkingX-14;
					}
				}
			}
			if(GameBoard.playerTwoJumpToLeft){
				if(!this.inBoundsJumpSideways((int)this.walkingX,GameBoard.p2y,this.rowForEveryoneButFalling)){
					this.walkingX-=8;
					this.jumpingX=this.walkingX+2;
					this.punchingX=this.walkingX-48;
					this.kickingX=this.walkingX-6;
					this.flipKickingX=this.walkingX-70;
					this.comboX=this.walkingX-94;
					this.fallingX=this.walkingX+36;
					this.jumpingSidewaysX=this.walkingX-60;
					this.alwaysX=this.walkingX-14;
				}
			}
			if(!GameBoard.playerTwoJumpToRight && !GameBoard.playerTwoPunch && !GameBoard.playerTwoCombo && !GameBoard.playerTwoFlipKick && !GameBoard.playerTwoKick && !GameBoard.playerTwoFall && !GameBoard.gameOver){
				if(move.equals("Right")){
					this.rowForEveryoneButFalling=0;
					this.rowForFalling=1;
					if(num1%6==0){
						currentSprite=walkingSprites[rowForEveryoneButFalling][columnWalking];
						columnWalking++;
						columnWalking=columnWalking%walkingColumns;
						counterOfSteps++;
						if(counterOfSteps%6==0){
							System.out.println("stepTwo");
							new Thread(

									new Runnable() {

										@Override
										public void run() {
											stepTwo();
										}
									}
									).start();
						}
						else if(counterOfSteps%3==0){
							System.out.println("stepOne");
							new Thread(

									new Runnable() {

										@Override
										public void run() {
											stepOne();
										}
									}
									).start();
						}
					}
					num1++;
					if(!this.inBoundsWalk((int)this.walkingX,GameBoard.p1y,this.rowForEveryoneButFalling)){
						this.walkingX+=5;
						this.jumpingX=this.walkingX+4;
						this.punchingX=this.walkingX+39;
						this.kickingX=this.walkingX+38;
						this.flipKickingX=this.walkingX+33;
						this.comboX=this.walkingX+34;
						this.fallingX=this.walkingX-73;
						this.jumpingSidewaysX=this.walkingX+36;
						this.alwaysX=this.walkingX-15;
					}
				}
			}
			if(GameBoard.playerTwoJumpToRight){
				if(!this.inBoundsJumpSideways((int)this.walkingX,GameBoard.p2y,this.rowForEveryoneButFalling)){
					this.walkingX+=8;
					this.jumpingX=this.walkingX+4;
					this.punchingX=this.walkingX+39;
					this.kickingX=this.walkingX+38;
					this.flipKickingX=this.walkingX+33;
					this.comboX=this.walkingX+34;
					this.fallingX=this.walkingX-73;
					this.jumpingSidewaysX=this.walkingX+36;
					this.alwaysX=this.walkingX-15;					
				}
			}
		}
	}
	int num2=0;
	int counter6=0;
	public void jump() {
		if(fighterNumber==1){
			GameBoard.p1x=(int)jumpingX;
			GameBoard.p1y=(int)jumpingY;
			if(columnJumping==0){
				counter6++;
				if(counter6==1){
					new Thread(

							new Runnable() {

								@Override
								public void run() {
									jumping();
								}
							}
							).start();
				}
			}
			if(num2%3==0){
				currentSprite=jumpingSprites[rowForEveryoneButFalling][columnJumping];
				columnJumping++;
			}
			if(columnJumping<=2){
				this.walkingY-=15;
				this.jumpingY=this.walkingY-43;
				this.punchingY=this.walkingY+2;
				this.kickingY=this.walkingY-29;
				this.flipKickingY=this.walkingY-29;
				this.comboY=this.walkingY-129;
				this.fallingY=this.walkingY-25;
				this.jumpingSidewaysY=this.walkingY;
			}
			if(columnJumping>2 && columnJumping<5){
				this.walkingY+=15;
				this.jumpingY=this.walkingY-43;
				this.punchingY=this.walkingY+2;
				this.kickingY=this.walkingY-29;
				this.flipKickingY=this.walkingY-29;
				this.comboY=this.walkingY-129;
				this.fallingY=this.walkingY-25;
				this.jumpingSidewaysY=this.walkingY;
			}
			num2++;
			if(columnJumping==5){
				GameBoard.p1x=(int)walkingX;
				GameBoard.p1y=(int)walkingY;
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				GameBoard.playerOneJump=false;
				num2=0;
				columnJumping=0;
				counter6=0;
			}
		}
		if(fighterNumber==2){
			GameBoard.p2x=(int)jumpingX;
			GameBoard.p2y=(int)jumpingY;
			if(columnJumping==0){
				counter6++;
				if(counter6==1){
					new Thread(

							new Runnable() {

								@Override
								public void run() {
									jumping();
								}
							}
							).start();
				}
			}
			if(num2%3==0){
				currentSprite=jumpingSprites[rowForEveryoneButFalling][columnJumping];
				columnJumping++;
			}
			if(columnJumping<=2){
				this.walkingY-=15;
				this.jumpingY=this.walkingY-43;
				this.punchingY=this.walkingY+2;
				this.kickingY=this.walkingY-29;
				this.flipKickingY=this.walkingY-29;
				this.comboY=this.walkingY-129;
				this.fallingY=this.walkingY-25;
				this.jumpingSidewaysY=this.walkingY;
			}
			if(columnJumping>2 && columnJumping<5){
				this.walkingY+=15;
				this.jumpingY=this.walkingY-43;
				this.punchingY=this.walkingY+2;
				this.kickingY=this.walkingY-29;
				this.flipKickingY=this.walkingY-29;
				this.comboY=this.walkingY-129;
				this.fallingY=this.walkingY-25;
				this.jumpingSidewaysY=this.walkingY;
			}
			num2++;
			if(columnJumping==5){
				GameBoard.p2x=(int)walkingX;
				GameBoard.p2y=(int)walkingY;
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				GameBoard.playerTwoJump=false;
				num2=0;
				columnJumping=0;
				counter6=0;
			}
		}
	}
	int num3=0;
	int counter1=0;
	boolean dontCheckPunch=false;
	public void punch() {
		if(fighterNumber==1){
			GameBoard.p1x=(int)punchingX;
			GameBoard.p1y=(int)punchingY;
			if(num3%5==0){
				currentSprite=punchingSprites[rowForEveryoneButFalling][columnPunching];
				columnPunching++;
			}
			num3++;
			if(!GameBoard.playerTwoJump && !dontCheckPunch){
				if(this.inBoundsPunch((int)this.walkingX, GameBoard.p2y, this.rowForEveryoneButFalling)){
					playerPunch=true;
				}
			}
			if(columnPunching==3){
				counter1++;
				if(playerPunch){
					if(counter1==1){
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										punchHitting();
										pain();
									}
								}
								).start();
					}
				}
				if(!playerPunch){
					if(counter1==1){
						dontCheckPunch=true;
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										missing();
									}
								}
								).start();
					}
				}
			}
			if(columnPunching==6){
				GameBoard.p1x=(int)walkingX;
				GameBoard.p1y=(int)walkingY;
				if(playerPunch){
					GameBoard.playerTwoPowerUpFromPunch=true;
					GameBoard.playerTwoHitByPunch=true;
					playerPunch=false;
				}
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				GameBoard.playerOnePunch=false;
				num3=0;
				columnPunching=0;
				counter1=0;
				dontCheckPunch=false;
			}
		}
		if(fighterNumber==2){
			GameBoard.p2x=(int)punchingX;
			GameBoard.p2y=(int)punchingY;
			if(num3%5==0){
				currentSprite=punchingSprites[rowForEveryoneButFalling][columnPunching];
				columnPunching++;
			}
			num3++;
			if(!GameBoard.playerOneJump && !dontCheckPunch){
				if(this.inBoundsPunch((int)this.walkingX, GameBoard.p1y, this.rowForEveryoneButFalling)){
					playerPunch=true;
				}
			}
			if(columnPunching==3){
				counter1++;
				if(playerPunch){
					if(counter1==1){
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										punchHitting();
										pain();
									}
								}
								).start();
					}
				}
				if(!playerPunch){
					if(counter1==1){
						dontCheckPunch=true;
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										missing();
									}
								}
								).start();
					}
				}
			}
			if(columnPunching==6){
				GameBoard.p2x=(int)walkingX;
				GameBoard.p2y=(int)walkingY;
				if(playerPunch){
					GameBoard.playerOnePowerUpFromPunch=true;
					GameBoard.playerOneHitByPunch=true;
					playerPunch=false;
				}
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				GameBoard.playerTwoPunch=false;
				num3=0;
				columnPunching=0;
				counter1=0;
				dontCheckPunch=false;
			}
		}
	}
	boolean dontCheckKick=false;
	int num4=0;
	int counter2=0;
	public void kick() {
		if(fighterNumber==1){
			GameBoard.p1x=(int)kickingX;
			GameBoard.p1y=(int)kickingY;
			if(num4%7==0){
				currentSprite=kickingSprites[rowForEveryoneButFalling][columnKicking];
				columnKicking++;
			}
			num4++;
			if(!GameBoard.playerTwoJump && !dontCheckKick){
				if(this.inBoundsKick((int)this.walkingX, GameBoard.p2y, this.rowForEveryoneButFalling)){
					playerKick=true;
				}
			}
			if(columnKicking==2){
				counter2++;
				if(playerKick){
					if(counter2==1){
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										kickHitting();
										pain();
									}
								}
								).start();
					}
				}
				if(!playerKick){
					if(counter2==1){
						dontCheckKick=true;
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										missing();
									}
								}
								).start();
					}
				}
			}
			if(columnKicking==4){
				GameBoard.p1x=(int)walkingX;
				GameBoard.p1y=(int)walkingY;
				if(playerKick){
					GameBoard.playerTwoPowerUpFromKick=true;
					GameBoard.playerTwoHitByKick=true;
					playerKick=false;
				}
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				GameBoard.playerOneKick=false;
				num4=0;
				columnKicking=0;
				counter2=0;
				dontCheckKick=false;
			}
		}
		if(fighterNumber==2){
			GameBoard.p2x=(int)kickingX;
			GameBoard.p2y=(int)kickingY;
			if(num4%7==0){
				currentSprite=kickingSprites[rowForEveryoneButFalling][columnKicking];
				columnKicking++;
			}
			num4++;
			if(!GameBoard.playerOneJump && !dontCheckKick){
				if(this.inBoundsKick((int)this.walkingX, GameBoard.p1y, this.rowForEveryoneButFalling)){
					playerKick=true;
				}
			}
			if(columnKicking==2){
				counter2++;
				if(playerKick){
					if(counter2==1){
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										kickHitting();
										pain();
									}
								}
								).start();
					}
				}
				if(!playerKick){
					if(counter2==1){
						dontCheckKick=true;
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										missing();
									}
								}
								).start();
					}
				}
			}
			if(columnKicking==4){
				GameBoard.p2x=(int)walkingX;
				GameBoard.p2y=(int)walkingY;
				if(playerKick){
					GameBoard.playerOnePowerUpFromKick=true;
					GameBoard.playerOneHitByKick=true;
					playerKick=false;
				}
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				GameBoard.playerTwoKick=false;
				num4=0;
				columnKicking=0;
				counter2=0;
				dontCheckKick=false;
			}
		}
	}
	boolean dontCheckFlipKick=false;
	int num5=0;
	int counter3=0;
	public void flipKick() {
		if(fighterNumber==1){
			GameBoard.p1x=(int)flipKickingX;
			GameBoard.p1y=(int)flipKickingY;
			if(num5%4==0){
				currentSprite=flipKickingSprites[rowForEveryoneButFalling][columnFlipKicking];
				columnFlipKicking++;
			}
			num5++;
			if(!GameBoard.playerTwoJump && !dontCheckFlipKick){
				if(this.inBoundsFlipKick((int)this.walkingX, GameBoard.p2y, this.rowForEveryoneButFalling)){
					playerFall=true;
					playerFlipKick=true;
				}
			}
			if(columnFlipKicking==5){
				counter3++;
				if(playerFall){
					if(counter3==1){
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										flipKickHitting();
										falling();
									}
								}
								).start();
					}
					GameBoard.playerTwoFall=true;
				}
				if(!playerFlipKick){
					if(counter3==1){
						dontCheckFlipKick=true;
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										missing();
									}
								}
								).start();
					}
				}
			}
			if(columnFlipKicking==10){
				if(playerFall){
					GameBoard.playerTwoFall=true;
					playerFall=false;
				}
				if(playerFlipKick){
					GameBoard.playerTwoPowerUpFromFlipKick=true;
					GameBoard.playerTwoHitByFlipKick=true;
					playerFlipKick=false;
				}
				GameBoard.playerOnePowerDownFromFlipKick=true;
				GameBoard.p1x=(int)walkingX;
				GameBoard.p1y=(int)walkingY;
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				GameBoard.playerOneFlipKick=false;
				num5=0;
				columnFlipKicking=0;
				counter3=0;
				dontCheckFlipKick=false;
			}
		}
		if(fighterNumber==2){
			GameBoard.p2x=(int)flipKickingX;
			GameBoard.p2y=(int)flipKickingY;
			if(num5%4==0){
				currentSprite=flipKickingSprites[rowForEveryoneButFalling][columnFlipKicking];
				columnFlipKicking++;	
			}
			num5++;
			if(!GameBoard.playerOneJump && !dontCheckFlipKick){
				if(this.inBoundsFlipKick((int)this.walkingX, GameBoard.p1y, this.rowForEveryoneButFalling)){
					playerFall=true;
					playerFlipKick=true;
				}
			}
			if(columnFlipKicking==5){
				counter3++;
				if(playerFall){
					if(counter3==1){
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										flipKickHitting();
										falling();
									}
								}
								).start();
					}
					GameBoard.playerOneFall=true;
				}
				if(!playerFlipKick){
					if(counter3==1){
						dontCheckFlipKick=true;
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										missing();
									}
								}
								).start();
					}
				}
			}
			if(columnFlipKicking==10){
				if(playerFall){
					GameBoard.playerOneFall=true;
					playerFall=false;
				}
				if(playerFlipKick){
					GameBoard.playerOnePowerUpFromFlipKick=true;
					GameBoard.playerOneHitByFlipKick=true;
					playerFlipKick=false;
				}
				GameBoard.playerTwoPowerDownFromFlipKick=true;
				GameBoard.p2x=(int)walkingX;
				GameBoard.p2y=(int)walkingY;
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				GameBoard.playerTwoFlipKick=false;
				num5=0;
				columnFlipKicking=0;
				counter3=0;
				dontCheckFlipKick=false;
			}
		}
	}
	int num6=0;
	int counter4=0;
	int counter5=0;
	boolean dontCheckCombo=false;
	public void combo() {
		if(fighterNumber==1){
			GameBoard.p1x=(int)comboX;
			GameBoard.p1y=(int)comboY;
			if(num6%5==0){
				currentSprite=comboSprites[rowForEveryoneButFalling][columnCombo];
				columnCombo++;	
			}
			num6++;
			if(!GameBoard.playerTwoJump && !dontCheckCombo){
				if(this.inBoundsCombo((int)this.walkingX, GameBoard.p2y, this.rowForEveryoneButFalling)){
					playerFall=true;
					playerCombo=true;
				}
			}
			if(columnCombo==4){
				counter4++;
				if(playerFall){
					if(counter4==1){
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										comboHitting();
										painFalling();
										falling();
									}
								}
								).start();
					}
					GameBoard.playerTwoFall=true;
					playerFall=false;
				}
				if(!playerFall){
					if(counter4==1){
						dontCheckCombo=true;
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										missing();
									}
								}
								).start();
					}
				}
			}
			if(columnCombo==10){
				counter5++;
				if(!playerFall){
					if(counter5==1){
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										missing();
									}
								}
								).start();
					}
				}
			}
			if(columnCombo==13){
				if(playerFall){
					GameBoard.playerTwoFall=true;
					playerFall=false;
				}
				if(playerCombo){
					GameBoard.playerTwoPowerUpFromCombo=true;
					GameBoard.playerTwoHitByCombo=true;
					playerCombo=false;
				}
				GameBoard.playerOnePowerDownFromCombo=true;
				GameBoard.p1x=(int)walkingX;
				GameBoard.p1y=(int)walkingY;
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				GameBoard.playerOneCombo=false;
				num6=0;
				columnCombo=0;
				counter4=0;
				counter5=0;
				dontCheckCombo=false;
			}
		}
		if(fighterNumber==2){
			GameBoard.p2x=(int)comboX;
			GameBoard.p2y=(int)comboY;
			if(num6%5==0){
				currentSprite=comboSprites[rowForEveryoneButFalling][columnCombo];
				columnCombo++;	
			}
			num6++;
			if(!GameBoard.playerOneJump && !dontCheckCombo){
				if(this.inBoundsCombo((int)this.walkingX, GameBoard.p1y, this.rowForEveryoneButFalling)){
					playerFall=true;
					playerCombo=true;
				}
			}
			if(columnCombo==4){
				counter4++;
				if(playerFall){
					if(counter4==1){
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										comboHitting();
										painFalling();
										falling();
									}
								}
								).start();
					}
					GameBoard.playerOneFall=true;
					playerFall=false;
				}
				if(!playerFall){
					if(counter4==1){
						dontCheckCombo=true;
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										missing();
									}
								}
								).start();
					}
				}
			}
			if(columnCombo==10){
				counter5++;
				if(!playerFall){
					if(counter5==1){
						new Thread(

								new Runnable() {

									@Override
									public void run() {
										missing();
									}
								}
								).start();
					}
				}
			}
			if(columnCombo==13){
				if(playerFall){
					GameBoard.playerOneFall=true;
					playerFall=false;
				}
				if(playerCombo){
					GameBoard.playerOnePowerUpFromCombo=true;
					GameBoard.playerOneHitByCombo=true;
					playerCombo=false;
				}
				GameBoard.playerTwoPowerDownFromCombo=true;
				GameBoard.p2x=(int)walkingX;
				GameBoard.p2y=(int)walkingY;
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				GameBoard.playerTwoCombo=false;
				num6=0;
				columnCombo=0;
				counter4=0;
				counter5=0;
				dontCheckCombo=false;
			}
		}

	}
	int num7=0;
	public void fall() {
		if(fighterNumber==1){
			if(GameBoard.gameOver && this.hitpoints==0){
				GameBoard.p1x=(int)fallingX;
				GameBoard.p1y=(int)fallingY;
				if(num7%4==0){
					currentSprite=fallingSprites[rowForFalling][columnFalling];
					columnFalling++;	
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				num7++;
				if(columnFalling==3){
					GameBoard.p1x=(int)fallingX;
					GameBoard.p1y=(int)fallingY;
					currentSprite=fallingSprites[rowForFalling][3];
					GameBoard.playerOneFall=false;
					num7=0;
					columnFalling=0;
				}
			}
			else{
				GameBoard.p1x=(int)fallingX;
				GameBoard.p1y=(int)fallingY;
				if(num7%10==0){
					currentSprite=fallingSprites[rowForFalling][columnFalling];
					columnFalling++;	
				}
				num7++;
				if(columnFalling==7){
					GameBoard.p1x=(int)walkingX;
					GameBoard.p1y=(int)walkingY;
					currentSprite=walkingSprites[rowForEveryoneButFalling][1];
					GameBoard.playerOneFall=false;
					num7=0;
					columnFalling=0;
				}
			}
		}
		if(fighterNumber==2){
			if(GameBoard.gameOver && this.hitpoints==0){
				GameBoard.p2x=(int)fallingX;
				GameBoard.p2y=(int)fallingY;
				if(num7%4==0){
					currentSprite=fallingSprites[rowForFalling][columnFalling];
					columnFalling++;	
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				num7++;
				if(columnFalling==3){
					GameBoard.p2x=(int)fallingX;
					GameBoard.p2y=(int)fallingY;
					currentSprite=fallingSprites[rowForFalling][3];
					GameBoard.playerTwoFall=false;
					num7=0;
					columnFalling=0;
				}
			}
			else{
				GameBoard.p2x=(int)fallingX;
				GameBoard.p2y=(int)fallingY;
				if(num7%10==0){
					currentSprite=fallingSprites[rowForFalling][columnFalling];
					columnFalling++;	
				}
				num7++;
				if(columnFalling==7){
					GameBoard.p2x=(int)walkingX;
					GameBoard.p2y=(int)walkingY;
					currentSprite=walkingSprites[rowForEveryoneButFalling][1];
					GameBoard.playerTwoFall=false;
					num7=0;
					columnFalling=0;
				}
			}
		}
	}
	int counter7=0;
	int num8=0;
	public void jumpSideways(String move) {
		if(fighterNumber==1){
			GameBoard.p1x=(int)jumpingSidewaysX;
			GameBoard.p1y=(int)jumpingSidewaysY;
			if(columnJumpingSideways==0){
				counter7++;
				if(counter7==1){
					new Thread(

							new Runnable() {

								@Override
								public void run() {
									jumpingSideways();
								}
							}
							).start();
				}
			}
			if(num8%4==0){
				currentSprite=jumpingSidewaysSprites[rowForEveryoneButFalling][columnJumpingSideways];
				columnJumpingSideways++;	
			}
			if(columnJumpingSideways<=4){
				this.walkingY-=10;
				this.jumpingY=this.walkingY-43;
				this.punchingY=this.walkingY+2;
				this.kickingY=this.walkingY-29;
				this.flipKickingY=this.walkingY-29;
				this.comboY=this.walkingY-129;
				this.fallingY=this.walkingY-25;
				this.jumpingSidewaysY=this.walkingY;
			}
			if(columnJumpingSideways>4 && columnJumpingSideways<9){
				this.walkingY+=10;
				this.jumpingY=this.walkingY-43;
				this.punchingY=this.walkingY+2;
				this.kickingY=this.walkingY-29;
				this.flipKickingY=this.walkingY-29;
				this.comboY=this.walkingY-129;
				this.fallingY=this.walkingY-25;
				this.jumpingSidewaysY=this.walkingY;
			}
			num8++;
			if(columnJumpingSideways==9){
				GameBoard.p1x=(int)walkingX;
				GameBoard.p1y=(int)walkingY;
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				if(move.equals("Right")){
					GameBoard.playerOneJumpToRight=false;
				}
				if(move.equals("Left")){
					GameBoard.playerOneJumpToLeft=false;
				}
				num8=0;
				columnJumpingSideways=0;
				counter7=0;
			}
		}
		if(fighterNumber==2){
			GameBoard.p2x=(int)jumpingSidewaysX;
			GameBoard.p2y=(int)jumpingSidewaysY;
			if(columnJumpingSideways==0){
				counter7++;
				if(counter7==1){
					new Thread(

							new Runnable() {

								@Override
								public void run() {
									jumpingSideways();
								}
							}
							).start();
				}
			}
			if(num8%4==0){
				currentSprite=jumpingSidewaysSprites[rowForEveryoneButFalling][columnJumpingSideways];
				columnJumpingSideways++;	
			}
			if(columnJumpingSideways<=4){
				this.walkingY-=10;
				this.jumpingY=this.walkingY-43;
				this.punchingY=this.walkingY+2;
				this.kickingY=this.walkingY-29;
				this.flipKickingY=this.walkingY-29;
				this.comboY=this.walkingY-129;
				this.fallingY=this.walkingY-25;
				this.jumpingSidewaysY=this.walkingY;
			}
			if(columnJumpingSideways>4 && columnJumpingSideways<9){
				this.walkingY+=10;
				this.jumpingY=this.walkingY-43;
				this.punchingY=this.walkingY+2;
				this.kickingY=this.walkingY-29;
				this.flipKickingY=this.walkingY-29;
				this.comboY=this.walkingY-129;
				this.fallingY=this.walkingY-25;
				this.jumpingSidewaysY=this.walkingY;
			}
			num8++;
			if(columnJumpingSideways==9){
				GameBoard.p2x=(int)walkingX;
				GameBoard.p2y=(int)walkingY;
				currentSprite=walkingSprites[rowForEveryoneButFalling][1];
				if(move.equals("Right")){
					GameBoard.playerTwoJumpToRight=false;
				}
				if(move.equals("Left")){
					GameBoard.playerTwoJumpToLeft=false;
				}
				num8=0;
				columnJumpingSideways=0;
				counter7=0;
			}
		}

	}
	int num9=0;
	public void always(){
		if(fighterNumber==1){
			GameBoard.p1x=(int)alwaysX;
			GameBoard.p1y=(int)alwaysY;
			if(num9%10==0){
				currentSprite=alwaysSprites[rowForEveryoneButFalling][columnAlways];
				columnAlways++;
				columnAlways=columnAlways%alwaysColumns;
			}
			num9++;
		}
		if(fighterNumber==2){
			GameBoard.p2x=(int)alwaysX;
			GameBoard.p2y=(int)alwaysY;
			if(num9%10==0){
				currentSprite=alwaysSprites[rowForEveryoneButFalling][columnAlways];
				columnAlways++;
				columnAlways=columnAlways%alwaysColumns;
			}
			num9++;
		}
	}
	public void setCol(int col){
		this.columnWalking=col;
	}
}