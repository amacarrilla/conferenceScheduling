package com.paradigmadigital.conferenceScheduling.utils;

import java.util.ArrayList;
import java.util.List;

import com.paradigmadigital.conferenceScheduling.configuration.Configuration;
import com.paradigmadigital.conferenceScheduling.models.Proposal;

/**
 * FitAndSuffleSessions try to find out the different combinations in order to fill the morning and evening sessions
 * This algorithm shuffle the sessions to fit in the remaining the time for each session
 * 
 * @param proposalsListForOperation
 *            List<Proposal> with the complete list of proposals
 *                
 * @param totalPossibleDays
 *            int number of possible days      
 *                
 * @author amacarrilla
 * @version 1.0
 * @since 2019-02-02
 */

public class FitAndSuffleSessions {

	public List<List<Proposal>> doSomeCalcs(List<Proposal> proposalsListForOperation, int totalPossibleDays,
			boolean morningSession) {
		int minSessionTimeLimit = Configuration.MORNING_TRACK_LENGHT;
		int maxSessionTimeLimit = Configuration.EVENING_TRACK_LENGHT;

		if (morningSession)
			maxSessionTimeLimit = minSessionTimeLimit;

		int proposalListSize = proposalsListForOperation.size();
		List<List<Proposal>> possibleCombinationsOfProposals = new ArrayList<List<Proposal>>();
		int possibleCombinationCount = 0;
		for (int count = 0; count < proposalListSize; count++) {
			int startPoint = count;
			int totalTime = 0;
			List<Proposal> possibleCombinationList = new ArrayList<Proposal>();
			while (startPoint != proposalListSize) {
				int currentCount = startPoint;
				startPoint++;
				Proposal currentProposal = proposalsListForOperation.get(currentCount);
				if (currentProposal.isScheduled())
					continue;
				int proposalTime = currentProposal.getLength();
				if (proposalTime > maxSessionTimeLimit || proposalTime + totalTime > maxSessionTimeLimit) {
					continue;
				}
				possibleCombinationList.add(currentProposal);
				totalTime += proposalTime;
				if (morningSession) {
					if (totalTime == maxSessionTimeLimit)
						break;
				} else if (totalTime >= minSessionTimeLimit)
					break;
			}

			boolean validSession;
			if (morningSession)
				validSession = (totalTime == maxSessionTimeLimit);
			else
				validSession = (totalTime >= minSessionTimeLimit && totalTime <= maxSessionTimeLimit);

			if (validSession) {
				possibleCombinationsOfProposals.add(possibleCombinationList);
				for (Proposal proposal : possibleCombinationList) {
					proposal.scheduled(true);
				}
				possibleCombinationCount++;
				if (possibleCombinationCount == totalPossibleDays)
					break;
			}
		}
		return possibleCombinationsOfProposals;
	}

}
