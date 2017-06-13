package model;

import java.util.ArrayList;

import model.Card.CardType;
import model.Card.PokmonType;

public class Pokemon extends Card {

	//attributes for pokmon 
	private int hp ;
	private int damage; 
	private PokmonType P_Type;
	private ArrayList <Attack> attacks;
	private int numOfENERGYCard; 
	
	/*
	 * construct for pokmon
	 */
	public Pokemon(String name , int hp , PokmonType P_Type, ArrayList <Attack> attacks){
		super(name , Card.CardType.POKEMON);
		this.hp = hp;
		this.P_Type = P_Type;
		this.attacks = attacks;
		damage = hp; 
		numOfENERGYCard = 0;
	}

	public void  gotHit(int damageReceived){
		damage = damage - damageReceived;
	}
	
	/*
	 * true is alive 
	 */
	public boolean isPokmonAlive(){
		   return (damage  >= 1);
	}
	
	
	/*
	 * hit the Pokmon and check if the pokmon is still alive
	 * true is alive 
	 */
	public  boolean hitPokmon(Attack attack , Pokemon card ){
		card.gotHit(attack.getDamage());
		return card.isPokmonAlive();
	}

	
	public String toString() {
		String info = "";
		
		info += " Name: " + getName() + "\n Type: " +type.toString();
		info += "\n HP: " + hp + "\n P_Type " + P_Type.toString();
		info += "\n numOfENERGYCard: " + numOfENERGYCard;
		info += "\n damage: " + damage ;
		info +="\n-------------\n";
		for(int i = 0 ; i < attacks.size() ; i++){
			info += " Ability:  " + (i+1);
			info += "\n Name: " + attacks.get(i).getAttacksName();
			info +="\n Damage: " + attacks.get(i).getDamage();
			info +="\n NumOfEnergyCard " +  attacks.get(i).getNumOfEnergyCard();
			info +="\n-------------\n";
		}
			return info;
	}
	
		public int getNumOfENERGYCard() {
			return numOfENERGYCard;
		}

		public void setNumOfENERGYCard(int numOfENERGYCard) {
			this.numOfENERGYCard = numOfENERGYCard;
		}

		public int getHp() {
			return hp;
		}

		public void setHp(int hp) {
			this.hp = hp;
		}

		public int getDamage() {
			return damage;
		}

		public void setDamage(int damage) {
			this.damage = damage;
		}

		public PokmonType getP_Type() {
			return P_Type;
		}

		public void setP_Type(PokmonType p_Type) {
			P_Type = p_Type;
		}

		public ArrayList<Attack> getAttakcs() {
			return attacks;
		}

		public void setAttakcs(ArrayList<Attack> attakcs) {
			this.attacks = attakcs;
		}


	
}
