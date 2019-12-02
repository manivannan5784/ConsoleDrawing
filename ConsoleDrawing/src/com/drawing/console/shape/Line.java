package com.drawing.console.shape;

/**
 * Line coordinates
 * 
 * @author Manivannan
 *
 */
public class Line extends Shape {

	private Point startingPoint;
	private Point endingPoint;

	public Point getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(Point startingPoint) {
		this.startingPoint = startingPoint;
	}

	public Point getEndingPoint() {
		return endingPoint;
	}

	public void setEndingPoint(Point endingPoint) {
		this.endingPoint = endingPoint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endingPoint == null) ? 0 : endingPoint.hashCode());
		result = prime * result + ((startingPoint == null) ? 0 : startingPoint.hashCode());
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
		Line other = (Line) obj;
		if (endingPoint == null) {
			if (other.endingPoint != null)
				return false;
		} else if (!endingPoint.equals(other.endingPoint))
			return false;
		if (startingPoint == null) {
			if (other.startingPoint != null)
				return false;
		} else if (!startingPoint.equals(other.startingPoint))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Line [startingPoint=" + startingPoint + ", endingPoint=" + endingPoint + "]";
	}

}
