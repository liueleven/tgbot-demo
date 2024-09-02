# Java telegram bot demo


# 实现功能
使用的是拉取模式，没有对 offset 进行处理，实际生产需要进行持久化处理。

1. 初始化菜单
2. 消息回声
3. 图片回声

# 配置说明
resources 目录下新建文件 config.properties，内容如下：
```angular2html
bot.username=xxx
bot.token=xxx
```

# 运行
函数入口：Main.java
因为在外网，所以需要配置下代理。