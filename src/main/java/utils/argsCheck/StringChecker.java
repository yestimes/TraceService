package utils.argsCheck;

public class StringChecker {
    public static boolean isStringBlank(String src){
        boolean flag;
        if (src == null || src.equals("")){
            flag = true;
        }else {
            flag = false;
        }
        return flag;
    }
}
