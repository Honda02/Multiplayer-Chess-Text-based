package it.uniba.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used for controlling and managing
 * the commands taken as input by the user
 */
public class Controller { // <<Control>>
	/**
	 * Class constructor
	 */
	Controller() {
	}

	// Pattern for the commands and move
	private static Matcher matcher;
	private static Pattern move = Pattern.compile("[TACDR][a-h][1-8]x[a-h][1-8]|[TACDR][1-8]x[a-h][1-8]|"
			+ "[TACDR][a-h]x[a-h][1-8]|[TACDR]x[a-h][1-8]|[TACDR][a-h][1-8]-[a-h][1-8]|"
			+ "[TACDR][1-8][a-h][1-8]|[TACDR][a-h][a-h][1-8]|[TACDR][a-h][1-8]|"
			+ "0-0|0-0-0|[a-h]x[a-h][1-8]|[a-h][1-8]");
	private static Pattern legal = Pattern.compile("legal-moves [a-h][1-8]");

	/**
	 * All the possible commands to take in input
	 */
	public enum Commands {
		PLAY {
			@Override
			public String toString() {
				return (String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "play",
						Ansi.RESET, "- Starts new match"));
			}
		},
		HELP {
			@Override
			public String toString() {
				return (String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "help",
						Ansi.RESET, "- Shows all commands"));
			}
		},
		BOARD {
			@Override
			public String toString() {
				return (String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "board",
						Ansi.RESET, "- Shows positions on board"));
			}
		},
		MOVES {
			@Override
			public String toString() {
				return (String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "moves",
						Ansi.RESET, "- Shows played moves"));
			}
		},
		CAPTURES {
			@Override
			public String toString() {
				return (String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "captures",
						Ansi.RESET, "- Shows all captures"));
			}
		},
		LEGAL_MOVES {
			@Override
			public String toString() {
				return (String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "legal-moves",
						Ansi.RESET,	"- Shows all player's legal moves"));
			}
		},
		LEGAL_SQUARE_MOVES {
			@Override
			public String toString() {
				return (String.format("%s %s%n %s%s%n", Ansi.GREEN, "legal-moves [square]",
						Ansi.RESET, "- Shows legal moves for the piece placed in the square")
						+ String.format("%s%n", Ansi.GREEN
						+ Ansi.BOLD + "   legal-moves e4"
						+ Ansi.RESET + " highlights the piece in e4 in "
						+ Ansi.YELLOW + " yellow"
                        + Ansi.RESET + ",") + String.format("%s%n%n", "   shows possible moves in "
                        + Ansi.GREEN + Ansi.BOLD + "green" + Ansi.RESET	+ " and captures in "
                        + Ansi.RED + Ansi.BOLD + "red" + Ansi.RESET));
			}
		},
		MATERIAL {
			@Override
			public String toString() {
				return (String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "material",
						Ansi.RESET, "- Shows the material score of the two players"));
			}
		},
		DRAW {
			@Override
			public String toString() {
				return (String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "draw",
						Ansi.RESET, "- Propose a draw"));
			}
		},
		RESIGN {
			@Override
			public String toString() {
				return (String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "resign",
						Ansi.RESET, "- Abandon the game"));
			}
		},
		QUIT {
			@Override
			public String toString() {
				return (String.format("%s %s%n %s%s%n%n", Ansi.GREEN, "quit",
						Ansi.RESET, "- Closes the game"));
			}
		},
		MOVE {
			@Override
			public String toString() {
				return "";
			}
		},
		NOT_RECOGNIZED {
			@Override
			public String toString() {
				return "";
			}
		},
	};

	/**
	 * Compares the input with all available commands of the application
	 * and returns the selected one, returning an error status if the
	 * command is not recognized
	 * @param command input command
	 * @return selected command or error
	 */
	public static Commands getCommand(final String command) {

		String newCommand = command.replaceAll("\\P{Print}", "");

		switch (newCommand.toUpperCase()) {
		case "PLAY":
			return Commands.PLAY;

		case "BOARD":
			return Commands.BOARD;

		case "CAPTURES":
			return Commands.CAPTURES;

		case "HELP":
			return Commands.HELP;

		case "MOVES":
			return Commands.MOVES;

		case "LEGAL-MOVES":
			return Commands.LEGAL_MOVES;

		case "MATERIAL":
			return Commands.MATERIAL;

		case "DRAW":
			return Commands.DRAW;

		case "RESIGN":
			return Commands.RESIGN;

		case "QUIT":
			return Commands.QUIT;

		default:
			matcher = move.matcher(newCommand);
			if (matcher.matches()) {
				return Commands.MOVE;
			}

			matcher = legal.matcher(newCommand.toLowerCase());
			if (matcher.matches()) {
				return Commands.LEGAL_SQUARE_MOVES;
			}

			return Commands.NOT_RECOGNIZED;
		}

	}

}
