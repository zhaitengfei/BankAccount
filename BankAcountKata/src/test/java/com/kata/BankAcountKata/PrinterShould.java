package com.kata.BankAcountKata;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
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
public class PrinterShould {
	
	private Printer printer;
	@Mock
	Console console;
    
    @Before
    public void init() {
    	printer = new Printer(console);
    }
    
    @Test
    public void printOnlyHeaderWhenNoTransaction(){
    	printer.print(new ArrayList<Transaction>());    	
    	verify(console).printf("DATE | AMOUNT | BALANCE");
    }
    
    @Test
    public void printAllTransactions(){    	
    	List<Transaction> transactions = getTransactions();
    	printer.print(transactions);
        
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
