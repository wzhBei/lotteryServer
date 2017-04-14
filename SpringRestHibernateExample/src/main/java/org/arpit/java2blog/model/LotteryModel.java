package org.arpit.java2blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LotteryNumberTable")
public class LotteryModel {
	
	public LotteryModel() {
		super();
	}
	

	public LotteryModel(String id, int one, int two, int three, int four, int five, int six, int seven) {
		super();
		this.id = id;
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
		this.five = five;
		this.six = six;
		this.seven = seven;
	}


	@Id
	@Column(name="id")
	String id;
	
	@Column(name="one")
	int one;
	
	@Column(name="two")
	int two;
	
	@Column(name="three")
	int three;
	
	@Column(name="four")
	int four;
	
	@Column(name="five")
	int five;
	
	@Column(name="six")
	int six;
	
	@Column(name="seven")
	int seven;
	
	public int getOne() {
		return one;
	}

	public void setOne(int one) {
		this.one = one;
	}

	public int getTwo() {
		return two;
	}

	public void setTwo(int two) {
		this.two = two;
	}

	public int getThree() {
		return three;
	}

	public void setThree(int three) {
		this.three = three;
	}

	public int getFour() {
		return four;
	}

	public void setFour(int four) {
		this.four = four;
	}

	public int getFive() {
		return five;
	}

	public void setFive(int five) {
		this.five = five;
	}

	public int getSix() {
		return six;
	}

	public void setSix(int six) {
		this.six = six;
	}

	public int getSeven() {
		return seven;
	}

	public void setSeven(int seven) {
		this.seven = seven;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
