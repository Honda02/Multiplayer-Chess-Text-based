package it.uniba.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used for controlling and managing the progress of a chess game
 */
public class Game { // <<Control>>

	public static final int ROOK_VALUE = 5;
	public static final int BK_VALUE = 3;
	public static final int QUEEN_VALUE = 9;
	public static final int RANK_SIZE = 8;

	private Board board;
	private ChessForm form;

	/**
	 * Class constructor
	 */
	public Game() {
		this.board = new Board();
		this.form = new ChessForm();
	}

	/**
	 * It compares the notation taken as input with all the moves of
	 * the player and if it matches it executes it, otherwise it prints
	 * an error on the screen. If the move ends the game true, false otherwise
	 * @param notation input notation
	 * @return if the move ends the game true, false otherwise
	 */
	public boolean move(final String notation) {
		Move plMove = null;
		int counter = 0;

		String newNotation = notation.replace('O', '0');

		for (int i = 0; i < board.player.getNumMoves(); i++) {
			if (board.player.getMove(i).matches(newNotation)) {
				plMove = new Move(board.player.getMove(i));
				counter++;
			}
		}

		if (counter == 0) {
			System.out.println(Ansi.RED + " Illegal move" + Ansi.RESET);
			System.out.println();
		} else if (counter == 1) {
			board.executeMove(plMove);
			board.advanceTurn();
			boolean kingCheck = board.kingInCheck(board.getTurn());
			if (board.player.getNumMoves() == 0) {
				if (kingCheck) {
					form.addMove(newNotation, ChessForm.STATUS.CHECKMATE);
				} else {
					form.addMove(newNotation, ChessForm.STATUS.NORMAL);
				}

				if (kingCheck) {
					if (board.getTurn() == Piece.SIDE.WHITE) {
						form.blackWins();
					} else {
						form.whiteWins();
					}
				} else {
					form.draw();
				}
				return true;
			} else {
				if (kingCheck) {
					form.addMove(newNotation, ChessForm.STATUS.CHECK);
				} else {
					form.addMove(newNotation, ChessForm.STATUS.NORMAL);
				}
			}
		} else {
			System.out.println(Ansi.RED + " Ambiguous move" + Ansi.RESET);
			System.out.println();
		}
		return false;
	}

	/**
	 * Prints the board
	 */
	public void printPositionOnBoard() {
		board.printBoard();
	}

	/**
	 * Prints the chess form
	 */
	public void printChessForm() {
		form.printForm();
	}

	/**
	 * Prints the captures for each player
	 */
	public void printCaptures() {
		if (board.white.getNumTaken() + board.black.getNumTaken() != 0) {
			System.out.print(" These are the captures made by White Player: ");
			System.out.println();
			System.out.print(" " + board.getCaptures(Piece.SIDE.WHITE));
			System.out.println();
			System.out.println();
			System.out.print(" These are the captures made by Black Player: ");
			System.out.println();
			System.out.print(" " + board.getCaptures(Piece.SIDE.BLACK));
			System.out.println();
		} else {
			System.out.println(" No capture has been made");
			System.out.println();
		}
	}

	/**
	 * Returns the current turn side
	 * @return current turn side
	 */
	public Piece.SIDE getTurn() {
		return board.getTurn();
	}

	/**
	 * Prints a list of all possible moves for the current player
	 */
	public void legalMoves() {
		String[] moves;
		moves = new String[board.player.getNumMoves()];

		for (int i = 0; i < board.player.getNumMoves(); i++) {
			moves[i] = board.player.getMove(i).getNotation();
		}

		Arrays.sort(moves);

		if (getTurn() == Piece.SIDE.WHITE) {
			System.out.println(" WHITE PLAYER'S MOVES");
		} else {
			System.out.println(" BLACK PLAYER'S MOVES");
		}
		System.out.println();

		for (int i = 0; i < moves.length; i++) {
			System.out.println(String.format(" %2d. %-7s", i + 1, moves[i]));
		}
	}

