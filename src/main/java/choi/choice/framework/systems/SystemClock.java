package choi.choice.framework.systems;

import java.util.Date;

public interface SystemClock {
    long currentTimeMillis();

   	Date nowInMinutes();

   	Date nowInHours();

   	Date nowInDays();

   	Date now();

   	boolean isNotOver(long timestamp, long interval);

   	boolean isOver(long timestamp, long interval);

   	boolean isNotOver(long start, long end, long interval);

   	boolean isOver(long start, long end, long interval);

   	void sleep(long timemilis);

}
