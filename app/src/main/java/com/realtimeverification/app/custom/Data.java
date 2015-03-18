package com.realtimeverification.app.custom;

import java.util.ArrayList;

/**
 * Created by vaal on 3/17/2015.
 */
public class Data {

	private String folderId;
	private String parentId;
	private String folderName;
	private ArrayList<Data> data;
	private ArrayList<File> files;

	public Data(String folderId, String parentId, String folderName, ArrayList<File> files){
		setFolderId(folderId);
		setParentId(parentId);
		setFolderName(folderName);
		setFiles(files);
	}

	private void setFolderId(String folderId){
		this.folderId = folderId;
	}

	private void setParentId(String parentId){
		this.parentId = parentId;
	}

	private void setFolderName(String folderName){
		this.folderName = folderName;
	}

	public void setData(ArrayList<Data> data) {
		this.data = data;
	}

	public ArrayList<File> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}

	public String getFolderId() {
		return folderId;
	}

	public String getParentId() {
		return parentId;
	}

	public String getFolderName() {
		return folderName;
	}


	public ArrayList<Data> getData() {
		return data;
	}
}
