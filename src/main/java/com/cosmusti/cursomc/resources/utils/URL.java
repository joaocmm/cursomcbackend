
package com.cosmusti.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class URL {
    
    public static String decodeParam(String s){
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return "";
        }
    }
    
    
    public static List<Long> decodeLongList(String s){
        String[] vet =s.split(",");
        List<Long> list = new ArrayList<>();
        for(int i=0; i<vet.length;i++){
            list.add(Long.parseLong(vet[i]));
        }
        return list;
    }
    
}
