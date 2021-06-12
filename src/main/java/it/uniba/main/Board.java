package it.uniba.main;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;

/**
* This class defines the abstraction of a chessboard in the chess game,
* keeping track of the current state of the game on the board
*/
public class Board { // <<Entity>>
	private static final int PIECE_PER_PLAYER = 16;
	private static final int FR_SIZE = 8;
	private static final int WHITE_START_POSITION = 48;
	private static final int BLACK_START_POSITION = 0;
	private static final int POSITION_TWO = 2;
	private static final int POSITION_THREE = 3;
	private static final int POSITION_FOUR = 4;
	private static final int POSITION_FIVE = 5;
	private static final int POSITION_SEVEN = 7;

	private static final char[][] SYMBOLS = {{' ', '\u2654', '\u2655', '\u2656', '\u2657', '\u2658', '\u2659' },
			{' ', '\u265A', '\u265B', '\u265C', '\u265D', '\u265E', '\u265F' }};
	private static final int RANKS = 8;
	private static final int FILES = 8;
	private static final int BOARD_SIZE = 64;

	Piece[] board;
	Player white;
	Player black;

	Player player;
	Player opponent;

	private Piece.SIDE turn;
	private Move lastMove;

	/**
	 * Class constructor from another board
	 * @param origin board to copy
	 */
	Board(final Board origin) {
		this.board = new Piece[BOARD_SIZE];
		this.white = new Player();
		this.black = new Player();

		this.player = origin.player;
		this.opponent = origin.opponent;

		this.white.setNumMoves(origin.white.getNumMoves());
		this.white.setNumPieces(origin.white.getNumPieces());
		this.white.setNumTaken(origin.white.getNumTaken());
		System.arraycopy(origin.white.getPieces(), 0, this.white.getPieces(), 0, this.white.getNumPieces());
		System.arraycopy(origin.white.getTaken(), 0, this.white.getTaken(), 0, this.white.getNumTaken());

		this.black.setNumMoves(origin.black.getNumMoves());
		this.black.setNumPieces(origin.black.getNumPieces());
		this.black.setNumTaken(origin.black.getNumTaken());
		System.arraycopy(origin.black.getPieces(), 0, this.black.getPieces(), 0, this.black.getNumPieces());
		System.arraycopy(origin.black.getTaken(), 0, this.black.getTaken(), 0, this.black.getNumTaken());

		turn = origin.turn;
		lastMove = new Move(origin.lastMove);

		System.arraycopy(origin.board, 0, board, 0, BOARD_SIZE);

		for (int i = 0; i < this.white.getNumMoves(); i++) {
			this.white.setMove(i, new Move(origin.white.getMove(i)));
		}

		for (int i = 0; i < this.black.getNumMoves(); i++) {
			this.black.setMove(i, new Move(origin.black.getMove(i)));
		}
	}

	/**
	 * Class constructor
	 */
	public Board() {
		super();
		this.board = new Piece[BOARD_SIZE];
		this.white = new Player();
		this.black = new Player();

		this.player = this.white;
		this.opponent = this.black;

		lastMove = new Move(0, 0, 0, 0, "");
		turn = Piece.SIDE.WHITE;

		for (int i = 0; i < BOARD_SIZE; i++) {
			board[i] = new Piece(Piece.PIECE.EMPTY, Piece.SIDE.BLACK, false);
		}

		int i = BLACK_START_POSITION;
		board[i++] = new Piece(Piece.PIECE.ROOK, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.KNIGHT, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.BISHOP, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.QUEEN, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.KING, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.BISHOP, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.KNIGHT, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.ROOK, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.BLACK, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.BLACK, false);

        i = WHITE_START_POSITION;
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.PAWN, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.ROOK, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.KNIGHT, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.BISHOP, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.QUEEN, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.KING, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.BISHOP, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.KNIGHT, Piece.SIDE.WHITE, false);
		board[i++] = new Piece(Piece.PIECE.ROOK, Piece.SIDE.WHITE, false);

