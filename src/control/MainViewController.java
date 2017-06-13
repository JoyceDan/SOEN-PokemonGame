package control;


import javax.swing.*;

import view.BaseWindow;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController {


	MainView mainViewMenu;
    
    /**
     * Creates listeners for options in the main menu of the game.
     */
    public MainViewController() {
        mainViewMenu = new MainView();
        

        
        /**
         * Sets listener for starting the game view.
         */
        mainViewMenu.startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               mainViewMenu.setVisible(false);
//               new BaseWindow();
              new GameEngine();
               }
        });
    }
}
