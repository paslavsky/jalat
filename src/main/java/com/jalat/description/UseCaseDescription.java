package com.jalat.description;

import com.jalat.VoidFunction;
import org.junit.runner.Description;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Use-case description
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public final class UseCaseDescription extends BaseDescription implements CheckHolder {
    private Description jUnitDescription;
    private final List<CheckDescription> checks = new ArrayList<>();
    private PreConditionDescription preCondition;
    private PostConditionDescription postCondition;

    public UseCaseDescription(String name, VoidFunction body) {
        super(name, body);
    }

    public String getName() {
        return getShortDescription();
    }

    public void setJUnitDescription(Description jUnitDescription) {
        this.jUnitDescription = jUnitDescription;
    }

    public Description getJUnitDescription() {
        return jUnitDescription;
    }

    @Override
    public void addCheck(@Nonnull CheckDescription check) {
        checks.add(check);
    }

    @Nonnull
    @Override
    public Collection<CheckDescription> getChecks() {
        return Collections.unmodifiableCollection(checks);
    }

    public void setPreCondition(PreConditionDescription preCondition) {
        this.preCondition = preCondition;
    }

    public PreConditionDescription getPreCondition() {
        return preCondition;
    }

    public PostConditionDescription getPostCondition() {
        return postCondition;
    }

    public void setPostCondition(PostConditionDescription postCondition) {
        this.postCondition = postCondition;
    }
}
