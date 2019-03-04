import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class IndetAutoTest {

    @Test
    public void check() throws ParseException {
        String[] rules = {"a0a", "a1b", "b0b", "b1b"};
        IndetAuto indetAuto = new IndetAuto(rules, 'a', "b");
        assert indetAuto.check("000000001");
        assert indetAuto.check("100000100");
        assert !indetAuto.check("00000000");
    }

    @Test
    public void check1() throws ParseException {
        String[] rules = {"SaS", "SbS", "SaA", "AaB", "BaC", "CaD"};
        IndetAuto indetAuto = new IndetAuto(rules, 'S', "D");
        assert !indetAuto.check("ababbabaabba");
        assert indetAuto.check("abbbaaaaa");
    }
}