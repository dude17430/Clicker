package code;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Game {
	
	private int windowWidth;
	private int windowHeight;
	private String text; //'You Have: '
	private String text2;// Cookies!
	private static double num; //number of cookies
	private JLabel l1; //cookies text
	private JLabel l2; //cursor text
	@SuppressWarnings("unused")
	private Game game;
	private int t1cost; //cursor cost
	private int numTiers; //Determines the size of buildings array. each slot holds number of buildings of one type
	private double[] buildings; 
	private JFrame window;
	
	public Game(Game game){
		this.game = game;
		windowWidth = 900;
		windowHeight = 500;
		t1cost = 15;
		num = 0;
		text = "You Have: ";
		text2 = " Cookies!";
		numTiers = 1;
		buildings = new double[1];
//		buildings[0] = 0.0;
		setupWindow();
		gameLoop();
		
	}
	

	private void setupWindow(){
		window = new JFrame("Clicker By: David Rowe | Verison: INDEV");
		window.setSize(windowWidth, windowHeight);
		window.setResizable(false);
		l1 = new JLabel(text + num + text2);
		
		JButton b1 = new JButton();//The big cookie :D
		b1.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent e){num++;}
		});
		
		try {
			BufferedImage img = ImageIO.read(getClass().getResource("/resources/Crystal_Project_cookie.png"));
			b1.setIcon(new ImageIcon(img));
			b1.setFocusPainted(false);
			b1.setContentAreaFilled(false);
			b1.setBorder(null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JButton b2 = new JButton("Tier 1");
		b2.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e){
				if(num>=t1cost){
					num-=t1cost;
					t1cost+=t1cost*.2;
					buildings[0]++;
					//increase Cps
				}
			}
		});
		l2 = new JLabel("Cost: "+t1cost+ "\n | Amount: "+buildings[0]);
		
		JPanel leftp = new JPanel();
		JPanel midelp = new JPanel();
		JPanel rightp = new JPanel();
		
		leftp.setSize(300, 300);
		midelp.setSize(300, 300);
		rightp.setSize(300, 300);
		
		leftp.setLayout(new FlowLayout());
		midelp.setLayout(new FlowLayout());
		rightp.setLayout(new FlowLayout());
		
		leftp.add(l1);
		midelp.add(b1);
		rightp.add(b2);
		rightp.add(l2);
		
		int rows = 1;
		int cols = 3;
		LayoutManager grid = new GridLayout(rows, cols);
//		LayoutManager flow = new FlowLayout();
		window.setLayout(grid);
		window.add(leftp);
		window.add(midelp);
		window.add(rightp);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	private void gameLoop(){
		while(true){
			update();
			render();
			debug();
		}
	}
	
	private void debug() {
		
	}


	private void render(){
		l1.setText(text + num + text2);
		l2.setText("Cost: "+t1cost+ "\n | Amount: "+buildings[0]);
	}
	
	private void update(){
		double sum;
		for(int i=0; i<numTiers; i++){
			sum = buildings[i]*.1;
		}
	}
	
	public void incrementNum(int i){
		num += i;
	}
}
