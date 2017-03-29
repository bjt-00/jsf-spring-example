package com.bitguiders.jsf;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

public class JSFPhaseListener implements PhaseListener {

@Inject
JSFMessageSupport message;
	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		message.setInfo("JSF after phase called");
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		message.setInfo("JSF before phase called");
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return null;
	}

}
