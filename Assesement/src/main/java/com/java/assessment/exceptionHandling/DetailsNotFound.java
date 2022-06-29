package com.java.assessment.exceptionHandling;

public class DetailsNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public DetailsNotFound() {
		super();
	}

	public DetailsNotFound(final String message) {
		super(message);
	}

}
