package cn.zhanguozhi.chapter1_Introduction;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author zhanguozhi
 * @date 2019.11.9
 * @desc To look for several words in a puzzle, use a method contained three steps
 *          1. input the puzzle and words(dictionary) which may be found
 *          2. add the string with a max length of each direction to an array
 *          3. check the array whether it contains the words
 */
public class _1_2_WordFind {
    char[][] puzzle;
    int row;
    int col;
    String[] dictionary;
    HashSet<String> maxStr = new HashSet<>();  // contains string of each directions
    HashSet<String> result = new HashSet<>();


    public void getInput() {
        // set the puzzle array
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入行数：");
        row = scanner.nextInt();
        System.out.println("请输入列数：");
        col = scanner.nextInt();
        puzzle = new char[row][col];
        System.out.println("请输入" + row*col + "个字符,以逗号分隔");
        String str = scanner.next();
        String[] chars = str.split(",");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                puzzle[i][j] = chars[i * row + j].charAt(0);
            }
        }

        //set the dictionary array
        System.out.println("请输入单词表，以逗号分隔");
        String words = scanner.next();
        dictionary = words.split(",");
    }

    public void getMaxLengthStr() {
        // find the maxStr from the horizontal and vertical direction
        for (int i = 0; i < puzzle.length; i++) {
            String horizon = "";
            String vertical = "";
            for (int j = 0; j < puzzle[i].length; j++) {
                horizon += puzzle[i][j];
                vertical += puzzle[j][i];
            }
            maxStr.add(horizon);
            maxStr.add(vertical);
            maxStr.add(new StringBuffer(horizon).reverse().toString());
            maxStr.add(new StringBuffer(vertical).reverse().toString());

        }
        // low right direction(主对角线) !!!难点
        int i = 0;
        for (int j = 0; j < col - 1; j++) { // 最长的对交线加了两次 因为是HashSet不需要单独处理
            String s1 = ""; //上三角
            String s2 = ""; //下三角
            int m = 0;
            int n = j;
            while (m < row && n < col) {
                s1 += puzzle[m][n];
                s2 += puzzle[n][m];
                m++;
                n++;
            }
            maxStr.add(s1);
            maxStr.add(s2);
            maxStr.add(new StringBuffer(s1).reverse().toString());
            maxStr.add(new StringBuffer(s2).reverse().toString());
        }

        // low left direction(次对角线)
        i = row - 1;
        for (int j = 0; j < row - 1; j++) {
            String s1 = ""; //下三角
            String s2 = ""; //上三角
            int m = row - 1;
            int n = j;
            while (m >= 0 && n < col) {
                s1 += puzzle[m][n];
                //s2 += puzzle[n][m];
                m--;
                n++;
            }
            m = row - 1 - j;
            n = 0;
            while (m >= 0 && n < col) {
                s2 += puzzle[m][n];
                m--;
                n++;
            }
            maxStr.add(s1);
            maxStr.add(s2);
            maxStr.add(new StringBuffer(s1).reverse().toString());
            maxStr.add(new StringBuffer(s2).reverse().toString());
        }
    }

    public static void main(String[] args) {
        _1_2_WordFind wordFind = new _1_2_WordFind();
        wordFind.getInput();
        wordFind.getMaxLengthStr();
        wordFind.find();
        for (String s : wordFind.result) {
            System.out.println(s);
        }
    }

    public void find() {
        for (String s : maxStr) {
            for (String word : dictionary) { // find the word in the dictionary with the maxStr
                if (s.contains(word)) {
                    result.add(word);
                }
            }
        }
    }
}
