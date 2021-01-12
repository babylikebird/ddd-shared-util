package com.dsy.sunshine.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-09-10
 * @time 16:06
 */
public class StringValidatorUtil {
    /**
     * 是否为纯数字
     * @param text 待验证字符串
     * @return
     */
    public static boolean isNumber(String text) {
        if (null == text) {
            return false;
        }
        final String regex = "^\\d+$";
        return text.matches(regex);
    }

    /**
     * 是否长度在指定范围内
     * @param text 待验证字符串
     * @param min 最小长度
     * @param max 最大长度
     * @return
     */
    public static boolean isBetween(String text, int min, int max) {
        if (null == text) {
            return false;
        }
        int length = text.length();
        return length >= min && length <= max;
    }


    /**
     * <p>是否是纯中文</p>
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * <p>【除英文字母和数字外无其他字符(只有英文数字的字符串)】</p>
     * @param str
     * @return
     */
    public static boolean isEnglishAndNumber(String str){
        if (null == str){
            return false;
        }
        return str.matches("[a-zA-Z0-9]+");
    }
    /**
     * 匹配手机号
     * @param text 待验证字符串
     */
    public static boolean isMobile(String text){
        if (null == text) {
            return false;
        }
        final String regex = "1[3-9][0-9]{9}";
        return text.matches(regex);
    }

    /**
     * 匹配固定电话
     * @param text 待验证字符串
     */
    public static boolean isPhone(String text){
        if (null == text) {
            return false;
        }
        final String regex = "\\d{10,12}";
        return text.matches(regex);
    }
    /**
     * 匹配身份证号
     * @param text 待验证字符串
     */
    public static boolean isIdCard(String text)  {
        if (null == text) {
            return false;
        }
        final String regex = "\\d{17}[\\dX]";
        return text.matches(regex);

    }

    /**
     * 匹配统一社会信用代码
     * @param text 待验证字符串
     */
    public static boolean isUnifiedCode(String text) {
        if (null == text) {
            return false;
        }
        final String regex = "^([0-9A-HJ-NPQRTUWXY]{2}\\d{6}[0-9A-HJ-NPQRTUWXY]{10}|[1-9]\\d{14})$";
        return text.matches(regex);
    }

    /**
     * 判断是否含有特殊字符
     *
     * @param text
     * @return true为包含，false为不包含
     */
    public static boolean isContainSpecialChar(String text) {
        if(null == text){
            return false;
        }
        String regEx = "[ _.`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        return m.find();
    }

    /**
     * 是否包含数字
     * @param text
     * @return
     */
    public static boolean isContainNumber(String text){
        if ( null == text ){
            return false;
        }
        return text.matches(".*\\d+.*");
    }

    /**
     * 是否包含大写字母
     * @param text
     * @return
     */
    public static boolean isContainUpperCase(String text){
        if ( null == text ){
            return false;
        }
        return text.matches(".*[A-Z]+.*");
    }
    /**
     * 是否包含小写字母
     * @param text
     * @return
     */
    public static boolean isContainLowerCase(String text){
        if ( null == text ){
            return false;
        }
        return text.matches(".*[a-z]+.*");
    }

    /**
     * 是否包含特殊字符
     * @param text
     * @return
     */
    public static boolean isContainSpecialSymbol(String text){
        if ( null == text ){
            return false;
        }
        return text.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*");
    }
}
