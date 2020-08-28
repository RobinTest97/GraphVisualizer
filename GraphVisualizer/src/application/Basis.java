package application;

import java.util.Optional;

import javafx.scene.control.*;

/**
 * errorBox @author Michael Wilhelm
 * Other Boxes
 * @author robinkopitz
 *
 */

class Basis {

 public static void errorBox( String message, String title) {
         Dialog<?> alert = new Alert(Alert.AlertType.ERROR,message);
         alert.setTitle(title);
         alert.setHeaderText(title);
         alert.setResizable(true);
         alert.show();
//        a.setContentText(content);
      } // ErrorBox
 
 public static void infoBox( String message, String title) {
     Dialog<?> alert = new Alert(Alert.AlertType.INFORMATION,message);
     alert.setTitle(title);
     alert.setHeaderText(title);
     alert.setResizable(true);
     alert.show();
  } // infoBox
 
 public static void askForInput(Label label, String message, String title) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(message);
		dialog.setContentText("Name:");;

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            label.setText(name);
        });
 }//askForNodeName
 
 
 public static boolean isNummeric(String strNumber) {
	 try {
		 Double.parseDouble(strNumber);		
	} catch (NumberFormatException | NullPointerException e) {
		return false;
	}
	return true;
 }//isNummeric
 
}