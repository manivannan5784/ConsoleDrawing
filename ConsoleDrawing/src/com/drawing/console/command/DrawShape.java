package com.drawing.console.command;

public interface DrawShape {

	public static final char X_AXIS_DRAW_SYMBOL = '-';
	public static final char Y_AXIS_DRAW_SYMBOL = '|';
	public static final char EMPTY_SCREEN_FILL_CHAR = ' ';


	public void execute();
}
