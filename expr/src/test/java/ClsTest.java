import static org.junit.Assert.*;

public class ClsTest {

    @org.junit.Test
    public void foo() {
        Cls a = new Cls();
        assert a.foo(4) == 8;
        assert a.foo(5) == 9;
    }
}