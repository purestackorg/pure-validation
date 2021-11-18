package com.purestack.common.validation;


import java.math.BigDecimal;

/**
 * 数字工具类
 * @author Benson
 */
	public class NumberUtil {
		/**
		 * 数字转换为字符串
		 * @param num 数字
		 * @return 字符串,如果 num 为空, 返回空字符串
		 */
		public static String toString(Object num) {
			String str = null;

			if (num == null) {
				str = "";

			}

			else {
				str = String.valueOf(num);

			}

			return str;

		}

	public static BigDecimal toDecimal(Object obj, double def ) {
		String str = obj == null ? "" : obj.toString();

		BigDecimal  i = null;

		if (str.trim().length() == 0) {
			i = new BigDecimal(def);

		}

		else {
			try {
				i = new BigDecimal(str);

			}
			catch (Exception e) {
			}

		}
		return i == null ? new BigDecimal(def) : i;
	}

	public static BigDecimal toDecimal(Object obj) {
		return toDecimal(obj, 0);

	}
		/**
		 * 字符串转换为Integer
		 * @param str 字符串
		 * @return Integer, str为null时返回0
		 */
		public static Integer toInteger(Object obj) {
			return toInteger(obj, 0);

		}

		/**
		 * 字符串转换为Integer
		 * @param str 字符串
		 * @param def 默认值
		 * @return Integer, 字符串为null时返回def
		 */
		public static Integer toInteger(Object obj, int def) {
			String str = obj == null ? "" : obj.toString();

			Integer i = null;

			if (str.trim().length() == 0) {
				i = new Integer(def);

			}

			else {
				try {
					i = Integer.valueOf(str);

				}
				catch (Exception e) {
				}

			}
			return i == null ? new Integer(def) : i;
		}

		/**
		 * 字符串转换为Long
		 * @param str 字符串
		 * @return Long, str为null时返回0
		 */
		public static Long toLong(Object obj) {
			return toLong(obj, 0);

		}

		/**
		 * 字符串转换为Long
		 * @param str 字符串
		 * @param def 默认值
		 * @return Long, 字符串为null时返回def
		 */

		public static Long toLong(Object obj, long def) {
			String str = obj == null ? "" : obj.toString();

			Long l = null;

			if (str.trim().length() == 0) {
				l = new Long(def);

			}

			else {
				try {
					l = Long.valueOf(str);

				}

				catch (Exception e) {
				}

			}

			return l == null ? new Long(def) : l;

		}

		/**
		 * 字符串转换为Integer
		 * @param str 字符串
		 * @return Integer, str为null时返回0
		 */
		public static int toIntegerValue(Object obj) {
			return toIntegerValue(obj, 0);

		}

		/**
		 * 字符串转换为Integer
		 * @param str 字符串
		 * @param def 默认值
		 * @return Integer, 字符串为null时返回def
		 */
		public static int toIntegerValue(Object obj, int def) {
			return toInteger(obj, def).intValue();

		}

		/**
		 * 字符串转换为Long
		 * @param str 字符串
		 * @return Long, str为null时返回0
		 */
		public static long toLongValue(Object obj) {
			return toLongValue(obj, 0);

		}

		/**
		 * 字符串转换为Long
		 * @param str 字符串
		 * @param def 默认值
		 * @return Long, 字符串为null时返回def
		 */
		public static long toLongValue(Object obj, long def) {
			return toLong(obj, def).longValue();

		}
}
