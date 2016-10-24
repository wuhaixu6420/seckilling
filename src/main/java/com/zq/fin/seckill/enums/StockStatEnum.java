package com.zq.fin.seckill.enums;

public enum StockStatEnum {

	GDTYPE_SH(1,"沪A-A466777393"),
	GDTYPE_SZ(0, "深A-0200381345");
	
	private int state;
	
	private String stateInfo;

	private StockStatEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static StockStatEnum stateOf(int index){
		for(StockStatEnum state : values()){
			if(state.getState() == index){
				return state;
			}
		}
		return null;
	}
}
