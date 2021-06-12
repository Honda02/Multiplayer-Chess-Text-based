package it.uniba.main;

/**
 * This class defines the abstraction of a form in the chess game,
 * keeping track of the moves made and the outcome of the game
 */
public class ChessForm { // <<Entity>>

	public static final int MOVE_SIZE = 9;

	/**
	 * All the possible states of the king
	 */
	public enum STATUS {
		NORMAL, CHECK, CHECKMATE
	};

	// ChessForm values
	private String form;
	private int move;
	private int counter;

	/**
	 * Class constructor
	 */
	public ChessForm() {
		super();
		move = 1;
		form = Ansi.RED + " White hasn't moved" + Ansi.RESET;
		counter = 1;
	}

	/**
	 * Inserts a new move into the game form making sure
	 * to note down the check and checkmate
	 * @param nextMove	the move to add
	 * @param status	the status of the opponent king
	 */
	public void addMove(final String nextMove, final STATUS status) {
		if (move == 1 && counter == 1) {
			form = ("");
		}

		String newNextMove = nextMove;
		if (newNextMove.charAt(0) >= 'a' && newNextMove.charAt(0) <= 'h') {
			if (newNextMove.charAt(newNextMove.length() - 1) == '1'
			    || newNextMove.charAt(newNextMove.length() - 1) == '8') {
				newNextMove += "=D";
			}
		}

		if (counter == 1) {
			form += ("\n ");
			form += (Integer.toString(move));
			form += (". ");
			if (move <= MOVE_SIZE) {
				form += (" ");
			}

			if (status == STATUS.CHECK) {
				newNextMove += ('+');
			}
			if (status == STATUS.CHECKMATE) {
				newNextMove += ('#');
			}
			form += (String.format("%-7s", newNextMove));

			form += (" ");
			counter++;

		} else if (counter == 2) {
			if (status == STATUS.CHECK) {
				newNextMove += ('+');
			}
			if (status == STATUS.CHECKMATE) {
				newNextMove += ('#');
			}
			form += (String.format("%-7s", newNextMove));

			move++;
			counter = 1;
		}
	}

	/**
	 * Write down a draw as a result of the match
	 */
	public void draw() {
		form += ("\n\n");
		form += ("        ");
		form += ('\u00BD');
		form += (" - ");
		form += ('\u00BD');
	}

	/**
	 * Write down the white's win as a result of the match
	 */
	public void whiteWins() {
		form += ("\n\n");
		form += ("        " + Ansi.GREEN + "1" + Ansi.RESET + " - " + Ansi.RED + "0" + Ansi.RESET);
	}

	/**
	 * Write down the black's win as a result of the match
	 */
	public void blackWins() {
		form += ("\n\n");
		form += ("        " + Ansi.RED + "0" + Ansi.RESET + " - " + Ansi.GREEN + "1" + Ansi.RESET);
	}

	/**
	 * Prints the chess form
	 */
	public void printForm() {
		System.out.print(form);
	}
}
