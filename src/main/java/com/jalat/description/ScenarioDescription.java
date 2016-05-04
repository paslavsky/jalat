package com.jalat.description;

import com.jalat.error.IncorectBehaviorDetectedException;
import com.jalat.util.InitializeOnlyOnce;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Scenario description
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public class ScenarioDescription implements InitializeOnlyOnce {
    private String name;
    private Collection<UseCaseDescription> useCases = new ArrayList<>();
    private PreConditionDescription preCondition;
    private PostConditionDescription postCondition;
    private CharSequence[] description;
    private long duration = -1;

    public void addUseCase(UseCaseDescription useCaseDescription) {
        useCases.add(useCaseDescription);
    }

    public Collection<UseCaseDescription> getUseCases() {
        return Collections.unmodifiableCollection(useCases);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PreConditionDescription getPreCondition() {
        return preCondition;
    }

    public PostConditionDescription getPostCondition() {
        return postCondition;
    }

    public void setPreCondition(PreConditionDescription preCondition) {
        this.preCondition = initOnce(preCondition, this.preCondition, () ->
                new IncorectBehaviorDetectedException("Allowed only one pre-condition"));
    }

    public void setPostCondition(PostConditionDescription postCondition) {
        this.postCondition = initOnce(postCondition, this.postCondition, () ->
                new IncorectBehaviorDetectedException("Allowed only one post-condition"));
    }

    public void setDescription(CharSequence[] description) {
        this.description = description;
    }

    public CharSequence[] getDescription() {
        return description;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
