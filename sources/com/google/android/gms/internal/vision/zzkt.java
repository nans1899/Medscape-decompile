package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzkt {
    static String zzd(zzgs zzgs) {
        zzks zzks = new zzks(zzgs);
        StringBuilder sb = new StringBuilder(zzks.size());
        for (int i = 0; i < zzks.size(); i++) {
            byte zzau = zzks.zzau(i);
            if (zzau == 34) {
                sb.append("\\\"");
            } else if (zzau == 39) {
                sb.append("\\'");
            } else if (zzau != 92) {
                switch (zzau) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (zzau >= 32 && zzau <= 126) {
                            sb.append((char) zzau);
                            break;
                        } else {
                            sb.append('\\');
                            sb.append((char) (((zzau >>> 6) & 3) + 48));
                            sb.append((char) (((zzau >>> 3) & 7) + 48));
                            sb.append((char) ((zzau & 7) + 48));
                            break;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