		listPossibleMoves();
	}

	/**
	 * Lists the possible moves for each player
	 * based on the position on the board
	 */
	public void listPossibleMoves() {
		List<Move> moves;

		moves = getMoves(Piece.SIDE.WHITE, true);
		white.setNumMoves(moves.size());
		for (int p = 0; p < white.getNumMoves(); p++) {
			white.setMove(p, new Move(moves.get(p)));
		}

		moves = getMoves(Piece.SIDE.BLACK, true);
		black.setNumMoves(moves.size());
		for (int o = 0; o < black.getNumMoves(); o++) {
			black.setMove(o, new Move(moves.get(o)));
		}
	}

	/**
	 * For the side player he scans the board and adds every legal move.
	 * If the checkKing flag is true, moves that
	 * expose the king to check are eliminated
	 * @param side		the player side
	 * @param checkKing flag for illegal king moves
	 * @return a list of all legal moves for the player
	 */
	private List<Move> getMoves(final Piece.SIDE side, final boolean checkKing) {
		List<Move> moves = new ArrayList<>();

		Piece[] pieces = new Piece[PIECE_PER_PLAYER];
		int numPieces = 0;

		for (int i = 0; i < BOARD_SIZE; i++) {

			// SKIP IF IS EMPTY OR IS NOT ON MY SIDE
			if (board[i].isEmpty() || board[i].getSide() != side) {
				continue;
			}

			Piece p = new Piece(board[i]);
			pieces[numPieces++] = p;

			int file = i % FR_SIZE;
			int rank = i / FR_SIZE;

			switch (board[i].getType()) {
			case PAWN:
				moves.addAll(getPawnMoves(file, rank));
				break;
			case ROOK:
				moves.addAll(getRookMoves(file, rank));
				break;
			case KNIGHT:
				moves.addAll(getKnightMoves(file, rank));
				break;
			case BISHOP:
				moves.addAll(getBishopMoves(file, rank));
				break;
			case QUEEN:
				moves.addAll(getQueenMoves(file, rank));
				break;
			case KING:
				moves.addAll(getKingMoves(file, rank));
				break;
			default:
				break;
			}
		}

		if (side == turn) {
			player.setPieces(pieces);
			player.setNumPieces(numPieces);
		} else {
			opponent.setPieces(pieces);
			opponent.setNumPieces(numPieces);
		}

		if (checkKing) {
			moves = dismissIllegalMoves(moves, side);
			moves = dismissIllegalCastling(moves, side);
		}

		return moves;
	}

	/**
	 * Get's all the legal moves for the pawn in the input position
	 * @param file the pawn file
	 * @param rank the pawn rank
	 * @return a list of all legal moves for the pawn
	 */
	private List<Move> getPawnMoves(final int file, final int rank) {
		List<Move> moves = new ArrayList<>();
		int pawnPos = file + rank * FR_SIZE;
		int forward;
        if (board[pawnPos].getSide() == Piece.SIDE.WHITE) {
            forward = -1;
        } else {
            forward = 1;
        }
		int ep1x;

		// SIMPLE PAWN MOVE
		addMoveIfValid(moves, file, rank, file, rank + forward);

		// DOUBLE PAWN MOVE
		if (!board[pawnPos].hasMoved()) {
			addMoveIfValid(moves, file, rank, file, rank + forward + forward);
		}

		// SIMPLE CAPTURE ON THE LEFT
		if (isValidSquare(file - 1, rank + forward)
			&& !board[((file - 1) + (rank + forward) * FR_SIZE)].isEmpty()) {
			addMoveIfValid(moves, file, rank, file - 1, rank + forward);
		}

		// SIMPLE CAPTURE ON THE RIGHT
		if (isValidSquare(file + 1, rank + forward)
			&& !board[((file + 1) + (rank + forward) * FR_SIZE)].isEmpty()) {
			addMoveIfValid(moves, file, rank, file + 1, rank + forward);
		}

		// EN-PASSANT ON THE LEFT
		ep1x = file - 1;
		if (isValidSquare(ep1x, rank)
				&& board[ep1x + rank * FR_SIZE].getSide() != board[pawnPos].getSide()) {
			if (lastMove.getToFile() == ep1x && lastMove.getToRank() == rank) {
				if (abs(lastMove.getFromRank() - lastMove.getToRank()) == 2) {
					if (board[ep1x + rank * FR_SIZE].getType() == Piece.PIECE.PAWN) {
						addMoveIfValid(moves, file, rank, ep1x, rank + forward);
					}
				}
			}
		}

		// EN-PASSANT ON THE RIGHT
		ep1x = file + 1;
		if (isValidSquare(ep1x, rank) && board[ep1x + rank * FR_SIZE].getSide() != board[pawnPos].getSide()) {
			if (lastMove.getToFile() == ep1x && lastMove.getToRank() == rank) {
				if (abs(lastMove.getFromRank() - lastMove.getToRank()) == 2) {
					if (board[ep1x + rank * FR_SIZE].getType() == Piece.PIECE.PAWN) {
						addMoveIfValid(moves, file, rank, ep1x, rank + forward);
					}
				}
			}
		}

		return moves;
	}

	/**
	 * Get's all the legal moves for the knight in the input position
	 * @param file the pawn knight
	 * @param rank the pawn knight
	 * @return a list of all legal moves for the knight
	 */
	private List<Move> getKnightMoves(final int file, final int rank) {
		List<Move> moves = new ArrayList<>();

		addMoveIfValid(moves, file, rank, file - 1, rank - 2);
		addMoveIfValid(moves, file, rank, file + 1, rank - 2);
		addMoveIfValid(moves, file, rank, file - 1, rank + 2);
		addMoveIfValid(moves, file, rank, file + 1, rank + 2);
		addMoveIfValid(moves, file, rank, file - 2, rank - 1);
		addMoveIfValid(moves, file, rank, file + 2, rank - 1);
		addMoveIfValid(moves, file, rank, file - 2, rank + 1);
		addMoveIfValid(moves, file, rank, file + 2, rank + 1);

		return moves;
	}

	/**
	 * Get's all the legal moves for the king in the input position
	 * @param file the pawn king
	 * @param rank the pawn king
	 * @return a list of all legal moves for the king
	 */
	private List<Move> getKingMoves(final int file, final int rank) {
		int kingPos = file + rank * FR_SIZE;
		List<Move> moves = new ArrayList<>();

		addMoveIfValid(moves, file, rank, file - 1, rank - 1);
		addMoveIfValid(moves, file, rank, file + 1, rank - 1);
		addMoveIfValid(moves, file, rank, file - 1, rank + 1);
		addMoveIfValid(moves, file, rank, file + 1, rank + 1);
		addMoveIfValid(moves, file, rank, file, rank - 1);
		addMoveIfValid(moves, file, rank, file, rank + 1);
		addMoveIfValid(moves, file, rank, file - 1, rank);
		addMoveIfValid(moves, file, rank, file + 1, rank);

		// KING SIDE CASTLING
		if (!board[kingPos].hasMoved() && board[kingPos + 1].isEmpty() && board[kingPos + 2].isEmpty()
				&& board[kingPos + POSITION_THREE].getType() == Piece.PIECE.ROOK
				&& !board[kingPos + POSITION_THREE].hasMoved()) {
			addMoveIfValid(moves, file, rank, file + 2, rank);
		}

		// KING SIDE CASTLING
		if (!board[kingPos].hasMoved() && board[kingPos - 1].isEmpty() && board[kingPos - 2].isEmpty()
				&& board[kingPos - POSITION_THREE].isEmpty()
				&& board[kingPos - POSITION_FOUR].getType() == Piece.PIECE.ROOK
				&& !board[kingPos - POSITION_FOUR].hasMoved()) {
			addMoveIfValid(moves, file, rank, file - 2, rank);
		}

		return moves;
	}

	/**
	 * Adds consecutives moves for the pieces Rook, Bishop, Queen
	 * @param moves list of piece moves
	 * @param file starting file
	 * @param rank starting rank
	 * @param x	arrival file
	 * @param y arrival rank
	 * @return true if the move is valid, false otherwise
	 */
	private boolean slider(final List<Move> moves, final int file, final int rank, final int x, final int y) {
		if (!isValidSquare(x, y)) {
			return false;
		}
		if (!board[x + y * FR_SIZE].isEmpty()) {
			if (board[file + rank * FR_SIZE].getSide() == board[x + y * FR_SIZE].getSide()) {
				return false;
			}
			addMoveIfValid(moves, file, rank, x, y);
			return false;
		}
		addMoveIfValid(moves, file, rank, x, y);
		return true;
	}

	/**
	 * Get's all the legal moves for the rook in the input position
	 * @param file the pawn rook
	 * @param rank the pawn rook
	 * @return a list of all legal moves for the rook
	 */
	private List<Move> getRookMoves(final int file, final int rank) {
		List<Move> moves = new ArrayList<>();

		for (int offset = 1; offset <= POSITION_SEVEN; offset++) {
			int x = file - offset;
			int y = rank;
			if (!slider(moves, file, rank, x, y)) {
				break;
			}
		}

		for (int offset = 1; offset <= POSITION_SEVEN; offset++) {
			int x = file + offset;
			int y = rank;
			if (!slider(moves, file, rank, x, y)) {
				break;
			}
		}

		for (int offset = 1; offset <= POSITION_SEVEN; offset++) {
			int x = file;
			int y = rank - offset;
			if (!slider(moves, file, rank, x, y)) {
				break;
			}
		}

		for (int offset = 1; offset <= POSITION_SEVEN; offset++) {
			int x = file;
			int y = rank + offset;
			if (!slider(moves, file, rank, x, y)) {
				break;
			}
		}

		return moves;
	}

	/**
	 * Get's all the legal moves for the bishop in the input position
	 * @param file the pawn bishop
	 * @param rank the pawn bishop
	 * @return a list of all legal moves for the bishop
	 */
	private List<Move> getBishopMoves(final int file, final int rank) {
		List<Move> moves = new ArrayList<>();

		for (int offset = 1; offset <= POSITION_SEVEN; offset++) {
			int x = file - offset;
			int y = rank - offset;
			if (!slider(moves, file, rank, x, y)) {
				break;
			}
		}

		for (int offset = 1; offset <= POSITION_SEVEN; offset++) {
			int x = file + offset;
			int y = rank - offset;
			if (!slider(moves, file, rank, x, y)) {
				break;
			}
		}

		for (int offset = 1; offset <= POSITION_SEVEN; offset++) {
			int x = file - offset;
			int y = rank + offset;
			if (!slider(moves, file, rank, x, y)) {
				break;
			}
		}

		for (int offset = 1; offset <= POSITION_SEVEN; offset++) {
			int x = file + offset;
			int y = rank + offset;
			if (!slider(moves, file, rank, x, y)) {
				break;
			}
		}

		return moves;
	}

	/**
	 * Get's all the legal moves for the queen in the input position
	 * @param file the pawn queen
	 * @param rank the pawn queen
	 * @return a list of all legal moves for the queen
	 */
	private List<Move> getQueenMoves(final int file, final int rank) {
		List<Move> moves;
		moves = getRookMoves(file, rank);
		moves.addAll(getBishopMoves(file, rank));
		return moves;
	}

	/**
	 * Returns if the position in input is legal or not
	 * @param file file value
	 * @param rank rank value
	 * @return true if is legal, false otherwise
	 */
	private static boolean isValidSquare(final int file, final int rank) {
		return (file >= 0) && (file <= POSITION_SEVEN) && (rank >= 0) && (rank <= POSITION_SEVEN);
	}

	/**
	 * Add the move to moves only if
	 * it's valid according to the rules of chess
	 * @param moves		list of piece moves
	 * @param fromFile	starting file
	 * @param fromRank	starting rank
	 * @param toFile	arrival file
	 * @param toRank	arrival rank
	 */
	private void addMoveIfValid(final List<Move> moves, final int fromFile, final int fromRank, final int toFile,
			final int toRank) {

		if (!isValidSquare(fromFile, fromRank)) {
			return;
		}

		if (!isValidSquare(toFile, toRank)) {
			return;
		}

		int fromPos = fromFile + fromRank * FR_SIZE;
		int toPos = toFile + toRank * FR_SIZE;
		int delta;
		String notation = "";

		Piece.PIECE pieceType = board[fromPos].getType();
		Piece.SIDE pieceSide = board[fromPos].getSide();

		if (!board[toPos].isEmpty()) {
			if (pieceSide == board[toPos].getSide()) {
				return;
			}
		}

		if (pieceType == Piece.PIECE.PAWN) {
			int forward;
			 if (pieceSide == Piece.SIDE.WHITE) {
		            forward = -1;
		        } else {
		            forward = 1;
		        }

			if (fromFile == toFile) {
				if (!board[toPos].isEmpty()) {
					return;
				}
				if (!board[fromFile + (fromRank + forward) * FR_SIZE].isEmpty()) {
					return;
				}
			}
		}

		switch (pieceType) {

		case PAWN:
			if (fromFile == toFile) {
				notation = String.format("%c%d", fromFile + 'a', FR_SIZE - toRank);
			} else {
				notation = String.format("%cx%c%d", fromFile + 'a', toFile + 'a', FR_SIZE - toRank);
			}
			break;

		case KING:
			delta = toFile - fromFile;

			if (delta == 2) {
				notation = "0-0";
			} else if (delta == -POSITION_TWO) {
				notation = "0-0-0";
			} else if (!board[toPos].isEmpty()) {
				notation = String.format("%c%c%dx%c%d", 'R', fromFile + 'a',
						   FR_SIZE - fromRank, toFile + 'a', FR_SIZE - toRank);
			} else {
				notation = String.format("%c%c%d-%c%d", 'R', fromFile + 'a',
						   FR_SIZE - fromRank, toFile + 'a', FR_SIZE - toRank);
			}
			break;

		case QUEEN:
			if (!board[toPos].isEmpty()) {
				notation = String.format("%c%c%dx%c%d", 'D', fromFile + 'a',
						   FR_SIZE - fromRank, toFile + 'a', FR_SIZE - toRank);
			} else {
				notation = String.format("%c%c%d-%c%d", 'D', fromFile + 'a',
						   FR_SIZE - fromRank, toFile + 'a', FR_SIZE - toRank);
			}
			break;

		case ROOK:
			if (!board[toPos].isEmpty()) {
				notation = String.format("%c%c%dx%c%d", 'T', fromFile + 'a',
						   FR_SIZE - fromRank, toFile + 'a', FR_SIZE - toRank);
			} else {
				notation = String.format("%c%c%d-%c%d", 'T', fromFile + 'a',
						   FR_SIZE - fromRank, toFile + 'a', FR_SIZE - toRank);
			}
			break;

		case BISHOP:
			if (!board[toPos].isEmpty()) {
				notation = String.format("%c%c%dx%c%d", 'A', fromFile + 'a',
						   FR_SIZE - fromRank, toFile + 'a', FR_SIZE - toRank);
			} else {
				notation = String.format("%c%c%d-%c%d", 'A', fromFile + 'a',
						   FR_SIZE - fromRank, toFile + 'a', FR_SIZE - toRank);
			}
			break;

		case KNIGHT:
			if (!board[toPos].isEmpty()) {
				notation = String.format("%c%c%dx%c%d", 'C', fromFile + 'a',
						   FR_SIZE - fromRank, toFile + 'a', FR_SIZE - toRank);
			} else {
				notation = String.format("%c%c%d-%c%d", 'C', fromFile + 'a',
						   FR_SIZE - fromRank, toFile + 'a', FR_SIZE - toRank);
			}
			break;

		default:
			break;
		}

		moves.add(new Move(fromFile, fromRank, toFile, toRank, notation));
	}

	/**
	 * Removes all moves that expose the king to check
	 * @param moves initial list of moves
	 * @param side	side of the player
	 * @return list of valid moves
	 */
	private List<Move> dismissIllegalMoves(final List<Move> moves, final Piece.SIDE side) {

		List<Move> valid = new ArrayList<>();
		for (Move move : moves) {
			Board current = new Board(this);
			current.executeMove(move);
			if (!current.kingInCheck(side)) {
				valid.add(new Move(move));
			}
		}
		return valid;
	}

	/**
	 * Removes illegal casteling
	 * @param moves initial list of moves
	 * @param side	side of the player
	 * @return list of valid moves
	 */
	private List<Move> dismissIllegalCastling(final List<Move> moves, final Piece.SIDE side) {
		List<Move> valid = new ArrayList<>();
		int castlingSide;

		for (Move move : moves) {

			if (move.getNotation().equals("0-0") || move.getNotation().equals("0-0-0")) {
				if (kingInCheck(side)) {
					continue;
				}
                if (move.getNotation().equals("0-0")) {
                    castlingSide = 1;
                } else {
                    castlingSide = -1;
                }
				if (spotIsAttacked(side, move.getFrom() + castlingSide)) {
					continue;
				}
			}

			valid.add(move);
		}
		return valid;
	}

	/**
	 * Performs the move and reports the consequences both
	 * on the board and in the data of the individual players
	 * @param move the move to be executed
	 */
	public void executeMove(final Move move) {
		int fromFile = move.getFromFile();
		int fromRank = move.getFromRank();
		int from = move.getFrom();

		int toFile = move.getToFile();
		int toRank = move.getToRank();
		int to = move.getTo();

		Piece.PIECE fromType = board[from].getType();
		Piece.PIECE toType = board[to].getType();

		// EN-PASSANT CAPTURE
		if (toType == Piece.PIECE.EMPTY && fromType == Piece.PIECE.PAWN && fromFile != toFile) {
			if (turn != Piece.SIDE.BLACK) {
				black.setTake(black.getNumTaken(),
				new Piece(Piece.PIECE.PAWN, Piece.SIDE.BLACK, false));
				black.setNumTaken(black.getNumTaken() + 1);
			} else {
				white.setTake(white.getNumTaken(),
				new Piece(Piece.PIECE.PAWN, Piece.SIDE.WHITE, false));
				white.setNumTaken(white.getNumTaken() + 1);
			}
			board[toFile + fromRank * FR_SIZE] = new Piece(Piece.PIECE.EMPTY, Piece.SIDE.BLACK, false);
		} else {
			if (toType != Piece.PIECE.EMPTY) {
				if (turn != Piece.SIDE.BLACK) {
					black.setTake(black.getNumTaken(), new Piece(toType, Piece.SIDE.BLACK, false));
					black.setNumTaken(black.getNumTaken() + 1);
				} else {
					white.setTake(white.getNumTaken(), new Piece(toType, Piece.SIDE.WHITE, false));
					white.setNumTaken(white.getNumTaken() + 1);
				}
			}
		}

		board[to] = new Piece(board[from]);
		board[from] = new Piece(Piece.PIECE.EMPTY, Piece.SIDE.BLACK, false);
		board[to].setMoved(true);

		if (fromType == Piece.PIECE.KING) {
			int delta = toFile - fromFile;
			if (abs(delta) == 2) {
				int rookFrom;
				int rookTo;

				if (delta < 0) {
					rookFrom = fromRank * FR_SIZE;
					rookTo = fromRank * FR_SIZE + POSITION_THREE;
				} else {
					rookFrom = fromRank * FR_SIZE + POSITION_SEVEN;
					rookTo = fromRank * FR_SIZE + POSITION_FIVE;
				}

				board[rookTo] = new Piece(board[rookFrom]);
				board[rookTo].setMoved(true);
				board[rookFrom] = new Piece(Piece.PIECE.EMPTY, Piece.SIDE.BLACK, false);
			}
		} else if (fromType == Piece.PIECE.PAWN) {
			if (toRank == 0 || toRank == POSITION_SEVEN) {
				board[to].setType(Piece.PIECE.QUEEN);
			}
		}

		lastMove = new Move(move);
	}

	/**
	 * Modify all the values necessary for the progress of the turn
	 */
	public void advanceTurn() {
		turn = Piece.getOpponent(turn);

		if (turn == Piece.SIDE.WHITE) {
			player = white;
			opponent = black;
		} else {
			player = black;
			opponent = white;
		}

		listPossibleMoves();
	}

	/**
	 * Returns if a player's king is in check
	 * @param side player's king side
	 * @return true if it's on check, false otherwise
	 */
	public boolean kingInCheck(final Piece.SIDE side) {
		Piece.SIDE newOpponent = Piece.getOpponent(side);
		List<Move> opponentMoves = getMoves(newOpponent, false);

		for (int i = 0; i < BOARD_SIZE; i++) {

			if (board[i].getType() != Piece.PIECE.KING || board[i].getSide() != side) {
				continue;
			}

			for (Move move : opponentMoves) {
				if (move.getTo() == i) {
					return true;
				}
			}

			break;
		}
		return false;
	}

	/**
	 * Returns if a spot is attacked from an opponent piece
	 * @param side player's spot side
	 * @param spot spot position
	 * @return true if it's under attack, false otherwise
	 */
	public boolean spotIsAttacked(final Piece.SIDE side, final int spot) {
		Piece.SIDE newOpponent = Piece.getOpponent(side);
		List<Move> opponentMoves = getMoves(newOpponent, false);

		for (Move move : opponentMoves) {
			if (move.getTo() == spot) {
				return true;
			}
		}

		return false;
	}

	// UTILITY FUNCTIONS
	/**
	 * Print the chessboard
	 */
	public void printBoard() {
		for (int r = 0; r < RANKS; r++) {
			System.out.print(Ansi.BOLD + Ansi.WHITE + " " + (FR_SIZE - r) + " " + Ansi.RESET);
			for (int f = 0; f < FILES; f++) {
                if ((f + r) % 2 == 0) {
                    System.out.print(Ansi.LIGHT_SQUARE);
                } else {
                    System.out.print(Ansi.DARK_SQUARE);
                }
				System.out.print(Ansi.BLACK);
				System.out.print(" " + getPieceSymbol(board[f + r * FR_SIZE]) + " ");
				System.out.print(Ansi.RESET);
			}
			System.out.println();
		}
		System.out.println(Ansi.BOLD + Ansi.WHITE + "    A  B  C  D  E  F  G  H" + Ansi.RESET);
	}

	/**
	 * Print the chessboard with the moves highlighted for the piece in from
	 * @param squares 	list of possible moves
	 * @param from		piece position
	 */
	public void printBoard(final List<Integer> squares, final int from) {
		for (int r = 0; r < RANKS; r++) {
			System.out.print(Ansi.BOLD + Ansi.WHITE + " " + (FR_SIZE - r) + " " + Ansi.RESET);
			for (int f = 0; f < FILES; f++) {
				if (f + r * FR_SIZE == from) {
					System.out.print(Ansi.SPOT_SQUARE);
				} else if (squares.contains(f + r * FR_SIZE)) {
					if (board[f + r * FR_SIZE].isEmpty()) {
                        if ((f + r) % 2 == 0) {
                            System.out.print(Ansi.MOVE_LIGHT_SQUARE);
                        } else {
                            System.out.print(Ansi.MOVE_DARK_SQUARE);
                        }
					} else {
						System.out.print(Ansi.CAPTURE_SQUARE);
					}
				} else {
                    if ((f + r) % 2 == 0) {
                    System.out.print(Ansi.LIGHT_SQUARE);
                } else {
                    System.out.print(Ansi.DARK_SQUARE);
                }
				}
				System.out.print(Ansi.BLACK);
				System.out.print(" " + getPieceSymbol(board[f + r * FR_SIZE]) + " ");
				System.out.print(Ansi.RESET);
			}
			System.out.println();
		}
		System.out.println(Ansi.BOLD + Ansi.WHITE + "    A  B  C  D  E  F  G  H" + Ansi.RESET);
	}

	/**
	 * Returns capture for the side's player
	 * @param side side of the player
	 * @return String with all the player's captures
	 */
	public String getCaptures(final Piece.SIDE side) {
		String captures = "";

		if (Piece.getOpponent(side) == Piece.SIDE.WHITE) {
			for (int w = 0; w < white.getNumTaken(); w++) {
				captures = captures.concat(getPieceSymbol(white.getTake(w)) + " ");
			}
		} else {
			for (int b = 0; b < black.getNumTaken(); b++) {
				captures = captures.concat(getPieceSymbol(black.getTake(b)) + " ");
			}
		}

		return captures;
	}

	/**
	 * Returns the symbol of a piece
	 * @param piece the piece from which to take the symbol
	 * @return piece symbol
	 */
	private static char getPieceSymbol(final Piece piece) {
		int side = piece.getSide().ordinal();
		int type = piece.getType().ordinal();

		return SYMBOLS[side][type];
	}

	/**
	 * Returns the symbol of a piece knowing the type and color
	 * @param side	color of the piece
	 * @param piece	type of the piece
	 * @return symbol of the piece
	 */
	public static char getPieceSymbol(final Piece.SIDE side, final Piece.PIECE piece) {

		return SYMBOLS[side.ordinal()][piece.ordinal()];
	}

	/**
	 * Returns the current turn
	 * @return the current turn
	 */
	public Piece.SIDE getTurn() {
		return turn;
	}
}
