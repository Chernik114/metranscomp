import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class DetAuto {
    private Map<Character, Map<Character, Character>> map;
    private Character start;
    private String end;
    public DetAuto(String[] rules, Character start, String end) throws ParseException {
        map = new HashMap<Character, Map<Character, Character>>();
        for(String rule: rules){
            Character inp = rule.charAt(0);
            Character exp = rule.charAt(1);
            Character outp = rule.charAt(2);
            if(!map.containsKey(inp)){
                map.put(inp, new HashMap<Character, Character>());
            }
            if(map.get(inp).containsKey(exp)){
                throw new ParseException("Duplicate in rule " + rule, 0);
            }
            map.get(inp).put(exp, outp);
            this.start = start;
            this.end = end;
        }
    }
    public boolean check(String lang){
        Character n = start;
        for(int i = 0; i < lang.length(); i++){
            if(!map.containsKey(n)){
                continue;
            }
            if(!map.get(n).containsKey(lang.charAt(i))){
                continue;
            }
            n = map.get(n).get(lang.charAt(i));
        }
        return end.indexOf(n) >= 0;
    }
}
