package application;
import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Pos;

/**
 * 
 * @author robinkopitz
 *	
 */

public class GraphVisualizer extends Application{
    
	/**
	 * OnStart uses a BorderPane with top, bot and center elements
	 * The stylesheet are implemented on the scene aswell
	 */
     @Override 
     public void start(Stage stage) {
            BorderPane root = new BorderPane(); 
            
            root.setTop( setTopElements() );
            root.setBottom( setBottomElements() );
            root.setCenter( setCenterElements() );
            
            Scene scene= new Scene(root, 1000, 500);
            scene.getStylesheets().addAll(Constants.STYLE_FONT_QUICKSAND
            		, Constants.STYLE_FONT_RALEWAY
            		, Constants.STYLE_FONT_SIGNIKA);
            scene.getStylesheets().add(Constants.STYLESHEET_PATH);
            stage.setTitle(Constants.TEXT_START_STAGE_HEADING);        
            stage.setScene(scene);     
            StageChanger.mainStage = stage;
            stage.show();  
    }//start
    
    /**
     * The center pane uses a gridpane to order the toogle buttons and text elements
     * Text Data and property data are pulled out of the Constant class
     * 
     * @return GridPane
     */
    private Pane setCenterElements() {
    	GridPane gridpane = new GridPane();

    	ToggleGroup creationGroupConnection = new ToggleGroup();
 		ToggleButton radBtnConnectionUnbound = createNewToggleProperty(Constants.TOGGLE_BUTTON_DEACTIVATED
 						, Constants.CONNECTION_UNBOUND, creationGroupConnection);
 		ToggleButton radBtnConnectionBound =  createNewToggleProperty(Constants.TOGGLE_BUTTON_ACTIVATED
 						, Constants.CONNECTION_BOUND, creationGroupConnection);
 		radBtnConnectionBound.setSelected(true);

 		creationGroupConnection.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observeable
					, Toggle oldValue, Toggle newValue) {
				
				String userData = Constants.UNSELECTED;
				if(radBtnConnectionBound.isSelected() || radBtnConnectionUnbound.isSelected())
					userData = newValue.getUserData().toString();
				
		 		if(userData.contentEquals(Constants.CONNECTION_UNBOUND)) {
		 			radBtnConnectionUnbound.setText(Constants.TOGGLE_BUTTON_ACTIVATED);
		 			radBtnConnectionBound.setText(Constants.TOGGLE_BUTTON_DEACTIVATED);
		 			GraphPannelSettings.settingConnection = Constants.CONNECTION_UNBOUND;
		 		}
		 		else if(userData.contentEquals(Constants.CONNECTION_BOUND)) {
		 			radBtnConnectionBound.setText(Constants.TOGGLE_BUTTON_ACTIVATED);
		 			radBtnConnectionUnbound.setText(Constants.TOGGLE_BUTTON_DEACTIVATED);
		 			GraphPannelSettings.settingConnection = Constants.CONNECTION_BOUND;
		 		}
		 		else {
		 			radBtnConnectionBound.setText(Constants.TOGGLE_BUTTON_DEACTIVATED);
		 			radBtnConnectionUnbound.setText(Constants.TOGGLE_BUTTON_DEACTIVATED);
		 			GraphPannelSettings.settingConnection = Constants.SETTINGS_RESET;
		 		}
			}
 		});
 		
    	ToggleGroup creationGroupExport = new ToggleGroup();
 		ToggleButton radBtnAdditionalInfos = createNewToggleProperty(Constants.TOGGLE_BUTTON_ACTIVATED
 						, Constants.ADDITIONAL_EXPORT, creationGroupExport);
 		radBtnAdditionalInfos.setSelected(true);

 		ToggleButton radBtnNormalInfos = createNewToggleProperty(Constants.TOGGLE_BUTTON_DEACTIVATED
 						, Constants.DEFAULT_EXPORT, creationGroupExport);

 		
 		/**
 		 * Select the Toggle property via Toggle Group and changes the text and selected property
 		 */
 		creationGroupExport.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observeable
					, Toggle oldValue, Toggle newValue) {
				String userData = Constants.UNSELECTED;
				if(radBtnAdditionalInfos.isSelected() || radBtnNormalInfos.isSelected())
					userData = newValue.getUserData().toString();
				
		 		if(userData.contentEquals(Constants.ADDITIONAL_EXPORT)) {
		 			radBtnAdditionalInfos.setText(Constants.TOGGLE_BUTTON_ACTIVATED);
		 			radBtnNormalInfos.setText(Constants.TOGGLE_BUTTON_DEACTIVATED);
		 			GraphPannelSettings.settingExport = Constants.ADDITIONAL_EXPORT;
		 		}
		 		else if(userData.contentEquals(Constants.DEFAULT_EXPORT)) {
		 			radBtnNormalInfos.setText(Constants.TOGGLE_BUTTON_ACTIVATED);
		 			radBtnAdditionalInfos.setText(Constants.TOGGLE_BUTTON_DEACTIVATED);
		 			GraphPannelSettings.settingExport = Constants.DEFAULT_EXPORT;
		 		}
		 		else {
		 			radBtnNormalInfos.setText(Constants.TOGGLE_BUTTON_DEACTIVATED);
		 			radBtnAdditionalInfos.setText(Constants.TOGGLE_BUTTON_DEACTIVATED);
		 			GraphPannelSettings.settingExport = Constants.SETTINGS_RESET;
		 		}
			}
 		});
    	 
 		 Label heading = new Label(Constants.TEXT_START_HEADING);
 		 heading.getStyleClass().add(Constants.STYLE_HEADING);
 		 Label bound = new Label(Constants.TEXT_START_BOUND);
 		 bound.getStyleClass().add(Constants.STYLE_NORMAL_TEXT);
 		 Label unbound = new Label(Constants.TEXT_START_UNBOUND);
 		 unbound.getStyleClass().add(Constants.STYLE_NORMAL_TEXT);
 		 Label additionalExport = new Label(Constants.TEXT_START_ADDITIONAL_EXPORT);
 		 additionalExport.getStyleClass().add(Constants.STYLE_NORMAL_TEXT);
 		 Label commonExport = new Label(Constants.TEXT_START_COMMON_EXPORT);
 		 commonExport.getStyleClass().add(Constants.STYLE_NORMAL_TEXT);
 		 
 		 gridpane.add(heading, 1, 0);
    	 gridpane.addRow(4, bound, radBtnConnectionBound, unbound, radBtnConnectionUnbound);
    	 gridpane.addRow(6, additionalExport, radBtnAdditionalInfos, commonExport, radBtnNormalInfos);
    	 
    	 gridpane.setAlignment(Pos.CENTER);
    	 gridpane.setHgap(20);
    	 gridpane.setVgap(20);
    	 
    	 return gridpane;
    	 
     }//setCenterElements
    
    //Function to setUserData and new BtnText (activate and deactivate)
    private ToggleButton createNewToggleProperty(String btnText, String userData, ToggleGroup toggleGroup) {
 		ToggleButton tg = new ToggleButton(btnText);
 		tg.setUserData(userData);
 		tg.setToggleGroup(toggleGroup);
 		return tg;
    }//createNewToggleProperty
	

    /**
     * Set Menubar in vbox style classes are pulled out of Constants class
     * @return VBox
     */
     private Pane setTopElements() {
         VBox vbox = new VBox(22);       
         vbox.setAlignment(Pos.CENTER);
         vbox.setFillWidth(true);

         MenuBar menuBar = new MenuBar();
         Menu menuDatei = new Menu(Constants.TEXT_MENU_PROGRAMM);
         menuBar.getMenus().add(menuDatei);
         MenuItem menuNewGraph = new MenuItem(Constants.TEXT_MENU_ITEM_NEW_GRAPH);
         MenuItem menuImportGraph = new MenuItem(Constants.TEXT_MENU_ITEM_IMPORT_GRAPH);
         MenuItem menuExit = new MenuItem(Constants.TEXT_MENU_ITEM_EXIT);
         menuDatei.getItems().addAll(menuNewGraph, menuImportGraph, menuExit);
         menuNewGraph.setOnAction( e->openNewGraphMenu() );
         menuImportGraph.setOnAction( e->importGraph() );
         menuExit.setOnAction( e->endApplication() );
         
 		Menu menuHelp = new Menu(Constants.TEXT_MENU_HELP);
 		menuBar.getMenus().add(menuHelp);
 		MenuItem doc = new MenuItem(Constants.TEXT_MENU_ITEM_DOCUMENTATION);
 		doc.setOnAction(e->Basis.infoBox(Constants.MESSAGE_INFO_HELP, Constants.TEXT_INFORMATION));
 		menuHelp.getItems().addAll(doc);
        /** menuOpen.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));*/

         vbox.getChildren().add(menuBar);

         return vbox ;
     }//setTopElements
    
     /**
      * Menu Function to End Application
      */
 	private void endApplication() {
 		Platform.exit();
 		System.exit(0);
	}//endApllication
     
 	/**
 	 * Menubar Function import
 	 */
 	private void importGraph() {
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(StageChanger.mainStage);	
		
		if(file != null)	{
			NewGraphPannel graphPannel = new NewGraphPannel();
			StageChanger.hideMainStage();
			graphPannel.createNewGraphPannelViaImport(file);	
		}
		
	}//importGraph
     
     /**
      * Set Bottom Elements of the Gridpane
      * @return FlowPane
      */
     private Pane setBottomElements() {            
         FlowPane flowpane = new FlowPane(20,20);     
         flowpane.setMaxWidth(Double.POSITIVE_INFINITY);
         flowpane.getStyleClass().add(Constants.STYLE_BOTTOM_ELEMENTS);
         
         Button next = new Button(Constants.TEXT_START_BTN_NEXT);
         next.getStyleClass().add(Constants.STYLE_BOTTOM_ELEMENTS);
         next.setOnAction( e->openNewGraphMenu() );
         flowpane.getChildren().add(next);
         flowpane.setAlignment(Pos.BOTTOM_RIGHT);

         return flowpane;
     }//setBottomElements
     
     
     /**
      * Open NewGraphPannel instance when toggle button settings are selected
      */
	  private void openNewGraphMenu() {
		if(!GraphPannelSettings.settingConnection.isEmpty() && !GraphPannelSettings.settingExport.isEmpty()) {
			NewGraphPannel graphPannel = new NewGraphPannel();
			StageChanger.hideMainStage();
			graphPannel.createNewGraphPannel();	
		}
		else
			Basis.errorBox(Constants.MESSAGE_ERROR_NO_SETTINGS_SELECTED, Constants.TEXT_ERROR);
	}//openNewGraphMenu

	public static void main(String[] argv) {       
	          launch(argv);   
	  }
}


