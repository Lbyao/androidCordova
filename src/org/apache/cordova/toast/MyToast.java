package org.apache.cordova.toast;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Administrator on 2017-10-11.
 */

public class MyToast extends CordovaPlugin {

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
            throws JSONException {
        Activity activity = this.cordova.getActivity();
        if (action.equals("show")) {
            Intent i = activity.getIntent();

            if (i.hasExtra(Intent.EXTRA_TEXT)) {
                Toast.makeText(cordova.getActivity(), "show", Toast.LENGTH_SHORT).show();
                callbackContext.success(i.getStringExtra(Intent.EXTRA_TEXT));
            } else {
                callbackContext.error("");
            }
            return true;
        }
        return false;
    }

}
