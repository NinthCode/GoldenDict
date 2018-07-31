# GoldenDict翻译插件(已加入百度翻译、有道翻译)
## 构建文档
- 首先需要给common模块执行install命令安装Jar到本地
- 然后对要构建的项目执行package assembly:single打Jar包
## 使用文档

### 插件用法
- 依次选择GoldenDict词典的编辑->词典->程序->添加
- 类型选择HTML
- 名称随意，然后命令行处添加，比如你想添加百度翻译插件，就输入以下命令：
```
java -jar -Dfile.encoding=utf-8 D:\GoldenDict\Dict\baiduTranslate.jar %GDWORD%
```
D:\GoldenDict\Dict\baiduTranslate.jar是你的翻译插件的路径。

### 百度翻译GoldenDict插件帮助文档

---
首次使用
百度翻译插件在第一次使用时，会在Jar包目录下生成一个配置文件，内如如下：
```json
{
  "appId": null,
  "appKey": null,
  "devMode": true,
  "langDetectAppreciationWordNum": 3,
  "langDetectLength": 40
}
```
1. 其中appId与appKey需要注册成为百度翻译开放平台开发者才可以获得：
百度翻译开放平台地址：http://api.fanyi.baidu.com/api/trans/product/index
2. devMode字段，设置为true开启开发者模式，会把变量名拆分。
常规使用
3. langDetectLength，语言类型识别取样长度阈值（不需要设置）
4. langDetectAppreciationWordNum，单词数量阈值（不需要设置）

正常使用时，将需要翻译的非中文字（词）或一段文字（词）输入，即可自动识别并翻译为中文，如果输入为中文，则会输出英文翻译。

强制源语言类型与目标语言类型

- 使用如下方式来指定源语言类型a与目标语言类型b
      [a->b]:待翻译词语
- 使用如下方式来指定源语言类型a
      [a->?]:待翻译词语
- 使用如下方式来指定目标语言类型b
      [?->b]:待翻译词语

支持的语言类型

| 命令名  | 中文名  |
| :--: | :--: |
|  en  |  英语  |
|  zh  |  中文  |
|  jp  |  日语  |
| kor  |  韩语  |
| wyw  | 文言文  |
|  ru  |  俄语  |
|  de  |  德语  |
| yue  |  粤语  |
| cht  |  繁体  |

### 有道翻译GoldenDict插件帮助文档

---
有道翻译插件在第一次使用时，会在Jar包目录下生成一个配置文件，内如如下：
```json
{
  "appKey": "",
  "easy": false,
  "secretKey": "",
  "devMode":false
}
```
1. 其中appKey与secretKey需要申请有道智云翻译服务才可以获得，申请方式：
- 注册有道智云帐号并登录到控制台页面
- 创建一个翻译实例：控制台 > 自然语言翻译 > 翻译实例 > 创建翻译实例
- 创建一个应用并绑定翻译服务：控制台 > 应用管理 > 我的应用 > 创建应用 > 绑定服务
- 在插件中绑定应用——将上步骤的应用和应用密钥填写到插件配置的相应位置上
2. easy字段：true:简单模式，只提供简单的释义，false：复杂模式，可以输出详细释义
3. devMode字段，设置为true开启开发者模式，会把变量名拆分。

### google翻译GoldenDict插件帮助文档

- 首先，你需要能上“网”
- 其次，没什么可以配置的。
