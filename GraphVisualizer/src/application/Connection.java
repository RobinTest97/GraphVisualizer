package application;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Shape;

/**
 * 
 * @author robinkopitz
 *
 */

abstract public class Connection {
	protected GraphNode start = null;
	protected GraphNode end = null;
	protected int cost = 0;
	protected FlowPane costPane = new FlowPane();
	protected Label costLabel = new Label();
	
	public abstract void setConnectionCost();
	
	public abstract int getCost();
	public abstract Shape getConnection();
	public abstract FlowPane getCostPane();
	public abstract Label getCostLabel();
	public abstract GraphNode getStartNode();
	public abstract GraphNode getEndNode();
	
	public abstract void removeElement(ArrayList<Connection> createdConnections);
	public abstract void setNewNodeConnectionState();
	
}
