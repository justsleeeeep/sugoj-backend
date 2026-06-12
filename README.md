# SpringBoot 项目初始模板


Java SpringBoot 项目初始模板，整合了常用框架和示例代码，大家可以在此基础上快速开发自己的项目。

## 模板功能

- Spring Boot 2.7.0（贼新）
- Spring MVC
- MySQL 驱动
- MyBatis
- MyBatis Plus
- Spring Session Redis 分布式登录
- Spring AOP
- Apache Commons Lang3 工具类
- Lombok 注解
- Swagger + Knife4j 接口文档
- Spring Boot 调试工具和项目处理器
- 全局请求响应拦截器（记录日志）
- 全局异常处理器
- 自定义错误码
- 封装通用响应类
- 示例用户注册、登录、搜索功能
- 示例单元测试类
- 示例 SQL（用户表）

访问 localhost:7529/api/doc.html 在线调试接口

## Improve
1.我现在问题每submit一次 都会在数据库里原子操作提交数+1
但是这样如果遇到同时许多请求都是提交同一个题目 由于数据库的加锁就会使
程序变慢 可以redis(INCR)+异步刷库
2.如果确定沙箱不会有线程安全问题可以用单例的工厂模式

##测试A+B
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

```java
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(a+b);
    }
}
```
