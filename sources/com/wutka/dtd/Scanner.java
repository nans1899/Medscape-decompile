package com.wutka.dtd;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import java.util.Stack;
import org.mozilla.classfile.ByteCode;

class Scanner {
    public static final TokenType ASTERISK = new TokenType(13, "ASTERISK");
    public static final TokenType COMMA = new TokenType(5, "COMMA");
    public static final TokenType COMMENT = new TokenType(16, "COMMENT");
    public static final TokenType CONDITIONAL = new TokenType(18, "CONDITIONAL");
    public static final TokenType ENDCONDITIONAL = new TokenType(19, "ENDCONDITIONAL");
    public static final TokenType EOF = new TokenType(15, "EOF");
    public static final TokenType EQUAL = new TokenType(2, "EQUAL");
    public static final TokenType GT = new TokenType(9, "GT");
    public static final TokenType IDENTIFIER = new TokenType(1, "IDENTIFIER");
    public static final TokenType LPAREN = new TokenType(3, "LPAREN");
    public static final TokenType LT = new TokenType(14, "LT");
    public static final TokenType LTBANG = new TokenType(8, "LTBANG");
    public static final TokenType LTQUES = new TokenType(0, "LTQUES");
    public static final TokenType NMTOKEN = new TokenType(20, "NMTOKEN");
    public static final TokenType PERCENT = new TokenType(17, "PERCENT");
    public static final TokenType PIPE = new TokenType(10, "PIPE");
    public static final TokenType PLUS = new TokenType(12, "PLUS");
    public static final TokenType QUES = new TokenType(11, "QUES");
    public static final TokenType QUESGT = new TokenType(7, "QUESGT");
    public static final TokenType RPAREN = new TokenType(4, "RPAREN");
    public static final TokenType STRING = new TokenType(6, "STRING");
    public static char[][] letterRanges;
    protected boolean atEOF;
    protected Hashtable entityExpansion;
    protected char[] expandBuffer;
    protected int expandPos;
    protected EntityExpansion expander;
    protected StreamInfo in;
    protected Stack inputStreams;
    protected int nextChar;
    protected Token nextToken;
    protected boolean trace;

