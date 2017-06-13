package view;
import java.awt.*;
import javax.swing.*;

public class MainView extends JFrame {



	    private final static int WINDOW_WIDTH = 400;
	    private final static int WINDOW_HEIGHT = 222;
	    private final String MENU_TITLE = "PokemonGOBack";
	    public JButton startGameButton;


	    /**
	     * Default constructor, calls initializing  method .
	     */
	    public MainView() {
	        initMenuWindow();
	    }

	    /**
	     * Creates the menu with default info.
	     */
	    private void initMenuWindow() {

	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setTitle(MENU_TITLE);
	        this.setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
	        this.setResizable(false);
	        this.pack();
	        this.setLocationRelativeTo(null);
	        startGameButton = new JButton("Start Game");
	        Container c = this.getContentPane();
	        c.setLayout(new GridBagLayout());
	        c.add(startGameButton, new GridBagConstraints());
	    }
	}
