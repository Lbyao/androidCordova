cordova.define("toast-plugin.plugin-toast", function(require, exports, module) {
var exec = require('cordova/exec');

exports.showToast = function(arg0, success, error) {
    exec(success, error, "plugin-toast", "showToast", [arg0]);
};

});
