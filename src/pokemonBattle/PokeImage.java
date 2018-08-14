package pokemonBattle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class PokeImage {
	
	File file1 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\charizard.png");
	File file2 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\chesnaught.png");
	File file3 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\crobat.png");
	File file4 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\garchomp.png");
	File file5 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\glaceon.png");
	File file6 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\imgFiles\\greninja.png");
	Map<String, File> imageFiles = new HashMap<String, File>();
	
	public PokeImage(){
		imageFiles.put("Charizard", file1);
		imageFiles.put("Chesnaught", file2);
		imageFiles.put("Crobat", file3);
		imageFiles.put("Garchomp", file4);
		imageFiles.put("Glaceon", file5);
		imageFiles.put("Greninja", file6);
		
	}
	
	public BufferedImage getImage(String name){
		BufferedImage image = null;
		
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
}
