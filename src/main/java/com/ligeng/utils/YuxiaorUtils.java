package com.ligeng.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dream.utils.DateUtil;
import com.dream.utils.json.JsonObjectTools;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * YuxiaorUtils
 *
 * @author zhaihao
 * @version 1.0
 * @since 15/9/15 11:14.
 */
public class YuxiaorUtils {

    /**
     * 比较两个字符串日期 yyyy-MM-dd 大小
     */
    public static int compareStringDate(String date1, String date2) {
        Long d1 = Long.valueOf(date1.replaceAll("-", ""));
        Long d2 = Long.valueOf(date2.replaceAll("-", ""));
        return d1.compareTo(d2);
    }

    /**
     * 比较第一个日期的下一天是否是第二个日期
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return 是-true 否-false
     */
    public static boolean equalsNextDay(String date1, String date2) {
        Date date = DateUtil.setDate(date1, 1);
        return date.equals(DateUtil.stringToDate(date2));
    }

    /**
     * string date 的下一个日期
     *
     * @param date date 字符串
     * @return a {@link String} 下一个日期
     */
    public static String getNextDay(String date) {
        Date temp = DateUtil.setDate(date, 1);
        return DateUtil.dateToString(temp, "yyyy-MM-dd");
    }

    /**
     * string date 的上一个日期
     *
     * @param date date 字符串
     * @return a {@link String} 上一个日期
     */
    public static String getBeforeDay(String date) {
        Date temp = DateUtil.setDate(date, -1);
        return DateUtil.dateToString(temp, "yyyy-MM-dd");
    }

    /**
     * 添加几个月之后的日期, 此方法针对2月份做过处理
     * @param date
     * @param month
     * @return
     */
    public static String getDateStrLastMonth(Date date, int month) {
        return DateUtil.dateToString(getDateStrLastMonthDate(date, month), "yyyy-MM-dd");
    }

    /**
     *  添加几个月之后的日期, 此方法针对2月份做过处理
     * @param date
     * @param month
     * @return
     */
    public static Date getDateStrLastMonthDate(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(2, c.get(2) + month);
        //处理2月份的特殊情况
        if (c.get(Calendar.MONTH) == 2 && (c.get(Calendar.DAY_OF_MONTH) == 2 || c.get(Calendar.DAY_OF_MONTH) == 3)) {
            //3.2,3.3 计算后的日期是3.2号,判断是否真正的是3.2号
            Calendar old = Calendar.getInstance();
            old.setTime(date);
            if (old.get(Calendar.DAY_OF_MONTH) == 31 && c.get(Calendar.DAY_OF_MONTH) == 3) {
                c.add(Calendar.DAY_OF_MONTH, -2);
            }else {
                c.add(Calendar.DAY_OF_MONTH, -1);
            }
        }
        return c.getTime();
    }

    public static int getBetweenDays(Date date1, Date date2) {

        Calendar before = Calendar.getInstance();
        Calendar after = Calendar.getInstance();
        if (date1.after(date2)) {
            before.setTime(date2);
            after.setTime(date1);
        } else {
            before.setTime(date1);
            after.setTime(date2);
        }
        int days = 0;
        int startDay = before.get(Calendar.DAY_OF_YEAR);
        int endDay = after.get(Calendar.DAY_OF_YEAR);
        int startYear = before.get(1);
        int endYear = after.get(1);
        before.clear();
        before.set(startYear, 0, 1);

        while (startYear != endYear) {
            before.set(startYear++, 11, 31);
            days += before.get(6);
        }
        int rtn = days + endDay - startDay;
        if (date1.after(date2)) {
            rtn = 0 - rtn;
        }
        return rtn;
    }

    public static Object deepCopy(Object o) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(o);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }

    public static String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        String rtnStr = "";
        if (cookies != null && cookies.length > 0) {
            // 读取cookie
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if ((cookie != null) && (cookie.getName() != null) && cookie.getName().equals(key)) {
                    rtnStr = cookie.getValue();
                    break;
                }
            }
        }
        return rtnStr;
    }

