package it.uniba.test;

import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

import it.uniba.main.Board;
import it.uniba.main.Piece;
import it.uniba.main.Move;

@Tag("Board")
public class BoardTest {

	private static Board board;

	@BeforeAll
	static void setUpAll() throws Exception {
		System.out.println();
		System.out.println("--- BOARD TEST ---");
		System.out.println("setUpAll()");
	}

	@BeforeEach
	void setUp() {
		System.out.println("  setUp()");
		board = new Board();
	}

	@Test
	@DisplayName("Testing getCaptures() method")
	void testGetCaptures() {
		System.out.println("    testGetCaptures()");
			assertAll("      Check the correct output for getCaptures()",()->{
			assertEquals("", board.getCaptures(Piece.SIDE.WHITE));
			Move firstMove = new Move(4, 6, 4, 4, "e4");
			board.executeMove(firstMove);
			board.advanceTurn();
			Move secondMove = new Move(3, 1, 3, 3, "d5");
			board.executeMove(secondMove);
			board.advanceTurn();
			Move firstCapture = new Move(4, 4, 3, 3, "exd5");
			board.executeMove(firstCapture);
			board.advanceTurn();
			assertEquals("\u265F ", board.getCaptures(Piece.SIDE.WHITE));
		});
	}

	@Test
	@DisplayName("Testing getTurn() method")
	void testGetTurn() {
		assumeNotNull(board);
		assertEquals(Piece.SIDE.WHITE, board.getTurn());
	}


	@Test
	@DisplayName("Testing advanceTurn() method")
	void testAdvanceTurn() {
		System.out.println("    testAdvanceTurn()");
		assertAll("      Advance turns with lambdas",() ->{
			assertEquals(Piece.SIDE.WHITE, board.getTurn());
			board.advanceTurn();
			assertEquals(Piece.SIDE.BLACK, board.getTurn());
			board.advanceTurn();
			assertEquals(Piece.SIDE.WHITE, board.getTurn());
		});
	}

	@Test
	@DisplayName("Testing spotIsAttacked() method")
	void testSpotIsAttacked() {
		System.out.println("    testSpotIsAttacked()");
		assertAll("      Check if spot is attacked with lambdas",()->{
			assertFalse(board.spotIsAttacked(Piece.SIDE.BLACK, 1));
			assertTrue(board.spotIsAttacked(Piece.SIDE.BLACK, 40));
		}
		);
	}

	@AfterEach
	void tearDown() {
		System.out.println("  tearDown()");
		board = null;
	}

	@AfterAll
	static void tearDownAll() {
		System.out.println("tearDownAll()");
		System.out.println("--- BOARD TEST ---");
		System.out.println();
	}

}
