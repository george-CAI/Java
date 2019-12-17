package myCom.com;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class MyApp {
    public static void main(String[] args) {
        final String myConstantStr = "the open source is an interesting world...";
        myConstantStr.chars().forEach(c -> System.out.printf("%c",c));

        //count every char in the string
        HashMap<Character,Integer> map = new HashMap();
        myConstantStr.chars().forEach( c -> {
            if (map.containsKey((char)c)) map.replace((char)c,map.get((char)c)+1);
            else map.put((char)c,1);
        });

        //count the char in a string
        int count = countChar(myConstantStr,'e');

        //reverse a sentence/string
        String reversedStr = reverse(myConstantStr);
        String sourceStr = "This is Chinese string: 风景如画，美不胜收";
        String targetStr = reverse(sourceStr);
        String targetStr2 = reverse2(sourceStr);
        String targetStr3 = reverse3(sourceStr);

        //reverse a string using recursion


        //reverse the word in a sentence, but keep the word unchanged/unreversed
        String targetStr4 = reverseSentence(sourceStr);



        //checkif a string is a palindrome or not
        boolean isPalindrome = isPalindrome("1");
        boolean isPalindrome2 = isPalindrome("121");
        boolean isPalindrome3 = isPalindrome("1221");

        //check if two String are Anagram or not
        boolean anagram = checkAnagram1("mary","army");
        boolean anagram2 = checkAnagram2("pot","top");


        //remove any character from a stringh
        String removedStr = removeChar("1",'1');
        String removedStr2 = removeChar("112341561",'1');



        //find all permuations of string
        System.out.println();
        String str="abcd";
        char[] cs = new char[str.length()];
        permutation(str,cs,0);
        permutation2("",str);


        //find duplicate words in a string



        //print 1st not repeated char from a string
        char ch = firstNotRepeated("a");
        ch = firstNotRepeated("cba");
        ch= firstNotRepeated("abca");


        int a= 3, b = 1, c=-1;

        int smallest = smaller(a, smaller(b,c));
        int smallest2 = Math.min(a,Math.min(b,c));

    }

    static int countChar(String s, char c) {

        if (null==s) throw new NullPointerException("the input string cannot be null!");

        //error: local variables referenced from a lambda expression must be final or effectively final
//        int count =0;
//        s.chars().forEach( x -> {if (c == x) count++;});
//        return count;

        //to avoid the above compiling error!!!
        int[] counts={0};
        s.chars().forEach( x -> {if (c == x) counts[0]++;});

        return counts[0];
    }

    static int smaller(int a, int b) {
        return a>b? b:a;
    }

    static String reverse(String s) {
        if (null == s) throw new NullPointerException();
        char[] cs = s.toCharArray();
        int mid = cs.length/2;
        for (int i=0;i<mid;i++) {
            char temp = cs[i];
            cs[i] = cs[cs.length - i -1];
            cs[cs.length - i -1] = temp;
        }
        return new String(cs);
    }

    static String reverse2(String s) {

        if (null==s || s.isEmpty()) return s;
        String reversed = "";
        for(int i = s.length()-1;i>=0;i--)
            reversed += s.charAt(i);

        String reversed2="";
        for(int j=0;j<s.length();j++)
            reversed2 = s.charAt(j) + reversed2;


        return reversed2;
    }

    static String reverse3(String s) {

        char[] ss = s.toCharArray();
        int i=0,j=s.length()-1;

        while(i<j) {
            char temp = ss[j];
            ss[j] = ss[i];
            ss[i] = temp;
            i++;
            j--;
        }

        String temp = new String(ss);
        return temp;
    }

    static void reverse(char[] cs,int start, int end) {
        while (start < end) {
            char temp = cs[start];
            cs[start] = cs[end];
            cs[end] = temp;
            start++;
            end--;
        }
    }

    static String reverseSentence(String sourceStr) {
        char[] cs = sourceStr.toCharArray();
        reverse(cs,0,cs.length-1);  //first reverse the whole sentence

        int start = 0;
        int end = 0;

        while(end<cs.length) {
            while (end<cs.length && cs[end]!=' ') end++;     //assume only one space, no other separator
            reverse(cs,start,end-1);
            start = ++end;
        }

        return new String(cs);

    }


    static boolean isPalindrome(String s) {
        if ((null==s) || s.isEmpty())
            return false;

        char[] cs = s.toCharArray();

        int i = 0, j = cs.length -1;

        while (i < j) {
            if (cs[i] != cs[j]) return false;
            i++;
            j--;
        }
        return true;
    }


    static boolean checkAnagram1(String s1, String s2) {
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();

        Arrays.sort(cs1);
        Arrays.sort(cs2);

        //same as cs1==cs2   (check both are the same array or not)
        boolean  same = cs1.equals(cs2);            //false

        boolean  temp = Arrays.equals(cs1,cs2);    //true

        return temp;
    }

    static boolean checkAnagram2(String s1, String s2) {
        char[] cs =  s1.toCharArray();

        StringBuilder builder = new StringBuilder(s2);

        for(char c: cs) {
            int index = builder.indexOf("" + c);
            if (-1 == index) return false;
            else {
                builder.deleteCharAt(index);
            }
        }

        return builder.length() == 0? true : false;

    }

    static String removeChar(String s, char c) {

        if(null == s) return null;

        StringBuilder sb = new StringBuilder(s);
        int index;
        while ((index = sb.indexOf(""+c)) != -1) {
            sb.deleteCharAt(index);
        }

        return sb.toString();
    }


    static void permutation(String s, char[] cs,int pos) {

        //assume there no repeated char in the string

        for ( int i = 0; i < s.length(); i++) {

            StringBuilder sb = new StringBuilder(s);

            cs[pos]=s.charAt(i);

            permutation(sb.deleteCharAt(i).toString(),cs,pos +1);

            if (s.length()==1) {
                for (char c : cs) {
                    System.out.print(" " + c);
                }
                System.out.println();
            }
        }
    }


    //see https://javarevisited.blogspot.com/2015/08/how-to-find-all-permutations-of-string-java-example.html
    static void permutation2(String perm, String word) {

        for (int i=0; i<word.length();i++)
        permutation2(perm + word.charAt(i), word.substring(0,i) + word.substring(i+1,word.length()));

        if(word.isEmpty())
            System.out.println(perm);
    }


    //
    static char firstNotRepeated(String s) {

        char c;
       do {
            c= s.charAt(0);

            s =s.substring(1,s.length());
            if (s ==null || s.isEmpty()) return c;

            int index = s.indexOf(c);
            if(index ==-1) return c;
            s = s.replaceAll(""+c, "");

        } while (true);
    }

}
