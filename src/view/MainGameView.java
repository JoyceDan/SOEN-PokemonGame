package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class MainGameView extends JPanel {

	
	//TODO build the communication panel the card panel 

    
	private ArrayList<Coordinate> jpanels ;
	
	private HandView handBoard;
	private BenchView bench;
	private AttackCardView attack;
	
	private HandView handBoard_Player;
	private BenchView bench_Player;
	private AttackCardView attack_Player;
	private InfoView infoView ; 
    /**
     * Constructor sets all views used in the game view.
     */
    public MainGameView() {
 	   jpanels = new ArrayList<Coordinate>();
       setBackground(  Color.white);
       setBorder( BorderFactory.createEmptyBorder(5,5,5,5) );
       
       handBoard = new HandView( false );
       jpanels.add(handBoard);
       bench = new BenchView ();
       jpanels.add(bench);
       attack = new AttackCardView(false);
       jpanels.add(attack);
     
       attack_Player = new AttackCardView(true);
       jpanels.add(attack_Player);
       bench_Player = new BenchView ();
       jpanels.add(bench_Player);
       handBoard_Player = new HandView(true);
       jpanels.add(handBoard_Player);
       
       infoView = new InfoView();
   
       setLayout(null);
       
       for (int i = 0 ; i < jpanels.size() ; i++ ){
    	   add((JPanel)jpanels.get(i));
    	   jpanels.get(i).setBackgroundColor(Color.BLUE);
    	   jpanels.get(i).setViewLocation(5, 125 * i + 5 + (5 * i), 1000, 125);
    	   jpanels.get(i).refresh();
       }
       
       add(infoView);
//       infoView.setBackgroundColor(Color.YELLOW);
       infoView.setViewLocation(1005 , 5, 200, 775 );
       infoView.refresh();
       
      
    }

    //------------setter & getter 
    
	public ArrayList<Coordinate> getJpanels() {
		return jpanels;
	}

	public void setJpanels(ArrayList<Coordinate> jpanels) {
		this.jpanels = jpanels;
	}

	public HandView getHandBoard() {
		return handBoard;
	}

	public void setHandBoard(HandView handBoard) {
		this.handBoard = handBoard;
	}

	public BenchView getBench() {
		return bench;
	}

	public void setBench(BenchView bench) {
		this.bench = bench;
	}

	public AttackCardView getAttack() {
		return attack;
	}

	public void setAttack(AttackCardView attack) {
		this.attack = attack;
	}

	public HandView getHandBoard_Player() {
		return handBoard_Player;
	}

	public void setHandBoard_Player(HandView handBoard_Player) {
		this.handBoard_Player = handBoard_Player;
	}

	public BenchView getBench_Player() {
		return bench_Player;
	}

	public void setBench_Player(BenchView bench_Player) {
		this.bench_Player = bench_Player;
	}

	public AttackCardView getAttack_Player() {
		return attack_Player;
	}

	public void setAttack_Player(AttackCardView attack_Player) {
		this.attack_Player = attack_Player;
	}

	public InfoView getInfoView() {
		return infoView;
	}

	public void setInfoView(InfoView infoView) {
		this.infoView = infoView;
	}
	
	
      
}
