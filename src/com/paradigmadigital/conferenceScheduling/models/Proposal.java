package com.paradigmadigital.conferenceScheduling.models;

import java.util.Comparator;

public class Proposal {

	private String title;
	private int length;
	private boolean scheduledTalk;
	private String hourOfTheDay;

	public Proposal(String title, int length) {
		super();
		this.title = title;
		this.length = length;
	}

	public Proposal() {
		super();
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the hourOfTheDay
	 */
	public String getHourOfTheDay() {
		return hourOfTheDay;
	}

	/**
	 * @param hourOfTheDay
	 *            the hourOfTheDay to set
	 */
	public void setHourOfTheDay(String hourOfTheDay) {
		this.hourOfTheDay = hourOfTheDay;
	}

	public boolean isScheduled() {
		return scheduledTalk;
	}

	public void scheduled(boolean newState) {
		this.scheduledTalk = newState;
	}

	// Comparator for sorting the list by length in descending order
	public static Comparator<Proposal> CompLength = new Comparator<Proposal>() {

		public int compare(Proposal proposal1, Proposal proposal2) {

			int length1 = proposal1.getLength();
			int legnth2 = proposal2.getLength();

			/* For descending order */
			return legnth2 - length1;
		}
	};

	@Override
	public String toString() {
		return "Title: " + title + " Length: " + length;
	}

}
