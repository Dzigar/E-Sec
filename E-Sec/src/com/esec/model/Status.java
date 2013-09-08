package com.esec.model;

public enum Status {

	STATUS_MADE(1), STATUS_FAILED(-1), STATUS_WAIT(0);

	private int status;

	private Status(int status) {
		this.setStatus(status);
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