    public boolean isCombiningChar(char c) {
        if (c < 768) {
            return false;
        }
        if (c >= 768 && c <= 837) {
            return true;
        }
        if (c >= 864 && c <= 865) {
            return true;
        }
        if (c >= 1155 && c <= 1158) {
            return true;
        }
        if (c >= 1425 && c <= 1441) {
            return true;
        }
        if (c >= 1443 && c <= 1465) {
            return true;
        }
        if ((c >= 1467 && c <= 1469) || c == 1471) {
            return true;
        }
        if ((c >= 1473 && c <= 1474) || c == 1476) {
            return true;
        }
        if ((c >= 1611 && c <= 1618) || c == 1648) {
            return true;
        }
        if (c >= 1750 && c <= 1756) {
            return true;
        }
        if (c >= 1757 && c <= 1759) {
            return true;
        }
        if (c >= 1760 && c <= 1764) {
            return true;
        }
        if (c >= 1767 && c <= 1768) {
            return true;
        }
        if (c >= 1770 && c <= 1773) {
            return true;
        }
        if ((c >= 2305 && c <= 2307) || c == 2364) {
            return true;
        }
        if ((c >= 2366 && c <= 2380) || c == 2381) {
            return true;
        }
        if (c >= 2385 && c <= 2388) {
            return true;
        }
        if (c >= 2402 && c <= 2403) {
            return true;
        }
        if ((c >= 2433 && c <= 2435) || c == 2492 || c == 2494 || c == 2495) {
            return true;
        }
        if (c >= 2496 && c <= 2500) {
            return true;
        }
        if (c >= 2503 && c <= 2504) {
            return true;
        }
        if ((c >= 2507 && c <= 2509) || c == 2519) {
            return true;
        }
        if ((c >= 2530 && c <= 2531) || c == 2562 || c == 2620 || c == 2622 || c == 2623) {
            return true;
        }
        if (c >= 2624 && c <= 2626) {
            return true;
        }
        if (c >= 2631 && c <= 2632) {
            return true;
        }
        if (c >= 2635 && c <= 2637) {
            return true;
        }
        if (c >= 2672 && c <= 2673) {
            return true;
        }
        if ((c >= 2689 && c <= 2691) || c == 2748) {
            return true;
        }
        if (c >= 2750 && c <= 2757) {
            return true;
        }
        if (c >= 2759 && c <= 2761) {
            return true;
        }
        if (c >= 2763 && c <= 2765) {
            return true;
        }
        if ((c >= 2817 && c <= 2819) || c == 2876) {
            return true;
        }
        if (c >= 2878 && c <= 2883) {
            return true;
        }
        if (c >= 2887 && c <= 2888) {
            return true;
        }
        if (c >= 2891 && c <= 2893) {
            return true;
        }
        if (c >= 2902 && c <= 2903) {
            return true;
        }
        if (c >= 2946 && c <= 2947) {
            return true;
        }
        if (c >= 3006 && c <= 3010) {
            return true;
        }
        if (c >= 3014 && c <= 3016) {
            return true;
        }
        if ((c >= 3018 && c <= 3021) || c == 3031) {
            return true;
        }
        if (c >= 3073 && c <= 3075) {
            return true;
        }
        if (c >= 3134 && c <= 3140) {
            return true;
        }
        if (c >= 3142 && c <= 3144) {
            return true;
        }
        if (c >= 3146 && c <= 3149) {
            return true;
        }
        if (c >= 3157 && c <= 3158) {
            return true;
        }
        if (c >= 3202 && c <= 3203) {
            return true;
        }
        if (c >= 3262 && c <= 3268) {
            return true;
        }
        if (c >= 3270 && c <= 3272) {
            return true;
        }
        if (c >= 3274 && c <= 3277) {
            return true;
        }
        if (c >= 3285 && c <= 3286) {
            return true;
        }
        if (c >= 3330 && c <= 3331) {
            return true;
        }
        if (c >= 3390 && c <= 3395) {
            return true;
        }
        if (c >= 3398 && c <= 3400) {
            return true;
        }
        if ((c >= 3402 && c <= 3405) || c == 3415 || c == 3633) {
            return true;
        }
        if (c >= 3636 && c <= 3642) {
            return true;
        }
        if ((c >= 3655 && c <= 3662) || c == 3761) {
            return true;
        }
        if (c >= 3764 && c <= 3769) {
            return true;
        }
        if (c >= 3771 && c <= 3772) {
            return true;
        }
        if (c >= 3784 && c <= 3789) {
            return true;
        }
        if ((c >= 3864 && c <= 3865) || c == 3893 || c == 3895 || c == 3897 || c == 3902 || c == 3903) {
            return true;
        }
        if (c >= 3953 && c <= 3972) {
            return true;
        }
        if (c >= 3974 && c <= 3979) {
            return true;
        }
        if ((c >= 3984 && c <= 3989) || c == 3991) {
            return true;
        }
        if (c >= 3993 && c <= 4013) {
            return true;
        }
        if ((c >= 4017 && c <= 4023) || c == 4025) {
            return true;
        }
        if ((c < 8400 || c > 8412) && c != 8417) {
            return (c >= 12330 && c <= 12335) || c == 12441 || c == 12442;
        }
        return true;
    }

    public boolean isDigit(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        if (c < 1632) {
            return false;
        }
        if (c >= 1632 && c <= 1641) {
            return true;
        }
        if (c < 1776) {
            return false;
        }
        if (c >= 1776 && c <= 1785) {
            return true;
        }
        if (c < 2406) {
            return false;
        }
        if (c >= 2406 && c <= 2415) {
            return true;
        }
        if (c < 2534) {
            return false;
        }
        if (c >= 2534 && c <= 2543) {
            return true;
        }
        if (c < 2662) {
            return false;
        }
        if (c >= 2662 && c <= 2671) {
            return true;
        }
        if (c < 2790) {
            return false;
        }
        if (c >= 2790 && c <= 2799) {
            return true;
        }
        if (c < 2918) {
            return false;
        }
        if (c >= 2918 && c <= 2927) {
            return true;
        }
        if (c < 3047) {
            return false;
        }
        if (c >= 3047 && c <= 3055) {
            return true;
        }
        if (c < 3174) {
            return false;
        }
        if (c >= 3174 && c <= 3183) {
            return true;
        }
        if (c < 3302) {
            return false;
        }
        if (c >= 3302 && c <= 3311) {
            return true;
        }
        if (c < 3430) {
            return false;
        }
        if (c >= 3430 && c <= 3439) {
            return true;
        }
        if (c < 3664) {
            return false;
        }
        if (c >= 3664 && c <= 3673) {
            return true;
        }
        if (c < 3792) {
            return false;
        }
        if (c < 3792 || c > 3801) {
            return c >= 3872 && c >= 3872 && c <= 3881;
        }
        return true;
    }

