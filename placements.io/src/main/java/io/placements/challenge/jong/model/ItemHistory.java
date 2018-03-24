package io.placements.challenge.jong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ItemHistory {
	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	private int item_id;
	private String column;
	private String old_val;
	private String new_val;
	
	public ItemHistory() {}
	
	public ItemHistory(int item_id, String column, String old_val, String new_val) {
		this.item_id = item_id;
		this.column = column;
		this.old_val = old_val;
		this.new_val = new_val;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getOld_val() {
		return old_val;
	}
	public void setOld_val(String old_val) {
		this.old_val = old_val;
	}
	public String getNew_val() {
		return new_val;
	}
	public void setNew_val(String new_val) {
		this.new_val = new_val;
	}
}

