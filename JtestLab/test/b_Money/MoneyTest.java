package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	// Testing method getAmount() that returns amount of money
	@Test
	public void testGetAmount() {
		assertEquals(10000, SEK100.getAmount(), 0);
	}

	// Testing method getCurrency() that returns the currency of this Money.
	@Test
	public void testGetCurrency() {
		assertEquals(EUR, EUR10.getCurrency());
	}

	// Returns the amount of the money in the string form "(amount) (currencyname)", e.g. "10.5 SEK".
	@Test
	public void testToString() {
		System.out.println(EUR20);
	}
	
	// Gets the universal value of the Money, according the rate of its Currency
	@Test
	public void testGlobalValue() {
		assertEquals((int)(10000 * 0.15), SEK100.universalValue(), 0);
	}

	// Check to see if the value of this money is equal to the value of another Money of some other Currency.
	@Test
	public void testEqualsMoney() {
		Money SEKS = new Money(10000, SEK);
		assertTrue(SEKS.equals(SEK100));
	}

	@Test
	public void testAdd() {
		Money added = EUR10.add(SEK100);
		int sum = (int)((1000) + (10000 * 0.15 / 1.5));
		assertEquals(sum, added.getAmount(), 0);
	}

	@Test
	public void testSub() {
		Money subs = SEK200.sub(EUR10);
		int result = (int)((20000) - (1000 * 1.5 / 0.15 ));
		assertEquals(result , subs.getAmount(),0);
		
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(-1000, EUR10.negate().getAmount(), 0);
	}

	@Test
	public void testCompareTo() {
		assertEquals(-1, SEK100.compareTo(SEK200));
	}
}
