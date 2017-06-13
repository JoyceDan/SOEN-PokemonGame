package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import model.Card.PokmonType;

public class StupLoadDecks {

	
	private Deck player_Deck ;
	private Deck AI_Deck;
	
    // Singleton
    private static StupLoadDecks instance = new StupLoadDecks();
    public static StupLoadDecks sharedInstance() {
    	if (instance == null)
    		instance = new StupLoadDecks();
        return instance;
    }
    
    private StupLoadDecks(){
    	player_Deck = new Deck();
    	AI_Deck = new Deck();
    	//ReadDecksFromFile();
    }
    
	public  void ReadDecksFromFile(){
		CreateStupDecks(AI_Deck,"deck1.txt");
		CreateStupDecks(player_Deck,"deck2.txt");
	}
	
	public Deck assignDecks(boolean player){
		if(player)
			return player_Deck;
		return AI_Deck;
			
	}
	
	/**
	 * create the deck denpends on different player.
	 * @param deck
	 * @param deck_file
	 */
	private void CreateStupDecks(Deck deck, String deck_file) {
		// TODO Auto-generated method stub
			try {
				List<String> numbers = new ArrayList<String>();
				Scanner deckFile = new Scanner(new File(deck_file));
				while (deckFile.hasNextLine()) {
					numbers.add(deckFile.nextLine());
				}
				for(String liString : numbers) {
					int eachNum = Integer.parseInt(liString);
					String aString = Files.readAllLines(Paths.get("cards.txt")).get(eachNum-1);
					if ((!aString.equals(""))&&(!aString.equals("#"))) {
						String[] getElement = aString.split(":");
						if (getElement[1].equalsIgnoreCase("energy")) {
							createEnergyCard(deck, aString);	
						}else if (getElement[1].equalsIgnoreCase("pokemon")) {
							createPokemonCard(deck, aString);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("There is no file :" + e.getMessage());
			}
	}

	/**
	 * create a pokemon card
	 * @param deck ( player or AI)
	 * @param aString = Each line in the card file denpends on "pokemon" type
	 */
	private void createPokemonCard(Deck deck, String aString) {
		// TODO Auto-generated method stub

		String[] getPokemon = aString.split(":");
		/**
		 * read damage and ablities form file. 
		 * there exists some problem: 
		 * 1. some damage have the condition
		 * 2. Not all of the cards have the damage. some card just have functions.(asleep etc.)
		 */
		ArrayList<Attack> attacks = new ArrayList<Attack>(4);
		
		String abilityString = aString.substring( aString.indexOf("attacks"));
		if (abilityString.indexOf(",") == -1) {
			//find the ability from position 4.
			System.err.println("the basic attack for "+getPokemon[0]+" "+abilityString+"\n");
			attacks = addAb(attacks,abilityString);
		} else {
			//problem: some pokemon have three ability. 
			String[] getAbility2 = abilityString.split(",");
			//find the ability from position 4.  energycard number in position3
			System.err.println("the 1st attack for "+getPokemon[0]+" "+getAbility2[0]+"\n");
			attacks = addAb(attacks,getAbility2[0]);
			//Second subString.  find the ability from position 3. energycard number in position 2.
			for (int i=1; i < getAbility2.length; i++) {
				System.err.println("the "+(i+1)+"th attack for "+getPokemon[0]+" "+getAbility2[i]+"\n");
				attacks = addAbc(attacks,getAbility2[i]);
			}
		}

		for(Attack attack: attacks) {
			System.out.println(attack);
		}
		
		if (getPokemon[3].equalsIgnoreCase("basic")) {
			int hp = Integer.parseInt(getPokemon[6]);
			Pokemon card = new Pokemon(getPokemon[0], hp , PokmonType.LEVEL1, attacks );
			deck.addCard(card);
			//save to hashmap
			deck.addToHash(card);
		} else {
			int hp = Integer.parseInt(getPokemon[7]);
			Pokemon card = new Pokemon(getPokemon[0], hp , PokmonType.STAGE1, attacks );
			deck.addCard(card);
			//save to hashmap
			deck.addToHash(card);
		}
	}

	private ArrayList<Attack> addAb(ArrayList<Attack> attacks, String abilityString) {
		System.out.println("ablity string is : "+abilityString);
		int rand;
		rand = ThreadLocalRandom.current().nextInt(10, 20);
		List<String> findAttack = new ArrayList<String>();
		
		String[] getAbility1 = abilityString.split(":");
		//去找文件里找伤害值
		findAttack.add(getAbility1[4]);
		for (String bString : findAttack) {
			System.out.println("addAB :"+bString);
			int eachPageNum = Integer.parseInt(bString);
			try {
				String attackLine = Files.readAllLines(Paths.get("abilities.txt")).get(eachPageNum-1);
				String[] splitAttack = attackLine.split(":");
				int energyNumber = Integer.parseInt(getAbility1[3]);
				Attack attack = new Attack(splitAttack[0],rand,energyNumber);
				System.out.println("attack name: "+attack.getAttacksName());
				System.out.println("attack damage: "+attack.getDamage());
				System.out.println("attack number enegycard: "+attack.getNumOfEnergyCard()+"\n");
				attacks.add(attack);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("No such a file");
			}
			
		}
		return attacks;
	}
	
	private ArrayList<Attack> addAbc(ArrayList<Attack> attacks, String abilityString) {
		System.out.println("addABC: "+abilityString);
		int rand;
		rand = ThreadLocalRandom.current().nextInt(10, 20);
		List<String> findAttack = new ArrayList<String>();
		String[] getAbility1 = abilityString.split(":");
		//go for abilities file
		findAttack.add(getAbility1[3]);
		for (String bString : findAttack) {
			System.out.println(bString);
			int eachPageNum = Integer.parseInt(bString);
			try {
				String attackLine = Files.readAllLines(Paths.get("abilities.txt")).get(eachPageNum-1);
				System.out.println(attackLine);
				String[] splitAttack = attackLine.split(":");
				int energyNumber = Integer.parseInt(getAbility1[2]);
				Attack attack = new Attack(splitAttack[0],rand,energyNumber);
				System.out.println("attack name: "+attack.getAttacksName());
				System.out.println("attack dam: "+attack.getDamage());
				System.out.println("attack ecergycard: "+attack.getNumOfEnergyCard()+"\n");
				attacks.add(attack);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("No such a file");
			}
			
		}
		return attacks;
	}

	/**
	 * create a energy card
	 * @param deck ( player or AI)
	 * @param aString = each line of card file depends on "energy" type
	 */
	private void createEnergyCard(Deck deck, String aString) {
		// TODO Auto-generated method stub
		 String[] getEnergyName = aString.split(":");
		 Energy card  = new Energy(getEnergyName[0], Card.CardType.ENERGY );
		//save to hashmap
		deck.addToHash(card);
		 deck.addCard(card);
	}
	

}
