package com.kata.BankAcountKata;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Printer {

	private Console console;

	private DecimalFormat decimalFormatter = new DecimalFormat("#.00");

	public Printer(Console console) {
		this.console = console;
	}

	public void print(List<Transaction> transactions) {
		printStatementHeader();
		printStatementLines(transactions);
	}

	private void printStatementHeader() {
		console.printf("DATE | AMOUNT | BALANCE");
	}

	private void printStatementLines(List<Transaction> transactions) {
		List<String> statementLines = formatStatementLines(transactions);
		statementLines.stream().collect(Collectors.toCollection(LinkedList::new)).descendingIterator()
				.forEachRemaining(console::printf);
	}

	private List<String> formatStatementLines(List<Transaction> transactions) {
		List<String> lines = new ArrayList<String>();
		int balance = 0;

		for (Transaction transaction : transactions) {
			balance += transaction.getAmount();
			String statementLine = transaction.getDate() + " | " + decimalFormatter.format(transaction.getAmount())
					+ " | " + decimalFormatter.format(balance);
			lines.add(statementLine);
		}

		return lines;
	}
}
