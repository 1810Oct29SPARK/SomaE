package com.revature.mutation;

import java.io.IOException;

public class MinimumMutationsTest {

	public static void main(String[] args) {
		
		// Create a Mutations object for testing
		MinimumMutations myGeneLab = null;
		
		// Try load a gene bank from file
		try {
			System.out.println("Try loading from gene bank...");
			myGeneLab = new MinimumMutations("gene-bank.txt");
		} catch (IOException e) {
			System.out.println("Unable to load from gene bank. Initializing from default gene pool...");
			myGeneLab = new MinimumMutations();
		}
		finally {
			System.out.println("Loading complete.");
		}
		
		// Start the mutation tests
		System.out.println("\n==========Minimum Mutations Test==========\n");
		myGeneLab.findMutations("AACCGGTT", "AACCGGTT");
		myGeneLab.findMutations("AACCGGTT", "EEACGGTA");
		myGeneLab.findMutations("AACCGGTT", "AAACGGTA");
		myGeneLab.findMutations("AACCGGTA", "GGCCTGTG");
		myGeneLab.findMutations("AAAAAAAA", "TTTTTTTT");
		myGeneLab.findMutations("EGHABBTG", "AACCGGTT");

	}

}
