# vueblog

技术栈

- 前端
  - vue
  - element-ui
  - axios
- 后端
  - SpringBoot
  - mybatis plus
  - shiro
  - redis
  - hibernate validatior
  - jwt

开发步骤

- 后端
  - SpringBoot整合Mybatis-Plus
  - 利用Mybatis-Plus代码生成
  - 整合shiro+redis，会话共享
  - shiro整合jwt，身份校验
  - 统一结果封装
  - 实体校验
  - 解决跨域问题
  - 登录接口开发
  - 博客接口开发
- 前端
  - vue整合element-ui，axios
  - 登录页面
  - 博客列表
  - 博客详情

## JWT

登录使用JWT技术。JWT可以生成一个加密的token，作为用户登录的令牌，当用户登录成功之后，发给客户端

请求需要登录的资源或者接口的时候，将token携带，后端验证token是否合法

JWT有三部分组成：A.B.C

A：Header，`{"type":"JWT","alg":"HS256"}`固定

B：playload，存放信息，比如，用户id，过期时间等等，可以被解密，不能存放敏感信息

C：签证，A和B加上秘钥加密而成，只要秘钥不丢失，可以认为是安全的

JWT验证，主要就是验证C部分是否合法

## 登录拦截器

每次访问需要登录的资源的时候都需要在代码中进行判断，一旦登录的逻辑有所改变，代码都得进行变动，非常不合适。

进行统一登录拦截判断：使用拦截器进行登录拦截，遇到需要登录才能访问的接口，如果未登录，拦截器直接返回，并跳转登录页面。

## ThreadLocal内存泄漏

![1660108692855](C:\Users\35141\AppData\Roaming\Typora\typora-user-images\1660108692855.png)

实线代表强引用，虚线代表弱引用。

每一个Thread维护一个ThreadLocalMap，key为使用弱引用的ThreadLocal实例，value为线程变量的副本。

强引用，使用最普遍的引用，一个对象具有强引用，不会被垃圾回收器回收。当内存空间不足，Java虚拟机宁愿抛出OutOfMemoryError错误，使程序异常终止，也不回收这种对象。

如果想取消强引用和某个对象之间的关联，可以显式的将引用赋值为null，这样可以使JVM在合适的时间就会回收该对象。

弱引用，JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象，在Java中，用`java.lang.ref.WeakReference`类来表示。

