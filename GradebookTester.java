import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradebookTester {

	GradeBook g1, g2;

	@BeforeEach
	void setUp() throws Exception {
		g1 = new GradeBook(5);
		g1.addScore(2);
		g1.addScore(4);
		g1.addScore(6);
		g1.addScore(8);
		g1.addScore(10);


		g2 = new GradeBook(5);
		g2.addScore(1);
		g2.addScore(3);
		g2.addScore(5);
		g2.addScore(7);
		g2.addScore(9);

	}

	@AfterEach
	void tearDown() throws Exception {
		g1 = null;
		g2 = null;
	}

	@Test
	public void testSum() {
		assertEquals(30, g1.sum(), .0001);
		assertEquals(25, g2.sum(), .0001);
	}
	
	@Test
	public void testMinimum() {
		assertEquals(2, g1.minimum(), .001);
		assertEquals(1, g2.minimum(), .001);
	}

	@Test
	public void addScoreTest() {
		assertTrue(g1.toString().equalsIgnoreCase("2.0 4.0 6.0 8.0 10.0 "));
		assertTrue(g2.toString().equalsIgnoreCase("1.0 3.0 5.0 7.0 9.0 "));
	}
	
	@Test
	public void finalScoreTest() {
		assertEquals(28, g1.finalScore(), .0001);
		assertEquals(24, g2.finalScore(), .0001);

	}

}
