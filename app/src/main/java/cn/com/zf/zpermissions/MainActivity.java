package cn.com.zf.zpermissions;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
        Toast.makeText(this, "请去设置->应用->" + getString(R.string.app_name) + "->权限,打开权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ZPermissions.onRequestPermissionsResult(requestCode, grantResults);
    }

    @SuppressLint("MissingPermission")
    public void callPhone() {
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        Uri data = Uri.parse("tel:" + "10086");
//        intent.setData(data);
//        startActivity(intent);
    }
}
