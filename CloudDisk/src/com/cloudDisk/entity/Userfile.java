package com.cloudDisk.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Userfile entity. @author MyEclipse Persistence Tools
 */

public class Userfile implements java.io.Serializable {

	// Fields

	private String fileId;
	private User user;
	private String fileName;
	private Float fileSize;
	private Timestamp uploadDate;
	private String fileRemarks;
	private String fileUrl;

	// Constructors

	/** default constructor */
	public Userfile() {
	}

	/** minimal constructor */
	public Userfile(User user) {
		this.user = user;
	}

	/** full constructor */
	public Userfile(User user, String fileName, Float fileSize,
			Timestamp uploadDate, String fileRemarks, String fileUrl) {
		this.user = user;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.uploadDate = uploadDate;
		this.fileRemarks = fileRemarks;
		this.fileUrl = fileUrl;
	}

	// Property accessors

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Float getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Float fileSize) {
		this.fileSize = fileSize;
	}

	public Date getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getFileRemarks() {
		return this.fileRemarks;
	}

	public void setFileRemarks(String fileRemarks) {
		this.fileRemarks = fileRemarks;
	}

	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}