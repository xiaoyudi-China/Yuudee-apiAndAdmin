package com.xfkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(value = "com.xfkj.mapper")
public class XiaoyudiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(XiaoyudiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(XiaoyudiApplication.class, args);
        System.out.println(
                "          _____                    _____                    _____                    _____          \n" +
                        "         /\\    \\                  /\\    \\                  /\\    \\                  /\\    \\         \n" +
                        "        /::\\____\\                /::\\    \\                /::\\    \\                /::\\    \\        \n" +
                        "       /:::/    /                \\:::\\    \\              /::::\\    \\              /::::\\    \\       \n" +
                        "      /:::/    /                  \\:::\\    \\            /::::::\\    \\            /::::::\\    \\      \n" +
                        "     /:::/    /                    \\:::\\    \\          /:::/\\:::\\    \\          /:::/\\:::\\    \\     \n" +
                        "    /:::/____/                      \\:::\\    \\        /:::/__\\:::\\    \\        /:::/__\\:::\\    \\    \n" +
                        "   /::::\\    \\                      /::::\\    \\      /::::\\   \\:::\\    \\      /::::\\   \\:::\\    \\   \n" +
                        "  /::::::\\    \\   _____    ____    /::::::\\    \\    /::::::\\   \\:::\\    \\    /::::::\\   \\:::\\    \\  \n" +
                        " /:::/\\:::\\    \\ /\\    \\  /\\   \\  /:::/\\:::\\    \\  /:::/\\:::\\   \\:::\\____\\  /:::/\\:::\\   \\:::\\    \\ \n" +
                        "/:::/  \\:::\\    /::\\____\\/::\\   \\/:::/  \\:::\\____\\/:::/  \\:::\\   \\:::|    |/:::/__\\:::\\   \\:::\\____\\\n" +
                        "\\::/    \\:::\\  /:::/    /\\:::\\  /:::/    \\::/    /\\::/   |::::\\  /:::|____|\\:::\\   \\:::\\   \\::/    /\n" +
                        " \\/____/ \\:::\\/:::/    /  \\:::\\/:::/    / \\/____/  \\/____|:::::\\/:::/    /  \\:::\\   \\:::\\   \\/____/ \n" +
                        "          \\::::::/    /    \\::::::/    /                 |:::::::::/    /    \\:::\\   \\:::\\    \\     \n" +
                        "           \\::::/    /      \\::::/____/                  |::|\\::::/    /      \\:::\\   \\:::\\____\\    \n" +
                        "           /:::/    /        \\:::\\    \\                  |::| \\::/____/        \\:::\\   \\::/    /    \n" +
                        "          /:::/    /          \\:::\\    \\                 |::|  ~|               \\:::\\   \\/____/     \n" +
                        "         /:::/    /            \\:::\\    \\                |::|   |                \\:::\\    \\         \n" +
                        "        /:::/    /              \\:::\\____\\               \\::|   |                 \\:::\\____\\        \n" +
                        "        \\::/    /                \\::/    /                \\:|   |                  \\::/    /        \n" +
                        "         \\/____/                  \\/____/                  \\|___|                   \\/____/         \n");
    }
}
