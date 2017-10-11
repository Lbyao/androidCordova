cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "id": "coolPlugin.CoolPlugin",
        "file": "plugins/coolPlugin/www/CoolPlugin.js",
        "pluginId": "coolPlugin",
        "clobbers": [
            "cordova.plugins.CoolPlugin"
        ]
    },
    {
        "id": "toast-plugin.plugin-toast",
        "file": "plugins/toast-plugin/www/plugin-toast.js",
        "pluginId": "toast-plugin",
        "clobbers": [
            "cordova.plugins.plugin-toast"
        ]
    },
    {
        "id": "com.lby.plugin.uninstallPlugin",
        "file": "plugins/com.lby.plugin/www/uninstallPlugin.js",
        "pluginId": "com.lby.plugin",
        "clobbers": [
            "cordova.plugins.uninstallPlugin"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.3.2",
    "coolPlugin": "0.0.1",
    "toast-plugin": "1.0.0",
    "com.lby.plugin": "1.0.0"
};
// BOTTOM OF METADATA
});