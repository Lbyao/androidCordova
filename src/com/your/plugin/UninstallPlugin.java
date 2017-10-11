package com.your.plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Administrator on 2017-10-11.
 */

public class UninstallPlugin extends CordovaPlugin {

    @Override
    protected void pluginInitialize() {
        super.pluginInitialize();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        final Activity activity = this.cordova.getActivity();
        if (action.equals("showInfo")){
            show(args.getString(0));
        }else if (action.equals("uninstallApp")){
            //这里要输入app包名
            uninstallMyself("com.lby.test.android",activity);
            return true;
        }
        return false;
    }

    /**
     * 弹出手机系统本身的卸载对话框，让用户卸载
     * @param packageName
     * @param activity
     */
    public void uninstallMyself(String packageName,Activity activity){
        if (checkApplication(packageName,activity)){
            Uri packageURI = Uri.parse("package:"+packageName);
            //调用手机系统本身的卸载activity
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(packageURI);
            activity.startActivity(intent);
        }
    }

    /**
     * 匹配你的APP包名是否能找到，然后卸载
     * @param packageName
     * @param activity
     * @return
     */
    public boolean checkApplication(String packageName,Activity activity){
        if (packageName == null||"".equals(packageName)){
            return false;
        }
        try {
            activity.getPackageManager().getApplicationInfo(packageName,PackageManager.MATCH_UNINSTALLED_PACKAGES);
            return true;
        }catch (PackageManager.NameNotFoundException e){
            return false;
        }
    }

    //弹出对话框
    void show(String info){
        Activity activity = this.cordova.getActivity();
        Toast toast = Toast.makeText(activity,info,Toast.LENGTH_SHORT);
        toast.show();
    }
}
