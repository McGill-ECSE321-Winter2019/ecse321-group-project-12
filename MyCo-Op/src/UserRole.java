import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public abstract class UserRole {
	private String password;

	private void setPassword(String value) {
		this.password = value;
	}

	private String getPassword() {
		return this.password;
	}

	private MyCoOp myCoop;

	@ManyToOne(optional = false)
	public MyCoOp getMyCoop() {
		return this.myCoop;
	}

	public void setMyCoop(MyCoOp myCoop) {
		this.myCoop = myCoop;
	}

	private String userRoleId;

	private void setUserRoleId(String value) {
		this.userRoleId = value;
	}

	@Id
	private String getUserRoleId() {
		return this.userRoleId;
	}
}