    public boolean isExtender(char c) {
        if (c < 183) {
            return false;
        }
        if (c == 183 || c == 720 || c == 721 || c == 903 || c == 1600 || c == 3654) {
            return true;
        }
        if (c >= 12337 && c <= 12341) {
            return true;
        }
        if (c < 12445 || c > 12446) {
            return c >= 12540 && c <= 12542;
        }
        return true;
    }

    public boolean isIdeographic(char c) {
        if (c < 19968) {
            return false;
        }
        if ((c < 19968 || c > 40869) && c != 12295) {
            return c >= 12321 && c <= 12329;
        }
        return true;
    }

    static {
        char[][] cArr = new char[ByteCode.BREAKPOINT][];
        cArr[0] = new char[]{'A', ASCIIPropertyListParser.DATE_APPLE_END_TOKEN};
        cArr[1] = new char[]{'a', 'z'};
        cArr[2] = new char[]{192, 214};
        cArr[3] = new char[]{216, 246};
        cArr[4] = new char[]{248, 255};
        cArr[5] = new char[]{256, 305};
        cArr[6] = new char[]{308, 318};
        cArr[7] = new char[]{321, 328};
        cArr[8] = new char[]{330, 382};
        cArr[9] = new char[]{384, 451};
        cArr[10] = new char[]{461, 496};
        cArr[11] = new char[]{500, 501};
        cArr[12] = new char[]{506, 535};
        cArr[13] = new char[]{592, 680};
        cArr[14] = new char[]{699, 705};
        cArr[15] = new char[]{902, 902};
        cArr[16] = new char[]{904, 906};
        cArr[17] = new char[]{908, 908};
        cArr[18] = new char[]{910, 929};
        cArr[19] = new char[]{931, 974};
        cArr[20] = new char[]{976, 982};
        cArr[21] = new char[]{986, 986};
        cArr[22] = new char[]{988, 988};
        cArr[23] = new char[]{990, 990};
        cArr[24] = new char[]{992, 992};
        cArr[25] = new char[]{994, 1011};
        cArr[26] = new char[]{1025, 1036};
        cArr[27] = new char[]{1038, 1103};
        cArr[28] = new char[]{1105, 1116};
        cArr[29] = new char[]{1118, 1153};
        cArr[30] = new char[]{1168, 1220};
        cArr[31] = new char[]{1223, 1224};
        cArr[32] = new char[]{1227, 1228};
        cArr[33] = new char[]{1232, 1259};
        cArr[34] = new char[]{1262, 1269};
        cArr[35] = new char[]{1272, 1273};
        cArr[36] = new char[]{1329, 1366};
        cArr[37] = new char[]{1369, 1369};
        cArr[38] = new char[]{1377, 1414};
        cArr[39] = new char[]{1488, 1514};
        cArr[40] = new char[]{1520, 1522};
        cArr[41] = new char[]{1569, 1594};
        cArr[42] = new char[]{1601, 1610};
        cArr[43] = new char[]{1649, 1719};
        cArr[44] = new char[]{1722, 1726};
        cArr[45] = new char[]{1728, 1742};
        cArr[46] = new char[]{1744, 1747};
        cArr[47] = new char[]{1749, 1749};
        cArr[48] = new char[]{1765, 1766};
        cArr[49] = new char[]{2309, 2361};
        cArr[50] = new char[]{2365, 2365};
        cArr[51] = new char[]{2392, 2401};
        cArr[52] = new char[]{2437, 2444};
        cArr[53] = new char[]{2447, 2448};
        cArr[54] = new char[]{2451, 2472};
        cArr[55] = new char[]{2474, 2480};
        cArr[56] = new char[]{2482, 2482};
        cArr[57] = new char[]{2486, 2489};
        cArr[58] = new char[]{2524, 2525};
        cArr[59] = new char[]{2527, 2529};
        cArr[60] = new char[]{2544, 2545};
        cArr[61] = new char[]{2565, 2570};
        cArr[62] = new char[]{2575, 2576};
        cArr[63] = new char[]{2579, 2600};
        cArr[64] = new char[]{2602, 2608};
        cArr[65] = new char[]{2610, 2611};
        cArr[66] = new char[]{2613, 2614};
        cArr[67] = new char[]{2616, 2617};
        cArr[68] = new char[]{2649, 2652};
        cArr[69] = new char[]{2654, 2654};
        cArr[70] = new char[]{2674, 2676};
        cArr[71] = new char[]{2693, 2699};
        cArr[72] = new char[]{2701, 2701};
        cArr[73] = new char[]{2703, 2705};
        cArr[74] = new char[]{2707, 2728};
        cArr[75] = new char[]{2730, 2736};
        cArr[76] = new char[]{2738, 2739};
        cArr[77] = new char[]{2741, 2745};
        cArr[78] = new char[]{2749, 2749};
        cArr[79] = new char[]{2784, 2784};
        cArr[80] = new char[]{2821, 2828};
        cArr[81] = new char[]{2831, 2832};
        cArr[82] = new char[]{2835, 2856};
        cArr[83] = new char[]{2858, 2864};
        cArr[84] = new char[]{2866, 2867};
        cArr[85] = new char[]{2870, 2873};
        cArr[86] = new char[]{2877, 2877};
        cArr[87] = new char[]{2908, 2909};
        cArr[88] = new char[]{2911, 2913};
        cArr[89] = new char[]{2949, 2954};
        cArr[90] = new char[]{2958, 2960};
        cArr[91] = new char[]{2962, 2965};
        cArr[92] = new char[]{2969, 2970};
        cArr[93] = new char[]{2972, 2972};
        cArr[94] = new char[]{2974, 2975};
        cArr[95] = new char[]{2979, 2980};
        cArr[96] = new char[]{2984, 2986};
        cArr[97] = new char[]{2990, 2997};
        cArr[98] = new char[]{2999, 3001};
        cArr[99] = new char[]{3077, 3084};
        cArr[100] = new char[]{3086, 3088};
        cArr[101] = new char[]{3090, 3112};
        cArr[102] = new char[]{3114, 3123};
        cArr[103] = new char[]{3125, 3129};
        cArr[104] = new char[]{3168, 3169};
        cArr[105] = new char[]{3205, 3212};
        cArr[106] = new char[]{3214, 3216};
        cArr[107] = new char[]{3218, 3240};
        cArr[108] = new char[]{3242, 3251};
        cArr[109] = new char[]{3253, 3257};
        cArr[110] = new char[]{3294, 3294};
        cArr[111] = new char[]{3296, 3297};
        cArr[112] = new char[]{3333, 3340};
        cArr[113] = new char[]{3342, 3344};
        cArr[114] = new char[]{3346, 3368};
        cArr[115] = new char[]{3370, 3385};
        cArr[116] = new char[]{3424, 3425};
        cArr[117] = new char[]{3585, 3630};
        cArr[118] = new char[]{3632, 3632};
        cArr[119] = new char[]{3634, 3635};
        cArr[120] = new char[]{3648, 3653};
        cArr[121] = new char[]{3713, 3714};
        cArr[122] = new char[]{3716, 3716};
        cArr[123] = new char[]{3719, 3720};
        cArr[124] = new char[]{3722, 3722};
        cArr[125] = new char[]{3725, 3725};
        cArr[126] = new char[]{3732, 3735};
        cArr[127] = new char[]{3737, 3743};
        cArr[128] = new char[]{3745, 3747};
        cArr[129] = new char[]{3749, 3749};
        cArr[130] = new char[]{3751, 3751};
        cArr[131] = new char[]{3754, 3755};
        cArr[132] = new char[]{3757, 3758};
        cArr[133] = new char[]{3760, 3760};
        cArr[134] = new char[]{3762, 3763};
        cArr[135] = new char[]{3773, 3773};
        cArr[136] = new char[]{3776, 3780};
        cArr[137] = new char[]{3904, 3911};
        cArr[138] = new char[]{3913, 3945};
        cArr[139] = new char[]{4256, 4293};
        cArr[140] = new char[]{4304, 4342};
        cArr[141] = new char[]{4352, 4352};
        cArr[142] = new char[]{4354, 4355};
        cArr[143] = new char[]{4357, 4359};
        cArr[144] = new char[]{4361, 4361};
        cArr[145] = new char[]{4363, 4364};
        cArr[146] = new char[]{4366, 4370};
        cArr[147] = new char[]{4412, 4412};
        cArr[148] = new char[]{4414, 4414};
        cArr[149] = new char[]{4416, 4416};
        cArr[150] = new char[]{4428, 4428};
        cArr[151] = new char[]{4430, 4430};
        cArr[152] = new char[]{4432, 4432};
        cArr[153] = new char[]{4436, 4437};
        cArr[154] = new char[]{4441, 4441};
        cArr[155] = new char[]{4447, 4449};
        cArr[156] = new char[]{4451, 4451};
        cArr[157] = new char[]{4453, 4453};
        cArr[158] = new char[]{4455, 4455};
        cArr[159] = new char[]{4457, 4457};
        cArr[160] = new char[]{4461, 4462};
        cArr[161] = new char[]{4466, 4467};
        cArr[162] = new char[]{4469, 4469};
        cArr[163] = new char[]{4510, 4510};
        cArr[164] = new char[]{4520, 4520};
        cArr[165] = new char[]{4523, 4523};
        cArr[166] = new char[]{4526, 4527};
        cArr[167] = new char[]{4535, 4536};
        cArr[168] = new char[]{4538, 4538};
        cArr[169] = new char[]{4540, 4546};
        cArr[170] = new char[]{4587, 4587};
        cArr[171] = new char[]{4592, 4592};
        cArr[172] = new char[]{4601, 4601};
        cArr[173] = new char[]{7680, 7835};
        cArr[174] = new char[]{7840, 7929};
        cArr[175] = new char[]{7936, 7957};
        cArr[176] = new char[]{7960, 7965};
        cArr[177] = new char[]{7968, 8005};
        cArr[178] = new char[]{8008, 8013};
        cArr[179] = new char[]{8016, 8023};
        cArr[180] = new char[]{8025, 8025};
        cArr[181] = new char[]{8027, 8027};
        cArr[182] = new char[]{8029, 8029};
        cArr[183] = new char[]{8031, 8061};
        cArr[184] = new char[]{8064, 8116};
        cArr[185] = new char[]{8118, 8124};
        cArr[186] = new char[]{8126, 8126};
        cArr[187] = new char[]{8130, 8132};
        cArr[188] = new char[]{8134, 8140};
        cArr[189] = new char[]{8144, 8147};
        cArr[190] = new char[]{8150, 8155};
        cArr[191] = new char[]{8160, 8172};
        cArr[192] = new char[]{8178, 8180};
        cArr[193] = new char[]{8182, 8188};
        cArr[194] = new char[]{8486, 8486};
        cArr[195] = new char[]{8490, 8491};
        cArr[196] = new char[]{8494, 8494};
        cArr[197] = new char[]{8576, 8578};
        cArr[198] = new char[]{12353, 12436};
        cArr[199] = new char[]{12449, 12538};
        cArr[200] = new char[]{12549, 12588};
        cArr[201] = new char[]{44032, 55203};
        letterRanges = cArr;
    }

