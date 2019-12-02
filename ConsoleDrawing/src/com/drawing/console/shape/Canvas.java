package com.drawing.console.shape;
 

/**
 * Canvas (container) shape. Convas has dimension coordinates and as well other
 * elements to be painted on the canvas (queued up). The shapes (to be painted
 * on Canvas) are queued up to preserve the order in which they were inserted.
 * 
 * @author Manivannan
 */
public class Canvas extends Shape {

	private Point hwDimension; 
	 
	public Point getHwDimension() {
		return hwDimension;
	}

	public void setHwDimension(Point hwDimension) {
		this.hwDimension = hwDimension;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hwDimension == null) ? 0 : hwDimension.hashCode());
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
		Canvas other = (Canvas) obj;
		if (hwDimension == null) {
			if (other.hwDimension != null)
				return false;
		} else if (!hwDimension.equals(other.hwDimension))
			return false;
		return true;
	} 
	
	
}
