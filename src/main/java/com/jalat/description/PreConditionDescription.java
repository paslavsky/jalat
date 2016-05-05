package com.jalat.description;

import com.jalat.VoidFunction;

/**
 * Pre-condition description
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public final class PreConditionDescription extends BaseDescription {
    public PreConditionDescription(String shortDescription, VoidFunction body) {
        super(shortDescription, body);
    }

}
