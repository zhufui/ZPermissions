package cn.com.zf.zpermissions;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.widget.Toast;

import cn.com.zf.library.PermissionCallback;
import cn.com.zf.library.PermissionGroup;
import cn.com.zf.library.ZPermissions;

public class MainActivity extends AppCompatActivity implements PermissionCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton bt = findViewById(R.id.bt);
        AppCompatButton bt1 = findViewById(R.id.bt1);
        bt.setOnClickListener((v) -> {
            ZPermissions.requestPermissions(MainActivity.this,
                    PermissionReqCode.CODE0, MainActivity.this, PermissionGroup.build(
                            PermissionGroup.CAMERA()
                    ));
        });
        bt1.setOnClickListener((v) -> {
            ZPermissions.requestPermissions(MainActivity.this,
                    PermissionReqCode.CODE1, MainActivity.this, PermissionGroup.build(
                            PermissionGroup.SD()
                    ));
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZPermissions.recycleCallback();
    }

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
        createDialog();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ZPermissions.onRequestPermissionsResult(requestCode, grantResults);
    }

    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("为了保证应用的正常使用，需要您在设置中点击【权限】并打开以下权限：存储、位置、相机、电话、通讯录");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gotoSettings();
                dialog.dismiss();
                finish();
            }
        });
        builder.setCancelable(false);
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
        builder.create().show();
    }

    private void gotoSettings() {
        Uri packageURI = Uri.fromParts("package", MainActivity.this.getPackageName(), null);
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
