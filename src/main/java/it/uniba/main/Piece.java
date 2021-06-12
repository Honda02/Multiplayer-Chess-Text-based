package it.uniba.main;

/**
 * This class defines the abstraction of a piece in the chess game,
 * keeping track of its type, its color and whether it has moved previously
 */
public class Piece { // <<Entity>>
	/**
	 *  All types of pieces that can be found on the board
	 */
	public enum PIECE {
		EMPTY, KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN
	};

	/**
	 *	The colors of the pieces of the two opponents
	 */
	public enum SIDE {
		WHITE, BLACK
	};

	/**
	 * Returns the opposite side to the input side
	 * @param side	the actual side
	 * @return		the opposite side
	 */
	public static SIDE getOpponent(final SIDE side) {
        if (side == SIDE.WHITE) {
            return  SIDE.BLACK;
        } else {
            return  SIDE.WHITE;
        }
	}

	// Piece values
	private PIECE type;
	private SIDE side;
	private boolean moved;

	/**
	 * Class constructor
	 * @param newType	piece type
	 * @param newSide	piece colour
	 * @param newMoved	if the piece has moved
	 */
	Piece(final PIECE newType, final SIDE newSide, final boolean newMoved) {
		super();
		this.type = newType;
		this.side = newSide;
		this.moved = newMoved;
	}

	/**
	 * Class constructor from another piece
	 * @param piece piece to copy
	 */
	Piece(final Piece piece) {
		super();
		this.type = piece.type;
		this.side = piece.side;
		this.moved = piece.moved;
	}

	/**
	 * Returns the piece type
	 * @return piece type
	 */
	public PIECE getType() {
		return type;
	}

	/**
	 * Sets the piece type
	 * @param newType	the new piece type
	 */
	public void setType(final PIECE newType) {
		this.type = newType;
	}

	/**
	 * Returns the piece side
	 * @return piece side
	 */
	public SIDE getSide() {
		return side;
	}

	/**
	 * Sets the piece side
	 * @param newSide	the new piece side
	 */
	public void setSide(final SIDE newSide) {
		this.side = newSide;
	}

	/**
	 * Returns if the piece has moved
	 * @return piece has moved
	 */
	public boolean hasMoved() {
		return moved;
	}

	/**
	 * Sets the piece movement status
	 * @param newMoved	the new movement status
	 */
	public void setMoved(final boolean newMoved) {
		this.moved = newMoved;
	}

	/**
	 * Returns if the piece is not filled
	 * @return type is empty
	 */
	public boolean isEmpty() {
		return this.getType() == PIECE.EMPTY;
	}
}
