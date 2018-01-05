package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="friendss")
public class Friend {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private String fromId;
private String ToId;
private char status;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFromId() {
	return fromId;
}
public void setFromId(String fromId) {
	this.fromId = fromId;
}
public String getToId() {
	return ToId;
}
public void setToId(String toId) {
	ToId = toId;
}
public char getStatus() {
	return status;
}
public void setStatus(char status) {
	this.status = status;
}

}