	/**
	 *  Prints a list of all possible moves for the piece placed in the square,
	 *  highlights the piece in yellow, shows possible moves in green
	 *  and captures in red
	 * @param notation the square position
	 */
	public void legalSquareMoves(final String notation) {
		int square = (notation.charAt(0) - 'a')
		+ (RANK_SIZE - Character.getNumericValue(notation.charAt(1))) * RANK_SIZE;
		if (board.board[square].isEmpty() || board.board[square].getSide() != board.getTurn()) {
			System.out.println(Ansi.RED + " There is none of your pieces in " + notation + Ansi.RESET);
		} else {
			List<Integer> squares = new ArrayList<>();
			List<String> moves = new ArrayList<>();
			for (int i = 0; i < board.player.getNumMoves(); i++) {
				if (board.player.getMove(i).getFrom() == square) {
					squares.add(board.player.getMove(i).getTo());
					moves.add(board.player.getMove(i).getNotation());
				}
			}

			board.printBoard(squares, square);
			System.out.println();
			for (int i = 0; i < moves.size(); i++) {
				System.out.println(String.format(" %2d. %-7s", i + 1, moves.get(i)));
			}
		}
	}

	/**
	 * Prints the material score of the player
	 * @param player selected player
	 * @param white	it's white or not
	 */
	public void materialForPlayer(final Player player, final boolean white) {
		int pawnVal, knightVal, bishopVal, rookVal, queenVal, tot;
		pawnVal = 0;
		knightVal = 0;
		bishopVal = 0;
		rookVal = 0;
		queenVal = 0;

		for (int i = 0; i < player.getNumPieces(); i++) {
			switch (player.getPiece(i).getType()) {
			case PAWN:
				pawnVal += 1;
				break;

			case KNIGHT:
				knightVal += BK_VALUE;
				break;

			case BISHOP:
				bishopVal += BK_VALUE;
				break;

			case ROOK:
				rookVal += ROOK_VALUE;
				break;

			case QUEEN:
				queenVal += QUEEN_VALUE;
				break;

			default:
				break;
			}
		}

		tot = pawnVal + knightVal + bishopVal + rookVal + queenVal;

		System.out.print(Ansi.BOLD);
		if (white) {
			System.out.print(" WHITE   ");
		} else {
			System.out.print(" BLACK   ");
		}
		System.out.print(Ansi.RESET);

		System.out.print((pawnVal)
		+ "x" + Board.getPieceSymbol(Piece.SIDE.WHITE, Piece.PIECE.PAWN));
		System.out.print("=" + pawnVal + "  ");
		System.out.print((knightVal / BK_VALUE)
		+ "x" + Board.getPieceSymbol(Piece.SIDE.WHITE, Piece.PIECE.KNIGHT));
		System.out.print("=" + knightVal + "  ");
		System.out.print((bishopVal / BK_VALUE)
		+ "x" + Board.getPieceSymbol(Piece.SIDE.WHITE, Piece.PIECE.BISHOP));
		System.out.print("=" + bishopVal + "  ");
		System.out.print((rookVal / ROOK_VALUE)
		+ "x" + Board.getPieceSymbol(Piece.SIDE.WHITE, Piece.PIECE.ROOK));
		System.out.print("=" + rookVal + "  ");
		System.out.print((queenVal / QUEEN_VALUE)
		+ "x" + Board.getPieceSymbol(Piece.SIDE.WHITE, Piece.PIECE.QUEEN));
		System.out.print("=" + queenVal + "  ");
		System.out.print("Tot " + tot);
		System.out.println();
	}

	/*
	 * Prints the material score of the two players
	 */
	public void material() {
		materialForPlayer(board.white, true);
		System.out.println();
		materialForPlayer(board.black, false);
	}

	/**
	 * Write down on the chess form a draw as a result of the match
	 */
	public void draw() {
		form.draw();
	}

	/**
	 * Write down on the chess form resign as a result of the match
	 */
	public void resign() {
		if (board.getTurn() == Piece.SIDE.WHITE) {
			form.blackWins();
		} else {
			form.whiteWins();
		}
	}

	/**
	 * Returns if the king of the side is in check
	 * @param side side of the king
	 * @return if it's in check or not
	 */
	public boolean kingInCheck(final Piece.SIDE side) {
		return board.kingInCheck(side);
	}

}
