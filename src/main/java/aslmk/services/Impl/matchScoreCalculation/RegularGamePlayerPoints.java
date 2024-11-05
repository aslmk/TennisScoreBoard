package aslmk.services.Impl.matchScoreCalculation;

public enum RegularGamePlayerPoints {
    ZERO("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("AD");
    private String points;
    RegularGamePlayerPoints(String points) {
        this.points = points;
    }
    public String getPoints() {
        return points;
    }

    public RegularGamePlayerPoints next() {
        if (this == ADVANTAGE) {
            throw new IllegalStateException("Cannot call next on ADVANTAGE");
        } else {
            return RegularGamePlayerPoints.values()[(this.ordinal() + 1)];
        }
    }

}
