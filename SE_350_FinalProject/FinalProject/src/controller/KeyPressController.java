package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.AdventurePanel;
import model.Chip;
import model.GameGrid;

public class KeyPressController implements KeyListener{

	AdventurePanel panel;
	ChipsAdventure game;
	GameGrid grid;
	Chip chip;
	public KeyPressController(AdventurePanel adventurePanel, GameGrid grid, Chip chip){ 	
		this.chip = chip;
		this.grid = grid;
		this.panel = adventurePanel;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyPressed = e.getKeyCode();
		if (keyPressed < 41 && keyPressed > 36 ){
			switch (keyPressed){
			case 37: // Go west
			   if(chip.getX()>0 && !grid.isBlocked(chip.getX()-1,chip.getY()))
				   chip.setX(chip.getX()-1);
			break;
			case 38: // Go North
				if(chip.getY()>0 && !grid.isBlocked(chip.getX(),chip.getY()-1))
					chip.setY(chip.getY()-1);
			break;
			case 39: // Go East
				if(chip.getX()<grid.getXDimension()-1 && !grid.isBlocked(chip.getX()+1,chip.getY()))
			        chip.setX(chip.getX()+1);
			   break;
			case 40: // Go South{
				if(chip.getY()<grid.getYDimension()-1 && !grid.isBlocked(chip.getX(),chip.getY()+1))
				    chip.setY(chip.getY()+1);	
			   break;
			}
		}
		if(keyPressed == 82){
			chip.game.reset();
		}
		if(keyPressed == 27){
			chip.game.close();
		}
		panel.repaint();
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {  
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
