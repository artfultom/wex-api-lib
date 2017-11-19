package my.artfultom.wexapi.util;

public enum OperationType {
    BUY,
    SELL;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
