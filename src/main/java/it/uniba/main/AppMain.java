package it.uniba.main;

import java.util.Scanner;

/**
 * This class is used for controlling and managing
 * the user interaction with the application
 */
public final class AppMain { // <<Boundary>>
	private AppMain() {
	}

	private static Scanner scanner = new Scanner(System.in, "UTF-8");
	private static Controller.Commands opCode;
/**
 * main method
 * @param args console args
 */
	public static void main(final String[] args) {
		Game chessgame = new Game();
		String command = "";
		boolean isPlaying = false;
		boolean isGameOver = false;
		introMessage();
		do {
			commandsMsg(isPlaying, chessgame.getTurn());
			command = scanner.nextLine();
			System.out.println(Ansi.RESET);
			opCode = Controller.getCommand(command);
			switch (opCode) {
			case PLAY:
				if ((isPlaying || isGameOver) && !areYouSure(" Are you sure you want to restart? ")) {
					break;
				}
				System.out.println();
				chessgame = new Game();
				chessgame.printPositionOnBoard();
				isPlaying = true;
				System.out.println();
				System.out.println(" Match started");
				System.out.println();
				break;

			case HELP:
				showAllCommands();
				break;

			case BOARD:
				if (isPlaying || isGameOver) {
					System.out.println();
					chessgame.printPositionOnBoard();
					System.out.println();
				} else {
					notPlaying();
				}
				break;

			case MOVES:
				if (isPlaying || isGameOver) {
					chessgame.printChessForm();
					System.out.println();
					System.out.println();
				} else {
					notPlaying();
				}
				break;

			case CAPTURES:
				if (isPlaying || isGameOver) {
					chessgame.printCaptures();
					System.out.println();
				} else {
					notPlaying();
				}
				break;

			case MOVE:
				if (isPlaying) {
					isGameOver = chessgame.move(command);
					if (isGameOver) {
						System.out.println();
						isPlaying = false;
						winningMessage(chessgame);
					}
				} else if (isGameOver) {
					matchIsOver();
				} else {
					notPlaying();
				}
				break;

			case LEGAL_MOVES:
				if (isPlaying) {
					chessgame.legalMoves();
					System.out.println();
				} else if (isGameOver) {
					matchIsOver();
				} else {
					notPlaying();
				}
				break;

			case LEGAL_SQUARE_MOVES:
				if (isPlaying) {
					chessgame.legalSquareMoves(command.split(" ")[1]);
					System.out.println();
				} else if (isGameOver) {
					matchIsOver();
				} else {
					notPlaying();
				}
				break;

			case MATERIAL:
				if (isPlaying || isGameOver) {
					chessgame.material();
					System.out.println();
				} else {
					notPlaying();
				}
				break;

			case DRAW:
				if (isPlaying) {
					acceptDrawMessage(chessgame.getTurn());
					if (areYouSure(" Do you accept the draw? ")) {
						isPlaying = false;
						isGameOver = true;
						chessgame.draw();
						drawMessage();
					}
				} else if (isGameOver) {
					matchIsOver();
				} else {
					notPlaying();
				}
				break;

			case RESIGN:
				if (isPlaying) {
					if (areYouSure(" Are you sure you want to resign? ")) {
						isPlaying = false;
						isGameOver = true;
						chessgame.resign();
						resignMessage(chessgame.getTurn());
					}
				} else if (isGameOver) {
					matchIsOver();
				} else {
					notPlaying();
				}
				break;

			case QUIT:
				opCode = areYouSure(" Are you sure you want to exit? ", Controller.Commands.QUIT);
				break;

			case NOT_RECOGNIZED:
				notRecognizedMessage(command);
				break;

			default:
				break;
			}
		} while (opCode != Controller.Commands.QUIT);
	}
/**
 * Shows request of a command
 * @param isPlaying state of the game
 * @param turn turn of the game
 */
	private static void commandsMsg(final boolean isPlaying, final Piece.SIDE turn) {
		System.out.print(" Next command");
		if (isPlaying) {
			if (turn == Piece.SIDE.WHITE) {
				System.out.print(" (White to move)");
			} else {
				System.out.print(" (Black to move)");
			}
		}
		System.out.print(": " + Ansi.GREEN);
	}
/**
 * Shows all game commands
 */
	private static void showAllCommands() {
		System.out.println();
		for (Controller.Commands info : Controller.Commands.values()) {
			System.out.print(info);
		}
		System.out.println();
	}
/**
 * Asks for user's confirmation
 * @param msg confirmation message
 * @param cmd confirmation command
 * @return confirmation command
 */
	private static Controller.Commands areYouSure(final String msg, final Controller.Commands cmd) {
		String answer = "";
		do {
			System.out.print(msg + "(" + Ansi.GREEN + "Y" + Ansi.RESET + "/");
			System.out.print(Ansi.RED + "N" + Ansi.RESET + "): " + Ansi.GREEN);
			if (scanner.hasNextLine()) {
				answer = scanner.nextLine().toUpperCase();
				System.out.println(Ansi.RESET);
				if (answer.equals("N")) {
					System.out.println();
					return Controller.Commands.NOT_RECOGNIZED;
				} else if (answer.equals("Y")) {
					System.out.println();
					return cmd;
				} else {
					System.out.println();
					System.out.println(Ansi.RED + " Invalid command" + Ansi.RESET);
				}
			}
		} while (!answer.equals("Y") && !answer.equals("N"));
		return Controller.Commands.NOT_RECOGNIZED;
	}
/**
 * Asks for user's confirmation
 * @param msg confirmation message
 * @return true if the user accepts, false otherwise
 */
	private static boolean areYouSure(final String msg) {
		String answer = "";
		do {
			System.out.print(msg + "(" + Ansi.GREEN + "Y" + Ansi.RESET + "/");
			System.out.print(Ansi.RED + "N" + Ansi.RESET + "): " + Ansi.GREEN);
			if (scanner.hasNextLine()) {
				answer = scanner.nextLine().toUpperCase();
				System.out.println(Ansi.RESET);
				if (answer.equals("N")) {
					return false;
				} else if (answer.equals("Y")) {
					return true;
				} else {
					System.out.println();
					System.out.println(Ansi.RED + " Invalid command" + Ansi.RESET);
				}
			}
		} while (!answer.equals("Y") && !answer.equals("N"));
		return false;
	}
/**
 * Shows an end game message 
 */
	private static void matchIsOver() {
		System.out.print(Ansi.RED + " The match you were playing is over");
		System.out.println(Ansi.RESET);
		System.out.print(" Type \"" + Ansi.GREEN + "play");
		System.out.println(Ansi.RESET + "\" to start another match");
		System.out.println();
	}
/**
 * Shows an error message if there is no game in progress
 */
	private static void notPlaying() {
		System.out.println(Ansi.RED + " You're not playing any match" + Ansi.RESET);
		System.out.println();
	}
/**
 * Shows game intro
 */
	private static void introMessage() {
		System.out.println();
		System.out.println(" Welcome to " + Ansi.BOLD + Ansi.WHITE + "STONEBRAKER'S CHESS GAME" + Ansi.RESET);
		System.out.println();
		System.out.println(" Type \"" + Ansi.GREEN + "play" + Ansi.RESET + "\" to start a new match");
		System.out.print(" Type \"" + Ansi.GREEN + "help" + Ansi.RESET);
		System.out.println("\" to view a list of available commands");
		System.out.println();
	}
/**
 * Shows match result
 * @param chessgame the game
 */
	private static void winningMessage(final Game chessgame) {
		if (!chessgame.kingInCheck(chessgame.getTurn())) {
			System.out.print(Ansi.BOLD + Ansi.GREEN);
			System.out.println(" The match is a draw" + Ansi.RESET);
		} else if (chessgame.getTurn() == Piece.SIDE.WHITE) {
			System.out.print(Ansi.BOLD + Ansi.GREEN);
			System.out.println(" Black wins!" + Ansi.RESET);
		} else {
			System.out.print(Ansi.BOLD + Ansi.GREEN);
			System.out.println(" White wins!" + Ansi.RESET);
		}
		System.out.print(" Type \"" + Ansi.GREEN + "play");
		System.out.println(Ansi.RESET + "\" to start another match");
		System.out.println();
	}
/**
 * Shows match result as draw
 */
	private static void drawMessage() {
		System.out.print(Ansi.BOLD + Ansi.GREEN);
		System.out.println(" The match is a draw" + Ansi.RESET);
		System.out.print(" Type \"" + Ansi.GREEN + "play");
		System.out.println(Ansi.RESET + "\" to start another match");
		System.out.println();
	}
/**
 * Shows "side" as winner after resign
 * @param side player side
 */
	private static void resignMessage(final Piece.SIDE side) {
		if (side == Piece.SIDE.WHITE) {
			System.out.print(Ansi.BOLD + Ansi.GREEN);
			System.out.println(" Black wins!" + Ansi.RESET);
		} else {
			System.out.print(Ansi.BOLD + Ansi.GREEN);
			System.out.println(" White wins!" + Ansi.RESET);
		}
		System.out.print(" Type \"" + Ansi.GREEN + "play");
		System.out.println(Ansi.RESET + "\" to start another match");
		System.out.println();
	}
/**
 * Shows an error message for unknown commands
 * @param command unknown command
 */
	private static void notRecognizedMessage(final String command) {
		System.out.print(Ansi.RED + " Command: \"" + command.trim());
		System.out.println("\" not recognized" + Ansi.RESET);
		System.out.println();
	}
/**
 * Shows a draw proposal by "side" player
 * @param side player side
 */
	private static void acceptDrawMessage(final Piece.SIDE side) {
		if (side == Piece.SIDE.WHITE) {
			System.out.print(" White");
		} else {
			System.out.print(" Black");
		}
		System.out.println(" player is offering a draw.");
	}
}
