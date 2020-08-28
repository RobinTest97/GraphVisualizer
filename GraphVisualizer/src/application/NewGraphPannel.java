package application;

import java.io.File;  
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * @author robinkopitz
 *
 */
public class NewGraphPannel extends Application {

	private Stage stage = null;
	private BorderPane root;
	private VBox centerBox = null;
	private Label state = new Label(Constants.TEXT_BOT_LABEL_START);
	
	private GenerateSceneIds generateId = new GenerateSceneIds();

	public NewGraphPannel() {
	}

	/**
	 * The start Methode creates a border pane with left, top, bot and right elements
	 * it also implements the stylesheets
	 */
	@Override
	public void start(Stage stage) throws Exception {

		stage = new Stage();
		root = new BorderPane();
		state.getStyleClass().add(Constants.STYLE_TOP_BOT_PANNEL);
		root.setTop( setTopElements() );
		root.setBottom( setBottomElements() );
		root.setCenter(centerBox = setCenterElements());
		BorderPane.setMargin(centerBox, new Insets(40));
		root.getStyleClass().add(Constants.STYLE_BACKGROUND_WHITE);
		root.setLeft( setLeftElements() );

		
		
		StageChanger.graphPannelStage = stage;
		Scene scene = new Scene(root, 1000, 500);
        scene.getStylesheets().addAll(Constants.STYLE_FONT_QUICKSAND
        		, Constants.STYLE_FONT_RALEWAY
        		, Constants.STYLE_FONT_SIGNIKA);
        scene.getStylesheets().add(Constants.STYLESHEET_PATH);
		stage.setTitle(Constants.TEXT_GRAPH_STAGE_HEADING);
		stage.setScene(scene);
		stage.show();
	}	

/* ---------------------------------------Top Element----------------------------------------------------*/

	/**
	 * Creates the top pannel and MenuBar
	 * 
	 * @return VBox
	 */
	private Node setTopElements() {
		VBox vbox = new VBox(22);
		vbox.setAlignment(Pos.CENTER);
		vbox.setFillWidth(true);

		MenuBar menuBar = new MenuBar();

		Menu menuDatei = new Menu(Constants.TEXT_MENU_PROGRAMM);
		menuBar.getMenus().add(menuDatei);
		MenuItem menuBackToMainPage = new MenuItem(Constants.TEXT_MENU_ITEM_BACK);
		MenuItem menuExportGraph = new MenuItem(Constants.TEXT_MENU_ITEM_EXPORT);
		MenuItem menuExit = new MenuItem(Constants.TEXT_MENU_ITEM_EXIT);
		
		menuExportGraph.setOnAction(e->exportGraph());
		menuBackToMainPage.setOnAction(e->StageChanger.changeStageFromPannelToMain());
		menuExit.setOnAction(e->endApplication());
		
		menuDatei.getItems().addAll(menuBackToMainPage, menuExportGraph, menuExit);

		Menu menuHelp = new Menu(Constants.TEXT_MENU_HELP);
		menuBar.getMenus().add(menuHelp);
		MenuItem doc = new MenuItem(Constants.TEXT_MENU_ITEM_DOCUMENTATION);
		doc.setOnAction(e->Basis.infoBox(Constants.MESSAGE_INFO_HELP, Constants.TEXT_INFORMATION));
		menuHelp.getItems().addAll(doc);
		/** menuOpen.setAccelerator(KeyCombination.keyCombination("Ctrl+O")); */

		vbox.getChildren().add(menuBar);

		return vbox;
	}//setTopElements
	
	/**
	 * Menu Method "Beenden"
	 */
	private void endApplication() {
 		Platform.exit();
 		System.exit(0);
	}//endApplication

	/**
	 * Menu Method export Graph
	 */
	private void exportGraph() {
		if(GraphInformations.createdNodes.size() > 0) {
			FileChooser fileChooser = new FileChooser();
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
	        fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showSaveDialog(stage);	
			if(file != null)
				new ExportGraph(file);
		}
		else
			Basis.errorBox(Constants.MESSAGE_ERROR_EXPORT_NOT_POSSIBLE, Constants.TEXT_ERROR);
	}//exportGraph
	

/* ---------------------------------------End top Elements-----------------------------------------------*/


/* ---------------------------------------Bottom Elements------------------------------------------------*/
	
