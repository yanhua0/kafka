package org.cache.study.entity;

import lombok.Getter;
import lombok.Setter;
import org.cache.study.anno.EscapeUnescape;

import java.io.UnsupportedEncodingException;

@Getter
@Setter
public class Users {
    private Integer id;
    private Integer ss;

    public static void main(String[] args) throws UnsupportedEncodingException {
//      String s="sercode:中文&username:中文";
//      s= URLEncoder.d(s,"utf-8");
//      s= URLEncoder.encode(s,"utf-8");
//      System.out.println(s);
        String s = "usercode%253A%25u4E2D%25u6587%2526username%253A%25u4E2D%25u6587";

      s= EscapeUnescape.unescape(s);
     s= EscapeUnescape.unescape(s);

        System.out.println(s);

    }
}
