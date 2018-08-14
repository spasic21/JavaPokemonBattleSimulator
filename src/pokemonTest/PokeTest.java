package pokemonTest;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pokemonBattle.Pokemon;
import pokemonBattle.PokemonParser;

public class PokeTest extends JFrame{
	static Random random = new Random();
	static double randInt = random.nextInt(15) + 85;
	static double modifier = 0;
	static double damage = 0;
	static double min = 0.85, max = 1;
	

	static File file1 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\charizard.png");
	static File file2 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\chestnaught.png");
	static File file3 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\crobat.png");
	static File file4 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\garchomp.png");
	static File file5 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\glaceon.png");
	static File file6 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\greninja.png");
	static Map<String, File> imageFiles = new HashMap<String, File>();
	
	
	public PokeTest(){
		this.setTitle("PokeTest");
		this.setSize(500, 500);
		this.add(createPanel());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public JPanel createPanel(){
		JPanel panel = new JPanel();
		JLabel label = new JLabel(new ImageIcon(getImage("Greninja")));
		panel.add(label, BorderLayout.CENTER);
		
		return panel;
	}
	
	public static BufferedImage getImage(String name){
		BufferedImage image = null;
		
		imageFiles.put("Charizard", file1);
		imageFiles.put("Chestnaught", file2);
		imageFiles.put("Crobat", file3);
		imageFiles.put("Garchomp", file4);
		imageFiles.put("Glaceon", file5);
		imageFiles.put("Greninja", file6);
		try{
			for(Map.Entry<String, File> entry : imageFiles.entrySet()){
				if(name.equals(entry.getKey())){
					image = ImageIO.read(entry.getValue());
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return image;
		
	}
	
	public static void main(String[] args){
		new PokeTest();
	}
}
