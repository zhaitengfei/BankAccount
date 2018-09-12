package com.kata.BankAcountKata;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {
	
	@Mock
	TransactionRepository transactionRepository;
	
	@Mock
	Console console;
	
	private Account account;
	private Printer printer;
	
	@Before
	public void init() {
		given(transactionRepository.getAllTransactions()).willReturn(getTransactions());
		printer = new Printer(console);
		account = new Account(transactionRepository, printer);
	}
	
	@Test
	public void processADepositTransaction() {
		account.deposit(100);		
		verify(transactionRepository).addDeposit(100);
	}
	
	@Test
	public void processAWithdrawTransaction() {
		account.withdraw(200);		
		verify(transactionRepository).addWithdraw(200);
	}
	
	@Test
	public void printStatement() {
		
		account.printStatement();
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printf("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printf("11/09/2018 | 400,00 | 700,00");
        inOrder.verify(console).printf("28/05/2018 | -200,00 | 300,00");
        inOrder.verify(console).printf("30/01/2018 | 500,00 | 500,00");
	}
	
    private List<Transaction> getTransactions()
    {
    	List<Transaction> transactions = Arrays.asList(new Transaction[]{
    		new Transaction("30/01/2018", 500),
            new Transaction("28/05/2018", -200),
            new Transaction("11/09/2018", 400)
    	});
        return transactions;
    }
}
