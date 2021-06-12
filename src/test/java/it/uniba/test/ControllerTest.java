package it.uniba.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import it.uniba.main.Controller;
import it.uniba.main.Controller.Commands;
import it.uniba.main.Ansi;

@Tag("Controller")
public class ControllerTest {

	private static String command = null;

	@BeforeAll
	static void setUpAll() {
		System.out.println();
		System.out.println("--- CONTROLLER TEST ---");
		System.out.println("setUpAll()");
	}

	@BeforeEach
	void setUp() {
		System.out.println("  setUp()");
	}

	@Test
	@DisplayName("Testing getCommand() method for the PLAY case")
	void testGetCommandPlay() {
		System.out.println("    testGetCommandPlay()");
		String expectedOutput = String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "play", Ansi.RESET, "- Starts new match");
		command = "play";
		
		assertAll("      Testing Commands.PLAY and its toString method with lambdas", ()->{
			assertEquals(Commands.PLAY, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.PLAY.toString());
		});
	}

	@Test
	@DisplayName("Testing getCommand() method for the HELP case")
	void testGetCommandHelp() {
		System.out.println("    testGetCommandHelp()");
		String expectedOutput = String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "help", Ansi.RESET,
				"- Shows all commands");
		command = "help";
		assertAll("Testing getCommand() method for the ??? case",()->{
			assertEquals(Commands.HELP, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.HELP.toString());
		});
	}

	@Test
	@DisplayName("Testing getCommand() method for the BOARD case")
	void testGetCommandBoard() {
		System.out.println("    testGetCommandBoard()");
		String expectedOutput = String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "board", Ansi.RESET,
				"- Shows positions on board");

		command = "board";
		assertAll("      Testing getCommand() method for the BOARD case with lambdas",()->{
			assertEquals(Commands.BOARD, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.BOARD.toString());
		});
	}

	@Test
	@DisplayName("Testing getCommand() method for the MOVES case")
	void testGetCommandMoves() {
		System.out.println("    testGetCommandMoves()");
		String expectedOutput = String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "moves", Ansi.RESET,
				"- Shows played moves");
		command = "moves";
		//assertAll("      Testing getCommand method for the MOVES case with lambdas",()->{
			assertEquals(Commands.MOVES, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.MOVES.toString());
		//});
	}

	@Test
	@DisplayName("Testing getCommand() method for the CAPTURES case")
	void testGetCommandCaptures() {
		System.out.println("    testGetCommandCaptures()");
		String expectedOutput = String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "captures", Ansi.RESET,
				"- Shows all captures");
		command = "captures";
		assertAll("      Testing getCommand method for the CAPTURES case with lambdas",()->{
			assertEquals(Commands.CAPTURES, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.CAPTURES.toString());
		});
	}

	@Test
	@DisplayName("Testing getCommand() method for the LEGAL_MOVES case")
	void testGetCommandLegalMoves() {
		System.out.println("    testGetCommandLegalMoves()");
		String expectedOutput = String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "legal-moves", Ansi.RESET,
				"- Shows all player's legal moves");
		command = "legal-moves";
		assertAll("      Testing getCommand method for the LEGAL_MOVES case with lambdas",()->{
			assertEquals(Commands.LEGAL_MOVES, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.LEGAL_MOVES.toString());
		});
	}

	@Test
	@DisplayName("Testing getCommand() method for the LEGAL_SQUARE_MOVES case")
	void testGetCommandLegalSquareMoves() {
		System.out.println("    testGetCommandLegalSquareMoves()");
		String expectedOutput = Commands.LEGAL_SQUARE_MOVES.toString();
		command = "legal-moves e4";
		assertAll("      Testing getCommand() method for the LEGAL_SQUARE_MOVES case with lambdas",()->{
			assertEquals(Commands.LEGAL_SQUARE_MOVES, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.LEGAL_SQUARE_MOVES.toString());
		});
	}

	@Test
	@DisplayName("Testing getCommand() method for the MATERIAL case")
	void testGetCommandMaterial() {
		System.out.println("    testGetCommandMaterial()");
		String expectedOutput = String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "material", Ansi.RESET,
				"- Shows the material score of the two players");
		command = "material";
		assertAll("      Testing getCommand() method for the MATERIAL case with lambdas", ()->{
			assertEquals(Commands.MATERIAL, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.MATERIAL.toString());
		});
	}

	@Test
	@DisplayName("Testing getCommand() method for the DRAW case")
	void testGetCommandDraw() {
		System.out.println("    testGetCommandDraw()");
		String expectedOutput = String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "draw", Ansi.RESET, "- Propose a draw");
		command = "draw";
		assertAll("     Testing getCommand() method for the DRAW case with lambdas", ()->{
			assertEquals(Commands.DRAW, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.DRAW.toString());
		});
	}

	@Test
	@DisplayName("Testing getCommand() method for the RESIGN case")
	void testGetCommandResign() {
		String expectedOutput = String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "resign", Ansi.RESET,
				"- Abandon the game");
		System.out.println("    testGetCommandResign()");
		command = "resign";
		assertAll("     Testing getCommand() method for the RESIGN case with lambdas", ()->{
			assertEquals(Commands.RESIGN, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.RESIGN.toString());
		});
	}

	@Test
	@DisplayName("Testing getCommand() method for the QUIT case")
	void testGetCommandQuit() {
		System.out.println("    testGetCommandQuit()");
		String expectedOutput = String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "quit", Ansi.RESET, "- Closes the game");
		command = "quit";
		assertAll("     Testing getCommand() method for the QUIT case with lambdas", ()->{
			assertEquals(Commands.QUIT, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.QUIT.toString());
		});
	}

	@Test
	@DisplayName("Testing getCommand() method for the MOVE case")
	void testGetCommandMove() {
		System.out.println("    testGetCommandMove()");
		String expectedOutput = "";
		command = "e4";
		assertAll("     Testing getCommand() method for the MOVE case with lambdas", ()->{
			assertEquals(Commands.MOVE, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.MOVE.toString());
		});
	}

	@Test
	@DisplayName("Testing getCommand() method for the NOT_RECOGNIZED case")
	void testGetCommandNotRecognized() {
		System.out.println("    testGetCommandNotRecognized()");
		String expectedOutput = "";
		command = "invalid command";
		assertAll("     Testing getCommand() method for the NOT_RECOGNIZED case with lambdas", ()->{
			assertEquals(Commands.NOT_RECOGNIZED, Controller.getCommand(command));
			assertEquals(expectedOutput, Commands.NOT_RECOGNIZED.toString());
		});
	}

	@AfterEach
	void tearDown() {
		System.out.println("  tearDown()");
		command = null;
	}

	@AfterAll
	static void tearDownAll() {
		System.out.println("tearDownAll()");
		System.out.println("--- CONTROLLER TEST ---");
		System.out.println();
		command = null;
	}
}