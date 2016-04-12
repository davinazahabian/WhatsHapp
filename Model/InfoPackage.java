package Model;
import java.io.Serializable;

// this serializable class sets up the types of packages that will be sent: user, event, and message
// these classes will always have a string identifier, to identify the contents when being received

public class InfoPackage implements Serializable {
	private static final long serialVersionUID = 1L;
	private String identifier;
	private boolean isValid;
	private boolean signUpRequest = false;
	private boolean loginRequest = false;
	private String userName = null;
	private int passWord = 0;
	private User user = null;
	public InfoPackage()
	{
		
	}
	
	public InfoPackage(String identifier) {
		this.identifier = identifier;
	}
	
	public String identifier() {
		return identifier;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isSignUpRequest() {
		return signUpRequest;
	}

	public void setSignUpRequest(boolean signUpRequest) {
		this.signUpRequest = signUpRequest;
	}

	public boolean isLoginRequest() {
		return loginRequest;
	}

	public void setLoginRequest(boolean loginRequest) {
		this.loginRequest = loginRequest;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPassWord() {
		return passWord;
	}

	public void setPassWord(int passWord) {
		this.passWord = passWord;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
