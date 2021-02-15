package com.medscape.android.db.model;

import android.content.Context;
import android.database.Cursor;
import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.drugs.helper.SearchHelper;
import com.wbmd.wbmddrugscommons.constants.Constants;
import java.io.Serializable;

public class Interaction implements Serializable {
    private static final long serialVersionUID = -5288562851786398004L;
    private String comment;
    private String direction;
    private String effect;
    private int interactionId;
    private int interactionType;
    private int mechId;
    private int modifiedStrengthId;
    private int objectId;
    private String objectName;
    private int strengthId;
    private int subjectId;
    private String subjectName;

    public int getInteractionId() {
        return this.interactionId;
    }

    public void setInteractionId(int i) {
        this.interactionId = i;
    }

    public int getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(int i) {
        this.subjectId = i;
    }

    public int getObjectId() {
        return this.objectId;
    }

    public void setObjectId(int i) {
        this.objectId = i;
    }

    public int getInteractionType() {
        return this.interactionType;
    }

    public void setInteractionType(int i) {
        this.interactionType = i;
    }

    public int getMechId() {
        return this.mechId;
    }

    public void setMechId(int i) {
        this.mechId = i;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String str) {
        this.direction = str;
    }

    public String getEffect() {
        return this.effect;
    }

    public void setEffect(String str) {
        this.effect = str;
    }

    public int getStrengthId() {
        return this.strengthId;
    }

    public void setStrengthId(int i) {
        this.strengthId = i;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public String getSubjectName() {
        return this.subjectName.substring(0, 1).toUpperCase() + this.subjectName.substring(1);
    }

    public void setSubjectName(String str) {
        this.subjectName = str;
    }

    public String getObjectName() {
        return this.objectName.substring(0, 1).toUpperCase() + this.objectName.substring(1);
    }

    public void setObjectName(String str) {
        this.objectName = str;
    }

    public String getHeader() {
        return getSubjectName() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getObjectName();
    }

    public String getContents(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        String str = this.effect;
        stringBuffer.append(this.subjectName);
        if (this.effect.equals("de")) {
            this.effect = " decreases effects of ";
        } else if (this.effect.equals("dl")) {
            this.effect = " decreases levels of ";
        } else if (this.effect.equals("dt")) {
            this.effect = " decreases toxicity of ";
        } else if (this.effect.equals("ie")) {
            this.effect = " increases effects of ";
        } else if (this.effect.equals("il")) {
            this.effect = " increases levels of ";
        } else if (this.effect.equals("it")) {
            this.effect = " increases toxicity of ";
        } else if (this.effect.equals("o")) {
            this.effect = ", ";
        } else if (this.effect.equals("")) {
            this.effect = " and ";
        }
        String mechScript = getMechScript(context);
        String strengthScript = getStrengthScript(context);
        String comment2 = getComment();
        if (this.effect.equals(" and ")) {
            if (this.direction.equals(SearchHelper.TYPE_DRUG)) {
                stringBuffer.append(" and " + this.objectName + " both decrease");
            } else if (this.direction.equals("EI")) {
                stringBuffer.append(" will increase the level or effect of" + this.objectName + " by");
            } else if (this.direction.equals("I")) {
                stringBuffer.append(" and " + this.objectName + " both increase");
            } else if (this.direction.equals(Constants.WBMDTugStringID)) {
                stringBuffer.append(" increases and " + this.objectName + " decreases");
            } else if (this.direction.equals("OD")) {
                stringBuffer.append(" will decrease the level or effect of " + this.objectName + " by");
            } else if (this.direction.equals("OI")) {
                stringBuffer.append(" will increase the level or effect of " + this.objectName + " by");
            } else if (this.direction.equals("SD")) {
                stringBuffer.append(" will decrease the level or effect of " + this.objectName + " by");
            } else if (this.direction.equals("SI")) {
                stringBuffer.append(" will increase the level or effect of " + this.objectName + " by");
            }
            if (!this.direction.equals(Constants.WBMDTugStringID)) {
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + mechScript + ".");
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + strengthScript + "");
                if (!comment2.equals("")) {
                    stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + comment2 + "");
                }
            } else {
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + mechScript + ". Effect of interaction is not clear, use caution. " + strengthScript + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + comment2 + "");
            }
        } else {
            if (this.direction.equals(ExifInterface.LONGITUDE_EAST)) {
                stringBuffer.append(", " + this.objectName + ".\r\n");
                if (!str.equals("o")) {
                    stringBuffer.append("Either" + this.effect + "the other");
                }
            } else {
                stringBuffer.append("" + this.effect + "");
                stringBuffer.append("" + this.objectName + "");
            }
            if (!str.equals("o")) {
                stringBuffer.append(" by " + mechScript + ".");
            } else if (this.direction.equals(ExifInterface.LONGITUDE_EAST)) {
                stringBuffer.append("" + mechScript + ".");
            } else {
                stringBuffer.append(". " + mechScript + ".");
            }
            stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + strengthScript + "");
            if (!comment2.equals("")) {
                if (this.mechId == 45) {
                    stringBuffer.append(" \r\nComment: " + comment2 + "");
                } else {
                    stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + comment2 + "");
                }
            }
        }
        return stringBuffer.toString();
    }

    public String getMechScript(Context context) {
        String str = "";
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT MechScript FROM tblMechanisms where MechID = ?", new String[]{String.valueOf(getMechId())});
            while (rawQuery.moveToNext()) {
                str = rawQuery.getString(0);
            }
            rawQuery.close();
            databaseHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getStrengthScript(Context context) {
        String str = "";
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT StrengthDesc FROM tblStrengths where StrengthID = ?", new String[]{String.valueOf(getStrengthId())});
            while (rawQuery.moveToNext()) {
                str = rawQuery.getString(0);
            }
            rawQuery.close();
            databaseHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public void setModifiedStrengthId(int i) {
        this.modifiedStrengthId = i;
    }

    public int getModifiedStrengthId() {
        return this.modifiedStrengthId;
    }
}
