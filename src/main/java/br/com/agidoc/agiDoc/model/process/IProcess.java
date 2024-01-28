package br.com.agidoc.agiDoc.model.process;

import br.com.agidoc.agiDoc.model.competitor.Competitor;

public interface IProcess {
    boolean chooseContractor(Competitor competitor);
}
