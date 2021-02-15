package org.unbescape.html;

final class Html5EscapeSymbolsInitializer {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: org.unbescape.html.HtmlEscapeSymbols$References} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static org.unbescape.html.HtmlEscapeSymbols initializeHtml5() {
        /*
            org.unbescape.html.HtmlEscapeSymbols$References r0 = new org.unbescape.html.HtmlEscapeSymbols$References
            r0.<init>()
            r1 = 9
            java.lang.String r2 = "&Tab;"
            r0.addReference(r1, r2)
            r1 = 10
            java.lang.String r2 = "&NewLine;"
            r0.addReference(r1, r2)
            r1 = 33
            java.lang.String r2 = "&excl;"
            r0.addReference(r1, r2)
            r1 = 34
            java.lang.String r2 = "&quot;"
            r0.addReference(r1, r2)
            java.lang.String r2 = "&quot"
            r0.addReference(r1, r2)
            java.lang.String r2 = "&QUOT"
            r0.addReference(r1, r2)
            java.lang.String r2 = "&QUOT;"
            r0.addReference(r1, r2)
            r2 = 35
            java.lang.String r3 = "&num;"
            r0.addReference(r2, r3)
            r2 = 36
            java.lang.String r3 = "&dollar;"
            r0.addReference(r2, r3)
            r2 = 37
            java.lang.String r3 = "&percnt;"
            r0.addReference(r2, r3)
            r2 = 38
            java.lang.String r3 = "&amp;"
            r0.addReference(r2, r3)
            java.lang.String r3 = "&amp"
            r0.addReference(r2, r3)
            java.lang.String r3 = "&AMP"
            r0.addReference(r2, r3)
            java.lang.String r3 = "&AMP;"
            r0.addReference(r2, r3)
            r3 = 39
            java.lang.String r4 = "&apos;"
            r0.addReference(r3, r4)
            r3 = 40
            java.lang.String r4 = "&lpar;"
            r0.addReference(r3, r4)
            r3 = 41
            java.lang.String r4 = "&rpar;"
            r0.addReference(r3, r4)
            r3 = 42
            java.lang.String r4 = "&ast;"
            r0.addReference(r3, r4)
            java.lang.String r4 = "&midast;"
            r0.addReference(r3, r4)
            r3 = 43
            java.lang.String r4 = "&plus;"
            r0.addReference(r3, r4)
            r3 = 44
            java.lang.String r4 = "&comma;"
            r0.addReference(r3, r4)
            r3 = 46
            java.lang.String r4 = "&period;"
            r0.addReference(r3, r4)
            r3 = 47
            java.lang.String r4 = "&sol;"
            r0.addReference(r3, r4)
            r3 = 58
            java.lang.String r4 = "&colon;"
            r0.addReference(r3, r4)
            r3 = 59
            java.lang.String r4 = "&semi;"
            r0.addReference(r3, r4)
            r3 = 60
            java.lang.String r4 = "&lt;"
            r0.addReference(r3, r4)
            java.lang.String r4 = "&lt"
            r0.addReference(r3, r4)
            java.lang.String r4 = "&LT"
            r0.addReference(r3, r4)
            java.lang.String r4 = "&LT;"
            r0.addReference(r3, r4)
            r4 = 8402(0x20d2, float:1.1774E-41)
            java.lang.String r5 = "&nvlt;"
            r0.addReference(r3, r4, r5)
            r5 = 61
            java.lang.String r6 = "&equals;"
            r0.addReference(r5, r6)
            r6 = 8421(0x20e5, float:1.18E-41)
            java.lang.String r7 = "&bne;"
            r0.addReference(r5, r6, r7)
            r5 = 62
            java.lang.String r6 = "&gt;"
            r0.addReference(r5, r6)
            java.lang.String r6 = "&gt"
            r0.addReference(r5, r6)
            java.lang.String r6 = "&GT"
            r0.addReference(r5, r6)
            java.lang.String r6 = "&GT;"
            r0.addReference(r5, r6)
            java.lang.String r6 = "&nvgt;"
            r0.addReference(r5, r4, r6)
            r6 = 63
            java.lang.String r7 = "&quest;"
            r0.addReference(r6, r7)
            r6 = 64
            java.lang.String r7 = "&commat;"
            r0.addReference(r6, r7)
            r6 = 91
            java.lang.String r7 = "&lbrack;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&lsqb;"
            r0.addReference(r6, r7)
            r6 = 92
            java.lang.String r7 = "&bsol;"
            r0.addReference(r6, r7)
            r6 = 93
            java.lang.String r7 = "&rbrack;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&rsqb;"
            r0.addReference(r6, r7)
            r6 = 94
            java.lang.String r7 = "&Hat;"
            r0.addReference(r6, r7)
            r6 = 95
            java.lang.String r7 = "&lowbar;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&UnderBar;"
            r0.addReference(r6, r7)
            r6 = 96
            java.lang.String r7 = "&grave;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&DiacriticalGrave;"
            r0.addReference(r6, r7)
            r6 = 102(0x66, float:1.43E-43)
            r7 = 106(0x6a, float:1.49E-43)
            java.lang.String r8 = "&fjlig;"
            r0.addReference(r6, r7, r8)
            r6 = 123(0x7b, float:1.72E-43)
            java.lang.String r7 = "&lbrace;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&lcub;"
            r0.addReference(r6, r7)
            r6 = 124(0x7c, float:1.74E-43)
            java.lang.String r7 = "&verbar;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&vert;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&VerticalLine;"
            r0.addReference(r6, r7)
            r6 = 125(0x7d, float:1.75E-43)
            java.lang.String r7 = "&rbrace;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&rcub;"
            r0.addReference(r6, r7)
            r6 = 160(0xa0, float:2.24E-43)
            java.lang.String r7 = "&nbsp;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&nbsp"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&NonBreakingSpace;"
            r0.addReference(r6, r7)
            r6 = 161(0xa1, float:2.26E-43)
            java.lang.String r7 = "&iexcl;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&iexcl"
            r0.addReference(r6, r7)
            r6 = 162(0xa2, float:2.27E-43)
            java.lang.String r7 = "&cent;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&cent"
            r0.addReference(r6, r7)
            r6 = 163(0xa3, float:2.28E-43)
            java.lang.String r7 = "&pound;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&pound"
            r0.addReference(r6, r7)
            r6 = 164(0xa4, float:2.3E-43)
            java.lang.String r7 = "&curren;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&curren"
            r0.addReference(r6, r7)
            r6 = 165(0xa5, float:2.31E-43)
            java.lang.String r7 = "&yen;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&yen"
            r0.addReference(r6, r7)
            r6 = 166(0xa6, float:2.33E-43)
            java.lang.String r7 = "&brvbar;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&brvbar"
            r0.addReference(r6, r7)
            r6 = 167(0xa7, float:2.34E-43)
            java.lang.String r7 = "&sect;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&sect"
            r0.addReference(r6, r7)
            r6 = 168(0xa8, float:2.35E-43)
            java.lang.String r7 = "&uml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&die;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&uml"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Dot;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&DoubleDot;"
            r0.addReference(r6, r7)
            r6 = 169(0xa9, float:2.37E-43)
            java.lang.String r7 = "&copy;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&copy"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&COPY"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&COPY;"
            r0.addReference(r6, r7)
            r6 = 170(0xaa, float:2.38E-43)
            java.lang.String r7 = "&ordf;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ordf"
            r0.addReference(r6, r7)
            r6 = 171(0xab, float:2.4E-43)
            java.lang.String r7 = "&laquo;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&laquo"
            r0.addReference(r6, r7)
            r6 = 172(0xac, float:2.41E-43)
            java.lang.String r7 = "&not;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&not"
            r0.addReference(r6, r7)
            r6 = 173(0xad, float:2.42E-43)
            java.lang.String r7 = "&shy;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&shy"
            r0.addReference(r6, r7)
            r6 = 174(0xae, float:2.44E-43)
            java.lang.String r7 = "&reg;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&circledR;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&reg"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&REG"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&REG;"
            r0.addReference(r6, r7)
            r6 = 175(0xaf, float:2.45E-43)
            java.lang.String r7 = "&macr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&macr"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&strns;"
            r0.addReference(r6, r7)
            r6 = 176(0xb0, float:2.47E-43)
            java.lang.String r7 = "&deg;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&deg"
            r0.addReference(r6, r7)
            r6 = 177(0xb1, float:2.48E-43)
            java.lang.String r7 = "&plusmn;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&plusmn"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&pm;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&PlusMinus;"
            r0.addReference(r6, r7)
            r6 = 178(0xb2, float:2.5E-43)
            java.lang.String r7 = "&sup2;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&sup2"
            r0.addReference(r6, r7)
            r6 = 179(0xb3, float:2.51E-43)
            java.lang.String r7 = "&sup3;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&sup3"
            r0.addReference(r6, r7)
            r6 = 180(0xb4, float:2.52E-43)
            java.lang.String r7 = "&acute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&acute"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&DiacriticalAcute;"
            r0.addReference(r6, r7)
            r6 = 181(0xb5, float:2.54E-43)
            java.lang.String r7 = "&micro;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&micro"
            r0.addReference(r6, r7)
            r6 = 182(0xb6, float:2.55E-43)
            java.lang.String r7 = "&para;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&para"
            r0.addReference(r6, r7)
            r6 = 183(0xb7, float:2.56E-43)
            java.lang.String r7 = "&middot;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&centerdot;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&middot"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&CenterDot;"
            r0.addReference(r6, r7)
            r6 = 184(0xb8, float:2.58E-43)
            java.lang.String r7 = "&cedil;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&cedil"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Cedilla;"
            r0.addReference(r6, r7)
            r6 = 185(0xb9, float:2.59E-43)
            java.lang.String r7 = "&sup1;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&sup1"
            r0.addReference(r6, r7)
            r6 = 186(0xba, float:2.6E-43)
            java.lang.String r7 = "&ordm;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ordm"
            r0.addReference(r6, r7)
            r6 = 187(0xbb, float:2.62E-43)
            java.lang.String r7 = "&raquo;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&raquo"
            r0.addReference(r6, r7)
            r6 = 188(0xbc, float:2.63E-43)
            java.lang.String r7 = "&frac14;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&frac14"
            r0.addReference(r6, r7)
            r6 = 189(0xbd, float:2.65E-43)
            java.lang.String r7 = "&frac12;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&frac12"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&half;"
            r0.addReference(r6, r7)
            r6 = 190(0xbe, float:2.66E-43)
            java.lang.String r7 = "&frac34;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&frac34"
            r0.addReference(r6, r7)
            r6 = 191(0xbf, float:2.68E-43)
            java.lang.String r7 = "&iquest;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&iquest"
            r0.addReference(r6, r7)
            r6 = 192(0xc0, float:2.69E-43)
            java.lang.String r7 = "&Agrave;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Agrave"
            r0.addReference(r6, r7)
            r6 = 193(0xc1, float:2.7E-43)
            java.lang.String r7 = "&Aacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Aacute"
            r0.addReference(r6, r7)
            r6 = 194(0xc2, float:2.72E-43)
            java.lang.String r7 = "&Acirc;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Acirc"
            r0.addReference(r6, r7)
            r6 = 195(0xc3, float:2.73E-43)
            java.lang.String r7 = "&Atilde;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Atilde"
            r0.addReference(r6, r7)
            r6 = 196(0xc4, float:2.75E-43)
            java.lang.String r7 = "&Auml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Auml"
            r0.addReference(r6, r7)
            r6 = 197(0xc5, float:2.76E-43)
            java.lang.String r7 = "&Aring;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&angst;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Aring"
            r0.addReference(r6, r7)
            r6 = 198(0xc6, float:2.77E-43)
            java.lang.String r7 = "&AElig;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&AElig"
            r0.addReference(r6, r7)
            r6 = 199(0xc7, float:2.79E-43)
            java.lang.String r7 = "&Ccedil;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Ccedil"
            r0.addReference(r6, r7)
            r6 = 200(0xc8, float:2.8E-43)
            java.lang.String r7 = "&Egrave;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Egrave"
            r0.addReference(r6, r7)
            r6 = 201(0xc9, float:2.82E-43)
            java.lang.String r7 = "&Eacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Eacute"
            r0.addReference(r6, r7)
            r6 = 202(0xca, float:2.83E-43)
            java.lang.String r7 = "&Ecirc;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Ecirc"
            r0.addReference(r6, r7)
            r6 = 203(0xcb, float:2.84E-43)
            java.lang.String r7 = "&Euml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Euml"
            r0.addReference(r6, r7)
            r6 = 204(0xcc, float:2.86E-43)
            java.lang.String r7 = "&Igrave;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Igrave"
            r0.addReference(r6, r7)
            r6 = 205(0xcd, float:2.87E-43)
            java.lang.String r7 = "&Iacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Iacute"
            r0.addReference(r6, r7)
            r6 = 206(0xce, float:2.89E-43)
            java.lang.String r7 = "&Icirc;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Icirc"
            r0.addReference(r6, r7)
            r6 = 207(0xcf, float:2.9E-43)
            java.lang.String r7 = "&Iuml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Iuml"
            r0.addReference(r6, r7)
            r6 = 208(0xd0, float:2.91E-43)
            java.lang.String r7 = "&ETH;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ETH"
            r0.addReference(r6, r7)
            r6 = 209(0xd1, float:2.93E-43)
            java.lang.String r7 = "&Ntilde;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Ntilde"
            r0.addReference(r6, r7)
            r6 = 210(0xd2, float:2.94E-43)
            java.lang.String r7 = "&Ograve;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Ograve"
            r0.addReference(r6, r7)
            r6 = 211(0xd3, float:2.96E-43)
            java.lang.String r7 = "&Oacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Oacute"
            r0.addReference(r6, r7)
            r6 = 212(0xd4, float:2.97E-43)
            java.lang.String r7 = "&Ocirc;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Ocirc"
            r0.addReference(r6, r7)
            r6 = 213(0xd5, float:2.98E-43)
            java.lang.String r7 = "&Otilde;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Otilde"
            r0.addReference(r6, r7)
            r6 = 214(0xd6, float:3.0E-43)
            java.lang.String r7 = "&Ouml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Ouml"
            r0.addReference(r6, r7)
            r6 = 215(0xd7, float:3.01E-43)
            java.lang.String r7 = "&times;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&times"
            r0.addReference(r6, r7)
            r6 = 216(0xd8, float:3.03E-43)
            java.lang.String r7 = "&Oslash;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Oslash"
            r0.addReference(r6, r7)
            r6 = 217(0xd9, float:3.04E-43)
            java.lang.String r7 = "&Ugrave;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Ugrave"
            r0.addReference(r6, r7)
            r6 = 218(0xda, float:3.05E-43)
            java.lang.String r7 = "&Uacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Uacute"
            r0.addReference(r6, r7)
            r6 = 219(0xdb, float:3.07E-43)
            java.lang.String r7 = "&Ucirc;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Ucirc"
            r0.addReference(r6, r7)
            r6 = 220(0xdc, float:3.08E-43)
            java.lang.String r7 = "&Uuml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Uuml"
            r0.addReference(r6, r7)
            r6 = 221(0xdd, float:3.1E-43)
            java.lang.String r7 = "&Yacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Yacute"
            r0.addReference(r6, r7)
            r6 = 222(0xde, float:3.11E-43)
            java.lang.String r7 = "&THORN;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&THORN"
            r0.addReference(r6, r7)
            r6 = 223(0xdf, float:3.12E-43)
            java.lang.String r7 = "&szlig;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&szlig"
            r0.addReference(r6, r7)
            r6 = 224(0xe0, float:3.14E-43)
            java.lang.String r7 = "&agrave;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&agrave"
            r0.addReference(r6, r7)
            r6 = 225(0xe1, float:3.15E-43)
            java.lang.String r7 = "&aacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&aacute"
            r0.addReference(r6, r7)
            r6 = 226(0xe2, float:3.17E-43)
            java.lang.String r7 = "&acirc;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&acirc"
            r0.addReference(r6, r7)
            r6 = 227(0xe3, float:3.18E-43)
            java.lang.String r7 = "&atilde;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&atilde"
            r0.addReference(r6, r7)
            r6 = 228(0xe4, float:3.2E-43)
            java.lang.String r7 = "&auml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&auml"
            r0.addReference(r6, r7)
            r6 = 229(0xe5, float:3.21E-43)
            java.lang.String r7 = "&aring;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&aring"
            r0.addReference(r6, r7)
            r6 = 230(0xe6, float:3.22E-43)
            java.lang.String r7 = "&aelig;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&aelig"
            r0.addReference(r6, r7)
            r6 = 231(0xe7, float:3.24E-43)
            java.lang.String r7 = "&ccedil;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ccedil"
            r0.addReference(r6, r7)
            r6 = 232(0xe8, float:3.25E-43)
            java.lang.String r7 = "&egrave;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&egrave"
            r0.addReference(r6, r7)
            r6 = 233(0xe9, float:3.27E-43)
            java.lang.String r7 = "&eacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&eacute"
            r0.addReference(r6, r7)
            r6 = 234(0xea, float:3.28E-43)
            java.lang.String r7 = "&ecirc;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ecirc"
            r0.addReference(r6, r7)
            r6 = 235(0xeb, float:3.3E-43)
            java.lang.String r7 = "&euml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&euml"
            r0.addReference(r6, r7)
            r6 = 236(0xec, float:3.31E-43)
            java.lang.String r7 = "&igrave;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&igrave"
            r0.addReference(r6, r7)
            r6 = 237(0xed, float:3.32E-43)
            java.lang.String r7 = "&iacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&iacute"
            r0.addReference(r6, r7)
            r6 = 238(0xee, float:3.34E-43)
            java.lang.String r7 = "&icirc;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&icirc"
            r0.addReference(r6, r7)
            r6 = 239(0xef, float:3.35E-43)
            java.lang.String r7 = "&iuml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&iuml"
            r0.addReference(r6, r7)
            r6 = 240(0xf0, float:3.36E-43)
            java.lang.String r7 = "&eth;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&eth"
            r0.addReference(r6, r7)
            r6 = 241(0xf1, float:3.38E-43)
            java.lang.String r7 = "&ntilde;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ntilde"
            r0.addReference(r6, r7)
            r6 = 242(0xf2, float:3.39E-43)
            java.lang.String r7 = "&ograve;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ograve"
            r0.addReference(r6, r7)
            r6 = 243(0xf3, float:3.4E-43)
            java.lang.String r7 = "&oacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&oacute"
            r0.addReference(r6, r7)
            r6 = 244(0xf4, float:3.42E-43)
            java.lang.String r7 = "&ocirc;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ocirc"
            r0.addReference(r6, r7)
            r6 = 245(0xf5, float:3.43E-43)
            java.lang.String r7 = "&otilde;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&otilde"
            r0.addReference(r6, r7)
            r6 = 246(0xf6, float:3.45E-43)
            java.lang.String r7 = "&ouml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ouml"
            r0.addReference(r6, r7)
            r6 = 247(0xf7, float:3.46E-43)
            java.lang.String r7 = "&divide;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&div;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&divide"
            r0.addReference(r6, r7)
            r6 = 248(0xf8, float:3.48E-43)
            java.lang.String r7 = "&oslash;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&oslash"
            r0.addReference(r6, r7)
            r6 = 249(0xf9, float:3.49E-43)
            java.lang.String r7 = "&ugrave;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ugrave"
            r0.addReference(r6, r7)
            r6 = 250(0xfa, float:3.5E-43)
            java.lang.String r7 = "&uacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&uacute"
            r0.addReference(r6, r7)
            r6 = 251(0xfb, float:3.52E-43)
            java.lang.String r7 = "&ucirc;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ucirc"
            r0.addReference(r6, r7)
            r6 = 252(0xfc, float:3.53E-43)
            java.lang.String r7 = "&uuml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&uuml"
            r0.addReference(r6, r7)
            r6 = 253(0xfd, float:3.55E-43)
            java.lang.String r7 = "&yacute;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&yacute"
            r0.addReference(r6, r7)
            r6 = 254(0xfe, float:3.56E-43)
            java.lang.String r7 = "&thorn;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&thorn"
            r0.addReference(r6, r7)
            r6 = 255(0xff, float:3.57E-43)
            java.lang.String r7 = "&yuml;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&yuml"
            r0.addReference(r6, r7)
            r6 = 256(0x100, float:3.59E-43)
            java.lang.String r7 = "&Amacr;"
            r0.addReference(r6, r7)
            r6 = 257(0x101, float:3.6E-43)
            java.lang.String r7 = "&amacr;"
            r0.addReference(r6, r7)
            r6 = 258(0x102, float:3.62E-43)
            java.lang.String r7 = "&Abreve;"
            r0.addReference(r6, r7)
            r6 = 259(0x103, float:3.63E-43)
            java.lang.String r7 = "&abreve;"
            r0.addReference(r6, r7)
            r6 = 260(0x104, float:3.64E-43)
            java.lang.String r7 = "&Aogon;"
            r0.addReference(r6, r7)
            r6 = 261(0x105, float:3.66E-43)
            java.lang.String r7 = "&aogon;"
            r0.addReference(r6, r7)
            r6 = 262(0x106, float:3.67E-43)
            java.lang.String r7 = "&Cacute;"
            r0.addReference(r6, r7)
            r6 = 263(0x107, float:3.69E-43)
            java.lang.String r7 = "&cacute;"
            r0.addReference(r6, r7)
            r6 = 264(0x108, float:3.7E-43)
            java.lang.String r7 = "&Ccirc;"
            r0.addReference(r6, r7)
            r6 = 265(0x109, float:3.71E-43)
            java.lang.String r7 = "&ccirc;"
            r0.addReference(r6, r7)
            r6 = 266(0x10a, float:3.73E-43)
            java.lang.String r7 = "&Cdot;"
            r0.addReference(r6, r7)
            r6 = 267(0x10b, float:3.74E-43)
            java.lang.String r7 = "&cdot;"
            r0.addReference(r6, r7)
            r6 = 268(0x10c, float:3.76E-43)
            java.lang.String r7 = "&Ccaron;"
            r0.addReference(r6, r7)
            r6 = 269(0x10d, float:3.77E-43)
            java.lang.String r7 = "&ccaron;"
            r0.addReference(r6, r7)
            r6 = 270(0x10e, float:3.78E-43)
            java.lang.String r7 = "&Dcaron;"
            r0.addReference(r6, r7)
            r6 = 271(0x10f, float:3.8E-43)
            java.lang.String r7 = "&dcaron;"
            r0.addReference(r6, r7)
            r6 = 272(0x110, float:3.81E-43)
            java.lang.String r7 = "&Dstrok;"
            r0.addReference(r6, r7)
            r6 = 273(0x111, float:3.83E-43)
            java.lang.String r7 = "&dstrok;"
            r0.addReference(r6, r7)
            r6 = 274(0x112, float:3.84E-43)
            java.lang.String r7 = "&Emacr;"
            r0.addReference(r6, r7)
            r6 = 275(0x113, float:3.85E-43)
            java.lang.String r7 = "&emacr;"
            r0.addReference(r6, r7)
            r6 = 278(0x116, float:3.9E-43)
            java.lang.String r7 = "&Edot;"
            r0.addReference(r6, r7)
            r6 = 279(0x117, float:3.91E-43)
            java.lang.String r7 = "&edot;"
            r0.addReference(r6, r7)
            r6 = 280(0x118, float:3.92E-43)
            java.lang.String r7 = "&Eogon;"
            r0.addReference(r6, r7)
            r6 = 281(0x119, float:3.94E-43)
            java.lang.String r7 = "&eogon;"
            r0.addReference(r6, r7)
            r6 = 282(0x11a, float:3.95E-43)
            java.lang.String r7 = "&Ecaron;"
            r0.addReference(r6, r7)
            r6 = 283(0x11b, float:3.97E-43)
            java.lang.String r7 = "&ecaron;"
            r0.addReference(r6, r7)
            r6 = 284(0x11c, float:3.98E-43)
            java.lang.String r7 = "&Gcirc;"
            r0.addReference(r6, r7)
            r6 = 285(0x11d, float:4.0E-43)
            java.lang.String r7 = "&gcirc;"
            r0.addReference(r6, r7)
            r6 = 286(0x11e, float:4.01E-43)
            java.lang.String r7 = "&Gbreve;"
            r0.addReference(r6, r7)
            r6 = 287(0x11f, float:4.02E-43)
            java.lang.String r7 = "&gbreve;"
            r0.addReference(r6, r7)
            r6 = 288(0x120, float:4.04E-43)
            java.lang.String r7 = "&Gdot;"
            r0.addReference(r6, r7)
            r6 = 289(0x121, float:4.05E-43)
            java.lang.String r7 = "&gdot;"
            r0.addReference(r6, r7)
            r6 = 290(0x122, float:4.06E-43)
            java.lang.String r7 = "&Gcedil;"
            r0.addReference(r6, r7)
            r6 = 292(0x124, float:4.09E-43)
            java.lang.String r7 = "&Hcirc;"
            r0.addReference(r6, r7)
            r6 = 293(0x125, float:4.1E-43)
            java.lang.String r7 = "&hcirc;"
            r0.addReference(r6, r7)
            r6 = 294(0x126, float:4.12E-43)
            java.lang.String r7 = "&Hstrok;"
            r0.addReference(r6, r7)
            r6 = 295(0x127, float:4.13E-43)
            java.lang.String r7 = "&hstrok;"
            r0.addReference(r6, r7)
            r6 = 296(0x128, float:4.15E-43)
            java.lang.String r7 = "&Itilde;"
            r0.addReference(r6, r7)
            r6 = 297(0x129, float:4.16E-43)
            java.lang.String r7 = "&itilde;"
            r0.addReference(r6, r7)
            r6 = 298(0x12a, float:4.18E-43)
            java.lang.String r7 = "&Imacr;"
            r0.addReference(r6, r7)
            r6 = 299(0x12b, float:4.19E-43)
            java.lang.String r7 = "&imacr;"
            r0.addReference(r6, r7)
            r6 = 302(0x12e, float:4.23E-43)
            java.lang.String r7 = "&Iogon;"
            r0.addReference(r6, r7)
            r6 = 303(0x12f, float:4.25E-43)
            java.lang.String r7 = "&iogon;"
            r0.addReference(r6, r7)
            r6 = 304(0x130, float:4.26E-43)
            java.lang.String r7 = "&Idot;"
            r0.addReference(r6, r7)
            r6 = 305(0x131, float:4.27E-43)
            java.lang.String r7 = "&imath;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&inodot;"
            r0.addReference(r6, r7)
            r6 = 306(0x132, float:4.29E-43)
            java.lang.String r7 = "&IJlig;"
            r0.addReference(r6, r7)
            r6 = 307(0x133, float:4.3E-43)
            java.lang.String r7 = "&ijlig;"
            r0.addReference(r6, r7)
            r6 = 308(0x134, float:4.32E-43)
            java.lang.String r7 = "&Jcirc;"
            r0.addReference(r6, r7)
            r6 = 309(0x135, float:4.33E-43)
            java.lang.String r7 = "&jcirc;"
            r0.addReference(r6, r7)
            r6 = 310(0x136, float:4.34E-43)
            java.lang.String r7 = "&Kcedil;"
            r0.addReference(r6, r7)
            r6 = 311(0x137, float:4.36E-43)
            java.lang.String r7 = "&kcedil;"
            r0.addReference(r6, r7)
            r6 = 312(0x138, float:4.37E-43)
            java.lang.String r7 = "&kgreen;"
            r0.addReference(r6, r7)
            r6 = 313(0x139, float:4.39E-43)
            java.lang.String r7 = "&Lacute;"
            r0.addReference(r6, r7)
            r6 = 314(0x13a, float:4.4E-43)
            java.lang.String r7 = "&lacute;"
            r0.addReference(r6, r7)
            r6 = 315(0x13b, float:4.41E-43)
            java.lang.String r7 = "&Lcedil;"
            r0.addReference(r6, r7)
            r6 = 316(0x13c, float:4.43E-43)
            java.lang.String r7 = "&lcedil;"
            r0.addReference(r6, r7)
            r6 = 317(0x13d, float:4.44E-43)
            java.lang.String r7 = "&Lcaron;"
            r0.addReference(r6, r7)
            r6 = 318(0x13e, float:4.46E-43)
            java.lang.String r7 = "&lcaron;"
            r0.addReference(r6, r7)
            r6 = 319(0x13f, float:4.47E-43)
            java.lang.String r7 = "&Lmidot;"
            r0.addReference(r6, r7)
            r6 = 320(0x140, float:4.48E-43)
            java.lang.String r7 = "&lmidot;"
            r0.addReference(r6, r7)
            r6 = 321(0x141, float:4.5E-43)
            java.lang.String r7 = "&Lstrok;"
            r0.addReference(r6, r7)
            r6 = 322(0x142, float:4.51E-43)
            java.lang.String r7 = "&lstrok;"
            r0.addReference(r6, r7)
            r6 = 323(0x143, float:4.53E-43)
            java.lang.String r7 = "&Nacute;"
            r0.addReference(r6, r7)
            r6 = 324(0x144, float:4.54E-43)
            java.lang.String r7 = "&nacute;"
            r0.addReference(r6, r7)
            r6 = 325(0x145, float:4.55E-43)
            java.lang.String r7 = "&Ncedil;"
            r0.addReference(r6, r7)
            r6 = 326(0x146, float:4.57E-43)
            java.lang.String r7 = "&ncedil;"
            r0.addReference(r6, r7)
            r6 = 327(0x147, float:4.58E-43)
            java.lang.String r7 = "&Ncaron;"
            r0.addReference(r6, r7)
            r6 = 328(0x148, float:4.6E-43)
            java.lang.String r7 = "&ncaron;"
            r0.addReference(r6, r7)
            r6 = 329(0x149, float:4.61E-43)
            java.lang.String r7 = "&napos;"
            r0.addReference(r6, r7)
            r6 = 330(0x14a, float:4.62E-43)
            java.lang.String r7 = "&ENG;"
            r0.addReference(r6, r7)
            r6 = 331(0x14b, float:4.64E-43)
            java.lang.String r7 = "&eng;"
            r0.addReference(r6, r7)
            r6 = 332(0x14c, float:4.65E-43)
            java.lang.String r7 = "&Omacr;"
            r0.addReference(r6, r7)
            r6 = 333(0x14d, float:4.67E-43)
            java.lang.String r7 = "&omacr;"
            r0.addReference(r6, r7)
            r6 = 336(0x150, float:4.71E-43)
            java.lang.String r7 = "&Odblac;"
            r0.addReference(r6, r7)
            r6 = 337(0x151, float:4.72E-43)
            java.lang.String r7 = "&odblac;"
            r0.addReference(r6, r7)
            r6 = 338(0x152, float:4.74E-43)
            java.lang.String r7 = "&OElig;"
            r0.addReference(r6, r7)
            r6 = 339(0x153, float:4.75E-43)
            java.lang.String r7 = "&oelig;"
            r0.addReference(r6, r7)
            r6 = 340(0x154, float:4.76E-43)
            java.lang.String r7 = "&Racute;"
            r0.addReference(r6, r7)
            r6 = 341(0x155, float:4.78E-43)
            java.lang.String r7 = "&racute;"
            r0.addReference(r6, r7)
            r6 = 342(0x156, float:4.79E-43)
            java.lang.String r7 = "&Rcedil;"
            r0.addReference(r6, r7)
            r6 = 343(0x157, float:4.8E-43)
            java.lang.String r7 = "&rcedil;"
            r0.addReference(r6, r7)
            r6 = 344(0x158, float:4.82E-43)
            java.lang.String r7 = "&Rcaron;"
            r0.addReference(r6, r7)
            r6 = 345(0x159, float:4.83E-43)
            java.lang.String r7 = "&rcaron;"
            r0.addReference(r6, r7)
            r6 = 346(0x15a, float:4.85E-43)
            java.lang.String r7 = "&Sacute;"
            r0.addReference(r6, r7)
            r6 = 347(0x15b, float:4.86E-43)
            java.lang.String r7 = "&sacute;"
            r0.addReference(r6, r7)
            r6 = 348(0x15c, float:4.88E-43)
            java.lang.String r7 = "&Scirc;"
            r0.addReference(r6, r7)
            r6 = 349(0x15d, float:4.89E-43)
            java.lang.String r7 = "&scirc;"
            r0.addReference(r6, r7)
            r6 = 350(0x15e, float:4.9E-43)
            java.lang.String r7 = "&Scedil;"
            r0.addReference(r6, r7)
            r6 = 351(0x15f, float:4.92E-43)
            java.lang.String r7 = "&scedil;"
            r0.addReference(r6, r7)
            r6 = 352(0x160, float:4.93E-43)
            java.lang.String r7 = "&Scaron;"
            r0.addReference(r6, r7)
            r6 = 353(0x161, float:4.95E-43)
            java.lang.String r7 = "&scaron;"
            r0.addReference(r6, r7)
            r6 = 354(0x162, float:4.96E-43)
            java.lang.String r7 = "&Tcedil;"
            r0.addReference(r6, r7)
            r6 = 355(0x163, float:4.97E-43)
            java.lang.String r7 = "&tcedil;"
            r0.addReference(r6, r7)
            r6 = 356(0x164, float:4.99E-43)
            java.lang.String r7 = "&Tcaron;"
            r0.addReference(r6, r7)
            r6 = 357(0x165, float:5.0E-43)
            java.lang.String r7 = "&tcaron;"
            r0.addReference(r6, r7)
            r6 = 358(0x166, float:5.02E-43)
            java.lang.String r7 = "&Tstrok;"
            r0.addReference(r6, r7)
            r6 = 359(0x167, float:5.03E-43)
            java.lang.String r7 = "&tstrok;"
            r0.addReference(r6, r7)
            r6 = 360(0x168, float:5.04E-43)
            java.lang.String r7 = "&Utilde;"
            r0.addReference(r6, r7)
            r6 = 361(0x169, float:5.06E-43)
            java.lang.String r7 = "&utilde;"
            r0.addReference(r6, r7)
            r6 = 362(0x16a, float:5.07E-43)
            java.lang.String r7 = "&Umacr;"
            r0.addReference(r6, r7)
            r6 = 363(0x16b, float:5.09E-43)
            java.lang.String r7 = "&umacr;"
            r0.addReference(r6, r7)
            r6 = 364(0x16c, float:5.1E-43)
            java.lang.String r7 = "&Ubreve;"
            r0.addReference(r6, r7)
            r6 = 365(0x16d, float:5.11E-43)
            java.lang.String r7 = "&ubreve;"
            r0.addReference(r6, r7)
            r6 = 366(0x16e, float:5.13E-43)
            java.lang.String r7 = "&Uring;"
            r0.addReference(r6, r7)
            r6 = 367(0x16f, float:5.14E-43)
            java.lang.String r7 = "&uring;"
            r0.addReference(r6, r7)
            r6 = 368(0x170, float:5.16E-43)
            java.lang.String r7 = "&Udblac;"
            r0.addReference(r6, r7)
            r6 = 369(0x171, float:5.17E-43)
            java.lang.String r7 = "&udblac;"
            r0.addReference(r6, r7)
            r6 = 370(0x172, float:5.18E-43)
            java.lang.String r7 = "&Uogon;"
            r0.addReference(r6, r7)
            r6 = 371(0x173, float:5.2E-43)
            java.lang.String r7 = "&uogon;"
            r0.addReference(r6, r7)
            r6 = 372(0x174, float:5.21E-43)
            java.lang.String r7 = "&Wcirc;"
            r0.addReference(r6, r7)
            r6 = 373(0x175, float:5.23E-43)
            java.lang.String r7 = "&wcirc;"
            r0.addReference(r6, r7)
            r6 = 374(0x176, float:5.24E-43)
            java.lang.String r7 = "&Ycirc;"
            r0.addReference(r6, r7)
            r6 = 375(0x177, float:5.25E-43)
            java.lang.String r7 = "&ycirc;"
            r0.addReference(r6, r7)
            r6 = 376(0x178, float:5.27E-43)
            java.lang.String r7 = "&Yuml;"
            r0.addReference(r6, r7)
            r6 = 377(0x179, float:5.28E-43)
            java.lang.String r7 = "&Zacute;"
            r0.addReference(r6, r7)
            r6 = 378(0x17a, float:5.3E-43)
            java.lang.String r7 = "&zacute;"
            r0.addReference(r6, r7)
            r6 = 379(0x17b, float:5.31E-43)
            java.lang.String r7 = "&Zdot;"
            r0.addReference(r6, r7)
            r6 = 380(0x17c, float:5.32E-43)
            java.lang.String r7 = "&zdot;"
            r0.addReference(r6, r7)
            r6 = 381(0x17d, float:5.34E-43)
            java.lang.String r7 = "&Zcaron;"
            r0.addReference(r6, r7)
            r6 = 382(0x17e, float:5.35E-43)
            java.lang.String r7 = "&zcaron;"
            r0.addReference(r6, r7)
            r6 = 402(0x192, float:5.63E-43)
            java.lang.String r7 = "&fnof;"
            r0.addReference(r6, r7)
            r6 = 437(0x1b5, float:6.12E-43)
            java.lang.String r7 = "&imped;"
            r0.addReference(r6, r7)
            r6 = 501(0x1f5, float:7.02E-43)
            java.lang.String r7 = "&gacute;"
            r0.addReference(r6, r7)
            r6 = 567(0x237, float:7.95E-43)
            java.lang.String r7 = "&jmath;"
            r0.addReference(r6, r7)
            r6 = 710(0x2c6, float:9.95E-43)
            java.lang.String r7 = "&circ;"
            r0.addReference(r6, r7)
            r6 = 711(0x2c7, float:9.96E-43)
            java.lang.String r7 = "&caron;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Hacek;"
            r0.addReference(r6, r7)
            r6 = 728(0x2d8, float:1.02E-42)
            java.lang.String r7 = "&breve;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Breve;"
            r0.addReference(r6, r7)
            r6 = 729(0x2d9, float:1.022E-42)
            java.lang.String r7 = "&dot;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&DiacriticalDot;"
            r0.addReference(r6, r7)
            r6 = 730(0x2da, float:1.023E-42)
            java.lang.String r7 = "&ring;"
            r0.addReference(r6, r7)
            r6 = 731(0x2db, float:1.024E-42)
            java.lang.String r7 = "&ogon;"
            r0.addReference(r6, r7)
            r6 = 732(0x2dc, float:1.026E-42)
            java.lang.String r7 = "&tilde;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&DiacriticalTilde;"
            r0.addReference(r6, r7)
            r6 = 733(0x2dd, float:1.027E-42)
            java.lang.String r7 = "&dblac;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&DiacriticalDoubleAcute;"
            r0.addReference(r6, r7)
            r6 = 785(0x311, float:1.1E-42)
            java.lang.String r7 = "&DownBreve;"
            r0.addReference(r6, r7)
            r6 = 913(0x391, float:1.28E-42)
            java.lang.String r7 = "&Alpha;"
            r0.addReference(r6, r7)
            r6 = 914(0x392, float:1.281E-42)
            java.lang.String r7 = "&Beta;"
            r0.addReference(r6, r7)
            r6 = 915(0x393, float:1.282E-42)
            java.lang.String r7 = "&Gamma;"
            r0.addReference(r6, r7)
            r6 = 916(0x394, float:1.284E-42)
            java.lang.String r7 = "&Delta;"
            r0.addReference(r6, r7)
            r6 = 917(0x395, float:1.285E-42)
            java.lang.String r7 = "&Epsilon;"
            r0.addReference(r6, r7)
            r6 = 918(0x396, float:1.286E-42)
            java.lang.String r7 = "&Zeta;"
            r0.addReference(r6, r7)
            r6 = 919(0x397, float:1.288E-42)
            java.lang.String r7 = "&Eta;"
            r0.addReference(r6, r7)
            r6 = 920(0x398, float:1.289E-42)
            java.lang.String r7 = "&Theta;"
            r0.addReference(r6, r7)
            r6 = 921(0x399, float:1.29E-42)
            java.lang.String r7 = "&Iota;"
            r0.addReference(r6, r7)
            r6 = 922(0x39a, float:1.292E-42)
            java.lang.String r7 = "&Kappa;"
            r0.addReference(r6, r7)
            r6 = 923(0x39b, float:1.293E-42)
            java.lang.String r7 = "&Lambda;"
            r0.addReference(r6, r7)
            r6 = 924(0x39c, float:1.295E-42)
            java.lang.String r7 = "&Mu;"
            r0.addReference(r6, r7)
            r6 = 925(0x39d, float:1.296E-42)
            java.lang.String r7 = "&Nu;"
            r0.addReference(r6, r7)
            r6 = 926(0x39e, float:1.298E-42)
            java.lang.String r7 = "&Xi;"
            r0.addReference(r6, r7)
            r6 = 927(0x39f, float:1.299E-42)
            java.lang.String r7 = "&Omicron;"
            r0.addReference(r6, r7)
            r6 = 928(0x3a0, float:1.3E-42)
            java.lang.String r7 = "&Pi;"
            r0.addReference(r6, r7)
            r6 = 929(0x3a1, float:1.302E-42)
            java.lang.String r7 = "&Rho;"
            r0.addReference(r6, r7)
            r6 = 931(0x3a3, float:1.305E-42)
            java.lang.String r7 = "&Sigma;"
            r0.addReference(r6, r7)
            r6 = 932(0x3a4, float:1.306E-42)
            java.lang.String r7 = "&Tau;"
            r0.addReference(r6, r7)
            r6 = 933(0x3a5, float:1.307E-42)
            java.lang.String r7 = "&Upsilon;"
            r0.addReference(r6, r7)
            r6 = 934(0x3a6, float:1.309E-42)
            java.lang.String r7 = "&Phi;"
            r0.addReference(r6, r7)
            r6 = 935(0x3a7, float:1.31E-42)
            java.lang.String r7 = "&Chi;"
            r0.addReference(r6, r7)
            r6 = 936(0x3a8, float:1.312E-42)
            java.lang.String r7 = "&Psi;"
            r0.addReference(r6, r7)
            r6 = 937(0x3a9, float:1.313E-42)
            java.lang.String r7 = "&Omega;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ohm;"
            r0.addReference(r6, r7)
            r6 = 945(0x3b1, float:1.324E-42)
            java.lang.String r7 = "&alpha;"
            r0.addReference(r6, r7)
            r6 = 946(0x3b2, float:1.326E-42)
            java.lang.String r7 = "&beta;"
            r0.addReference(r6, r7)
            r6 = 947(0x3b3, float:1.327E-42)
            java.lang.String r7 = "&gamma;"
            r0.addReference(r6, r7)
            r6 = 948(0x3b4, float:1.328E-42)
            java.lang.String r7 = "&delta;"
            r0.addReference(r6, r7)
            r6 = 949(0x3b5, float:1.33E-42)
            java.lang.String r7 = "&epsilon;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&epsi;"
            r0.addReference(r6, r7)
            r6 = 950(0x3b6, float:1.331E-42)
            java.lang.String r7 = "&zeta;"
            r0.addReference(r6, r7)
            r6 = 951(0x3b7, float:1.333E-42)
            java.lang.String r7 = "&eta;"
            r0.addReference(r6, r7)
            r6 = 952(0x3b8, float:1.334E-42)
            java.lang.String r7 = "&theta;"
            r0.addReference(r6, r7)
            r6 = 953(0x3b9, float:1.335E-42)
            java.lang.String r7 = "&iota;"
            r0.addReference(r6, r7)
            r6 = 954(0x3ba, float:1.337E-42)
            java.lang.String r7 = "&kappa;"
            r0.addReference(r6, r7)
            r6 = 955(0x3bb, float:1.338E-42)
            java.lang.String r7 = "&lambda;"
            r0.addReference(r6, r7)
            r6 = 956(0x3bc, float:1.34E-42)
            java.lang.String r7 = "&mu;"
            r0.addReference(r6, r7)
            r6 = 957(0x3bd, float:1.341E-42)
            java.lang.String r7 = "&nu;"
            r0.addReference(r6, r7)
            r6 = 958(0x3be, float:1.342E-42)
            java.lang.String r7 = "&xi;"
            r0.addReference(r6, r7)
            r6 = 959(0x3bf, float:1.344E-42)
            java.lang.String r7 = "&omicron;"
            r0.addReference(r6, r7)
            r6 = 960(0x3c0, float:1.345E-42)
            java.lang.String r7 = "&pi;"
            r0.addReference(r6, r7)
            r6 = 961(0x3c1, float:1.347E-42)
            java.lang.String r7 = "&rho;"
            r0.addReference(r6, r7)
            r6 = 962(0x3c2, float:1.348E-42)
            java.lang.String r7 = "&sigmaf;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&sigmav;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&varsigma;"
            r0.addReference(r6, r7)
            r6 = 963(0x3c3, float:1.35E-42)
            java.lang.String r7 = "&sigma;"
            r0.addReference(r6, r7)
            r6 = 964(0x3c4, float:1.351E-42)
            java.lang.String r7 = "&tau;"
            r0.addReference(r6, r7)
            r6 = 965(0x3c5, float:1.352E-42)
            java.lang.String r7 = "&upsilon;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&upsi;"
            r0.addReference(r6, r7)
            r6 = 966(0x3c6, float:1.354E-42)
            java.lang.String r7 = "&phi;"
            r0.addReference(r6, r7)
            r6 = 967(0x3c7, float:1.355E-42)
            java.lang.String r7 = "&chi;"
            r0.addReference(r6, r7)
            r6 = 968(0x3c8, float:1.356E-42)
            java.lang.String r7 = "&psi;"
            r0.addReference(r6, r7)
            r6 = 969(0x3c9, float:1.358E-42)
            java.lang.String r7 = "&omega;"
            r0.addReference(r6, r7)
            r6 = 977(0x3d1, float:1.369E-42)
            java.lang.String r7 = "&thetasym;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&thetav;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&vartheta;"
            r0.addReference(r6, r7)
            r6 = 978(0x3d2, float:1.37E-42)
            java.lang.String r7 = "&upsih;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Upsi;"
            r0.addReference(r6, r7)
            r6 = 981(0x3d5, float:1.375E-42)
            java.lang.String r7 = "&phiv;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&straightphi;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&varphi;"
            r0.addReference(r6, r7)
            r6 = 982(0x3d6, float:1.376E-42)
            java.lang.String r7 = "&piv;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&varpi;"
            r0.addReference(r6, r7)
            r6 = 988(0x3dc, float:1.384E-42)
            java.lang.String r7 = "&Gammad;"
            r0.addReference(r6, r7)
            r6 = 989(0x3dd, float:1.386E-42)
            java.lang.String r7 = "&digamma;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&gammad;"
            r0.addReference(r6, r7)
            r6 = 1008(0x3f0, float:1.413E-42)
            java.lang.String r7 = "&kappav;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&varkappa;"
            r0.addReference(r6, r7)
            r6 = 1009(0x3f1, float:1.414E-42)
            java.lang.String r7 = "&rhov;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&varrho;"
            r0.addReference(r6, r7)
            r6 = 1013(0x3f5, float:1.42E-42)
            java.lang.String r7 = "&epsiv;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&straightepsilon;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&varepsilon;"
            r0.addReference(r6, r7)
            r6 = 1014(0x3f6, float:1.421E-42)
            java.lang.String r7 = "&backepsilon;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&bepsi;"
            r0.addReference(r6, r7)
            r6 = 1025(0x401, float:1.436E-42)
            java.lang.String r7 = "&IOcy;"
            r0.addReference(r6, r7)
            r6 = 1026(0x402, float:1.438E-42)
            java.lang.String r7 = "&DJcy;"
            r0.addReference(r6, r7)
            r6 = 1027(0x403, float:1.439E-42)
            java.lang.String r7 = "&GJcy;"
            r0.addReference(r6, r7)
            r6 = 1028(0x404, float:1.44E-42)
            java.lang.String r7 = "&Jukcy;"
            r0.addReference(r6, r7)
            r6 = 1029(0x405, float:1.442E-42)
            java.lang.String r7 = "&DScy;"
            r0.addReference(r6, r7)
            r6 = 1030(0x406, float:1.443E-42)
            java.lang.String r7 = "&Iukcy;"
            r0.addReference(r6, r7)
            r6 = 1031(0x407, float:1.445E-42)
            java.lang.String r7 = "&YIcy;"
            r0.addReference(r6, r7)
            r6 = 1032(0x408, float:1.446E-42)
            java.lang.String r7 = "&Jsercy;"
            r0.addReference(r6, r7)
            r6 = 1033(0x409, float:1.448E-42)
            java.lang.String r7 = "&LJcy;"
            r0.addReference(r6, r7)
            r6 = 1034(0x40a, float:1.449E-42)
            java.lang.String r7 = "&NJcy;"
            r0.addReference(r6, r7)
            r6 = 1035(0x40b, float:1.45E-42)
            java.lang.String r7 = "&TSHcy;"
            r0.addReference(r6, r7)
            r6 = 1036(0x40c, float:1.452E-42)
            java.lang.String r7 = "&KJcy;"
            r0.addReference(r6, r7)
            r6 = 1038(0x40e, float:1.455E-42)
            java.lang.String r7 = "&Ubrcy;"
            r0.addReference(r6, r7)
            r6 = 1039(0x40f, float:1.456E-42)
            java.lang.String r7 = "&DZcy;"
            r0.addReference(r6, r7)
            r6 = 1040(0x410, float:1.457E-42)
            java.lang.String r7 = "&Acy;"
            r0.addReference(r6, r7)
            r6 = 1041(0x411, float:1.459E-42)
            java.lang.String r7 = "&Bcy;"
            r0.addReference(r6, r7)
            r6 = 1042(0x412, float:1.46E-42)
            java.lang.String r7 = "&Vcy;"
            r0.addReference(r6, r7)
            r6 = 1043(0x413, float:1.462E-42)
            java.lang.String r7 = "&Gcy;"
            r0.addReference(r6, r7)
            r6 = 1044(0x414, float:1.463E-42)
            java.lang.String r7 = "&Dcy;"
            r0.addReference(r6, r7)
            r6 = 1045(0x415, float:1.464E-42)
            java.lang.String r7 = "&IEcy;"
            r0.addReference(r6, r7)
            r6 = 1046(0x416, float:1.466E-42)
            java.lang.String r7 = "&ZHcy;"
            r0.addReference(r6, r7)
            r6 = 1047(0x417, float:1.467E-42)
            java.lang.String r7 = "&Zcy;"
            r0.addReference(r6, r7)
            r6 = 1048(0x418, float:1.469E-42)
            java.lang.String r7 = "&Icy;"
            r0.addReference(r6, r7)
            r6 = 1049(0x419, float:1.47E-42)
            java.lang.String r7 = "&Jcy;"
            r0.addReference(r6, r7)
            r6 = 1050(0x41a, float:1.471E-42)
            java.lang.String r7 = "&Kcy;"
            r0.addReference(r6, r7)
            r6 = 1051(0x41b, float:1.473E-42)
            java.lang.String r7 = "&Lcy;"
            r0.addReference(r6, r7)
            r6 = 1052(0x41c, float:1.474E-42)
            java.lang.String r7 = "&Mcy;"
            r0.addReference(r6, r7)
            r6 = 1053(0x41d, float:1.476E-42)
            java.lang.String r7 = "&Ncy;"
            r0.addReference(r6, r7)
            r6 = 1054(0x41e, float:1.477E-42)
            java.lang.String r7 = "&Ocy;"
            r0.addReference(r6, r7)
            r6 = 1055(0x41f, float:1.478E-42)
            java.lang.String r7 = "&Pcy;"
            r0.addReference(r6, r7)
            r6 = 1056(0x420, float:1.48E-42)
            java.lang.String r7 = "&Rcy;"
            r0.addReference(r6, r7)
            r6 = 1057(0x421, float:1.481E-42)
            java.lang.String r7 = "&Scy;"
            r0.addReference(r6, r7)
            r6 = 1058(0x422, float:1.483E-42)
            java.lang.String r7 = "&Tcy;"
            r0.addReference(r6, r7)
            r6 = 1059(0x423, float:1.484E-42)
            java.lang.String r7 = "&Ucy;"
            r0.addReference(r6, r7)
            r6 = 1060(0x424, float:1.485E-42)
            java.lang.String r7 = "&Fcy;"
            r0.addReference(r6, r7)
            r6 = 1061(0x425, float:1.487E-42)
            java.lang.String r7 = "&KHcy;"
            r0.addReference(r6, r7)
            r6 = 1062(0x426, float:1.488E-42)
            java.lang.String r7 = "&TScy;"
            r0.addReference(r6, r7)
            r6 = 1063(0x427, float:1.49E-42)
            java.lang.String r7 = "&CHcy;"
            r0.addReference(r6, r7)
            r6 = 1064(0x428, float:1.491E-42)
            java.lang.String r7 = "&SHcy;"
            r0.addReference(r6, r7)
            r6 = 1065(0x429, float:1.492E-42)
            java.lang.String r7 = "&SHCHcy;"
            r0.addReference(r6, r7)
            r6 = 1066(0x42a, float:1.494E-42)
            java.lang.String r7 = "&HARDcy;"
            r0.addReference(r6, r7)
            r6 = 1067(0x42b, float:1.495E-42)
            java.lang.String r7 = "&Ycy;"
            r0.addReference(r6, r7)
            r6 = 1068(0x42c, float:1.497E-42)
            java.lang.String r7 = "&SOFTcy;"
            r0.addReference(r6, r7)
            r6 = 1069(0x42d, float:1.498E-42)
            java.lang.String r7 = "&Ecy;"
            r0.addReference(r6, r7)
            r6 = 1070(0x42e, float:1.5E-42)
            java.lang.String r7 = "&YUcy;"
            r0.addReference(r6, r7)
            r6 = 1071(0x42f, float:1.501E-42)
            java.lang.String r7 = "&YAcy;"
            r0.addReference(r6, r7)
            r6 = 1072(0x430, float:1.502E-42)
            java.lang.String r7 = "&acy;"
            r0.addReference(r6, r7)
            r6 = 1073(0x431, float:1.504E-42)
            java.lang.String r7 = "&bcy;"
            r0.addReference(r6, r7)
            r6 = 1074(0x432, float:1.505E-42)
            java.lang.String r7 = "&vcy;"
            r0.addReference(r6, r7)
            r6 = 1075(0x433, float:1.506E-42)
            java.lang.String r7 = "&gcy;"
            r0.addReference(r6, r7)
            r6 = 1076(0x434, float:1.508E-42)
            java.lang.String r7 = "&dcy;"
            r0.addReference(r6, r7)
            r6 = 1077(0x435, float:1.509E-42)
            java.lang.String r7 = "&iecy;"
            r0.addReference(r6, r7)
            r6 = 1078(0x436, float:1.51E-42)
            java.lang.String r7 = "&zhcy;"
            r0.addReference(r6, r7)
            r6 = 1079(0x437, float:1.512E-42)
            java.lang.String r7 = "&zcy;"
            r0.addReference(r6, r7)
            r6 = 1080(0x438, float:1.513E-42)
            java.lang.String r7 = "&icy;"
            r0.addReference(r6, r7)
            r6 = 1081(0x439, float:1.515E-42)
            java.lang.String r7 = "&jcy;"
            r0.addReference(r6, r7)
            r6 = 1082(0x43a, float:1.516E-42)
            java.lang.String r7 = "&kcy;"
            r0.addReference(r6, r7)
            r6 = 1083(0x43b, float:1.518E-42)
            java.lang.String r7 = "&lcy;"
            r0.addReference(r6, r7)
            r6 = 1084(0x43c, float:1.519E-42)
            java.lang.String r7 = "&mcy;"
            r0.addReference(r6, r7)
            r6 = 1085(0x43d, float:1.52E-42)
            java.lang.String r7 = "&ncy;"
            r0.addReference(r6, r7)
            r6 = 1086(0x43e, float:1.522E-42)
            java.lang.String r7 = "&ocy;"
            r0.addReference(r6, r7)
            r6 = 1087(0x43f, float:1.523E-42)
            java.lang.String r7 = "&pcy;"
            r0.addReference(r6, r7)
            r6 = 1088(0x440, float:1.525E-42)
            java.lang.String r7 = "&rcy;"
            r0.addReference(r6, r7)
            r6 = 1089(0x441, float:1.526E-42)
            java.lang.String r7 = "&scy;"
            r0.addReference(r6, r7)
            r6 = 1090(0x442, float:1.527E-42)
            java.lang.String r7 = "&tcy;"
            r0.addReference(r6, r7)
            r6 = 1091(0x443, float:1.529E-42)
            java.lang.String r7 = "&ucy;"
            r0.addReference(r6, r7)
            r6 = 1092(0x444, float:1.53E-42)
            java.lang.String r7 = "&fcy;"
            r0.addReference(r6, r7)
            r6 = 1093(0x445, float:1.532E-42)
            java.lang.String r7 = "&khcy;"
            r0.addReference(r6, r7)
            r6 = 1094(0x446, float:1.533E-42)
            java.lang.String r7 = "&tscy;"
            r0.addReference(r6, r7)
            r6 = 1095(0x447, float:1.534E-42)
            java.lang.String r7 = "&chcy;"
            r0.addReference(r6, r7)
            r6 = 1096(0x448, float:1.536E-42)
            java.lang.String r7 = "&shcy;"
            r0.addReference(r6, r7)
            r6 = 1097(0x449, float:1.537E-42)
            java.lang.String r7 = "&shchcy;"
            r0.addReference(r6, r7)
            r6 = 1098(0x44a, float:1.539E-42)
            java.lang.String r7 = "&hardcy;"
            r0.addReference(r6, r7)
            r6 = 1099(0x44b, float:1.54E-42)
            java.lang.String r7 = "&ycy;"
            r0.addReference(r6, r7)
            r6 = 1100(0x44c, float:1.541E-42)
            java.lang.String r7 = "&softcy;"
            r0.addReference(r6, r7)
            r6 = 1101(0x44d, float:1.543E-42)
            java.lang.String r7 = "&ecy;"
            r0.addReference(r6, r7)
            r6 = 1102(0x44e, float:1.544E-42)
            java.lang.String r7 = "&yucy;"
            r0.addReference(r6, r7)
            r6 = 1103(0x44f, float:1.546E-42)
            java.lang.String r7 = "&yacy;"
            r0.addReference(r6, r7)
            r6 = 1105(0x451, float:1.548E-42)
            java.lang.String r7 = "&iocy;"
            r0.addReference(r6, r7)
            r6 = 1106(0x452, float:1.55E-42)
            java.lang.String r7 = "&djcy;"
            r0.addReference(r6, r7)
            r6 = 1107(0x453, float:1.551E-42)
            java.lang.String r7 = "&gjcy;"
            r0.addReference(r6, r7)
            r6 = 1108(0x454, float:1.553E-42)
            java.lang.String r7 = "&jukcy;"
            r0.addReference(r6, r7)
            r6 = 1109(0x455, float:1.554E-42)
            java.lang.String r7 = "&dscy;"
            r0.addReference(r6, r7)
            r6 = 1110(0x456, float:1.555E-42)
            java.lang.String r7 = "&iukcy;"
            r0.addReference(r6, r7)
            r6 = 1111(0x457, float:1.557E-42)
            java.lang.String r7 = "&yicy;"
            r0.addReference(r6, r7)
            r6 = 1112(0x458, float:1.558E-42)
            java.lang.String r7 = "&jsercy;"
            r0.addReference(r6, r7)
            r6 = 1113(0x459, float:1.56E-42)
            java.lang.String r7 = "&ljcy;"
            r0.addReference(r6, r7)
            r6 = 1114(0x45a, float:1.561E-42)
            java.lang.String r7 = "&njcy;"
            r0.addReference(r6, r7)
            r6 = 1115(0x45b, float:1.562E-42)
            java.lang.String r7 = "&tshcy;"
            r0.addReference(r6, r7)
            r6 = 1116(0x45c, float:1.564E-42)
            java.lang.String r7 = "&kjcy;"
            r0.addReference(r6, r7)
            r6 = 1118(0x45e, float:1.567E-42)
            java.lang.String r7 = "&ubrcy;"
            r0.addReference(r6, r7)
            r6 = 1119(0x45f, float:1.568E-42)
            java.lang.String r7 = "&dzcy;"
            r0.addReference(r6, r7)
            r6 = 8194(0x2002, float:1.1482E-41)
            java.lang.String r7 = "&ensp;"
            r0.addReference(r6, r7)
            r6 = 8195(0x2003, float:1.1484E-41)
            java.lang.String r7 = "&emsp;"
            r0.addReference(r6, r7)
            r6 = 8196(0x2004, float:1.1485E-41)
            java.lang.String r7 = "&emsp13;"
            r0.addReference(r6, r7)
            r6 = 8197(0x2005, float:1.1486E-41)
            java.lang.String r7 = "&emsp14;"
            r0.addReference(r6, r7)
            r6 = 8199(0x2007, float:1.1489E-41)
            java.lang.String r7 = "&numsp;"
            r0.addReference(r6, r7)
            r6 = 8200(0x2008, float:1.149E-41)
            java.lang.String r7 = "&puncsp;"
            r0.addReference(r6, r7)
            r6 = 8201(0x2009, float:1.1492E-41)
            java.lang.String r7 = "&thinsp;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ThinSpace;"
            r0.addReference(r6, r7)
            r6 = 8202(0x200a, float:1.1493E-41)
            java.lang.String r7 = "&hairsp;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&VeryThinSpace;"
            r0.addReference(r6, r7)
            r6 = 8203(0x200b, float:1.1495E-41)
            java.lang.String r7 = "&NegativeMediumSpace;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&NegativeThickSpace;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&NegativeThinSpace;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&NegativeVeryThinSpace;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ZeroWidthSpace;"
            r0.addReference(r6, r7)
            r6 = 8204(0x200c, float:1.1496E-41)
            java.lang.String r7 = "&zwnj;"
            r0.addReference(r6, r7)
            r6 = 8205(0x200d, float:1.1498E-41)
            java.lang.String r7 = "&zwj;"
            r0.addReference(r6, r7)
            r6 = 8206(0x200e, float:1.1499E-41)
            java.lang.String r7 = "&lrm;"
            r0.addReference(r6, r7)
            r6 = 8207(0x200f, float:1.15E-41)
            java.lang.String r7 = "&rlm;"
            r0.addReference(r6, r7)
            r6 = 8208(0x2010, float:1.1502E-41)
            java.lang.String r7 = "&dash;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&hyphen;"
            r0.addReference(r6, r7)
            r6 = 8211(0x2013, float:1.1506E-41)
            java.lang.String r7 = "&ndash;"
            r0.addReference(r6, r7)
            r6 = 8212(0x2014, float:1.1507E-41)
            java.lang.String r7 = "&mdash;"
            r0.addReference(r6, r7)
            r6 = 8213(0x2015, float:1.1509E-41)
            java.lang.String r7 = "&horbar;"
            r0.addReference(r6, r7)
            r6 = 8214(0x2016, float:1.151E-41)
            java.lang.String r7 = "&Verbar;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Vert;"
            r0.addReference(r6, r7)
            r6 = 8216(0x2018, float:1.1513E-41)
            java.lang.String r7 = "&lsquo;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&OpenCurlyQuote;"
            r0.addReference(r6, r7)
            r6 = 8217(0x2019, float:1.1514E-41)
            java.lang.String r7 = "&rsquo;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&rsquor;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&CloseCurlyQuote;"
            r0.addReference(r6, r7)
            r6 = 8218(0x201a, float:1.1516E-41)
            java.lang.String r7 = "&sbquo;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&lsquor;"
            r0.addReference(r6, r7)
            r6 = 8220(0x201c, float:1.1519E-41)
            java.lang.String r7 = "&ldquo;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&OpenCurlyDoubleQuote;"
            r0.addReference(r6, r7)
            r6 = 8221(0x201d, float:1.152E-41)
            java.lang.String r7 = "&rdquo;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&rdquor;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&CloseCurlyDoubleQuote;"
            r0.addReference(r6, r7)
            r6 = 8222(0x201e, float:1.1521E-41)
            java.lang.String r7 = "&bdquo;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ldquor;"
            r0.addReference(r6, r7)
            r6 = 8224(0x2020, float:1.1524E-41)
            java.lang.String r7 = "&dagger;"
            r0.addReference(r6, r7)
            r6 = 8225(0x2021, float:1.1526E-41)
            java.lang.String r7 = "&Dagger;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ddagger;"
            r0.addReference(r6, r7)
            r6 = 8226(0x2022, float:1.1527E-41)
            java.lang.String r7 = "&bull;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&bullet;"
            r0.addReference(r6, r7)
            r6 = 8229(0x2025, float:1.1531E-41)
            java.lang.String r7 = "&nldr;"
            r0.addReference(r6, r7)
            r6 = 8230(0x2026, float:1.1533E-41)
            java.lang.String r7 = "&hellip;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&mldr;"
            r0.addReference(r6, r7)
            r6 = 8240(0x2030, float:1.1547E-41)
            java.lang.String r7 = "&permil;"
            r0.addReference(r6, r7)
            r6 = 8241(0x2031, float:1.1548E-41)
            java.lang.String r7 = "&pertenk;"
            r0.addReference(r6, r7)
            r6 = 8242(0x2032, float:1.155E-41)
            java.lang.String r7 = "&prime;"
            r0.addReference(r6, r7)
            r6 = 8243(0x2033, float:1.1551E-41)
            java.lang.String r7 = "&Prime;"
            r0.addReference(r6, r7)
            r6 = 8244(0x2034, float:1.1552E-41)
            java.lang.String r7 = "&tprime;"
            r0.addReference(r6, r7)
            r6 = 8245(0x2035, float:1.1554E-41)
            java.lang.String r7 = "&backprime;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&bprime;"
            r0.addReference(r6, r7)
            r6 = 8249(0x2039, float:1.156E-41)
            java.lang.String r7 = "&lsaquo;"
            r0.addReference(r6, r7)
            r6 = 8250(0x203a, float:1.1561E-41)
            java.lang.String r7 = "&rsaquo;"
            r0.addReference(r6, r7)
            r6 = 8254(0x203e, float:1.1566E-41)
            java.lang.String r7 = "&oline;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&OverBar;"
            r0.addReference(r6, r7)
            r6 = 8257(0x2041, float:1.157E-41)
            java.lang.String r7 = "&caret;"
            r0.addReference(r6, r7)
            r6 = 8259(0x2043, float:1.1573E-41)
            java.lang.String r7 = "&hybull;"
            r0.addReference(r6, r7)
            r6 = 8260(0x2044, float:1.1575E-41)
            java.lang.String r7 = "&frasl;"
            r0.addReference(r6, r7)
            r6 = 8271(0x204f, float:1.159E-41)
            java.lang.String r7 = "&bsemi;"
            r0.addReference(r6, r7)
            r6 = 8279(0x2057, float:1.1601E-41)
            java.lang.String r7 = "&qprime;"
            r0.addReference(r6, r7)
            r6 = 8287(0x205f, float:1.1613E-41)
            java.lang.String r7 = "&MediumSpace;"
            r0.addReference(r6, r7)
            r7 = 8202(0x200a, float:1.1493E-41)
            java.lang.String r8 = "&ThickSpace;"
            r0.addReference(r6, r7, r8)
            r6 = 8288(0x2060, float:1.1614E-41)
            java.lang.String r7 = "&NoBreak;"
            r0.addReference(r6, r7)
            r6 = 8289(0x2061, float:1.1615E-41)
            java.lang.String r7 = "&af;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ApplyFunction;"
            r0.addReference(r6, r7)
            r6 = 8290(0x2062, float:1.1617E-41)
            java.lang.String r7 = "&it;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&InvisibleTimes;"
            r0.addReference(r6, r7)
            r6 = 8291(0x2063, float:1.1618E-41)
            java.lang.String r7 = "&ic;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&InvisibleComma;"
            r0.addReference(r6, r7)
            r6 = 8364(0x20ac, float:1.172E-41)
            java.lang.String r7 = "&euro;"
            r0.addReference(r6, r7)
            r6 = 8411(0x20db, float:1.1786E-41)
            java.lang.String r7 = "&tdot;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&TripleDot;"
            r0.addReference(r6, r7)
            r6 = 8412(0x20dc, float:1.1788E-41)
            java.lang.String r7 = "&DotDot;"
            r0.addReference(r6, r7)
            r6 = 8450(0x2102, float:1.1841E-41)
            java.lang.String r7 = "&complexes;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Copf;"
            r0.addReference(r6, r7)
            r6 = 8453(0x2105, float:1.1845E-41)
            java.lang.String r7 = "&incare;"
            r0.addReference(r6, r7)
            r6 = 8458(0x210a, float:1.1852E-41)
            java.lang.String r7 = "&gscr;"
            r0.addReference(r6, r7)
            r6 = 8459(0x210b, float:1.1854E-41)
            java.lang.String r7 = "&hamilt;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&HilbertSpace;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Hscr;"
            r0.addReference(r6, r7)
            r6 = 8460(0x210c, float:1.1855E-41)
            java.lang.String r7 = "&Hfr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Poincareplane;"
            r0.addReference(r6, r7)
            r6 = 8461(0x210d, float:1.1856E-41)
            java.lang.String r7 = "&quaternions;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Hopf;"
            r0.addReference(r6, r7)
            r6 = 8462(0x210e, float:1.1858E-41)
            java.lang.String r7 = "&planckh;"
            r0.addReference(r6, r7)
            r6 = 8463(0x210f, float:1.1859E-41)
            java.lang.String r7 = "&hbar;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&hslash;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&planck;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&plankv;"
            r0.addReference(r6, r7)
            r6 = 8464(0x2110, float:1.186E-41)
            java.lang.String r7 = "&imagline;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Iscr;"
            r0.addReference(r6, r7)
            r6 = 8465(0x2111, float:1.1862E-41)
            java.lang.String r7 = "&image;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&imagpart;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Ifr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Im;"
            r0.addReference(r6, r7)
            r6 = 8466(0x2112, float:1.1863E-41)
            java.lang.String r7 = "&lagran;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Laplacetrf;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Lscr;"
            r0.addReference(r6, r7)
            r6 = 8467(0x2113, float:1.1865E-41)
            java.lang.String r7 = "&ell;"
            r0.addReference(r6, r7)
            r6 = 8469(0x2115, float:1.1868E-41)
            java.lang.String r7 = "&naturals;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Nopf;"
            r0.addReference(r6, r7)
            r6 = 8470(0x2116, float:1.1869E-41)
            java.lang.String r7 = "&numero;"
            r0.addReference(r6, r7)
            r6 = 8471(0x2117, float:1.187E-41)
            java.lang.String r7 = "&copysr;"
            r0.addReference(r6, r7)
            r6 = 8472(0x2118, float:1.1872E-41)
            java.lang.String r7 = "&weierp;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&wp;"
            r0.addReference(r6, r7)
            r6 = 8473(0x2119, float:1.1873E-41)
            java.lang.String r7 = "&primes;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Popf;"
            r0.addReference(r6, r7)
            r6 = 8474(0x211a, float:1.1875E-41)
            java.lang.String r7 = "&rationals;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Qopf;"
            r0.addReference(r6, r7)
            r6 = 8475(0x211b, float:1.1876E-41)
            java.lang.String r7 = "&realine;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Rscr;"
            r0.addReference(r6, r7)
            r6 = 8476(0x211c, float:1.1877E-41)
            java.lang.String r7 = "&real;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&realpart;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Re;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Rfr;"
            r0.addReference(r6, r7)
            r6 = 8477(0x211d, float:1.1879E-41)
            java.lang.String r7 = "&reals;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Ropf;"
            r0.addReference(r6, r7)
            r6 = 8478(0x211e, float:1.188E-41)
            java.lang.String r7 = "&rx;"
            r0.addReference(r6, r7)
            r6 = 8482(0x2122, float:1.1886E-41)
            java.lang.String r7 = "&trade;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&TRADE;"
            r0.addReference(r6, r7)
            r6 = 8484(0x2124, float:1.1889E-41)
            java.lang.String r7 = "&integers;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Zopf;"
            r0.addReference(r6, r7)
            r6 = 8487(0x2127, float:1.1893E-41)
            java.lang.String r7 = "&mho;"
            r0.addReference(r6, r7)
            r6 = 8488(0x2128, float:1.1894E-41)
            java.lang.String r7 = "&zeetrf;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Zfr;"
            r0.addReference(r6, r7)
            r6 = 8489(0x2129, float:1.1896E-41)
            java.lang.String r7 = "&iiota;"
            r0.addReference(r6, r7)
            r6 = 8492(0x212c, float:1.19E-41)
            java.lang.String r7 = "&bernou;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Bernoullis;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Bscr;"
            r0.addReference(r6, r7)
            r6 = 8493(0x212d, float:1.1901E-41)
            java.lang.String r7 = "&Cayleys;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Cfr;"
            r0.addReference(r6, r7)
            r6 = 8495(0x212f, float:1.1904E-41)
            java.lang.String r7 = "&escr;"
            r0.addReference(r6, r7)
            r6 = 8496(0x2130, float:1.1905E-41)
            java.lang.String r7 = "&expectation;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Escr;"
            r0.addReference(r6, r7)
            r6 = 8497(0x2131, float:1.1907E-41)
            java.lang.String r7 = "&Fouriertrf;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Fscr;"
            r0.addReference(r6, r7)
            r6 = 8499(0x2133, float:1.191E-41)
            java.lang.String r7 = "&phmmat;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Mellintrf;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&Mscr;"
            r0.addReference(r6, r7)
            r6 = 8500(0x2134, float:1.1911E-41)
            java.lang.String r7 = "&order;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&orderof;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&oscr;"
            r0.addReference(r6, r7)
            r6 = 8501(0x2135, float:1.1912E-41)
            java.lang.String r7 = "&alefsym;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&aleph;"
            r0.addReference(r6, r7)
            r6 = 8502(0x2136, float:1.1914E-41)
            java.lang.String r7 = "&beth;"
            r0.addReference(r6, r7)
            r6 = 8503(0x2137, float:1.1915E-41)
            java.lang.String r7 = "&gimel;"
            r0.addReference(r6, r7)
            r6 = 8504(0x2138, float:1.1917E-41)
            java.lang.String r7 = "&daleth;"
            r0.addReference(r6, r7)
            r6 = 8517(0x2145, float:1.1935E-41)
            java.lang.String r7 = "&CapitalDifferentialD;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&DD;"
            r0.addReference(r6, r7)
            r6 = 8518(0x2146, float:1.1936E-41)
            java.lang.String r7 = "&dd;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&DifferentialD;"
            r0.addReference(r6, r7)
            r6 = 8519(0x2147, float:1.1938E-41)
            java.lang.String r7 = "&ee;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&exponentiale;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ExponentialE;"
            r0.addReference(r6, r7)
            r6 = 8520(0x2148, float:1.1939E-41)
            java.lang.String r7 = "&ii;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ImaginaryI;"
            r0.addReference(r6, r7)
            r6 = 8531(0x2153, float:1.1954E-41)
            java.lang.String r7 = "&frac13;"
            r0.addReference(r6, r7)
            r6 = 8532(0x2154, float:1.1956E-41)
            java.lang.String r7 = "&frac23;"
            r0.addReference(r6, r7)
            r6 = 8533(0x2155, float:1.1957E-41)
            java.lang.String r7 = "&frac15;"
            r0.addReference(r6, r7)
            r6 = 8534(0x2156, float:1.1959E-41)
            java.lang.String r7 = "&frac25;"
            r0.addReference(r6, r7)
            r6 = 8535(0x2157, float:1.196E-41)
            java.lang.String r7 = "&frac35;"
            r0.addReference(r6, r7)
            r6 = 8536(0x2158, float:1.1961E-41)
            java.lang.String r7 = "&frac45;"
            r0.addReference(r6, r7)
            r6 = 8537(0x2159, float:1.1963E-41)
            java.lang.String r7 = "&frac16;"
            r0.addReference(r6, r7)
            r6 = 8538(0x215a, float:1.1964E-41)
            java.lang.String r7 = "&frac56;"
            r0.addReference(r6, r7)
            r6 = 8539(0x215b, float:1.1966E-41)
            java.lang.String r7 = "&frac18;"
            r0.addReference(r6, r7)
            r6 = 8540(0x215c, float:1.1967E-41)
            java.lang.String r7 = "&frac38;"
            r0.addReference(r6, r7)
            r6 = 8541(0x215d, float:1.1968E-41)
            java.lang.String r7 = "&frac58;"
            r0.addReference(r6, r7)
            r6 = 8542(0x215e, float:1.197E-41)
            java.lang.String r7 = "&frac78;"
            r0.addReference(r6, r7)
            r6 = 8592(0x2190, float:1.204E-41)
            java.lang.String r7 = "&larr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&leftarrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&slarr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&LeftArrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ShortLeftArrow;"
            r0.addReference(r6, r7)
            r6 = 8593(0x2191, float:1.2041E-41)
            java.lang.String r7 = "&uarr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&uparrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ShortUpArrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&UpArrow;"
            r0.addReference(r6, r7)
            r6 = 8594(0x2192, float:1.2043E-41)
            java.lang.String r7 = "&rarr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&rightarrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&srarr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&RightArrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ShortRightArrow;"
            r0.addReference(r6, r7)
            r6 = 8595(0x2193, float:1.2044E-41)
            java.lang.String r7 = "&darr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&downarrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&DownArrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&ShortDownArrow;"
            r0.addReference(r6, r7)
            r6 = 8596(0x2194, float:1.2046E-41)
            java.lang.String r7 = "&harr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&leftrightarrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&LeftRightArrow;"
            r0.addReference(r6, r7)
            r6 = 8597(0x2195, float:1.2047E-41)
            java.lang.String r7 = "&updownarrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&varr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&UpDownArrow;"
            r0.addReference(r6, r7)
            r6 = 8598(0x2196, float:1.2048E-41)
            java.lang.String r7 = "&nwarr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&nwarrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&UpperLeftArrow;"
            r0.addReference(r6, r7)
            r6 = 8599(0x2197, float:1.205E-41)
            java.lang.String r7 = "&nearr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&nearrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&UpperRightArrow;"
            r0.addReference(r6, r7)
            r6 = 8600(0x2198, float:1.2051E-41)
            java.lang.String r7 = "&searr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&searrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&LowerRightArrow;"
            r0.addReference(r6, r7)
            r6 = 8601(0x2199, float:1.2053E-41)
            java.lang.String r7 = "&swarr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&swarrow;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&LowerLeftArrow;"
            r0.addReference(r6, r7)
            r6 = 8602(0x219a, float:1.2054E-41)
            java.lang.String r7 = "&nlarr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&nleftarrow;"
            r0.addReference(r6, r7)
            r6 = 8603(0x219b, float:1.2055E-41)
            java.lang.String r7 = "&nrarr;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&nrightarrow;"
            r0.addReference(r6, r7)
            r6 = 8605(0x219d, float:1.2058E-41)
            java.lang.String r7 = "&rarrw;"
            r0.addReference(r6, r7)
            java.lang.String r7 = "&rightsquigarrow;"
            r0.addReference(r6, r7)
            r7 = 824(0x338, float:1.155E-42)
            java.lang.String r8 = "&nrarrw;"
            r0.addReference(r6, r7, r8)
            r6 = 8606(0x219e, float:1.206E-41)
            java.lang.String r8 = "&twoheadleftarrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Larr;"
            r0.addReference(r6, r8)
            r6 = 8607(0x219f, float:1.2061E-41)
            java.lang.String r8 = "&Uarr;"
            r0.addReference(r6, r8)
            r6 = 8608(0x21a0, float:1.2062E-41)
            java.lang.String r8 = "&twoheadrightarrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Rarr;"
            r0.addReference(r6, r8)
            r6 = 8609(0x21a1, float:1.2064E-41)
            java.lang.String r8 = "&Darr;"
            r0.addReference(r6, r8)
            r6 = 8610(0x21a2, float:1.2065E-41)
            java.lang.String r8 = "&larrtl;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&leftarrowtail;"
            r0.addReference(r6, r8)
            r6 = 8611(0x21a3, float:1.2067E-41)
            java.lang.String r8 = "&rarrtl;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&rightarrowtail;"
            r0.addReference(r6, r8)
            r6 = 8612(0x21a4, float:1.2068E-41)
            java.lang.String r8 = "&mapstoleft;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&LeftTeeArrow;"
            r0.addReference(r6, r8)
            r6 = 8613(0x21a5, float:1.207E-41)
            java.lang.String r8 = "&mapstoup;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&UpTeeArrow;"
            r0.addReference(r6, r8)
            r6 = 8614(0x21a6, float:1.2071E-41)
            java.lang.String r8 = "&map;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&mapsto;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&RightTeeArrow;"
            r0.addReference(r6, r8)
            r6 = 8615(0x21a7, float:1.2072E-41)
            java.lang.String r8 = "&mapstodown;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&DownTeeArrow;"
            r0.addReference(r6, r8)
            r6 = 8617(0x21a9, float:1.2075E-41)
            java.lang.String r8 = "&hookleftarrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&larrhk;"
            r0.addReference(r6, r8)
            r6 = 8618(0x21aa, float:1.2076E-41)
            java.lang.String r8 = "&hookrightarrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&rarrhk;"
            r0.addReference(r6, r8)
            r6 = 8619(0x21ab, float:1.2078E-41)
            java.lang.String r8 = "&larrlp;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&looparrowleft;"
            r0.addReference(r6, r8)
            r6 = 8620(0x21ac, float:1.2079E-41)
            java.lang.String r8 = "&looparrowright;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&rarrlp;"
            r0.addReference(r6, r8)
            r6 = 8621(0x21ad, float:1.208E-41)
            java.lang.String r8 = "&harrw;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&leftrightsquigarrow;"
            r0.addReference(r6, r8)
            r6 = 8622(0x21ae, float:1.2082E-41)
            java.lang.String r8 = "&nharr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&nleftrightarrow;"
            r0.addReference(r6, r8)
            r6 = 8624(0x21b0, float:1.2085E-41)
            java.lang.String r8 = "&lsh;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Lsh;"
            r0.addReference(r6, r8)
            r6 = 8625(0x21b1, float:1.2086E-41)
            java.lang.String r8 = "&rsh;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Rsh;"
            r0.addReference(r6, r8)
            r6 = 8626(0x21b2, float:1.2088E-41)
            java.lang.String r8 = "&ldsh;"
            r0.addReference(r6, r8)
            r6 = 8627(0x21b3, float:1.2089E-41)
            java.lang.String r8 = "&rdsh;"
            r0.addReference(r6, r8)
            r6 = 8629(0x21b5, float:1.2092E-41)
            java.lang.String r8 = "&crarr;"
            r0.addReference(r6, r8)
            r6 = 8630(0x21b6, float:1.2093E-41)
            java.lang.String r8 = "&cularr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&curvearrowleft;"
            r0.addReference(r6, r8)
            r6 = 8631(0x21b7, float:1.2095E-41)
            java.lang.String r8 = "&curarr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&curvearrowright;"
            r0.addReference(r6, r8)
            r6 = 8634(0x21ba, float:1.2099E-41)
            java.lang.String r8 = "&circlearrowleft;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&olarr;"
            r0.addReference(r6, r8)
            r6 = 8635(0x21bb, float:1.21E-41)
            java.lang.String r8 = "&circlearrowright;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&orarr;"
            r0.addReference(r6, r8)
            r6 = 8636(0x21bc, float:1.2102E-41)
            java.lang.String r8 = "&leftharpoonup;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&lharu;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&LeftVector;"
            r0.addReference(r6, r8)
            r6 = 8637(0x21bd, float:1.2103E-41)
            java.lang.String r8 = "&leftharpoondown;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&lhard;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&DownLeftVector;"
            r0.addReference(r6, r8)
            r6 = 8638(0x21be, float:1.2104E-41)
            java.lang.String r8 = "&uharr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&upharpoonright;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&RightUpVector;"
            r0.addReference(r6, r8)
            r6 = 8639(0x21bf, float:1.2106E-41)
            java.lang.String r8 = "&uharl;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&upharpoonleft;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&LeftUpVector;"
            r0.addReference(r6, r8)
            r6 = 8640(0x21c0, float:1.2107E-41)
            java.lang.String r8 = "&rharu;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&rightharpoonup;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&RightVector;"
            r0.addReference(r6, r8)
            r6 = 8641(0x21c1, float:1.2109E-41)
            java.lang.String r8 = "&rhard;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&rightharpoondown;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&DownRightVector;"
            r0.addReference(r6, r8)
            r6 = 8642(0x21c2, float:1.211E-41)
            java.lang.String r8 = "&dharr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&downharpoonright;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&RightDownVector;"
            r0.addReference(r6, r8)
            r6 = 8643(0x21c3, float:1.2111E-41)
            java.lang.String r8 = "&dharl;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&downharpoonleft;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&LeftDownVector;"
            r0.addReference(r6, r8)
            r6 = 8644(0x21c4, float:1.2113E-41)
            java.lang.String r8 = "&rightleftarrows;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&rlarr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&RightArrowLeftArrow;"
            r0.addReference(r6, r8)
            r6 = 8645(0x21c5, float:1.2114E-41)
            java.lang.String r8 = "&udarr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&UpArrowDownArrow;"
            r0.addReference(r6, r8)
            r6 = 8646(0x21c6, float:1.2116E-41)
            java.lang.String r8 = "&leftrightarrows;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&lrarr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&LeftArrowRightArrow;"
            r0.addReference(r6, r8)
            r6 = 8647(0x21c7, float:1.2117E-41)
            java.lang.String r8 = "&leftleftarrows;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&llarr;"
            r0.addReference(r6, r8)
            r6 = 8648(0x21c8, float:1.2118E-41)
            java.lang.String r8 = "&upuparrows;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&uuarr;"
            r0.addReference(r6, r8)
            r6 = 8649(0x21c9, float:1.212E-41)
            java.lang.String r8 = "&rightrightarrows;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&rrarr;"
            r0.addReference(r6, r8)
            r6 = 8650(0x21ca, float:1.2121E-41)
            java.lang.String r8 = "&ddarr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&downdownarrows;"
            r0.addReference(r6, r8)
            r6 = 8651(0x21cb, float:1.2123E-41)
            java.lang.String r8 = "&leftrightharpoons;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&lrhar;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&ReverseEquilibrium;"
            r0.addReference(r6, r8)
            r6 = 8652(0x21cc, float:1.2124E-41)
            java.lang.String r8 = "&rightleftharpoons;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&rlhar;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Equilibrium;"
            r0.addReference(r6, r8)
            r6 = 8653(0x21cd, float:1.2125E-41)
            java.lang.String r8 = "&nLeftarrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&nlArr;"
            r0.addReference(r6, r8)
            r6 = 8654(0x21ce, float:1.2127E-41)
            java.lang.String r8 = "&nLeftrightarrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&nhArr;"
            r0.addReference(r6, r8)
            r6 = 8655(0x21cf, float:1.2128E-41)
            java.lang.String r8 = "&nRightarrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&nrArr;"
            r0.addReference(r6, r8)
            r6 = 8656(0x21d0, float:1.213E-41)
            java.lang.String r8 = "&lArr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&DoubleLeftArrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Leftarrow;"
            r0.addReference(r6, r8)
            r6 = 8657(0x21d1, float:1.2131E-41)
            java.lang.String r8 = "&uArr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&DoubleUpArrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Uparrow;"
            r0.addReference(r6, r8)
            r6 = 8658(0x21d2, float:1.2132E-41)
            java.lang.String r8 = "&rArr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&DoubleRightArrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Implies;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Rightarrow;"
            r0.addReference(r6, r8)
            r6 = 8659(0x21d3, float:1.2134E-41)
            java.lang.String r8 = "&dArr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&DoubleDownArrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Downarrow;"
            r0.addReference(r6, r8)
            r6 = 8660(0x21d4, float:1.2135E-41)
            java.lang.String r8 = "&hArr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&iff;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&DoubleLeftRightArrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Leftrightarrow;"
            r0.addReference(r6, r8)
            r6 = 8661(0x21d5, float:1.2137E-41)
            java.lang.String r8 = "&vArr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&DoubleUpDownArrow;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Updownarrow;"
            r0.addReference(r6, r8)
            r6 = 8662(0x21d6, float:1.2138E-41)
            java.lang.String r8 = "&nwArr;"
            r0.addReference(r6, r8)
            r6 = 8663(0x21d7, float:1.214E-41)
            java.lang.String r8 = "&neArr;"
            r0.addReference(r6, r8)
            r6 = 8664(0x21d8, float:1.2141E-41)
            java.lang.String r8 = "&seArr;"
            r0.addReference(r6, r8)
            r6 = 8665(0x21d9, float:1.2142E-41)
            java.lang.String r8 = "&swArr;"
            r0.addReference(r6, r8)
            r6 = 8666(0x21da, float:1.2144E-41)
            java.lang.String r8 = "&lAarr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Lleftarrow;"
            r0.addReference(r6, r8)
            r6 = 8667(0x21db, float:1.2145E-41)
            java.lang.String r8 = "&rAarr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Rrightarrow;"
            r0.addReference(r6, r8)
            r6 = 8669(0x21dd, float:1.2148E-41)
            java.lang.String r8 = "&zigrarr;"
            r0.addReference(r6, r8)
            r6 = 8676(0x21e4, float:1.2158E-41)
            java.lang.String r8 = "&larrb;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&LeftArrowBar;"
            r0.addReference(r6, r8)
            r6 = 8677(0x21e5, float:1.2159E-41)
            java.lang.String r8 = "&rarrb;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&RightArrowBar;"
            r0.addReference(r6, r8)
            r6 = 8693(0x21f5, float:1.2181E-41)
            java.lang.String r8 = "&duarr;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&DownArrowUpArrow;"
            r0.addReference(r6, r8)
            r6 = 8701(0x21fd, float:1.2193E-41)
            java.lang.String r8 = "&loarr;"
            r0.addReference(r6, r8)
            r6 = 8702(0x21fe, float:1.2194E-41)
            java.lang.String r8 = "&roarr;"
            r0.addReference(r6, r8)
            r6 = 8703(0x21ff, float:1.2196E-41)
            java.lang.String r8 = "&hoarr;"
            r0.addReference(r6, r8)
            r6 = 8704(0x2200, float:1.2197E-41)
            java.lang.String r8 = "&forall;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&ForAll;"
            r0.addReference(r6, r8)
            r6 = 8705(0x2201, float:1.2198E-41)
            java.lang.String r8 = "&comp;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&complement;"
            r0.addReference(r6, r8)
            r6 = 8706(0x2202, float:1.22E-41)
            java.lang.String r8 = "&part;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&PartialD;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&npart;"
            r0.addReference(r6, r7, r8)
            r6 = 8707(0x2203, float:1.2201E-41)
            java.lang.String r8 = "&exist;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Exists;"
            r0.addReference(r6, r8)
            r6 = 8708(0x2204, float:1.2203E-41)
            java.lang.String r8 = "&nexist;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&nexists;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&NotExists;"
            r0.addReference(r6, r8)
            r6 = 8709(0x2205, float:1.2204E-41)
            java.lang.String r8 = "&empty;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&emptyset;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&emptyv;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&varnothing;"
            r0.addReference(r6, r8)
            r6 = 8711(0x2207, float:1.2207E-41)
            java.lang.String r8 = "&nabla;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Del;"
            r0.addReference(r6, r8)
            r6 = 8712(0x2208, float:1.2208E-41)
            java.lang.String r8 = "&isin;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&in;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&isinv;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Element;"
            r0.addReference(r6, r8)
            r6 = 8713(0x2209, float:1.221E-41)
            java.lang.String r8 = "&notin;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&notinva;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&NotElement;"
            r0.addReference(r6, r8)
            r6 = 8715(0x220b, float:1.2212E-41)
            java.lang.String r8 = "&ni;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&niv;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&ReverseElement;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&SuchThat;"
            r0.addReference(r6, r8)
            r6 = 8716(0x220c, float:1.2214E-41)
            java.lang.String r8 = "&notni;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&notniva;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&NotReverseElement;"
            r0.addReference(r6, r8)
            r6 = 8719(0x220f, float:1.2218E-41)
            java.lang.String r8 = "&prod;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Product;"
            r0.addReference(r6, r8)
            r6 = 8720(0x2210, float:1.222E-41)
            java.lang.String r8 = "&coprod;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Coproduct;"
            r0.addReference(r6, r8)
            r6 = 8721(0x2211, float:1.2221E-41)
            java.lang.String r8 = "&sum;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Sum;"
            r0.addReference(r6, r8)
            r6 = 8722(0x2212, float:1.2222E-41)
            java.lang.String r8 = "&minus;"
            r0.addReference(r6, r8)
            r6 = 8723(0x2213, float:1.2224E-41)
            java.lang.String r8 = "&mnplus;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&mp;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&MinusPlus;"
            r0.addReference(r6, r8)
            r6 = 8724(0x2214, float:1.2225E-41)
            java.lang.String r8 = "&dotplus;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&plusdo;"
            r0.addReference(r6, r8)
            r6 = 8726(0x2216, float:1.2228E-41)
            java.lang.String r8 = "&setminus;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&setmn;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&smallsetminus;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&ssetmn;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Backslash;"
            r0.addReference(r6, r8)
            r6 = 8727(0x2217, float:1.2229E-41)
            java.lang.String r8 = "&lowast;"
            r0.addReference(r6, r8)
            r6 = 8728(0x2218, float:1.223E-41)
            java.lang.String r8 = "&compfn;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&SmallCircle;"
            r0.addReference(r6, r8)
            r6 = 8730(0x221a, float:1.2233E-41)
            java.lang.String r8 = "&radic;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Sqrt;"
            r0.addReference(r6, r8)
            r6 = 8733(0x221d, float:1.2238E-41)
            java.lang.String r8 = "&prop;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&propto;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&varpropto;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&vprop;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&Proportional;"
            r0.addReference(r6, r8)
            r6 = 8734(0x221e, float:1.2239E-41)
            java.lang.String r8 = "&infin;"
            r0.addReference(r6, r8)
            r6 = 8735(0x221f, float:1.224E-41)
            java.lang.String r8 = "&angrt;"
            r0.addReference(r6, r8)
            r6 = 8736(0x2220, float:1.2242E-41)
            java.lang.String r8 = "&ang;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&angle;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&nang;"
            r0.addReference(r6, r4, r8)
            r6 = 8737(0x2221, float:1.2243E-41)
            java.lang.String r8 = "&angmsd;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&measuredangle;"
            r0.addReference(r6, r8)
            r6 = 8738(0x2222, float:1.2245E-41)
            java.lang.String r8 = "&angsph;"
            r0.addReference(r6, r8)
            r6 = 8739(0x2223, float:1.2246E-41)
            java.lang.String r8 = "&mid;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&shortmid;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&smid;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&VerticalBar;"
            r0.addReference(r6, r8)
            r6 = 8740(0x2224, float:1.2247E-41)
            java.lang.String r8 = "&nmid;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&nshortmid;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&nsmid;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&NotVerticalBar;"
            r0.addReference(r6, r8)
            r6 = 8741(0x2225, float:1.2249E-41)
            java.lang.String r8 = "&par;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&parallel;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&shortparallel;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&spar;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&DoubleVerticalBar;"
            r0.addReference(r6, r8)
            r6 = 8742(0x2226, float:1.225E-41)
            java.lang.String r8 = "&npar;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&nparallel;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&nshortparallel;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&nspar;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&NotDoubleVerticalBar;"
            r0.addReference(r6, r8)
            r6 = 8743(0x2227, float:1.2252E-41)
            java.lang.String r8 = "&and;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&wedge;"
            r0.addReference(r6, r8)
            r6 = 8744(0x2228, float:1.2253E-41)
            java.lang.String r8 = "&or;"
            r0.addReference(r6, r8)
            java.lang.String r8 = "&vee;"
            r0.addReference(r6, r8)
            r6 = 8745(0x2229, float:1.2254E-41)
            java.lang.String r8 = "&cap;"
            r0.addReference(r6, r8)
            r8 = 65024(0xfe00, float:9.1118E-41)
            java.lang.String r9 = "&caps;"
            r0.addReference(r6, r8, r9)
            r6 = 8746(0x222a, float:1.2256E-41)
            java.lang.String r9 = "&cup;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&cups;"
            r0.addReference(r6, r8, r9)
            r6 = 8747(0x222b, float:1.2257E-41)
            java.lang.String r9 = "&int;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Integral;"
            r0.addReference(r6, r9)
            r6 = 8748(0x222c, float:1.2259E-41)
            java.lang.String r9 = "&Int;"
            r0.addReference(r6, r9)
            r6 = 8749(0x222d, float:1.226E-41)
            java.lang.String r9 = "&iiint;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&tint;"
            r0.addReference(r6, r9)
            r6 = 8750(0x222e, float:1.2261E-41)
            java.lang.String r9 = "&conint;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&oint;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&ContourIntegral;"
            r0.addReference(r6, r9)
            r6 = 8751(0x222f, float:1.2263E-41)
            java.lang.String r9 = "&Conint;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&DoubleContourIntegral;"
            r0.addReference(r6, r9)
            r6 = 8752(0x2230, float:1.2264E-41)
            java.lang.String r9 = "&Cconint;"
            r0.addReference(r6, r9)
            r6 = 8753(0x2231, float:1.2266E-41)
            java.lang.String r9 = "&cwint;"
            r0.addReference(r6, r9)
            r6 = 8754(0x2232, float:1.2267E-41)
            java.lang.String r9 = "&cwconint;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&ClockwiseContourIntegral;"
            r0.addReference(r6, r9)
            r6 = 8755(0x2233, float:1.2268E-41)
            java.lang.String r9 = "&awconint;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&CounterClockwiseContourIntegral;"
            r0.addReference(r6, r9)
            r6 = 8756(0x2234, float:1.227E-41)
            java.lang.String r9 = "&there4;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&therefore;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Therefore;"
            r0.addReference(r6, r9)
            r6 = 8757(0x2235, float:1.2271E-41)
            java.lang.String r9 = "&becaus;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&because;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Because;"
            r0.addReference(r6, r9)
            r6 = 8758(0x2236, float:1.2273E-41)
            java.lang.String r9 = "&ratio;"
            r0.addReference(r6, r9)
            r6 = 8759(0x2237, float:1.2274E-41)
            java.lang.String r9 = "&Colon;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Proportion;"
            r0.addReference(r6, r9)
            r6 = 8760(0x2238, float:1.2275E-41)
            java.lang.String r9 = "&dotminus;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&minusd;"
            r0.addReference(r6, r9)
            r6 = 8762(0x223a, float:1.2278E-41)
            java.lang.String r9 = "&mDDot;"
            r0.addReference(r6, r9)
            r6 = 8763(0x223b, float:1.228E-41)
            java.lang.String r9 = "&homtht;"
            r0.addReference(r6, r9)
            r6 = 8764(0x223c, float:1.2281E-41)
            java.lang.String r9 = "&sim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&thicksim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&thksim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Tilde;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nvsim;"
            r0.addReference(r6, r4, r9)
            r6 = 8765(0x223d, float:1.2282E-41)
            java.lang.String r9 = "&backsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&bsim;"
            r0.addReference(r6, r9)
            r9 = 817(0x331, float:1.145E-42)
            java.lang.String r10 = "&race;"
            r0.addReference(r6, r9, r10)
            r6 = 8766(0x223e, float:1.2284E-41)
            java.lang.String r9 = "&ac;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&mstpos;"
            r0.addReference(r6, r9)
            r9 = 819(0x333, float:1.148E-42)
            java.lang.String r10 = "&acE;"
            r0.addReference(r6, r9, r10)
            r6 = 8767(0x223f, float:1.2285E-41)
            java.lang.String r9 = "&acd;"
            r0.addReference(r6, r9)
            r6 = 8768(0x2240, float:1.2287E-41)
            java.lang.String r9 = "&wr;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&wreath;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&VerticalTilde;"
            r0.addReference(r6, r9)
            r6 = 8769(0x2241, float:1.2288E-41)
            java.lang.String r9 = "&nsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotTilde;"
            r0.addReference(r6, r9)
            r6 = 8770(0x2242, float:1.229E-41)
            java.lang.String r9 = "&eqsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&esim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&EqualTilde;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nesim;"
            r0.addReference(r6, r7, r9)
            java.lang.String r9 = "&NotEqualTilde;"
            r0.addReference(r6, r7, r9)
            r6 = 8771(0x2243, float:1.2291E-41)
            java.lang.String r9 = "&sime;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&simeq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&TildeEqual;"
            r0.addReference(r6, r9)
            r6 = 8772(0x2244, float:1.2292E-41)
            java.lang.String r9 = "&nsime;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nsimeq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotTildeEqual;"
            r0.addReference(r6, r9)
            r6 = 8773(0x2245, float:1.2294E-41)
            java.lang.String r9 = "&cong;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&TildeFullEqual;"
            r0.addReference(r6, r9)
            r6 = 8774(0x2246, float:1.2295E-41)
            java.lang.String r9 = "&simne;"
            r0.addReference(r6, r9)
            r6 = 8775(0x2247, float:1.2296E-41)
            java.lang.String r9 = "&ncong;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotTildeFullEqual;"
            r0.addReference(r6, r9)
            r6 = 8776(0x2248, float:1.2298E-41)
            java.lang.String r9 = "&asymp;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&ap;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&approx;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&thickapprox;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&thkap;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&TildeTilde;"
            r0.addReference(r6, r9)
            r6 = 8777(0x2249, float:1.2299E-41)
            java.lang.String r9 = "&nap;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&napprox;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotTildeTilde;"
            r0.addReference(r6, r9)
            r6 = 8778(0x224a, float:1.23E-41)
            java.lang.String r9 = "&ape;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&approxeq;"
            r0.addReference(r6, r9)
            r6 = 8779(0x224b, float:1.2302E-41)
            java.lang.String r9 = "&apid;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&napid;"
            r0.addReference(r6, r7, r9)
            r6 = 8780(0x224c, float:1.2303E-41)
            java.lang.String r9 = "&backcong;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&bcong;"
            r0.addReference(r6, r9)
            r6 = 8781(0x224d, float:1.2305E-41)
            java.lang.String r9 = "&asympeq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&CupCap;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nvap;"
            r0.addReference(r6, r4, r9)
            r6 = 8782(0x224e, float:1.2306E-41)
            java.lang.String r9 = "&bump;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Bumpeq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&HumpDownHump;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nbump;"
            r0.addReference(r6, r7, r9)
            java.lang.String r9 = "&NotHumpDownHump;"
            r0.addReference(r6, r7, r9)
            r6 = 8783(0x224f, float:1.2308E-41)
            java.lang.String r9 = "&bumpe;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&bumpeq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&HumpEqual;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nbumpe;"
            r0.addReference(r6, r7, r9)
            java.lang.String r9 = "&NotHumpEqual;"
            r0.addReference(r6, r7, r9)
            r6 = 8784(0x2250, float:1.2309E-41)
            java.lang.String r9 = "&doteq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&esdot;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&DotEqual;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nedot;"
            r0.addReference(r6, r7, r9)
            r6 = 8785(0x2251, float:1.231E-41)
            java.lang.String r9 = "&doteqdot;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&eDot;"
            r0.addReference(r6, r9)
            r6 = 8786(0x2252, float:1.2312E-41)
            java.lang.String r9 = "&efDot;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&fallingdotseq;"
            r0.addReference(r6, r9)
            r6 = 8787(0x2253, float:1.2313E-41)
            java.lang.String r9 = "&erDot;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&risingdotseq;"
            r0.addReference(r6, r9)
            r6 = 8788(0x2254, float:1.2315E-41)
            java.lang.String r9 = "&colone;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&coloneq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Assign;"
            r0.addReference(r6, r9)
            r6 = 8789(0x2255, float:1.2316E-41)
            java.lang.String r9 = "&ecolon;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&eqcolon;"
            r0.addReference(r6, r9)
            r6 = 8790(0x2256, float:1.2317E-41)
            java.lang.String r9 = "&ecir;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&eqcirc;"
            r0.addReference(r6, r9)
            r6 = 8791(0x2257, float:1.2319E-41)
            java.lang.String r9 = "&circeq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&cire;"
            r0.addReference(r6, r9)
            r6 = 8793(0x2259, float:1.2322E-41)
            java.lang.String r9 = "&wedgeq;"
            r0.addReference(r6, r9)
            r6 = 8794(0x225a, float:1.2323E-41)
            java.lang.String r9 = "&veeeq;"
            r0.addReference(r6, r9)
            r6 = 8796(0x225c, float:1.2326E-41)
            java.lang.String r9 = "&triangleq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&trie;"
            r0.addReference(r6, r9)
            r6 = 8799(0x225f, float:1.233E-41)
            java.lang.String r9 = "&equest;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&questeq;"
            r0.addReference(r6, r9)
            r6 = 8800(0x2260, float:1.2331E-41)
            java.lang.String r9 = "&ne;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotEqual;"
            r0.addReference(r6, r9)
            r6 = 8801(0x2261, float:1.2333E-41)
            java.lang.String r9 = "&equiv;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Congruent;"
            r0.addReference(r6, r9)
            r9 = 8421(0x20e5, float:1.18E-41)
            java.lang.String r10 = "&bnequiv;"
            r0.addReference(r6, r9, r10)
            r6 = 8802(0x2262, float:1.2334E-41)
            java.lang.String r9 = "&nequiv;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotCongruent;"
            r0.addReference(r6, r9)
            r6 = 8804(0x2264, float:1.2337E-41)
            java.lang.String r9 = "&le;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&leq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nvle;"
            r0.addReference(r6, r4, r9)
            r6 = 8805(0x2265, float:1.2338E-41)
            java.lang.String r9 = "&ge;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&geq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&GreaterEqual;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nvge;"
            r0.addReference(r6, r4, r9)
            r6 = 8806(0x2266, float:1.234E-41)
            java.lang.String r9 = "&lE;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&leqq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&LessFullEqual;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nlE;"
            r0.addReference(r6, r7, r9)
            java.lang.String r9 = "&nleqq;"
            r0.addReference(r6, r7, r9)
            r6 = 8807(0x2267, float:1.2341E-41)
            java.lang.String r9 = "&gE;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&geqq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&GreaterFullEqual;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&ngE;"
            r0.addReference(r6, r7, r9)
            java.lang.String r9 = "&ngeqq;"
            r0.addReference(r6, r7, r9)
            java.lang.String r9 = "&NotGreaterFullEqual;"
            r0.addReference(r6, r7, r9)
            r6 = 8808(0x2268, float:1.2343E-41)
            java.lang.String r9 = "&lnE;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&lneqq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&lvertneqq;"
            r0.addReference(r6, r8, r9)
            java.lang.String r9 = "&lvnE;"
            r0.addReference(r6, r8, r9)
            r6 = 8809(0x2269, float:1.2344E-41)
            java.lang.String r9 = "&gnE;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&gneqq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&gvertneqq;"
            r0.addReference(r6, r8, r9)
            java.lang.String r9 = "&gvnE;"
            r0.addReference(r6, r8, r9)
            r6 = 8810(0x226a, float:1.2345E-41)
            java.lang.String r9 = "&ll;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Lt;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NestedLessLess;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nLtv;"
            r0.addReference(r6, r7, r9)
            java.lang.String r9 = "&NotLessLess;"
            r0.addReference(r6, r7, r9)
            java.lang.String r9 = "&nLt;"
            r0.addReference(r6, r4, r9)
            r6 = 8811(0x226b, float:1.2347E-41)
            java.lang.String r9 = "&gg;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Gt;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NestedGreaterGreater;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nGtv;"
            r0.addReference(r6, r7, r9)
            java.lang.String r9 = "&NotGreaterGreater;"
            r0.addReference(r6, r7, r9)
            java.lang.String r9 = "&nGt;"
            r0.addReference(r6, r4, r9)
            r6 = 8812(0x226c, float:1.2348E-41)
            java.lang.String r9 = "&between;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&twixt;"
            r0.addReference(r6, r9)
            r6 = 8813(0x226d, float:1.235E-41)
            java.lang.String r9 = "&NotCupCap;"
            r0.addReference(r6, r9)
            r6 = 8814(0x226e, float:1.2351E-41)
            java.lang.String r9 = "&nless;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nlt;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotLess;"
            r0.addReference(r6, r9)
            r6 = 8815(0x226f, float:1.2352E-41)
            java.lang.String r9 = "&ngt;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&ngtr;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotGreater;"
            r0.addReference(r6, r9)
            r6 = 8816(0x2270, float:1.2354E-41)
            java.lang.String r9 = "&nle;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nleq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotLessEqual;"
            r0.addReference(r6, r9)
            r6 = 8817(0x2271, float:1.2355E-41)
            java.lang.String r9 = "&nge;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&ngeq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotGreaterEqual;"
            r0.addReference(r6, r9)
            r6 = 8818(0x2272, float:1.2357E-41)
            java.lang.String r9 = "&lesssim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&lsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&LessTilde;"
            r0.addReference(r6, r9)
            r6 = 8819(0x2273, float:1.2358E-41)
            java.lang.String r9 = "&gsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&gtrsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&GreaterTilde;"
            r0.addReference(r6, r9)
            r6 = 8820(0x2274, float:1.236E-41)
            java.lang.String r9 = "&nlsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotLessTilde;"
            r0.addReference(r6, r9)
            r6 = 8821(0x2275, float:1.2361E-41)
            java.lang.String r9 = "&ngsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotGreaterTilde;"
            r0.addReference(r6, r9)
            r6 = 8822(0x2276, float:1.2362E-41)
            java.lang.String r9 = "&lessgtr;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&lg;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&LessGreater;"
            r0.addReference(r6, r9)
            r6 = 8823(0x2277, float:1.2364E-41)
            java.lang.String r9 = "&gl;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&gtrless;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&GreaterLess;"
            r0.addReference(r6, r9)
            r6 = 8824(0x2278, float:1.2365E-41)
            java.lang.String r9 = "&ntlg;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotLessGreater;"
            r0.addReference(r6, r9)
            r6 = 8825(0x2279, float:1.2366E-41)
            java.lang.String r9 = "&ntgl;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotGreaterLess;"
            r0.addReference(r6, r9)
            r6 = 8826(0x227a, float:1.2368E-41)
            java.lang.String r9 = "&pr;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&prec;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Precedes;"
            r0.addReference(r6, r9)
            r6 = 8827(0x227b, float:1.2369E-41)
            java.lang.String r9 = "&sc;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&succ;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Succeeds;"
            r0.addReference(r6, r9)
            r6 = 8828(0x227c, float:1.237E-41)
            java.lang.String r9 = "&prcue;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&preccurlyeq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&PrecedesSlantEqual;"
            r0.addReference(r6, r9)
            r6 = 8829(0x227d, float:1.2372E-41)
            java.lang.String r9 = "&sccue;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&succcurlyeq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&SucceedsSlantEqual;"
            r0.addReference(r6, r9)
            r6 = 8830(0x227e, float:1.2373E-41)
            java.lang.String r9 = "&precsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&prsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&PrecedesTilde;"
            r0.addReference(r6, r9)
            r6 = 8831(0x227f, float:1.2375E-41)
            java.lang.String r9 = "&scsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&succsim;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&SucceedsTilde;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotSucceedsTilde;"
            r0.addReference(r6, r7, r9)
            r6 = 8832(0x2280, float:1.2376E-41)
            java.lang.String r9 = "&npr;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nprec;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotPrecedes;"
            r0.addReference(r6, r9)
            r6 = 8833(0x2281, float:1.2378E-41)
            java.lang.String r9 = "&nsc;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nsucc;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotSucceeds;"
            r0.addReference(r6, r9)
            r6 = 8834(0x2282, float:1.2379E-41)
            java.lang.String r9 = "&sub;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&subset;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nsubset;"
            r0.addReference(r6, r4, r9)
            java.lang.String r9 = "&vnsub;"
            r0.addReference(r6, r4, r9)
            java.lang.String r9 = "&NotSubset;"
            r0.addReference(r6, r4, r9)
            r6 = 8835(0x2283, float:1.238E-41)
            java.lang.String r9 = "&sup;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&supset;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&Superset;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nsupset;"
            r0.addReference(r6, r4, r9)
            java.lang.String r9 = "&vnsup;"
            r0.addReference(r6, r4, r9)
            java.lang.String r9 = "&NotSuperset;"
            r0.addReference(r6, r4, r9)
            r6 = 8836(0x2284, float:1.2382E-41)
            java.lang.String r9 = "&nsub;"
            r0.addReference(r6, r9)
            r6 = 8837(0x2285, float:1.2383E-41)
            java.lang.String r9 = "&nsup;"
            r0.addReference(r6, r9)
            r6 = 8838(0x2286, float:1.2385E-41)
            java.lang.String r9 = "&sube;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&subseteq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&SubsetEqual;"
            r0.addReference(r6, r9)
            r6 = 8839(0x2287, float:1.2386E-41)
            java.lang.String r9 = "&supe;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&supseteq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&SupersetEqual;"
            r0.addReference(r6, r9)
            r6 = 8840(0x2288, float:1.2387E-41)
            java.lang.String r9 = "&nsube;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nsubseteq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotSubsetEqual;"
            r0.addReference(r6, r9)
            r6 = 8841(0x2289, float:1.2389E-41)
            java.lang.String r9 = "&nsupe;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nsupseteq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotSupersetEqual;"
            r0.addReference(r6, r9)
            r6 = 8842(0x228a, float:1.239E-41)
            java.lang.String r9 = "&subne;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&subsetneq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&varsubsetneq;"
            r0.addReference(r6, r8, r9)
            java.lang.String r9 = "&vsubne;"
            r0.addReference(r6, r8, r9)
            r6 = 8843(0x228b, float:1.2392E-41)
            java.lang.String r9 = "&supne;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&supsetneq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&varsupsetneq;"
            r0.addReference(r6, r8, r9)
            java.lang.String r9 = "&vsupne;"
            r0.addReference(r6, r8, r9)
            r6 = 8845(0x228d, float:1.2394E-41)
            java.lang.String r9 = "&cupdot;"
            r0.addReference(r6, r9)
            r6 = 8846(0x228e, float:1.2396E-41)
            java.lang.String r9 = "&uplus;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&UnionPlus;"
            r0.addReference(r6, r9)
            r6 = 8847(0x228f, float:1.2397E-41)
            java.lang.String r9 = "&sqsub;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&sqsubset;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&SquareSubset;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotSquareSubset;"
            r0.addReference(r6, r7, r9)
            r6 = 8848(0x2290, float:1.2399E-41)
            java.lang.String r9 = "&sqsup;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&sqsupset;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&SquareSuperset;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&NotSquareSuperset;"
            r0.addReference(r6, r7, r9)
            r6 = 8849(0x2291, float:1.24E-41)
            java.lang.String r9 = "&sqsube;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&sqsubseteq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&SquareSubsetEqual;"
            r0.addReference(r6, r9)
            r6 = 8850(0x2292, float:1.2401E-41)
            java.lang.String r9 = "&sqsupe;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&sqsupseteq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&SquareSupersetEqual;"
            r0.addReference(r6, r9)
            r6 = 8851(0x2293, float:1.2403E-41)
            java.lang.String r9 = "&sqcap;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&SquareIntersection;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&sqcaps;"
            r0.addReference(r6, r8, r9)
            r6 = 8852(0x2294, float:1.2404E-41)
            java.lang.String r9 = "&sqcup;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&SquareUnion;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&sqcups;"
            r0.addReference(r6, r8, r9)
            r6 = 8853(0x2295, float:1.2406E-41)
            java.lang.String r9 = "&oplus;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&CirclePlus;"
            r0.addReference(r6, r9)
            r6 = 8854(0x2296, float:1.2407E-41)
            java.lang.String r9 = "&ominus;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&CircleMinus;"
            r0.addReference(r6, r9)
            r6 = 8855(0x2297, float:1.2408E-41)
            java.lang.String r9 = "&otimes;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&CircleTimes;"
            r0.addReference(r6, r9)
            r6 = 8856(0x2298, float:1.241E-41)
            java.lang.String r9 = "&osol;"
            r0.addReference(r6, r9)
            r6 = 8857(0x2299, float:1.2411E-41)
            java.lang.String r9 = "&odot;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&CircleDot;"
            r0.addReference(r6, r9)
            r6 = 8858(0x229a, float:1.2413E-41)
            java.lang.String r9 = "&circledcirc;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&ocir;"
            r0.addReference(r6, r9)
            r6 = 8859(0x229b, float:1.2414E-41)
            java.lang.String r9 = "&circledast;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&oast;"
            r0.addReference(r6, r9)
            r6 = 8861(0x229d, float:1.2417E-41)
            java.lang.String r9 = "&circleddash;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&odash;"
            r0.addReference(r6, r9)
            r6 = 8862(0x229e, float:1.2418E-41)
            java.lang.String r9 = "&boxplus;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&plusb;"
            r0.addReference(r6, r9)
            r6 = 8863(0x229f, float:1.242E-41)
            java.lang.String r9 = "&boxminus;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&minusb;"
            r0.addReference(r6, r9)
            r6 = 8864(0x22a0, float:1.2421E-41)
            java.lang.String r9 = "&boxtimes;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&timesb;"
            r0.addReference(r6, r9)
            r6 = 8865(0x22a1, float:1.2423E-41)
            java.lang.String r9 = "&dotsquare;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&sdotb;"
            r0.addReference(r6, r9)
            r6 = 8866(0x22a2, float:1.2424E-41)
            java.lang.String r9 = "&vdash;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&RightTee;"
            r0.addReference(r6, r9)
            r6 = 8867(0x22a3, float:1.2425E-41)
            java.lang.String r9 = "&dashv;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&LeftTee;"
            r0.addReference(r6, r9)
            r6 = 8868(0x22a4, float:1.2427E-41)
            java.lang.String r9 = "&top;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&DownTee;"
            r0.addReference(r6, r9)
            r6 = 8869(0x22a5, float:1.2428E-41)
            java.lang.String r9 = "&perp;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&bot;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&bottom;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&UpTee;"
            r0.addReference(r6, r9)
            r6 = 8871(0x22a7, float:1.2431E-41)
            java.lang.String r9 = "&models;"
            r0.addReference(r6, r9)
            r6 = 8872(0x22a8, float:1.2432E-41)
            java.lang.String r9 = "&vDash;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&DoubleRightTee;"
            r0.addReference(r6, r9)
            r6 = 8873(0x22a9, float:1.2434E-41)
            java.lang.String r9 = "&Vdash;"
            r0.addReference(r6, r9)
            r6 = 8874(0x22aa, float:1.2435E-41)
            java.lang.String r9 = "&Vvdash;"
            r0.addReference(r6, r9)
            r6 = 8875(0x22ab, float:1.2437E-41)
            java.lang.String r9 = "&VDash;"
            r0.addReference(r6, r9)
            r6 = 8876(0x22ac, float:1.2438E-41)
            java.lang.String r9 = "&nvdash;"
            r0.addReference(r6, r9)
            r6 = 8877(0x22ad, float:1.244E-41)
            java.lang.String r9 = "&nvDash;"
            r0.addReference(r6, r9)
            r6 = 8878(0x22ae, float:1.2441E-41)
            java.lang.String r9 = "&nVdash;"
            r0.addReference(r6, r9)
            r6 = 8879(0x22af, float:1.2442E-41)
            java.lang.String r9 = "&nVDash;"
            r0.addReference(r6, r9)
            r6 = 8880(0x22b0, float:1.2444E-41)
            java.lang.String r9 = "&prurel;"
            r0.addReference(r6, r9)
            r6 = 8882(0x22b2, float:1.2446E-41)
            java.lang.String r9 = "&vartriangleleft;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&vltri;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&LeftTriangle;"
            r0.addReference(r6, r9)
            r6 = 8883(0x22b3, float:1.2448E-41)
            java.lang.String r9 = "&vartriangleright;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&vrtri;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&RightTriangle;"
            r0.addReference(r6, r9)
            r6 = 8884(0x22b4, float:1.2449E-41)
            java.lang.String r9 = "&ltrie;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&trianglelefteq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&LeftTriangleEqual;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nvltrie;"
            r0.addReference(r6, r4, r9)
            r6 = 8885(0x22b5, float:1.245E-41)
            java.lang.String r9 = "&rtrie;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&trianglerighteq;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&RightTriangleEqual;"
            r0.addReference(r6, r9)
            java.lang.String r9 = "&nvrtrie;"
            r0.addReference(r6, r4, r9)
            r4 = 8886(0x22b6, float:1.2452E-41)
            java.lang.String r6 = "&origof;"
            r0.addReference(r4, r6)
            r4 = 8887(0x22b7, float:1.2453E-41)
            java.lang.String r6 = "&imof;"
            r0.addReference(r4, r6)
            r4 = 8888(0x22b8, float:1.2455E-41)
            java.lang.String r6 = "&multimap;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&mumap;"
            r0.addReference(r4, r6)
            r4 = 8889(0x22b9, float:1.2456E-41)
            java.lang.String r6 = "&hercon;"
            r0.addReference(r4, r6)
            r4 = 8890(0x22ba, float:1.2458E-41)
            java.lang.String r6 = "&intcal;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&intercal;"
            r0.addReference(r4, r6)
            r4 = 8891(0x22bb, float:1.2459E-41)
            java.lang.String r6 = "&veebar;"
            r0.addReference(r4, r6)
            r4 = 8893(0x22bd, float:1.2462E-41)
            java.lang.String r6 = "&barvee;"
            r0.addReference(r4, r6)
            r4 = 8894(0x22be, float:1.2463E-41)
            java.lang.String r6 = "&angrtvb;"
            r0.addReference(r4, r6)
            r4 = 8895(0x22bf, float:1.2465E-41)
            java.lang.String r6 = "&lrtri;"
            r0.addReference(r4, r6)
            r4 = 8896(0x22c0, float:1.2466E-41)
            java.lang.String r6 = "&bigwedge;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xwedge;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Wedge;"
            r0.addReference(r4, r6)
            r4 = 8897(0x22c1, float:1.2467E-41)
            java.lang.String r6 = "&bigvee;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xvee;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Vee;"
            r0.addReference(r4, r6)
            r4 = 8898(0x22c2, float:1.2469E-41)
            java.lang.String r6 = "&bigcap;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xcap;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Intersection;"
            r0.addReference(r4, r6)
            r4 = 8899(0x22c3, float:1.247E-41)
            java.lang.String r6 = "&bigcup;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xcup;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Union;"
            r0.addReference(r4, r6)
            r4 = 8900(0x22c4, float:1.2472E-41)
            java.lang.String r6 = "&diam;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&diamond;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Diamond;"
            r0.addReference(r4, r6)
            r4 = 8901(0x22c5, float:1.2473E-41)
            java.lang.String r6 = "&sdot;"
            r0.addReference(r4, r6)
            r4 = 8902(0x22c6, float:1.2474E-41)
            java.lang.String r6 = "&sstarf;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Star;"
            r0.addReference(r4, r6)
            r4 = 8903(0x22c7, float:1.2476E-41)
            java.lang.String r6 = "&divideontimes;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&divonx;"
            r0.addReference(r4, r6)
            r4 = 8904(0x22c8, float:1.2477E-41)
            java.lang.String r6 = "&bowtie;"
            r0.addReference(r4, r6)
            r4 = 8905(0x22c9, float:1.2479E-41)
            java.lang.String r6 = "&ltimes;"
            r0.addReference(r4, r6)
            r4 = 8906(0x22ca, float:1.248E-41)
            java.lang.String r6 = "&rtimes;"
            r0.addReference(r4, r6)
            r4 = 8907(0x22cb, float:1.2481E-41)
            java.lang.String r6 = "&leftthreetimes;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lthree;"
            r0.addReference(r4, r6)
            r4 = 8908(0x22cc, float:1.2483E-41)
            java.lang.String r6 = "&rightthreetimes;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&rthree;"
            r0.addReference(r4, r6)
            r4 = 8909(0x22cd, float:1.2484E-41)
            java.lang.String r6 = "&backsimeq;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&bsime;"
            r0.addReference(r4, r6)
            r4 = 8910(0x22ce, float:1.2486E-41)
            java.lang.String r6 = "&curlyvee;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&cuvee;"
            r0.addReference(r4, r6)
            r4 = 8911(0x22cf, float:1.2487E-41)
            java.lang.String r6 = "&curlywedge;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&cuwed;"
            r0.addReference(r4, r6)
            r4 = 8912(0x22d0, float:1.2488E-41)
            java.lang.String r6 = "&Sub;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Subset;"
            r0.addReference(r4, r6)
            r4 = 8913(0x22d1, float:1.249E-41)
            java.lang.String r6 = "&Sup;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Supset;"
            r0.addReference(r4, r6)
            r4 = 8914(0x22d2, float:1.2491E-41)
            java.lang.String r6 = "&Cap;"
            r0.addReference(r4, r6)
            r4 = 8915(0x22d3, float:1.2493E-41)
            java.lang.String r6 = "&Cup;"
            r0.addReference(r4, r6)
            r4 = 8916(0x22d4, float:1.2494E-41)
            java.lang.String r6 = "&fork;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&pitchfork;"
            r0.addReference(r4, r6)
            r4 = 8917(0x22d5, float:1.2495E-41)
            java.lang.String r6 = "&epar;"
            r0.addReference(r4, r6)
            r4 = 8918(0x22d6, float:1.2497E-41)
            java.lang.String r6 = "&lessdot;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ltdot;"
            r0.addReference(r4, r6)
            r4 = 8919(0x22d7, float:1.2498E-41)
            java.lang.String r6 = "&gtdot;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&gtrdot;"
            r0.addReference(r4, r6)
            r4 = 8920(0x22d8, float:1.25E-41)
            java.lang.String r6 = "&Ll;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&nLl;"
            r0.addReference(r4, r7, r6)
            r4 = 8921(0x22d9, float:1.2501E-41)
            java.lang.String r6 = "&ggg;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Gg;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&nGg;"
            r0.addReference(r4, r7, r6)
            r4 = 8922(0x22da, float:1.2502E-41)
            java.lang.String r6 = "&leg;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lesseqgtr;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&LessEqualGreater;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lesg;"
            r0.addReference(r4, r8, r6)
            r4 = 8923(0x22db, float:1.2504E-41)
            java.lang.String r6 = "&gel;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&gtreqless;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&GreaterEqualLess;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&gesl;"
            r0.addReference(r4, r8, r6)
            r4 = 8926(0x22de, float:1.2508E-41)
            java.lang.String r6 = "&cuepr;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&curlyeqprec;"
            r0.addReference(r4, r6)
            r4 = 8927(0x22df, float:1.251E-41)
            java.lang.String r6 = "&cuesc;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&curlyeqsucc;"
            r0.addReference(r4, r6)
            r4 = 8928(0x22e0, float:1.2511E-41)
            java.lang.String r6 = "&nprcue;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotPrecedesSlantEqual;"
            r0.addReference(r4, r6)
            r4 = 8929(0x22e1, float:1.2512E-41)
            java.lang.String r6 = "&nsccue;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotSucceedsSlantEqual;"
            r0.addReference(r4, r6)
            r4 = 8930(0x22e2, float:1.2514E-41)
            java.lang.String r6 = "&nsqsube;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotSquareSubsetEqual;"
            r0.addReference(r4, r6)
            r4 = 8931(0x22e3, float:1.2515E-41)
            java.lang.String r6 = "&nsqsupe;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotSquareSupersetEqual;"
            r0.addReference(r4, r6)
            r4 = 8934(0x22e6, float:1.2519E-41)
            java.lang.String r6 = "&lnsim;"
            r0.addReference(r4, r6)
            r4 = 8935(0x22e7, float:1.252E-41)
            java.lang.String r6 = "&gnsim;"
            r0.addReference(r4, r6)
            r4 = 8936(0x22e8, float:1.2522E-41)
            java.lang.String r6 = "&precnsim;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&prnsim;"
            r0.addReference(r4, r6)
            r4 = 8937(0x22e9, float:1.2523E-41)
            java.lang.String r6 = "&scnsim;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&succnsim;"
            r0.addReference(r4, r6)
            r4 = 8938(0x22ea, float:1.2525E-41)
            java.lang.String r6 = "&nltri;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ntriangleleft;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotLeftTriangle;"
            r0.addReference(r4, r6)
            r4 = 8939(0x22eb, float:1.2526E-41)
            java.lang.String r6 = "&nrtri;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ntriangleright;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotRightTriangle;"
            r0.addReference(r4, r6)
            r4 = 8940(0x22ec, float:1.2528E-41)
            java.lang.String r6 = "&nltrie;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ntrianglelefteq;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotLeftTriangleEqual;"
            r0.addReference(r4, r6)
            r4 = 8941(0x22ed, float:1.2529E-41)
            java.lang.String r6 = "&nrtrie;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ntrianglerighteq;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotRightTriangleEqual;"
            r0.addReference(r4, r6)
            r4 = 8942(0x22ee, float:1.253E-41)
            java.lang.String r6 = "&vellip;"
            r0.addReference(r4, r6)
            r4 = 8943(0x22ef, float:1.2532E-41)
            java.lang.String r6 = "&ctdot;"
            r0.addReference(r4, r6)
            r4 = 8944(0x22f0, float:1.2533E-41)
            java.lang.String r6 = "&utdot;"
            r0.addReference(r4, r6)
            r4 = 8945(0x22f1, float:1.2535E-41)
            java.lang.String r6 = "&dtdot;"
            r0.addReference(r4, r6)
            r4 = 8946(0x22f2, float:1.2536E-41)
            java.lang.String r6 = "&disin;"
            r0.addReference(r4, r6)
            r4 = 8947(0x22f3, float:1.2537E-41)
            java.lang.String r6 = "&isinsv;"
            r0.addReference(r4, r6)
            r4 = 8948(0x22f4, float:1.2539E-41)
            java.lang.String r6 = "&isins;"
            r0.addReference(r4, r6)
            r4 = 8949(0x22f5, float:1.254E-41)
            java.lang.String r6 = "&isindot;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&notindot;"
            r0.addReference(r4, r7, r6)
            r4 = 8950(0x22f6, float:1.2542E-41)
            java.lang.String r6 = "&notinvc;"
            r0.addReference(r4, r6)
            r4 = 8951(0x22f7, float:1.2543E-41)
            java.lang.String r6 = "&notinvb;"
            r0.addReference(r4, r6)
            r4 = 8953(0x22f9, float:1.2546E-41)
            java.lang.String r6 = "&isinE;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&notinE;"
            r0.addReference(r4, r7, r6)
            r4 = 8954(0x22fa, float:1.2547E-41)
            java.lang.String r6 = "&nisd;"
            r0.addReference(r4, r6)
            r4 = 8955(0x22fb, float:1.2549E-41)
            java.lang.String r6 = "&xnis;"
            r0.addReference(r4, r6)
            r4 = 8956(0x22fc, float:1.255E-41)
            java.lang.String r6 = "&nis;"
            r0.addReference(r4, r6)
            r4 = 8957(0x22fd, float:1.2551E-41)
            java.lang.String r6 = "&notnivc;"
            r0.addReference(r4, r6)
            r4 = 8958(0x22fe, float:1.2553E-41)
            java.lang.String r6 = "&notnivb;"
            r0.addReference(r4, r6)
            r4 = 8965(0x2305, float:1.2563E-41)
            java.lang.String r6 = "&barwed;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&barwedge;"
            r0.addReference(r4, r6)
            r4 = 8966(0x2306, float:1.2564E-41)
            java.lang.String r6 = "&doublebarwedge;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Barwed;"
            r0.addReference(r4, r6)
            r4 = 8968(0x2308, float:1.2567E-41)
            java.lang.String r6 = "&lceil;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&LeftCeiling;"
            r0.addReference(r4, r6)
            r4 = 8969(0x2309, float:1.2568E-41)
            java.lang.String r6 = "&rceil;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&RightCeiling;"
            r0.addReference(r4, r6)
            r4 = 8970(0x230a, float:1.257E-41)
            java.lang.String r6 = "&lfloor;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&LeftFloor;"
            r0.addReference(r4, r6)
            r4 = 8971(0x230b, float:1.2571E-41)
            java.lang.String r6 = "&rfloor;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&RightFloor;"
            r0.addReference(r4, r6)
            r4 = 8972(0x230c, float:1.2572E-41)
            java.lang.String r6 = "&drcrop;"
            r0.addReference(r4, r6)
            r4 = 8973(0x230d, float:1.2574E-41)
            java.lang.String r6 = "&dlcrop;"
            r0.addReference(r4, r6)
            r4 = 8974(0x230e, float:1.2575E-41)
            java.lang.String r6 = "&urcrop;"
            r0.addReference(r4, r6)
            r4 = 8975(0x230f, float:1.2577E-41)
            java.lang.String r6 = "&ulcrop;"
            r0.addReference(r4, r6)
            r4 = 8976(0x2310, float:1.2578E-41)
            java.lang.String r6 = "&bnot;"
            r0.addReference(r4, r6)
            r4 = 8978(0x2312, float:1.2581E-41)
            java.lang.String r6 = "&profline;"
            r0.addReference(r4, r6)
            r4 = 8979(0x2313, float:1.2582E-41)
            java.lang.String r6 = "&profsurf;"
            r0.addReference(r4, r6)
            r4 = 8981(0x2315, float:1.2585E-41)
            java.lang.String r6 = "&telrec;"
            r0.addReference(r4, r6)
            r4 = 8982(0x2316, float:1.2586E-41)
            java.lang.String r6 = "&target;"
            r0.addReference(r4, r6)
            r4 = 8988(0x231c, float:1.2595E-41)
            java.lang.String r6 = "&ulcorn;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ulcorner;"
            r0.addReference(r4, r6)
            r4 = 8989(0x231d, float:1.2596E-41)
            java.lang.String r6 = "&urcorn;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&urcorner;"
            r0.addReference(r4, r6)
            r4 = 8990(0x231e, float:1.2598E-41)
            java.lang.String r6 = "&dlcorn;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&llcorner;"
            r0.addReference(r4, r6)
            r4 = 8991(0x231f, float:1.2599E-41)
            java.lang.String r6 = "&drcorn;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lrcorner;"
            r0.addReference(r4, r6)
            r4 = 8994(0x2322, float:1.2603E-41)
            java.lang.String r6 = "&frown;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&sfrown;"
            r0.addReference(r4, r6)
            r4 = 8995(0x2323, float:1.2605E-41)
            java.lang.String r6 = "&smile;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ssmile;"
            r0.addReference(r4, r6)
            r4 = 9005(0x232d, float:1.2619E-41)
            java.lang.String r6 = "&cylcty;"
            r0.addReference(r4, r6)
            r4 = 9006(0x232e, float:1.262E-41)
            java.lang.String r6 = "&profalar;"
            r0.addReference(r4, r6)
            r4 = 9014(0x2336, float:1.2631E-41)
            java.lang.String r6 = "&topbot;"
            r0.addReference(r4, r6)
            r4 = 9021(0x233d, float:1.2641E-41)
            java.lang.String r6 = "&ovbar;"
            r0.addReference(r4, r6)
            r4 = 9023(0x233f, float:1.2644E-41)
            java.lang.String r6 = "&solbar;"
            r0.addReference(r4, r6)
            r4 = 9084(0x237c, float:1.273E-41)
            java.lang.String r6 = "&angzarr;"
            r0.addReference(r4, r6)
            r4 = 9136(0x23b0, float:1.2802E-41)
            java.lang.String r6 = "&lmoust;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lmoustache;"
            r0.addReference(r4, r6)
            r4 = 9137(0x23b1, float:1.2804E-41)
            java.lang.String r6 = "&rmoust;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&rmoustache;"
            r0.addReference(r4, r6)
            r4 = 9140(0x23b4, float:1.2808E-41)
            java.lang.String r6 = "&tbrk;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&OverBracket;"
            r0.addReference(r4, r6)
            r4 = 9141(0x23b5, float:1.2809E-41)
            java.lang.String r6 = "&bbrk;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&UnderBracket;"
            r0.addReference(r4, r6)
            r4 = 9142(0x23b6, float:1.281E-41)
            java.lang.String r6 = "&bbrktbrk;"
            r0.addReference(r4, r6)
            r4 = 9180(0x23dc, float:1.2864E-41)
            java.lang.String r6 = "&OverParenthesis;"
            r0.addReference(r4, r6)
            r4 = 9181(0x23dd, float:1.2865E-41)
            java.lang.String r6 = "&UnderParenthesis;"
            r0.addReference(r4, r6)
            r4 = 9182(0x23de, float:1.2867E-41)
            java.lang.String r6 = "&OverBrace;"
            r0.addReference(r4, r6)
            r4 = 9183(0x23df, float:1.2868E-41)
            java.lang.String r6 = "&UnderBrace;"
            r0.addReference(r4, r6)
            r4 = 9186(0x23e2, float:1.2872E-41)
            java.lang.String r6 = "&trpezium;"
            r0.addReference(r4, r6)
            r4 = 9191(0x23e7, float:1.288E-41)
            java.lang.String r6 = "&elinters;"
            r0.addReference(r4, r6)
            r4 = 9251(0x2423, float:1.2963E-41)
            java.lang.String r6 = "&blank;"
            r0.addReference(r4, r6)
            r4 = 9416(0x24c8, float:1.3195E-41)
            java.lang.String r6 = "&circledS;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&oS;"
            r0.addReference(r4, r6)
            r4 = 9472(0x2500, float:1.3273E-41)
            java.lang.String r6 = "&boxh;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&HorizontalLine;"
            r0.addReference(r4, r6)
            r4 = 9474(0x2502, float:1.3276E-41)
            java.lang.String r6 = "&boxv;"
            r0.addReference(r4, r6)
            r4 = 9484(0x250c, float:1.329E-41)
            java.lang.String r6 = "&boxdr;"
            r0.addReference(r4, r6)
            r4 = 9488(0x2510, float:1.3296E-41)
            java.lang.String r6 = "&boxdl;"
            r0.addReference(r4, r6)
            r4 = 9492(0x2514, float:1.3301E-41)
            java.lang.String r6 = "&boxur;"
            r0.addReference(r4, r6)
            r4 = 9496(0x2518, float:1.3307E-41)
            java.lang.String r6 = "&boxul;"
            r0.addReference(r4, r6)
            r4 = 9500(0x251c, float:1.3312E-41)
            java.lang.String r6 = "&boxvr;"
            r0.addReference(r4, r6)
            r4 = 9508(0x2524, float:1.3324E-41)
            java.lang.String r6 = "&boxvl;"
            r0.addReference(r4, r6)
            r4 = 9516(0x252c, float:1.3335E-41)
            java.lang.String r6 = "&boxhd;"
            r0.addReference(r4, r6)
            r4 = 9524(0x2534, float:1.3346E-41)
            java.lang.String r6 = "&boxhu;"
            r0.addReference(r4, r6)
            r4 = 9532(0x253c, float:1.3357E-41)
            java.lang.String r6 = "&boxvh;"
            r0.addReference(r4, r6)
            r4 = 9552(0x2550, float:1.3385E-41)
            java.lang.String r6 = "&boxH;"
            r0.addReference(r4, r6)
            r4 = 9553(0x2551, float:1.3387E-41)
            java.lang.String r6 = "&boxV;"
            r0.addReference(r4, r6)
            r4 = 9554(0x2552, float:1.3388E-41)
            java.lang.String r6 = "&boxdR;"
            r0.addReference(r4, r6)
            r4 = 9555(0x2553, float:1.339E-41)
            java.lang.String r6 = "&boxDr;"
            r0.addReference(r4, r6)
            r4 = 9556(0x2554, float:1.3391E-41)
            java.lang.String r6 = "&boxDR;"
            r0.addReference(r4, r6)
            r4 = 9557(0x2555, float:1.3392E-41)
            java.lang.String r6 = "&boxdL;"
            r0.addReference(r4, r6)
            r4 = 9558(0x2556, float:1.3394E-41)
            java.lang.String r6 = "&boxDl;"
            r0.addReference(r4, r6)
            r4 = 9559(0x2557, float:1.3395E-41)
            java.lang.String r6 = "&boxDL;"
            r0.addReference(r4, r6)
            r4 = 9560(0x2558, float:1.3396E-41)
            java.lang.String r6 = "&boxuR;"
            r0.addReference(r4, r6)
            r4 = 9561(0x2559, float:1.3398E-41)
            java.lang.String r6 = "&boxUr;"
            r0.addReference(r4, r6)
            r4 = 9562(0x255a, float:1.3399E-41)
            java.lang.String r6 = "&boxUR;"
            r0.addReference(r4, r6)
            r4 = 9563(0x255b, float:1.34E-41)
            java.lang.String r6 = "&boxuL;"
            r0.addReference(r4, r6)
            r4 = 9564(0x255c, float:1.3402E-41)
            java.lang.String r6 = "&boxUl;"
            r0.addReference(r4, r6)
            r4 = 9565(0x255d, float:1.3403E-41)
            java.lang.String r6 = "&boxUL;"
            r0.addReference(r4, r6)
            r4 = 9566(0x255e, float:1.3405E-41)
            java.lang.String r6 = "&boxvR;"
            r0.addReference(r4, r6)
            r4 = 9567(0x255f, float:1.3406E-41)
            java.lang.String r6 = "&boxVr;"
            r0.addReference(r4, r6)
            r4 = 9568(0x2560, float:1.3408E-41)
            java.lang.String r6 = "&boxVR;"
            r0.addReference(r4, r6)
            r4 = 9569(0x2561, float:1.3409E-41)
            java.lang.String r6 = "&boxvL;"
            r0.addReference(r4, r6)
            r4 = 9570(0x2562, float:1.341E-41)
            java.lang.String r6 = "&boxVl;"
            r0.addReference(r4, r6)
            r4 = 9571(0x2563, float:1.3412E-41)
            java.lang.String r6 = "&boxVL;"
            r0.addReference(r4, r6)
            r4 = 9572(0x2564, float:1.3413E-41)
            java.lang.String r6 = "&boxHd;"
            r0.addReference(r4, r6)
            r4 = 9573(0x2565, float:1.3415E-41)
            java.lang.String r6 = "&boxhD;"
            r0.addReference(r4, r6)
            r4 = 9574(0x2566, float:1.3416E-41)
            java.lang.String r6 = "&boxHD;"
            r0.addReference(r4, r6)
            r4 = 9575(0x2567, float:1.3417E-41)
            java.lang.String r6 = "&boxHu;"
            r0.addReference(r4, r6)
            r4 = 9576(0x2568, float:1.3419E-41)
            java.lang.String r6 = "&boxhU;"
            r0.addReference(r4, r6)
            r4 = 9577(0x2569, float:1.342E-41)
            java.lang.String r6 = "&boxHU;"
            r0.addReference(r4, r6)
            r4 = 9578(0x256a, float:1.3422E-41)
            java.lang.String r6 = "&boxvH;"
            r0.addReference(r4, r6)
            r4 = 9579(0x256b, float:1.3423E-41)
            java.lang.String r6 = "&boxVh;"
            r0.addReference(r4, r6)
            r4 = 9580(0x256c, float:1.3424E-41)
            java.lang.String r6 = "&boxVH;"
            r0.addReference(r4, r6)
            r4 = 9600(0x2580, float:1.3452E-41)
            java.lang.String r6 = "&uhblk;"
            r0.addReference(r4, r6)
            r4 = 9604(0x2584, float:1.3458E-41)
            java.lang.String r6 = "&lhblk;"
            r0.addReference(r4, r6)
            r4 = 9608(0x2588, float:1.3464E-41)
            java.lang.String r6 = "&block;"
            r0.addReference(r4, r6)
            r4 = 9617(0x2591, float:1.3476E-41)
            java.lang.String r6 = "&blk14;"
            r0.addReference(r4, r6)
            r4 = 9618(0x2592, float:1.3478E-41)
            java.lang.String r6 = "&blk12;"
            r0.addReference(r4, r6)
            r4 = 9619(0x2593, float:1.3479E-41)
            java.lang.String r6 = "&blk34;"
            r0.addReference(r4, r6)
            r4 = 9633(0x25a1, float:1.3499E-41)
            java.lang.String r6 = "&squ;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&square;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Square;"
            r0.addReference(r4, r6)
            r4 = 9642(0x25aa, float:1.3511E-41)
            java.lang.String r6 = "&blacksquare;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&squarf;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&squf;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&FilledVerySmallSquare;"
            r0.addReference(r4, r6)
            r4 = 9643(0x25ab, float:1.3513E-41)
            java.lang.String r6 = "&EmptyVerySmallSquare;"
            r0.addReference(r4, r6)
            r4 = 9645(0x25ad, float:1.3516E-41)
            java.lang.String r6 = "&rect;"
            r0.addReference(r4, r6)
            r4 = 9646(0x25ae, float:1.3517E-41)
            java.lang.String r6 = "&marker;"
            r0.addReference(r4, r6)
            r4 = 9649(0x25b1, float:1.3521E-41)
            java.lang.String r6 = "&fltns;"
            r0.addReference(r4, r6)
            r4 = 9651(0x25b3, float:1.3524E-41)
            java.lang.String r6 = "&bigtriangleup;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xutri;"
            r0.addReference(r4, r6)
            r4 = 9652(0x25b4, float:1.3525E-41)
            java.lang.String r6 = "&blacktriangle;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&utrif;"
            r0.addReference(r4, r6)
            r4 = 9653(0x25b5, float:1.3527E-41)
            java.lang.String r6 = "&triangle;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&utri;"
            r0.addReference(r4, r6)
            r4 = 9656(0x25b8, float:1.3531E-41)
            java.lang.String r6 = "&blacktriangleright;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&rtrif;"
            r0.addReference(r4, r6)
            r4 = 9657(0x25b9, float:1.3532E-41)
            java.lang.String r6 = "&rtri;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&triangleright;"
            r0.addReference(r4, r6)
            r4 = 9661(0x25bd, float:1.3538E-41)
            java.lang.String r6 = "&bigtriangledown;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xdtri;"
            r0.addReference(r4, r6)
            r4 = 9662(0x25be, float:1.354E-41)
            java.lang.String r6 = "&blacktriangledown;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&dtrif;"
            r0.addReference(r4, r6)
            r4 = 9663(0x25bf, float:1.3541E-41)
            java.lang.String r6 = "&dtri;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&triangledown;"
            r0.addReference(r4, r6)
            r4 = 9666(0x25c2, float:1.3545E-41)
            java.lang.String r6 = "&blacktriangleleft;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ltrif;"
            r0.addReference(r4, r6)
            r4 = 9667(0x25c3, float:1.3546E-41)
            java.lang.String r6 = "&ltri;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&triangleleft;"
            r0.addReference(r4, r6)
            r4 = 9674(0x25ca, float:1.3556E-41)
            java.lang.String r6 = "&loz;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lozenge;"
            r0.addReference(r4, r6)
            r4 = 9675(0x25cb, float:1.3558E-41)
            java.lang.String r6 = "&cir;"
            r0.addReference(r4, r6)
            r4 = 9708(0x25ec, float:1.3604E-41)
            java.lang.String r6 = "&tridot;"
            r0.addReference(r4, r6)
            r4 = 9711(0x25ef, float:1.3608E-41)
            java.lang.String r6 = "&bigcirc;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xcirc;"
            r0.addReference(r4, r6)
            r4 = 9720(0x25f8, float:1.362E-41)
            java.lang.String r6 = "&ultri;"
            r0.addReference(r4, r6)
            r4 = 9721(0x25f9, float:1.3622E-41)
            java.lang.String r6 = "&urtri;"
            r0.addReference(r4, r6)
            r4 = 9722(0x25fa, float:1.3623E-41)
            java.lang.String r6 = "&lltri;"
            r0.addReference(r4, r6)
            r4 = 9723(0x25fb, float:1.3625E-41)
            java.lang.String r6 = "&EmptySmallSquare;"
            r0.addReference(r4, r6)
            r4 = 9724(0x25fc, float:1.3626E-41)
            java.lang.String r6 = "&FilledSmallSquare;"
            r0.addReference(r4, r6)
            r4 = 9733(0x2605, float:1.3639E-41)
            java.lang.String r6 = "&bigstar;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&starf;"
            r0.addReference(r4, r6)
            r4 = 9734(0x2606, float:1.364E-41)
            java.lang.String r6 = "&star;"
            r0.addReference(r4, r6)
            r4 = 9742(0x260e, float:1.3651E-41)
            java.lang.String r6 = "&phone;"
            r0.addReference(r4, r6)
            r4 = 9792(0x2640, float:1.3722E-41)
            java.lang.String r6 = "&female;"
            r0.addReference(r4, r6)
            r4 = 9794(0x2642, float:1.3724E-41)
            java.lang.String r6 = "&male;"
            r0.addReference(r4, r6)
            r4 = 9824(0x2660, float:1.3766E-41)
            java.lang.String r6 = "&spades;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&spadesuit;"
            r0.addReference(r4, r6)
            r4 = 9827(0x2663, float:1.377E-41)
            java.lang.String r6 = "&clubs;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&clubsuit;"
            r0.addReference(r4, r6)
            r4 = 9829(0x2665, float:1.3773E-41)
            java.lang.String r6 = "&hearts;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&heartsuit;"
            r0.addReference(r4, r6)
            r4 = 9830(0x2666, float:1.3775E-41)
            java.lang.String r6 = "&diams;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&diamondsuit;"
            r0.addReference(r4, r6)
            r4 = 9834(0x266a, float:1.378E-41)
            java.lang.String r6 = "&sung;"
            r0.addReference(r4, r6)
            r4 = 9837(0x266d, float:1.3785E-41)
            java.lang.String r6 = "&flat;"
            r0.addReference(r4, r6)
            r4 = 9838(0x266e, float:1.3786E-41)
            java.lang.String r6 = "&natur;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&natural;"
            r0.addReference(r4, r6)
            r4 = 9839(0x266f, float:1.3787E-41)
            java.lang.String r6 = "&sharp;"
            r0.addReference(r4, r6)
            r4 = 10003(0x2713, float:1.4017E-41)
            java.lang.String r6 = "&check;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&checkmark;"
            r0.addReference(r4, r6)
            r4 = 10007(0x2717, float:1.4023E-41)
            java.lang.String r6 = "&cross;"
            r0.addReference(r4, r6)
            r4 = 10016(0x2720, float:1.4035E-41)
            java.lang.String r6 = "&malt;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&maltese;"
            r0.addReference(r4, r6)
            r4 = 10038(0x2736, float:1.4066E-41)
            java.lang.String r6 = "&sext;"
            r0.addReference(r4, r6)
            r4 = 10072(0x2758, float:1.4114E-41)
            java.lang.String r6 = "&VerticalSeparator;"
            r0.addReference(r4, r6)
            r4 = 10098(0x2772, float:1.415E-41)
            java.lang.String r6 = "&lbbrk;"
            r0.addReference(r4, r6)
            r4 = 10099(0x2773, float:1.4152E-41)
            java.lang.String r6 = "&rbbrk;"
            r0.addReference(r4, r6)
            r4 = 10184(0x27c8, float:1.4271E-41)
            java.lang.String r6 = "&bsolhsub;"
            r0.addReference(r4, r6)
            r4 = 10185(0x27c9, float:1.4272E-41)
            java.lang.String r6 = "&suphsol;"
            r0.addReference(r4, r6)
            r4 = 10214(0x27e6, float:1.4313E-41)
            java.lang.String r6 = "&lobrk;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&LeftDoubleBracket;"
            r0.addReference(r4, r6)
            r4 = 10215(0x27e7, float:1.4314E-41)
            java.lang.String r6 = "&robrk;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&RightDoubleBracket;"
            r0.addReference(r4, r6)
            r4 = 10216(0x27e8, float:1.4316E-41)
            java.lang.String r6 = "&lang;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&langle;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&LeftAngleBracket;"
            r0.addReference(r4, r6)
            r4 = 10217(0x27e9, float:1.4317E-41)
            java.lang.String r6 = "&rang;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&rangle;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&RightAngleBracket;"
            r0.addReference(r4, r6)
            r4 = 10218(0x27ea, float:1.4318E-41)
            java.lang.String r6 = "&Lang;"
            r0.addReference(r4, r6)
            r4 = 10219(0x27eb, float:1.432E-41)
            java.lang.String r6 = "&Rang;"
            r0.addReference(r4, r6)
            r4 = 10220(0x27ec, float:1.4321E-41)
            java.lang.String r6 = "&loang;"
            r0.addReference(r4, r6)
            r4 = 10221(0x27ed, float:1.4323E-41)
            java.lang.String r6 = "&roang;"
            r0.addReference(r4, r6)
            r4 = 10229(0x27f5, float:1.4334E-41)
            java.lang.String r6 = "&longleftarrow;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xlarr;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&LongLeftArrow;"
            r0.addReference(r4, r6)
            r4 = 10230(0x27f6, float:1.4335E-41)
            java.lang.String r6 = "&longrightarrow;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xrarr;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&LongRightArrow;"
            r0.addReference(r4, r6)
            r4 = 10231(0x27f7, float:1.4337E-41)
            java.lang.String r6 = "&longleftrightarrow;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xharr;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&LongLeftRightArrow;"
            r0.addReference(r4, r6)
            r4 = 10232(0x27f8, float:1.4338E-41)
            java.lang.String r6 = "&xlArr;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&DoubleLongLeftArrow;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Longleftarrow;"
            r0.addReference(r4, r6)
            r4 = 10233(0x27f9, float:1.434E-41)
            java.lang.String r6 = "&xrArr;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&DoubleLongRightArrow;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Longrightarrow;"
            r0.addReference(r4, r6)
            r4 = 10234(0x27fa, float:1.4341E-41)
            java.lang.String r6 = "&xhArr;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&DoubleLongLeftRightArrow;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&Longleftrightarrow;"
            r0.addReference(r4, r6)
            r4 = 10236(0x27fc, float:1.4344E-41)
            java.lang.String r6 = "&longmapsto;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xmap;"
            r0.addReference(r4, r6)
            r4 = 10239(0x27ff, float:1.4348E-41)
            java.lang.String r6 = "&dzigrarr;"
            r0.addReference(r4, r6)
            r4 = 10498(0x2902, float:1.4711E-41)
            java.lang.String r6 = "&nvlArr;"
            r0.addReference(r4, r6)
            r4 = 10499(0x2903, float:1.4712E-41)
            java.lang.String r6 = "&nvrArr;"
            r0.addReference(r4, r6)
            r4 = 10500(0x2904, float:1.4714E-41)
            java.lang.String r6 = "&nvHarr;"
            r0.addReference(r4, r6)
            r4 = 10501(0x2905, float:1.4715E-41)
            java.lang.String r6 = "&Map;"
            r0.addReference(r4, r6)
            r4 = 10508(0x290c, float:1.4725E-41)
            java.lang.String r6 = "&lbarr;"
            r0.addReference(r4, r6)
            r4 = 10509(0x290d, float:1.4726E-41)
            java.lang.String r6 = "&bkarow;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&rbarr;"
            r0.addReference(r4, r6)
            r4 = 10510(0x290e, float:1.4728E-41)
            java.lang.String r6 = "&lBarr;"
            r0.addReference(r4, r6)
            r4 = 10511(0x290f, float:1.4729E-41)
            java.lang.String r6 = "&dbkarow;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&rBarr;"
            r0.addReference(r4, r6)
            r4 = 10512(0x2910, float:1.473E-41)
            java.lang.String r6 = "&drbkarow;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&RBarr;"
            r0.addReference(r4, r6)
            r4 = 10513(0x2911, float:1.4732E-41)
            java.lang.String r6 = "&DDotrahd;"
            r0.addReference(r4, r6)
            r4 = 10514(0x2912, float:1.4733E-41)
            java.lang.String r6 = "&UpArrowBar;"
            r0.addReference(r4, r6)
            r4 = 10515(0x2913, float:1.4735E-41)
            java.lang.String r6 = "&DownArrowBar;"
            r0.addReference(r4, r6)
            r4 = 10518(0x2916, float:1.4739E-41)
            java.lang.String r6 = "&Rarrtl;"
            r0.addReference(r4, r6)
            r4 = 10521(0x2919, float:1.4743E-41)
            java.lang.String r6 = "&latail;"
            r0.addReference(r4, r6)
            r4 = 10522(0x291a, float:1.4744E-41)
            java.lang.String r6 = "&ratail;"
            r0.addReference(r4, r6)
            r4 = 10523(0x291b, float:1.4746E-41)
            java.lang.String r6 = "&lAtail;"
            r0.addReference(r4, r6)
            r4 = 10524(0x291c, float:1.4747E-41)
            java.lang.String r6 = "&rAtail;"
            r0.addReference(r4, r6)
            r4 = 10525(0x291d, float:1.4749E-41)
            java.lang.String r6 = "&larrfs;"
            r0.addReference(r4, r6)
            r4 = 10526(0x291e, float:1.475E-41)
            java.lang.String r6 = "&rarrfs;"
            r0.addReference(r4, r6)
            r4 = 10527(0x291f, float:1.4751E-41)
            java.lang.String r6 = "&larrbfs;"
            r0.addReference(r4, r6)
            r4 = 10528(0x2920, float:1.4753E-41)
            java.lang.String r6 = "&rarrbfs;"
            r0.addReference(r4, r6)
            r4 = 10531(0x2923, float:1.4757E-41)
            java.lang.String r6 = "&nwarhk;"
            r0.addReference(r4, r6)
            r4 = 10532(0x2924, float:1.4758E-41)
            java.lang.String r6 = "&nearhk;"
            r0.addReference(r4, r6)
            r4 = 10533(0x2925, float:1.476E-41)
            java.lang.String r6 = "&hksearow;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&searhk;"
            r0.addReference(r4, r6)
            r4 = 10534(0x2926, float:1.4761E-41)
            java.lang.String r6 = "&hkswarow;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&swarhk;"
            r0.addReference(r4, r6)
            r4 = 10535(0x2927, float:1.4763E-41)
            java.lang.String r6 = "&nwnear;"
            r0.addReference(r4, r6)
            r4 = 10536(0x2928, float:1.4764E-41)
            java.lang.String r6 = "&nesear;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&toea;"
            r0.addReference(r4, r6)
            r4 = 10537(0x2929, float:1.4765E-41)
            java.lang.String r6 = "&seswar;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&tosa;"
            r0.addReference(r4, r6)
            r4 = 10538(0x292a, float:1.4767E-41)
            java.lang.String r6 = "&swnwar;"
            r0.addReference(r4, r6)
            r4 = 10547(0x2933, float:1.478E-41)
            java.lang.String r6 = "&rarrc;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&nrarrc;"
            r0.addReference(r4, r7, r6)
            r4 = 10549(0x2935, float:1.4782E-41)
            java.lang.String r6 = "&cudarrr;"
            r0.addReference(r4, r6)
            r4 = 10550(0x2936, float:1.4784E-41)
            java.lang.String r6 = "&ldca;"
            r0.addReference(r4, r6)
            r4 = 10551(0x2937, float:1.4785E-41)
            java.lang.String r6 = "&rdca;"
            r0.addReference(r4, r6)
            r4 = 10552(0x2938, float:1.4787E-41)
            java.lang.String r6 = "&cudarrl;"
            r0.addReference(r4, r6)
            r4 = 10553(0x2939, float:1.4788E-41)
            java.lang.String r6 = "&larrpl;"
            r0.addReference(r4, r6)
            r4 = 10556(0x293c, float:1.4792E-41)
            java.lang.String r6 = "&curarrm;"
            r0.addReference(r4, r6)
            r4 = 10557(0x293d, float:1.4794E-41)
            java.lang.String r6 = "&cularrp;"
            r0.addReference(r4, r6)
            r4 = 10565(0x2945, float:1.4805E-41)
            java.lang.String r6 = "&rarrpl;"
            r0.addReference(r4, r6)
            r4 = 10568(0x2948, float:1.4809E-41)
            java.lang.String r6 = "&harrcir;"
            r0.addReference(r4, r6)
            r4 = 10569(0x2949, float:1.481E-41)
            java.lang.String r6 = "&Uarrocir;"
            r0.addReference(r4, r6)
            r4 = 10570(0x294a, float:1.4812E-41)
            java.lang.String r6 = "&lurdshar;"
            r0.addReference(r4, r6)
            r4 = 10571(0x294b, float:1.4813E-41)
            java.lang.String r6 = "&ldrushar;"
            r0.addReference(r4, r6)
            r4 = 10574(0x294e, float:1.4817E-41)
            java.lang.String r6 = "&LeftRightVector;"
            r0.addReference(r4, r6)
            r4 = 10575(0x294f, float:1.4819E-41)
            java.lang.String r6 = "&RightUpDownVector;"
            r0.addReference(r4, r6)
            r4 = 10576(0x2950, float:1.482E-41)
            java.lang.String r6 = "&DownLeftRightVector;"
            r0.addReference(r4, r6)
            r4 = 10577(0x2951, float:1.4822E-41)
            java.lang.String r6 = "&LeftUpDownVector;"
            r0.addReference(r4, r6)
            r4 = 10578(0x2952, float:1.4823E-41)
            java.lang.String r6 = "&LeftVectorBar;"
            r0.addReference(r4, r6)
            r4 = 10579(0x2953, float:1.4824E-41)
            java.lang.String r6 = "&RightVectorBar;"
            r0.addReference(r4, r6)
            r4 = 10580(0x2954, float:1.4826E-41)
            java.lang.String r6 = "&RightUpVectorBar;"
            r0.addReference(r4, r6)
            r4 = 10581(0x2955, float:1.4827E-41)
            java.lang.String r6 = "&RightDownVectorBar;"
            r0.addReference(r4, r6)
            r4 = 10582(0x2956, float:1.4829E-41)
            java.lang.String r6 = "&DownLeftVectorBar;"
            r0.addReference(r4, r6)
            r4 = 10583(0x2957, float:1.483E-41)
            java.lang.String r6 = "&DownRightVectorBar;"
            r0.addReference(r4, r6)
            r4 = 10584(0x2958, float:1.4831E-41)
            java.lang.String r6 = "&LeftUpVectorBar;"
            r0.addReference(r4, r6)
            r4 = 10585(0x2959, float:1.4833E-41)
            java.lang.String r6 = "&LeftDownVectorBar;"
            r0.addReference(r4, r6)
            r4 = 10586(0x295a, float:1.4834E-41)
            java.lang.String r6 = "&LeftTeeVector;"
            r0.addReference(r4, r6)
            r4 = 10587(0x295b, float:1.4836E-41)
            java.lang.String r6 = "&RightTeeVector;"
            r0.addReference(r4, r6)
            r4 = 10588(0x295c, float:1.4837E-41)
            java.lang.String r6 = "&RightUpTeeVector;"
            r0.addReference(r4, r6)
            r4 = 10589(0x295d, float:1.4838E-41)
            java.lang.String r6 = "&RightDownTeeVector;"
            r0.addReference(r4, r6)
            r4 = 10590(0x295e, float:1.484E-41)
            java.lang.String r6 = "&DownLeftTeeVector;"
            r0.addReference(r4, r6)
            r4 = 10591(0x295f, float:1.4841E-41)
            java.lang.String r6 = "&DownRightTeeVector;"
            r0.addReference(r4, r6)
            r4 = 10592(0x2960, float:1.4843E-41)
            java.lang.String r6 = "&LeftUpTeeVector;"
            r0.addReference(r4, r6)
            r4 = 10593(0x2961, float:1.4844E-41)
            java.lang.String r6 = "&LeftDownTeeVector;"
            r0.addReference(r4, r6)
            r4 = 10594(0x2962, float:1.4845E-41)
            java.lang.String r6 = "&lHar;"
            r0.addReference(r4, r6)
            r4 = 10595(0x2963, float:1.4847E-41)
            java.lang.String r6 = "&uHar;"
            r0.addReference(r4, r6)
            r4 = 10596(0x2964, float:1.4848E-41)
            java.lang.String r6 = "&rHar;"
            r0.addReference(r4, r6)
            r4 = 10597(0x2965, float:1.485E-41)
            java.lang.String r6 = "&dHar;"
            r0.addReference(r4, r6)
            r4 = 10598(0x2966, float:1.4851E-41)
            java.lang.String r6 = "&luruhar;"
            r0.addReference(r4, r6)
            r4 = 10599(0x2967, float:1.4852E-41)
            java.lang.String r6 = "&ldrdhar;"
            r0.addReference(r4, r6)
            r4 = 10600(0x2968, float:1.4854E-41)
            java.lang.String r6 = "&ruluhar;"
            r0.addReference(r4, r6)
            r4 = 10601(0x2969, float:1.4855E-41)
            java.lang.String r6 = "&rdldhar;"
            r0.addReference(r4, r6)
            r4 = 10602(0x296a, float:1.4857E-41)
            java.lang.String r6 = "&lharul;"
            r0.addReference(r4, r6)
            r4 = 10603(0x296b, float:1.4858E-41)
            java.lang.String r6 = "&llhard;"
            r0.addReference(r4, r6)
            r4 = 10604(0x296c, float:1.486E-41)
            java.lang.String r6 = "&rharul;"
            r0.addReference(r4, r6)
            r4 = 10605(0x296d, float:1.4861E-41)
            java.lang.String r6 = "&lrhard;"
            r0.addReference(r4, r6)
            r4 = 10606(0x296e, float:1.4862E-41)
            java.lang.String r6 = "&udhar;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&UpEquilibrium;"
            r0.addReference(r4, r6)
            r4 = 10607(0x296f, float:1.4864E-41)
            java.lang.String r6 = "&duhar;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ReverseUpEquilibrium;"
            r0.addReference(r4, r6)
            r4 = 10608(0x2970, float:1.4865E-41)
            java.lang.String r6 = "&RoundImplies;"
            r0.addReference(r4, r6)
            r4 = 10609(0x2971, float:1.4866E-41)
            java.lang.String r6 = "&erarr;"
            r0.addReference(r4, r6)
            r4 = 10610(0x2972, float:1.4868E-41)
            java.lang.String r6 = "&simrarr;"
            r0.addReference(r4, r6)
            r4 = 10611(0x2973, float:1.4869E-41)
            java.lang.String r6 = "&larrsim;"
            r0.addReference(r4, r6)
            r4 = 10612(0x2974, float:1.487E-41)
            java.lang.String r6 = "&rarrsim;"
            r0.addReference(r4, r6)
            r4 = 10613(0x2975, float:1.4872E-41)
            java.lang.String r6 = "&rarrap;"
            r0.addReference(r4, r6)
            r4 = 10614(0x2976, float:1.4873E-41)
            java.lang.String r6 = "&ltlarr;"
            r0.addReference(r4, r6)
            r4 = 10616(0x2978, float:1.4876E-41)
            java.lang.String r6 = "&gtrarr;"
            r0.addReference(r4, r6)
            r4 = 10617(0x2979, float:1.4878E-41)
            java.lang.String r6 = "&subrarr;"
            r0.addReference(r4, r6)
            r4 = 10619(0x297b, float:1.488E-41)
            java.lang.String r6 = "&suplarr;"
            r0.addReference(r4, r6)
            r4 = 10620(0x297c, float:1.4882E-41)
            java.lang.String r6 = "&lfisht;"
            r0.addReference(r4, r6)
            r4 = 10621(0x297d, float:1.4883E-41)
            java.lang.String r6 = "&rfisht;"
            r0.addReference(r4, r6)
            r4 = 10622(0x297e, float:1.4885E-41)
            java.lang.String r6 = "&ufisht;"
            r0.addReference(r4, r6)
            r4 = 10623(0x297f, float:1.4886E-41)
            java.lang.String r6 = "&dfisht;"
            r0.addReference(r4, r6)
            r4 = 10629(0x2985, float:1.4894E-41)
            java.lang.String r6 = "&lopar;"
            r0.addReference(r4, r6)
            r4 = 10630(0x2986, float:1.4896E-41)
            java.lang.String r6 = "&ropar;"
            r0.addReference(r4, r6)
            r4 = 10635(0x298b, float:1.4903E-41)
            java.lang.String r6 = "&lbrke;"
            r0.addReference(r4, r6)
            r4 = 10636(0x298c, float:1.4904E-41)
            java.lang.String r6 = "&rbrke;"
            r0.addReference(r4, r6)
            r4 = 10637(0x298d, float:1.4906E-41)
            java.lang.String r6 = "&lbrkslu;"
            r0.addReference(r4, r6)
            r4 = 10638(0x298e, float:1.4907E-41)
            java.lang.String r6 = "&rbrksld;"
            r0.addReference(r4, r6)
            r4 = 10639(0x298f, float:1.4908E-41)
            java.lang.String r6 = "&lbrksld;"
            r0.addReference(r4, r6)
            r4 = 10640(0x2990, float:1.491E-41)
            java.lang.String r6 = "&rbrkslu;"
            r0.addReference(r4, r6)
            r4 = 10641(0x2991, float:1.4911E-41)
            java.lang.String r6 = "&langd;"
            r0.addReference(r4, r6)
            r4 = 10642(0x2992, float:1.4913E-41)
            java.lang.String r6 = "&rangd;"
            r0.addReference(r4, r6)
            r4 = 10643(0x2993, float:1.4914E-41)
            java.lang.String r6 = "&lparlt;"
            r0.addReference(r4, r6)
            r4 = 10644(0x2994, float:1.4915E-41)
            java.lang.String r6 = "&rpargt;"
            r0.addReference(r4, r6)
            r4 = 10645(0x2995, float:1.4917E-41)
            java.lang.String r6 = "&gtlPar;"
            r0.addReference(r4, r6)
            r4 = 10646(0x2996, float:1.4918E-41)
            java.lang.String r6 = "&ltrPar;"
            r0.addReference(r4, r6)
            r4 = 10650(0x299a, float:1.4924E-41)
            java.lang.String r6 = "&vzigzag;"
            r0.addReference(r4, r6)
            r4 = 10652(0x299c, float:1.4927E-41)
            java.lang.String r6 = "&vangrt;"
            r0.addReference(r4, r6)
            r4 = 10653(0x299d, float:1.4928E-41)
            java.lang.String r6 = "&angrtvbd;"
            r0.addReference(r4, r6)
            r4 = 10660(0x29a4, float:1.4938E-41)
            java.lang.String r6 = "&ange;"
            r0.addReference(r4, r6)
            r4 = 10661(0x29a5, float:1.4939E-41)
            java.lang.String r6 = "&range;"
            r0.addReference(r4, r6)
            r4 = 10662(0x29a6, float:1.494E-41)
            java.lang.String r6 = "&dwangle;"
            r0.addReference(r4, r6)
            r4 = 10663(0x29a7, float:1.4942E-41)
            java.lang.String r6 = "&uwangle;"
            r0.addReference(r4, r6)
            r4 = 10664(0x29a8, float:1.4943E-41)
            java.lang.String r6 = "&angmsdaa;"
            r0.addReference(r4, r6)
            r4 = 10665(0x29a9, float:1.4945E-41)
            java.lang.String r6 = "&angmsdab;"
            r0.addReference(r4, r6)
            r4 = 10666(0x29aa, float:1.4946E-41)
            java.lang.String r6 = "&angmsdac;"
            r0.addReference(r4, r6)
            r4 = 10667(0x29ab, float:1.4948E-41)
            java.lang.String r6 = "&angmsdad;"
            r0.addReference(r4, r6)
            r4 = 10668(0x29ac, float:1.4949E-41)
            java.lang.String r6 = "&angmsdae;"
            r0.addReference(r4, r6)
            r4 = 10669(0x29ad, float:1.495E-41)
            java.lang.String r6 = "&angmsdaf;"
            r0.addReference(r4, r6)
            r4 = 10670(0x29ae, float:1.4952E-41)
            java.lang.String r6 = "&angmsdag;"
            r0.addReference(r4, r6)
            r4 = 10671(0x29af, float:1.4953E-41)
            java.lang.String r6 = "&angmsdah;"
            r0.addReference(r4, r6)
            r4 = 10672(0x29b0, float:1.4955E-41)
            java.lang.String r6 = "&bemptyv;"
            r0.addReference(r4, r6)
            r4 = 10673(0x29b1, float:1.4956E-41)
            java.lang.String r6 = "&demptyv;"
            r0.addReference(r4, r6)
            r4 = 10674(0x29b2, float:1.4957E-41)
            java.lang.String r6 = "&cemptyv;"
            r0.addReference(r4, r6)
            r4 = 10675(0x29b3, float:1.4959E-41)
            java.lang.String r6 = "&raemptyv;"
            r0.addReference(r4, r6)
            r4 = 10676(0x29b4, float:1.496E-41)
            java.lang.String r6 = "&laemptyv;"
            r0.addReference(r4, r6)
            r4 = 10677(0x29b5, float:1.4962E-41)
            java.lang.String r6 = "&ohbar;"
            r0.addReference(r4, r6)
            r4 = 10678(0x29b6, float:1.4963E-41)
            java.lang.String r6 = "&omid;"
            r0.addReference(r4, r6)
            r4 = 10679(0x29b7, float:1.4964E-41)
            java.lang.String r6 = "&opar;"
            r0.addReference(r4, r6)
            r4 = 10681(0x29b9, float:1.4967E-41)
            java.lang.String r6 = "&operp;"
            r0.addReference(r4, r6)
            r4 = 10683(0x29bb, float:1.497E-41)
            java.lang.String r6 = "&olcross;"
            r0.addReference(r4, r6)
            r4 = 10684(0x29bc, float:1.4971E-41)
            java.lang.String r6 = "&odsold;"
            r0.addReference(r4, r6)
            r4 = 10686(0x29be, float:1.4974E-41)
            java.lang.String r6 = "&olcir;"
            r0.addReference(r4, r6)
            r4 = 10687(0x29bf, float:1.4976E-41)
            java.lang.String r6 = "&ofcir;"
            r0.addReference(r4, r6)
            r4 = 10688(0x29c0, float:1.4977E-41)
            java.lang.String r6 = "&olt;"
            r0.addReference(r4, r6)
            r4 = 10689(0x29c1, float:1.4978E-41)
            java.lang.String r6 = "&ogt;"
            r0.addReference(r4, r6)
            r4 = 10690(0x29c2, float:1.498E-41)
            java.lang.String r6 = "&cirscir;"
            r0.addReference(r4, r6)
            r4 = 10691(0x29c3, float:1.4981E-41)
            java.lang.String r6 = "&cirE;"
            r0.addReference(r4, r6)
            r4 = 10692(0x29c4, float:1.4983E-41)
            java.lang.String r6 = "&solb;"
            r0.addReference(r4, r6)
            r4 = 10693(0x29c5, float:1.4984E-41)
            java.lang.String r6 = "&bsolb;"
            r0.addReference(r4, r6)
            r4 = 10697(0x29c9, float:1.499E-41)
            java.lang.String r6 = "&boxbox;"
            r0.addReference(r4, r6)
            r4 = 10701(0x29cd, float:1.4995E-41)
            java.lang.String r6 = "&trisb;"
            r0.addReference(r4, r6)
            r4 = 10702(0x29ce, float:1.4997E-41)
            java.lang.String r6 = "&rtriltri;"
            r0.addReference(r4, r6)
            r4 = 10703(0x29cf, float:1.4998E-41)
            java.lang.String r6 = "&LeftTriangleBar;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotLeftTriangleBar;"
            r0.addReference(r4, r7, r6)
            r4 = 10704(0x29d0, float:1.5E-41)
            java.lang.String r6 = "&RightTriangleBar;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotRightTriangleBar;"
            r0.addReference(r4, r7, r6)
            r4 = 10716(0x29dc, float:1.5016E-41)
            java.lang.String r6 = "&iinfin;"
            r0.addReference(r4, r6)
            r4 = 10717(0x29dd, float:1.5018E-41)
            java.lang.String r6 = "&infintie;"
            r0.addReference(r4, r6)
            r4 = 10718(0x29de, float:1.5019E-41)
            java.lang.String r6 = "&nvinfin;"
            r0.addReference(r4, r6)
            r4 = 10723(0x29e3, float:1.5026E-41)
            java.lang.String r6 = "&eparsl;"
            r0.addReference(r4, r6)
            r4 = 10724(0x29e4, float:1.5028E-41)
            java.lang.String r6 = "&smeparsl;"
            r0.addReference(r4, r6)
            r4 = 10725(0x29e5, float:1.5029E-41)
            java.lang.String r6 = "&eqvparsl;"
            r0.addReference(r4, r6)
            r4 = 10731(0x29eb, float:1.5037E-41)
            java.lang.String r6 = "&blacklozenge;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lozf;"
            r0.addReference(r4, r6)
            r4 = 10740(0x29f4, float:1.505E-41)
            java.lang.String r6 = "&RuleDelayed;"
            r0.addReference(r4, r6)
            r4 = 10742(0x29f6, float:1.5053E-41)
            java.lang.String r6 = "&dsol;"
            r0.addReference(r4, r6)
            r4 = 10752(0x2a00, float:1.5067E-41)
            java.lang.String r6 = "&bigodot;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xodot;"
            r0.addReference(r4, r6)
            r4 = 10753(0x2a01, float:1.5068E-41)
            java.lang.String r6 = "&bigoplus;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xoplus;"
            r0.addReference(r4, r6)
            r4 = 10754(0x2a02, float:1.507E-41)
            java.lang.String r6 = "&bigotimes;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xotime;"
            r0.addReference(r4, r6)
            r4 = 10756(0x2a04, float:1.5072E-41)
            java.lang.String r6 = "&biguplus;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xuplus;"
            r0.addReference(r4, r6)
            r4 = 10758(0x2a06, float:1.5075E-41)
            java.lang.String r6 = "&bigsqcup;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&xsqcup;"
            r0.addReference(r4, r6)
            r4 = 10764(0x2a0c, float:1.5084E-41)
            java.lang.String r6 = "&iiiint;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&qint;"
            r0.addReference(r4, r6)
            r4 = 10765(0x2a0d, float:1.5085E-41)
            java.lang.String r6 = "&fpartint;"
            r0.addReference(r4, r6)
            r4 = 10768(0x2a10, float:1.5089E-41)
            java.lang.String r6 = "&cirfnint;"
            r0.addReference(r4, r6)
            r4 = 10769(0x2a11, float:1.509E-41)
            java.lang.String r6 = "&awint;"
            r0.addReference(r4, r6)
            r4 = 10770(0x2a12, float:1.5092E-41)
            java.lang.String r6 = "&rppolint;"
            r0.addReference(r4, r6)
            r4 = 10771(0x2a13, float:1.5093E-41)
            java.lang.String r6 = "&scpolint;"
            r0.addReference(r4, r6)
            r4 = 10772(0x2a14, float:1.5095E-41)
            java.lang.String r6 = "&npolint;"
            r0.addReference(r4, r6)
            r4 = 10773(0x2a15, float:1.5096E-41)
            java.lang.String r6 = "&pointint;"
            r0.addReference(r4, r6)
            r4 = 10774(0x2a16, float:1.5098E-41)
            java.lang.String r6 = "&quatint;"
            r0.addReference(r4, r6)
            r4 = 10775(0x2a17, float:1.5099E-41)
            java.lang.String r6 = "&intlarhk;"
            r0.addReference(r4, r6)
            r4 = 10786(0x2a22, float:1.5114E-41)
            java.lang.String r6 = "&pluscir;"
            r0.addReference(r4, r6)
            r4 = 10787(0x2a23, float:1.5116E-41)
            java.lang.String r6 = "&plusacir;"
            r0.addReference(r4, r6)
            r4 = 10788(0x2a24, float:1.5117E-41)
            java.lang.String r6 = "&simplus;"
            r0.addReference(r4, r6)
            r4 = 10789(0x2a25, float:1.5119E-41)
            java.lang.String r6 = "&plusdu;"
            r0.addReference(r4, r6)
            r4 = 10790(0x2a26, float:1.512E-41)
            java.lang.String r6 = "&plussim;"
            r0.addReference(r4, r6)
            r4 = 10791(0x2a27, float:1.5121E-41)
            java.lang.String r6 = "&plustwo;"
            r0.addReference(r4, r6)
            r4 = 10793(0x2a29, float:1.5124E-41)
            java.lang.String r6 = "&mcomma;"
            r0.addReference(r4, r6)
            r4 = 10794(0x2a2a, float:1.5126E-41)
            java.lang.String r6 = "&minusdu;"
            r0.addReference(r4, r6)
            r4 = 10797(0x2a2d, float:1.513E-41)
            java.lang.String r6 = "&loplus;"
            r0.addReference(r4, r6)
            r4 = 10798(0x2a2e, float:1.5131E-41)
            java.lang.String r6 = "&roplus;"
            r0.addReference(r4, r6)
            r4 = 10799(0x2a2f, float:1.5133E-41)
            java.lang.String r6 = "&Cross;"
            r0.addReference(r4, r6)
            r4 = 10800(0x2a30, float:1.5134E-41)
            java.lang.String r6 = "&timesd;"
            r0.addReference(r4, r6)
            r4 = 10801(0x2a31, float:1.5135E-41)
            java.lang.String r6 = "&timesbar;"
            r0.addReference(r4, r6)
            r4 = 10803(0x2a33, float:1.5138E-41)
            java.lang.String r6 = "&smashp;"
            r0.addReference(r4, r6)
            r4 = 10804(0x2a34, float:1.514E-41)
            java.lang.String r6 = "&lotimes;"
            r0.addReference(r4, r6)
            r4 = 10805(0x2a35, float:1.5141E-41)
            java.lang.String r6 = "&rotimes;"
            r0.addReference(r4, r6)
            r4 = 10806(0x2a36, float:1.5142E-41)
            java.lang.String r6 = "&otimesas;"
            r0.addReference(r4, r6)
            r4 = 10807(0x2a37, float:1.5144E-41)
            java.lang.String r6 = "&Otimes;"
            r0.addReference(r4, r6)
            r4 = 10808(0x2a38, float:1.5145E-41)
            java.lang.String r6 = "&odiv;"
            r0.addReference(r4, r6)
            r4 = 10809(0x2a39, float:1.5147E-41)
            java.lang.String r6 = "&triplus;"
            r0.addReference(r4, r6)
            r4 = 10810(0x2a3a, float:1.5148E-41)
            java.lang.String r6 = "&triminus;"
            r0.addReference(r4, r6)
            r4 = 10811(0x2a3b, float:1.515E-41)
            java.lang.String r6 = "&tritime;"
            r0.addReference(r4, r6)
            r4 = 10812(0x2a3c, float:1.5151E-41)
            java.lang.String r6 = "&intprod;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&iprod;"
            r0.addReference(r4, r6)
            r4 = 10815(0x2a3f, float:1.5155E-41)
            java.lang.String r6 = "&amalg;"
            r0.addReference(r4, r6)
            r4 = 10816(0x2a40, float:1.5156E-41)
            java.lang.String r6 = "&capdot;"
            r0.addReference(r4, r6)
            r4 = 10818(0x2a42, float:1.5159E-41)
            java.lang.String r6 = "&ncup;"
            r0.addReference(r4, r6)
            r4 = 10819(0x2a43, float:1.516E-41)
            java.lang.String r6 = "&ncap;"
            r0.addReference(r4, r6)
            r4 = 10820(0x2a44, float:1.5162E-41)
            java.lang.String r6 = "&capand;"
            r0.addReference(r4, r6)
            r4 = 10821(0x2a45, float:1.5163E-41)
            java.lang.String r6 = "&cupor;"
            r0.addReference(r4, r6)
            r4 = 10822(0x2a46, float:1.5165E-41)
            java.lang.String r6 = "&cupcap;"
            r0.addReference(r4, r6)
            r4 = 10823(0x2a47, float:1.5166E-41)
            java.lang.String r6 = "&capcup;"
            r0.addReference(r4, r6)
            r4 = 10824(0x2a48, float:1.5168E-41)
            java.lang.String r6 = "&cupbrcap;"
            r0.addReference(r4, r6)
            r4 = 10825(0x2a49, float:1.5169E-41)
            java.lang.String r6 = "&capbrcup;"
            r0.addReference(r4, r6)
            r4 = 10826(0x2a4a, float:1.517E-41)
            java.lang.String r6 = "&cupcup;"
            r0.addReference(r4, r6)
            r4 = 10827(0x2a4b, float:1.5172E-41)
            java.lang.String r6 = "&capcap;"
            r0.addReference(r4, r6)
            r4 = 10828(0x2a4c, float:1.5173E-41)
            java.lang.String r6 = "&ccups;"
            r0.addReference(r4, r6)
            r4 = 10829(0x2a4d, float:1.5175E-41)
            java.lang.String r6 = "&ccaps;"
            r0.addReference(r4, r6)
            r4 = 10832(0x2a50, float:1.5179E-41)
            java.lang.String r6 = "&ccupssm;"
            r0.addReference(r4, r6)
            r4 = 10835(0x2a53, float:1.5183E-41)
            java.lang.String r6 = "&And;"
            r0.addReference(r4, r6)
            r4 = 10836(0x2a54, float:1.5184E-41)
            java.lang.String r6 = "&Or;"
            r0.addReference(r4, r6)
            r4 = 10837(0x2a55, float:1.5186E-41)
            java.lang.String r6 = "&andand;"
            r0.addReference(r4, r6)
            r4 = 10838(0x2a56, float:1.5187E-41)
            java.lang.String r6 = "&oror;"
            r0.addReference(r4, r6)
            r4 = 10839(0x2a57, float:1.5189E-41)
            java.lang.String r6 = "&orslope;"
            r0.addReference(r4, r6)
            r4 = 10840(0x2a58, float:1.519E-41)
            java.lang.String r6 = "&andslope;"
            r0.addReference(r4, r6)
            r4 = 10842(0x2a5a, float:1.5193E-41)
            java.lang.String r6 = "&andv;"
            r0.addReference(r4, r6)
            r4 = 10843(0x2a5b, float:1.5194E-41)
            java.lang.String r6 = "&orv;"
            r0.addReference(r4, r6)
            r4 = 10844(0x2a5c, float:1.5196E-41)
            java.lang.String r6 = "&andd;"
            r0.addReference(r4, r6)
            r4 = 10845(0x2a5d, float:1.5197E-41)
            java.lang.String r6 = "&ord;"
            r0.addReference(r4, r6)
            r4 = 10847(0x2a5f, float:1.52E-41)
            java.lang.String r6 = "&wedbar;"
            r0.addReference(r4, r6)
            r4 = 10854(0x2a66, float:1.521E-41)
            java.lang.String r6 = "&sdote;"
            r0.addReference(r4, r6)
            r4 = 10858(0x2a6a, float:1.5215E-41)
            java.lang.String r6 = "&simdot;"
            r0.addReference(r4, r6)
            r4 = 10861(0x2a6d, float:1.522E-41)
            java.lang.String r6 = "&congdot;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ncongdot;"
            r0.addReference(r4, r7, r6)
            r4 = 10862(0x2a6e, float:1.5221E-41)
            java.lang.String r6 = "&easter;"
            r0.addReference(r4, r6)
            r4 = 10863(0x2a6f, float:1.5222E-41)
            java.lang.String r6 = "&apacir;"
            r0.addReference(r4, r6)
            r4 = 10864(0x2a70, float:1.5224E-41)
            java.lang.String r6 = "&apE;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&napE;"
            r0.addReference(r4, r7, r6)
            r4 = 10865(0x2a71, float:1.5225E-41)
            java.lang.String r6 = "&eplus;"
            r0.addReference(r4, r6)
            r4 = 10866(0x2a72, float:1.5227E-41)
            java.lang.String r6 = "&pluse;"
            r0.addReference(r4, r6)
            r4 = 10867(0x2a73, float:1.5228E-41)
            java.lang.String r6 = "&Esim;"
            r0.addReference(r4, r6)
            r4 = 10868(0x2a74, float:1.523E-41)
            java.lang.String r6 = "&Colone;"
            r0.addReference(r4, r6)
            r4 = 10869(0x2a75, float:1.5231E-41)
            java.lang.String r6 = "&Equal;"
            r0.addReference(r4, r6)
            r4 = 10871(0x2a77, float:1.5234E-41)
            java.lang.String r6 = "&ddotseq;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&eDDot;"
            r0.addReference(r4, r6)
            r4 = 10872(0x2a78, float:1.5235E-41)
            java.lang.String r6 = "&equivDD;"
            r0.addReference(r4, r6)
            r4 = 10873(0x2a79, float:1.5236E-41)
            java.lang.String r6 = "&ltcir;"
            r0.addReference(r4, r6)
            r4 = 10874(0x2a7a, float:1.5238E-41)
            java.lang.String r6 = "&gtcir;"
            r0.addReference(r4, r6)
            r4 = 10875(0x2a7b, float:1.5239E-41)
            java.lang.String r6 = "&ltquest;"
            r0.addReference(r4, r6)
            r4 = 10876(0x2a7c, float:1.524E-41)
            java.lang.String r6 = "&gtquest;"
            r0.addReference(r4, r6)
            r4 = 10877(0x2a7d, float:1.5242E-41)
            java.lang.String r6 = "&leqslant;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&les;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&LessSlantEqual;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&nleqslant;"
            r0.addReference(r4, r7, r6)
            java.lang.String r6 = "&nles;"
            r0.addReference(r4, r7, r6)
            java.lang.String r6 = "&NotLessSlantEqual;"
            r0.addReference(r4, r7, r6)
            r4 = 10878(0x2a7e, float:1.5243E-41)
            java.lang.String r6 = "&geqslant;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ges;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&GreaterSlantEqual;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&ngeqslant;"
            r0.addReference(r4, r7, r6)
            java.lang.String r6 = "&nges;"
            r0.addReference(r4, r7, r6)
            java.lang.String r6 = "&NotGreaterSlantEqual;"
            r0.addReference(r4, r7, r6)
            r4 = 10879(0x2a7f, float:1.5245E-41)
            java.lang.String r6 = "&lesdot;"
            r0.addReference(r4, r6)
            r4 = 10880(0x2a80, float:1.5246E-41)
            java.lang.String r6 = "&gesdot;"
            r0.addReference(r4, r6)
            r4 = 10881(0x2a81, float:1.5248E-41)
            java.lang.String r6 = "&lesdoto;"
            r0.addReference(r4, r6)
            r4 = 10882(0x2a82, float:1.5249E-41)
            java.lang.String r6 = "&gesdoto;"
            r0.addReference(r4, r6)
            r4 = 10883(0x2a83, float:1.525E-41)
            java.lang.String r6 = "&lesdotor;"
            r0.addReference(r4, r6)
            r4 = 10884(0x2a84, float:1.5252E-41)
            java.lang.String r6 = "&gesdotol;"
            r0.addReference(r4, r6)
            r4 = 10885(0x2a85, float:1.5253E-41)
            java.lang.String r6 = "&lap;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lessapprox;"
            r0.addReference(r4, r6)
            r4 = 10886(0x2a86, float:1.5255E-41)
            java.lang.String r6 = "&gap;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&gtrapprox;"
            r0.addReference(r4, r6)
            r4 = 10887(0x2a87, float:1.5256E-41)
            java.lang.String r6 = "&lne;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lneq;"
            r0.addReference(r4, r6)
            r4 = 10888(0x2a88, float:1.5257E-41)
            java.lang.String r6 = "&gne;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&gneq;"
            r0.addReference(r4, r6)
            r4 = 10889(0x2a89, float:1.5259E-41)
            java.lang.String r6 = "&lnap;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lnapprox;"
            r0.addReference(r4, r6)
            r4 = 10890(0x2a8a, float:1.526E-41)
            java.lang.String r6 = "&gnap;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&gnapprox;"
            r0.addReference(r4, r6)
            r4 = 10891(0x2a8b, float:1.5262E-41)
            java.lang.String r6 = "&lEg;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lesseqqgtr;"
            r0.addReference(r4, r6)
            r4 = 10892(0x2a8c, float:1.5263E-41)
            java.lang.String r6 = "&gEl;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&gtreqqless;"
            r0.addReference(r4, r6)
            r4 = 10893(0x2a8d, float:1.5264E-41)
            java.lang.String r6 = "&lsime;"
            r0.addReference(r4, r6)
            r4 = 10894(0x2a8e, float:1.5266E-41)
            java.lang.String r6 = "&gsime;"
            r0.addReference(r4, r6)
            r4 = 10895(0x2a8f, float:1.5267E-41)
            java.lang.String r6 = "&lsimg;"
            r0.addReference(r4, r6)
            r4 = 10896(0x2a90, float:1.5269E-41)
            java.lang.String r6 = "&gsiml;"
            r0.addReference(r4, r6)
            r4 = 10897(0x2a91, float:1.527E-41)
            java.lang.String r6 = "&lgE;"
            r0.addReference(r4, r6)
            r4 = 10898(0x2a92, float:1.5271E-41)
            java.lang.String r6 = "&glE;"
            r0.addReference(r4, r6)
            r4 = 10899(0x2a93, float:1.5273E-41)
            java.lang.String r6 = "&lesges;"
            r0.addReference(r4, r6)
            r4 = 10900(0x2a94, float:1.5274E-41)
            java.lang.String r6 = "&gesles;"
            r0.addReference(r4, r6)
            r4 = 10901(0x2a95, float:1.5276E-41)
            java.lang.String r6 = "&els;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&eqslantless;"
            r0.addReference(r4, r6)
            r4 = 10902(0x2a96, float:1.5277E-41)
            java.lang.String r6 = "&egs;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&eqslantgtr;"
            r0.addReference(r4, r6)
            r4 = 10903(0x2a97, float:1.5278E-41)
            java.lang.String r6 = "&elsdot;"
            r0.addReference(r4, r6)
            r4 = 10904(0x2a98, float:1.528E-41)
            java.lang.String r6 = "&egsdot;"
            r0.addReference(r4, r6)
            r4 = 10905(0x2a99, float:1.5281E-41)
            java.lang.String r6 = "&el;"
            r0.addReference(r4, r6)
            r4 = 10906(0x2a9a, float:1.5283E-41)
            java.lang.String r6 = "&eg;"
            r0.addReference(r4, r6)
            r4 = 10909(0x2a9d, float:1.5287E-41)
            java.lang.String r6 = "&siml;"
            r0.addReference(r4, r6)
            r4 = 10910(0x2a9e, float:1.5288E-41)
            java.lang.String r6 = "&simg;"
            r0.addReference(r4, r6)
            r4 = 10911(0x2a9f, float:1.529E-41)
            java.lang.String r6 = "&simlE;"
            r0.addReference(r4, r6)
            r4 = 10912(0x2aa0, float:1.5291E-41)
            java.lang.String r6 = "&simgE;"
            r0.addReference(r4, r6)
            r4 = 10913(0x2aa1, float:1.5292E-41)
            java.lang.String r6 = "&LessLess;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotNestedLessLess;"
            r0.addReference(r4, r7, r6)
            r4 = 10914(0x2aa2, float:1.5294E-41)
            java.lang.String r6 = "&GreaterGreater;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&NotNestedGreaterGreater;"
            r0.addReference(r4, r7, r6)
            r4 = 10916(0x2aa4, float:1.5297E-41)
            java.lang.String r6 = "&glj;"
            r0.addReference(r4, r6)
            r4 = 10917(0x2aa5, float:1.5298E-41)
            java.lang.String r6 = "&gla;"
            r0.addReference(r4, r6)
            r4 = 10918(0x2aa6, float:1.53E-41)
            java.lang.String r6 = "&ltcc;"
            r0.addReference(r4, r6)
            r4 = 10919(0x2aa7, float:1.5301E-41)
            java.lang.String r6 = "&gtcc;"
            r0.addReference(r4, r6)
            r4 = 10920(0x2aa8, float:1.5302E-41)
            java.lang.String r6 = "&lescc;"
            r0.addReference(r4, r6)
            r4 = 10921(0x2aa9, float:1.5304E-41)
            java.lang.String r6 = "&gescc;"
            r0.addReference(r4, r6)
            r4 = 10922(0x2aaa, float:1.5305E-41)
            java.lang.String r6 = "&smt;"
            r0.addReference(r4, r6)
            r4 = 10923(0x2aab, float:1.5306E-41)
            java.lang.String r6 = "&lat;"
            r0.addReference(r4, r6)
            r4 = 10924(0x2aac, float:1.5308E-41)
            java.lang.String r6 = "&smte;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&smtes;"
            r0.addReference(r4, r8, r6)
            r4 = 10925(0x2aad, float:1.5309E-41)
            java.lang.String r6 = "&late;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&lates;"
            r0.addReference(r4, r8, r6)
            r4 = 10926(0x2aae, float:1.531E-41)
            java.lang.String r6 = "&bumpE;"
            r0.addReference(r4, r6)
            r4 = 10927(0x2aaf, float:1.5312E-41)
            java.lang.String r6 = "&pre;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&preceq;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&PrecedesEqual;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&npre;"
            r0.addReference(r4, r7, r6)
            java.lang.String r6 = "&npreceq;"
            r0.addReference(r4, r7, r6)
            java.lang.String r6 = "&NotPrecedesEqual;"
            r0.addReference(r4, r7, r6)
            r4 = 10928(0x2ab0, float:1.5313E-41)
            java.lang.String r6 = "&sce;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&succeq;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&SucceedsEqual;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&nsce;"
            r0.addReference(r4, r7, r6)
            java.lang.String r6 = "&nsucceq;"
            r0.addReference(r4, r7, r6)
            java.lang.String r6 = "&NotSucceedsEqual;"
            r0.addReference(r4, r7, r6)
            r4 = 10931(0x2ab3, float:1.5318E-41)
            java.lang.String r6 = "&prE;"
            r0.addReference(r4, r6)
            r4 = 10932(0x2ab4, float:1.5319E-41)
            java.lang.String r6 = "&scE;"
            r0.addReference(r4, r6)
            r4 = 10933(0x2ab5, float:1.532E-41)
            java.lang.String r6 = "&precneqq;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&prnE;"
            r0.addReference(r4, r6)
            r4 = 10934(0x2ab6, float:1.5322E-41)
            java.lang.String r6 = "&scnE;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&succneqq;"
            r0.addReference(r4, r6)
            r4 = 10935(0x2ab7, float:1.5323E-41)
            java.lang.String r6 = "&prap;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&precapprox;"
            r0.addReference(r4, r6)
            r4 = 10936(0x2ab8, float:1.5325E-41)
            java.lang.String r6 = "&scap;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&succapprox;"
            r0.addReference(r4, r6)
            r4 = 10937(0x2ab9, float:1.5326E-41)
            java.lang.String r6 = "&precnapprox;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&prnap;"
            r0.addReference(r4, r6)
            r4 = 10938(0x2aba, float:1.5327E-41)
            java.lang.String r6 = "&scnap;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&succnapprox;"
            r0.addReference(r4, r6)
            r4 = 10939(0x2abb, float:1.5329E-41)
            java.lang.String r6 = "&Pr;"
            r0.addReference(r4, r6)
            r4 = 10940(0x2abc, float:1.533E-41)
            java.lang.String r6 = "&Sc;"
            r0.addReference(r4, r6)
            r4 = 10941(0x2abd, float:1.5332E-41)
            java.lang.String r6 = "&subdot;"
            r0.addReference(r4, r6)
            r4 = 10942(0x2abe, float:1.5333E-41)
            java.lang.String r6 = "&supdot;"
            r0.addReference(r4, r6)
            r4 = 10943(0x2abf, float:1.5334E-41)
            java.lang.String r6 = "&subplus;"
            r0.addReference(r4, r6)
            r4 = 10944(0x2ac0, float:1.5336E-41)
            java.lang.String r6 = "&supplus;"
            r0.addReference(r4, r6)
            r4 = 10945(0x2ac1, float:1.5337E-41)
            java.lang.String r6 = "&submult;"
            r0.addReference(r4, r6)
            r4 = 10946(0x2ac2, float:1.5339E-41)
            java.lang.String r6 = "&supmult;"
            r0.addReference(r4, r6)
            r4 = 10947(0x2ac3, float:1.534E-41)
            java.lang.String r6 = "&subedot;"
            r0.addReference(r4, r6)
            r4 = 10948(0x2ac4, float:1.5341E-41)
            java.lang.String r6 = "&supedot;"
            r0.addReference(r4, r6)
            r4 = 10949(0x2ac5, float:1.5343E-41)
            java.lang.String r6 = "&subE;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&subseteqq;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&nsubE;"
            r0.addReference(r4, r7, r6)
            java.lang.String r6 = "&nsubseteqq;"
            r0.addReference(r4, r7, r6)
            r4 = 10950(0x2ac6, float:1.5344E-41)
            java.lang.String r6 = "&supE;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&supseteqq;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&nsupE;"
            r0.addReference(r4, r7, r6)
            java.lang.String r6 = "&nsupseteqq;"
            r0.addReference(r4, r7, r6)
            r4 = 10951(0x2ac7, float:1.5346E-41)
            java.lang.String r6 = "&subsim;"
            r0.addReference(r4, r6)
            r4 = 10952(0x2ac8, float:1.5347E-41)
            java.lang.String r6 = "&supsim;"
            r0.addReference(r4, r6)
            r4 = 10955(0x2acb, float:1.5351E-41)
            java.lang.String r6 = "&subnE;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&subsetneqq;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&varsubsetneqq;"
            r0.addReference(r4, r8, r6)
            java.lang.String r6 = "&vsubnE;"
            r0.addReference(r4, r8, r6)
            r4 = 10956(0x2acc, float:1.5353E-41)
            java.lang.String r6 = "&supnE;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&supsetneqq;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&varsupsetneqq;"
            r0.addReference(r4, r8, r6)
            java.lang.String r6 = "&vsupnE;"
            r0.addReference(r4, r8, r6)
            r4 = 10959(0x2acf, float:1.5357E-41)
            java.lang.String r6 = "&csub;"
            r0.addReference(r4, r6)
            r4 = 10960(0x2ad0, float:1.5358E-41)
            java.lang.String r6 = "&csup;"
            r0.addReference(r4, r6)
            r4 = 10961(0x2ad1, float:1.536E-41)
            java.lang.String r6 = "&csube;"
            r0.addReference(r4, r6)
            r4 = 10962(0x2ad2, float:1.5361E-41)
            java.lang.String r6 = "&csupe;"
            r0.addReference(r4, r6)
            r4 = 10963(0x2ad3, float:1.5362E-41)
            java.lang.String r6 = "&subsup;"
            r0.addReference(r4, r6)
            r4 = 10964(0x2ad4, float:1.5364E-41)
            java.lang.String r6 = "&supsub;"
            r0.addReference(r4, r6)
            r4 = 10965(0x2ad5, float:1.5365E-41)
            java.lang.String r6 = "&subsub;"
            r0.addReference(r4, r6)
            r4 = 10966(0x2ad6, float:1.5367E-41)
            java.lang.String r6 = "&supsup;"
            r0.addReference(r4, r6)
            r4 = 10967(0x2ad7, float:1.5368E-41)
            java.lang.String r6 = "&suphsub;"
            r0.addReference(r4, r6)
            r4 = 10968(0x2ad8, float:1.537E-41)
            java.lang.String r6 = "&supdsub;"
            r0.addReference(r4, r6)
            r4 = 10969(0x2ad9, float:1.5371E-41)
            java.lang.String r6 = "&forkv;"
            r0.addReference(r4, r6)
            r4 = 10970(0x2ada, float:1.5372E-41)
            java.lang.String r6 = "&topfork;"
            r0.addReference(r4, r6)
            r4 = 10971(0x2adb, float:1.5374E-41)
            java.lang.String r6 = "&mlcp;"
            r0.addReference(r4, r6)
            r4 = 10980(0x2ae4, float:1.5386E-41)
            java.lang.String r6 = "&Dashv;"
            r0.addReference(r4, r6)
            java.lang.String r6 = "&DoubleLeftTee;"
            r0.addReference(r4, r6)
            r4 = 10982(0x2ae6, float:1.5389E-41)
            java.lang.String r6 = "&Vdashl;"
            r0.addReference(r4, r6)
            r4 = 10983(0x2ae7, float:1.539E-41)
            java.lang.String r6 = "&Barv;"
            r0.addReference(r4, r6)
            r4 = 10984(0x2ae8, float:1.5392E-41)
            java.lang.String r6 = "&vBar;"
            r0.addReference(r4, r6)
            r4 = 10985(0x2ae9, float:1.5393E-41)
            java.lang.String r6 = "&vBarv;"
            r0.addReference(r4, r6)
            r4 = 10987(0x2aeb, float:1.5396E-41)
            java.lang.String r6 = "&Vbar;"
            r0.addReference(r4, r6)
            r4 = 10988(0x2aec, float:1.5397E-41)
            java.lang.String r6 = "&Not;"
            r0.addReference(r4, r6)
            r4 = 10989(0x2aed, float:1.5399E-41)
            java.lang.String r6 = "&bNot;"
            r0.addReference(r4, r6)
            r4 = 10990(0x2aee, float:1.54E-41)
            java.lang.String r6 = "&rnmid;"
            r0.addReference(r4, r6)
            r4 = 10991(0x2aef, float:1.5402E-41)
            java.lang.String r6 = "&cirmid;"
            r0.addReference(r4, r6)
            r4 = 10992(0x2af0, float:1.5403E-41)
            java.lang.String r6 = "&midcir;"
            r0.addReference(r4, r6)
            r4 = 10993(0x2af1, float:1.5404E-41)
            java.lang.String r6 = "&topcir;"
            r0.addReference(r4, r6)
            r4 = 10994(0x2af2, float:1.5406E-41)
            java.lang.String r6 = "&nhpar;"
            r0.addReference(r4, r6)
            r4 = 10995(0x2af3, float:1.5407E-41)
            java.lang.String r6 = "&parsim;"
            r0.addReference(r4, r6)
            r4 = 11005(0x2afd, float:1.5421E-41)
            java.lang.String r6 = "&parsl;"
            r0.addReference(r4, r6)
            r6 = 8421(0x20e5, float:1.18E-41)
            java.lang.String r7 = "&nparsl;"
            r0.addReference(r4, r6, r7)
            r4 = 64256(0xfb00, float:9.0042E-41)
            java.lang.String r6 = "&fflig;"
            r0.addReference(r4, r6)
            r4 = 64257(0xfb01, float:9.0043E-41)
            java.lang.String r6 = "&filig;"
            r0.addReference(r4, r6)
            r4 = 64258(0xfb02, float:9.0045E-41)
            java.lang.String r6 = "&fllig;"
            r0.addReference(r4, r6)
            r4 = 64259(0xfb03, float:9.0046E-41)
            java.lang.String r6 = "&ffilig;"
            r0.addReference(r4, r6)
            r4 = 64260(0xfb04, float:9.0047E-41)
            java.lang.String r6 = "&ffllig;"
            r0.addReference(r4, r6)
            r4 = 119964(0x1d49c, float:1.68105E-40)
            java.lang.String r6 = "&Ascr;"
            r0.addReference(r4, r6)
            r4 = 119966(0x1d49e, float:1.68108E-40)
            java.lang.String r6 = "&Cscr;"
            r0.addReference(r4, r6)
            r4 = 119967(0x1d49f, float:1.6811E-40)
            java.lang.String r6 = "&Dscr;"
            r0.addReference(r4, r6)
            r4 = 119970(0x1d4a2, float:1.68114E-40)
            java.lang.String r6 = "&Gscr;"
            r0.addReference(r4, r6)
            r4 = 119973(0x1d4a5, float:1.68118E-40)
            java.lang.String r6 = "&Jscr;"
            r0.addReference(r4, r6)
            r4 = 119974(0x1d4a6, float:1.6812E-40)
            java.lang.String r6 = "&Kscr;"
            r0.addReference(r4, r6)
            r4 = 119977(0x1d4a9, float:1.68124E-40)
            java.lang.String r6 = "&Nscr;"
            r0.addReference(r4, r6)
            r4 = 119978(0x1d4aa, float:1.68125E-40)
            java.lang.String r6 = "&Oscr;"
            r0.addReference(r4, r6)
            r4 = 119979(0x1d4ab, float:1.68126E-40)
            java.lang.String r6 = "&Pscr;"
            r0.addReference(r4, r6)
            r4 = 119980(0x1d4ac, float:1.68128E-40)
            java.lang.String r6 = "&Qscr;"
            r0.addReference(r4, r6)
            r4 = 119982(0x1d4ae, float:1.6813E-40)
            java.lang.String r6 = "&Sscr;"
            r0.addReference(r4, r6)
            r4 = 119983(0x1d4af, float:1.68132E-40)
            java.lang.String r6 = "&Tscr;"
            r0.addReference(r4, r6)
            r4 = 119984(0x1d4b0, float:1.68133E-40)
            java.lang.String r6 = "&Uscr;"
            r0.addReference(r4, r6)
            r4 = 119985(0x1d4b1, float:1.68135E-40)
            java.lang.String r6 = "&Vscr;"
            r0.addReference(r4, r6)
            r4 = 119986(0x1d4b2, float:1.68136E-40)
            java.lang.String r6 = "&Wscr;"
            r0.addReference(r4, r6)
            r4 = 119987(0x1d4b3, float:1.68138E-40)
            java.lang.String r6 = "&Xscr;"
            r0.addReference(r4, r6)
            r4 = 119988(0x1d4b4, float:1.68139E-40)
            java.lang.String r6 = "&Yscr;"
            r0.addReference(r4, r6)
            r4 = 119989(0x1d4b5, float:1.6814E-40)
            java.lang.String r6 = "&Zscr;"
            r0.addReference(r4, r6)
            r4 = 119990(0x1d4b6, float:1.68142E-40)
            java.lang.String r6 = "&ascr;"
            r0.addReference(r4, r6)
            r4 = 119991(0x1d4b7, float:1.68143E-40)
            java.lang.String r6 = "&bscr;"
            r0.addReference(r4, r6)
            r4 = 119992(0x1d4b8, float:1.68145E-40)
            java.lang.String r6 = "&cscr;"
            r0.addReference(r4, r6)
            r4 = 119993(0x1d4b9, float:1.68146E-40)
            java.lang.String r6 = "&dscr;"
            r0.addReference(r4, r6)
            r4 = 119995(0x1d4bb, float:1.68149E-40)
            java.lang.String r6 = "&fscr;"
            r0.addReference(r4, r6)
            r4 = 119997(0x1d4bd, float:1.68152E-40)
            java.lang.String r6 = "&hscr;"
            r0.addReference(r4, r6)
            r4 = 119998(0x1d4be, float:1.68153E-40)
            java.lang.String r6 = "&iscr;"
            r0.addReference(r4, r6)
            r4 = 119999(0x1d4bf, float:1.68154E-40)
            java.lang.String r6 = "&jscr;"
            r0.addReference(r4, r6)
            r4 = 120000(0x1d4c0, float:1.68156E-40)
            java.lang.String r6 = "&kscr;"
            r0.addReference(r4, r6)
            r4 = 120001(0x1d4c1, float:1.68157E-40)
            java.lang.String r6 = "&lscr;"
            r0.addReference(r4, r6)
            r4 = 120002(0x1d4c2, float:1.68159E-40)
            java.lang.String r6 = "&mscr;"
            r0.addReference(r4, r6)
            r4 = 120003(0x1d4c3, float:1.6816E-40)
            java.lang.String r6 = "&nscr;"
            r0.addReference(r4, r6)
            r4 = 120005(0x1d4c5, float:1.68163E-40)
            java.lang.String r6 = "&pscr;"
            r0.addReference(r4, r6)
            r4 = 120006(0x1d4c6, float:1.68164E-40)
            java.lang.String r6 = "&qscr;"
            r0.addReference(r4, r6)
            r4 = 120007(0x1d4c7, float:1.68166E-40)
            java.lang.String r6 = "&rscr;"
            r0.addReference(r4, r6)
            r4 = 120008(0x1d4c8, float:1.68167E-40)
            java.lang.String r6 = "&sscr;"
            r0.addReference(r4, r6)
            r4 = 120009(0x1d4c9, float:1.68168E-40)
            java.lang.String r6 = "&tscr;"
            r0.addReference(r4, r6)
            r4 = 120010(0x1d4ca, float:1.6817E-40)
            java.lang.String r6 = "&uscr;"
            r0.addReference(r4, r6)
            r4 = 120011(0x1d4cb, float:1.68171E-40)
            java.lang.String r6 = "&vscr;"
            r0.addReference(r4, r6)
            r4 = 120012(0x1d4cc, float:1.68173E-40)
            java.lang.String r6 = "&wscr;"
            r0.addReference(r4, r6)
            r4 = 120013(0x1d4cd, float:1.68174E-40)
            java.lang.String r6 = "&xscr;"
            r0.addReference(r4, r6)
            r4 = 120014(0x1d4ce, float:1.68175E-40)
            java.lang.String r6 = "&yscr;"
            r0.addReference(r4, r6)
            r4 = 120015(0x1d4cf, float:1.68177E-40)
            java.lang.String r6 = "&zscr;"
            r0.addReference(r4, r6)
            r4 = 120068(0x1d504, float:1.68251E-40)
            java.lang.String r6 = "&Afr;"
            r0.addReference(r4, r6)
            r4 = 120069(0x1d505, float:1.68253E-40)
            java.lang.String r6 = "&Bfr;"
            r0.addReference(r4, r6)
            r4 = 120071(0x1d507, float:1.68255E-40)
            java.lang.String r6 = "&Dfr;"
            r0.addReference(r4, r6)
            r4 = 120072(0x1d508, float:1.68257E-40)
            java.lang.String r6 = "&Efr;"
            r0.addReference(r4, r6)
            r4 = 120073(0x1d509, float:1.68258E-40)
            java.lang.String r6 = "&Ffr;"
            r0.addReference(r4, r6)
            r4 = 120074(0x1d50a, float:1.6826E-40)
            java.lang.String r6 = "&Gfr;"
            r0.addReference(r4, r6)
            r4 = 120077(0x1d50d, float:1.68264E-40)
            java.lang.String r6 = "&Jfr;"
            r0.addReference(r4, r6)
            r4 = 120078(0x1d50e, float:1.68265E-40)
            java.lang.String r6 = "&Kfr;"
            r0.addReference(r4, r6)
            r4 = 120079(0x1d50f, float:1.68267E-40)
            java.lang.String r6 = "&Lfr;"
            r0.addReference(r4, r6)
            r4 = 120080(0x1d510, float:1.68268E-40)
            java.lang.String r6 = "&Mfr;"
            r0.addReference(r4, r6)
            r4 = 120081(0x1d511, float:1.6827E-40)
            java.lang.String r6 = "&Nfr;"
            r0.addReference(r4, r6)
            r4 = 120082(0x1d512, float:1.68271E-40)
            java.lang.String r6 = "&Ofr;"
            r0.addReference(r4, r6)
            r4 = 120083(0x1d513, float:1.68272E-40)
            java.lang.String r6 = "&Pfr;"
            r0.addReference(r4, r6)
            r4 = 120084(0x1d514, float:1.68274E-40)
            java.lang.String r6 = "&Qfr;"
            r0.addReference(r4, r6)
            r4 = 120086(0x1d516, float:1.68276E-40)
            java.lang.String r6 = "&Sfr;"
            r0.addReference(r4, r6)
            r4 = 120087(0x1d517, float:1.68278E-40)
            java.lang.String r6 = "&Tfr;"
            r0.addReference(r4, r6)
            r4 = 120088(0x1d518, float:1.68279E-40)
            java.lang.String r6 = "&Ufr;"
            r0.addReference(r4, r6)
            r4 = 120089(0x1d519, float:1.6828E-40)
            java.lang.String r6 = "&Vfr;"
            r0.addReference(r4, r6)
            r4 = 120090(0x1d51a, float:1.68282E-40)
            java.lang.String r6 = "&Wfr;"
            r0.addReference(r4, r6)
            r4 = 120091(0x1d51b, float:1.68283E-40)
            java.lang.String r6 = "&Xfr;"
            r0.addReference(r4, r6)
            r4 = 120092(0x1d51c, float:1.68285E-40)
            java.lang.String r6 = "&Yfr;"
            r0.addReference(r4, r6)
            r4 = 120094(0x1d51e, float:1.68288E-40)
            java.lang.String r6 = "&afr;"
            r0.addReference(r4, r6)
            r4 = 120095(0x1d51f, float:1.68289E-40)
            java.lang.String r6 = "&bfr;"
            r0.addReference(r4, r6)
            r4 = 120096(0x1d520, float:1.6829E-40)
            java.lang.String r6 = "&cfr;"
            r0.addReference(r4, r6)
            r4 = 120097(0x1d521, float:1.68292E-40)
            java.lang.String r6 = "&dfr;"
            r0.addReference(r4, r6)
            r4 = 120098(0x1d522, float:1.68293E-40)
            java.lang.String r6 = "&efr;"
            r0.addReference(r4, r6)
            r4 = 120099(0x1d523, float:1.68295E-40)
            java.lang.String r6 = "&ffr;"
            r0.addReference(r4, r6)
            r4 = 120100(0x1d524, float:1.68296E-40)
            java.lang.String r6 = "&gfr;"
            r0.addReference(r4, r6)
            r4 = 120101(0x1d525, float:1.68297E-40)
            java.lang.String r6 = "&hfr;"
            r0.addReference(r4, r6)
            r4 = 120102(0x1d526, float:1.68299E-40)
            java.lang.String r6 = "&ifr;"
            r0.addReference(r4, r6)
            r4 = 120103(0x1d527, float:1.683E-40)
            java.lang.String r6 = "&jfr;"
            r0.addReference(r4, r6)
            r4 = 120104(0x1d528, float:1.68302E-40)
            java.lang.String r6 = "&kfr;"
            r0.addReference(r4, r6)
            r4 = 120105(0x1d529, float:1.68303E-40)
            java.lang.String r6 = "&lfr;"
            r0.addReference(r4, r6)
            r4 = 120106(0x1d52a, float:1.68304E-40)
            java.lang.String r6 = "&mfr;"
            r0.addReference(r4, r6)
            r4 = 120107(0x1d52b, float:1.68306E-40)
            java.lang.String r6 = "&nfr;"
            r0.addReference(r4, r6)
            r4 = 120108(0x1d52c, float:1.68307E-40)
            java.lang.String r6 = "&ofr;"
            r0.addReference(r4, r6)
            r4 = 120109(0x1d52d, float:1.68309E-40)
            java.lang.String r6 = "&pfr;"
            r0.addReference(r4, r6)
            r4 = 120110(0x1d52e, float:1.6831E-40)
            java.lang.String r6 = "&qfr;"
            r0.addReference(r4, r6)
            r4 = 120111(0x1d52f, float:1.68311E-40)
            java.lang.String r6 = "&rfr;"
            r0.addReference(r4, r6)
            r4 = 120112(0x1d530, float:1.68313E-40)
            java.lang.String r6 = "&sfr;"
            r0.addReference(r4, r6)
            r4 = 120113(0x1d531, float:1.68314E-40)
            java.lang.String r6 = "&tfr;"
            r0.addReference(r4, r6)
            r4 = 120114(0x1d532, float:1.68316E-40)
            java.lang.String r6 = "&ufr;"
            r0.addReference(r4, r6)
            r4 = 120115(0x1d533, float:1.68317E-40)
            java.lang.String r6 = "&vfr;"
            r0.addReference(r4, r6)
            r4 = 120116(0x1d534, float:1.68318E-40)
            java.lang.String r6 = "&wfr;"
            r0.addReference(r4, r6)
            r4 = 120117(0x1d535, float:1.6832E-40)
            java.lang.String r6 = "&xfr;"
            r0.addReference(r4, r6)
            r4 = 120118(0x1d536, float:1.68321E-40)
            java.lang.String r6 = "&yfr;"
            r0.addReference(r4, r6)
            r4 = 120119(0x1d537, float:1.68323E-40)
            java.lang.String r6 = "&zfr;"
            r0.addReference(r4, r6)
            r4 = 120120(0x1d538, float:1.68324E-40)
            java.lang.String r6 = "&Aopf;"
            r0.addReference(r4, r6)
            r4 = 120121(0x1d539, float:1.68325E-40)
            java.lang.String r6 = "&Bopf;"
            r0.addReference(r4, r6)
            r4 = 120123(0x1d53b, float:1.68328E-40)
            java.lang.String r6 = "&Dopf;"
            r0.addReference(r4, r6)
            r4 = 120124(0x1d53c, float:1.6833E-40)
            java.lang.String r6 = "&Eopf;"
            r0.addReference(r4, r6)
            r4 = 120125(0x1d53d, float:1.68331E-40)
            java.lang.String r6 = "&Fopf;"
            r0.addReference(r4, r6)
            r4 = 120126(0x1d53e, float:1.68332E-40)
            java.lang.String r6 = "&Gopf;"
            r0.addReference(r4, r6)
            r4 = 120128(0x1d540, float:1.68335E-40)
            java.lang.String r6 = "&Iopf;"
            r0.addReference(r4, r6)
            r4 = 120129(0x1d541, float:1.68337E-40)
            java.lang.String r6 = "&Jopf;"
            r0.addReference(r4, r6)
            r4 = 120130(0x1d542, float:1.68338E-40)
            java.lang.String r6 = "&Kopf;"
            r0.addReference(r4, r6)
            r4 = 120131(0x1d543, float:1.6834E-40)
            java.lang.String r6 = "&Lopf;"
            r0.addReference(r4, r6)
            r4 = 120132(0x1d544, float:1.68341E-40)
            java.lang.String r6 = "&Mopf;"
            r0.addReference(r4, r6)
            r4 = 120134(0x1d546, float:1.68344E-40)
            java.lang.String r6 = "&Oopf;"
            r0.addReference(r4, r6)
            r4 = 120138(0x1d54a, float:1.68349E-40)
            java.lang.String r6 = "&Sopf;"
            r0.addReference(r4, r6)
            r4 = 120139(0x1d54b, float:1.6835E-40)
            java.lang.String r6 = "&Topf;"
            r0.addReference(r4, r6)
            r4 = 120140(0x1d54c, float:1.68352E-40)
            java.lang.String r6 = "&Uopf;"
            r0.addReference(r4, r6)
            r4 = 120141(0x1d54d, float:1.68353E-40)
            java.lang.String r6 = "&Vopf;"
            r0.addReference(r4, r6)
            r4 = 120142(0x1d54e, float:1.68355E-40)
            java.lang.String r6 = "&Wopf;"
            r0.addReference(r4, r6)
            r4 = 120143(0x1d54f, float:1.68356E-40)
            java.lang.String r6 = "&Xopf;"
            r0.addReference(r4, r6)
            r4 = 120144(0x1d550, float:1.68358E-40)
            java.lang.String r6 = "&Yopf;"
            r0.addReference(r4, r6)
            r4 = 120146(0x1d552, float:1.6836E-40)
            java.lang.String r6 = "&aopf;"
            r0.addReference(r4, r6)
            r4 = 120147(0x1d553, float:1.68362E-40)
            java.lang.String r6 = "&bopf;"
            r0.addReference(r4, r6)
            r4 = 120148(0x1d554, float:1.68363E-40)
            java.lang.String r6 = "&copf;"
            r0.addReference(r4, r6)
            r4 = 120149(0x1d555, float:1.68365E-40)
            java.lang.String r6 = "&dopf;"
            r0.addReference(r4, r6)
            r4 = 120150(0x1d556, float:1.68366E-40)
            java.lang.String r6 = "&eopf;"
            r0.addReference(r4, r6)
            r4 = 120151(0x1d557, float:1.68367E-40)
            java.lang.String r6 = "&fopf;"
            r0.addReference(r4, r6)
            r4 = 120152(0x1d558, float:1.68369E-40)
            java.lang.String r6 = "&gopf;"
            r0.addReference(r4, r6)
            r4 = 120153(0x1d559, float:1.6837E-40)
            java.lang.String r6 = "&hopf;"
            r0.addReference(r4, r6)
            r4 = 120154(0x1d55a, float:1.68372E-40)
            java.lang.String r6 = "&iopf;"
            r0.addReference(r4, r6)
            r4 = 120155(0x1d55b, float:1.68373E-40)
            java.lang.String r6 = "&jopf;"
            r0.addReference(r4, r6)
            r4 = 120156(0x1d55c, float:1.68374E-40)
            java.lang.String r6 = "&kopf;"
            r0.addReference(r4, r6)
            r4 = 120157(0x1d55d, float:1.68376E-40)
            java.lang.String r6 = "&lopf;"
            r0.addReference(r4, r6)
            r4 = 120158(0x1d55e, float:1.68377E-40)
            java.lang.String r6 = "&mopf;"
            r0.addReference(r4, r6)
            r4 = 120159(0x1d55f, float:1.68379E-40)
            java.lang.String r6 = "&nopf;"
            r0.addReference(r4, r6)
            r4 = 120160(0x1d560, float:1.6838E-40)
            java.lang.String r6 = "&oopf;"
            r0.addReference(r4, r6)
            r4 = 120161(0x1d561, float:1.68381E-40)
            java.lang.String r6 = "&popf;"
            r0.addReference(r4, r6)
            r4 = 120162(0x1d562, float:1.68383E-40)
            java.lang.String r6 = "&qopf;"
            r0.addReference(r4, r6)
            r4 = 120163(0x1d563, float:1.68384E-40)
            java.lang.String r6 = "&ropf;"
            r0.addReference(r4, r6)
            r4 = 120164(0x1d564, float:1.68386E-40)
            java.lang.String r6 = "&sopf;"
            r0.addReference(r4, r6)
            r4 = 120165(0x1d565, float:1.68387E-40)
            java.lang.String r6 = "&topf;"
            r0.addReference(r4, r6)
            r4 = 120166(0x1d566, float:1.68388E-40)
            java.lang.String r6 = "&uopf;"
            r0.addReference(r4, r6)
            r4 = 120167(0x1d567, float:1.6839E-40)
            java.lang.String r6 = "&vopf;"
            r0.addReference(r4, r6)
            r4 = 120168(0x1d568, float:1.68391E-40)
            java.lang.String r6 = "&wopf;"
            r0.addReference(r4, r6)
            r4 = 120169(0x1d569, float:1.68393E-40)
            java.lang.String r6 = "&xopf;"
            r0.addReference(r4, r6)
            r4 = 120170(0x1d56a, float:1.68394E-40)
            java.lang.String r6 = "&yopf;"
            r0.addReference(r4, r6)
            r4 = 120171(0x1d56b, float:1.68395E-40)
            java.lang.String r6 = "&zopf;"
            r0.addReference(r4, r6)
            r4 = 129(0x81, float:1.81E-43)
            byte[] r4 = new byte[r4]
            r6 = 3
            java.util.Arrays.fill(r4, r6)
            r6 = 65
        L_0x378f:
            r7 = 90
            if (r6 > r7) goto L_0x379a
            r7 = 4
            r4[r6] = r7
            int r6 = r6 + 1
            char r6 = (char) r6
            goto L_0x378f
        L_0x379a:
            r6 = 97
        L_0x379c:
            r7 = 122(0x7a, float:1.71E-43)
            if (r6 > r7) goto L_0x37a7
            r7 = 4
            r4[r6] = r7
            int r6 = r6 + 1
            char r6 = (char) r6
            goto L_0x379c
        L_0x37a7:
            r6 = 48
        L_0x37a9:
            r7 = 57
            if (r6 > r7) goto L_0x37b4
            r7 = 4
            r4[r6] = r7
            int r6 = r6 + 1
            char r6 = (char) r6
            goto L_0x37a9
        L_0x37b4:
            r6 = 39
            r7 = 1
            r4[r6] = r7
            r6 = 0
            r4[r1] = r6
            r1 = 0
            r4[r3] = r1
            r4[r5] = r1
            r4[r2] = r1
            r1 = 128(0x80, float:1.794E-43)
            r2 = 2
            r4[r1] = r2
            org.unbescape.html.HtmlEscapeSymbols r1 = new org.unbescape.html.HtmlEscapeSymbols
            r1.<init>(r0, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.unbescape.html.Html5EscapeSymbolsInitializer.initializeHtml5():org.unbescape.html.HtmlEscapeSymbols");
    }

    private Html5EscapeSymbolsInitializer() {
    }
}