	/**
	 * Set bottom pannel with editor state label
	 * @return VBox
	 */
	private Node setBottomElements() {
		VBox vbox = new VBox();
		vbox.getStyleClass().add(Constants.STYLE_BOTTOM_ELEMENTS);
		vbox.getChildren().add(state);
		return vbox;
	}//setBottomElement

/* --------------------------------------End Bottom Elements---------------------------------------------*/


/*---------------------------------------Left Elements---------------------------------------------------*/

	/**
	 * Creates the sidepannel with the toggle button features
	 * @return VBox
	 */
	private Node setLeftElements() {
		VBox vbox = new VBox();
		vbox.setPrefWidth(150);
		vbox.getStyleClass().add(Constants.STYLE_SIDEPANNEL);
		vbox.setAlignment(Pos.CENTER);
		
		ToggleGroup creationGroup = new ToggleGroup();

		ToggleButton radBtnNode = new ToggleButton(Constants.TOGGLE_BUTTON_NODE);
		radBtnNode.setUserData(Constants.NODE);
		radBtnNode.setToggleGroup(creationGroup);
		vbox.getChildren().add(radBtnNode);
		VBox.setMargin(radBtnNode, new Insets(10, 2, 10, 2));

		ToggleButton radBtnConnectionUnbound = new ToggleButton(Constants.TOGGLE_BUTTON_UNBOUND);
		radBtnConnectionUnbound.setUserData(Constants.CONNECTION_UNBOUND);
		radBtnConnectionUnbound.setToggleGroup(creationGroup);
		vbox.getChildren().add(radBtnConnectionUnbound);
		VBox.setMargin(radBtnConnectionUnbound, new Insets(50, 2, 10, 2));

		ToggleButton radBtnConnectionBound = new ToggleButton(Constants.TOGGLE_BUTTON_BOUND);
		radBtnConnectionBound.setUserData(Constants.CONNECTION_BOUND);
		radBtnConnectionBound.setToggleGroup(creationGroup);
		vbox.getChildren().add(radBtnConnectionBound);
		VBox.setMargin(radBtnConnectionBound, new Insets(50, 2, 10, 2));

		
		System.out.println(GraphPannelSettings.settingConnection);
		if(GraphPannelSettings.settingConnection.contentEquals(Constants.CONNECTION_BOUND)) {
			radBtnConnectionUnbound.setDisable(true);
		}
		else if(GraphPannelSettings.settingConnection.contentEquals(Constants.CONNECTION_UNBOUND)) {
			radBtnConnectionBound.setDisable(true);
		}
		
		/**
		 * Resets the click listeners on every click to remove all clicked feautres
		 * we differentiate the button clicks over the userData method
		 */
		creationGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable
					, Toggle oldValue, Toggle newValue) {
				if (creationGroup.getSelectedToggle() != null) {
					String task = newValue.getUserData().toString();

					switch (task) {
					case Constants.NODE:
						resetOnClicks();
						createNodeOnClick();
						break;
					case Constants.CONNECTION_UNBOUND:
						resetOnClicks();
						createConnectionOnClick(task);
						break;
					case Constants.CONNECTION_BOUND:
						resetOnClicks();
						createConnectionOnClick(task);
					default:
						root.setOnMouseClicked(null);
						break;
					}
				}
			}
		});

		return vbox;
	}//setLeftElements

/* ------------------------------------End Left Elements-------------------------------------------------*/


/*-------------------------------------Center Elements---------------------------------------------------*/

	/**
	 * Create center box for white node pannel
	 * 
	 * @return VBox
	 */
	private VBox setCenterElements() {
		VBox vbox = new VBox();
		vbox.getStyleClass().add(Constants.STYLE_BACKGROUND_WHITE);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}//setCenterElements

