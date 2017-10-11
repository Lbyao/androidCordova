cordova.define("toast-plugin.plugin-toast", function(require, exports, module) {
var exec = require('cordova/exec');

exports.show = function(arg0, success, error) {
    exec(success, error, "MyToast", "show", [arg0]);
};

});
