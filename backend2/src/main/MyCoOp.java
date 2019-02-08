import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Set;
import java.util.HashSet;

@Entity
public class MyCoOp {
	@OneToMany(mappedBy = "myCoOp", cascade = { CascadeType.ALL })
	public Set<User> getUsers() {
		return this.users;
	}

	@OneToMany(mappedBy = "myCoop", cascade = { CascadeType.ALL })
	public Set<UserRole> getRoles() {
		return this.roles;
	}

	private String iD;

	private void setID(String value) {
		this.iD = value;
	}

	@Id
	private String getID() {
		return this.iD;
	}

	private Set<User> users;

	public void setUsers(Set<User> userss) {
		this.users = userss;
	}

	private Set<UserRole> roles;

	public void setRoles(Set<UserRole> roless) {
		this.roles = roless;
	}

}
