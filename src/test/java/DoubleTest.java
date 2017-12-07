import org.junit.Assert;
import org.junit.Test;

public class DoubleTest {

    @Test
    public void test() {
        double d = 24718937;
        double d2 = 5000d;
        System.out.println("d=" + d);

        Assert.assertTrue(d > d2);
    }
}
