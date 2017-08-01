import java.io.IOException;
import java.util.Properties;

/**
 * Project Name:aaWeb
 * File Name:Test.java
 * Package Name:
 * Date:2017年7月18日下午7:28:41
 * Copyright (c) 2017, SPRIXIN.com All Rights Reserved.
*/

/**
 * ClassName:Test <br/>
 * (这里用一句话描述这个类的作用). <br/>
 * Date:     2017年7月18日 下午7:28:41 <br/>
 * @author   yaonp 
 */
public class Test {
/*    static{
        try {
            Properties p =  new Properties();
            p.load(Test.class.getClassLoader().getResourceAsStream("config.properties"));
            System.out.println(p.get("InitRTDB").equals("1"));
            
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    
    }*/
    
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.library.path"));
    }
}

