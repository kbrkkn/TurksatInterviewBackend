package com.turksat.belgenet.interview.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Table(name = "FILE")
@Entity
public class File implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "FILE_NAME")
	private String fileName;

    @Lob
	@Column(name = "FILE_CONTENT")
    private byte[] data;
    
	@Column(name = "UPLOADER_KEY")
	private String uploaderKey;

	@Column(name = "FILE_LINK")
	private String fileLink;

	@Column(name = "REFERENCE_KEY")
	private UUID referenceKey;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name="UPLOAD_DATE")
	@CreationTimestamp
	private Date uploadDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploaderKey() {
		return uploaderKey;
	}

	public void setUploaderKey(String uploaderKey) {
		this.uploaderKey = uploaderKey;
	}

	public String getFileLink() {
		return fileLink;
	}

	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}

	public UUID getReferenceKey() {
		return referenceKey;
	}

	public void setReferenceKey(UUID referenceKey) {
		this.referenceKey = referenceKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
