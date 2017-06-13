package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class InfoView extends JPanel implements Coordinate  {

	public int x = 1005;
	public int y = 5 ;
	private Font smallFont;
	
	private JLabel cardInfo ;
	private JLabel communication;
	private JTextArea T_cardInfo;
	private JTextArea T_communication;
	
	private JButton moveCardTOBench;
	private JButton moveCardTOAttackArea;
	private JButton endTurn ;
	
	
	
	public InfoView(){
		setLayout(null); 
		smallFont = new Font("SansSerif",Font.PLAIN, 14);
		//TODO color size and font
		cardInfo = new JLabel("Card Info");
		communication = new JLabel(" Communication");
		
		T_cardInfo = new JTextArea() ; 
		T_communication = new JTextArea() ;
		
		moveCardTOBench = new JButton("Move Card To bench Area");
		moveCardTOAttackArea = new JButton("Move Card TO Attack Area");
		endTurn = new JButton("End Turn");
		
		T_cardInfo.setFont(smallFont);
		T_communication.setFont(smallFont);
		T_cardInfo.setLineWrap(true);
		T_communication.setLineWrap(true);
		T_cardInfo.setWrapStyleWord(true);
		T_communication.setWrapStyleWord(true);
		T_cardInfo.setEditable(false);
		T_communication.setEditable(false);
	
		JScrollPane cardScroll = new JScrollPane(T_cardInfo);
		JScrollPane communicationScroll = new JScrollPane(T_communication);
		
		cardScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		communicationScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
	   	add(cardInfo);
	   	add(communication);
		add(cardScroll);
	   	add(communicationScroll);
	   	add(moveCardTOBench);
	   	add(moveCardTOAttackArea);
	   	add(endTurn);
	   	
	    //infoView.setViewLocation(1005 , 5, 200, 775 );
	   	
	   	cardInfo.setBounds(5, 10 , 100 , 20);
	   	cardScroll.setBounds(5, 50 , 185,270 );
	   	
	 	moveCardTOBench.setBounds(5 , 330, 180 , 30);
	   	moveCardTOAttackArea.setBounds(5 , 370, 180, 30);
	   	endTurn.setBounds(5 , 410, 180, 30);
	   	
	   	communication.setBounds(5,  450 , 100, 20);
	   	communicationScroll.setBounds(5, 480 , 185, 270);
	}
	
	
//--------- getter & setter 
	
	
	public JTextArea getT_cardInfo() {
		return T_cardInfo;
	}

	public JButton getMoveCardTOBench() {
		return moveCardTOBench;
	}

	public JButton getMoveCardTOAttackArea() {
		return moveCardTOAttackArea;
	}

	public JTextArea getT_communication() {
		return T_communication;
	}
	
	public JButton getEndTurn() {
		return endTurn;
	}


	//------------
	@Override
	public void refresh() {
		repaint();
	}

	@Override
	public void setBackgroundColor(Color color) {
		setBackground(color);
		
	}

	@Override
	public void setViewLocation(int x, int y, int width, int height) {
		setBounds(x , y, width , height );
	}


}
