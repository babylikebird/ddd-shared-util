package com.dsy.shared.util.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author  Mr.Yangxiufeng
 * @date  2018-06-11
 */
public class PinYinUtil {
    /**
     *  获取汉字首字母或全拼大写字母，多音字只取第一个
     *
     * @param chinese 汉字
     * @param isFull  是否全拼 true:表示全拼 false表示：首字母
     *
     * @return 全拼或者首字母大写字符窜
     */
    public static String getUpperCase(String chinese,boolean isFull){
        return convertHanzi2Pinyin(chinese,isFull).toUpperCase();
    }

    /**
     * 获取汉字首字母或全拼小写字母，多音字只取第一个
     *
     * @param chinese 汉字
     * @param isFull 是否全拼 true:表示全拼 false表示：首字母
     *
     * @return 全拼或者首字母小写字符窜
     */
    public static  String getLowerCase(String chinese,boolean isFull){
        return convertHanzi2Pinyin(chinese,isFull).toLowerCase();
    }

    /**
     * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（长沙市长:cssc,zssz,zssc,cssz）
     * @param chinese 中文
     * @return
     */
    public static String getDuoYinFirstSpell(String chinese,boolean isUpperCase){
        String firstSpell = converterToFirstSpell(chinese);
        if (isUpperCase){
            return firstSpell.toUpperCase();
        }
        return firstSpell.toLowerCase();
    }

    /**
     * 将汉字转成拼音
     * <P>
     * 取首字母或全拼
     *
     * @param hanzi 汉字字符串
     * @param isFull 是否全拼 true:表示全拼 false表示：首字母
     *
     * @return 拼音
     */
    private static String convertHanzi2Pinyin(String hanzi,boolean isFull){
        /***
         * ^[\u2E80-\u9FFF]+$ 匹配所有东亚区的语言
         * ^[\u4E00-\u9FFF]+$ 匹配简体和繁体
         * ^[\u4E00-\u9FA5]+$ 匹配简体
         */
        String regExp="^[\u4E00-\u9FFF]+$";
        StringBuilder sb=new StringBuilder();
        if(hanzi==null||"".equals(hanzi.trim())){
            return "";
        }
        String pinyin;
        for(int i=0;i<hanzi.length();i++){
            char unit=hanzi.charAt(i);
            //是汉字，则转拼音
            if(match(String.valueOf(unit),regExp)){
                pinyin=convertSingleHanzi2Pinyin(unit);
                if(isFull){
                    sb.append(pinyin);
                }
                else{
                    sb.append(pinyin.charAt(0));
                }
            }else{
                sb.append(unit);
            }
        }
        return sb.toString();
    }

    /**
     * 将单个汉字转成拼音
     *
     * @param hanzi 汉字字符
     *
     * @return 拼音
     */
    private static String convertSingleHanzi2Pinyin(char hanzi){
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String[] res;
        StringBuilder sb=new StringBuilder();
        try {
            res = PinyinHelper.toHanyuPinyinStringArray(hanzi,outputFormat);
            sb.append(res[0]);//对于多音字，只用第一个拼音
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }

    /***
     * 匹配
     * <P>
     * 根据字符和正则表达式进行匹配
     *
     * @param str 源字符串
     * @param regex 正则表达式
     *
     * @return true：匹配成功  false：匹配失败
     */
    private static boolean match(String str,String regex){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(str);
        return matcher.find();
    }
    /**
     * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（长沙市长:cssc,zssz,zssc,cssz）
     *
     * @param chines
     *            汉字
     * @return 拼音
     */
    private static String converterToFirstSpell(String chines) {
        StringBuilder pinyinName = new StringBuilder();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char c : nameChar) {
            if (c > 128) {
                try {
                    // 取得当前汉字的所有全拼
                    String[] strs = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat);
                    if (strs != null) {
                        for (int j = 0; j < strs.length; j++) {
                            // 取首字母
                            pinyinName.append(strs[j].charAt(0));
                            if (j != strs.length - 1) {
                                pinyinName.append(",");
                            }
                        }
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(c);
            }
            pinyinName.append(" ");
        }
        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
    }
    /**
     * 解析并组合拼音，对象合并方案(推荐使用)
     *
     * @return
     */
    private static String parseTheChineseByObject(List<Map<String, Integer>> list) {
        Map<String, Integer> first = null; // 用于统计每一次,集合组合数据
        // 遍历每一组集合
        for (Map<String, Integer> stringIntegerMap : list) {
            // 每一组集合与上一次组合的Map
            Map<String, Integer> temp = new HashMap<>();
            // 第一次循环，first为空
            if (first != null) {
                // 取出上次组合与此次集合的字符，并保存
                for (String s : first.keySet()) {
                    for (String s1 : stringIntegerMap.keySet()) {
                        String str = s + s1;
                        temp.put(str, 1);
                    }
                }
                // 清理上一次组合数据
                if (temp.size() > 0) {
                    first.clear();
                }
            } else {
                for (String s : stringIntegerMap.keySet()) {
                    String str = s;
                    temp.put(str, 1);
                }
            }
            // 保存组合数据以便下次循环使用
            if (temp.size() > 0) {
                first = temp;
            }
        }
        String returnStr = "";
        if (first != null) {
            // 遍历取出组合字符串
            for (String str : first.keySet()) {
                returnStr += (str + ",");
            }
        }
        if (returnStr.length() > 0) {
            returnStr = returnStr.substring(0, returnStr.length() - 1);
        }
        return returnStr;
    }
    /**
     * 去除多音字重复数据
     *
     * @param theStr
     * @return
     */
    private static List<Map<String, Integer>> discountTheChinese(String theStr) {
        // 去除重复拼音后的拼音列表
        List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>();
        // 用于处理每个字的多音字，去掉重复
        Map<String, Integer> onlyOne = null;
        String[] firsts = theStr.split(" ");
        // 读出每个汉字的拼音
        for (String str : firsts) {
            onlyOne = new HashMap<>();
            String[] china = str.split(",");
            // 多音字处理
            for (String s : china) {
                Integer count = onlyOne.get(s);
                if (count == null) {
                    onlyOne.put(s, 1);
                } else {
                    onlyOne.remove(s);
                    count++;
                    onlyOne.put(s, count);
                }
            }
            mapList.add(onlyOne);
        }
        return mapList;
    }
}
