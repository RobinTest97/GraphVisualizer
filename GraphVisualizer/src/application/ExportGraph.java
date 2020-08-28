package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author robinkopitz
 *
 */
public class ExportGraph{

	private int[][] adjMatrix;
	String strExport="";
	
	public ExportGraph(File file) {
		adjMatrix = new int[GraphInformations.createdNodes.size()][GraphInformations.createdNodes.size()];
		
		if(GraphPannelSettings.settingExport.contentEquals(Constants.ADDITIONAL_EXPORT))
			addAdditionalNodeInformations();
		prepareAdjMatrix();
		writeToFile(strExport, file);
	}//Constructor ExportGraph
	
	/**
	 * Additional Informations for Import Feature
	 * Saves: Node Coordinates ArrayListen and all other Informations about the connections and so
	 * on to the TXT file
	 */
	private void addAdditionalNodeInformations() {
		strExport +="Zusaetzliche Node Informationen: \n\n";
		
		for(GraphNode node: GraphInformations.createdNodes) {
			strExport +="-------------------------------------------------------------\n";
			strExport +="Knotenname: "+node.getName()+"\n";
			strExport +="KnotenID: "+node.getNodeID()+"\n";
			strExport +="Koordinate X: "+node.getCoordX()+"\n";
			strExport +="Koordinate Y: "+node.getCoordY()+"\n";
			strExport +="Nachfolger Knoten: \n";
			if(node.getNext().size() != 0) {
				for(GraphNode next: node.getNext())
					strExport +="	Knotenname: "+next.getName() + " | Id: "+next.getNodeID()+"\n";
				strExport +="\n";
			}
			else
				strExport +="	Keine Nachfolger gefunden!\n"; 
			strExport += ("\nVorgaenger Knoten:\n");
			if(node.getPrev().size() != 0) {
				for(GraphNode prev: node.getPrev())
					strExport +="	Nachfolgende Knotenname: "+prev.getName() + " | Id: "+prev.getNodeID();
				strExport +="\n";
			}
			else
				strExport +="	Keine Vorgaenger gefunden!\n";
			strExport +="\n";
			strExport +="Ausgehende Verbindungen: \n";
			ArrayList<Connection> connections = node.getOutgoingConnections();
			for(Connection connection : connections) {
					strExport += " "+ GraphPannelSettings.settingConnection +" Verbindung: startKnotenId: "+ connection.getStartNode().getNodeID()
							+" | endKnotenId: "+ connection.getEndNode().getNodeID()
					+" | Kosten: "+ connection.getCost()+"\n";
;
			}
			if(connections.size() == 0)
				strExport +="	Keine ausgehenden Verbindungen gefunden!\n";
			
				
			
			strExport +="-------------------------------------------------------------\n\n";
		}		
	}//Additional Informations
	
	/**
	 * Creates the adjacency Matrix of the created Graph
	 */
	private void prepareAdjMatrix() {
		strExport +="Adjazenz Matrix: \n\n";
		for(GraphNode node: GraphInformations.createdNodes) {
			int matrixPlaceX = node.getNodeID()-1;
			
			ArrayList<GraphNode> prevs = node.getPrev();
			
			for(GraphNode prev: prevs) {
				int matrixPlaceY = prev.getNodeID()-1;
				adjMatrix[matrixPlaceY][matrixPlaceX] = 1;	
			}
		
			matrixPlaceX++;
		}
		
		for(int row = 0; row < adjMatrix.length; row++) {
			for(int col = 0; col < adjMatrix.length; col++) {
				strExport+=adjMatrix[row][col]+" ";
			}
			strExport+="\n";
		}
	}//prepareAdjMatrix
	
	
	
	/**
	 * 
	 * @param informations
	 * @param file - contain the file path
	 * @return void but created a text file with a save dialog
	 */
	private void writeToFile(String informations, File file) {
		
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			
			bufferedWriter.write(informations);
			
			bufferedWriter.close();
			Basis.infoBox(Constants.MESSAGE_INFO_EXPORT, Constants.TEXT_INFORMATION);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//writeToFile
}
