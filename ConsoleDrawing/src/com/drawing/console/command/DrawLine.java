package com.drawing.console.command;

import com.drawing.console.shape.Canvas;
import com.drawing.console.shape.Line;

public class DrawLine extends DrawBasicShape{

	@Override
	public void execute() {
		
		
 		char screen[][] = this.getScreen();  //screen is shared across all models.
 		Line model = (Line) this.getModel();
		int x1  = model.getStartingPoint().getX();
		int y1  = model.getStartingPoint().getY();
		int x2 = model.getEndingPoint().getX();
		int y2 = model.getEndingPoint().getY();
		
		if (!isStraightLine(x1,y1, x2, y2)){
			System.out.println("Error: Line coordinates should be vertical or horizontal straight line.");
			return;
		}   		 
	}

	private boolean isStraightLine(int x1, int y1, int x2, int y2) { 
		  return  x1 == x2 || y1 == y2;
	} 
	
}
