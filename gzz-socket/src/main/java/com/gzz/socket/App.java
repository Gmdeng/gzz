package com.gzz.socket;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class App {
    private final static Logger log = Logger.getLogger("App");
    public static void main(String[] args) {
        log.info("hello wo");
        log.severe("严重信息");
        log.warning("警示信息");
        log.info("一般信息");
        log.config("设定方面的信息");
        log.fine("细微的信息");
        log.finer("更细微的信息");
        log.finest("最细微的信息");
    }
}
