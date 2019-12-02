package com.drawing.console.command;

import java.util.ArrayList;
import java.util.List;

import com.drawing.console.messages.en.Messages;

/**
 * Singleton, factory pattern.
 * 
 * @author Manivannan
 *
 */
public class ConsoleDrawingCommandFactory {

	private static final String LINE_EXCEEDS_CANVAS_ERROR = "Error: Line coordinates exceed Canvas width or height";
	private static final String CANVAS_ALREADY_SET_ERROR = "Error: Canvas has been already created, multi canvas not supported. Refer manual for help.\n";
	private static final String CANVAS_NOT_SET_ERROR = "Error: Canvas should be created first. Refer manual for help.\n";
	public static final String RAISE_CANVAS_NOT_SET_ERROR_CMD = "RAISE_CANVAS_NOT_SET_ERROR_CMD";
	public static final String RAISE_CANVAS_ALREADY_SET_ERROR_CMD = "RAISE_CANVAS_ALREADY_SET_ERROR_CMD";


	public static final String CREATE_CANVAS_CMD = "C";
	public static final String CREATE_LINE_CMD = "L";
	public static final String CREATE_RECTANGLE_CMD = "R";
	public static final String CREATE_BUCKET_FILL_CMD = "B";
	public static final String CREATE_QUIT_CMD = "Q";

	private static final int CREATE_LINE_ARG_SIZE = 5;
	private static final int CREATE_CANVAS_ARG_SIZE = 3;
	private static final int CREATE_RECTANGLE_ARG_SIZE = 5;
	private static final int CREATE_BUCKET_FILL_ARG_SIZE = 4;
	public static final int MAX_ARG_SIZE = 6;
	public static List<String> supportedCommandList = null; 




	private static final ConsoleDrawingCommandFactory instance = new ConsoleDrawingCommandFactory();
	public static final int CREATE_QUIT_CMD_ARG_SIZE = 1;
	public static final String RAISE_LINE_EXCEEDS_CANVAS_ERROR_CMD = "RAISE_LINE_EXCEEDS_CANVAS_ERROR_CMD";

	private ConsoleDrawingCommandFactory() {
	}

	public static ConsoleDrawingCommandFactory getInstance() {
		return instance;
	}
	static { 
		supportedCommandList= supportedCommands();
	} 

	public DrawBasicShape getCommand(String[] args) {
		DrawUndefinedShape invalidCommand = createInvalidCommand(args);

		if (invalidCommand!=null)
			return invalidCommand;  //all negative cases are covered in this case. 

		return createDrawShapeCommand(args);
	}

	private DrawBasicShape createDrawShapeCommand(String[] args) {

		ConsoleDrawingCommandBuilder builder =  ConsoleDrawingCommandBuilder.getInstance();

		DrawBasicShape drawShape = null;
		switch(args[0])
		{
		case CREATE_CANVAS_CMD:   
			drawShape = builder.buildDrawCanvasCommand(args);
			break;
		case CREATE_RECTANGLE_CMD:    
			DrawRectAngle drawRectAngle = new DrawRectAngle();
			drawShape= drawRectAngle;
			break;
		case CREATE_LINE_CMD:    
			drawShape = builder.buildDrawLineCommand(args);
			break;

		case CREATE_BUCKET_FILL_CMD:    
			BucketFill bucketFill = new BucketFill();
			//bucketFill.configureParams(args);  
			drawShape= bucketFill;
			break;
		case CREATE_QUIT_CMD:    
			QuitConsole quitConsole = new QuitConsole();
			//quitConsole.configureParams(args); 
			drawShape= quitConsole;
			break;

		}

		return drawShape;
	}

