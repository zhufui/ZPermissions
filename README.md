# ZPermissions
在项目中使用方法：
```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

```
dependencies {
	implementation 'com.github.zhufui:ZPermissions:1.0.1'
}
```

<br/>
如果出现support包冲突可以采用下面方式将冲突的包去除


```
dependencies {
	implementation ('com.github.zhufui:ZPermissions:1.0.1'){
    exclude group:'com.android.support'
    }
}
```
