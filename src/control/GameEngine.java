package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import model.Attack;
import model.Card;
import model.CardCollection;
import model.Deck;
import model.Pokemon;
import model.StupLoadDecks;
import view.BaseWindow;
import view.Coordinate;

public class GameEngine {

	
	//TODO prize card when u kill the pokemon 
	//TODO good response by AI 

	private Pokemon player_AttackCard;
	private Pokemon AI_AttackCard;
	private Card cardInfo;
	
	private CardCollection<Card> player_Hand;
	private CardCollection<Card> AI_Hand;
	private CardCollection<Pokemon> player_Bench;
	private CardCollection<Pokemon> AI_Bench;
	private CardCollection<Card> player_Trash;
	private CardCollection<Card> AI_Trash;
	private CardCollection<Card> player_Prize;
	private CardCollection<Card> AI_Prize;
	
	private Deck player_Deck;
	private Deck AI_Deck;
	
	private StupLoadDecks loadDeck;
	private BaseWindow baseWindo;
	private AI A_I ; 
	
    Timer paintingTimer;
    private final int REFRESH_RATE = 200;
	private ArrayList<Coordinate> Jpanels;
	private boolean playerUsedEngOREvo;
	private boolean AIFirstTurn;
	private boolean AIControl;
	
	public GameEngine(){
		Load_Assign_Deck();
		intiObjects();
		CreateView();
		createViewListener();
		intiTimerToRefreshScreen();
		prepareHands();
	}
	
	private void intiObjects(){
		player_Hand = new CardCollection<Card>();
		AI_Hand = new CardCollection<Card>();
		player_Bench = new CardCollection<Pokemon>();
		AI_Bench = new CardCollection<Pokemon>();
		player_Trash = new CardCollection<Card>();
		AI_Trash = new CardCollection<Card>();
		player_Prize = new CardCollection<Card>();
		AI_Prize = new CardCollection<Card>();
		A_I = new AI(AI_Hand, AI_Bench , this);
		playerUsedEngOREvo = false;
		AIFirstTurn = true ;
		AIControl = false;
	}
	
	private void Load_Assign_Deck(){
		loadDeck = StupLoadDecks.sharedInstance();
		loadDeck.ReadDecksFromFile();
		player_Deck = loadDeck.assignDecks(true);
		AI_Deck = loadDeck.assignDecks(false);
	}
	
	
	private void CreateView(){
		baseWindo = new BaseWindow();
		Jpanels =  baseWindo.getMainGameView().getJpanels(); // To refresh the view

		baseWindo.getMainGameView().getHandBoard().setCardCollection(AI_Hand);
		baseWindo.getMainGameView().getHandBoard_Player().setCardCollection(player_Hand);
		baseWindo.getMainGameView().getBench().setCardCollection(AI_Bench);
		baseWindo.getMainGameView().getBench_Player().setCardCollection(player_Bench);
		baseWindo.getMainGameView().getAttack().setCard(AI_AttackCard);
		baseWindo.getMainGameView().getAttack_Player().setCard(player_AttackCard);
	}
	
	private void intiTimerToRefreshScreen(){//TODO it can be replace by observer pattern 
		 paintingTimer = new Timer(REFRESH_RATE, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	refresh();	
	            }
	        });
	        paintingTimer.start();
		
	}
	
	
	private void refresh(){//TODO we can replace this with a observer pattern( would be nice )
		for(Coordinate coordinate : Jpanels  ){
			coordinate.refresh();
		}
	}
	
	
	private void prepareHands(){
		for(int i = 0 ; i < 7 ; i++){
			if(player_Deck ==null)
			player_Deck.dealCard();
			player_Hand.addCard(player_Deck.dealCard());
			AI_Hand.addCard(AI_Deck.dealCard());
		}
	}
	
	
//	private void preparePrizeCards(){
//		//TODO assign 6 cards each 
		// Player_Prize & AI_Prize
