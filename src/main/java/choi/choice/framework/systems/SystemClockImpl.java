package choi.choice.framework.systems;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class SystemClockImpl implements SystemClock{
    @Override
   	public long currentTimeMillis() {
   		return System.currentTimeMillis();
   	}

   	@Override
   	public boolean isNotOver(long timestamp, long interval) {
   		return !isOver(timestamp, interval);
   	}

   	@Override
   	public boolean isOver(long timestamp, long interval) {
   		return isOver(currentTimeMillis(), timestamp, interval);
   	}

   	@Override
   	public boolean isNotOver(long start, long end, long interval) {
   		return !isOver(start, end, interval);
   	}

   	@Override
   	public boolean isOver(long start, long end, long interval) {
   		return (start - end) > interval;
   	}

   	@Override
   	public Date now() {
   		return new Date(currentTimeMillis());
   	}

   	@Override
   	public Date nowInMinutes() {
   		Date now = now();
   		return new Date(now.getYear(), now.getMonth(), now.getDate(), now.getHours(), now.getMinutes(), 0);
   	}

   	@Override
   	public Date nowInHours() {
   		Date now = now();
   		return new Date(now.getYear(), now.getMonth(), now.getDate(), now.getHours(), 0, 0);
   	}

   	@Override
   	public Date nowInDays() {
   		Date now = now();
   		return new Date(now.getYear(), now.getMonth(), now.getDate(), 0, 0, 0);
   	}

   	@Override
   	public void sleep(long timemilis) {
   		try {
   			Thread.sleep(timemilis);
   		} catch (InterruptedException e) {
   			log.error("", e);
   		} finally {

   		}
   	}
}
