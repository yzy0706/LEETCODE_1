package Math.algebra;

public class AnglesBetweenHands {
    // 做法: 我就用了个简单的数学计算, 算当前时针和分针在表盘上的比例, 可能有更好的办法?
    // Runtime: O(1), Space: O(1);

    public double angleClock(int hour, int minutes) {
        double dH = (30 * hour + (double)minutes/60 * 30) % 360, dM = (double)minutes/60 * 360;
        double angle = Math.abs(dH - dM);
        return angle > 180? 360 - angle : angle;
    }
}
