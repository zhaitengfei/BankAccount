package com.kata.BankAcountKata;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private DateProvider dateProvider;

	private List<Transaction> allTransactions = new ArrayList<Transaction>();
	
	public TransactionRepository(DateProvider dateProvider) {
		this.dateProvider = dateProvider;
	}
	
	
	public void addDeposit(int amount) {
		Transaction depositTransaction = new Transaction( formatter.format(dateProvider.getNow()), amount);
		allTransactions.add(depositTransaction);
	}
	
	public void addWithdraw(int amount) {
		Transaction withdrawTransaction = new Transaction( formatter.format(dateProvider.getNow()), -amount);
		allTransactions.add(withdrawTransaction);	
	}

	public List<Transaction> getAllTransactions() {
		return Collections.unmodifiableList(allTransactions);
	}
}
