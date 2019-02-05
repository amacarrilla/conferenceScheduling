package com.paradigmadigital.conferenceScheduling.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.paradigmadigital.conferenceScheduling.configuration.Configuration;
import com.paradigmadigital.conferenceScheduling.models.Proposal;

/**
 * The ProposalManager class implements two methods for read the proposal's file and convert into Proposal objects
 * 
 * @author amacarrilla
 * @version 1.0
 * @since 2019-02-02
 */

public class ProposalManager {

	public List<Proposal> readFile(String file) throws FileNotFoundException, IOException {
		String lineFromFile;
		List<Proposal> proposalList = new ArrayList<Proposal>();
		InputStream is = getClass().getResourceAsStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		int id = 0;
		while ((lineFromFile = reader.readLine()) != null) {
			id++;
			Proposal proposal = createProposals(id, lineFromFile);
			proposalList.add(proposal);
		}
		reader.close();
		return proposalList;
	}

	private Proposal createProposals(int id, String talk) {
		Proposal proposal = new Proposal();
		// Setting title and scheduled state
		proposal.setTitle(talk.substring(0, talk.lastIndexOf(" ")));
		proposal.scheduled(false);
		// Setting lenght
		if (isFastTalk(talk))
			proposal.setLength(5);
		else
			proposal.setLength(Integer.parseInt(talk.replaceAll("\\D+", "")));
		return proposal;
	}

	private boolean isFastTalk(String talk) {
		if (talk.substring(talk.lastIndexOf(" ") + 1).equals(Configuration.FASTTALK))
			return true;
		else
			return false;

	}

}
