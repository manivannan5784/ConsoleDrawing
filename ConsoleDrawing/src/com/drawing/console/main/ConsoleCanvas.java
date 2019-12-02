package com.drawing.console.main;

import java.util.Scanner;

import com.drawing.console.command.ConsoleDrawingCommandFactory;
import com.drawing.console.command.ConsoleDrawingCommandProcessor;
import com.drawing.console.command.DrawBasicShape;
import com.drawing.console.command.DrawUndefinedShape;
import com.drawing.console.command.QuitConsole;

/**
 * A console based Canvas.
 * 
 * @author Manivannan
 *
 */
public class ConsoleCanvas {


	public static void main(String[] args) {

		Scanner consoleInputStream=null;

		try {
			String[] userInputAsArgs = getNextUserInputAsArgs(consoleInputStream);
			DrawBasicShape drawAshape = ConsoleDrawingCommandFactory.getInstance().getCommand(userInputAsArgs);
			ConsoleDrawingCommandProcessor processor = new ConsoleDrawingCommandProcessor();

			while (!isQuitCommand(drawAshape)) { 
				
				processor.processCommand(drawAshape);
 
				userInputAsArgs = getNextUserInputAsArgs(consoleInputStream);
				drawAshape = ConsoleDrawingCommandFactory.getInstance().getCommand(userInputAsArgs);
			}
		} finally {
			if (consoleInputStream != null)
				consoleInputStream.close();
		}
	}
 
	private static boolean isQuitCommand(DrawBasicShape drawAShapeCmd) {
		return drawAShapeCmd instanceof QuitConsole;
	}

	public static String[] getNextUserInputAsArgs(Scanner consoleInputStream) {
		System.out.println("\n_____________\nEnter next command:");
		consoleInputStream = new Scanner(System.in);
		String userInput = consoleInputStream.nextLine();
		return tokenizeUserInput(userInput);
	}

	private static String[] tokenizeUserInput(String input) {
		if (input == null)
			return null; 
		return input.split("\\s+");
	}
}
