package application;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * 
 * @author robinkopitz
 *
 * Class to save all the GrahpNode informations
 * 	Coordinates,Shape, radius, name, id, next and prev nodes and connections
 */

public class GraphNode {
	
	private double coordX = 0;
	private double coordY = 0;
	private double radius = 25;
	private Circle circle = null;
	private FlowPane namePane = new FlowPane();
	private Label nameLabel = new Label();
	private BorderPane root = null;
	//Abholen der einzigartigen Id
	private int nodeID = 0;
	//Name ist standardmaesig die Id
	private String name = String.valueOf(nodeID);
	
	//Knoten Vorgaenger und Nachfolger
	private ArrayList<GraphNode> next = new ArrayList<GraphNode>();
	private ArrayList<GraphNode> prev = new ArrayList<GraphNode>();	
	
	private ArrayList<Connection> outgoingConnections = new ArrayList<Connection>();
	
	public GraphNode(double coordX, double coordY, BorderPane root) {
		this.root = root;
		this.coordX = coordX;
		this.coordY = coordY;
		drawNode(coordX, coordY);
		
	}//Construktor
	
	// draws the node in the user interface
	private void drawNode(double coordX, double coordY) {
        circle = new Circle();
        circle.setRadius(radius);
        circle.setCenterX(coordX);
        circle.setCenterY(coordY);
        circle.setFill(Color.rgb(220, 220, 220, 0.7));
        circle.setSmooth(true);
        root.getChildren().add(circle);
        setNamePaneAndLabel(root);
	}//drawCircle
	
	private void setNamePaneAndLabel(BorderPane root) {
		namePane.getChildren().add(nameLabel);
		root.getChildren().add(namePane);
	}//setFlowPaneAndLabel
	
	
/**-------------------------------------------Setter Methoden--------------------------------------------------*/
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNodeId(int id) {
		this.nodeID = id;
	}

	/**
	 * Injected the already created Label and set the entered Text
	 * @if no text is entered the old name will be set
	 */
	public void setNodeNameLabel() {
		nameLabel.setText(name);
		String title = Constants.TEXT_INPUT_NODE_NAME;
		String message = Constants.MESSAGE_INPUT_NODE_NAME;
		Basis.askForInput(nameLabel, message, title);

		name = nameLabel.getText();
		namePane.setTranslateX(coordX);
		namePane.setTranslateY(coordY + 35);
		namePane.setAlignment(Pos.CENTER);
	}

/**-------------------------------------------Ende Setter Methoden---------------------------------------------*/
	
/**-------------------------------------------Getter Methoden--------------------------------------------------*/
	public String getName() {
		return name;
	}
	
	public int getNodeID() {
		return nodeID;
	}
	
	public ArrayList<GraphNode> getNext() {
		return next;
	}

	public ArrayList<GraphNode> getPrev() {
		return prev;
	}
	
	public ArrayList<Connection> getOutgoingConnections() {
		return outgoingConnections;
	}
	
	public double getCoordX() {
		return coordX;
	}
	
	public double getCoordY() {
		return coordY;
	}
	
	public Circle getCircle() {
		return circle;
	}
	
	public FlowPane getNodeNamePane() {
		return namePane;
	}
	
	public Label getNodeNameLabel() {
		return nameLabel;
	}
	
/**-------------------------------------------Ende Getter Methoden---------------------------------------------*/

	/**
	 * @param createdNodes
	 * Removes the node from all saved spots to prepare the it for the
	 * garbage colletor
	 */
	public void removeElement(ArrayList<GraphNode> createdNodes) {
			root.getChildren().remove(namePane);
			root.getChildren().remove(circle);
			createdNodes.remove(this);
	}//removeElement

}
