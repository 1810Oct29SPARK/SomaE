package com.revature.mutation;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MinimumMutations {
	
	private List<String> geneBank;

	// Default constructor
	public MinimumMutations() {
		super();
		this.geneBank = new ArrayList<String>();
		this.geneBank.add("AACCGGTA");
		this.geneBank.add("AACCGCTA");
		this.geneBank.add("AAACGGTA");
	}
	
	// Constructing from a gene bank file
	public MinimumMutations(String fileName) throws IOException {
		
		this.geneBank = new ArrayList<String>();
		
		FileReader fr = new FileReader(fileName);
		StringBuilder geneStream = new StringBuilder("");
		int geneData = 0;
		
		while ((geneData = fr.read()) != -1) {
			geneStream.append((char)geneData);
		}
		
		String[] geneSet = (new String(geneStream)).split("[\\s,]+");
		for (String s: geneSet) {
			this.geneBank.add(s);
		}
		
		fr.close();
		
	}
	
	//
	
}
