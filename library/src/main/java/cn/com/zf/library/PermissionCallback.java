package cn.com.zf.library;

/**
 * author  : zhufu
 * email   : zhufui@sina.com
 * time    : 2018/2/4 0004
 * desc    : 动态权限回调类
 * version : 1.0
 */

public interface PermissionCallback {
    void permissionGrant(int requestCode);

    void permissionDenied(int requestCode);
}
