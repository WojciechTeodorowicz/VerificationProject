
public class Period {
    int startPeriod;
    int endPeriod;

    public Period(int start, int end) {
        this.startPeriod = start;
        this.endPeriod = end;
    }

    int duration() {
        return this.endPeriod - this.startPeriod;
    };

    boolean overlaps() {
        return true;
    }
}