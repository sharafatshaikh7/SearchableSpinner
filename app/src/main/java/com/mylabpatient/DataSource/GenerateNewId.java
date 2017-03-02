package com.mylabpatient.DataSource;

/**
 * Created by Sunil on 3/1/2017.
 */

public class GenerateNewId {

    //method for generate next id
    public String GenerateID(String id){
        String AppNewID = "";
        String chartAt_1 = String.valueOf(id.charAt(0));
        String charAt_2 = String.valueOf(id.charAt(1));
        String num = id.substring(2);
        int first_char_int = id.charAt(0);
        int second_char_int = id.charAt(1);
        int num_int = Integer.parseInt(num);
        int next_num = 0;
        String nextNum_string = "";
        String Second_next_char = "";
        String first_next_char = "";
        int second_next_char_int = 0;
        if (chartAt_1 == "Z") {
            if (charAt_2 == "Z") {
                if (num_int == 999) {
                    String Error = "CannotGerate";
                }
                else {
                    next_num = num_int + 1;
                    nextNum_string = String.valueOf(next_num);
                    if (nextNum_string.length() == 1) {
                        nextNum_string = "00" + nextNum_string.trim();
                    }
                    if (nextNum_string.length() == 2) {
                        nextNum_string = "0" + nextNum_string.trim();
                    }
                    AppNewID = chartAt_1.trim() + charAt_2.trim() + nextNum_string;
                }
            }
            else {
                if (num_int == 999) {
                    second_next_char_int = second_char_int + 1;
                    char character3 = (char)second_next_char_int;
                    Second_next_char = String.valueOf(character3);
                    nextNum_string = "001";
                    AppNewID = chartAt_1.trim() + Second_next_char.trim() + nextNum_string;
                }
                else {
                    next_num = num_int + 1;
                    nextNum_string = String.valueOf(next_num);
                    if (nextNum_string.length() == 1) {
                        nextNum_string = "00" + nextNum_string.trim();
                    }
                    if (nextNum_string.length() == 2) {
                        nextNum_string = "0" + nextNum_string.trim();
                    }
                    AppNewID = chartAt_1.trim() + charAt_2.trim() + nextNum_string;
                }
            }
        }
        else if (charAt_2 == "Z") {
            if (num_int == 999) {
                nextNum_string = "001";
                Second_next_char = "A";
                int first_char_next_int = first_char_int + 1;
                char character1 = (char)first_char_next_int;
                first_next_char = String.valueOf(character1);
                AppNewID = first_next_char.trim() + Second_next_char.trim() + nextNum_string.trim();
            }
            else {
                next_num = num_int + 1;
                nextNum_string = String.valueOf(next_num);
                if (nextNum_string.length() == 1) {
                    nextNum_string = "00" + nextNum_string.trim();
                }
                if (nextNum_string.length() == 2) {
                    nextNum_string = "0" + nextNum_string.trim();
                }
                AppNewID = chartAt_1.trim() + charAt_2.trim() + nextNum_string.trim();
            }
        }
        else if (num_int == 999) {
            second_next_char_int = second_char_int + 1;
            char character2 = (char)second_next_char_int;
            Second_next_char = String.valueOf(character2);
            nextNum_string = "001";
            AppNewID = chartAt_1.trim() + Second_next_char.trim() + nextNum_string;
        }
        else {
            next_num = num_int + 1;
            nextNum_string = String.valueOf(next_num);
            if (nextNum_string.length() == 1) {
                nextNum_string = "00" + nextNum_string.trim();
            }
            if (nextNum_string.length() == 2) {
                nextNum_string = "0" + nextNum_string.trim();
            }
            AppNewID = chartAt_1.trim() + charAt_2.trim() + nextNum_string.toString().trim();

        }
        return AppNewID;
    }
}
