package com.paouke.gdp.common.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public final class StringUtils extends org.apache.commons.lang.StringUtils {
	private static final String TIME_FORMAT_SHORT = "yyyyMMddHHmmss";
	private static final String TIME_FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";
	private static final String TIME_FORMAT_ENGLISH = "MM/dd/yyyy HH:mm:ss";
	private static final String TIME_FORMAT_CHINA = "yyyy年MM月dd日 HH时mm分ss秒";

	private static final String DATE_FORMAT_SHORT = "yyyyMMdd";
	private static final String DATE_FORMAT_NORMAL = "yyyy-MM-dd";
	private static final String DATE_FORMAT_ENGLISH = "MM/dd/yyyy";
	private static final String DATE_FORMAT_CHINA = "yyyy年MM月dd日";
	private static final String DATE_FORMAT_NORMAL2 = "yyyy.MM.dd";
	
	
	/***
	 * HTTP发送协议
	 */
	public static final String HTTP_TYPES="application/json";  
	public static final String HTTP_TYPES_TEXT="text/xml";  
	public static final String HTTP_CODES="UTF-8";
	/**
	 * 
	 *  @Description    : oracle字段类型为数字,实体类为String时,tochar会导致'0.11'get()时是'.11'
	 *  @Method_Name    : oracleTocharPatches
	 *  @param number
	 *  @return 
	 *  @Creation Date  : 2015年12月7日 下午3:15:19 
	 *  @Author         : 郑志阳
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2015年12月7日			郑志阳				1.0			创建
	 */
	public static String oracleTocharPatches(String number){
		if(!blank(number)){
			if(".".equals(number.substring(0,1))){
				number="0"+number;
			}
		}
		return number;
	}
	
	/**
	 * 
	 *  @Description    : 遍历map的value加分隔符拼接返回
	 *  @Method_Name    : spilteHashMap
	 *  @param map
	 *  @param split
	 *  @return 
	 *  @Creation Date  : 2015年12月7日 下午3:04:53 
	 *  @Author         : 郑志阳
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2015年12月7日			郑志阳				1.0			创建
	 */
	public static String spilteHashMap(Map<String, String> map,String split) {
		String str="";
		if(map.size()>0){
			for (Entry<String, String> entry : map.entrySet()) {
				str+=entry.getValue()+split;
			}
			str=str.substring(0, str.length()-1);
		}
		return str;
	}
	
	/**
	 * 
	 *  @Description    : 把标准的json转成String
	 *  @Method_Name    : jsonstrToString
	 *  @param jsonstr
	 *  @return 
	 *  @Creation Date  : 2016年1月26日 上午11:29:53 
	 *  @Author         : 郑志阳
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2016年1月26日			郑志阳				1.0			创建
	 */
	public static String jsonToString(String json){
		if(!blank(json)){
			json=json.replace("\\", "").replace("\\\"", "").replace("\"", "\\\"");
		}
		return json;
	}
	/**
	 * 判断给定的字符串是否为空,以及空字符串
	 * 
	 * @param input
	 *            输入字符串
	 * @return 是否为空,空则返回true，反之亦反
	 * @since 0.1
	 */
	public static boolean blank(Object input) {
		return input == null || "".equals(input) || input.toString().length() == 0 || input.toString().trim().length() == 0;
	}

	/**
	 * 判断给定的字符串不为空
	 * 
	 * @param input
	 *            输入字符串
	 * @return 是否不为空，不为空返回true，反之亦反.
	 * @since 0.1
	 */
	public static boolean notBlank(Object input) {
		return !blank(input);
	}

	/**
	 * 求字符串中某个子串的位置（自字符串orig的i位开始将orig按组长度len分隔为若干段，求第一个indexOf(段,sub)=0
	 * 的段首字符出现的位置）
	 * 
	 * @param orig
	 *            原字符串
	 * @param sub
	 *            查找的子串
	 * @param len
	 *            每组长度
	 * @param i
	 *            开始查找位置
	 * @return
	 * @since 0.1
	 */
	public static int indexOf(String orig, String sub, int len, int i) {
		if (orig == null)
			return -1;
		int idx = orig.indexOf(sub, i);
		if (idx == -1)
			return idx;
		if (idx % len == 0)
			return idx;
		i = (idx / len + 1) * len;
		int tmp = -1;
		if ((tmp = indexOf(orig, sub, len, i)) > -1) {
			return tmp;
		} else {
			return -1;
		}
	}

	/**
	 * 求字符串中某个子串的位置（将字符串按组长度len分隔为若干段，求第一个indexOf(段,sub)=0的段首字符出现的位置）
	 * 
	 * @param orig
	 *            原字符串
	 * @param sub
	 *            查找的子串
	 * @param len
	 *            每组长度
	 * @return
	 * @since 0.1
	 */
	public static int indexOf(String orig, String sub, int len) {
		return indexOf(orig, sub, len, 0);
	}

	/**
	 * 求字符串中某个子串的位置（将字符串按子串长度分隔为若干段，求第一个同子串相同的段 首字符出现的位置）
	 * 
	 * @param orig
	 *            原字符串
	 * @param sub
	 *            查找的子串
	 * @return
	 * @since 0.1
	 */
	public static int indexOf(String orig, String sub) {
		return indexOf(orig, sub, sub.length(), 0);
	}

	/**
	 * 截取字符串
	 * 
	 * @param orig
	 *            源字符串
	 * @param length
	 *            字符串长度
	 * @return
	 * @since 0.1
	 */
	public static String substr(String orig, int length) {
		if (orig == null)
			return "";
		if (orig.length() <= length)
			return orig;
		return orig.substring(0, length - 1) + "...";
	}

	/**
	 * 首字母大写
	 * 
	 * @param input
	 *            输入字符串
	 * @return
	 * @since 0.1
	 */
	public static String toFirstUpperCase(String input) {
		return blank(input) ? input : input.substring(0, 1).toUpperCase() + input.substring(1);
	}

	/**
	 * 填充字符串到固定长度
	 * 
	 * @param orig
	 *            源字符串
	 * @param num
	 *            填充后字符串长度几位
	 * @param fillCharacter
	 *            要填充的字符
	 * @param location
	 *            位置 true:后 false:前
	 * @return
	 */
	public static String fillCharacter(String orig, int num, String fillCharacter, boolean location) {
		if (orig == null)
			return null;
		if (orig.length() >= num)
			return orig;
		StringBuffer sb = new StringBuffer("");
		for (int i = 0;; i++) {
			if (orig.length() + i * fillCharacter.length() >= num) {
				break;
			}
			sb.append(fillCharacter);
		}
		if (location) {
			orig = orig + sb.substring(0, num - orig.length());
		} else {
			orig = sb.substring(0, num - orig.length()) + orig;
		}
		return orig;
	}

	/**
	 * 字符转换方法
	 * 
	 * @param source
	 *            原字符串
	 * @param clazz
	 *            转换类型
	 * @return
	 * @throws ParseException
	 */
	public static Object convert(String orig, Class<?> clazz) {
		if (orig == null) {
			return null;
		}
		if(clazz !=Calendar.class && clazz != java.util.Date.class){
		    orig=orig.replaceAll("\\s", "");
		}
		if (clazz == String.class) {
			return orig;
		}
		if (clazz == short.class) {
			return Short.parseShort(orig);
		}
		if (clazz == Short.class) {
			return new Short(orig);
		}
		if (clazz == int.class) {
			return Integer.parseInt(orig);
		}
		if (clazz == Integer.class) {
			return new Integer(orig);
		}
		if (clazz == long.class) {
			return Long.parseLong(orig);
		}
		if (clazz == Long.class) {
			return new Long(orig);
		}
		if (clazz == float.class) {
			return Float.parseFloat(orig);
		}
		if (clazz == Float.class) {
			return new Float(orig);
		}
		if (clazz == double.class) {
			return Double.parseDouble(orig);
		}
		if (clazz == Double.class) {
			return new Double(orig);
		}

		if (orig.equalsIgnoreCase("t") || orig.equalsIgnoreCase("ture") || orig.equalsIgnoreCase("y") || orig.equalsIgnoreCase("yes")) {
			if (clazz == boolean.class) {
				return true;
			}
			if (clazz == Boolean.class) {
				return new Boolean(true);
			}
		} else {
			if (clazz == boolean.class) {
				return false;
			}
			if (clazz == Boolean.class) {
				return new Boolean(false);
			}
		}

		try {
			if (clazz == java.util.Date.class) {
				DateFormat fmt = null;
				if (orig.matches("\\d{14}")) {
					fmt = new SimpleDateFormat(TIME_FORMAT_SHORT);
				} else if (orig.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
					fmt = new SimpleDateFormat(TIME_FORMAT_NORMAL);
				} else if (orig.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
					fmt = new SimpleDateFormat(TIME_FORMAT_ENGLISH);
				} else if (orig.matches("\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}时\\d{1,2}分\\d{1,2}秒")) {
					fmt = new SimpleDateFormat(TIME_FORMAT_CHINA);
				} else if (orig.matches("\\d{8}")) {
					fmt = new SimpleDateFormat(DATE_FORMAT_SHORT);
				} else if (orig.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
					fmt = new SimpleDateFormat(DATE_FORMAT_NORMAL);
				} else if (orig.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
					fmt = new SimpleDateFormat(DATE_FORMAT_ENGLISH);
				} else if (orig.matches("\\d{4}年\\d{1,2}月\\d{1,2}日")) {
					fmt = new SimpleDateFormat(DATE_FORMAT_CHINA);
				}else if (orig.matches("\\d{4}.\\d{1,2}.\\d{1,2}")){
				    fmt = new SimpleDateFormat(DATE_FORMAT_NORMAL2);
				}
				return fmt.parse(orig);
			}
			if (clazz == Calendar.class) {
				Calendar cal = Calendar.getInstance();
				DateFormat fmt = null;
				if (orig.matches("\\d{14}")) {
					fmt = new SimpleDateFormat(TIME_FORMAT_SHORT);
				} else if (orig.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
					fmt = new SimpleDateFormat(TIME_FORMAT_NORMAL);
				} else if (orig.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
					fmt = new SimpleDateFormat(TIME_FORMAT_ENGLISH);
				} else if (orig.matches("\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}时\\d{1,2}分\\d{1,2}秒")) {
					fmt = new SimpleDateFormat(TIME_FORMAT_CHINA);
				} else if (orig.matches("\\d{8}")) {
					fmt = new SimpleDateFormat(DATE_FORMAT_SHORT);
				} else if (orig.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
					fmt = new SimpleDateFormat(DATE_FORMAT_NORMAL);
				} else if (orig.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
					fmt = new SimpleDateFormat(DATE_FORMAT_ENGLISH);
				} else if (orig.matches("\\d{4}年\\d{1,2}月\\d{1,2}日")) {
					fmt = new SimpleDateFormat(DATE_FORMAT_CHINA);
				}else if (orig.matches("\\d{4}.\\d{1,2}.\\d{1,2}")){
                    fmt = new SimpleDateFormat(DATE_FORMAT_NORMAL2);
                }
				cal.setTime(fmt.parse(orig));
				return cal;
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException("字符串不能转换为" + clazz.getName() + "类型.");
		}
		throw new IllegalArgumentException("字符串不能转换为" + clazz.getName() + "类型.");
	}

	/**
	 * 字符转换方法
	 *
	 * @param source
	 *            原字符串
	 * @param clazz
	 *            转换类型
	 * @return
	 * @throws ParseException
	 */
	public static String convert(Object orig) {
		if (orig == null) {
			return null;
		}
		if (orig instanceof String) {
			return ((String) orig).trim();
		}
		if (orig instanceof Short) {
			return Short.toString((Short) orig).trim();
		}
		if (orig instanceof Integer) {
			return Integer.toString((Integer) orig).trim();
		}
		if (orig instanceof Long) {
			return Long.toString((Long) orig).trim();
		}
		if (orig instanceof Float) {
			return Float.toString((Float) orig).trim();
		}
		if (orig instanceof Double) {
			return Double.toString((Double) orig).trim();
		}
		if (orig instanceof Boolean) {
			return Boolean.toString((Boolean) orig).trim();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		if (orig instanceof java.util.Date) {
			return format.format((java.util.Date) orig).trim();
		}
		if (orig instanceof java.sql.Date) {
			return format.format((java.sql.Date) orig).trim();
		}
		if (orig instanceof Calendar) {
			return format.format(((Calendar) orig).getTime()).trim();
		}
		throw new IllegalArgumentException("参数类型不支持.");
	}


	/**
	 * 转换数据库列名，分隔符(_)后的首字母大写其他字母小写，删除分隔符
	 * 
	 * @param column
	 *            列名
	 * @return 属性名
	 * @since 0.1
	 */
	public static String convertColumn2Field(String column) {
		String[] fields = column.toLowerCase().split("_");
		StringBuffer sfield = new StringBuffer();
		int i = 0;
		for (String field : fields) {
			if (i > 0) {
				sfield.append(StringUtils.toFirstUpperCase(field));
			} else {
				sfield.append(field);
			}
			i++;
		}
		return sfield.toString();
	}

	/**
	 * 转换数据库表名，首字母及分隔符(_)后的首字母大写其他字母小写，删除分隔符
	 * 
	 * @param column
	 *            列名
	 * @return 属性名
	 * @since 0.1
	 */
	public static String convertTable2Class(String column) {
		String[] fields = column.toLowerCase().split("_");
		StringBuffer sfield = new StringBuffer();
		for (String field : fields) {
			sfield.append(StringUtils.toFirstUpperCase(field));
		}
		return sfield.toString();
	}


	/**
	 * 从map字符串获取value值
	 * 
	 * @param orig
	 *            字符串 例:a=1&b=2&c=3
	 * @param param
	 *            key 例:a
	 * @param split
	 *            例:&
	 * @return 例:1
	 * @since 0.1
	 */
	public static String[] getValueFromString(String orig, String param, String split) {
		String[] result = new String[] {};
		if (StringUtils.isBlank(orig)) {
			return result;
		}
		List<String> list = new ArrayList<String>();
		String[] values = orig.split(split);
		for (String tmp : values) {
			String key = StringUtils.substringBefore(tmp, "=");
			String value = StringUtils.substringAfter(tmp, "=");
			if (param.equals(key)) {
				list.add(value);
			}
		}
		return list.toArray(result);
	}
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String null2String(String s) {
		return s == null ? "" : s;
	}
	/**
     * 
     * @param s
     * @return
     */
    public static String null2String(Object s) {
        return s == null ? "" : s.toString();
    }
	/**
	 * 
	 * @param s
	 * @param s1
	 * @return
	 */
	public static String null2String(String s,String s1) {
		return s == null ? s1 : s;
	}
	/**
	 * 反射根据Method获得类名、方法名
	 * @param method
	 * @return
	 */
	public static String methodToClass(Method method){
		String methodName = method.getName();
		String className = method.getDeclaringClass().getName();
		Class<?>[] typeNames = method.getParameterTypes();
		StringBuffer buffer = new StringBuffer();
		for(Class<?> type : typeNames){
			buffer.append(type.getName()).append(",");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return className + "." + methodName + "(" + buffer.toString() +")";
	}
	
	public static void main(String[] args) {
//	    List<String> stringList = new  ArrayList<String>();
//	    stringList.add("贷款审批1");
//	    stringList.add("贷款审批2");
//	    stringList.add("贷款审批2");
//	    stringList.add("贷款审批3");
//	    stringList.add("贷款审批3");
//	    stringList.add("贷款审批1");
//	    stringList.add("贷款审批1");
//	    System.out.println(stringList);
//	    Map<String, Integer> map = new HashMap<String, Integer>();
//	    for(String s:stringList){
//	        if(map.containsKey(s)){
//	            map.put(s, map.get(s)+1);
//	        }else{
//	            map.put(s, 1);
//	        }
//	        System.out.println(map);
//	    }
//	  System.out.println("::::::        "+map);
//	  int max = 0;
//      String result = null;
//	  for(Entry<String,Integer> ent:map.entrySet()){
//	      if(ent.getValue()>max){
//	          result=ent.getKey();
//	          if(result!=null){
//	              max=ent.getValue();
//	          }
//	      }
//	  }
//
//	  System.out.println("result:"+result+"    max"+max);
	}
	
	public static void st() {
        List<String> list = new ArrayList<String>();
        list.add("aa1");
        list.add("aa2");
        list.add("aa3");
        list.add("aa");
        list.add("bb");
        list.add("bb");
        list.add("cc");
        list.add("cc");
        list.add("dd");
        list.add("ed");
        //      
        String regex;
        Pattern p;
        Matcher m;
        String tmp = "";        
        String tot_str = list.toString();
        System.out.println(tot_str);
        int max_cnt = 0;
        String max_str = "";
        for(String str : list) {
            if (tmp.equals(str))
                continue;
            
            tmp = str;
            regex = str;
            p = Pattern.compile(regex);
            m = p.matcher(tot_str);
            int cnt = 0;
            while(m.find()) {
                cnt++;
            }
            System.out.println(str + ":" + cnt);
            if (cnt > max_cnt) {
                max_cnt = cnt;
                max_str = str;
            }
        }       
        System.out.println("字符串 " + max_str + " 出现的最大次数："+ max_cnt) ;
    }
	
	
	
	
	
	
	public static String joinUrl(String header, String subUrl){
		header = org.apache.commons.lang.StringUtils.trimToEmpty(header);
		subUrl = org.apache.commons.lang.StringUtils.trimToEmpty(subUrl);
		if(header.endsWith("/") || header.endsWith("\\"))
			header = header.substring(0, header.length() - 1);
		if(subUrl.startsWith("/") || subUrl.startsWith("\\"))
			subUrl = subUrl.substring(1, subUrl.length());
		return header + "/" + subUrl;
	}
	
	/**
	 * 将Throwable的异常输出成字符串
	 * @param t 异常
	 * @return
	 */
	public static String getErrStackStr(Throwable t){
		if(t == null)
			return "";
		CharArrayWriter caw = new CharArrayWriter();
		try{
			t.printStackTrace(new PrintWriter(caw));
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(caw);
		}
		return new String(caw.toCharArray());
	}
	/**
	 * String 型的数字转成Integer
	 * @param num
	 */
	public static Integer stringNum2Iteger(Object num){
	    if(num==null || blank(num.toString())){
	        return null;
	    }else {
	        try {
	            return Integer.valueOf(num.toString().trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
	}
	
	public static Double object2Double(Object object){
	    Double reDouble = null;
	    if(null==object){
	        return reDouble;
	    }else{
	        /*if(object instanceof BigDecimal) {
	            return ((BigDecimal)object).doubleValue();
	        }
	        if(object instanceof Integer){
	            return ((Integer)object).doubleValue();
	        }*/
	        try {
                reDouble = new Double(object.toString());
            } catch (NumberFormatException e) {
                return null;
            }
	    }
        return reDouble;
	}
	
	public static Long integer2Long(Integer integer){
	    if(null!=integer){
	       return  integer.longValue();
	    }
	    return null;
	}
	
	public static String replace(String strSource, String strFrom, String strTo) {
        if(strFrom == null || strFrom.equals(""))
          return strSource;
        String strDest = "";
        int intFromLen = strFrom.length();
        int intPos;
        while((intPos = strSource.indexOf(strFrom)) != -1) {
            strDest = strDest + strSource.substring(0,intPos);
            strDest = strDest + strTo;
            strSource = strSource.substring(intPos + intFromLen);
        }
        strDest = strDest + strSource;
        return strDest;
  }
	/***
	 * 格式化执行标的in :9,000.234元 out :9000.234
	 * @param money
	 * @return
	 */
	public static String  formatMoney(String money){
	    if(blank(money)){
	        return null;
	    }else {
            return money.replaceAll("[^\\d\\\\.]", "");
        }
	}
	/***
	 * String 去空格后转 Long 
	 * @param s
	 * @return
	 */
	public static Long string2Long(String s){
	    if(blank(s)){
	        return null;
	    }else{
	        return Long.valueOf(s.replaceAll("\\s", ""));
	    }
	}
	/***
	 * String 去空格后 转 BigDecimal 
	 * @param bigDecimal
	 * @return
	 */
	public static BigDecimal string2BigDecimal(String bigDecimal){
	    if(blank(bigDecimal)){
	        return null;
	    }else{
	        return new BigDecimal(bigDecimal.replaceAll("\\s", ""));
	    }
	}
	/**
	 * String 去千分位，去空格后 转 BigDecimal 
	 * @param str
	 * @return
	 * licui 20150316 add
	 */
    public static BigDecimal str2BigDecimal(String str) {
        try {
            str = str.replaceAll(",", "");
            str = str.replaceAll("\\s", "");
            return new BigDecimal(str);
        } catch(Exception e) {
        		return new BigDecimal(0);
        }
    }
    
   /**
    * String 去千分位，去空格后 转 Integer
    * @param str
    * @return
    * licui 20150316 add
    */
    public static Integer str2Integer(String str) {
        try {
        	str = str.replaceAll(",", "");
            str = str.replaceAll("\\s", "");
            return new Integer(str);
        } catch(Exception e) {
            return 0;
        }
    }
	/****
	 * object 转 bigDecimal null 时返回null
	 * @param bigDecimal
	 * @return bigDecimal
	 */
	public static BigDecimal object2BigDecimal(Object bigDecimal){
	    if(blank(bigDecimal)){
	        return null;
	    }else{
	        return new BigDecimal(new Double(bigDecimal.toString().replaceAll("\\s", "")));
	    }
	}
	
	/**
	 * object 数字转Long
	 * @param number
	 * @return Long
	 */
	public static Long object2Long(Object number){
	    if(blank(number)){
	        return null;
	    }else{
	        return Long.valueOf(number.toString().replaceAll("\\s", ""));
	    }
	}
	
	/***
	 * 返回两个字符串数字中较大的一个
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static String MaxNumerString(String num1,String num2){
	    return object2Long(num1)<object2Long(num2)?num2:num1;
	}
	/***
     * 判断一变量是不是数字型
     * @param obj
     * @return
     */
    public static boolean isNumber(Object obj){
        if(blank(obj)){
            return false;
        }else{
            Pattern pattern = Pattern.compile("[0-9]*");
            if(pattern.matcher(obj.toString().trim()).matches()){
                return true; 
            }else{
                return false;
            }
        }
    }

    /**
     * 
     *  @Description    : zip压缩文本算法
     *  @Method_Name    : zip
     *  @param str				压缩前的文本
     *  @return 					压缩后的文本
     *  @Creation Date  : 2016年5月19日 下午4:48:18 
     *  @Author         : 刘静芳
     *  @ModificationHistory  
     *  Date			Author      	Version    	Description  
     *  ------------------------------------------------------------------  
     *  2016年5月19日			刘静芳				1.0			创建
     */
	public static final String zip(String str) {
		if (str == null)
			return null;
		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;
		String compressedStr = null;
		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str.getBytes());
			zout.closeEntry();
			compressed = out.toByteArray();
			compressedStr = new sun.misc.BASE64Encoder()
					.encodeBuffer(compressed);
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return compressedStr;
	}

	/**
	 * 
	 *  @Description    : 使用zip进行解压缩
	 *  @Method_Name    : unzip
	 *  @param compressedStr    压缩后的文本
	 *  @return 							解压缩的字符串
	 *  @Creation Date  : 2016年5月19日 下午4:49:10 
	 *  @Author         : 刘静芳
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2016年5月19日			刘静芳				1.0			创建
	 */
	public static final String unzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed = null;
		try {
			byte[] compressed = new sun.misc.BASE64Decoder()
					.decodeBuffer(compressedStr);
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return decompressed;
	}
	//给姓名打马赛克，比如王大拿，变成王**,刘大脑袋变成刘***,david变成d****,mabs哈哈123变成m********
	public static String mosaic(String name){
		if(name == null || name.isEmpty()){
			return "";
		}
		char c = name.charAt(0);
		StringBuffer sb = new StringBuffer(name.replaceAll("([^\\*]){1}","*"));
		sb.setCharAt(0,c);
		return sb.toString();
	}

	public static String wipeNull(Object object){
		return object == null ? "" : object.toString();
	}
	
	/** 
	 *  @Description    : 首字母大写 
	 *  @Method_Name    : firstCharacterToUpper
	 *  @param srcStr
	 *  @return 
	 *  @Creation Date  : 2016年12月6日 上午11:31:39 
	 *  @Author         : 何俊杰
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2016年12月6日			何俊杰				1.0			创建
	 */
	public static String firstCharacterToUpper(String srcStr) {  
	   return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);  
	}  
	
	/** 
	 *  @Description    : 替换字符串并让它的下一个字母为大写
	 *  @Method_Name    : replaceUnderlineAndfirstToUpper
	 *  @param srcStr   : 源字符串
	 *  @param org    	: 需要替换的字符
	 *  @param ob		: 替换后的字符
	 *  @return 
	 *  @Creation Date  : 2016年12月6日 上午11:31:57 
	 *  @Author         : 何俊杰
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2016年12月6日			何俊杰				1.0			创建
	 */
	public static String replaceUnderlineAndfirstToUpper(String srcStr,String org,String ob)  
	{  
	   String newString = "";  
	   int first=0;  
	   while(srcStr.indexOf(org)!=-1)  
	   {  
	    first=srcStr.indexOf(org);  
	    if(first!=srcStr.length())  
	    {  
	     newString=newString+srcStr.substring(0,first)+ob;  
	     srcStr=srcStr.substring(first+org.length(),srcStr.length());  
	     srcStr=firstCharacterToUpper(srcStr);  
	    }  
	   }  
	   newString=newString+srcStr;
	   return newString;  
	} 

/**
 * 
 *  @Description    : 转换JSON内部的一些特殊符号
 *  @Method_Name    : htmlEncode
 *  @param c
 *  @return 转换之后的char
 *  @Creation Date  : 2016年12月21日 下午4:33:22 
 *  @Author         : 李强
 *  @ModificationHistory  
 *  Date			Author      	Version    	Description  
 *  ------------------------------------------------------------------  
 *  2016年12月21日			李强				1.0			创建
 */
	private static String htmlEncode(char c) {
		switch (c) {
		case '&':
			return "&amp;";
		case '<':
			return "&lt;";
		case '>':
			return "&gt;";
		// JSON原因目前只转< > &
		/*case '"':
			return "&quot;";
		case ' ':
			return "&nbsp;";*/
		default:
			return c + "";
		}
	}

/**
 * 
 *  @Description    : 转换html符号防止XSS及SQL注入
 *  @Method_Name    : escapeHtml
 *  @param str
 *  @return 转换之后的String
 *  @Creation Date  : 2016年12月21日 下午4:33:14 
 *  @Author         : 李强
 *  @ModificationHistory  
 *  Date			Author      	Version    	Description  
 *  ------------------------------------------------------------------  
 *  2016年12月21日			李强				1.0			创建
 */
	public static String escapeHtml(String str) {
		if (str == null || str.trim().equals("")) {
			return str;	
		}
		StringBuilder encodeStrBuilder = new StringBuilder();
		for (int i = 0, len = str.length(); i < len; i++) {
			encodeStrBuilder.append(htmlEncode(str.charAt(i)));
		}
		return encodeStrBuilder.toString();
	}
}
