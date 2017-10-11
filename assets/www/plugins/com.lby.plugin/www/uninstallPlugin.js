cordova.define("com.lby.plugin.uninstallPlugin", function(require, exports, module) {
var exec = require('cordova/exec');

var uninstallPlugin ={
	showInfo:function(info,success,error){
		exec(success, error, "UninstallPlugin", "showInfo", [info]);
	},
	uninstallApp:function(success,error){
		exec(success, error, "UninstallPlugin", "uninstallApp", []);
	}
}

module.exports = uninstallPlugin;
exports.coolMethod = function(arg0, success, error) {
    exec(success, error, "uninstallPlugin", "coolMethod", [arg0]);
};

});
