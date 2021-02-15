package com.appboy.models.cards;

import bo.app.bq;
import bo.app.c;
import bo.app.dm;
import com.appboy.enums.CardKey;
import com.appboy.enums.CardType;
import org.json.JSONObject;

public class ControlCard extends Card {
    public ControlCard(JSONObject jSONObject, CardKey.Provider provider, bq bqVar, dm dmVar, c cVar) {
        super(jSONObject, provider, bqVar, dmVar, cVar);
    }

    public CardType getCardType() {
        return CardType.CONTROL;
    }

    public String toString() {
        return "ControlCard{" + super.toString() + "}";
    }
}
