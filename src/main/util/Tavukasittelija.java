
package util;

public class Tavukasittelija {
    
    public static int kaannaInt(byte b){
        int i = b;
        if(i < 0) i += 256;
        return i;
    }
    
    public static String kaannaString(byte b){
        int i = kaannaInt(b);
        String s = "";
        if(i < 10) s += "0";
        if(i < 100) s += "0";
        s += String.valueOf(i);
        return s;
    }
    
    public static byte stringTavuksi(String s){
        int i = Integer.parseInt(s);
        return (byte) i;
    }
}