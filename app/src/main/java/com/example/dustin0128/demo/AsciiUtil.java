package com.example.dustin0128.demo;

/**
 * Created by dustin0128 on 2018/10/22.
 */

public class AsciiUtil {

    /*
    1.半形字元是從33開始到126結束
    2.與半形字元對應的全形字元是從65281開始到65374結束
    3.其中半形的空格是32.對應的全形空格是12288
    4.半形和全形的關係很明顯,除空格外的字元偏移量是65248(65281-33 = 65248)
    */

    public static final char SBC_SPACE = 12288; // 全形空格 12288
    public static final char DBC_SPACE = 32; //半形空格 32
    // ASCII character 33-126 <-> unicode 65281-65374
    public static final char ASCII_START = 33; //半形開始
    public static final char ASCII_END = 126; //半形結束
    public static final char UNICODE_START = 65281; //全形開始
    public static final char UNICODE_END = 65374; //全形結束
    public static final char DBC_SBC_STEP = 65248; // 全形半形轉換間隔

    public static char sbc2dbc(char src){
        if (src == SBC_SPACE) {
            return DBC_SPACE;
        }
        if (src >= UNICODE_START && src <= UNICODE_END) {
            return (char) (src - DBC_SBC_STEP);
        }
        return src;
    }

    /**
     * Convert from SBC case to DBC case
     * 全形轉半形
     * @param src
     * @return DBC case
     */
    public static String sbc2dbcCase(String src) {
        if (src == null) {
            return null;
        }

        char[] c = src.toCharArray();

        for (int i = 0; i < c.length; i++) {
            c[i] = sbc2dbc(c[i]);
        }
        return new String(c);
    }

    public static char dbc2sbc(char src){
        if (src == DBC_SPACE) {
            return SBC_SPACE;
        }
        if (src <= ASCII_END) {
            return (char) (src + DBC_SBC_STEP);
        }
        return src;
    }

    /**
     * Convert from DBC case to SBC case.
     * 半形轉全形
     * @param src
     * @return SBC case string
     */
    public static String dbc2sbcCase(String src) {
        if (src == null) {
            return null;
        }
        char[] c = src.toCharArray();

        for (int i = 0; i < c.length; i++ ) {
            c[i] = dbc2sbc(c[i]);
        }
        return new String(c);
    }

    public static void main(String[] args){
        System.out.println(AsciiUtil.sbc2dbcCase("Ｐ６11"));
        System.out.println(AsciiUtil.dbc2sbcCase("c68222Ｅ２２"));

        for(int i=0; i<=10;i++){
            System.out.println(i);
        }
    }
}
