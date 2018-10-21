package cn.com.zf.library;

import android.Manifest;

import java.util.ArrayList;
import java.util.List;

/**
 * author:zhufu
 * email:zhufui@sina.com
 * time:2018/10/21
 * desc:
 * version:1.0
 */
public class PermissionGroup {

    /**
     * 电话
     *
     * @return
     */
    public static String[] PHONE() {
        return new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.WRITE_CALL_LOG,
                Manifest.permission.USE_SIP,
                Manifest.permission.PROCESS_OUTGOING_CALLS};
    }

    /**
     * 短信
     *
     * @return
     */
    public static String[] SMS() {
        return new String[]{Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_WAP_PUSH,
                Manifest.permission.RECEIVE_MMS};
    }

    /**
     * 相机
     *
     * @return
     */
    public static String[] CAMERA() {
        return new String[]{Manifest.permission.CAMERA};
    }

    /**
     * sd卡
     *
     * @return
     */
    public static String[] SD() {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
    }

    /**
     * 联系人
     *
     * @return
     */
    public static String[] CONTACTS() {
        return new String[]{Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.GET_ACCOUNTS};
    }

    /**
     * 定位
     *
     * @return
     */
    public static String[] LOCATION() {
        return new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
    }

    /**
     * 日历
     *
     * @return
     */
    public static String[] CALENDAR() {
        return new String[]{Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR};
    }

    /**
     * 麦克风
     *
     * @return
     */
    public static String[] MICROPHONE() {
        return new String[]{Manifest.permission.RECORD_AUDIO};
    }

    /**
     * 传感器
     *
     * @return
     */
    public static String[] SENSORS() {
        return new String[]{Manifest.permission.BODY_SENSORS};
    }

    public static String[] build(String[]... permissionArray) {
        List<String> list = new ArrayList<>();
        for (String[] permissions : permissionArray) {
            for (String permission : permissions) {
                list.add(permission);
            }
        }

        return list.toArray(new String[list.size()]);
    }
}
