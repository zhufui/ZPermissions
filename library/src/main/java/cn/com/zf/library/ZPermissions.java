package cn.com.zf.library;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.lang.ref.WeakReference;

/**
 * author  : zhufu
 * email   : zhufui@sina.com
 * time    : 2018/2/4 0004
 * desc    : 动态权限请求
 * version : 1.0
 */
public class ZPermissions {

    private static PermissionCallback permissionCallback;

    /**
     * 请求权限
     *
     * @param activity
     * @param requestCode
     * @param pc
     * @param permissions
     */
    public static void requestPermissions(Activity activity, int requestCode, PermissionCallback pc, String[] permissions) {
        permissionCallback = pc;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionCallback.permissionGrant(requestCode);
            return;
        }

        boolean checkResult = true;
        for (int i = 0; i < permissions.length; i++) {
            if (!checkPermission(activity, permissions[i])) {
                checkResult = false;
                break;
            }
        }

        if (checkResult) {
            permissionCallback.permissionGrant(requestCode);
        } else {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }

    /**
     * 检查权限是否通过
     *
     * @param context
     * @param permission
     * @return
     */
    private static boolean checkPermission(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    /**
     * 检查一组权限是否通过
     *
     * @param context
     * @param permissionArrays
     * @return
     */
    public static boolean checkPermission(Context context, String[]... permissionArrays) {
        for (String[] permissionArray : permissionArrays) {
            for (String permission : permissionArray) {
                if (!checkPermission(context, permission)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 权限的回调
     *
     * @param requestCode
     * @param grantResults
     */
    public static void onRequestPermissionsResult(int requestCode, int[] grantResults) {
        boolean grantResult = true;
        for (int i = 0, size = grantResults.length; i < size; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                grantResult = false;
            }
        }

        if (grantResult) {
            permissionCallback.permissionGrant(requestCode);
            return;
        }

        permissionCallback.permissionDenied(requestCode);
    }

    /**
     * 回收回调
     */
    public static void recycleCallback() {
        permissionCallback = null;
    }
}
