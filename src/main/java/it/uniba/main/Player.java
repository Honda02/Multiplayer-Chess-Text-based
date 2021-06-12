package it.uniba.main;

/**
 * This class defines the abstraction of a player in the chess game,
 * keeping track of the legal moves, the captured pieces and the available pieces
 */
final class Player { // <<Entity>>

	private static final int PIECE_PER_PLAYER = 16;

	// Player possible moves
	private Move[] moves;
	private int numMoves;

	// Player captures
	private Piece[] taken;
	private int numTaken;

	// Player pieces
	private Piece[] pieces;
	private int numPieces;

	/**
	 * Class constructor
	 */
	Player() {
		super();
		moves  = new Move[PIECE_PER_PLAYER * PIECE_PER_PLAYER];
		taken  = new Piece[PIECE_PER_PLAYER];
		pieces = new Piece[PIECE_PER_PLAYER];
		numMoves = 0;
		numTaken = 0;

        for (int i = 0; i < moves.length; i++) {
            moves[i] = new Move(0, 0, 0, 0, "");
        }
	}

	/**
	 * Returns the player's move from position i
	 * @param i	move index
	 * @return	the player's i-th move
	 */
	public Move getMove(final int i) {
		return moves[i];
	}

	/**
	 * Sets a move into the player's moves
	 * @param i		move index
	 * @param move	the player's i-th move
	 */
	public void setMove(final int i, final Move move) {
		this.moves[i] = move;
	}

	/**
	 * Returns the number of possible moves for the player
	 * @return number of moves
	 */
	public int getNumMoves() {
		return numMoves;
	}

	/**
	 * Sets the number of player moves
	 * @param newNumMoves	the number of player moves
	 */
	public void setNumMoves(final int newNumMoves) {
		this.numMoves = newNumMoves;
	}

	/**
	 * Returns the player's take from position i
	 * @param i	take index
	 * @return	the player's i-th capture
	 */
	public Piece getTake(final int i) {
		return taken[i];
	}

	/**
	 * Sets a take into the player's captures
	 * @param i		take index
	 * @param take	the player's i-th capture
	 */
	public void setTake(final int i, final Piece take) {
		this.taken[i] = take;
	}

	/**
	 * Returns the number of player captures
	 * @return number of captures
	 */
	public int getNumTaken() {
		return numTaken;
	}

	/**
	 * Sets the number of player captures
	 * @param newNumTaken	the number of player moves
	 */
	public void setNumTaken(final int newNumTaken) {
		this.numTaken = newNumTaken;
	}

	/**
	 * Returns the number of player pieces
	 * @return number of pieces
	 */
	public int getNumPieces() {
		return numPieces;
	}

	/**
	 * Sets the number of player pieces
	 * @param newNumPieces	the number of player pieces
	 */
	public void setNumPieces(final int newNumPieces) {
		this.numPieces = newNumPieces;
	}

	/**
	 * Returns the player's piece from position i
	 * @param i	piece index
	 * @return	the player's i-th piece
	 */
	public Piece getPiece(final int i) {
		return pieces[i];
	}

	/**
	 * Sets a piece into the player's pieces
	 * @param i		piece index
	 * @param piece	the player's i-th piece
	 */
	public void setPiece(final int i, final Piece piece) {
		this.pieces[i] = piece;
	}

	/**
	 * Returns all the player pieces
	 * @return	player pieces
	 */
	public Piece[] getPieces() {
		return pieces;
	}

	/**
	 * Sets all the player pieces
	 * @param newPieces	new player pieces
	 */
	public void setPieces(final Piece[] newPieces) {
		this.pieces = newPieces;
	}

	/**
	 * Returns all the player moves
	 * @return player moves
	 */
	public Move[] getMoves() {
		return moves;
	}

	/**
	 * Sets all the player moves
	 * @param newMoves	new player moves
	 */
	public void setMoves(final Move[] newMoves) {
		this.moves = newMoves;
	}

	/**
	 * Returns all the player captures
	 * @return player captures
	 */
	public Piece[] getTaken() {
		return taken;
	}

	/**
	 * Sets all the player captures
	 * @param newTaken	new player captures
	 */
	public void setTaken(final Piece[] newTaken) {
		this.taken = newTaken;
	}
}

