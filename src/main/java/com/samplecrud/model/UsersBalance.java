package com.samplecrud.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

/**
 * The persistent class for the users_balance database table.
 * 
 */
@Entity
@Table(name="users_balance")
@NamedQuery(name="UsersBalance.findAll", query="SELECT u FROM UsersBalance u")
public class UsersBalance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	private double addamount;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int userid;

	private double withdrawamount;
	
	private int transferid;

	private String typeoftxn;
	private int withdrawfee;
	private double transferfee;

	
	@Override
	public String toString() {
		return "UsersBalance [id=" + id + ", addamount=" + addamount + ", date=" + date + ", userid=" + userid
				+ ", withdrawamount=" + withdrawamount + "]";
	}

	@ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name="userid", insertable=false, updatable=false)
	private Users user;
	
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public UsersBalance() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAddamount() {
		return this.addamount;
	}

	public void setAddamount(double addamount) {
		this.addamount = addamount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public double getWithdrawamount() {
		return this.withdrawamount;
	}

	public void setWithdrawamount(double withdrawamount) {
		this.withdrawamount = withdrawamount;
	}
	public double getTransferfee() {
		return this.transferfee;
	}

	public void setTransferfee(double transferfee) {
		this.transferfee = transferfee;
	}
	public int getTransferid() {
		return this.transferid;
	}

	public void setTransferid(int transferid) {
		this.transferid = transferid;
	}

	public String getTypeoftxn() {
		return this.typeoftxn;
	}

	public void setTypeoftxn(String typeoftxn) {
		this.typeoftxn = typeoftxn;
	}
	public int getWithdrawfee() {
		return this.withdrawfee;
	}

	public void setWithdrawfee(int withdrawfee) {
		this.withdrawfee = withdrawfee;
	}
	
}