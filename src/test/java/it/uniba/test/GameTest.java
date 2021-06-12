package it.uniba.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;

import it.uniba.main.Game;

@Tag("Game")
public class GameTest {

	public static Game game = null;

	@BeforeAll
	static void setUpAll() throws Exception {
		System.out.println();
		System.out.println("--- GAME TEST ---");
		System.out.println("setUpAll()");
	}

	@BeforeEach
	void setUp() {
		System.out.println("  setUp()");
		game = new Game();
	}

	@Test
	@DisplayName("Testing material() method")
	void testMaterial() {
		System.out.println("    testMaterial()");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		game.material();

		String expectedOutput = outContent.toString();
		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	@DisplayName("Testing move() method")
	void testMove() {
		System.out.println("    testMove()");
		assertAll("      Testing move() method with lambdas", ()->{
			assertFalse(game.move("e4"));
			assertFalse(game.move("e4"));
		});
	}

	@Test
	@DisplayName("Testing move() method when king is in check")
	void testMoveCheck() {
		System.out.println("    testMoveCheck()");
		assertAll("      Testing move() method when king is in check with lambdas", ()->{
			assertFalse(game.move("e4"));
			assertFalse(game.move("d5"));
			assertFalse(game.move("Bb5"));
		});

	}

	@Test
	@DisplayName("Testing move() method when king is in checkmate")
	void testMoveCheckMate() {
		System.out.println("    testMoveCheckMate()");
		assertAll("      Testing move() method when king is in checkmate with lambdas",()->{
			assertFalse(game.move("e4"));
			assertFalse(game.move("f6"));
			assertFalse(game.move("d4"));
			assertFalse(game.move("g5"));
			assertTrue(game.move("Dh5"));
		});

	}

	@Test
	@DisplayName("Testing move() method when move is ambiguous")
	void testMoveAmbiguousMove() {
		System.out.println("    testMoveAmbiguousMove()");
		assertAll("      Testing move() method when is ambiguous with lambdas",()->{
			assertFalse(game.move("Cf3"));
			assertFalse(game.move("f5"));
			assertFalse(game.move("d4"));
			assertFalse(game.move("e5"));
			assertFalse(game.move("Cd2"));
		});
	}

	@Test
	@DisplayName("Testing printCaptures() method")
	void testPrintCaptures() {
		System.out.println("    testPrintCaptures()");
		game.move("e4");
		game.move("d5");
		game.move("exd5");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		game.printCaptures();

		String expectedOutput = outContent.toString();
		assertEquals(expectedOutput,outContent.toString());
	}

	@AfterEach
	void tearDown() {
		System.out.println("  tearDown()");
		game = null;
	}

	@AfterAll
	static void tearDownAll() {
		System.out.println("tearDownAll()");
		System.out.println("--- GAME TEST ---");
		System.out.println();
	}

}
