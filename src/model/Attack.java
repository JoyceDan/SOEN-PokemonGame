package model;

import java.util.ArrayList;

public class Attack {

		private String attacksName ;
		private int damage ;
		private int numOfEnergyCard;
		
	//TODO add the energy  card to the attack 
		
		public  Attack (String attacksName , int damage, int numOfEnergyCard ){
			this.attacksName = attacksName;
			this.damage = damage ;
			this.numOfEnergyCard = numOfEnergyCard;
		}
	
		public String toString(){
			return attacksName;
		}
		//---- getter & setter 
		public String getAttacksName() {
			return attacksName;
		}
	
		public void setAttacksName(String attacksName) {
			this.attacksName = attacksName;
		}
	
		public int getDamage() {
			return damage;
		}
	
		public void setDamage(int damage) {
			this.damage = damage;
		}

		public int getNumOfEnergyCard() {
			return numOfEnergyCard;
		}
		
		public void setNumOfEnergyCard(int numOfEnergyCard) {
			this.numOfEnergyCard = numOfEnergyCard;
		}
		
		
	}
