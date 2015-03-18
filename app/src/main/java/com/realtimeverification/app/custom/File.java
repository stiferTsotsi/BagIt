package com.realtimeverification.app.custom;

/**
 * Created by vaal on 3/18/2015.
 */
public class File {

	private String fileId;
	private String fileName;
	private String docName;
	private String fileURL;
	private String fileLocation;

	public File(String fileId,String fileName, String fileURL,String docName, String fileLocation){
		setFileId(fileId);
		setFileName(fileName);
		setFileURL(fileURL);
		setDocName(docName);
		setFileLocation(fileLocation);
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
}
