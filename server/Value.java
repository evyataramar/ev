package micTest;
import java.util.concurrent.atomic.AtomicInteger;

public class Value {
	private long startTime;
	private AtomicInteger count = new AtomicInteger(0);
	
	public Value(long time){
		this.startTime = time;
		this.count.incrementAndGet();
	}
	public int AddAndGetCount() {
		return this.count.incrementAndGet();
	}
	
	public long getStartTime() {
		return this.startTime;
	}
	
}
