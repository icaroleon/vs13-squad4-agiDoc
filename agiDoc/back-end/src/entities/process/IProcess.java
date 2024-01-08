package entities.process;

import entities.competitor.Competitor;

public interface IProcess {
    boolean chooseContractor(Competitor competitor);
}
