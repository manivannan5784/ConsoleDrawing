package com.drawing.console.command;

public class QuitConsole extends DrawBasicShape{

	@Override
	public void execute() {
		System.out.println(this.getMessage());
	}

}
