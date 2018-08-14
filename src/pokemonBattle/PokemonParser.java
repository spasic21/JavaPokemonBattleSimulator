package pokemonBattle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PokemonParser {

	String name, type;
	int hp, lv, attack, defence, speed;
	List<Pokemon> pokemon = new ArrayList<Pokemon>();
	
	
	public PokemonParser(File file){
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
			NodeList nList = doc.getElementsByTagName("Pokemon");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					
					name = eElement.getElementsByTagName("name").item(0).getTextContent();
					type = eElement.getElementsByTagName("type").item(0).getTextContent();
					lv = Integer.parseInt(eElement.getElementsByTagName("lv").item(0).getTextContent());
					hp = Integer.parseInt(eElement.getElementsByTagName("hp").item(0).getTextContent());
					attack = Integer.parseInt(eElement.getElementsByTagName("attack").item(0).getTextContent());
					defence = Integer.parseInt(eElement.getElementsByTagName("defence").item(0).getTextContent());
					speed = Integer.parseInt(eElement.getElementsByTagName("speed").item(0).getTextContent());
					
					Pokemon poke = new Pokemon(name, type, lv, hp, attack, defence, speed);
					pokemon.add(poke);
				}
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public List<Pokemon> getPokemonTeam(){
		return pokemon;
	}
}
