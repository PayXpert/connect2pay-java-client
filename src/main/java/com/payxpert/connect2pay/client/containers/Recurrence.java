package com.payxpert.connect2pay.client.containers;

import com.payxpert.connect2pay.constants.SubscriptionType;
import com.payxpert.connect2pay.utils.Utils;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.MaxLength;

public class Recurrence {
    private SubscriptionType type;

    private Integer totalIterations;

    @MaxLength(8)
    @MatchPattern(pattern = "^[0-9]{8}$")
    private String expiry;

    private Integer frequency;

    public SubscriptionType getType() {
        return type;
    }

    public Recurrence setType(SubscriptionType type) {
        this.type = type;
        return this;
    }

    public Integer getTotalIterations() {
        return totalIterations;
    }

    public Recurrence setTotalIterations(Integer totalIterations) {
        this.totalIterations = totalIterations;
        return this;
    }

    public String getExpiry() {
        return expiry;
    }

    public Recurrence setExpiry(String expiry) {
        this.expiry = Utils.limitLength(expiry, 8);
        return this;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public Recurrence setFrequency(Integer frequency) {
        this.frequency = frequency;
        return this;
    }
}
