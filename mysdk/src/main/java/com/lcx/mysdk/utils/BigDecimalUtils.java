package com.lcx.mysdk.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @ClassName(类名) : BigDecimalUtils
 * @Description(描述) : 数值工具类
 * @author(作者) ：pengfei
 */
public abstract class BigDecimalUtils {
	public static final BigDecimal ZERO = new BigDecimal(0);
	public static final BigDecimal ONE = new BigDecimal(1);
	public static final BigDecimal TEN = new BigDecimal(10);
	public static final BigDecimal HUNDRED = new BigDecimal(100);
	public static final BigDecimal THOUSAND = new BigDecimal(1000);

	/**
	 * @Description(功能描述) : 检查数值是否为空,如果为空则返回0,否则返回原值
	 * @author(作者) ： pengfei
	 * @date (开发日期) : 2015年3月6日 上午9:41:12
	 * @exception :
	 * @param a 数值
	 * @return BigDecimal
	 */
	public static BigDecimal isNull(BigDecimal a) {
		return (a == null) ? BigDecimal.ZERO : a;
	}

	/**
	 * @Description(功能描述) : 检查数值是否等于0,为空也表示等于0
	 * @author(作者) ： pengfei
	 * @date (开发日期) : 2015年3月6日 上午9:41:34
	 * @exception :
	 * @param a 数值
	 * @return boolean
	 */
	public static boolean isZERO(BigDecimal a) {
		return (a != null) && a.compareTo(BigDecimal.ZERO) == 0;
	}

	/**
	 * @Description(功能描述) : 取两个数值中的最大值
	 * @author(作者) ： pengfei
	 * @date (开发日期) : 2015年3月6日 上午9:42:16
	 * @exception :
	 * @param a 数值a
	 * @param b 数值b
	 * @return a和b中的最大值
	 */
	public static BigDecimal max(BigDecimal a, BigDecimal b) {
		return a.compareTo(b) >= 0 ? a : b;
	}

	/**
	 * @Description(功能描述) ：取两个数值中的最小值.
	 * @param a 数值a
	 * @param b 数值b
	 * @return a和b中的最小值
	 */
	public static BigDecimal min(BigDecimal a, BigDecimal b) {
		return a.compareTo(b) >= 0 ? b : a;
	}

	/**
	 * @Description(功能描述) ：两个数值相加.
	 * @param a 数值a,如果为空则转换为0
	 * @param b 数值b,如果为空则转换为0
	 * @return a和b相加的结果
	 */
	public static BigDecimal add(BigDecimal a, BigDecimal b) {
		return isNull(a).add(isNull(b));
	}

	/**
	 * @Description(功能描述) ：两个数值相减(a - b).
	 * @param a 数值a,如果为空则转换为0
	 * @param b 数值b,如果为空则转换为0
	 * @return a和b相减的结果
	 */
	public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
		return isNull(a).subtract(isNull(b));
	}

	/**
	 * @Description(功能描述) ：两个数值相乘
	 * @param a 数值a,如果为空则转换为0
	 * @param b 数值b,如果为空则转换为0
	 * @return a和b相剩的结果
	 */
	public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
		if (a == null || b == null) {
			return BigDecimal.ZERO;
		}
		return a.multiply(b);
	}

	/**
	 * @Description(功能描述) ：两个数值相除(a/b).
	 * @param a 数值a,如果为空则转换为0
	 * @param b 数值b,如果为空则转换为0
	 * @param scale 精度
	 * @return a和b相除的结果
	 */
	public static BigDecimal divide(BigDecimal a, BigDecimal b, int scale) {
		if (a == null || b == null) {
			return BigDecimal.ZERO;
		}
		return a.divide(b, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @Description(功能描述) : 去掉数值中无用的后缀零
	 * @author(作者) ： pengfei
	 * @date (开发日期) : 2015年3月6日 上午9:44:11
	 * @exception :
	 * @param value 数值
	 * @return BigDecimal
	 */
	public static BigDecimal roundBigDecimal(BigDecimal value) {
		if (value == null) {
			return null;
		}
		DecimalFormat df = new DecimalFormat("###0.###########");
		return new BigDecimal(df.format(value.doubleValue()));
	}
	
	/**
     * @Description(功能描述) : 保留两位小数
     * @author(作者) ： pengfei
     * @date (开发日期) : 2015年3月6日 上午9:44:11
     * @exception :
     * @param value 数值
     * @param num 小数位
     * @return BigDecimal
     */
	public static BigDecimal roundBigDecimal(BigDecimal value,int num) {
	    if (value == null) {
	        return null;
	    }
	    DecimalFormat df = new DecimalFormat("###0.00#########");
	    return new BigDecimal(df.format(value.doubleValue()));
	}

	/**
	 * @Description(功能描述) : 转为 String
	 * @author(作者) ： pengfei
	 * @date (开发日期) : 2015年3月6日 上午9:45:22
	 * @exception :
	 * @param value 数值
	 * @return String
	 */
	public static String bigDecimal2Str(BigDecimal value) {
		if (value == null) {
			return "";
		}
		NumberFormat fmt = new DecimalFormat();
		fmt.setMaximumFractionDigits(10);
		return fmt.format(value);
	}
}
