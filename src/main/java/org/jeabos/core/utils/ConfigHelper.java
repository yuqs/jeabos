package org.jeabos.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigHelper implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 8934566010799356941L;
    
    private static final transient Log log = LogFactory.getLog(ConfigHelper.class);
    
    private static final String PAGESIZE = "pagesize";
    
    private final static String PROPERTIES_FILENAME = "/jeabos.properties";

    private static Properties properties = null;
    
    static
    {
        if (log.isDebugEnabled()) {
            log.debug("Config called");
        }
        properties = loadProperties(PROPERTIES_FILENAME, "the default configuration");
    }
    
    public static String getProperty(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        if (properties == null) {
            return null;
        }

        return properties.getProperty(key);
    }
    
    public static Properties getProperties() {
        return properties;
    }
    
    public static void setProperties(Properties props)
    {
        properties = props;
    }

    public static Object get(Object key) {
        return properties.get(key);
    }
    
    public static void set(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        if (value == null) {
            return;
        }

        if (properties == null) {
            properties = new Properties();
        }

        properties.put(key, value);
    }
    
    public static Properties loadProperties(URL url, String info) {
        log.info("属性加载URL：" + url + " for " + info);

        Properties properties = new Properties();
        InputStream in = null;

        try {
            in = url.openStream();
            properties.load(in);
            log.info("属性读取： " + properties);
        } catch (Exception e) {
            log.error("读取失败：" + url, e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                log.warn("关闭IO产生异常：" + e.getMessage());
            }
        }
        
        return properties;
    }

    public static Properties loadProperties(String filename, String info) {
        URL url = null;
        
        ClassLoader threadContextClassLoader = Thread.currentThread().getContextClassLoader();
        if (threadContextClassLoader != null) {
            url = threadContextClassLoader.getResource(filename);
        }
        if (url == null) {
            url = ConfigHelper.class.getResource(filename);
            if (url == null) {
                log.warn("当前线程的ClassPath未发现属性文件：" + filename);
                return new Properties();
            }
        }
        
        return loadProperties(url, info);
    }

    public static int getPageSize() {
    	String value = getProperty(PAGESIZE);
    	try {
    		return Integer.parseInt(value);
    	} catch(Exception e) {
    		return 12;
    	}
    }
}

