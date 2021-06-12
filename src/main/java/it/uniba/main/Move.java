package it.uniba.main;

/**
 * This class defines the abstraction of a move in the chess game,
 * keeping track of the starting and finishing point of a move
 */
public class Move { // <<Entity>>

	public static final int POSITION_THREE = 3;
	public static final int POSITION_FOUR = 4;
	public static final int POSITION_FIVE = 5;
	public static final int POSITION_SIX = 6;
	public static final int RANK_SIZE = 8;

	// Move's position specification
	private int fromFile;
	private int fromRank;
	private int toFile;
	private int toRank;
	private String notation;

	/**
	 * Class constructor
	 * @param newFromFile	starting file
	 * @param newFromRank	starting rank
	 * @param newToFile		arrival file
	 * @param newToRank		arrival rank
	 * @param newNotation	move notation
	 */
	public Move(final int newFromFile, final int newFromRank, final int newToFile,
		 final int newToRank, final String newNotation) {
		super();
		this.fromFile = newFromFile;
		this.fromRank = newFromRank;
		this.toFile = newToFile;
		this.toRank = newToRank;
		this.notation = newNotation;
	}

	/**
	 * Class constructor from another move
	 * @param move move to copy
	 */
	Move(final Move move) {
		super();
		this.fromFile = move.getFromFile();
		this.fromRank = move.getFromRank();
		this.toFile = move.getToFile();
		this.toRank = move.getToRank();
		this.notation = move.getNotation();
	}

	/**
	 * Returns the starting file
	 * @return starting file
	 */
	public int getFromFile() {
		return fromFile;
	}

	/**
	 * Sets the starting file
	 * @param newFromFile	the new starting file
	 */
	public void setFromFile(final int newFromFile) {
		this.fromFile = newFromFile;
	}

	/**
	 * Returns the starting rank
	 * @return starting rank
	 */
	public int getFromRank() {
		return fromRank;
	}

	/**
	 * Sets the starting rank
	 * @param newFromRank the new starting rank
	 */
	public void setFromRank(final int newFromRank) {
		this.fromRank = newFromRank;
	}

	/**
	 * Returns the arrival file
	 * @return arrival file
	 */
	public int getToFile() {
		return toFile;
	}

	/**
	 * Sets the arrival file
	 * @param newToFile	the new arrival file
	 */
	public void setToFile(final int newToFile) {
		this.toFile = newToFile;
	}

	/**
	 * Returns the arrival rank
	 * @return arrival rank
	 */
	public int getToRank() {
		return toRank;
	}

	/**
	 * Sets the arrival rank
	 * @param newToRank the new arrival rank
	 */
	public void setToRank(final int newToRank) {
		this.toRank = newToRank;
	}

	/**
	 * Returns the arrival position on board
	 * @return arrival position on board
	 */
	public int getTo() {
		return toFile + toRank * RANK_SIZE;
	}

	/**
	 * Returns the starting position on board
	 * @return starting position on board
	 */
	public int getFrom() {
		return fromFile + fromRank * RANK_SIZE;
	}

	/**
	 * Returns the move notation
	 * @return starting position on board
	 */
	public String getNotation() {
		return notation;
	}

	/**
	 * Sets the move notation
	 * @param newNotation the new move notation
	 */
	public void setNotation(final String newNotation) {
		this.notation = newNotation;
	}

	/**
	 * It compares the notation of the move taken as input and
	 * compares it with its own, saved in extended notation.
	 * If the moves match returns true, false otherwise
	 * @param newNotation notation to compare with
	 * @return notations matches or not
	 */
	public boolean matches(final String newNotation) {
		int length = newNotation.length();

		if (length > this.notation.length()) {
			return false;
		}

		// EXTENDED NOTATIOH
		if (length == POSITION_SIX) {
			return (this.notation.contentEquals(newNotation));
		}

		// CASTELING
		if (newNotation.charAt(0) == '0') {
			return (this.notation.contentEquals(newNotation));
		}

		// PAWN MOVES
		if (newNotation.charAt(0) >= 'a' && newNotation.charAt(0) <= 'h') {
			return (this.notation.contentEquals(newNotation));
		}

		// GENERAL MOVE
		if (length == POSITION_THREE) {
			return ((newNotation.charAt(0) == this.notation.charAt(0)) // SAME Piece
					&& (newNotation.charAt(1) == this.notation.charAt(POSITION_FOUR)) // SAME toFile
					&& (newNotation.charAt(2) == this.notation.charAt(POSITION_FIVE))); // SAME Rank
		}

		// GENERAL CAPTURE OR SEMI-EXTENDED MOVE
		if (length == POSITION_FOUR) {
			if ((newNotation.charAt(0) == this.notation.charAt(0)) // SAME Piece
					&& (newNotation.charAt(2) == this.notation.charAt(POSITION_FOUR)) // SAME toFile
					&& (newNotation.charAt(POSITION_THREE)
                    == this.notation.charAt(POSITION_FIVE))) { // SAME toRank
				return (newNotation.charAt(1) == this.notation.charAt(1) // SAME fromFile
						|| newNotation.charAt(1) == this.notation.charAt(2) // SAME fromRank
						|| newNotation.charAt(1)
                        == this.notation.charAt(POSITION_THREE)); // SAME Capture
			}
		}

		// SEMI-EXTENDED CAPTURE
		if (length == POSITION_FIVE) {
			if ((newNotation.charAt(0) == this.notation.charAt(0)) // SAME Piece
					&& (newNotation.charAt(2)
					== this.notation.charAt(POSITION_THREE)) // SAME Capture (X)
					&& (newNotation.charAt(POSITION_THREE)
                    == this.notation.charAt(POSITION_FOUR)) // SAME toFile
					&& (newNotation.charAt(POSITION_FOUR)
                    == this.notation.charAt(POSITION_FIVE))) { // SAME toRank
				return (newNotation.charAt(1) == this.notation.charAt(1) // SAME fromFile
						|| newNotation.charAt(1) == this.notation.charAt(2)); // OR fromRank
			}
		}

		return false;
	}
}
