package bo.app;

public final class ee {
    public static double a(double d, double d2, double d3, double d4) {
        return Math.asin(Math.sqrt(Math.pow(Math.sin(Math.toRadians(d3 - d) / 2.0d), 2.0d) + (Math.pow(Math.sin(Math.toRadians(d4 - d2) / 2.0d), 2.0d) * Math.cos(Math.toRadians(d)) * Math.cos(Math.toRadians(d3))))) * 2.0d * 6371000.0d;
    }
}
