package control;

import java.util.ArrayList;

import model.Attack;
import model.Card;
import model.CardCollection;
import model.Pokemon;

public class AI {

	private CardCollection<Card> hand ;
	private CardCollection<Pokemon> bench ;
	private GameEngine gameEngine;
	private boolean firstRound;
	
	public AI(CardCollection<Card> hand , CardCollection<Pokemon> bench , GameEngine gameEngine){
		this.hand = hand ;
		this.bench = bench ;
		this.gameEngine = gameEngine;
		firstRound = true;
	}
	
	
	public void  play(){
		
		if(gameEngine.getAI_AttackCard() == null)
			MoveCardToAttackArea();
		
		moveCardsToBench();
		findEnergyCardAndChargePokemon();
		MoveCardToAttackArea();
		
		// do not attack in the first round 
		if(firstRound){
			firstRound = false;
		}else{
			tryToAttackPlayer();
		}
		
		gameEngine.dealCard(true);
	}
	
	
	private void moveCardsToBench(){
		
		for(int i = 0 ; i < hand.getCardCount() ; i++){
			if(hand.getCard(i).getType().equals(Card.CardType.POKEMON)){
				if(bench.getCardCount() < 5){
					gameEngine.validateMovingCardToBench(hand.getCard(i), false);
					gameEngine.updateCommicuntionPanel("AI has moved a card to bench");
					sleep();
				}
			}
		}
	}
	
	/**
	 * can be change with dynamic programming approach
	 */
	public void MoveCardToAttackArea(){//TODO to Be fix  
		
		Pokemon bestCard = null; 
		Pokemon candidate = null;
		
		for (int i = 0 ; i < hand.getCardCount() ; i++){
			if(hand.getCard(i).getType().equals(Card.CardType.POKEMON)){
				candidate = (Pokemon) hand.getCard(i);
				if(bestCard == null){
					bestCard =  candidate;
				}else{
					if(bestCard.getHp() < candidate.getHp())
						bestCard = candidate;
				}
		}
	}
		
			
		for (int i = 0 ; i < bench.getCardCount() ; i++){
			if(bench.getCard(i).getType().equals(Card.CardType.POKEMON)){
				candidate =  (Pokemon) bench.getCard(i);
				if(bestCard == null){
					bestCard = candidate;
				}else{
					if(bestCard.getHp() < candidate.getHp())
						bestCard = candidate;
				}
			}
	}
		
		if (bestCard != null){
			sleep();
			gameEngine.validateMovingCardToAttackArea(bestCard , false);
		}
	}

	
	private void findEnergyCardAndChargePokemon(){
		
		Card energyCard = null ;
		String message = "";
		
		for(int i = 0 ; i < hand.getCardCount() ; i ++){
			if(hand.getCard(i).getType().equals(Card.CardType.ENERGY)){
				energyCard = hand.getCard(i);
				break; 
			}
		}
		
		if (energyCard != null){
			
			if(gameEngine.getAI_AttackCard() != null && !hasFullEnergyCard(gameEngine.getAI_AttackCard())){
				gameEngine.validateMovingCardToAttackArea(energyCard, false);
				sleep();
				message = "AI charge it's attack card with energy card";  
				gameEngine.updateCommicuntionPanel(message);
			} else{
				if (findPokemonOnBenchToCharge(energyCard)){
					sleep();
					message = "AI have charged a pokemone on bench with energy card";  
					gameEngine.updateCommicuntionPanel(message);
				}
					
			}
		}
	}
	
	private void tryToAttackPlayer(){
		
		
			int numOfEnergyCard = gameEngine.getAI_AttackCard().getNumOfENERGYCard();
			ArrayList<Attack> attacks = gameEngine.getAI_AttackCard().getAttakcs();
			Attack bestAttack = null ;
			for ( Attack attack : attacks){
				if(attack.getNumOfEnergyCard() >= numOfEnergyCard)
					if(bestAttack != null ){
						if(bestAttack.getDamage() <= attack.getDamage())
							bestAttack = attack;
					}else
						bestAttack = attack;
					
			}
			
			if(bestAttack != null){
				sleep();
				gameEngine.attack(gameEngine.getPlayer_AttackCard() , bestAttack, true);
				gameEngine.updateCommicuntionPanel("AI has attack u ): ");
			}
	}
	
	private void findPokemonCanEvolve(){
		
	}
	
	private boolean findPokemonOnBenchToCharge(Card energyCard){
			for(int i = 0 ; i < bench.getCardCount() ; i++){
				Card card  = bench.getCard(i);
				if(bench.getCard(i).equals(Card.CardType.POKEMON)){
					if(!hasFullEnergyCard((Pokemon)card)){
						gameEngine.validateCharging_evoleingBenchCard((Pokemon)card, energyCard, false );
						return true;
				}
			}
		}
			return false;
	}
	
	private boolean hasFullEnergyCard(Pokemon card){
		ArrayList<Attack> attacks = card.getAttakcs();
		int maxNumOfEnergyCard = 0 ;                          
		int numOfEnergyCard = card.getNumOfENERGYCard(); // number of energy pokemon has 
		
		for(Attack attack : attacks){
			if(attack.getNumOfEnergyCard() > maxNumOfEnergyCard )
				maxNumOfEnergyCard = attack.getNumOfEnergyCard();
		}
		
		if(maxNumOfEnergyCard > numOfEnergyCard ){
			return true;
		}
		return false;
	}
	
	private void sleep(){
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