//	}
	
	private void createViewListener(){
		
		/**
		 * Bench_Player mouse listener 
		 */
		baseWindo.getMainGameView().getBench_Player().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                int x = e.getX();
                int y = e.getY();
                
                Pokemon benchPokemon = null;
                
                
                for (int i = 0 ; i < player_Bench.getCardCount() ; i++){
              	  if( (x >= ( 50 + (i + 2) * 90  + 5) && (x <= ( 50 + (i + 2) * 90  + 5) + 80 ))
              			  && ((y >= 10) && (y <= 100 )) ){
              		  benchPokemon = player_Bench.getCard(i);
              		  updateCardInfoPanel(player_Bench.getCard(i).toString());
              
              	  }
                }

                if((x >= 230 && x <= 695) &&( y >=5 && y <=120)){ // bench area
                	if (cardInfo != null && benchPokemon != null){
                		
                		if(validateCharging_evoleingBenchCard(benchPokemon, cardInfo , true )){
                            cardInfo = null;
                		}
                			
                	}else if(cardInfo != null && benchPokemon == null){
                		validateMovingCardToBench(cardInfo, true);
                	}
               }

          }
		});

		
		
		/**
		 * HandBoard_Player  mouse listener 
		 */
		baseWindo.getMainGameView().getHandBoard_Player().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                int x = e.getX();
                int y = e.getY();

                  for (int i = 0 ; i < player_Hand.getCardCount() ; i++){
                	  if( (x >= (10 +  (i + 2) * 90) && (x <= (10 +  (i + 2) * 90) + 80 ))
                			  && ((y >= 10) && (y <= 100 )) ){
                		  cardInfo = player_Hand.getCard(i);
                		  updateCardInfoPanel(cardInfo.toString());
                	  }
                  }
            }
		});
		
		
		/**
		 * playerAttack area  mouse listener 
		 */
		baseWindo.getMainGameView().getAttack_Player().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                int x = e.getX();
                int y = e.getY();
                Card card = null;
                
                	  if( (x >= 420) && (x <= 500)
                			  && ((y >= 10) && (y <= 100 )) ){
                		  
                		  if(player_AttackCard != null){
                			  if(cardInfo != null){
                				  System.out.println(cardInfo.getLocation());
                				  validateMovingCardToAttackArea(cardInfo , true);
                				  }
	                		  cardInfo = player_AttackCard;
	                		  updateCardInfoPanel(player_AttackCard.toString());
                		  }else{
                			  
                			  if(cardInfo != null){
                			  	  validateMovingCardToAttackArea(cardInfo , true);
                			  	cardInfo = null ; 
   
                			  }
                		  }
                		 
                	  }
                	  
                	  cardInfo = null;
                  }
		});
		
		
		
		/**
		 * AI attack area  mouse listener 
		 */
		baseWindo.getMainGameView().getAttack().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                int x = e.getX();
                int y = e.getY();

                if(AI_AttackCard != null){
                	  if( (x >= 420) && (x <= 500)
                			  && ((y >= 10) && (y <= 100 )) ){
                		  updateCardInfoPanel(AI_AttackCard.toString());
                	  }
                  }
                cardInfo = null;
            }
		});
		
		
		/**
		 * Player attack button listener  mouse listener 
		 */
		baseWindo.getMainGameView().getAttack_Player().getAttackButoon().addActionListener(new  ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {//TODO deal with combo_box
				
				if(player_AttackCard != null){
					Attack attackObj = (Attack) baseWindo.getMainGameView().getAttack_Player().getjComboBox().getSelectedItem();
					if(AI_AttackCard != null){
						if(player_AttackCard.getNumOfENERGYCard() >= attackObj.getNumOfEnergyCard()){
							attack(AI_AttackCard , attackObj, false);
							AITurn();
						}else{
							updateCommicuntionPanel("you need more energy card");
						}
					}else{
						updateCommicuntionPanel("you cannot attack now");
					}
				}
			}
		});
		
		
		
		/**
		 * mover card to AttackArea button listener  (InfoView)
		 */
		baseWindo.getMainGameView().getInfoView().getMoveCardTOAttackArea().addActionListener(new  ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cardInfo != null){
					if(validateMovingCardToAttackArea(cardInfo, true))
						cardInfo = null;
				}
				
			}
		});
		
		
		/**
		 * mover card to MoveCardTOBench button listener  (InfoView)
		 */
		baseWindo.getMainGameView().getInfoView().getMoveCardTOBench().addActionListener(new  ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
		
				if (cardInfo != null){
					if(validateMovingCardToBench(cardInfo , true)){
						cardInfo = null;
					}
				}
			}
		});
		
		
		/**
		 * End Turn button listener  (InfoView)
		 */
		baseWindo.getMainGameView().getInfoView().getEndTurn().addActionListener(new  ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AITurn();
			}
		});
		
	}// end of createViewListener
		
	
	private void AITurn(){
		if(player_AttackCard != null){
			if(AIFirstTurn){
				AIFirstTurn = false;
			}else
				dealCard(false);
			
			AIControl = true;
			creatThreadForAI();
			//TODO Stop Player control
			playerUsedEngOREvo = false;
			
		}else {
			updateCommicuntionPanel("you have to have one card at the attack area before ending your turn");
		}
	}
	
	
	private void creatThreadForAI(){
		Thread thread = new Thread(new Runnable() {

		    @Override
		    public void run() {
		    	A_I.play(); 
		    }
		});

		thread.start();
	}
	public void dealCard(boolean player){
		try{
			if(player)		
				player_Hand.addCard(player_Deck.dealCard());
			else
				AI_Hand.addCard(AI_Deck.dealCard());
			}catch(IllegalStateException e){
				if(player)
					updateCommicuntionPanel("you do not have any more cards in your deck");
				else 
					updateCommicuntionPanel("AI Deck has no more card");
			}
		}
	
	public boolean validateMovingCardToAttackArea(Card card , boolean player){// card = cardInfo & player_AttackCard 
		
		String message = ""; 
		Pokemon attackCard;
		
		if(player)
			attackCard = player_AttackCard;
		else
			attackCard = AI_AttackCard;
			
		if( attackCard != null){ 
			
				if(card.getType().equals(Card.CardType.ENERGY)){

					if(player && !playerUsedEngOREvo){
						attackCard.setNumOfENERGYCard(attackCard.getNumOfENERGYCard() + 1);
						deleteCard(card , player);
						message = "your pokemon has " + attackCard.getNumOfENERGYCard() + " energy cards" ;
						updateCommicuntionPanel(message);
						playerUsedEngOREvo = true ; 
					}else if(!player){
						deleteCard(card , player);
						attackCard.setNumOfENERGYCard(attackCard.getNumOfENERGYCard() + 1);
					}else{
						message = "you only can use one energy cards turn" ;
						updateCommicuntionPanel(message);
						return false;
					}
					
					return true;
					
				}else if(card.getType().equals(Card.CardType.POKEMON)){
						
						Pokemon castPokemon = (Pokemon)card;
						if (castPokemon.getP_Type().equals(Card.PokmonType.STAGE1) &&
						attackCard.getP_Type().equals(Card.PokmonType.LEVEL1)) {
					

//						
//						 deleteCard(card , player);
//						if(player){
//							player_AttackCard = evolve(card);
//							UpdateJComboBox(player_AttackCard);
//						}else{
//							AI_AttackCard = evolve(card);
//						}
//						message = "a pokemon has evolve to stage 1 pokemon";
//						if(player)
//							updateCommicuntionPanel(message);
//						return true;
//					}else{
//						message = "TO move a card to the attack area it has to be a pokemon type(Stage1) or Energy Card";
//						if(player)
//							updateCommicuntionPanel(message);
//						return false;
//					}
					
					return true;
					
						}
				}
					
		}else{// no card in the attack area

			if(card.getType().equals(Card.CardType.POKEMON)){
				Pokemon castpokemon = (Pokemon) card;
				if(castpokemon.getP_Type().equals(Card.PokmonType.LEVEL1)) {
					
					deleteCard(card , player, false);
					message = " new pokemon in the attack area \n Pokemon Info \n ";
					message += castpokemon.toString();
					card.setLocation("Attack");
					if(player){
						updateCommicuntionPanel(message);
						player_AttackCard = castpokemon;
						UpdateJComboBox(player_AttackCard);
					}else {
						updateCommicuntionPanel(message);
						updateAIAttackCard(AI_AttackCard);
					}
				return true;
				}
			}
			
			}
			
		message = "TO move a card to the attack area it has to be a pokemon type(Level1)";
		if(player)
			updateCommicuntionPanel(message);
		return false ;
	}
	
	public boolean validateMovingCardToBench(Card card , boolean player){// TODO TO be fix i still have to add other conditions
		if(player){
			if (player_Bench.getCardCount() < 5 ){
				
				if(card.getType().equals(Card.CardType.POKEMON)){
					
					deleteCard(card, true, false);
					card.setLocation("Bench");
					player_Bench.addCard((Pokemon)card);
					return true;
				} else{
					updateCommicuntionPanel("you canot put energy card in the bench");
				}
			
			}else{
				updateCommicuntionPanel(" you can only have 5 cards in the bench");
			}
			
		}
		
		if(AI_Bench.getCardCount() < 5){
			if(card.getType().equals(Card.CardType.POKEMON)){
				deleteCard(card, false, false);
				card.setLocation("Bench");
				AI_Bench.addCard((Pokemon)card);
				return true;
				}
			}
			return false;
	}
	
	public boolean validateCharging_evoleingBenchCard(Pokemon cardOnBench , Card cardToValidate , boolean player){
						
				if(cardToValidate.getType().equals(Card.CardType.ENERGY)){
					
					if(player){
						if( !playerUsedEngOREvo){
							player_Bench.getCard(cardOnBench.getPosition()).setNumOfENERGYCard(cardOnBench.getNumOfENERGYCard()+1);
							updateCardInfoPanel(player_Bench.getCard(cardOnBench.getPosition()).toString());
							deleteCard(cardToValidate, player);
							playerUsedEngOREvo = true;
						}else{
							updateCommicuntionPanel("you only can one energy card every turn....");
							return false;
						}
					}else{
						AI_Bench.getCard(cardOnBench.getPosition()).setNumOfENERGYCard(cardOnBench.getNumOfENERGYCard()+1);
						deleteCard(cardToValidate, player);
					}
					
					
					return true;
					   
				}else if(cardToValidate.getType().equals(Card.CardType.POKEMON)){
					Pokemon castpokemon = (Pokemon) cardToValidate;
					if(castpokemon.getP_Type().equals(Card.PokmonType.STAGE1)
							&& cardOnBench.getP_Type().equals(Card.PokmonType.LEVEL1) ){
						
						//TODO evolve 
//						Card newCard = new Card (cardToValidate.getName(), cardToValidate.getHp(), 
//								Card.PokmonType.STAGE1 , cardToValidate.getAttakcs());
//						newCard.setDamage(cardInfo.getDamage());
//						newCard.setNumOfENERGYCard(cardInfo.getNumOfENERGYCard());
//						player_Bench.setCardAtPosition(cardInfo.getPosition(), newCard);
//    					updateCardInfoPanel(player_Bench.getCard(cardOnBench.getPosition()).cardInfo());
//    					deleteCard(cardToValidate, true);

						return true;
					}
				}
				
				return false;
	}
	
	/**
	 * to be implemented 
	 * @param card
	 * @return
	 */
