package com.drawing.console.command;

import com.drawing.console.shape.Canvas;
import com.drawing.console.shape.Line;
import com.drawing.console.shape.Point;

/**
 * 
 * @author mani
 *
 */
public class ConsoleDrawingCommandBuilder {

	private static final ConsoleDrawingCommandBuilder instance = new ConsoleDrawingCommandBuilder();
 
	private ConsoleDrawingCommandBuilder() {
	}

	public static ConsoleDrawingCommandBuilder getInstance() {
		return instance;
	}

	public DrawBasicShape buildDrawCanvasCommand(String[] args) {
		Canvas canvas = new Canvas();
		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		Point hwDimension = new Point();
		hwDimension.setX(width);
		hwDimension.setY(height);
		canvas.setHwDimension(hwDimension);
		
		DrawBasicShape drawCanvasCommand = new DrawCanvas();
		drawCanvasCommand.setModel(canvas);
		return drawCanvasCommand;
  	}

	public DrawBasicShape buildDrawLineCommand(String[] args) {
		Line line = new Line(); 
		int x1 = Integer.parseInt(args[1]);
		int y1 = Integer.parseInt(args[2]);
		int x2 = Integer.parseInt(args[3]);
		int y2 = Integer.parseInt(args[4]);
		Point startingPoint = new Point();
		Point endingPoint = new Point();
		startingPoint.setX(x1);
		startingPoint.setY(y1);
		endingPoint.setX(x2);
		endingPoint.setX(y2);
		line.setStartingPoint(startingPoint);
		line.setEndingPoint(endingPoint); 	
		
		DrawBasicShape drawLineCmd = new DrawLine();
		drawLineCmd.setModel(line); 
		return drawLineCmd;
	}   

}
