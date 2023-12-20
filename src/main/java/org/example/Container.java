package org.example;

import org.example.DB.DBConnection;
import org.example.member.Member;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Container {
    private static Scanner scanner;
    private static DBConnection dbConnection;
    private static Member loginedMember;
    private static int resetByCreateId;
    private static boolean tryFlag;

    public static Member getLoginedMember(){
        return loginedMember;
    }
    public static void setLoginedMember(Member member){
        loginedMember = member;
    }

    public static void scannerInit(){
        scanner = new Scanner(System.in);
    }
    public static Scanner getScanner(){
        return scanner;
    }
    public static void scannerClose(){
        scanner.close();
    }
    public static DBConnection getDbConnection(){
        if(dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
    public static int getResetByCreateId(){
        return resetByCreateId;
    }
    public static void setResetByCreateId(int resetByCreateId1){
        resetByCreateId = resetByCreateId1;
    }
    public static String formatDate(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formattedDate = dateTime.format(formatter);

        return formattedDate;
    }
    public static boolean isTryFlag(){
        return tryFlag;
    }
    public static void setTryFlag(boolean tryFlag1){
        tryFlag = tryFlag1;
    }

}
