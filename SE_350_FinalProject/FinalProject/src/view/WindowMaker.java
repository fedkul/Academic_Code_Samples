package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class WindowMaker extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public WindowMaker(AdventurePanel p, ScorePanel sp, LevelPanel lp){
        super("Chip's New Challenge");
        final AdventurePanel panel = p; 
        final ScorePanel scorePanel = sp;
        final LevelPanel levelPanel = lp;
       // final ScorePanel scorePanel
        add(panel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.EAST);
        add(levelPanel, BorderLayout.NORTH);
    }
	public WindowMaker(WinPanel p){
		super("You Win!");
		final WinPanel panel = p;
		add(panel, BorderLayout.CENTER);
	}
//	public WindowMaker(WinPanel p){
//		super("You Win!");
//		final WinPanel panel = p;
//		add(panel, BorderLayout.CENTER);
//	}
}
