package io.placements.challenge.jong.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
	@Id
    @GeneratedValue( strategy = GenerationType.AUTO )
	private int id;
	private int campaign_id;
	private String campaign_name;
	private String line_item_name;
	private double booked_amount;
	private double actual_amount;
	private double adjustments;
	
	public Item() {}
	
	public Item(int campaign_id, String campaign_name, String line_item_name, double booked_amount, double actual_amount, double adjustments) {
		this.campaign_id = campaign_id;
		this.campaign_name = campaign_name;
		this.line_item_name = line_item_name;
		this.booked_amount = booked_amount;
		this.actual_amount = actual_amount;
		this.adjustments = adjustments;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(int campaign_id) {
		this.campaign_id = campaign_id;
	}
	public String getCampaign_name() {
		return campaign_name;
	}
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}
	public String getLine_item_name() {
		return line_item_name;
	}
	public void setLine_item_name(String line_item_name) {
		this.line_item_name = line_item_name;
	}
	public double getBooked_amount() {
		return booked_amount;
	}
	public void setBooked_amount(double booked_amount) {
		this.booked_amount = booked_amount;
	}
	public double getActual_amount() {
		return actual_amount;
	}
	public void setActual_amount(double actual_amount) {
		this.actual_amount = actual_amount;
	}
	public double getAdjustments() {
		return adjustments;
	}
	public void setAdjustments(double adjustments) {
		this.adjustments = adjustments;
	}
}
