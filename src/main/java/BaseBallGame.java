import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class BaseBallGame {
	BufferedReader in;
	Random random;
	boolean[] picked;
	int[] targetNo;
	int pickCount;
	boolean isFinished;
	int[] evalResult;
	
	
	
	public BaseBallGame() {
		in = new BufferedReader(new InputStreamReader(System.in));
		random = new Random();
		picked = new boolean[10];
		targetNo = new int[3];
		pickCount = 0;
		isFinished = false;
		evalResult = new int[2];
	}

	private void init() {
		for(int i = 0; i < 10; i++) {
			picked[i] = false;
		}
		pickCount = 0;
		
		while(pickCount < 3) {
			int currentPick = random.nextInt(10);
			if(!picked[currentPick]) {
				targetNo[pickCount++] = currentPick;
				picked[currentPick] = true;
			}
		}
	}
	
	public boolean isCorrectInput(String s) {
		boolean isCorrect = s.matches("^(?!.*(.).*\\1)\\d{3}$");
		if(!isCorrect) {
			System.out.println("�� ���� �Է��Դϴ�.");
		}
		return isCorrect;
	}
	
	/**
	 * ������ ��� false,
	 * ��ġ �ϴ� �κ��� ���� ��� true ���� �� ���ÿ� evalResult[0]�� ��Ʈ��ũ ���� evalResult[1]�� �� ����
	 */
	private boolean evaluate(String s) {
		int strikeCount = 0;
		int ballCount = 0;
		boolean matchFound = false;
		for(int i = 0; i < 3; i++) {
			int currentInputNo = s.charAt(i)-'0';
			if(targetNo[i] == currentInputNo) {
				++strikeCount;
				matchFound = true;
			}
			if(picked[currentInputNo]) {
				++ballCount;
				matchFound = true;
			}
		}
		evalResult[0] = strikeCount;
		evalResult[1] = ballCount-strikeCount;
		return matchFound;
	}
	
//	private void start() {
//		init();
//		while(!isFinished) {
//			
//		}
//		
//	}
}
