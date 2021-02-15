package com.afollestad.date.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import java.io.Serializable;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000fH\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0015"}, d2 = {"Lcom/afollestad/date/view/DatePickerSavedState;", "Landroid/view/View$BaseSavedState;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "selectedDate", "Ljava/util/Calendar;", "superState", "Landroid/os/Parcelable;", "(Ljava/util/Calendar;Landroid/os/Parcelable;)V", "getSelectedDate", "()Ljava/util/Calendar;", "setSelectedDate", "(Ljava/util/Calendar;)V", "describeContents", "", "writeToParcel", "", "parcel", "flags", "CREATOR", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: DatePickerSavedState.kt */
public final class DatePickerSavedState extends View.BaseSavedState {
    public static final CREATOR CREATOR = new CREATOR((DefaultConstructorMarker) null);
    private Calendar selectedDate;

    public int describeContents() {
        return 0;
    }

    public final Calendar getSelectedDate() {
        return this.selectedDate;
    }

    public final void setSelectedDate(Calendar calendar) {
        this.selectedDate = calendar;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DatePickerSavedState(Parcel parcel) {
        super(parcel);
        Intrinsics.checkParameterIsNotNull(parcel, "source");
        Serializable readSerializable = parcel.readSerializable();
        this.selectedDate = (Calendar) (!(readSerializable instanceof Calendar) ? null : readSerializable);
    }

    public DatePickerSavedState(Calendar calendar, Parcelable parcelable) {
        super(parcelable);
        this.selectedDate = calendar;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        super.writeToParcel(parcel, i);
        parcel.writeSerializable(this.selectedDate);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/afollestad/date/view/DatePickerSavedState$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/afollestad/date/view/DatePickerSavedState;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/afollestad/date/view/DatePickerSavedState;", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
    /* compiled from: DatePickerSavedState.kt */
    public static final class CREATOR implements Parcelable.Creator<DatePickerSavedState> {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public DatePickerSavedState createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new DatePickerSavedState(parcel);
        }

        public DatePickerSavedState[] newArray(int i) {
            return new DatePickerSavedState[i];
        }
    }
}
