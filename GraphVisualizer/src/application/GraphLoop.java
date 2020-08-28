package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

/**
 * 
 * @author robinkopitz
 *	Shape for the loop to the node
 */

public class GraphLoop extends Arc{
	
	public GraphLoop(double startX, double startY) {
		super();
		setCenterX(startX+20);
		setCenterY(startY-20);
		setRadiusX(20);
		setRadiusY(20);
		setStartAngle(-75);
		setLength(240);
		setType(ArcType.OPEN);
		setFill(Color.TRANSPARENT);
		setStroke(Color.rgb(0, 0, 0, 0.4));
	}	
}

