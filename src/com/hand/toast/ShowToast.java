package com.hand.toast;

import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class ShowToast extends CordovaPlugin {
    private CallbackContext mCallbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.mCallbackContext = callbackContext;
        if ("toast".equals(action)) {
            String msg = args.getString(0);
            Toast.makeText(cordova.getActivity(), msg, Toast.LENGTH_SHORT).show();
            callbackContext.success("success");
            return true;
        }
        mCallbackContext.error("error");
        return false;
    }
} 