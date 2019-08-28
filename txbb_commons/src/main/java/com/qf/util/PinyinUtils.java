package com.qf.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/28 15:29
 */
public class PinyinUtils {

    /**
     * 将文本转换成字符串
     * @param content
     * @return
     */
    public static String string2Pinyin(String content){

        char[] chars = content.toCharArray();

        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            String[] pinyin = null;
            try {
                pinyin = PinyinHelper.toHanyuPinyinStringArray(aChar, outputFormat);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }

            if(pinyin != null){
                sb.append(pinyin[0]);
            } else {
                sb.append(aChar);
            }
        }
        return sb.toString();
    }
}
