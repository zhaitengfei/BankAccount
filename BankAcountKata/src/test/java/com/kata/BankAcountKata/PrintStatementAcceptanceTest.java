package com.kata.BankAcountKata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementAcceptanceTest {
	@Mock
	DateProvider dateProvider;
	
	@Mock
	Console console;
	
	private Account account;
	
	@Before
	public void init() {
		
		LocalDate date1 = LocalDate.parse("30/01/2018",
				DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate date2 = LocalDate.parse("28/05/2018",
				DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate date3 = LocalDate.parse("11/09/2018",
				DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		given(dateProvider.getNow()).willReturn(date1, date2, date3);

		TransactionRepository transactionRepository = new TransactionRepository(dateProvider);
		Printer printer = new Printer(console);
		account = new Account(transactionRepository, printer);
	}

	@Test
	public void printAllTransactionsStatement(){
		account.deposit(500);
		account.withdraw(200);
		account.deposit(400);
		
        account.printStatement();
        
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printf("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printf("11/09/2018 | 400,00 | 700,00");
        inOrder.verify(console).printf("28/05/2018 | -200,00 | 300,00");
        inOrder.verify(console).printf("30/01/2018 | 500,00 | 500,00");
	}
	
}
