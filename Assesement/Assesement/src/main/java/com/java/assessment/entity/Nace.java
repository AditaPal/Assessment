package com.java.assessment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "NACE")
public class Nace {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "OrderId")
	private int order;
	@Column(name = "Level")
	private int level;
	@Column(name = "Code")
	private String code;
	@Column(name = "Parent")
	private String parent;
	@Column(name = "Description" )
	private String description;
	@Column(name = "This_Item_Includes" ,length=1000)
	@Lob
	private String thisItemIncludes;
	@Column(name = "This_Item_Also_Includes")
	private String thisItemAlsoIncludes;
	@Column(name = "Rulings")
	private String rulings;
	@Column(name = "This_Item_Excludes")
	private String thisItemExcludes;
	@Column(name = "Reference")
	private String reference;

	public Nace(long id, int order, int level, String code, String parent, String description, String thisItemIncludes,
			String thisItemAlsoIncludes, String rulings, String thisItemExcludes, String reference) {
		super();
		this.id = id;
		this.order = order;
		this.level = level;
		this.code = code;
		this.parent = parent;
		this.description = description;
		this.thisItemIncludes = thisItemIncludes;
		this.thisItemAlsoIncludes = thisItemAlsoIncludes;
		this.rulings = rulings;
		this.thisItemExcludes = thisItemExcludes;
		this.reference = reference;
	}

	public Nace() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThisItemIncludes() {
		return thisItemIncludes;
	}

	public void setThisItemIncludes(String thisItemIncludes) {
		this.thisItemIncludes = thisItemIncludes;
	}

	public String getThisItemAlsoIncludes() {
		return thisItemAlsoIncludes;
	}

	public void setThisItemAlsoIncludes(String thisItemAlsoIncludes) {
		this.thisItemAlsoIncludes = thisItemAlsoIncludes;
	}

	public String getRulings() {
		return rulings;
	}

	public void setRulings(String rulings) {
		this.rulings = rulings;
	}

	public String getThisItemExcludes() {
		return thisItemExcludes;
	}

	public void setThisItemExcludes(String thisItemExcludes) {
		this.thisItemExcludes = thisItemExcludes;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "Nace [id=" + id + ", order=" + order + ", level=" + level + ", code=" + code + ", parent=" + parent
				+ ", description=" + description + ", thisItemIncludes=" + thisItemIncludes + ", thisItemAlsoIncludes="
				+ thisItemAlsoIncludes + ", rulings=" + rulings + ", thisItemExcludes=" + thisItemExcludes
				+ ", reference=" + reference + "]";
	}

}
