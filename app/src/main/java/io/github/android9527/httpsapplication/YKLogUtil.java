package io.github.android9527.httpsapplication;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Wbaokang-PC on 2016/3/8.
 * modify by chenfeiyue
 */
public class YKLogUtil {

    private static Boolean MYLOG_SWITCH = true; // 日志文件总开关
    private static char MYLOG_TYPE = 'v';// 输入日志类型，w代表只输出告警信息等，v代表输出所有信息

    public static void w(String tag, Object msg) { // 警告信息
        writeLog(tag, msg.toString(), 'w', "", null);
    }

    public static void e(String tag, Object msg) { // 错误信息
        writeLog(tag, msg.toString(), 'e', "", null);
    }

    public static void d(String tag, Object msg) {// 调试信息
        writeLog(tag, msg.toString(), 'd', "", null);
    }

    public static void i(String tag, Object msg) {//
        writeLog(tag, msg.toString(), 'i', "", null);
    }

    public static void v(String tag, Object msg) {
        writeLog(tag, msg.toString(), 'v', "", null);
    }

    public static void w(String tag, String text) {
        writeLog(tag, text, 'w', "", null);
    }

    public static void e(String tag, String text) {
        writeLog(tag, text, 'e', "", null);
    }

    public static void json(String tag, String className, String text) {
        writeLog(tag, text, 'j', "", className);
    }

    public static void d(String tag, String text) {
        writeLog(tag, text, 'd', "", null);
    }

    public static void i(String tag, String text) {
        writeLog(tag, text, 'i', "", null);
    }

    public static void v(String tag, String text) {
        writeLog(tag, text, 'v', "", null);
    }

    public static void w(String tag, Object msg, String fileName) { // 警告信息
        writeLog(tag, msg.toString(), 'w', fileName, null);
    }

    public static void e(String tag, Object msg, String fileName) { // 错误信息
        writeLog(tag, msg.toString(), 'e', fileName, null);
    }

    public static void d(String tag, Object msg, String fileName) {// 调试信息
        writeLog(tag, msg.toString(), 'd', fileName, null);
    }

    public static void i(String tag, Object msg, String fileName) {//
        writeLog(tag, msg.toString(), 'i', fileName, null);
    }

    public static void v(String tag, Object msg, String fileName) {
        writeLog(tag, msg.toString(), 'v', fileName, null);
    }

    public static void w(String tag, String text, String fileName) {
        writeLog(tag, text, 'w', fileName, null);
    }

    public static void e(String tag, String text, String fileName) {
        writeLog(tag, text, 'e', fileName, null);
    }

    public static void d(String tag, String text, String fileName) {
        writeLog(tag, text, 'd', fileName, null);
    }

    public static void i(String tag, String text, String fileName) {
        writeLog(tag, text, 'i', fileName, null);
    }

    public static void v(String tag, String text, String fileName) {
        writeLog(tag, text, 'v', fileName, null);
    }

    /**
     * 根据tag, msg和等级，输出日志
     *
     * @param tag
     * @param msg
     * @param level
     * @return void
     * @since v 1.0
     */

    private static void writeLog(String tag, String msg, char level,
                                 String fileName, String className) {
        if (MYLOG_SWITCH) {
            if ('e' == level && ('e' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) { // 输出错误信息
                Log.e(tag, getMsgWithLineNumber(msg));
            } else if ('w' == level && ('w' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.w(tag, getMsgWithLineNumber(msg));
            } else if ('d' == level && ('d' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.d(tag, getMsgWithLineNumber(msg));
            } else if ('i' == level && ('d' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.i(tag, getMsgWithLineNumber(msg));
            } else if ('j' == level && ('d' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
            } else {
                Log.v(tag, getMsgWithLineNumber(msg));
            }
        }
    }

    private static String classname;

    private static ArrayList<String> methods;

    static {
        classname = YKLogUtil.class.getName();
        methods = new ArrayList<>();

        Method[] ms = YKLogUtil.class.getDeclaredMethods();
        for (Method m : ms) {
            methods.add(m.getName());
        }
    }


    /**
     * 获取带行号的日志信息内容。
     *
     * @param msg 日志内容。
     * @return 带行号的日志信息内容。
     */
    private static String getMsgWithLineNumber(String msg) {
        try {
            for (StackTraceElement st : (new Throwable()).getStackTrace()) {
                if (!classname.equals(st.getClassName()) && !methods.contains(st.getMethodName())) {
                    return "line + " + st.getLineNumber() + "----->" + st.getMethodName() + "(): " + msg;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
}
