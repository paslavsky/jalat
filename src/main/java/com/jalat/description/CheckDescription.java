package com.jalat.description;

import com.jalat.VoidFunction;

/**
 * Check description
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public class CheckDescription extends BaseDescription {
    public CheckDescription(String name, VoidFunction body) {
        super(name, body);
    }

    public String getName() {
        return getShortDescription();
    }
}
