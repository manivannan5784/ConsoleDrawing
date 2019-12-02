package com.drawing.console.command;

 
import com.drawing.console.shape.Canvas;

public class DrawCanvas extends DrawBasicShape{

	  
 
	@Override
	public void execute() {
		
 		char screen[][] = this.getScreen();  //screen is shared across all models.
 		Canvas model = (Canvas) this.getModel();
		int width  = model.getHwDimension().getX();
		int hieght =model.getHwDimension().getY();
 
		for (int i = 0; i < width ; i++) {
			screen[0][i] = DrawShape.X_AXIS_DRAW_SYMBOL;	
			screen[hieght-1][i] = DrawShape.X_AXIS_DRAW_SYMBOL;
		}
	
		for (int i = 1; i < hieght-1 ; i++) {
			screen[i][0] = DrawShape.Y_AXIS_DRAW_SYMBOL;	
			screen[i][width-1] = DrawShape.Y_AXIS_DRAW_SYMBOL;
		}
  	 		 
	}
 }
