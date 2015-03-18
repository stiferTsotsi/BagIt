package com.realtimeverification.app.custom;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by vaal on 3/17/2015.
 */
public class Content {

	private String folderId;
	private String parentId;
	private String folderName;
	private File[] files;
	private ArrayList<Content> contents;

	public Content(){

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

	public void setContents(ArrayList<Content> contents) {
		this.contents = contents;
	}

	private void setFiles(File[] files){

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

	public File[] getFiles() {
		return files;
	}

	public ArrayList<Content> getContents() {
		return contents;
	}
}
