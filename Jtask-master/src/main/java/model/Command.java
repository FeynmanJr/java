package model;

import jakarta.persistence.*;

@Entity
@Table(name = "command")
public class Command {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String command;
	@Column(name = "class")
	private String className;
	private String method;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public Command () {}
	
	public Command (Class<?> className, String method) {
		this.className = className.getName();
		this.method = method;
		command = className.getSimpleName().toLowerCase() +"-"+ this.method;
	}
}
