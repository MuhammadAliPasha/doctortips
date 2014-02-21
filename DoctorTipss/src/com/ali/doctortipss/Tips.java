package com.ali.doctortipss;

import java.util.ArrayList;

public class Tips {
	private String tipId;
	private String tipTypeId;
	private String tipCategoryId;
	private String tipTitle;
	private String tipImage;
	private String tipShortDescription;
	private String tipDescription;
	private ArrayList<String> imagesArrayList;

	public String getTipId() {
		return tipId;
	}

	public void setTipId(String tipId) {
		this.tipId = tipId;
	}

	public String getTipTypeId() {
		return tipTypeId;
	}

	public void setTipTypeId(String tipTypeId) {
		this.tipTypeId = tipTypeId;
	}

	public String getTipCategoryId() {
		return tipCategoryId;
	}

	public void setTipCategoryId(String tipCategoryId) {
		this.tipCategoryId = tipCategoryId;
	}

	public String getTipTitle() {
		return tipTitle;
	}

	public void setTipTitle(String tipTitle) {
		this.tipTitle = tipTitle;
	}

	public String getTipImage() {
		return tipImage;
	}

	public void setTipImage(String tipImage) {
		this.tipImage = tipImage;
	}

	public String getTipShortDescription() {
		return tipShortDescription;
	}

	public void setTipShortDescription(String tipShortDescription) {
		this.tipShortDescription = tipShortDescription;
	}

	public String getTipDescription() {
		return tipDescription;
	}

	public void setTipDescription(String tipDescription) {
		this.tipDescription = tipDescription;
	}

	public void ImagesArrayList() {
		
		imagesArrayList=new ArrayList<String>();
	}
	public ArrayList<String> getImagesArrayList() {
		return imagesArrayList;
	}
	public void setImagesArrayList(String img) {
		
		this.imagesArrayList.add(img);
	}

}
