package model;

import java.util.ArrayList;


public class Card {
	
	
	public static enum CardType{POKEMON , TRAINER , ENERGY}
	public static enum PokmonType{LEVEL1 , STAGE1};
	 
	protected  String name ;
	protected  CardType type;
	protected String location;
	protected int position;

	
	
	public  String toString(){
		return " Name: " + name + "\n Type: " +type.toString();
	}
	

	/*
	 * construct for TRAINER and  ENERGY
	 */
	public Card(String name ,  CardType type){
		this.name = name ;
		this.type = type ;
	}
	
	//-----------------------------Setter & getter -------------------------------------------
	
	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	
}
