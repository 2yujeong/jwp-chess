package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMapper;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Color turn;

    public ChessGame() {
        this(new ChessBoard(), Color.WHITE);
    }

    public ChessGame(ChessBoard chessBoard, Color turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    public boolean moved(Position source, Position target) {
        if (validTurn(source) && movable(source, target)) {
            turn = turn.getOppositeColor();
            return true;
        }
        return false;
    }

    public boolean isOver() {
        return chessBoard.isOver();
    }

    public boolean movable(Position source, Position target) {
        try {
            chessBoard.move(source, target);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean validTurn(Position source) {
        if (turn != chessBoard.getPiece(source).getColor()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Map<Position, Piece> getChessBoardMap() {
        return Collections.unmodifiableMap(chessBoard.getChessBoard());
    }

    public Piece getPiece(Position position) {
        return chessBoard.getPiece(position);
    }

    public String getTurn() {
        return turn.toString();
    }

    public Result getResult() {
        Result result = new Result();
        result.add(Color.BLACK, chessBoard.score(Color.BLACK));
        result.add(Color.WHITE, chessBoard.score(Color.WHITE));
        return result;
    }
}
