package com.paradigmadigital.conferenceScheduling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.paradigmadigital.conferenceScheduling.configuration.Configuration;
import com.paradigmadigital.conferenceScheduling.models.Proposal;
import com.paradigmadigital.conferenceScheduling.utils.ProposalManager;
import com.paradigmadigital.conferenceScheduling.utils.SchedulerOfProposals;

/**
 * The MainConferenceManager try to provide a solution for the problem
 * The steps are: 
 * - Read the different talks from the file and parser it to Proposals objects 
 * - Order the proposals in order to get the proposals grouped by length 
 * - Look for the different possible combinations 
 * - Finally create the agenda with the combinations 
 * - Print Output the results
 * 
 * @throws Exception if there is a problem with the input file (not found it or reading it)
 * 
 * @author amacarrilla
 * @version 1.0
 * @since 2019-02-02
 */
public class MainConferenceManager {

	public static void main(String[] args) throws Exception {

		ProposalManager proposalManager = new ProposalManager();
		SchedulerOfProposals scheduler = new SchedulerOfProposals();
		List<Proposal> proposalsList = new ArrayList<Proposal>();
		try {
			// Reading the different talks from the file and parsing it into proposal
			proposalsList = proposalManager.readFile(Configuration.SCHEDULE_TASKS_FILE);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Sorting the list in descending order
		Collections.sort(proposalsList, Proposal.CompLength);

		// Creating agenda
		scheduler.createDailyAgenda(proposalsList);
	}
}
