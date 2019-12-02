package com.drawing.console.shape;
/**
 * Rectangle shape representation.
 * @author Manivannan
 *
 */
public class Rectangle extends Shape{

	private Point upperLeftPoint;
	private Point lowerRightPoint;

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public Point getLowerRightPoint() {
		return lowerRightPoint;
	}

	public void setLowerRightPoint(Point lowerRightPoint) {
		this.lowerRightPoint = lowerRightPoint;
	}

	@Override
	public String toString() {
		return "Rectangle [upperLeftPoint=" + upperLeftPoint + ", lowerRightPoint=" + lowerRightPoint + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lowerRightPoint == null) ? 0 : lowerRightPoint.hashCode());
		result = prime * result + ((upperLeftPoint == null) ? 0 : upperLeftPoint.hashCode());
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
		Rectangle other = (Rectangle) obj;
		if (lowerRightPoint == null) {
			if (other.lowerRightPoint != null)
				return false;
		} else if (!lowerRightPoint.equals(other.lowerRightPoint))
			return false;
		if (upperLeftPoint == null) {
			if (other.upperLeftPoint != null)
				return false;
		} else if (!upperLeftPoint.equals(other.upperLeftPoint))
			return false;
		return true;
	}

}