//	private Card evolve(Pokemon card){
////		Card newCard = new Card (card.getName(), card.getHp(), Card.PokmonType.STAGE1 , card.getAttakcs());
////		newCard.setDamage(card.getDamage());
////		newCard.setNumOfENERGYCard(card.getNumOfENERGYCard());
////		UpdateJComboBox(newCard); // To update combobox
//		return newCard;
//	}
//	
	/**
	 * 
	 * return false if pokemon died 
	 * @return
	 */
	public boolean attack(Pokemon cardUnderAttack , Attack kindOfAttack, boolean player){
		cardUnderAttack.gotHit(kindOfAttack.getDamage());
		updateCommicuntionPanel(cardUnderAttack.getName() + " has been attack");
		if( cardUnderAttack.isPokmonAlive())
			return true;
		else
			deleteCard(cardUnderAttack , player);
		return false;
	}
	
	private void UpdateJComboBox(Pokemon card ){
		baseWindo.getMainGameView().getAttack_Player().setCard(card);
	}
	
	private Card copyInfo(Card card){
		Card newCard = new Card(card.getName(), card.getType());
		newCard.setLocation(card.getLocation());
		newCard.setPosition(card.getPosition());
		return newCard;
	}
	
	private Pokemon copyInfo (Pokemon pokemon){

		Pokemon newPokemon = new Pokemon(pokemon.getLocation() , 
				pokemon.getHp() , pokemon.getP_Type() , pokemon.getAttakcs());
		newPokemon.setDamage(pokemon.getDamage());
		newPokemon.setNumOfENERGYCard(pokemon.getNumOfENERGYCard());
		return newPokemon;
	}
	
	public void updateCommicuntionPanel(String message){
		baseWindo.getMainGameView().getInfoView().getT_communication().setText(message);
	}
	
	private void updateCardInfoPanel(String message){
		baseWindo.getMainGameView().getInfoView().getT_cardInfo().setText(message);
	}
	
	
	
	public void deleteCard(Card card , boolean player, boolean trash){
		if (card.getLocation().equals("Hand")){
			if(player){
				player_Hand.removeCard(card.getPosition());
				if(trash)
					addToTrash(card , true);
			}
			else{ 
				AI_Hand.removeCard(card.getPosition());
				if(trash)
					addToTrash(card , false);
			}
		}else if(card.getLocation().equals("Bench")){
			if(player){
				player_Bench.removeCard(card.getPosition());
				if(trash)
					addToTrash(card , true);
				
			}
			else {
				AI_Bench.removeCard(card.getPosition());
				if(trash)
					addToTrash(card , false);
			}
		}else{
			if(player){
				if(trash)
					addToTrash(player_AttackCard , true);
				player_AttackCard = null;
				UpdateJComboBox(null);
			}else{
				if(trash)
				addToTrash(AI_AttackCard , false);
				AI_AttackCard = null;
				A_I.MoveCardToAttackArea();
				updateAIAttackCard(AI_AttackCard);
			}
		}
		
	}
	
	private void deleteCard(Card card , boolean player){
		deleteCard(card , player , true);
	}
	
	
	private void updateAIAttackCard(Pokemon card){
		baseWindo.getMainGameView().getAttack().setCard(card);
	}
	
	private void addToTrash(Card card , boolean player){
		if(player){
			card.setLocation("Trash");
			player_Trash.addCard(card);
		}else{
			card.setLocation("Trash");
			AI_Trash.addCard(card);
		}
		
		
	}

	
	//------------------- getter for AI ------------------------
	public Pokemon getPlayer_AttackCard() {
		return player_AttackCard;
	}

	public Pokemon getAI_AttackCard() {
		return AI_AttackCard;
	}
	
	//------------------- getter and setter  for only testing ------------------------
	public CardCollection<Card> getPlayer_Hand() {
		return player_Hand;
	}

	public void setPlayer_Hand(CardCollection<Card> player_Hand) {
		this.player_Hand = player_Hand;
	}

	public CardCollection<Card> getAI_Hand() {
		return AI_Hand;
	}

	public void setAI_Hand(CardCollection<Card> aI_Hand) {
		AI_Hand = aI_Hand;
	}

	public CardCollection<Pokemon> getPlayer_Bench() {
		return player_Bench;
	}

	public void setPlayer_Bench(CardCollection<Pokemon> player_Bench) {
		this.player_Bench = player_Bench;
	}

	public CardCollection<Pokemon> getAI_Bench() {
		return AI_Bench;
	}

	public void setAI_Bench(CardCollection<Pokemon> aI_Bench) {
		AI_Bench = aI_Bench;
	}

	public CardCollection<Card> getPlayer_Trash() {
		return player_Trash;
	}

	public void setPlayer_Trash(CardCollection<Card> player_Trash) {
		this.player_Trash = player_Trash;
	}

	public CardCollection<Card> getAI_Trash() {
		return AI_Trash;
	}

	public void setAI_Trash(CardCollection<Card> aI_Trash) {
		AI_Trash = aI_Trash;
	}

	public Deck getPlayer_Deck() {
		return player_Deck;
	}

	public void setPlayer_Deck(Deck player_Deck) {
		this.player_Deck = player_Deck;
	}

	public Deck getAI_Deck() {
		return AI_Deck;
	}

	public void setAI_Deck(Deck aI_Deck) {
		AI_Deck = aI_Deck;
	}

	public void setAI_AttackCard(Pokemon aI_AttackCard) {
		AI_AttackCard = aI_AttackCard;
	}
	
	public BaseWindow getBaseWindo(){
		return baseWindo;
	}
}