package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 * 
 * @author robinkopitz
 *
 */
public class ImportGraph {
	
	BorderPane root = null;

	
	public ImportGraph(BorderPane root, File file) {
		this.root = root;
		readData(file);
	}//Constructor
	
	/**
	 * @param file
	 * Read the Lines from the chosen file and saves them as a block
	 */
	public void readData(File file) {
		
		if (!file.canRead() || !file.isFile())
            return;

        BufferedReader reader = null;
        
        try {
        	
        	reader = new BufferedReader(new FileReader(file));
            String block = null;
            String line = null;
            while ((line = reader.readLine()) != null) {
            	block += line+"\n";
            }
            
    		String arr[] = block.split("-------------------------------------------------------------");
    		createNodeInformations(arr);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        
        } 
	}//readData

	private void createNodeInformations(String[] data) {

		String settingsConnections = null;
		ArrayList<Integer> connections = new ArrayList<Integer>();
		
		for(String node: data) {
			String nodeName = null;
			String strNodeId = null;
			String strCoordX = null;
			String strCoordY = null;
			
			if(node.contains("Knotenname")) {
				String arrLine[] = node.split("\n");
				for(String text: arrLine) {
					if(!text.isEmpty()){					
						if(text.contains("Knotenname: ") && !text.contains("|")) {
							String arr[] = text.split(": ");
							nodeName = arr[1];
						}
						else if(text.contains("KnotenID:")) {
							String arr[] = text.split(": ");
							strNodeId = arr[1];
						}
						else if(text.contains("Koordinate X: ")) {
							String arr[] = text.split(": ");
							strCoordX = arr[1];
						}
						else if(text.contains("Koordinate Y: ")) {
							String arr[] = text.split(": ");
							strCoordY = arr[1];
						}
						else if(text.contains("unbound Verbindung: ") || text.contains("bound Verbindung")) {
							String arr[] = text.split("Verbindung: ");
							settingsConnections = arr[0].trim();
							String conInfo[] = arr[1].split(" | ");
							for(String conInfoStr : conInfo) {
								if(conInfoStr.contains("|") || conInfoStr.contains("Kosten:") 
										|| conInfoStr.contains("endKnotenId:")
										|| conInfoStr.contains("startKnotenId:"));
								else {
									if(Basis.isNummeric(conInfoStr))
										connections.add(Integer.parseInt(conInfoStr));
									else
										Basis.errorBox(Constants.MESSAGE_ERROR_IS_NUMERIC, Constants.TEXT_ERROR);
								}
	
							}
						}
					}
				}
				if(Basis.isNummeric(strCoordX) && Basis.isNummeric(strCoordY) && Basis.isNummeric(strNodeId)) {
					double coordX = Double.parseDouble(strCoordX);
					double coordY = Double.parseDouble(strCoordY);
					int nodeId = Integer.parseInt(strNodeId);
					GraphNode newNode = new GraphNode(coordX, coordY, root);
					setNodeName(newNode, nodeName);
					newNode.setNodeId(nodeId);
					GraphInformations.createdNodes.add(newNode);
				}
				
			}
		}
		
		
		establishConnections(connections, settingsConnections);
	}
	
	private void setNodeName(GraphNode node, String knotenname) {

		Label label = node.getNodeNameLabel();
		label.setText(knotenname);
		FlowPane pane = node.getNodeNamePane();

		pane.setTranslateX(node.getCoordX());
		pane.setTranslateY(node.getCoordY() + 35);
		pane.setAlignment(Pos.CENTER);
		
		//GraphNode Information Update
		node.setName(label.getText());
	}
	
	private void establishConnections(ArrayList<Integer> connections, String settingsConnections) {
		GraphNode startNode = null;
		GraphNode endNode = null;
		
		//Hier wird eine Counter Variable genutzt, die nach jeder Verbindung korregiert wird,
		//um die Verbindungen korrekt zu etablieren, da sonst eine differenzierung über Modulo nicht möglich wäre
		int counter = 1;
		for(int id = 0; id < connections.size(); id++) {
			if((counter)%3 == 0) {
				int cost = connections.get(id);
				
				if(settingsConnections.contentEquals("bound")) {	
					BoundConnectionNodes connection = new BoundConnectionNodes(
							startNode.getCoordX(), startNode.getCoordY()
							,endNode.getCoordX(), endNode.getCoordY(), root
							,startNode, endNode);
					connection.setCost(cost);
					setConnectionCost(connection, settingsConnections, cost);
					connection.setNewNodeConnectionState();
					GraphPannelSettings.settingConnection = Constants.CONNECTION_BOUND;
					GraphInformations.createdConnections.add(connection);
				}
				else if(settingsConnections.contentEquals("unbound")) {
					UnboundConnectionNodes connection = new UnboundConnectionNodes(
							startNode.getCoordX(), startNode.getCoordY()
							,endNode.getCoordX(), endNode.getCoordY(), root
							,startNode, endNode);
					connection.setCost(cost);
					connection.setNewNodeConnectionState();
					setConnectionCost(connection, settingsConnections, cost);
					GraphPannelSettings.settingConnection = Constants.CONNECTION_UNBOUND;
					GraphInformations.createdConnections.add(connection);
				}else{
					Basis.errorBox(Constants.MESSAGE_ERROR_UNKNOWN_TASK, Constants.TEXT_ERROR);
					return;
				}
				counter = 1;
			}
			else if((counter) % 2 == 0) {
				for(GraphNode checkNode: GraphInformations.createdNodes) {
					if(checkNode.getNodeID() == connections.get(id))
						endNode = checkNode;
				}
				counter++;
			}
			else {
				for(GraphNode checkNode: GraphInformations.createdNodes) {
					if(checkNode.getNodeID() == connections.get(id))
						startNode = checkNode;
				}
				counter++;
			}

		}
	}

	/**
	 * 
	 * @param connection
	 * @param settingsConnections
	 * @param cost
	 * 
	 * We cannot use the already created function for setConnectionCost because we dont want
	 * to use a input field inside the import feature
	 */
	private void setConnectionCost(Connection connection, String settingsConnections, int cost) {
		Label label = null;
		GraphNode startNode = null;
		GraphNode endNode = null;
		FlowPane pane = null;
		
		label = connection.getCostLabel();
		pane = connection.getCostPane();
		startNode = connection.getStartNode();
		endNode = connection.getEndNode();
		
		//Outgoing Connections fÃ¼r den nÃ¤chsten Export setzen
		startNode.getOutgoingConnections().add(connection);
		
		label.setText(String.valueOf(cost));
		
		double coordY = 0;
		double coordX = 0;
		
		if(startNode.equals(endNode)) {
			coordX = startNode.getCoordX()+45;
			coordY = startNode.getCoordY()-60;
		}
		else {
			coordY = (startNode.getCoordY() + endNode.getCoordY())/2-15;
			coordX = (startNode.getCoordX() + endNode.getCoordX())/2+15;
		}
		
		pane.setTranslateX(coordX);
		pane.setTranslateY(coordY);
		pane.setAlignment(Pos.CENTER);
	}//setConnectionCost
}
