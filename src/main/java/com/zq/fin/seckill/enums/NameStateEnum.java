package com.zq.fin.seckill.enums;

public enum NameStateEnum {
	
	activate_state(0, "激活状态"),
	normal_state(1, "正常状态"),
	failure_state(-1, "失效状态");
	
	private int state;
	
	private String stateInfo;

	private NameStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static NameStateEnum stateOf(Object object){
		int index = (int)object;
		for(NameStateEnum state : values()){
			if(state.getState() == index){
				return state;
			}
		}
		return null;
	}
}
