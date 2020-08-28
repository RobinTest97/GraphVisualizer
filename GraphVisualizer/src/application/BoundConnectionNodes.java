package application;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Shape;

/**
 * 
 * @author robinkopitz
 *
 */
public class BoundConnectionNodes extends Connection {
	
	private Shape connection = null;
	private BorderPane root = null;
	
	public BoundConnectionNodes(double startX, double startY, double endX, double endY, BorderPane root
			, GraphNode start, GraphNode end) {
		this.root = root;
		this.start = start;
		this.end = end;
		createArrowConnection(startX, startY, endX, endY);
		setCostPaneAndLabel(root);
	}// expanded Constructor

	private void createArrowConnection(double startX, double startY, double endX, double endY) {
		if(startX != endX && startY != endY)
			connection = new Arrow(startX, startY, endX, endY);
		else
			connection = new GraphLoop(startX, startY);	
		connection.setSmooth(true);
		connection.setStrokeWidth(3);
		root.getChildren().add(connection);
	}// common constructor
	
	
/*-------------------------------------------Setter Methods----------------------------------------------*/
	private void setCostPaneAndLabel(BorderPane root) {
		costPane.getChildren().add(costLabel);
		root.getChildren().add(costPane);
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	/**
	 * Set Connection Cost via Input Box 
	 * 	@if Input is empty Old Cost will be set
	 * 	@if Input is not nummeric Error message appears
	 */
	public void setConnectionCost() {
		
		String title = Constants.TEXT_INPUT_COST;
		String message = Constants.MESSAGE_INPUT_COST;
		Basis.askForInput(costLabel, message, title);

		String strKosten = costLabel.getText();
		
		if(Basis.isNummeric(strKosten)) {
			cost = (Integer.parseInt(strKosten));
		}else {
			Basis.errorBox(Constants.MESSAGE_ERROR_NO_CHANGES, Constants.TEXT_ERROR);
			costLabel.setText(String.valueOf(cost));
		}
		
		
		double coordY = 0;
		double coordX = 0;
		
		if(start.equals(end)) {
			coordX = start.getCoordX()+45;
			coordY = start.getCoordY()-60;
		}
		else {
			coordY = (start.getCoordY() + end.getCoordY())/2-15;
			coordX = (start.getCoordX() + end.getCoordX())/2+15;
		}
		
		costPane.setTranslateX(coordX);
		costPane.setTranslateY(coordY);
		costPane.setAlignment(Pos.CENTER);
	}//setConnectionCost
	
	/**
	 * Sets the connection Informations on the specific nodes
	 */
	public void setNewNodeConnectionState() {

		ArrayList<GraphNode> prev = new ArrayList<GraphNode>();
		ArrayList<GraphNode> next = new ArrayList<GraphNode>();

		next = start.getNext();
		prev = end.getPrev();

		next.add(end);
		prev.add(start);
	}//setNewNodeConnectionState
/*-------------------------------------------End Setter Methods-------------------------------------------*/
	
/*-------------------------------------------Getter Methods-----------------------------------------------*/	
	public Shape getConnection() {
		return connection;
	}
	
	public GraphNode getStartNode() {
		return start;
	}
	
	public GraphNode getEndNode() {
		return end;
	}
	
	public int getCost() {
		return cost;
	}
	
	public FlowPane getCostPane() {
		return costPane;
	}
	
	public Label getCostLabel() {
		return costLabel;
	}
/*-------------------------------------------End Getter Methods------------------------------------------*/	
	
	/**
	 * Remove Elements from the root Pane
	 */
	public void removeElement(ArrayList<Connection> createdConnections) {
			createdConnections.remove(this);
			root.getChildren().remove(costPane);
			root.getChildren().remove(connection);
			resetGrahpNodeInformations(start, end);
	}//removeElement
	
	/**
	 * 
	 * @param startNode
	 * @param endNode
	 * @param task
	 * 
	 * Reset Start and End Node Informations from the Connections
	 */
	private void resetGrahpNodeInformations(GraphNode startNode, GraphNode endNode) {
		ArrayList<GraphNode> prev = new ArrayList<GraphNode>();
		ArrayList<GraphNode> next = new ArrayList<GraphNode>();

		// Remove startNode Informations
		next = startNode.getNext();
		next.remove(endNode);
		startNode.getOutgoingConnections().remove(this);


		// Remove endNode Informations
		prev = endNode.getPrev();
		prev.remove(startNode);
	}//resetGraphNodeInfromations
	
}