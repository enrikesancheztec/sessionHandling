package mx.tec.lab.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull	
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	@JsonIgnore
	private Set<User> users;

	public Role() {}
	
	public Role(final Long id, final String name, final Set<User> users) {
		this.id = id;
		this.name = name;
		this.users = users;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
