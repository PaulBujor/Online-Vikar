package dk.grouptwo.utility;

import java.util.ArrayList;

public class EmailValidator {

    private static char[] emailToArray;
    private static ArrayList<Character> emailBeforeAtSign = new ArrayList<>();
    private static ArrayList<Character> emailAfterAtSign = new ArrayList<>();
    private static ArrayList<Character> domain = new ArrayList<>();
    private static ArrayList<Character> postDomain = new ArrayList<>();

    public static boolean emailCheck(String email) {
        int atCount = 0;
        int atPosition = 0;
        emailToArray = email.toCharArray();
        for (int i = 0; i < emailToArray.length; i++) {
            if (emailToArray[i] == '@')
                atCount++;
        }
        if (!(atCount == 1)) {
            return false;
        } else {

            for (int i = 0; i < emailToArray.length; i++)
            {
                if (emailToArray[i] == '@')
                    atPosition = i;
            }

            for (int i = 0; i < atPosition; i++)
            {
                emailBeforeAtSign.add(emailToArray[i]);
            }

            for (int i = atPosition + 1; i < emailToArray.length; i++)
            {
                emailAfterAtSign.add(emailToArray[i]);
            }

            return beforeAtSign() & afterAtSign();
        }

    }

    private static boolean beforeAtSign() {
        System.out.println(emailBeforeAtSign.toString());
        System.out.println(emailAfterAtSign.toString());
        if (emailBeforeAtSign.get(0) == '.' || emailBeforeAtSign.get(emailBeforeAtSign.size() - 1) == '.') {
            return false;
        } else {
            for (int i = 0; i < emailBeforeAtSign.size(); i++) {
                if (emailBeforeAtSign.get(i) == ' ')
                    return false;
            }
        }
        return true;
    }

    private static boolean afterAtSign() {

        int dotPosition = 0;
        for (int i = 0; i < emailAfterAtSign.size(); i++)
        {
            if (emailAfterAtSign.get(i) == '.')
            {
                if (i == emailAfterAtSign.size() - 1  || emailAfterAtSign.get(i-1) == '-' || emailAfterAtSign.get(i-1) == '.' || emailAfterAtSign.get(i+1) == '-' || emailAfterAtSign.get(i+1) == '.')
                    return false;
                dotPosition = i;
            }
        }

        for (int i = 0; i < dotPosition - 1; i++)
        {
            domain.add(emailAfterAtSign.get(i));
        }

        for (int i = dotPosition + 1; i < emailAfterAtSign.size(); i++)
        {
            postDomain.add(emailAfterAtSign.get(i));
        }

        for (int i = 0; i < domain.size(); i++)
        {
            if (!((domain.get(i) >= 48 && domain.get(i) <= 57) || (domain.get(i) >= 65 && domain.get(i) <= 90) || (domain.get(i) >= 97 && domain.get(i) <= 122) || (domain.get(i) == 46)))
                return false;
        }

        if (postDomain.size() < 2)
            return false;

        for (int i = 0; i < postDomain.size(); i++)
        {
            if (!((domain.get(i) >= 48 && domain.get(i) <= 57) || (domain.get(i) >= 65 && domain.get(i) <= 90) || (domain.get(i) >= 97 && domain.get(i) <= 122) || (domain.get(i) == 46)))
                return false;
        }

        return true;

    }
}
