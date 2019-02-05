package com.paradigmadigital.conferenceScheduling.utils;

import java.util.ArrayList;
import java.util.List;

import com.paradigmadigital.conferenceScheduling.configuration.Configuration;
import com.paradigmadigital.conferenceScheduling.models.Proposal;

/**
 * SchedulerOfProposals is in charge of the whole process, executing the different steps
 * 
 * @param proposalsList
 *            List<Proposal> with the complete list of proposals        
 * @author amacarrilla
 * @version 1.0
 * @since 2019-02-02
 */

public class SchedulerOfProposals {

	public void createDailyAgenda(List<Proposal> proposalsList) throws Exception {
		// Find the total possible days.
		int perDayMinTime = 6 * 60;
		int totalProposalsTime = getTotalProposalsTime(proposalsList);
		int totalPossibleDays = totalProposalsTime / perDayMinTime;
		FitAndSuffleSessions fass = new FitAndSuffleSessions();

		// Find possible combinations for the morning session.
		List<List<Proposal>> morningSessions = fass.doSomeCalcs(proposalsList, totalPossibleDays, false);
		// Remove proposals assigned to morning sessions
		for (List<Proposal> proposalList : morningSessions) {
			proposalsList.removeAll(proposalList);
		}
		// Find possible combinations for the evening session.
		List<List<Proposal>> eveningSessions = fass.doSomeCalcs(proposalsList, totalPossibleDays, false);
		// Remove proposals assigned to evening sessions
		for (List<Proposal> proposalList : eveningSessions) {
			proposalsList.removeAll(proposalList);
		}
		// if more tracks left, try to fill the evening track (if its possible)
		int maxSessionTimeLimit = Configuration.EVENING_TRACK_LENGHT;
		if (!proposalsList.isEmpty()) {
			List<Proposal> scheduledProposalList = new ArrayList<Proposal>();
			for (List<Proposal> proposalList : eveningSessions) {
				int totalTime = getTotalProposalsTime(proposalList);

				for (Proposal proposal : proposalsList) {
					int proposalTime = proposal.getLength();

					if (proposalTime + totalTime <= maxSessionTimeLimit) {
						proposalList.add(proposal);
						proposal.scheduled(true);
						scheduledProposalList.add(proposal);
					}
				}

				proposalsList.removeAll(scheduledProposalList);
				if (proposalsList.isEmpty())
					break;
			}
		}

		// Create Agenda for morning and evening session
		AgendaListGenerator agenda = new AgendaListGenerator();
		agenda.finalAgenda(morningSessions, eveningSessions);
	}

	public static int getTotalProposalsTime(List<Proposal> proposalsList) {
		if (proposalsList == null || proposalsList.isEmpty())
			return 0;

		int totalTime = 0;
		for (Proposal proposal : proposalsList) {
			totalTime += proposal.getLength();
		}
		return totalTime;
	}

}
