package model.process;

import model.competitor.Competitor;

public interface IProcess {
    boolean chooseContractor(Competitor competitor);
}
