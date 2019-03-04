import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class DetAutoTest {

    @Test
    public void check() throws ParseException {
        String[] rules = {"a0a", "a1b"};
        DetAuto detAuto = new DetAuto(rules, 'a', "b");
        assert detAuto.check("0000001");
        assert detAuto.check("1000000100");
        assert !detAuto.check("000000");
    }

    @Test
    public void check1() throws ParseException {
        String[] rules = {"SbS", "SaA", "AaB", "BaC", "Caq", "AbS", "BbS", "CbS", "qbS"};
        DetAuto detAuto = new DetAuto(rules, 'S', "q");
        assert !detAuto.check("ababababba");
        assert detAuto.check("ababaaaa");
    }
}