//    public static Long getUserCityId(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        Cookie cookie = null;
//        String rtnStr = "";
//        if (cookies != null && cookies.length > 0) {
//            // 读取cookie
//            for (int i = 0; i < cookies.length; i++) {
//                cookie = cookies[i];
//                if ((cookie != null) && (cookie.getName() != null) && cookie.getName().equals(Constants.KEY_USER_CITYID)) {
//                    rtnStr = cookie.getValue();
//                    break;
//                }
//            }
//        }
//        if (StringUtils.isBlank(rtnStr)) {
//            return Constants.CITYID;
//        } else {
//            return Long.parseLong(rtnStr);
//        }
//    }


    public static <T> T jsonToObject(String objStr, Class<T> c) {
        if (objStr != null && !"".equals(objStr)) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(objStr, c);
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static <T> List<T> jsonToList(String objStr, Class<T> c) {
        if (objStr != null && !"".equals(objStr)) {
            try {
                List<T> list;
                ObjectMapper mapper=new ObjectMapper();
                mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
                JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, c);
                list = mapper.readValue(objStr, javaType);
                return list;
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static String objectToJson(Object o) {
        return objectToJson(o, false);
    }

    public static String objectToJson(Object o, boolean escapeNonAscii) {
        StringWriter str = new StringWriter();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (escapeNonAscii) {
                objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            }
            objectMapper.writeValue(str, o);
            return str.toString();
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    /**
     * make object json
     *
     * @param list
     * @return
     */
    public static JSON makeJson(Object[] list) {
        if (null == list) {
            return null;
        }
        boolean isAllKeyString = true;
        for (int i = 0; i < list.length - 1; i += 2) {
            if (list[i] == null || !(list[i] instanceof String)) {
                isAllKeyString = false;
                break;
            }
        }
        if (list.length % 2 != 0 || !isAllKeyString) {
            JSONArray jsonArray = new JSONArray();
            for (Object item : list) {
                if (item instanceof Object[][]) {
                    item = makeJson((Object[][]) item);
                } else if (item instanceof Object[]) {
                    item = makeJson((Object[]) item);
                }
                jsonArray.add(item);
            }
            return jsonArray;
        }
        JSONObject json = new JSONObject(true);
        for (int i = 0; i < list.length - 1; i += 2) {
            String key = (String) list[i];
            Object value = list[i + 1];
            if (value instanceof Object[][]) {
                value = makeJson((Object[][]) value);
            } else if (value instanceof Object[]) {
                value = makeJson((Object[]) value);
            }
            json.put(key, value);
        }
        return json;
    }

    /**
     * make array json
     *
     * @param list
     * @return
     */
    public static JSON makeJson(Object[][] list) {
        JSONArray json = new JSONArray();
        for (Object[] kv : list) {
            json.add(makeJson(kv));
        }
        return json;
    }

    public static String makeJsonString(Object[] list) {
        return JSONObject.toJSONString(makeJson(list), SerializerFeature.WriteMapNullValue);
    }

    public static String makeJsonString(Object[][] list) {
        return JSONObject.toJSONString(makeJson(list), SerializerFeature.WriteMapNullValue);
    }

    public static Map<String, Object> jsonToMap(String jsonString) {
        return jsonToMap(JsonObjectTools.getObject(jsonString, JSONObject.class));
    }

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();
        if (json != null) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keySet().iterator();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.size(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }


    //getHeadersInfo
    public static Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String,String >();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    //计算两时间差,按照标准自然日计算(.年.月.日)
    public static String getDiffDay2(String rentStart, String rentEnd) {
        if (StringUtils.isNotBlank(rentStart) && StringUtils.isNotBlank(rentEnd)) {
            try {
                Date start = DateUtil.stringToDate(rentStart);
                Date end = DateUtil.stringToDate(rentEnd);

                Calendar start1 = Calendar.getInstance();
                start1.setTime(start);

                Calendar end1 = Calendar.getInstance();
                end1.setTime(end);
                int year1 = start1.get(Calendar.YEAR);
                int month1 = start1.get(Calendar.MONTH);
                int day1 = start1.get(Calendar.DAY_OF_MONTH);

                int year2 = end1.get(Calendar.YEAR);
                int month2 = end1.get(Calendar.MONTH);
                int day2 = end1.get(Calendar.DAY_OF_MONTH);

                String str = "";
                int year = (year2 - year1);
                int month = month2 - month1;
                int day = day2 - day1;
                if (day < 0) {
                    //获取日期的最后一天
                    Calendar ca = Calendar.getInstance();
                    ca.setTime(start);
                    ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
                    int lastDay = ca.get(Calendar.DAY_OF_MONTH);
                    day = lastDay - day1 + day2;
                    month--;
                }
                if (month < 0) {
                    //最大月份是11(0-11)
                    month = 11 - month1 + month2;
                    year--;
                }

                if (year > 0) {
                    str = year + "年";
                }
                if (month > 0) {
                    str += month + "个月";
                }
                if (day > 0) {
                    str += day + "天";
                }
                return str;
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }

    //计算两时间差,按照 日期计算,yuxiaor自己的算法(.年.月.日)
    public static String getDiffDay(String rentStart, String rentEnd) {
        if (StringUtils.isNotBlank(rentStart) && StringUtils.isNotBlank(rentEnd)) {
            int days = 0;
            int month = 0;
            int year = 0;
            if (DateUtil.addDay(DateUtil.getDateStrLastMonthDate(DateUtil.stringToDate(rentStart), 1), -1).after(DateUtil.stringToDate(rentEnd))) {//合同租期不足一个月，按照间夜计算租金
                days = DateUtil.getDayBetween(DateUtil.stringToDate(rentEnd), DateUtil.stringToDate(rentStart));
            } else {
                month = DateUtil.getMonthsBetween(DateUtil.stringToDate(rentStart), DateUtil.stringToDate(rentEnd));
                if (DateUtil.dateToString(DateUtil.addDay(DateUtil.getDateStrLastMonthDate(DateUtil.stringToDate(rentStart), month), -1), "yyyy-MM-dd").equals(rentEnd)) {
                    days = 0;
                } else {
                    //最后一个月不满整月
                    days = DateUtil.getDayBetween(DateUtil.stringToDate(rentEnd), DateUtil.addDay(DateUtil.getDateStrLastMonthDate(DateUtil.stringToDate(rentStart), month - 1), -1));
                    month--;
                }
            }
            year = (month / 12);
            month = month - (12 * year);
            String str = "";
            if (year > 0) {
                str = year + "年";
            }
            if (month > 0) {
                str += month + "个月";
            }
            if (days > 0) {
                str += days + "天";
            }
            return str;
        }
        return "";
    }

    //获取12个月份,从当前月份开始
    public static List<Integer> get12Months() {
        int m = Calendar.getInstance().get(Calendar.MONTH) + 1;
        List<Integer> rtn = new ArrayList<Integer>();
        for (int i = 0, k = 0; i < 12; i++) {
            if (m <= 12) {
                rtn.add(m++);
            } else {
                rtn.add(++k);
            }
        }
        return rtn;
    }

    public static List<String> getMonthList(Date start, Date end, int cycle) {
        int months = DateUtil.getMonthsBetween(start, end);
        //生成租金的应付明细
        int payNums = months % cycle > 0 ? (months / cycle) + 1 : (months / cycle);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < payNums; i++) {
            String s = DateUtil.dateToString(YuxiaorUtils.getDateStrLastMonthDate(start, i * cycle), "yyyy-MM-dd");
            String s1 = DateUtil.dateToString(DateUtil.addDay(YuxiaorUtils.getDateStrLastMonthDate(start, (i + 1) * cycle), -1), "yyyy-MM-dd");
            list.add(s + "," + s1);
        }
        return list;
    }

    /**
     * 新增租客 随即生成一个 图像
     *
     * @param gender 0 ,女;1,男
     * @return
     */
    public static String randomFlatmatePhoto(int gender) {
        String userType = "b";
        if (gender == 0) {
            userType = "g";
        }
        // 1- 15张默认图片[1,15]
        String url = "user-default-img-" + userType + "-";
        int a = new Random().nextInt(33) + 1;
        return url + a + ".png";
    }


    /**
     * 替换掉字符串中的4个字节的字符
     * @param str
     * @return
     */
    public static String replace4ByteStr(String str){
        //CharUtils.isAscii((char) tt) //1个字节的字符
        //(tt & 0xE0) == 0xC0 //2个字节的字符
        //(tt & 0xF0) == 0xE0 //3个字节的字符
        //(tt & 0xF8) == 0xF0 //4个字节的字符

        String rtn ="";
        if(StringUtils.isNotBlank(str)){
            try {
                byte[] t = str.getBytes("UTF-8");
                //获取有效字符的长度
                int len = 0;
                for (int i = 0; i < t.length;) {
                    if ((t[i] & 0xF8) == 0xF0) {
                        //4个字节的字符,开始的第一个字符
                        i+= 4;
                    }else{
                        //拼接有效的字符
                        i++;
                        len++;
                    }
                }

                //拼接byte
                if (len >0) {
                    byte[] bytes = new byte[len];
                    for (int i = 0,j=0; i < t.length;) {
                        if ((t[i] & 0xF8) == 0xF0) {
                            //4个字节的字符,开始的第一个字符
                            i+= 4;
                        }else{
                            //拼接有效的字符
                            bytes[j] = t[i];
                            i++;
                            j++;
                        }
                    }
                    rtn = new String(bytes,"utf-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(rtn.length() == 0) {
            rtn = "" + System.currentTimeMillis() / 1000;
        }
        return rtn;
    }

    /**
     * 数组 转化为字符串
     * @param array
     * @param splt 分隔符,默认逗号(,)
     * @return
     */
    public static String array2str(String[] array,String splt){
        if(array != null && array.length >0){
            if(StringUtils.isBlank(splt)){
                splt = ",";
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; i++) {
                if(i > 0){
                    sb.append(splt);
                }
                sb.append(array[i]);
            }
            return sb.toString();
        }else{
            return null;
        }
    }

    /**
     * 验证手机号
     * @param mobilePhone
     * @return
     */
    public static boolean valiMobilePhone(String mobilePhone){
        String mb = mobilePhone;
        return Pattern.compile("^(13|14|15|17|18)\\d{9}$").matcher(mb).find();
    }

    public static void main(String[] args) {


    }

}
