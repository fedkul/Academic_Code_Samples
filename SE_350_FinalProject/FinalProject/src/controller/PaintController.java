package controller;

import view.AdventurePanel;

public class PaintController implements Runnable{

	AdventurePanel pap;
	public PaintController(AdventurePanel panel){
		this.pap = panel;
	}
	@Override
	public void run() {
		while (true){
			pap.repaint();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
