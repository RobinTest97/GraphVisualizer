package application;

/**
 * 
 * @author robinkopitz
 *
 */
public class Constants {

/*--------------------------------------------------Setting Constants------------------------------------*/
	public static final String CONNECTION_UNBOUND = "unbound";
	public static final String CONNECTION_BOUND= "bound";
	
	public static final String DEFAULT_EXPORT = "normalInfo";
	public static final String ADDITIONAL_EXPORT = "additionalInfo";
	
	public static final String NODE = "node";
	
	public static final String UNSELECTED ="unselected";
/*--------------------------------------------------End Setting Constants-------------------------------*/	

/*--------------------------------------------------Texts------------------------------------------------*/
	
	/*---------------------------------------------Error Box---------------------------------------------*/
	public static final String TEXT_ERROR = "Error";
	public static final String MESSAGE_ERROR_UNKNOWN_TASK = "Dieser Befehl ist nicht bekannt!";
	public static final String MESSAGE_ERROR_NO_CHANGES = 
			"Geben Sie bitte ein Zahl ein, es wurden keine aenderungen durchgef\u00fchrt!";
	public static final String MESSAGE_ERROR_IS_NUMERIC = 
			"Es wurde ein Zahl erwartet doch text \u00dcbermittelt!";
	public static final String MESSAGE_ERROR_NO_SETTINGS_SELECTED =
			"Bitten w\u00e4hlen Sie die Eigenschaften aus!";
	public static final String MESSAGE_ERROR_EXPORT_NOT_POSSIBLE = 
			"Ein Export ist leider nicht m\u00f6glich da noch kein Knoten angelegt wurde!";
	public static final String MESSAGE_ERROR_CONNECTION_EXIST = 
			"Ein Export ist leider nicht m\u00f6glich da noch kein Knoten angelegt wurde";
	public static final String MESSAGE_ERROR_NO_STAGE =
			"Die Stage konnte nicht g\u00f6ffnet werden!";
	public static final String MESSAGE_ERROR_IMPORT =
			"Die Datei konnte nicht importiert werden!";
	/*-----------------------------------------End Error Box---------------------------------------------*/
	
	/*----------------------------------------Information Box--------------------------------------------*/
	public static final String TEXT_INFORMATION = "Information";
	public static final String MESSAGE_INFO_EXPORT = "Der Export wurde durchgef\u00fchrt!";
	public static final String MESSAGE_INFO_HELP = "Hilfe finde Sie in der beiliegenden Hausarbeit unter dem Themengebiet \"Beschreibung"
			+ " der Oberfläche\"!";
	/*----------------------------------------End Information Box----------------------------------------*/
	
	
	/*----------------------------------------Input Fields-----------------------------------------------*/
	//Input Connection Cost
	public static final String TEXT_INPUT_COST = "Verbindungskosten";
	public static final String MESSAGE_INPUT_COST = "Geben Sie bitte die Kosten ein: ";
	
	//Input GraphNode Name
	public static final String TEXT_INPUT_NODE_NAME = "Graph Node Name";
	public static final String MESSAGE_INPUT_NODE_NAME = "Geben Sie bitte den Node Namen ein: ";

	
	
	/*-----------------------------------------GraphVisualizer-------------------------------------------*/
	//GraphVisualizer Stage Header
	public static final String TEXT_START_STAGE_HEADING = "GraphVisualizer";
	
	//GraphVisualizer Toggle Buttons
	public static final String TOGGLE_BUTTON_ACTIVATED = "aktiviert";
	public static final String TOGGLE_BUTTON_DEACTIVATED = "deaktiviert";
	public static final String SETTINGS_RESET = "";
	
	//GraphVisualizer Label Texte
	public static final String TEXT_START_HEADING = "Graph Eigenschaften: ";
	public static final String TEXT_START_BOUND = "Gerichteter Graph: ";
	public static final String TEXT_START_UNBOUND = "Ungerichteter Graph: ";
	public static final String TEXT_START_ADDITIONAL_EXPORT = "Export mit zus\u00e4tzlichen Informationen: ";
	public static final String TEXT_START_COMMON_EXPORT = "Export mit Standard Infromationen: ";
	
	//GraphVisualizer Button
	public static final String TEXT_START_BTN_NEXT = "Next";
	
	
	//Menu und MenuItems Start Page
	public static final String TEXT_MENU_PROGRAMM = "Programm";
	public static final String TEXT_MENU_ITEM_NEW_GRAPH = "Neuen Graph erstellen";
	public static final String TEXT_MENU_ITEM_IMPORT_GRAPH = "Importieren eines Graphen";
	public static final String TEXT_MENU_ITEM_EXIT = "Beenden";
	
	public static final String TEXT_MENU_HELP = "Hilfe";
	public static final String TEXT_MENU_ITEM_DOCUMENTATION = "Dokumentation";
	/*-------------------------------------End GraphVisualizer-------------------------------------------*/
	
	/*-------------------------------------New Graph Pannel----------------------------------------------*/	
	//Stage Header NewGraphPannel
	public static final String TEXT_GRAPH_STAGE_HEADING = "Erstellen eines neune Graphens";
	
	//Bottom Line Label
	public static final String TEXT_BOT_LABEL_START = "-";
	public static final String TEXT_BOT_IN_NODE_CREATION = "Knoten Erstellung";
	public static final String TEXT_BOT_IN_CONNECTION_CREATION = "erstelle Knoten Verbindung";
	
	//Menu Items additionals
	public static final String TEXT_MENU_ITEM_BACK = "Zur\u00fcck zur Startseite";
	public static final String TEXT_MENU_ITEM_EXPORT = "Exportieren des Graphen";
	
	//Toggle Button New Graph Pannel
	public static final String TOGGLE_BUTTON_NODE = "Knoten";
	public static final String TOGGLE_BUTTON_UNBOUND = "Ungerichtet";
	public static final String TOGGLE_BUTTON_BOUND = "Gerichtet";
	
	//left Click Menu
	public static final String LEFT_CLICK_MENU_DELETE = "L\u00f6schen";
	public static final String LEFT_CLICK_MENU_ADD_NODE_NAME = "Knoten benennen";
	public static final String LEFT_CLICK_MENU_ADD_COST = "Kosten setzen";
	/*-------------------------------------End New Graph Pannel------------------------------------------*/

/*-----------------------------------------End Texts-----------------------------------------------------*/
	
/*-------------------------------------Style Constants-----------------------------------------------*/
	/*Fonts*/
	public static final String STYLE_FONT_RALEWAY =
			"https://fonts.googleapis.com/css?family=Quicksand&display=swap";
	
	public static final String STYLE_FONT_SIGNIKA =
			"https://fonts.googleapis.com/css?family=Signika&display=swap";
	
	public static final String STYLE_FONT_QUICKSAND = 
			"https://fonts.googleapis.com/css?family=Quicksand&display=swap";
	
	public static final String STYLESHEET_PATH ="application/style.css";
	
	//Style Classes
	public static final String STYLE_NORMAL_TEXT = "style-normal-text";
	public static final String STYLE_HEADING = "style-heading";
	public static final String STYLE_BOTTOM_ELEMENTS = "bottom-elements";
	public static final String STYLE_BACKGROUND_WHITE = "background-white";
	public static final String STYLE_TOP_BOT_PANNEL = "text-bot-top-pannel";
	public static final String STYLE_SIDEPANNEL = "sidepannel";
	
/*------------------------------------End Style Constants--------------------------------------------*/

	
}
