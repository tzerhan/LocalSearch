import java.util.Arrays;
import java.util.Random;

// The room class
public class PRoom {
	int[] roomPos;
	int[] profPos;
	int[] studentPos;
	
	int size;	
	int profNum;
	int studentNum;
	
	int[] tempRoom;
	int[] tempProfPos;
	int[] tempStudentPos;
	
	public PRoom(int n, int M, int N){
		size= n*n;
		profNum = M;
		studentNum = N;
	}
	
	public int[] getRoom(){
		return roomPos;
	}
	
	public int[] getProfPos(){
		return profPos;
	}
	
	public int[] getStudentPos(){
		return studentPos;
	}
	
	// generates random configuration of the room
		public void startParty(){
			Random gen = new Random();
			roomPos= new int[size];
			profPos= new int[profNum];
			studentPos= new int[studentNum];	
			
			for (int i=0; i<profNum; i++){
				int k = gen.nextInt(size);
				while (roomPos[k]!=0){
					k = gen.nextInt(size);
				}
				profPos[i]=k;
				roomPos[k]=1;
			}
			
			for (int i=0; i<studentNum; i++){
				int k = gen.nextInt(size);
				while (roomPos[k]!=0){
					k = gen.nextInt(size);
				}
				studentPos[i]=k;
				roomPos[k]=2;
			}
		}
		
		// One step of hill climb and return the discontent sum
		public float hillClimb(){
			Random gen = new Random();

			tempRoom = new int[size];
			tempProfPos = new int[profNum];
			tempStudentPos = new int[studentNum];
			
			for (int i=0; i<profNum; i++){
				int k = 0;
				int r = gen.nextInt(4);
				if (r==0) {
					k=profPos[i]+1;
					if (k%10==0) {k=size+1;}
				}  
				if (r==1) {
					k=profPos[i]-1;
					if ((k+1)%10==0) {k=size+1;}
				}
				if (r==2) {k=profPos[i]+10;}  if (r==3) {k=profPos[i]-10;}
				if (!((k>=0 && k<size) && tempRoom[k]==0)){
					k=profPos[i];
				}
				tempProfPos[i]=k;
				tempRoom[k]=1;
			}
			
			for (int i=0; i<studentNum; i++){
				int k = 0;
				int r = gen.nextInt(4);
				if (r==0) {
					k=studentPos[i]+1;
					if (k%10==0) {k=size+1;}
				}  
				if (r==1) {
					k=studentPos[i]-1;
					if ((k+1)%10==0) {k=size+1;}
				}
				if (r==2) {k=studentPos[i]+10;}  if (r==3) {k=studentPos[i]-10;}
				if (!((k>=0 && k<size) && tempRoom[k]==0)){
					k=studentPos[i];
				}
				tempStudentPos[i]=k;
				tempRoom[k]=2;
			}
			
			return discontentSum(tempProfPos, tempStudentPos);
		}
		
		// print this room
		public void printRoom(){
			printRoom(roomPos);
		}
		
		// calculate the discontentSum of this room
		public float discontentSum(){
			return discontentSum(profPos, studentPos);
		}	
		
		// print room r
		public void printRoom(int[] r){
			for (int i=0; i<10; i++){
				System.out.println(Arrays.toString(Arrays.copyOfRange(r,i*10,(i+1)*10)));
			}
		}
		
		// calculate discontentSum given profPos and student Pos
		public float discontentSum(int[] profPos, int[] studentPos){
			int m=profPos.length;
			int n=studentPos.length;
			float sum = 0;
			for (int i=0; i<m; i++){
				float pi = (float) ((profPos[i]+1)/10)+1;
				float pj = (float) ((profPos[i]+1)%10);
				for (int j=0; j<n; j++){
					float si = (float) ((studentPos[j]+1)/10)+1;
					float sj = (float) ((studentPos[j]+1)%10);
					sum = sum + (float) (1/Math.sqrt(Math.pow((pi-si),2)+Math.pow((pj-sj),2)));
				}
			}
			
			for (int i=0; i<m; i++){
				float pi = (float) ((profPos[i]+1)/10)+1;
				float pj = (float) ((profPos[i]+1)%10);
				for (int j=i+1; j<m; j++){
					float si = (float) ((profPos[j]+1)/10)+1;
					float sj = (float) ((profPos[j]+1)%10);
					sum = sum + (float) (1/Math.sqrt(Math.pow((pi-si),2)+Math.pow((pj-sj),2)));
				}
			}
			
			return sum;
		}
}
