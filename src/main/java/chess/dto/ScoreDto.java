package chess.dto;

public class ScoreDto {

    private final double blackScore;
    private final double whiteScore;

    public ScoreDto(final double blackScore, final double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
