
# cordova自定义插件

---

> ==注意：存放自定义cordova插件目录不能有空格可能会报错==
>
> 有个toast和卸载应用的例子

## cordova的安装
1. 下载[node.js](https://nodejs.org/en/download/)，安装完成后你可以在命令行中使用node和npm.
2. 安装cordova使用node.js的npm工具。打开控制台输入
>     npm install -g cordova

-g是全局安装cordova。安装完成后就可以在命令行使用cordova命令。

## 安装plugman
cordova需要用plugman来创建自定义插件

> 命令：npm install -g plugman

## 用cordova创建android工程

#### 1.创建cordova工程

首先在电脑中创建一个cordova工程的目录，然后进入到创建的目录里。之后就可以开始创建cordova工程，下面是创建工程的代码和实例图。

> cordova create demo com.lmr.android
>
> demo --> 工程名  ——  com.lmr.android --> 包名

![image](http://i63.tinypic.com/2uhp8h2.png)

创建成功后的目录：

![image](http://i67.tinypic.com/1z1r5n4.png)

#### 2.生成android工程

先进入到刚刚创建的cordova目录中，然后开始创建android工程。
> cordova platform add android 
>
> android --> 创建的平台名也可以是iOS等

下图是创建成功的情况，失败的原因有可能是android的环境没有配好。
![image](http://i66.tinypic.com/t5n2va.png)

生成android工程的目录
![image](http://i64.tinypic.com/2z3rxbs.png)

生成之后我们开始导入到android studio中：

第一步：选择打开本地已有的android工程。
![image](http://i68.tinypic.com/nc1pmx.png)
第二步：找到刚刚创建好的android工程导入。
![image](http://i65.tinypic.com/29kugdj.png)

导入之后目录如下：

![image](http://i64.tinypic.com/b4axdf.png)

至此就已经把cordova插件开头的部分创建好了。

## 使用cordova官方提供的插件

首先进入到创建好的android工程目录下，然后再进行添加插件操作：

```
cordova plugin add cordova-plugin-camera
```

![image](http://i67.tinypic.com/2i05f11.png)

添加成功后的Android目录的变化

![image](http://i66.tinypic.com/5cd6jr.png)

#### 使用增加的插件

> 可以在官网查到 [cordova官网camera插件](hhttps://cordova.apache.org/docs/en/latest/reference/cordova-plugin-camera/index.html#module_camera.getPicture)

使用插件的位置，在Android工程下找到index.html和index.js这两个文件。

![image](http://i68.tinypic.com/15drxo8.png)

在index.html文件中添加一个测试按钮。


```
<button id="test">test</button>
```

![image](http://i65.tinypic.com/292a6pu.png)

在index.js文件中添加：

```
//前面的test是之前那个button的按钮，后面的test是方法名，给按钮注册点击事件
document.getElementById("test").addEventListener("click",test);
//点击事件绑定的方法
function test(){
    //调用添加的camera插件
    // onSuccess:是调用成功的返回事件；onFail：是调用失败的返回事件
    navigator.camera.getPicture(onSuccess,onFail);
}
//成功的返回事件
function onSuccess() {
    console.log("Camera cleanup success.")
}
//失败的返回事件
function onFail(message) {
    alert('Failed because: ' + message);
}
```
然后运行起来项目，下面是我运行起来的效果。点击test按钮，会打开手机上的相机，效果图就不放了。

![image](http://i63.tinypic.com/oanbwx.png)

## 创建自定义插件 

==注意：创建插件是先cd到你要创建的目录下，存放自定义cordova插件目录不能有中午和空格可能会报错==

先在电脑上创建一个cordova插件的目录，然后跳转到该目录，开始创建cordova插件。

![image](http://i67.tinypic.com/ethcu9.png)

> plugman create --name toast-plugin --plugin_id toast-plugin --plugin_version 1.0.0
>
> --name toast-plugin --> 插件名
>
> --plugin_id toast-plugin --> 插件ID
>
> --plugin_version 1.0.0 --> 插件版本号

生成插件的目录如下
- toast-plugin
  - src
    - android
      - android的目录
  - www

#### 编写ToastDemo.java用于被调用

可以在刚刚创建的Android工程下面编写这个代码，具体代码如下：

> execute方法是插件被调用时，会把操作和参数以及回调传递过来。

```
package com.lmr.android;

import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

/**
 * ToastDemo
 *
 * @author lmr
 * @date 2018-08-19
 */
public class ToastDemo extends CordovaPlugin {

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if ("show".equals(action)){
            // 获取activity和context --> cordova.getActivity()和cordova.getContext()
            Toast.makeText(cordova.getContext(),args.getString(0),Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}

```

把这个文件复制到刚刚生成的插件目录下

![image](http://i66.tinypic.com/119xqwi.png)

#### 编辑plugin.xml文件

修改plugin.xml,代码都有注释，如下：

```
<?xml version='1.0' encoding='utf-8'?>
<plugin id="toast-plugin" version="1.0.0" xmlns="http://apache.org/cordova/ns/plugins/1.0" xmlns:android="http://schemas.android.com/apk/res/android">
    <name>toast-plugin</name>
    <js-module name="ToastShow" src="www/toast-plugin.js">
        <clobbers target="ToastShow" />
    </js-module>

	<!--添加Android平台  -->
    <platform name="android">
        <config-file target="res/xml/config.xml" parent="/*">  
		    <!-- JS调用时的名字 -->
            <feature name="ToastShow">  
			    <!-- value：的值是对应的插件中ToastDemo.java存放的路径 --> 
                <param name="android-package" value="com.lmr.android.ToastDemo"/>
            </feature>  
        </config-file>  
		<!-- src:是插件里存放对应.java的路径， target-dir:安装插件时把.java文件存放的位置，要和上面的value路径对应 -->
        <source-file src="src/android/ToastDemo.java" target-dir="src/com/lmr/android" />
    </platform> 
</plugin>
```


#### 编辑www目录下的toast-plugin.js文件

打开后是这样的：

```
var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'toast-plugin', 'coolMethod', [arg0]);
};

```

修改为


```
var exec = require('cordova/exec');

// ToastShow： 是plugin.xml文件中的feature标签 name属性
// show：是js中调用的方法名
// [arg0]: 表示一个参数，[arg0,arg1]:表示两个参数
exports.show = function (arg0, success, error) {
    exec(success, error, 'ToastShow', 'show', [arg0]);
};
```

## 初始化插件

进入到插件目录，初始化插件


```
npm init
```
效果图如下，输入信息时有括号的按照括号里面的输入，没有的可以跳过。

![image](http://i67.tinypic.com/34siadc.png)

## 向项目中添加自定义插件

上面基本上就完成了一个简单的自定义插件的制作，接下来把插件添加到之前创建的Android工程中测试，制作是否成功。

添加的方法和上面一样，先进入到platforms目录下

然后输入

```
// 插件的本地目录
cordova plugin add D:\CordovaPlugin\plugins\toast-plugin
```

添加成功
![image](http://i66.tinypic.com/2u8clrs.png)

然后在Android studio中调用测试。调用方式通俗的讲就是toast-plugin.js文件中的那两个参数。

```
  ToastShow.show("123456");
```

![image](http://i63.tinypic.com/2hfrll1.png)

成功！！

![image](http://i65.tinypic.com/sxn69g.gif)

基础内容大概就这些，后面还会在写一个复杂一点的插件教程，大家有疑问可以在评论中问，看到了会回复，有错误也欢迎指出，共同学习。
