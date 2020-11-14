package com.wjmao.algorithm;

/**
 * kpm算法示例
 */
public class KpmDemo {

    /**
     * 暴力破解法
     * @param s
     * @param p
     * @return
     */
    public static int ViolentMatch(char s[], char p[]) {
        int sLen = s.length;
        int pLen = p.length;

        int i = 0;
        int j = 0;
        while (i < sLen && j < pLen) {
            if (s[i] == p[j]) {
                //①如果当前字符匹配成功（即S[i] == P[j]），则i++，j++
                i++;
                j++;
                System.out.println("匹配成功，从第"+i+"位再次比较");
            } else {
                //②如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0

                i = i - j + 1;//表示从下一个字符进行再次比较
                System.out.println("匹配失败，从第"+i+"位进行比较");
                j = 0;
            }
        }
        //匹配成功，返回模式串p在文本串s中的位置，否则返回-1
        if (j == pLen)
            return i - j;
        else
            return -1;

    }

    static int next [] = {-1,0,0,0,0,1,2};

    /**
     * kpm算法
     * @param s
     * @param p
     * @return
     */
    static int KmpSearch(char s[], char p[])
    {
        int i = 0;
        int j = 0;
        int sLen = s.length;
        int pLen = p.length;
        while (i < sLen && j < pLen)
        {
            //①如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
            if (j == -1 || s[i] == p[j])
            {
                i++;
                j++;
            }
            else
            {
                //②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
                //next[j]即为j所对应的next值
                j = next[j];
            }
        }
        if (j == pLen)
            return i - j;
        else
            return -1;
    }


    public static void main(String[] args) {
        char [] s  ={'B','B','C',' ','A','B','C','D','A','B',' ','A','B','C','D','A','B','C','D','A',
                'B','D','E'};
        char [] p = {'A','B','C','D','A','B','D'};
        //System.out.println(ViolentMatch(s,p));
        System.out.println(KmpSearch(s,p));
    }
}
