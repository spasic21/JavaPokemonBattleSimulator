package pokemonBattle;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class PokeGui extends JFrame{

	List<Pokemon> team1 = new ArrayList<Pokemon>();
	List<Pokemon> team2 = new ArrayList<Pokemon>();
	File file1 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\docs\\PokemonTeam1.xml");
	File file2 = new File("D:\\MSOE\\Misc\\JavaPrograms\\PokemonBattle\\docs\\PokemonTeam1.xml");
	PokemonParser pokeParser = new PokemonParser(file1);
	PokemonParser pokeParser2 = new PokemonParser(file2);
	JButton battleButton = new JButton("Battle");
	JButton stepButton = new JButton("Step Button");
	JPanel panel1, panel2;
	JLabel nameOneLabel, lvOneLabel, hpOneLabel, nameTwoLabel, lvTwoLabel, hpTwoLabel, winnerLabel, imgLabel1, imgLabel2;
	PokeImage pokeImage = new PokeImage();
	double min = .85, max = 1;
	double damage = 0;
	boolean turn1 = false, turn2 = false;
	
	
	public PokeGui(){
		team1 = pokeParser.getPokemonTeam();
		team2 = pokeParser2.getPokemonTeam();
		this.setTitle("Pokemon Battle Simulation");
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(createPanel1(), BorderLayout.WEST);
		this.add(createPanel2(), BorderLayout.EAST);
		this.add(createPanel3(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public JPanel createPanel1(){
		panel1 = new JPanel();
		nameOneLabel = new JLabel();
		lvOneLabel = new JLabel();
		hpOneLabel = new JLabel();
		imgLabel1 = new JLabel(new ImageIcon(pokeImage.getImage(team1.get(0).getName())));
		
		panel1.setLayout(new GridLayout(4, 1));
		nameOneLabel.setText(team1.get(0).getName());
		lvOneLabel.setText(String.valueOf(team1.get(0).getLv()));
		hpOneLabel.setText(String.valueOf(team1.get(0).getTmpHp()) + "/" + String.valueOf(team1.get(0).getHp()));
		
		panel1.add(imgLabel1);
		panel1.add(nameOneLabel);
		panel1.add(lvOneLabel);
		panel1.add(hpOneLabel);
		
		return panel1;
	}
	
	public JPanel createPanel2(){
		panel2 = new JPanel();
		nameTwoLabel = new JLabel();
		lvTwoLabel = new JLabel();
		hpTwoLabel = new JLabel();
		imgLabel2 = new JLabel(new ImageIcon(pokeImage.getImage(team2.get(0).getName())));
		
		panel2.setLayout(new GridLayout(4, 1));
		nameTwoLabel.setText(team2.get(0).getName());
		lvTwoLabel.setText(String.valueOf(team2.get(0).getLv()));
		hpTwoLabel.setText(String.valueOf(team2.get(0).getTmpHp()) + "/" + String.valueOf(team2.get(0).getHp()));
		
		panel2.add(imgLabel2);
		panel2.add(nameTwoLabel);
		panel2.add(lvTwoLabel);
		panel2.add(hpTwoLabel);
		
		return panel2;
	}
	
	public JPanel createPanel3(){
		JPanel panel3 = new JPanel();
		winnerLabel = new JLabel();
		battleButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				battle();
			}});
		stepButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stepBattle();
			}});
		panel3.setLayout(new GridLayout(3, 1));
		
		panel3.add(winnerLabel);
		panel3.add(stepButton);
		panel3.add(battleButton);
		return panel3;
	}
	
	public void stepBattle(){
		winnerLabel.setText("Battling!");
		playerTurn();
	}
	
	public void battle(){
		winnerLabel.setText("Battling!");
		
		while(!teamDead(team1) && !teamDead(team2)){
			playerTurn();
//			playerTwoTurn();
		}
		teamWon();
	}
	
	public void teamWon(){
		if(teamDead(team1)){
			winnerLabel.setText("Congradulations Team 2! You Won!");
			stepButton.setEnabled(false);
			battleButton.setEnabled(false);
		}else if(teamDead(team2)){
			winnerLabel.setText("Congradulations Team 1! You Won!");
			stepButton.setEnabled(false);
			battleButton.setEnabled(false);
		}
	}
	
	public void playerTurn(){
		Random random = new Random();
		int randInt = random.nextInt(2);
		if(teamDead(team1) || teamDead(team2)){
			teamWon();
		}else{
			if(team1.get(0).getSpeed() > team2.get(0).getSpeed()){
				playerOneAction();
			}else if(team1.get(0).getSpeed() == team2.get(0).getSpeed()){
				if(randInt == 0){
					playerOneAction();
				}else{
					playerTwoAction();
				}
			}else{
				playerTwoAction();
			}
		}
		
	}
	
	public void playerOneAction(){
		Random random = new Random();
		if(isOneStrongAgainstTwo(team1.get(0).getType(), team2.get(0).getType())){
			min = (((2 * team1.get(0).getLv() + 10) / 250.0) * (team1.get(0).getAttack()/team2.get(0).getDefence()) * 65 + 2) * min;
			max = (((2 * team1.get(0).getLv() + 10) / 250.0) * (team1.get(0).getAttack()/team2.get(0).getDefence()) * 65 + 2) * max;
			damage = random.nextInt((int) min) + max;
		}else if(isOneWeakAgainstTwo(team1.get(0).getType(), team2.get(0).getType())){
			min = (((2 * team1.get(0).getLv() + 10) / 250.0) * (team1.get(0).getAttack()/team2.get(0).getDefence()) * 65 + 2) * min;
			max = (((2 * team1.get(0).getLv() + 10) / 250.0) * (team1.get(0).getAttack()/team2.get(0).getDefence()) * 65 + 2) * max;
			damage = random.nextInt((int) min) + max;
		}else if(noEffect(team1.get(0).getType(), team2.get(0).getType())){
			min = (((2 * team1.get(0).getLv() + 10) / 250.0) * (team1.get(0).getAttack()/team2.get(0).getDefence()) * 65 + 2) * min;
			max = (((2 * team1.get(0).getLv() + 10) / 250.0) * (team1.get(0).getAttack()/team2.get(0).getDefence()) * 65 + 2) * max;
			damage = random.nextInt((int) min) + max;
		}else{
			min = (((2 * team1.get(0).getLv() + 10) / 250.0) * (team1.get(0).getAttack()/team2.get(0).getDefence()) * 65 + 2) * min * 1.5;
			max = (((2 * team1.get(0).getLv() + 10) / 250.0) * (team1.get(0).getAttack()/team2.get(0).getDefence()) * 65 + 2) * max * 1.5;
			damage = random.nextInt((int) min) + max;
		}
		
		System.out.println("Team 1 damage: " + damage);
		team2.get(0).setHp(team2.get(0).getTmpHp() - (int)damage);
		hpTwoLabel.setText(String.valueOf(team2.get(0).getTmpHp()) + "/" + String.valueOf(team2.get(0).getHp()));
		min = .85;
		max = 1;
		turn1 = true;
		
		if(isDead(team2, team2.get(0).getTmpHp())){
			if(!teamDead(team2)){
				nameTwoLabel.setText(team2.get(0).getName());
				lvTwoLabel.setText(String.valueOf(team2.get(0).getLv()));
				hpTwoLabel.setText(String.valueOf(team2.get(0).getTmpHp()) + "/" + String.valueOf(team2.get(0).getHp()));
				imgLabel2.setIcon(new ImageIcon(pokeImage.getImage(team2.get(0).getName())));
				turn1 = false;
				playerTurn();
			}
		}else{
			if(turn2){
				turn2 = false;
			}else{
				playerTwoAction();
			}
		}
	}
	
	public void playerTwoAction(){
		Random random = new Random();
		if(isOneStrongAgainstTwo(team2.get(0).getType(), team1.get(0).getType())){
			min = (((2 * team2.get(0).getLv() + 10) / 250.0) * (team2.get(0).getAttack()/team1.get(0).getDefence()) * 65 + 2) * min;
			max = (((2 * team2.get(0).getLv() + 10) / 250.0) * (team2.get(0).getAttack()/team1.get(0).getDefence()) * 65 + 2) * max;
			damage = random.nextInt((int) min) + max;
		}else if(isOneWeakAgainstTwo(team2.get(0).getType(), team1.get(0).getType())){
			min = (((2 * team2.get(0).getLv() + 10) / 250.0) * (team2.get(0).getAttack()/team1.get(0).getDefence()) * 65 + 2) * min;
			max = (((2 * team2.get(0).getLv() + 10) / 250.0) * (team2.get(0).getAttack()/team1.get(0).getDefence()) * 65 + 2) * max;
			damage = random.nextInt((int) min) + max;
		}else if(noEffect(team2.get(0).getType(), team1.get(0).getType())){
			min = (((2 * team2.get(0).getLv() + 10) / 250.0) * (team2.get(0).getAttack()/team1.get(0).getDefence()) * 65 + 2) * min;
			max = (((2 * team2.get(0).getLv() + 10) / 250.0) * (team2.get(0).getAttack()/team1.get(0).getDefence()) * 65 + 2) * max;
			damage = random.nextInt((int) min) + max;
		}else{
			min = (((2 * team2.get(0).getLv() + 10) / 250.0) * (team2.get(0).getAttack()/team1.get(0).getDefence()) * 65 + 2) * min * 1.5;
			max = (((2 * team2.get(0).getLv() + 10) / 250.0) * (team2.get(0).getAttack()/team1.get(0).getDefence()) * 65 + 2) * max * 1.5;
			damage = random.nextInt((int) min) + max;
		}
		
		System.out.println("Team 2 damage: " + damage);
		team1.get(0).setHp(team1.get(0).getTmpHp() - (int)damage);
		hpOneLabel.setText(String.valueOf(team1.get(0).getTmpHp()) + "/" + String.valueOf(team1.get(0).getHp()));
		min = .85;
		max = 1;
		turn2 = true;
		
		if(isDead(team1, team1.get(0).getTmpHp())){
			if(!teamDead(team1)){
				nameOneLabel.setText(team1.get(0).getName());
				lvOneLabel.setText(String.valueOf(team1.get(0).getLv()));
				hpOneLabel.setText(String.valueOf(team1.get(0).getTmpHp()) + "/" + String.valueOf(team1.get(0).getHp()));
				imgLabel1.setIcon(new ImageIcon(pokeImage.getImage(team1.get(0).getName())));
				turn2 = false;
				playerTurn();
			}
		}else{
			if(turn1){
				turn1 = false;
			}else{
				playerOneAction();
			}
		}
	}
	
	public boolean isDead(List<Pokemon> team, double hp){
		boolean dead = false;
		if(hp == 0){
			dead = true;
			System.out.println(team.get(0).getName() + " fainted!");
			team.remove(0);
		}
		
		return dead;
	}
	
	public boolean teamDead(List<Pokemon> team){
		boolean teamDead = false;
		if(team.size() == 0){
			teamDead = true;
		}
		
		return teamDead;
	}
	
	public boolean isOneStrongAgainstTwo(String type1, String type2){
		boolean weak = false;
		
		if(type1.equals("Fire") && (type2.equals("Grass") || type2.equals("Ice") || type2.equals("Bug") || type2.equals("Steel"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Water") && (type2.equals("Fire") || type2.equals("Ground") || type2.equals("Rock") || type2.equals("Steel"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Electric") && (type2.equals("Water") || type2.equals("Flying"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max;  
			weak = true;
		}else if(type1.equals("Grass") && (type2.equals("Water") || type2.equals("Ground") || type2.equals("Rock"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Ice") && (type2.equals("Grass") || type2.equals("Ground") || type2.equals("Flying") || type2.equals("Dragon"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Fighting") && (type2.equals("Normal") || type2.equals("Ice") || type2.equals("Rock") || type2.equals("Dark") || type2.equals("Steel"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Poison") && (type2.equals("Grass") || type2.equals("Fairy"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Ground") && (type2.equals("Fire") || type2.equals("Electric")|| type2.equals("Poison") || type2.equals("Rock") || type2.equals("Steel"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Flying") && (type2.equals("Grass") || type2.equals("Fighting") || type2.equals("Bug"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Psychic") && (type2.equals("Fighting") || type2.equals("Poison"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Bug") && (type2.equals("Grass") || type2.equals("Psychic") || type2.equals("Dark"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Rock") && (type2.equals("Fire") || type2.equals("Ice") || type2.equals("Flying") || type2.equals("Bug"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Ghost") && (type2.equals("Psychic") || type2.equals("Ghost"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Dragon") && (type2.equals("Dragon"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Dark") && (type2.equals("Psychic") || type2.equals("Ghost"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Steel") && (type2.equals("Ice") || type2.equals("Rock") || type2.equals("Fairy"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}else if(type1.equals("Fairy") && (type2.equals("Fighting") || type2.equals("Dragon") || type2.equals("Dark"))){
			min = 1.5 * 2 * min; 
			max = 1.5 * 2 * max; 
			weak = true;
		}
		
		return weak;
	}
	
	public boolean isOneWeakAgainstTwo(String type1, String type2){
		boolean weak = false;
		
		if(type1.equals("Fire") && (type2.equals("Fire") || type2.equals("Water") || type2.equals("Rock") || type2.equals("Dragon"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}else if(type1.equals("Water") && (type2.equals("Water") || type2.equals("Grass")|| type2.equals("Dragon"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}else if(type1.equals("Electric") && (type2.equals("Electric") || type2.equals("Grass") || type2.equals("Dragon"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}else if(type1.equals("Grass") && (type2.equals("Fire") || type2.equals("Grass") || type2.equals("Poison") || type2.equals("Flying") || type2.equals("Bug") || type2.equals("Dragon"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}else if(type1.equals("Ice") && (type2.equals("Fire") || type2.equals("Water") || type2.equals("Ice") || type2.equals("Steel"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max; 
			weak = true;
		}else if(type1.equals("Fighting") && (type2.equals("Poison") || type2.equals("Flying") || type2.equals("Psychic") || type2.equals("Bug") || type2.equals("Fairy"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}else if(type1.equals("Poison") && (type2.equals("Poison") || type2.equals("Ground") || type2.equals("Rock") || type2.equals("Ghost"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}else if(type1.equals("Ground") && (type2.equals("Grass") || type2.equals("Bug"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}else if(type1.equals("Flying") && (type2.equals("Electric") || type2.equals("Rock") || type2.equals("Steel"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max; 
			weak = true;
		}else if(type1.equals("Psychic") && (type2.equals("Psychic") || type2.equals("Steel"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}else if(type1.equals("Bug") && (type2.equals("Fire") || type2.equals("Fighting") || type2.equals("Poison") || type2.equals("Flying") || type2.equals("Ghost") || type2.equals("Steel") || type2.equals("Fairy"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}else if(type1.equals("Rock") && (type2.equals("Fighting") || type2.equals("Ground") || type2.equals("Steel"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max; 
			weak = true;
		}else if(type1.equals("Ghost") && (type2.equals("Dark"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}else if(type1.equals("Dragon") && (type2.equals("Steel"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}else if(type1.equals("Dark") && (type2.equals("Fighting") || type2.equals("Dragon") || type2.equals("Fairy"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max; 
			weak = true;
		}else if(type1.equals("Steel") && (type2.equals("Fire") || type2.equals("Water") || type2.equals("Electric") || type2.equals("Dark"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max; 
			weak = true;
		}else if(type1.equals("Fairy") && (type2.equals("Fire") || type2.equals("Poison") || type2.equals("Steel"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max; 
			weak = true;
		}else if(type1.equals("Normal") && (type2.equals("Rock") || type2.equals("Steel"))){
			min = 1.5 * .5 * min; 
			max = 1.5 * .5 * max;
			weak = true;
		}
		
		return weak;
	}
	
	public boolean noEffect(String type1, String type2){
		boolean noEffect = false;
		
		if(type1.equals("Normal") && (type2.equals("Ghost"))){
			min = 0;
			max = 0;
			noEffect = true;
		}else if(type1.equals("Electric") && (type2.equals("Ground"))){
			min = 0;
			max = 0; 
			noEffect = true;
		}else if(type1.equals("Fighting") && (type2.equals("Ghost"))){
			min = 0;
			max = 0; 
			noEffect = true;
		}else if(type1.equals("Poison") && (type2.equals("Steel"))){
			min = 0;
			max = 0;
			noEffect = true;
		}else if(type1.equals("Ground") && (type2.equals("Flying"))){
			min = 0;
			max = 0;
			noEffect = true;
		}else if(type1.equals("Psychic") && (type2.equals("Dark"))){
			min = 0;
			max = 0;  
			noEffect = true;
		}else if(type1.equals("Dragon") && (type2.equals("Fairy"))){
			min = 0;
			max = 0; 
			noEffect = true;
		}
		
		return noEffect;
	}
	
	public static void main(String[] args){
		new PokeGui();
	}
}
