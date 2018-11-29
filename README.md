# ZPermissions
在项目中使用方法：
```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

```
dependencies {
	implementation 'com.github.zhufui:ZPermissions:1.0.2'
}
```

<br/>
如果出现support包冲突可以采用下面方式将冲突的包去除


```
dependencies {
	implementation ('com.github.zhufui:ZPermissions:1.0.2'){
    exclude group:'com.android.support'
    }
}
```

使用方法：<br/>
requestCode写在一个单独的类中

	public class PermissionReqCode {
	    public static final int CODE0 = 0;
	}


请求权限：

	ZPermissions.requestPermissions(MainActivity.this,
                    PermissionReqCode.CODE0, MainActivity.this, PermissionGroup.build(
                            PermissionGroup.CAMERA()
                    ));

在destory中取消回调

	@Override
    protected void onDestroy() {
        super.onDestroy();
        ZPermissions.recycleCallback();
    }


在onRequestPermissionsResult中的写法：

	@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ZPermissions.onRequestPermissionsResult(requestCode, grantResults);
    }


同意/拒绝权限：

	@Override
    public void permissionGrant(int requestCode) {
        if (requestCode == PermissionReqCode.CODE0) {
            Toast.makeText(this, "相机", Toast.LENGTH_SHORT).show();
            return;
        }
        if (requestCode == PermissionReqCode.CODE1) {
            Toast.makeText(this, "sd卡", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void permissionDenied(int requestCode) {
        Toast.makeText(this, "请去设置->应用->" + getString(R.string.app_name) + "->权限,打开权限", Toast.LENGTH_SHORT).show();
    }

