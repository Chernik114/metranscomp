import java.text.ParseException;
import java.util.*;

public class IndetAuto {
    private Map<Character, Map<Character, List<Character>>> map;
    private Character start;
    private String end;
    public IndetAuto(String[] rules, Character start, String end) throws ParseException {
        this.start = start;
        this.end = end;
        map = new HashMap<Character, Map<Character, List<Character>>>();
        for(String rule: rules){
            Character inp = rule.charAt(0);
            Character exp = rule.charAt(1);
            Character outp = rule.charAt(2);
            if(!map.containsKey(inp)){
                map.put(inp, new HashMap<Character, List<Character>>());
            }
            if(!map.get(inp).containsKey(exp)){
                map.get(inp).put(exp, new ArrayList<Character>());
            }
            if(map.get(inp).get(exp).indexOf(outp) >= 0){
                throw new ParseException("Duplicate in rule " + rule, 0);
            }
            map.get(inp).get(exp).add(outp);
        }
    }

    public boolean check(String lang) throws ParseException {
        List<Integer> stackKeys = new ArrayList<Integer>();
        List<Character> stackValues = new ArrayList<Character>();
        stackKeys.add(0);
        stackValues.add(start);
        while(stackKeys.size() > 0){
            Integer upKey = stackKeys.remove(stackKeys.size() - 1);
            Character upValue = stackValues.remove(stackValues.size() - 1);
            if(upKey == lang.length()){
                if(end.indexOf(upValue) >= 0) {
                    return true;
                }
                continue;
            }
            if(!map.containsKey(upValue)){
                continue;
            }
            if(!map.get(upValue).containsKey(lang.charAt(upKey))) {
                continue;
            }
            for(Character n: map.get(upValue).get(lang.charAt(upKey))){
                stackKeys.add(upKey + 1);
                stackValues.add(n);
            }
        }
        return false;
    }
}
