
public class Request implements Comparable {
	double startTime;
	double endTime;
	public Request(double start, double end){
		this.startTime = start;
		this.endTime = end;
		
	}
	
	public double getStartTime(){
		return startTime;
	}
	public double getEndTime(){
		return endTime;
	}
	public boolean isNotBlocked(double time){
		if (this.startTime > time || this.endTime < time)
			return false;
		return true;	
	}		
	public int compareTo(Object r) {
		
		// TODO Auto-generated method stub
		return this.startTime > ((Request) r).getStartTime() ? 1 : 
			this.startTime == ((Request) r).getStartTime() ? 0:
				-1;
	}

	
}
