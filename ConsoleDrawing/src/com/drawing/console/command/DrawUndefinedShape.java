package com.drawing.console.command;
/**
 * Inherits basic methods.
 * @author mani
 *
 */
public class DrawUndefinedShape extends DrawBasicShape{

	@Override
	public void execute() { 
		System.out.println(this.getMessage());
	}

	 
}
