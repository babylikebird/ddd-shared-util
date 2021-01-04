package com.dsy.shared.util;

import com.dsy.shared.util.pinyin.PinYinUtil;
import com.dsy.shared.util.validator.StringValidatorUtil;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-09-11
 * @time 9:45
 */
public class Test {
    public static void main(String[] args) {
//        System.out.println(StringValidator.isContainSpecialChar("lakaj'j,)&"));
//        System.out.println(StringValidator.isContainNumber("adaa2@"));
//        System.out.println(StringValidator.isContainUpperCase("aCaa2@"));
//        System.out.println(StringValidator.isContainLowerCase("aCaa2@"));
//        System.out.println(StringValidator.isContainSpecialSymbol("aCaa2"));
        System.out.println(PinYinUtil.getDuoYinFirstSpell("长沙市市长", false));
        System.out.println(PinYinUtil.getUpperCase("长沙市市长", false));
        System.out.println(StringValidatorUtil.isUnifiedCode("91520115321756976P"));
    }
}
