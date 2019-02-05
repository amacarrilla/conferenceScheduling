package com.paradigmadigital.conferenceScheduling.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.paradigmadigital.conferenceScheduling.configuration.Configuration;
import com.paradigmadigital.conferenceScheduling.models.Proposal;

/**
 * The AgendaListGenerator will build a final agenda of the different tracks
 * given a proposal's list
 * 
 * @param morningSessionsTrack
 *            List<Proposal> with the proposals for the morning
 * @param eveningSessionsTrack
 *            List<Proposal> with the proposals for the evening
 *            
 * @author amacarrilla
 * @version 1.0
 * @since 2019-02-02
 */
public class AgendaListGenerator {

	public void finalAgenda(List<List<Proposal>> morningSessionsTrack, List<List<Proposal>> eveningSessionsTrack) {
		List<List<Proposal>> scheduledProposalsList = new ArrayList<List<Proposal>>();
		int totalPossibleDays = morningSessionsTrack.size();

		// Iterating over the different days 
		for (int dayCount = 0; dayCount < totalPossibleDays; dayCount++) {
			List<Proposal> proposalList = new ArrayList<Proposal>();

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
			Calendar cal = new GregorianCalendar();
			cal.set(Calendar.HOUR, 9);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.AM_PM, Calendar.AM);

			int trackCount = dayCount + 1;
			String scheduledTime = sdf.format(cal.getTime());

			System.out.println(Configuration.NAME_OF_TRACK + trackCount + ":");

			// Morning Sessions
			List<Proposal> morningSessionProposalList = morningSessionsTrack.get(dayCount);
			for (Proposal proposal : morningSessionProposalList) {
				proposal.setHourOfTheDay(scheduledTime);
				System.out.println(
						scheduledTime + " " + proposal.getTitle() + " " + proposal.getLength() + Configuration.MINUTES);
				cal.add(Calendar.MINUTE, proposal.getLength());
				scheduledTime = sdf.format(cal.getTime());
				proposalList.add(proposal);
			}

			// Lunch Time
			Proposal lunchProposal = new Proposal(Configuration.LUCNH, Configuration.LUCNH_LENGTH);
			lunchProposal.setHourOfTheDay(scheduledTime);
			proposalList.add(lunchProposal);
			System.out.println(
					scheduledTime + " " + Configuration.LUCNH + " " + Configuration.LUCNH_LENGTH + Configuration.MINUTES);

			// Evening Sessions
			cal.add(Calendar.MINUTE, Configuration.LUCNH_LENGTH);
			scheduledTime = sdf.format(cal.getTime());
			List<Proposal> eveningSessionProposalList = eveningSessionsTrack.get(dayCount);
			for (Proposal proposal : eveningSessionProposalList) {
				proposal.setHourOfTheDay(scheduledTime);
				proposalList.add(proposal);
				System.out.println(
						scheduledTime + " " + proposal.getTitle() + " " + proposal.getLength() + Configuration.MINUTES);
				cal.add(Calendar.MINUTE, proposal.getLength());
				scheduledTime = sdf.format(cal.getTime());
			}

			// Networking and beers time
			Proposal networkingProposal = new Proposal(Configuration.NETWORKING, Configuration.NETWORKING_LENGTH);
			networkingProposal.setHourOfTheDay(scheduledTime);
			proposalList.add(networkingProposal);
			System.out.println(scheduledTime + " " + Configuration.NETWORKING + "\n");
			scheduledProposalsList.add(proposalList);
		}
	}

}
