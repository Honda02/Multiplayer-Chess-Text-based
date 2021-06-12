package it.uniba.test;

import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import it.uniba.main.Move;

@Tag("Move")
public class MoveTest {
	private static Move pawnMove = null;
	private static Move pieceMove = null;
	private static Move pawnCapture = null;
	private static Move pieceCapture = null;
	private static Move shortCastle = null;
	private static Move longCastle = null;

	@BeforeAll
	static void setUpAll() throws Exception {
		System.out.println();
		System.out.println("--- MOVE TEST ---");
		System.out.println("setUpAll()");
	}

	@BeforeEach
	void setUp() {
		System.out.println("  setUp()");
		pawnMove = new Move(0, 0, 0, 0, "e4");
		pieceMove = new Move(0, 0, 0, 0, "Ta1-a8");
		pawnCapture = new Move(0, 0, 0, 0, "exd4");
		pieceCapture = new Move(0, 0, 0, 0, "Ta1xa8");
		shortCastle = new Move(0, 0, 0, 0, "0-0");
		longCastle = new Move(0, 0, 0, 0, "0-0-0");
	}


	@Test
	@DisplayName("Testing setFromRank() method")
	void testSetFromRank(){
		assumeNotNull(pawnMove);
		pawnMove.setFromRank(2);
		assertEquals(2,pawnMove.getFromRank());
	}

	@Test
	@DisplayName("Testing setFromFile() method")
	void testSetFromFile(){
		assumeNotNull(pawnMove);
		pawnMove.setFromFile(2);
		assertEquals(2,pawnMove.getFromFile());
	}

	@Test
	@DisplayName("Testing setToRank() method")
	void testSetToRank(){
		assumeNotNull(pawnMove);
		pawnMove.setToRank(2);
		assertEquals(2,pawnMove.getToRank());
	}

	@Test
	@DisplayName("Testing setToFile() method")
	void testSetToFile(){
		assumeNotNull(pawnMove);
		pawnMove.setToFile(2);
		assertEquals(2,pawnMove.getToFile());
	}

	@Test
	@DisplayName("Testing setNotation() method")
	void testSetNotation(){
		assumeNotNull(pawnMove);
		pawnMove.setNotation("e4");
		assertEquals("e4",pawnMove.getNotation());
	}

	@Test
	@DisplayName("Testing matches() method for pawn moves")
	void testMatchesPawn() {
		System.out.println("    testMatchesPawn()");
		assumeNotNull(pawnMove);
		assertAll("      Testingg matches() method for pawn moves with lambdas", ()->{
			assertTrue(pawnMove.matches("e4"));
			assertFalse(pawnMove.matches("z4"));
			assertFalse(pawnMove.matches("Ce4"));
		});
	}

	@Test
	@DisplayName("Testing matches() method for piece moves")
	void testMatchesPiece() {
		System.out.println("    testMatchesPiece()");
		assumeNotNull(pieceMove);
		assertAll("      Testing matches() method for piece moves with lambdas",()->{
			assertTrue(pieceMove.matches("Ta8"));
			assertTrue(pieceMove.matches("Taa8"));
			assertTrue(pieceMove.matches("T1a8"));
			assertFalse(pieceMove.matches("e4"));
			assertFalse(pieceMove.matches("Txa8"));
		});
	}

	@Test
	@DisplayName("Testing matches() method for pawn captures")
	void testMatchesPawnCaptures() {
		System.out.println("    testMatchesPawnCaptures()");
		assumeNotNull(pawnCapture);
		assertAll("      Testing matches() method for pawn captures",()->{
			assertTrue(pawnCapture.matches("exd4"));
			assertFalse(pawnCapture.matches("Ta2"));
		});
	}

	@Test
	@DisplayName("Testing matches() method for piece captures")
	void testMatchesPieceCaptures() {
		System.out.println("    testMatchesPieceCaptures()");
		assumeNotNull(pieceCapture);
		assertAll("",()->{
			assertTrue(pieceCapture.matches("Ta1xa8"));
			assertTrue(pieceCapture.matches("T1xa8"));
			assertTrue(pieceCapture.matches("Taxa8"));
			assertTrue(pieceCapture.matches("Txa8"));
			assertFalse(pieceCapture.matches("Tb6"));
		});
	}

	@Test
	@DisplayName("Testing matches() method for short castling moves")
	void testMatchesShortCastle() {
		System.out.println("    testMatchesPieceCaptures()");
		assumeNotNull(shortCastle);
		assertAll("",()->{
			assertTrue(shortCastle.matches("0-0"));
			assertFalse(shortCastle.matches("0-0-0"));
			assertFalse(shortCastle.matches("Ab2"));
		});
	}

	@Test
	@DisplayName("Testing matches() method for long castling")
	void testMatchesLongCastle() {
		System.out.println("    testMatchesLongCastle()");
		assumeNotNull(longCastle);
		assertAll("",()->{
			assertTrue(longCastle.matches("0-0-0"));
			assertFalse(longCastle.matches("0-0"));
			assertFalse(longCastle.matches("Ab2"));
		});
	}

	@AfterEach
	void tearDown() {
		System.out.println("  tearDown()");
		pawnMove = new Move(0, 0, 0, 0, "e4");
		pieceMove = new Move(0, 0, 0, 0, "Ta1-a8");
		pawnCapture = new Move(0, 0, 0, 0, "exd4");
		pieceCapture = new Move(0, 0, 0, 0, "Ta1xa8");
		shortCastle = new Move(0, 0, 0, 0, "0-0");
		longCastle = new Move(0, 0, 0, 0, "0-0-0");
	}

	@AfterAll
	static void tearDownAll() {
		System.out.println("tearDownAll()");
		System.out.println("--- MOVE TEST ---");
		System.out.println();
		pawnMove = null;
		pieceMove = null;
		pawnCapture = null;
		pieceCapture = null;
		shortCastle = null;
		longCastle = null;
	}

}
