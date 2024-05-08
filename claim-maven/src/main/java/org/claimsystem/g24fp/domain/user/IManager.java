package org.claimsystem.g24fp.domain.user;

import java.util.ArrayList;
import java.util.List;

public class IManager extends Provider {
    private List<ISurveyor> surveyors;

    public IManager() {
        super();
        this.surveyors = new ArrayList<>();
    }

    public IManager(Provider p) {
        super(p.getUsername(), p.getProviderID(), p.getProviderName());
        this.surveyors = new ArrayList<>();
    }

    public List<ISurveyor> getSurveyors() {
        return surveyors;
    }

    public void setSurveyor(List<ISurveyor> surveyors) {
        this.surveyors = surveyors;
    }

    @Override
    public String toString() {
        return "IManager{" +
                super.toString() +
                '}';
    }
}
