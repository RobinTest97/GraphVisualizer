package application;

import javafx.stage.Stage;

/**
 * 
 * @author robinkopitz
 * Stage Changer to change the designed Stages
 */
public class StageChanger {
	public static Stage mainStage = null;
	public static Stage graphPannelStage = null;
	
	public static void changeStageFromPannelToMain() {
		//Reset GraphInformations
		GraphInformations.createdConnections.clear();
		GraphInformations.createdNodes.clear();
		
		graphPannelStage.hide();
		mainStage.show();
	}//changeStageFromPannelToMain
	
	public static void hideMainStage() {
		mainStage.hide();
	}//hideMainStage
	
	public static void showMainStage() {
		mainStage.show();
	}//showMainStage
}
