package com.jalat.description;

import com.jalat.VoidFunction;

/**
 * Post-condition description
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public final class PostConditionDescription extends BaseDescription {
    public PostConditionDescription(String shortDescription, VoidFunction body) {
        super(shortDescription, body);
    }
}
