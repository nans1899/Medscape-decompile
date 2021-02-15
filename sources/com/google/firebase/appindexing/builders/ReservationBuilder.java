package com.google.firebase.appindexing.builders;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Date;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class ReservationBuilder extends IndexableBuilder<ReservationBuilder> {
    ReservationBuilder() {
        super("Reservation");
    }

    public final ReservationBuilder setStartDate(Date date) {
        Preconditions.checkNotNull(date);
        return (ReservationBuilder) put("startDate", date.getTime());
    }

    public final ReservationBuilder setPartySize(long j) {
        return (ReservationBuilder) put("partySize", j);
    }

    public final ReservationBuilder setReservationFor(LocalBusinessBuilder localBusinessBuilder) {
        return (ReservationBuilder) put("reservationFor", (S[]) new LocalBusinessBuilder[]{localBusinessBuilder});
    }
}
