package control;

import javax.swing.SwingUtilities;

public class PokmonDrive {
	
	

	    public static void main(String[] args) {

	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new MainViewController().mainViewMenu.setVisible(true);
	            }
	        });
	    }
	}


