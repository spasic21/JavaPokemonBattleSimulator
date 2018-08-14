package pokemonBattle;

public class Pokemon {

	String name, type;
	double hp, tmpHP, lv, attack, defence, speed;
	
	public Pokemon(String name, String type, double lv, double hp, double attack, double defence, double speed){
		this.name = name;
		this.type = type;
		this.lv = lv;
		this.hp = hp;
		tmpHP = hp;
		this.attack = attack;
		this.defence = defence;
		this.speed = speed;
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public double getHp(){
		return hp;
	}
	
	public double getTmpHp(){
		return tmpHP;
	}
	
	public double getLv(){
		return lv;
	}
	
	public double getAttack(){
		return attack;
	}
	
	public double getDefence(){
		return defence;
	}
	
	public double getSpeed(){
		return speed;
	}
	
	public void setHp(double newHp){
		tmpHP = newHp;
		if(tmpHP < 0){
			tmpHP = 0;
		}
	}
}