/* ------------------------------------End Center Elementes----------------------------------------------*/

	/**
	 * Use class variable because of the enclosing scope
	 */
	private double startX = 0;
	private double startY = 0;
	private int clickCounter = 0;
	private GraphNode firstClickedNode = null;

	/**
	 * Creates a connection with start, end point and one of the two connections classes
	 * it also checks if the connection from one node to another alread exists
	 * we use a counter to count the clicks on the nodes to ensure that we have two points
	 * 
	 * @param task
	 */
	private void createConnectionOnClick(String task) {
		// Setzen des Status in Connection Creation
		state.setText(Constants.TEXT_BOT_IN_CONNECTION_CREATION);
		root.setBottom( setBottomElements() );
		for (GraphNode node : GraphInformations.createdNodes) {
			node.getCircle().setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					clickCounter++;
					if (event.getButton() == MouseButton.SECONDARY) {
						// Add Menu
					} else if (clickCounter == 1) {
						startX = node.getCoordX();
						startY = node.getCoordY();
						firstClickedNode = node;
					} else {
						double endX = node.getCoordX();
						double endY = node.getCoordY();
						if (task == Constants.CONNECTION_UNBOUND) {
							if (!connectionExists(firstClickedNode, node)) {
								UnboundConnectionNodes newConnection = 
										new UnboundConnectionNodes(startX, startY,
										endX, endY, root, firstClickedNode, node);
								GraphInformations.createdConnections.add(newConnection);
								setContextMenuConnection(newConnection);
								newConnection.setNewNodeConnectionState();
								firstClickedNode.getOutgoingConnections().add(newConnection);
							} else
								Basis.errorBox(Constants.MESSAGE_ERROR_CONNECTION_EXIST, Constants.TEXT_ERROR);
						} else {
							if (!connectionExists(firstClickedNode, node)) {
								BoundConnectionNodes newConnection = 
										new BoundConnectionNodes(startX, startY, endX,
										endY, root, firstClickedNode, node);
								GraphInformations.createdConnections.add(newConnection);
								setContextMenuConnection(newConnection);
								newConnection.setNewNodeConnectionState();
								firstClickedNode.getOutgoingConnections().add(newConnection);
							} else
								Basis.errorBox(Constants.MESSAGE_ERROR_CONNECTION_EXIST, Constants.TEXT_ERROR);
						}
							clickCounter = 0;
						}
					}
			});
		}
	}//createConnectionOnClick

	/**
	 * The method looks through the already created connections and checks if the connection
	 * already exits with the same start and end point
	 * 
	 * @param startNode
	 * @param endNode
	 * @return
	 */
	private boolean connectionExists(GraphNode startNode, GraphNode endNode) {
		boolean conExist = false;
		if (GraphPannelSettings.settingConnection.contentEquals(Constants.CONNECTION_UNBOUND)) {
			for (Connection connection : GraphInformations.createdConnections) {
				GraphNode conStartNode = connection.getStartNode();
				GraphNode conEndNode = connection.getEndNode();
				if (startNode.equals(conStartNode) && endNode.equals(conEndNode))
					conExist = true;
				if(startNode.equals(conEndNode) && endNode.equals(conStartNode))
					conExist = true;
			}
		} else if (GraphPannelSettings.settingConnection.contentEquals(Constants.CONNECTION_BOUND)) {
			for (Connection connection : GraphInformations.createdConnections) {
				GraphNode conStartNode = connection.getStartNode();
				GraphNode conEndNode = connection.getEndNode();
				if (startNode.equals(conStartNode) && endNode.equals(conEndNode))
					conExist = true;
			}
		} else {
			Basis.errorBox(Constants.MESSAGE_ERROR_UNKNOWN_TASK, Constants.TEXT_ERROR);
			conExist = true;
		}
		return conExist;
	}//connectionExists
	
	/**
	 * Sets the prepared Context Menu to the connection
	 * 
	 * @param connectionInf
	 */
	private void setContextMenuConnection(Connection connectionInf) {
		Shape connection = connectionInf.getConnection();
		connection.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
			@Override
			public void handle(ContextMenuEvent event) {
				ContextMenu contextMenu = prepareContextMenuConnection(connectionInf);
				contextMenu.show(connection, event.getScreenX(), event.getScreenY());
			}
		});
	}//setContextMenuConnection

	/**
	 * Prepares the context menu for the right click of the connections
	 * 
	 * @param connection
	 * @return contextMenu
	 */
	private ContextMenu prepareContextMenuConnection(Connection connection) {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem delete = new MenuItem(Constants.LEFT_CLICK_MENU_DELETE);
		MenuItem setCost = new MenuItem(Constants.LEFT_CLICK_MENU_ADD_COST);
		delete.setOnAction(e -> connection.removeElement(GraphInformations.createdConnections));
		setCost.setOnAction(e->connection.setConnectionCost());
		contextMenu.getItems().addAll(setCost, delete);
		return contextMenu;
		
	}//prepareContextMenuConnection

	/**
	 * Method to set the prepared context menu for the nodes
	 * 
	 * @param node
	 */
	private void setContextMenuNode(GraphNode node) {
		Circle circle = node.getCircle();
		circle.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
			@Override
			public void handle(ContextMenuEvent event) {
				ContextMenu contextMenu = prepareContextMenuNode(node);
				contextMenu.show(circle, event.getScreenX(), event.getScreenY());
			}
		});
	}//setContextMenuNode

	/**
	 * Prepares the context menu for every Node
	 * 
	 * @param node
	 * @return
	 */
	private ContextMenu prepareContextMenuNode(GraphNode node) {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem delete = new MenuItem(Constants.LEFT_CLICK_MENU_DELETE);
		delete.setOnAction(e -> node.removeElement(GraphInformations.createdNodes));
		MenuItem setName = new MenuItem(Constants.LEFT_CLICK_MENU_ADD_NODE_NAME);
		setName.setOnAction(e -> node.setNodeNameLabel());
		contextMenu.getItems().addAll(setName, delete);
		return contextMenu;
	}//prepareContextMenuNode

	/**
	 * Create the node on left click with the primary button and the coordinates of the centerBox
	 */
	private void createNodeOnClick() {
		// Setzen des Status in Node creation
		state.setText(Constants.TEXT_BOT_IN_NODE_CREATION);
		root.setBottom(setBottomElements());

		centerBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double coordX = event.getSceneX();
				double coordY = event.getSceneY();
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					GraphNode node = new GraphNode(coordX, coordY, root);
					node.setNodeId(generateId.getNewSceneId());
					node.setName(String.valueOf(node.getNodeID()));
					setContextMenuNode(node);
					GraphInformations.createdNodes.add(node);
				}
			}
		});
	}//createNodeOnClick


	private void resetOnClicks() {
		centerBox.setOnMouseClicked(null);

	}//resetOnClicks

	/**
	 * Start Method of NewGraphPannel
	 */
	public void createNewGraphPannel() {
		try {
			this.start(this.stage);
		} catch (Exception e) {
			Basis.errorBox(Constants.MESSAGE_ERROR_NO_STAGE, Constants.TEXT_ERROR);
			StageChanger.showMainStage();
			e.printStackTrace();
		}
	}//createNewGraphPannel
	
	/**
	 * Start Method for Import File to get the NewGraphPannel stage
	 * 
	 * @param file
	 */
	public void createNewGraphPannelViaImport(File file) {
		try {
			this.start(this.stage);
			importData(file);
		} catch (Exception e) {
			Basis.errorBox(Constants.MESSAGE_ERROR_NO_STAGE, Constants.TEXT_ERROR);
			StageChanger.showMainStage();
			e.printStackTrace();
		}
	}//createNewGraphPannelViaImport
	
	
	/**
	 * Set the context menus for the created connections and nodes in the import class
	 * 
	 * @param file
	 */
	private void importData(File file) {
		new ImportGraph(root, file);
		if(GraphInformations.createdNodes.size() == 0) {
			StageChanger.changeStageFromPannelToMain();
			Basis.errorBox(Constants.MESSAGE_ERROR_IMPORT, Constants.TEXT_ERROR);
		}
		else {
			//Setzen des Context Menues fuer die importierten Knoten
			for(GraphNode node : GraphInformations.createdNodes)
				setContextMenuNode(node);
					
			if(GraphPannelSettings.settingConnection.contentEquals(Constants.CONNECTION_BOUND)) {
				for(Connection connection: GraphInformations.createdConnections)
					setContextMenuConnection((BoundConnectionNodes)connection);
			}else if(GraphPannelSettings.settingConnection.contentEquals(Constants.CONNECTION_UNBOUND)) {
				for(Connection connection: GraphInformations.createdConnections) 
					setContextMenuConnection((UnboundConnectionNodes)connection);
			}
			int startId = GraphInformations.createdNodes.get(GraphInformations.createdNodes.size()-1).getNodeID();
			generateId.setStartSceneId(startId);
		}
	}//importData
}//NewGraphPannel

