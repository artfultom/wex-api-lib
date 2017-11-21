package my.artfultom.wexapi.util;

public enum OrderType {
    BUY,
    SELL;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
