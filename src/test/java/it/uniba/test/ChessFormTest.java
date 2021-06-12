package it.uniba.test;

import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;

import it.uniba.main.Ansi;
import it.uniba.main.ChessForm;
import it.uniba.main.ChessForm.STATUS;

@Tag("ChessForm")
public class ChessFormTest {
	private static ChessForm form = null;
	private static String moveNotation = null;
	private static ChessForm.STATUS status = null;

	@BeforeAll
	static void setUpAll() {
		System.out.println();
		System.out.println("--- CHESSFORM TEST ---");
		System.out.println("setUpAll()");
		moveNotation = "e4";
		status = STATUS.NORMAL;
	}

	@BeforeEach
	void setUp() {
		System.out.println("  setUp()");
		form = new ChessForm();
	}

	@Test
	@DisplayName("Testing printForm() method")
	public void testPrintForm() throws Exception {
		System.out.println("    testPrintForm()");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		form.printForm();
		String expectedOutput = Ansi.RED + " White hasn't moved" + Ansi.RESET;

		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	@DisplayName("Testing whiteWins() method")
	void testWhiteWins() {
		System.out.println("    testWhiteWins()");
		assumeNotNull(form);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		form.whiteWins();
		form.printForm();

		String expectedOutput = Ansi.RED + " White hasn't moved" + Ansi.RESET;
		expectedOutput += ("\n\n");
		expectedOutput += ("        " + Ansi.GREEN + "1" + Ansi.RESET + " - " + Ansi.RED + "0" + Ansi.RESET);

		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	@DisplayName("Testing blackWins() method")
	void testBlackWins() {
		System.out.println("    testBlackWins()");
		assumeNotNull(form);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		form.blackWins();
		form.printForm();

		String expectedOutput = Ansi.RED + " White hasn't moved" + Ansi.RESET;
		expectedOutput += ("\n\n");
		expectedOutput += ("        " + Ansi.RED + "0" + Ansi.RESET + " - " + Ansi.GREEN + "1" + Ansi.RESET);

		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	@DisplayName("Testing draw() method")
	void testDraw() {
		System.out.println("    testDraw()");
		assumeNotNull(form);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		form.draw();
		form.printForm();

		String expectedOutput = Ansi.RED + " White hasn't moved" + Ansi.RESET;
		expectedOutput += ("\n\n");
		expectedOutput += ("        ");
		expectedOutput += ('\u00BD');
		expectedOutput += (" - ");
		expectedOutput += ('\u00BD');

		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	@DisplayName("Testing addMove() method")
	void testAddMove() {
		System.out.println("    testAddMove()");
		assumeNotNull(form);
		assumeNotNull(moveNotation);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		form.addMove(moveNotation, status);
		form.printForm();
		String expectedOutput = "\n 1.  e4      ";

		assertEquals(expectedOutput, outContent.toString());
	}

	@AfterEach
	void tearDown() {
		System.out.println("  tearDown()");
	}

	@AfterAll
	static void tearDownAll() {
		System.out.println("tearDownAll()");
		System.out.println("--- CHESSFORM TEST ---");
		System.out.println();
		form = null;
		moveNotation = null;
	}
}
