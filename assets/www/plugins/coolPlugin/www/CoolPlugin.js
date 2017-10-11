cordova.define("coolPlugin.CoolPlugin", function(require, exports, module) {
    var exec = require('cordova/exec');

    exports.toast = function(arg0, success, error) {
        exec(success, error, "ShowToast", "toast", [arg0]);
    };
});
