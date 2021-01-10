package com.bank.modal;

import java.util.Date;

public class Transaction {
	
	private int t_id;
	private long acc_num;
	private Date date;
	private String msg;
	private float amount;
	private String t_type;
	private float balance;
	private int status;
	//private Time time;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	public Transaction(int t_id, long acc_num, Date date, String msg, float amount, String t_type, float balance,
			int status) {
		super();
		this.t_id = t_id;
		this.acc_num = acc_num;
		this.date = date;
		this.msg = msg;
		this.amount = amount;
		this.t_type = t_type;
		this.balance = balance;
		this.status = status;
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public long getAcc_num() {
		return acc_num;
	}
	public void setAcc_num(long acc_num) {
		this.acc_num = acc_num;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getT_type() {
		return t_type;
	}
	public void setT_type(String t_type) {
		this.t_type = t_type;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Transaction\nacc_num=" + acc_num + "\ndate=" + date + "\nmsg=" + msg + "\namount=" + amount
				+ "\nt_type=" + t_type + "\nbalance=" + balance + "\n";
	}
	
	

}
