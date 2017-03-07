package LeetCode.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kashishtayal on 2/14/17.
 */
public class EncodeAndDecodeStrings {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if(strs.isEmpty()){
            return"null";
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strs.size(); i++){
            String str = strs.get(i);
            if(str.equals("")){
                sb.append(" ");
            }else{
                for(int j = 0; j < str.length(); j++){
                    sb.append((int) str.charAt(j));
                    if(j < str.length()-1)sb.append("-");
                }
            }
            if(i < strs.size()-1)sb.append(":");
        }
        return sb.toString();
    }


    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> list = new ArrayList<>();
        if(s.equals("null")){
            return list;
        }
        String[] strs = s.split(":");
        for(String str : strs){
            if(str.equals(" ")){
                list.add("");
            }else{
                StringBuilder sb = new StringBuilder();
                String[] chars = str.split("-");
                for(String c : chars){
                    sb.append((char)Integer.parseInt(c));
                }
                list.add(sb.toString());
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("ada87t87gv8oag8");
        list.add("");
        list.add(" ");
        EncodeAndDecodeStrings ed = new EncodeAndDecodeStrings();
        String str = ed.encode(list);
        System.out.println(ed.decode(str));
    }
}