    protected class StreamInfo {
        int column = 1;
        String id;
        Reader in;
        int lineNumber = 1;

        StreamInfo(String str, Reader reader) {
            this.id = str;
            this.in = reader;
        }
    }

    public Scanner(Reader reader, EntityExpansion entityExpansion2) {
        this(reader, false, entityExpansion2);
    }

    public Scanner(Reader reader, boolean z, EntityExpansion entityExpansion2) {
        this.in = new StreamInfo("", reader);
        this.atEOF = false;
        this.trace = z;
        this.expandBuffer = null;
        this.entityExpansion = new Hashtable();
        this.expander = entityExpansion2;
    }

    public Token peek() throws IOException {
        if (this.nextToken == null) {
            this.nextToken = readNextToken();
        }
        return this.nextToken;
    }

    public Token get() throws IOException {
        if (this.nextToken == null) {
            this.nextToken = readNextToken();
        }
        Token token = this.nextToken;
        this.nextToken = null;
        return token;
    }

    /* access modifiers changed from: protected */
    public int readNextChar() throws IOException {
        Stack stack;
        int read = this.in.in.read();
        if (read >= 0 || (stack = this.inputStreams) == null || stack.empty()) {
            return read;
        }
        this.in.in.close();
        this.in = (StreamInfo) this.inputStreams.pop();
        return readNextChar();
    }

