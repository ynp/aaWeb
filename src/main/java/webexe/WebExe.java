/**
 * Project Name:aaWeb
 * File Name:WebExe.java
 * Package Name:webexe
 * Date:2017年7月1日下午1:38:34
 * Copyright (c) 2017, SPRIXIN.com All Rights Reserved.
*/

package webexe;

import java.io.IOException;

/**
 * ClassName:WebExe <br/>
 * (这里用一句话描述这个类的作用). <br/>
 * Date: 2017年7月1日 下午1:38:34 <br/>
 * 
 * @author yaonp
 */
public class WebExe {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd /c start D://11.txt");
//            runtime.exec("cmd /c start C://a.txt");
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}
