package application;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 * 
 * @author robinkopitz
 *
 */
public class UnboundConnectionNodes extends Connection {
	
	private Shape connection = null;
	private BorderPane root = null;
	
	public UnboundConnectionNodes(double startX, double startY, double endX, double endY, BorderPane root, GraphNode start, GraphNode end) {
		this.root = root;
		this.start = start;
		this.end = end;
		createLineConnection(startX, startY, endX, endY);
		setCostPaneAndLabel(root);	
	}//Constructor

	/**
	 * 
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * Create the undirected Line connection to an other node
	 */
	private void createLineConnection(double startX, double startY, double endX, double endY) {
		if(startX != endX && startY != endY)
			connection = new Line(startX, startY, endX, endY);
		else
			connection = new GraphLoop(startX, startY);	
		connection.setSmooth(true);
		connection.setStrokeWidth(3);
		connection.setStroke(Color.rgb(0, 0, 0, 0.3));
		root.getChildren().add(connection);		
	}//createLineConnection
	
/*-----------------------------------------------Setter Methods------------------------------------------*/
	private void setCostPaneAndLabel(BorderPane root) {
		costPane.getChildren().add(costLabel);
		root.getChildren().add(costPane);
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
		
	public void setConnectionCost() {
		
		String title = "Verbindungskosten";
		String message = "Geben Sie bitte die Kosten ein:";
		Basis.askForInput(costLabel, message, title);

		String strKosten = costLabel.getText();
		
		if(Basis.isNummeric(strKosten)) {
			cost = (Integer.parseInt(strKosten));
		}else {
			costLabel.setText(String.valueOf(cost));
		}
		
		
		double coordY = 0;
		double coordX = 0;
		
		if(start.equals(end)) {
			coordX = start.getCoordX()+40;
			coordY = start.getCoordY()-60;
		}
		else {
			coordY = (start.getCoordY() + end.getCoordY())/2-15;
			coordX = (start.getCoordX() + end.getCoordX())/2+15;
		}
		costPane.setTranslateX(coordX);
		costPane.setTranslateY(coordY);
		costPane.setAlignment(Pos.CENTER);
	}
	
	/**
	 * Sets the new established Connection Information to the nodes
	 */
	public void setNewNodeConnectionState() {

		ArrayList<GraphNode> prev = new ArrayList<GraphNode>();
		ArrayList<GraphNode> next = new ArrayList<GraphNode>();
		
		next = start.getNext();
		prev = start.getPrev();
		next.add(end);
		prev.add(end);

		next = end.getNext();
		prev = end.getPrev();
		next.add(start);
		prev.add(start);

	}
/*-----------------------------------------End Setter Methods--------------------------------------------*/

/*-----------------------------------------Getter Methods------------------------------------------------*/	
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
/*-----------------------------------------End Getter Methods--------------------------------------------*/
	
	
	/**
	 * Removes the established visuell connection from the stage
	 */
	public void removeElement(ArrayList<Connection> createdConnections) {
			createdConnections.remove(this);
			root.getChildren().remove(costPane);
			root.getChildren().remove(connection);
			resetGrahpNodeInformations(start, end, Constants.CONNECTION_BOUND);
	}//removeElement

	//Removes the connection informations of the nodes
	private void resetGrahpNodeInformations(GraphNode startNode, GraphNode endNode, String task) {
		ArrayList<GraphNode> prev = new ArrayList<GraphNode>();
		ArrayList<GraphNode> next = new ArrayList<GraphNode>();

		// Remove startNode Informations
		next = startNode.getNext();
		next.remove(endNode);

		// Remove endNode Informations
		next = endNode.getNext();
		prev = endNode.getPrev();
		prev.remove(startNode);
		next.remove(startNode);
	}//resetGraphNodeInformations
}
