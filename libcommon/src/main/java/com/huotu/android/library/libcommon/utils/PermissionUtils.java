package com.huotu.android.library.libcommon.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/7.
 */
public class PermissionUtils {
    public static final Integer REQUEST_CODE_ASK_PERMISSIONS = 12001;

    private Activity activity;
    private String[] permissions;

    public PermissionUtils(Activity activity) {
        this.activity = activity;
        this.permissions = new String[]{};
    }

    public PermissionUtils(Activity activity, String[] permissions) {
        this.activity = activity;
        this.permissions = permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public Boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(activity, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    public String[] getMissingPermissions() {
        List<String> missingPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (!hasPermission(permission)) {
                missingPermissions.add(permission);
            }
        }

        String[] missingPermissionsArray = new String[missingPermissions.size()];
        missingPermissions.toArray(missingPermissionsArray);
        return missingPermissionsArray;
    }

    public Boolean hasAllPermissions() {
        String[] missingPermissions = getMissingPermissions();
        return missingPermissions.length == 0;
    }

    public void requestPermissions(Integer requestCode) {
        if (Build.VERSION.SDK_INT >= 23 && !hasAllPermissions()) {
            activity.requestPermissions(getMissingPermissions(), requestCode);
        }
    }

    public void requestPermissions() {
        requestPermissions(REQUEST_CODE_ASK_PERMISSIONS);
    }
}
