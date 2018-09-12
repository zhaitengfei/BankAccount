package com.kata.BankAcountKata;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {

	@Mock
	DateProvider dateProvider;

	public static final LocalDate transactionDate = LocalDate.parse("11/09/2018",
			DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	private TransactionRepository transactionRepository;

	@Before
	public void init() {
		given(dateProvider.getNow()).willReturn(transactionDate);
		transactionRepository = new TransactionRepository(dateProvider);
	}

	@Test
	public void processADepositTransaction() {
		transactionRepository.addDeposit(200);

		List<Transaction> transactions = transactionRepository.getAllTransactions();
		assertThat(transactions.size(), is(1));
		assertThat(transactions.get(0), is(new Transaction("11/09/2018", 200)));
	}

	@Test
	public void processAWithdrawTransaction() {
		transactionRepository.addWithdraw(200);

		List<Transaction> transactions = transactionRepository.getAllTransactions();
		assertThat(transactions.size(), is(1));
		assertThat(transactions.get(0), is(new Transaction("11/09/2018", -200)));

	}
}
