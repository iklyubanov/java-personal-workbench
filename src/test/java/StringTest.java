import org.junit.Assert;
import org.junit.Test;

public class StringTest {

    @Test
    public void test() {
        String testString = "0123456789";
        String result = testString.substring(testString.length()-4);
        System.out.println(result);
        Assert.assertEquals("6789", result);
    }
}
