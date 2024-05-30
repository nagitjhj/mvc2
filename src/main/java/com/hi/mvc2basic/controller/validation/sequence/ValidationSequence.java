package com.hi.mvc2basic.controller.validation.sequence;

import jakarta.validation.GroupSequence;

import static com.hi.mvc2basic.controller.validation.sequence.ValidationGroups.*;

@GroupSequence({ItemNameGroup.class, PriceGroup.class, QuantityGroup.class})
public interface ValidationSequence {
}
