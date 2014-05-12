package tennis;

import static java.lang.String.format;

public class TennisGame1 implements TennisGame {
    private Player player1;
    private Player player2;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    public void wonPoint(String playerName) {
        if (player1.is(playerName)) player1.scored();
        else player2.scored();
    }

    public String getScore() {
        return player1.makeScoreTextWith(player2);
    }

    private static class Player {
        private String name;
        private int score;

        public Player(String name) {
            this.name = name;
        }

        public boolean is(String playerName) {
            return name.equals(playerName);
        }

        public void scored() {
            score++;
        }

        public String makeScoreTextWith(Player other) {
            if (isTiedTo(other)) return makeTieScore();
            else if (hasAdvantageOrWinOver(other)) return makeAdvantageOrWinTextWith(other);
            else if (other.hasAdvantageOrWinOver(this)) return other.makeAdvantageOrWinTextWith(this);
            else return makeRunningScoreTextWith(other);
        }

        private boolean isTiedTo(Player other) {
            return score == other.score;
        }

        private String makeTieScore() {
            return score < 3 ? format("%s-All", scoreText()) : "Deuce";
        }

        private boolean hasAdvantageOrWinOver(Player other) {
            return score >= 4 && score > other.score;
        }

        private String makeAdvantageOrWinTextWith(Player other) {
            if (score - other.score >= 2) return format("Win for %s", name);
            else return format("Advantage %s", name);
        }

        private String makeRunningScoreTextWith(Player other) {
            return format("%s-%s", makeRunningScore(), other.makeRunningScore());
        }

        private String makeRunningScore() {
            return score < 3 ? scoreText() : "Forty";
        }

        private String scoreText() {
            switch (score) {
                case 0:
                    return "Love";
                case 1:
                    return "Fifteen";
                case 2:
                    return "Thirty";
                default:
                    return null;
            }
        }
    }
}
