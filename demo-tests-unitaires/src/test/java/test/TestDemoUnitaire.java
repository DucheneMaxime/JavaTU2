package test;

import org.junit.Test;

import dev.utils.StringUtils;
import junit.framework.TestCase;

public class TestDemoUnitaire extends TestCase {

	public TestDemoUnitaire() {
		super();
	}

	/*
	 * @Test public void testCharNull(CharSequence lhs, CharSequence rhs) {
	 * assertNotNull(lhs); assertNotNull(rhs); }
	 */

	@Test
	public void testChat() {
		StringBuilder lhs = new StringBuilder("chat");
		StringBuilder rhs = new StringBuilder("chats");
		assertEquals(StringUtils.levenshteinDistance(lhs, rhs), 1);
	}

	@Test
	public void testMachine() {
		StringBuilder lhs = new StringBuilder("machins");
		StringBuilder rhs = new StringBuilder("machines");
		assertEquals(StringUtils.levenshteinDistance(lhs, rhs), 1);
	}

	@Test
	public void testAvion() {
		StringBuilder lhs = new StringBuilder("avion");
		StringBuilder rhs = new StringBuilder("aviron");
		assertEquals(StringUtils.levenshteinDistance(lhs, rhs), 1);
	}

	@Test
	public void testInstance() {
		StringBuilder lhs = new StringBuilder("distance");
		StringBuilder rhs = new StringBuilder("instance");
		assertEquals(StringUtils.levenshteinDistance(lhs, rhs), 2);
	}

	@Test
	public void testChien() {
		StringBuilder lhs = new StringBuilder("chien");
		StringBuilder rhs = new StringBuilder("chine");
		assertEquals(StringUtils.levenshteinDistance(lhs, rhs), 2);
	}

}
