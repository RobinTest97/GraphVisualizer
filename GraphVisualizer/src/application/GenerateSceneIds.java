package application;

/**
 * 
 * @author robinkopitz
 * 
 * Generate a Scene id with a instance it is not a unique Id like Signelton Id
 * because of the back to menu button and the import. Otherwise the ids stack up
 * and the import feature does not work cause of out of bound exception.
 *
 */
public class GenerateSceneIds {

	private int sceneId = 0;
	
	public GenerateSceneIds() {
		
	}
	
	public int getNewSceneId() {
		return ++sceneId;
	}
	
	public void setStartSceneId(int startId) { 
		this.sceneId = startId;
	}
	
	
}
