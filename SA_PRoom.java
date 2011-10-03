import java.util.Random;

public class SA_PRoom extends PRoom {
	
	public static void main(String[] args){
		SA_PRoom r1 = new SA_PRoom(10, 1, 1);
		SA_PRoom r2 = new SA_PRoom(10, 3, 10);
		SA_PRoom r3 = new SA_PRoom(10, 15, 10);
		SA_PRoom r4 = new SA_PRoom(10, 20, 20);
		SA_PRoom[] rooms = {r1, r2, r3, r4};
		
		for (SA_PRoom r : rooms){
			System.out.println("Simulated Annealing: ");
			r.SA();
		}
	}
	
	public SA_PRoom(int n, int M, int N){
		super(n,M,N);
	}
	
	// Simulated Annealing
	public float SA(){
		startParty();
		float leastSum = discontentSum();
		int T = 0;
		Random r = new Random();
		float delE = 0f;
		for (int i=999; i>=0; i--){
			float newSum = hillClimb();
			T = i/1000;
			delE = newSum - leastSum;
			if (delE < 0){
				roomPos = tempRoom;
				profPos = tempProfPos;
				studentPos = tempStudentPos;
				leastSum = newSum;
			} else {
				if (r.nextFloat()<Math.exp(-delE/T)){
					roomPos = tempRoom;
					profPos = tempProfPos;
					studentPos = tempStudentPos;
					leastSum = newSum;
				}
			}
		}
		
		System.out.println("For case "+getProfPos().length+" professors and "+getStudentPos().length+" students.");
		System.out.println("The discontent value is "+leastSum+".");
		System.out.println("The room configuration is:");
		printRoom(roomPos);
		System.out.println("**********************************");
		return leastSum;
	}
}
