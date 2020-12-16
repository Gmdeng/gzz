package com.gzz.socket;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private final static Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static void main(String[] args) {
        log.info("hello wo");
//        log.severe("严重信息");
//        log.warning("警示信息");
        log.info("一般信息");
//        log.config("设定方面的信息");
//        log.fine("细微的信息");
//        log.finer("更细微的信息");
//        log.finest("最细微的信息");
        log.warn("Warn ");
        log.error("Error ");
        log.fatal("Fatal ");
        log.debug("Debug");
    }
}
