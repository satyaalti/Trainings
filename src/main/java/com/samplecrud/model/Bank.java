package com.samplecrud.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the banks database table.
 * 
 */
@Entity
@Table(name="banks")
@NamedQuery(name="Bank.findAll", query="SELECT b FROM Bank b")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "bankid")
	private int bankid;

	private String bankname;
	

	@Override
	public String toString() {
		return "Bank [bankid=" + bankid + ", bankname=" + bankname + "]";
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bank")
	private Set<Users> user = new HashSet<Users>();
		
	public Set<Users> getUser() {
		return user;
	}

	public void setUser(Set<Users> user) {
		this.user = user;
	}

	public Bank() {
	}

	public int getBankid() {
		return this.bankid;
	}

	public void setBankid(int bankid) {
		this.bankid = bankid;
	}

	public String getBankname() {
		return this.bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

}