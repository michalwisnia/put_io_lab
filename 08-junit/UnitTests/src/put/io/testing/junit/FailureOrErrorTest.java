package put.io.testing.junit;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FailureOrErrorTest {
    @Test
    public void test1()
    {
        assertTrue(false);
    }

    @Test
    public void test2() throws Exception
    {
        throw new Exception();
    }

    @Test
    public void test3()
    {
        try {assertTrue(false); }
        catch(Throwable t) {t.printStackTrace(); }
    }
}
