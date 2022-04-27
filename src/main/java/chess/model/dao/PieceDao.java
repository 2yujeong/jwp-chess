package chess.model.dao;

import chess.entity.PieceEntity;
import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PieceDao {
    private final JdbcTemplate jdbcTemplate;

    public PieceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static String getPieceName(Piece piece) {
        return (piece.getTeam().name() + "-" + piece.getName()).toLowerCase();
    }

    public void savePieces(Board board, Long gameId) {
        String query = "insert into pieces (position, name, game_id) values (?, ?, ?)";
        for (Map.Entry<Position, Piece> entry : board.getBoard().entrySet()) {
            jdbcTemplate.update(query, entry.getKey().getPosition(), getPieceName(entry.getValue()), gameId);
        }
    }

    public List<PieceEntity> findAllPiecesByGameId(Long gameId) {
        String sql = "select name, position from pieces where game_id = ?";
        return jdbcTemplate.query(sql,
                (resultSet, rowNum) -> {
                    PieceEntity pieceEntity = new PieceEntity(
                            resultSet.getString("name"),
                            resultSet.getString("position")
                    );
                    return pieceEntity;
                }, gameId);
    }

    public String findNameByPositionAndGameId(String position, Long gameId) {
        String sql = "select name from pieces where position = (?) and game_id = (?)";
        return jdbcTemplate.queryForObject(sql, String.class, position, gameId);
    }

    public void updateByPositionAndGameId(String pieceName, String position, Long gameId) {
        String sql = "update pieces set name = (?) where position = (?) and game_id = (?)";
        jdbcTemplate.update(sql, pieceName, position, gameId);
    }

    public void deleteByGameId(Long gameId) {
        String query = "delete from pieces where game_id = (?)";
        jdbcTemplate.update(query, gameId);
    }
}