    /* access modifiers changed from: protected */
    public int peekChar() throws IOException {
        char[] cArr = this.expandBuffer;
        if (cArr != null) {
            return cArr[this.expandPos];
        }
        if (this.nextChar == 0) {
            this.nextChar = readNextChar();
            this.in.column++;
            if (this.nextChar == 10) {
                this.in.lineNumber++;
                this.in.column = 1;
            }
        }
        return this.nextChar;
    }

    /* access modifiers changed from: protected */
    public int read() throws IOException {
        char[] cArr = this.expandBuffer;
        if (cArr != null) {
            int i = this.expandPos;
            int i2 = i + 1;
            this.expandPos = i2;
            char c = cArr[i];
            if (i2 >= cArr.length) {
                this.expandPos = -1;
                this.expandBuffer = null;
            }
            if (this.trace) {
                System.out.print((char) c);
            }
            return c;
        }
        if (this.nextChar == 0) {
            peekChar();
        }
        int i3 = this.nextChar;
        this.nextChar = 0;
        if (this.trace) {
            System.out.print((char) i3);
        }
        return i3;
    }

    public String getUntil(char c) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int read = read();
            if (read < 0) {
                return stringBuffer.toString();
            }
            if (read == c) {
                return stringBuffer.toString();
            }
            stringBuffer.append((char) read);
        }
    }

    public void skipUntil(char c) throws IOException {
        int read;
        do {
            read = read();
            if (read < 0) {
                return;
            }
        } while (read != c);
    }

    /* access modifiers changed from: protected */
    public Token readNextToken() throws IOException {
        int read;
        while (true) {
            read = read();
            if (read == 60) {
                int peekChar = peekChar();
                if (peekChar == 33) {
                    read();
                    if (peekChar() == 91) {
                        read();
                        return new Token(CONDITIONAL);
                    } else if (peekChar() != 45) {
                        return new Token(LTBANG);
                    } else {
                        read();
                        if (peekChar() == 45) {
                            read();
                            StringBuffer stringBuffer = new StringBuffer();
                            while (peekChar() >= 0) {
                                if (peekChar() != 45) {
                                    stringBuffer.append((char) read());
                                } else {
                                    read();
                                    if (peekChar() < 0) {
                                        String uriId = getUriId();
                                        StringBuffer stringBuffer2 = new StringBuffer();
                                        stringBuffer2.append("Unterminated comment: <!--");
                                        stringBuffer2.append(stringBuffer.toString());
                                        throw new DTDParseException(uriId, stringBuffer2.toString(), getLineNumber(), getColumn());
                                    } else if (peekChar() == 45) {
                                        read();
                                        if (peekChar() == 62) {
                                            read();
                                            return new Token(COMMENT, stringBuffer.toString());
                                        }
                                        String uriId2 = getUriId();
                                        StringBuffer stringBuffer3 = new StringBuffer();
                                        stringBuffer3.append("Invalid character sequence --");
                                        stringBuffer3.append(read());
                                        throw new DTDParseException(uriId2, stringBuffer3.toString(), getLineNumber(), getColumn());
                                    } else {
                                        stringBuffer.append('-');
                                    }
                                }
                            }
                            String uriId3 = getUriId();
                            StringBuffer stringBuffer4 = new StringBuffer();
                            stringBuffer4.append("Unterminated comment: <!--");
                            stringBuffer4.append(stringBuffer.toString());
                            throw new DTDParseException(uriId3, stringBuffer4.toString(), getLineNumber(), getColumn());
                        }
                        String uriId4 = getUriId();
                        StringBuffer stringBuffer5 = new StringBuffer();
                        stringBuffer5.append("Invalid character sequence <!-");
                        stringBuffer5.append(read());
                        throw new DTDParseException(uriId4, stringBuffer5.toString(), getLineNumber(), getColumn());
                    }
                } else if (peekChar != 63) {
                    return new Token(LT);
                } else {
                    read();
                    return new Token(LTQUES);
                }
            } else if (read == 63) {
                return new Token(QUES);
            } else {
                if (read == 34 || read == 39) {
                    StringBuffer stringBuffer6 = new StringBuffer();
                } else if (read == 40) {
                    return new Token(LPAREN);
                } else {
                    if (read == 41) {
                        return new Token(RPAREN);
                    }
                    if (read == 124) {
                        return new Token(PIPE);
                    }
                    if (read == 62) {
                        return new Token(GT);
                    }
                    if (read == 61) {
                        return new Token(EQUAL);
                    }
                    if (read == 42) {
                        return new Token(ASTERISK);
                    }
                    if (read == 93) {
                        if (read() != 93) {
                            String uriId5 = getUriId();
                            StringBuffer stringBuffer7 = new StringBuffer();
                            stringBuffer7.append("Illegal character in input stream: ");
                            stringBuffer7.append(read);
                            throw new DTDParseException(uriId5, stringBuffer7.toString(), getLineNumber(), getColumn());
                        } else if (read() == 62) {
                            return new Token(ENDCONDITIONAL);
                        } else {
                            String uriId6 = getUriId();
                            StringBuffer stringBuffer8 = new StringBuffer();
                            stringBuffer8.append("Illegal character in input stream: ");
                            stringBuffer8.append(read);
                            throw new DTDParseException(uriId6, stringBuffer8.toString(), getLineNumber(), getColumn());
                        }
                    } else if (read == 35) {
                        StringBuffer stringBuffer9 = new StringBuffer();
                        stringBuffer9.append((char) read);
                        if (isIdentifierChar((char) peekChar())) {
                            stringBuffer9.append((char) read());
                            while (isNameChar((char) peekChar())) {
                                stringBuffer9.append((char) read());
                            }
                        }
                        return new Token(IDENTIFIER, stringBuffer9.toString());
                    } else {
                        boolean z = true;
                        if (read == 38 || read == 37) {
                            if (read == 37 && Character.isWhitespace((char) peekChar())) {
                                return new Token(PERCENT);
                            }
                            if (read != 37) {
                                z = false;
                            }
                            StringBuffer stringBuffer10 = new StringBuffer();
                            stringBuffer10.append((char) read);
                            if (isIdentifierChar((char) peekChar())) {
                                stringBuffer10.append((char) read());
                                while (isNameChar((char) peekChar())) {
                                    stringBuffer10.append((char) read());
                                }
                            }
                            if (read() == 59) {
                                stringBuffer10.append(';');
                                if (!z) {
                                    return new Token(IDENTIFIER, stringBuffer10.toString());
                                }
                                boolean expandEntity = expandEntity(stringBuffer10.toString());
                            } else {
                                String uriId7 = getUriId();
                                StringBuffer stringBuffer11 = new StringBuffer();
                                stringBuffer11.append("Expected ';' after reference ");
                                stringBuffer11.append(stringBuffer10.toString());
                                stringBuffer11.append(", found '");
                                stringBuffer11.append(read);
                                stringBuffer11.append("'");
                                throw new DTDParseException(uriId7, stringBuffer11.toString(), getLineNumber(), getColumn());
                            }
                        } else if (read == 43) {
                            return new Token(PLUS);
                        } else {
                            if (read == 44) {
                                return new Token(COMMA);
                            }
                            char c = (char) read;
                            if (isIdentifierChar(c)) {
                                StringBuffer stringBuffer12 = new StringBuffer();
                                stringBuffer12.append(c);
                                while (isNameChar((char) peekChar())) {
                                    stringBuffer12.append((char) read());
                                }
                                return new Token(IDENTIFIER, stringBuffer12.toString());
                            } else if (isNameChar(c)) {
                                StringBuffer stringBuffer13 = new StringBuffer();
                                stringBuffer13.append(c);
                                while (isNameChar((char) peekChar())) {
                                    stringBuffer13.append((char) read());
                                }
                                return new Token(NMTOKEN, stringBuffer13.toString());
                            } else if (read < 0) {
                                if (!this.atEOF) {
                                    this.atEOF = true;
                                    return new Token(EOF);
                                }
                                throw new IOException("Read past EOF");
                            } else if (!Character.isWhitespace(c)) {
                                String uriId8 = getUriId();
                                StringBuffer stringBuffer14 = new StringBuffer();
                                stringBuffer14.append("Illegal character in input stream: ");
                                stringBuffer14.append(read);
                                throw new DTDParseException(uriId8, stringBuffer14.toString(), getLineNumber(), getColumn());
                            }
                        }
                    }
                }
            }
        }
        StringBuffer stringBuffer62 = new StringBuffer();
        while (peekChar() != read) {
            int read2 = read();
            if (read2 == 92) {
                stringBuffer62.append((char) read());
            } else if (read2 < 0) {
                break;
            } else {
                stringBuffer62.append((char) read2);
            }
        }
        read();
        return new Token(STRING, stringBuffer62.toString());
    }

    public void skipConditional() throws IOException {
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i != 93) {
                i = read();
            }
            if (i == 93 && (i = read()) == 93 && (i = read()) == 62) {
                if (i2 != 0) {
                    i2--;
                } else {
                    return;
                }
            }
            if (i == 60 && (i = read()) == 33 && (i = read()) == 91) {
                i2++;
            }
        }
    }

    public String getUriId() {
        return this.in.id;
    }

    public int getLineNumber() {
        return this.in.lineNumber;
    }

    public int getColumn() {
        return this.in.column;
    }

    public boolean isIdentifierChar(char c) {
        return isLetter(c) || c == '_' || c == ':';
    }

    public boolean isNameChar(char c) {
        return isLetter(c) || isDigit(c) || c == '-' || c == '_' || c == '.' || c == ':' || isCombiningChar(c) || isExtender(c);
    }

    public boolean isLetter(char c) {
        return isBaseChar(c) || isIdeographic(c);
    }

    public boolean isBaseChar(char c) {
        int i = 0;
        while (true) {
            char[][] cArr = letterRanges;
            if (i >= cArr.length || c < cArr[i][0]) {
                return false;
            }
            if (c >= cArr[i][0] && c <= cArr[i][1]) {
                return true;
            }
            i++;
        }
    }

    public boolean expandEntity(String str) throws IOException {
        Reader reader;
        String str2 = (String) this.entityExpansion.get(str);
        if (str2 != null) {
            expand(str2.toCharArray());
            return true;
        }
        DTDEntity expandEntity = this.expander.expandEntity(str.substring(1, str.length() - 1));
        if (expandEntity == null || (reader = expandEntity.getReader()) == null) {
            return false;
        }
        if (this.inputStreams == null) {
            this.inputStreams = new Stack();
        }
        this.inputStreams.push(this.in);
        this.in = new StreamInfo(expandEntity.getExternalId(), reader);
        return true;
    }

    public void expand(char[] cArr) {
        char[] cArr2 = this.expandBuffer;
        if (cArr2 != null) {
            int length = cArr2.length - this.expandPos;
            char[] cArr3 = new char[(cArr.length + length)];
            System.arraycopy(cArr, 0, cArr3, 0, cArr.length);
            System.arraycopy(this.expandBuffer, this.expandPos, cArr3, cArr.length, length);
            this.expandPos = 0;
            this.expandBuffer = cArr3;
            if (cArr3.length == 0) {
                this.expandBuffer = null;
                this.expandPos = -1;
                return;
            }
            return;
        }
        this.expandBuffer = cArr;
        this.expandPos = 0;
        if (cArr.length == 0) {
            this.expandBuffer = null;
            this.expandPos = -1;
        }
    }

    public void addEntity(String str, String str2) {
        Hashtable hashtable = this.entityExpansion;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("%");
        stringBuffer.append(str);
        stringBuffer.append(";");
        hashtable.put(stringBuffer.toString(), str2);
    }
}
