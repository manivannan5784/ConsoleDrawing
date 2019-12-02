package com.drawing.console.command;

import com.drawing.console.shape.Canvas;
import com.drawing.console.shape.Line;

public class ConsoleDrawingCommandProcessor {

	private DrawCanvas drawCanvasCmd = null;
	private char [][] screen = null;
	private int width=0;
	private int hieght=0;

	public void processCommand(DrawBasicShape drawShape){

		boolean validationSuccessful = validateCommandSequence(drawShape);
		if (!validationSuccessful)
			return;

		//adjustment for Canvas drawing alone.
		if ((drawShape instanceof DrawCanvas)) { 
			drawCanvasCmd=(DrawCanvas)drawShape;
			Canvas canvas = (Canvas)drawCanvasCmd.getModel();
			width = canvas.getHwDimension().getX() + 2;
			hieght= canvas.getHwDimension().getY() + 2;
			canvas.getHwDimension().setX(width);  //re-adjust 
			canvas.getHwDimension().setY(hieght);  //re-adjust
			screen = new char[hieght][width];
			initializeScreen(screen);//initialized for nice formatting.
			drawCanvasCmd.setScreen(screen);
			drawCanvasCmd.execute();
			printScreen();
			return;
		} 

		drawShape.setScreen(screen);
		//drawCanvasCmd.getSubCommandList().add(drawShape);
		drawShape.execute();
		printScreen();
	}
  
	private void printScreen(){
		 
		for(char[] arr: screen){  
			System.out.println(arr);  
		}
	}

	private void initializeScreen(char[][] emptyScreen) {
		for(char[] row: emptyScreen) { 
			for(int i=0; i<row.length;i++)
				row[i]=DrawShape.EMPTY_SCREEN_FILL_CHAR;
		}
	}

	public boolean validateCommandSequence(DrawBasicShape drawShape) { 

		boolean validationSuccessful = true;
		if (drawShape instanceof DrawUndefinedShape){
			drawShape.execute();
			validationSuccessful=false;
		} 
		//Canvas should be issued as first command, if not print invalid command sequence error and exit early.
		else if (!isCanvasSet(drawShape) && !(drawShape instanceof DrawCanvas)) { 
			DrawBasicShape invalidCommand=	ConsoleDrawingCommandFactory.getInstance().getCommand(new String[]{ConsoleDrawingCommandFactory.RAISE_CANVAS_NOT_SET_ERROR_CMD});
			invalidCommand.execute(); 
			validationSuccessful=false; 
		}
		//if Canvas already set but canvas command issued again, then print validation error and exit early. 
		else if (isCanvasSet(drawShape) && (drawShape instanceof DrawCanvas)) { 
			DrawBasicShape invalidCommand=	ConsoleDrawingCommandFactory.getInstance().getCommand(new String[]{ConsoleDrawingCommandFactory.RAISE_CANVAS_ALREADY_SET_ERROR_CMD});
			invalidCommand.execute(); 
			validationSuccessful=false;
		}
		else if (drawShape instanceof DrawLine) {
			DrawLine drawline=(DrawLine) drawShape;
			Line line = (Line)drawline.getModel();
			boolean lineXBelowWidth = line.getStartingPoint().getX() <= this.width && line.getEndingPoint().getX() <=this.width;
			boolean lineYBelowHeight = line.getStartingPoint().getY() <= this.hieght && line.getEndingPoint().getY() <=this.hieght;

			if (lineXBelowWidth || lineYBelowHeight){
				DrawBasicShape invalidCommand=	ConsoleDrawingCommandFactory.getInstance().getCommand(new String[]{ConsoleDrawingCommandFactory.RAISE_LINE_EXCEEDS_CANVAS_ERROR_CMD});
				invalidCommand.execute(); 
				validationSuccessful=false;  
			} 

		}
 
		return validationSuccessful;

	}


	private boolean isCanvasSet(DrawBasicShape drawShape) { 
		return drawCanvasCmd!=null;
	}

	public ConsoleDrawingCommandProcessor(){

	}
}
