package choi.choice.framework.systems;

import lombok.Getter;

public enum Lane {
    LANE1(1), LANE2(2);

   	@Getter
   	private int no;

   	Lane(int no) {
   		this.no = no;
   	}

   	public static Lane decideLane(Stage stage, WasInstance wasInstance) {
   		if (stage == Stage.LOCAL || stage == Stage.DEV || stage == Stage.STAGING) {
   			return Lane.LANE1;
   		}
   		// 운영에서는 M1,M3이 LANE1 M2,M4가 LANE2임
   		if (wasInstance.getNo() % 2 == 1) {
   			return Lane.LANE1;
   		} else {
   			return Lane.LANE2;
   		}
   	}
}
