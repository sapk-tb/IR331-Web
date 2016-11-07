/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.etu.ir331.web.tools;

import java.util.Random;

/**
 *
 * @author sapk
 */
public class Generator {

    public static Object chooseFrom(Object[] s) {
        Random rng = new Random();
        return s[rng.nextInt(s.length)];
    }
    public static String generateContratType() {
        String[] source = {"CDD","CDI"};
        return (String) chooseFrom(source);
        /*
        Random rng = new Random();
        String[] source = {"CDD","CDI"};
        return source[rng.nextInt(source.length)];
        */
    }
    public static String generateServiceName() {
        String[] source = {"Service1","Service2","Service3","Service4","Service5","Service6"};
        return (String) chooseFrom(source);
    }
    public static String generatePrenom() {
        String[] source = {"Maxime", "Antoine", "Paul","Testeur"};
        return (String) chooseFrom(source);
    }

    public static String generateNom() {
        String[] source = {"L", "G", "D","Testeur", ""};
        return (String) chooseFrom(source);
    }
    
    public static String generateDate() {
        String[] source = {"1998-05-27","","","2003-07-11","2003-09-08","","2005-12-30","2007-07-18","","2009-04-10","2010-09-22","","2012-06-19","2019-02-21","","2019-07-23","2018-04-03","","2020-01-08","2020-02-04","2020-07-03","","2020-07-16","2020-10-20","2020-11-04","","2024-02-23","2025-08-11","","2026-05-06","2028-01-05","","2029-06-15","2030-01-22","2033-08-08","","2034-04-13",""};
        return (String) chooseFrom(source);    }
}
