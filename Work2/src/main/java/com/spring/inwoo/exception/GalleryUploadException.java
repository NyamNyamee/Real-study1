package com.spring.inwoo.exception;

public class GalleryUploadException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2844575395236746227L;
	
	public GalleryUploadException() {
		super("Gallery File Upload Exception");
	}
	public GalleryUploadException(String message) {
		super(message);
	}
}




