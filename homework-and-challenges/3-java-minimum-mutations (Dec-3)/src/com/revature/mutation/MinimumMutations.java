package com.revature.mutation;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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
	
	// Check if a gene is in the gene bank
	public boolean isInBank(String gene) {
		for (String s: this.geneBank) {
			if (gene.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	// The string operation of gene mutation
	public String doMutate(String source, String target, int index) {
		return source.substring(0, index).concat(target.substring(index, index + 1)).concat(source.substring(index + 1));
	}
	
	// Try to perform one level of mutation
	// Return a list with the extra gene if a mutation is possible, or the original list (empty) if no mutation is found
	private List<String> mutateOne(String currentGene, String targetGene, List<String> geneList) {
		
		// Terminate if the no mutation is needed
		if (currentGene.equals(targetGene)) {
			geneList.add(currentGene);
			return geneList;
		}
		
		// Perform mutation trials
		for (int i = 0; i < targetGene.length(); ++i) {
			if (currentGene.charAt(i) != targetGene.charAt(i) && isInBank(doMutate(currentGene, targetGene, i))) {
				if (mutateOne(doMutate(currentGene, targetGene, i), targetGene, geneList).size() != 0) {
					geneList.add(doMutate(currentGene, targetGene, i));
					return geneList;
				}
				else {
					continue;
				}
			}
		}
		// No successful mutation found
		return geneList;
		
	}
	
	// Attempt to find a mutation path given a start and end gene
	// Return the minimum mutations needed and print the steps of mutation if such path is found
	public int findMutations(String startGene, String endGene) {
		
		// Check invalid end gene
		if (!isInBank(endGene)) {
			System.out.println("Invalid gene string\n");
			return -1;
		}
		
		// Check same gene
		if (startGene.equals(endGene)) {
			System.out.println("No mutation needed\n");
			return 0;
		}
		
		// An empty list to store the steps of mutation
		List<String> geneList = new ArrayList<String>();
		
		// Get the mutation steps if possible
		geneList = mutateOne(startGene, endGene, geneList);
		
		// Process the gene list
		if (geneList.size() > 0) {
			// Remove the first repeated element and reverse the list
			geneList.remove(0);
			Collections.reverse(geneList);
			
			// Print out the steps
			System.out.println("Mutation found.\nTotal steps required: " + geneList.size());
			System.out.format("Step %d: %s%n", 0, startGene);
			for (int i = 0; i < geneList.size(); ++i) {
				System.out.format("Step %d: %s%n", i + 1, geneList.get(i));
			}
			System.out.println("");
			return geneList.size();
		}
		else {
			System.out.println("No mutations found");
			return -1;
		}
		
	}
	
}
