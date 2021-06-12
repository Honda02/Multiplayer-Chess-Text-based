package it.uniba.main;

/**
 * This class is used to provide the background/foreground colors
 */
public class Ansi { // <<noECB>>
	/**
	 * Class constructor
	 */
	private Ansi() {
	}

	// Text color for console printing
	public static final String GREEN = "\u001b[32m";
	public static final String RED = "\u001b[31m";
	public static final String WHITE = "\u001b[37m";
	public static final String BLACK = "\u001b[30m";
	public static final String YELLOW = "\u001b[33m";
	public static final String RESET = "\u001b[0m";

	// Background color for the board printing
	public static final String LIGHT_SQUARE = "\u001b[47m";
	public static final String DARK_SQUARE = "\u001b[46m";

	// Background color for the board actions
	public static final String MOVE_DARK_SQUARE = "\u001b[42m";
	public static final String MOVE_LIGHT_SQUARE = "\u001b[0;102m";
	public static final String SPOT_SQUARE = "\u001b[0;103m";
	public static final String CAPTURE_SQUARE = "\u001b[0;101m";

	// Text style for console printing
	public static final String BOLD = "\u001b[1m";
}
