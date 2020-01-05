package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank TurkishBank, PolishBank, JapaneseBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		TurkishBank = new Bank("TurkishBank", SEK);
		PolishBank = new Bank("PolishBank", SEK);
		JapaneseBank = new Bank("JapaneseBank", DKK);
		TurkishBank.openAccount("Jane");
		TurkishBank.openAccount("Ela");
		PolishBank.openAccount("Bob");
		JapaneseBank.openAccount("Toho");
	}
	
	
	 // Check of getName() method
	 
	@Test
	public void GetNameTest() {
		assertEquals("TurkishBank", TurkishBank.getName());
	}

	
	 // Check of getCurrency() method
	 
	@Test
	public void GetCurrencyTest() {
		assertEquals(DKK, JapaneseBank.getCurrency());
	}

	
	 // Check of openAccount() method
	 
	@Test
	public void OpenAccountTest() throws AccountExistsException, AccountDoesNotExistException {
		TurkishBank.openAccount("test1");
		assertTrue(TurkishBank.existsAccount("test1"));
	}

	// Check of deposit() method
	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		TurkishBank.deposit("Ela", new Money(1000, SEK));
		assertEquals(1000, TurkishBank.getBalance("Ela"), 0);
	}

	// Check of withdraw() method
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		TurkishBank.withdraw("Ela", new Money(1000, SEK));
		assertEquals(-1000, TurkishBank.getBalance("Ela"), 0);
	}
	
	// Check of getBalance() method
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(0, TurkishBank.getBalance("Ela"), 0);
	}
	
	// Check of transfer() method between banks
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		TurkishBank.deposit("Ela", new Money(1000, SEK));
		TurkishBank.transfer("Ela", "Jane", new Money(500, SEK)); 
		assertEquals(500, TurkishBank.getBalance("Ela"), 0);
		assertEquals(500, TurkishBank.getBalance("Jane"), 0);

		TurkishBank.transfer("Ela", JapaneseBank, "Toho", new Money(500, SEK));
		assertEquals(0, TurkishBank.getBalance("Ela"), 0);
		assertEquals((int) (500 * 0.15 / 0.20), JapaneseBank.getBalance("Toho"), 0);	
		}
	
	// check of addTimedPayment() method
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		TurkishBank.deposit("Ela", new Money(1000, SEK));
		TurkishBank.addTimedPayment("Ela", "1", 2, 0, new Money(1000, SEK), TurkishBank, "Jane");
		TurkishBank.tick();
		
		assertEquals(1000, TurkishBank.getBalance("Ela"), 0);
		assertEquals(0, TurkishBank.getBalance("Jane"), 0);

		
	}
}
