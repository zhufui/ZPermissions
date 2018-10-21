package cn.com.zf.zpermissions;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.com.zf.library.PermissionCallback;
import cn.com.zf.library.PermissionGroup;
import cn.com.zf.library.ZPermissions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PermissionCallback {

    String[] permissions = {Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton bt = findViewById(R.id.bt);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ZPermissions.requestPermissions(MainActivity.this,
                PermissionReqCode.CODE0, MainActivity.this, PermissionGroup.build(
                        PermissionGroup.SENSORS()
                ));
    }

    @Override
    public void permissionGrant(int requestCode) {
        callPhone();
    }

    @Override
    public void permissionDenied(int requestCode) {
        Toast.makeText(this, "请去设置->应用->" + getString(R.string.app_name) + "->权限,打开权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        ZPermissions.onRequestPermissionsResult(requestCode, grantResults, PermissionReqCode.CODE0);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @SuppressLint("MissingPermission")
    public void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }
}
