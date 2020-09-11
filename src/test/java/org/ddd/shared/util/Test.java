package org.ddd.shared.util;

import org.ddd.shared.util.validate.StringValidator;

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
        System.out.println(StringValidator.isContainSpecialSymbol("aCaa2"));
    }
}