	private DrawUndefinedShape createInvalidCommand(String[] args) {


		DrawUndefinedShape invalidCommand = new DrawUndefinedShape();
		invalidCommand.setMessage(Messages.INVALID_ARGS_REFER_BELOW_HELP_MESSAGE );

		DrawUndefinedShape resultCmd = null;

		if (args == null || args.length == 0) {
			resultCmd= invalidCommand;
		} else if (args.length > MAX_ARG_SIZE) {
			invalidCommand.setMessage(Messages.INVALID_ARGS_REFER_BELOW_HELP_MESSAGE );
			resultCmd= invalidCommand;
		}else if (args[0]!=null && args[0].equals(RAISE_CANVAS_NOT_SET_ERROR_CMD)) { 
			invalidCommand.setMessage(CANVAS_NOT_SET_ERROR ); 
			resultCmd= invalidCommand;
		}
		else if (args[0]!=null && args[0].equals(RAISE_CANVAS_ALREADY_SET_ERROR_CMD)) { 
			invalidCommand.setMessage(CANVAS_ALREADY_SET_ERROR ); 
			resultCmd= invalidCommand;
		}
		else if(args[0]!=null && args[0].equals(RAISE_LINE_EXCEEDS_CANVAS_ERROR_CMD)){
			invalidCommand.setMessage(LINE_EXCEEDS_CANVAS_ERROR); 
			resultCmd= invalidCommand; 
		}
		else if (args[0] != null && args[0].equals(CREATE_CANVAS_CMD)) {
			if (args.length != CREATE_CANVAS_ARG_SIZE)
				resultCmd= invalidCommand; 
			else if (!isValidPositiveNumber(args[1], args[2])) {
				invalidCommand.setMessage(Messages.INVALID_COORDINATES_ERROR );
				resultCmd= invalidCommand;
			}
		} else if (args[0] != null && args[0].equals(CREATE_LINE_CMD)) {
			if (args.length != CREATE_LINE_ARG_SIZE)
				resultCmd= invalidCommand;

			else if (!isValidPositiveNumber(args[1], args[2], args[3], args[4])) {
				invalidCommand.setMessage(Messages.INVALID_COORDINATES_ERROR );
				resultCmd= invalidCommand;
			}
		} else if (args[0] != null && args[0].equals(CREATE_RECTANGLE_CMD)) {
			if (args.length != CREATE_RECTANGLE_ARG_SIZE)
				resultCmd= invalidCommand;

			else if (!isValidPositiveNumber(args[1], args[2], args[3], args[4])) {
				invalidCommand.setMessage(Messages.INVALID_COORDINATES_ERROR );
				resultCmd= invalidCommand;
			}
		} else if (args[0] != null && args[0].equals(CREATE_BUCKET_FILL_CMD)) {
			if (args.length != CREATE_BUCKET_FILL_ARG_SIZE)
				resultCmd= invalidCommand;

			else if (!isValidPositiveNumber(args[1], args[2], args[3])) {
				invalidCommand.setMessage(Messages.INVALID_COORDINATES_ERROR );
				resultCmd= invalidCommand;
			}
		} else if (args[0] != null && args[0].equals(CREATE_QUIT_CMD)) {
			if (args.length != CREATE_QUIT_CMD_ARG_SIZE)
				resultCmd= invalidCommand;
		}
		else if (!isAValidCommand(args)) {
			resultCmd= invalidCommand;

		}

		return resultCmd; //means, no invalid cases.
	}

	private boolean isAValidCommand(String[] args) {
		String command=null;
		if (args!=null && args.length>= 1 && args[0]!=null)
			command = args[0];

		return supportedCommandList.contains(command);
	}

	public static List<String> supportedCommands() { 
		return new ArrayList<String>() {{
			add(CREATE_CANVAS_CMD);
			add(CREATE_LINE_CMD);
			add(CREATE_RECTANGLE_CMD);
			add(CREATE_BUCKET_FILL_CMD);
			add(CREATE_QUIT_CMD);
			add(RAISE_CANVAS_NOT_SET_ERROR_CMD);
			add(RAISE_CANVAS_ALREADY_SET_ERROR_CMD);

		}};
	}

	private boolean isValidPositiveNumber(String... args) {

		int allCoordinatesSum = 0;
		for (String numS : args) {
			try {
				int num = Integer.parseInt(numS);
				if (num < 0)
					return false;
				allCoordinatesSum = allCoordinatesSum + num;
			} catch (Exception e) { // if the string arg is a junk, invalidate
				// the number.
				return false;
			}
		}

		if (allCoordinatesSum==0)   //all coordinates cannot be zero.
			return false; 


		return true;
	}
}
