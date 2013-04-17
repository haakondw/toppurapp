package com.ntnu.eit.socket;

import com.ntnu.eit.common.model.Deviation;

public class DeviationSocketObject {

	private Deviation deviation;
	
	public DeviationSocketObject(Deviation deviation){
		this.deviation = deviation;
	}

	public Deviation getDeviation() {
		return deviation;
	}

	public void setDeviation(Deviation deviation) {
		this.deviation = deviation;
	}
	
	
	
}
