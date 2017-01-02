package utilities;

public class LoginUtility {
	
	private static LoginUtility loginUtility = new LoginUtility();
	
	private LoginUtility(){}
	
	static LoginUtility getInstance(){
		return loginUtility;
	}
	
	public String getImageToDisplay() {
		return imageToDisplay;
	}

	public void setImageToDisplay(String imageToDisplay) {
		this.imageToDisplay = imageToDisplay;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	private String imageToDisplay;
	private String action;

}
