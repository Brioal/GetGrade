package com.brioal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Scanner;

/**
 * Created by null on 15-11-4.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        Scanner keyin = new Scanner(System.in);
        String username = null;
        String password = null;
        String year = null;
        int term = 0;
        System.out.println("输入用户名");
        username = keyin.nextLine();
        int y = Integer.valueOf(username.charAt(4) + "");
        int n =3;
        int y_ = 0;
        System.out.println("输入密码");
        password = keyin.nextLine();
        System.out.println("选择学年 1 . 大一   2.大二   3.大三   4. 大四");
        switch (keyin.nextInt()) {
            case 1:
                y_ = y + 0;
                break;
            case 2:
                y_ = y + 1;
                break;
            case 3:
                y_ = y + 2;
                break;
            case 4:
                y_ = y + 3;
                break;
        }
        System.out.println("选择学期 : 1 . 上学期 2. 下学期");
        switch (keyin.nextInt()) {
            case 1:
                term = 2;
                break;
            case 2:
                y_ += 1;
                term = 1;
                break;
        }
        year = n + "" + y_;

        String logo_params = "j_username=" + username + "&" + "j_password=" + password;
        String grade_params = "year=" + year + "&term=" + term + "&para=0&sortColumn=&Submit=��ѯ";

        String URL_Logo = "http://jwc.lzu.cn/academic/j_acegi_security_check";
        String URL_QueryGrade = "http://jwc.lzu.cn/academic/manager/score/studentOwnScore.do?groupId=&moduleId=2020";
        Info from_logo = GetLzu.Post(new Info(URL_Logo, null, logo_params)); // �˴��ѽ��е�½����
        from_logo.setUrl(URL_QueryGrade);
        from_logo.setData(grade_params);
        Info from_grade = GetLzu.Post(from_logo);
        String data = from_grade.getData();
        Document doc = Jsoup.parse(data);


        Elements tables = doc.select("table");
        Elements trs = null;
        trs = tables.get(1).select("tr");
        Elements ths = trs.select("th");
        String text1 = ths.text();
        String s1 = text1.replaceAll("<th> ", "");
        System.out.print(s1 + "\t\t\t");
        System.out.println();
        String[][] datas = new String[trs.size()][15];
        for (int i = 0; i < trs.size(); i++) {
            Elements tds = trs.get(i).select("td");
            String text = tds.text();
            String s = text.replaceAll("<th> ", "");
            datas[i] = s.split(" +");
            for (String str : datas[i]) {
                System.out.print(str + "\t");
            }
            System.out.println();
        }
    }
}



