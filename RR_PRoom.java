
public class RR_PRoom extends PRoom {
	
	public static void main(String[] args){
		RR_PRoom r1 = new RR_PRoom(10, 1, 1);
		RR_PRoom r2 = new RR_PRoom(10, 3, 10);
		RR_PRoom r3 = new RR_PRoom(10, 15, 10);
		RR_PRoom r4 = new RR_PRoom(10, 20, 20);
		RR_PRoom[] rooms = {r1, r2, r3, r4};
		
		for (RR_PRoom r : rooms){
			System.out.println("Random Restart: ");
			r.randomRestart();
		}
	}
	
	public RR_PRoom(int n, int M, int N){
		super(n,M,N);
	}
	
	// Restart Hill Climbing at random points
	public void randomRestart(){
		startParty();
		float leastVal = NHillClimbs(100);
		int[] goodRoom = getRoom();
		
		for (int i = 0; i<50; i++){
			startParty(); //random restart
			float newVal = NHillClimbs(100);
			if (newVal < leastVal) {
				leastVal = newVal; 
				goodRoom = getRoom();
			}
		}
		System.out.println("For case "+getProfPos().length+" professors and "+getStudentPos().length+" students.");
		System.out.println("The discontent value is "+leastVal+".");
		System.out.println("The room configuration is:");
		printRoom(goodRoom);
		System.out.println("**********************************");
	}
	
	// The actual hill climbing with iterNum of iterations
	public float NHillClimbs(int iterNum){
		float leastSum = discontentSum();
		for (int i=0; i<iterNum; i++){
			float newSum = hillClimb();			
			if (newSum < leastSum){
				roomPos = tempRoom;
				profPos = tempProfPos;
				studentPos = tempStudentPos;
				leastSum = newSum;
			} 
		}
		return leastSum;
	}
				
}
