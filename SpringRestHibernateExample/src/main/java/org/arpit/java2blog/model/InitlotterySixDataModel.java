package org.arpit.java2blog.model;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.arpit.java2blog.controller.InitlotterySixData;

@Entity
@Table(name="lottery.lotterysixbase")
public class InitlotterySixDataModel {

	@Id
	@Column(name="ID")
	protected String id;
	
	@Column(name="N1")
	protected int n1;
	
	@Column(name="N2")
	protected int n2;
	
	@Column(name="N3")
	protected int n3;
	
	@Column(name="N4")
	protected int n4;
	
	@Column(name="N5")
	protected int n5;
	
	@Column(name="N6")
	protected int n6;
	
	@Column(name="SUMVALUE")
	protected int sumValue;
	
	@Column(name="CRSVALUE")
	protected int crsValue;
	
	@Column(name="ODDVALUE")
	protected int oddValue;
	
	@Column(name="INTVALUE")
	protected int intValue;
	
	@Column(name="CONVALUE")
	protected int conValue;

	public InitlotterySixDataModel() {
		super();	
	}
	
	public boolean isEqualToModel(InitlotterySixDataModel model) {
		return this.n1 == model.n1 &&
				this.n2 == model.n2 &&
				this.n3 == model.n3 &&
				this.n4 == model.n4 &&
				this.n5 == model.n5 &&
				this.n6 == model.n6;
	}
	
	public static InitlotterySixDataModel generateRandomModel() {
		InitlotterySixDataModel newModel = new InitlotterySixDataModel();
		InitlotterySixData init = new InitlotterySixData();
		Random random = new Random();
		newModel.n1 = random.nextInt(35) + 1;
		newModel.n2 = random.nextInt(35) + 1;
		newModel.n3 = random.nextInt(35) + 1;
		newModel.n4 = random.nextInt(35) + 1;
		newModel.n5 = random.nextInt(35) + 1;
		newModel.n6 = random.nextInt(35) + 1;
		int[] intAry = {newModel.n1,newModel.n2,newModel.n3,newModel.n4,newModel.n5,newModel.n6};
		newModel.sumValue = init.sumData(intAry);
		newModel.crsValue = init.crsData(intAry);
		newModel.oddValue = init.oddData(intAry); 
		newModel.intValue = init.intData(intAry); 
		newModel.conValue = init.conData(intAry);
		return newModel;
	}
	
	public InitlotterySixDataModel( String id, int[] arry, int sumValue, int crsValue, int oddValue, int intValue, int conValue) {
		super();
		this.id = id;
		this.n1 = arry[0];
		this.n2 = arry[1];
		this.n3 = arry[2];
		this.n4 = arry[3];
		this.n5 = arry[4];
		this.n6 = arry[5];
		this.sumValue = sumValue;
		this.crsValue = crsValue;
		this.oddValue = intValue;
		this.intValue = intValue;
		this.conValue = conValue;
	}

	public String getId() {
		return id;
	}

	public int getN1() {
		return n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}

	public int getN2() {
		return n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

	public int getN3() {
		return n3;
	}

	public void setN3(int n3) {
		this.n3 = n3;
	}

	public int getN4() {
		return n4;
	}

	public void setN4(int n4) {
		this.n4 = n4;
	}

	public int getN5() {
		return n5;
	}

	public void setN5(int n5) {
		this.n5 = n5;
	}

	public int getN6() {
		return n6;
	}

	public void setN6(int n6) {
		this.n6 = n6;
	}

	public int getSumValue() {
		return sumValue;
	}

	public void setSumValue(int sumValue) {
		this.sumValue = sumValue;
	}

	public int getCrsValue() {
		return crsValue;
	}

	public void setCrsValue(int crsValue) {
		this.crsValue = crsValue;
	}

	public int getOddValue() {
		return oddValue;
	}

	public void setOddValue(int oddValue) {
		this.oddValue = oddValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public int getConValue() {
		return conValue;
	}

	public void setConValue(int conValue) {
		this.conValue = conValue;
	}


}
