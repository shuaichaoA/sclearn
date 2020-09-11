package com.rq.example;

import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能描述:
 *
 * @author: $
 * @date: $ $
 */
public class test {


    public static void main(String[] args) throws UnsupportedEncodingException {
        String cmd = "23 23 ";
        String cmdMark = "07 00 ";
        String deviceType = "02 ff";// E9N:02 E9:01 E10:00
        String deviceCode = testHex2Asic("881181912332");// 设备编号
        if (deviceCode.length() < 34) {
            int i = 34 - deviceCode.length();
            String collect = Stream.iterate("0", t -> "0").limit(i).collect(Collectors.joining(""));
            deviceCode = deviceCode + collect;
        }
        String enctryMark = "01";
        String smokeEventMessage = smokeEventMessage();
        String s = Integer.toHexString(smokeEventMessage.length() / 2);

        System.out.println(s);


    }
    public static String smokeEventMessage() {
        String dateStr = Long.toString(System.currentTimeMillis() / 1000L);
        String status = "01 ";
        String eventCode = "08 ";
        String effectiveLength = "03 ";
        String smokeStatus = "00 ";
        return dateStr + status + eventCode + effectiveLength + smokeStatus;

    }

    public static String testHex2Asic(String str) throws UnsupportedEncodingException {
        byte[] bytes = str.getBytes("ASCII");
//        String result = new String(bytes, "ASCII");
        String res = bytesToHex(bytes);
        System.out.println(res);
        return res;
    }

    /**
     * 字节数组转16进制
     *
     * @param bytes 需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}


