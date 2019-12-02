package com.drawing.console.command;

import java.util.Arrays;

import com.drawing.console.shape.Shape;

/**
 * Basic shape command class
 * It stores other sub commands. 
 * @author mani
 *
 */
public abstract class DrawBasicShape  implements DrawShape {

 	private String message; 
	private char[][] screen=null; 
	private Shape model = null;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public char[][] getScreen() {
		return screen;
	}
	public void setScreen(char[][] screen) {
		this.screen = screen;
	}
	public Shape getModel() {
		return model;
	}
	public void setModel(Shape basicShape) {
		this.model = basicShape;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + Arrays.deepHashCode(screen);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DrawBasicShape other = (DrawBasicShape) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (!Arrays.deepEquals(screen, other.screen))
			return false;
		return true;
	}  
	 
}
