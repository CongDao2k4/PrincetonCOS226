/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author CONG
 */
public class J07071 {
    public static void main(String[] args) throws IOException{
        File file = new File("D:\\CODE Ptit\\OOP\\mavenproject1\\src\\main\\java\\com\\mycompany\\mavenproject1\\filedata\\DANHSACH.in");
        Scanner sc = new Scanner(file);
        ArrayList<String> a = new ArrayList<>();
        int n = sc.nextInt(); sc.nextLine();
        for(int i=0; i<n; i++) {
            a.add(sc.nextLine());
        }
        int m = sc.nextInt(); sc.nextLine();
        for(int i=0; i<m; i++) {
            String[] form = sc.nextLine().split("\\.");
            String regex = "";
            System.out.println(form.length);
            for(String e : form) {
                if(e.equals("*"))
                    regex += "[A-Z]{1}[a-z]+(\\s)*";
                else regex += e + "[a-z]+(\\s)*";
            }
            regex = regex.trim();
            System.out.println(regex);
            Pattern pattern = Pattern.compile(regex);
            for(String ten : a) {
                //System.out.println(ten.matches(regex));
                Matcher matcher = pattern.matcher(ten);
                //String out = "";
                while(matcher.find()) {
                    System.out.println(matcher.group());
                }
                //System.out.println(out);
            }
        }
    }
}
