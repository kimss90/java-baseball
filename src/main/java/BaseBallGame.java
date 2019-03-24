import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class BaseBallGame {
	BufferedReader in;
	Random random;
	boolean[] picked;
	int[] targetNo;
	int pickCount;
	boolean isFinished;
	boolean isCorrect;
	int[] evalResult;
	
	
	
	public BaseBallGame() {
		in = new BufferedReader(new InputStreamReader(System.in));
		random = new Random();
		picked = new boolean[10];
		targetNo = new int[3];
		pickCount = 0;
		isFinished = false;
		isCorrect = false;
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
		System.out.println(targetNo[0] + " " + targetNo[1] + " " + targetNo[2]);
	}
	
	private boolean isCorrectInput(String s) {
		boolean isCorrect = s.matches("^(?!.*(.).*\\1)\\d{3}$");
		if(!isCorrect) {
			System.out.println("�� ���� �Է��Դϴ�.");
		}
		return isCorrect;
	}
	
	/**
	 * 3strike�� ��� true,
	 * ������ false ���� �� ���ÿ� evalResult[0]�� ��Ʈ��ũ ���� evalResult[1]�� �� ����
	 */
	private boolean evaluate(String s) {
		int strikeCount = 0;
		int ballCount = 0;
		for(int i = 0; i < 3; i++) {
			int currentInputNo = s.charAt(i)-'0';
			if(targetNo[i] == currentInputNo) {
				++strikeCount;
			}
			if(picked[currentInputNo]) {
				++ballCount;
			}
		}
		evalResult[0] = strikeCount;
		evalResult[1] = ballCount-strikeCount;
		return strikeCount == 3;
	}
	
	public void start() throws IOException {
		String userInput;
		boolean correctInputForFinish;
		while(!isFinished) {
			init();
			correctInputForFinish = false;
			isCorrect = false;
			while(!isCorrect) {
				System.out.println("���ڸ��Է����ּ��� : ");
				userInput = in.readLine();
				
				//�� ���� �Է� �� ���
				if(!isCorrectInput(userInput)) {
					continue;
				}
				
				if(evaluate(userInput)) {
					while(!correctInputForFinish) {
						isCorrect = true;
						System.out.println("3���� ���ڸ� ��� �����̽��ϴ�! ��������");
						System.out.println("������ ���� �����Ϸ��� 1, �����Ϸ��� 2�� �Է��ϼ���");
						userInput = in.readLine();
						if(userInput.equals("1")) {
							correctInputForFinish = true;
						}else if(userInput.equals("2")) {
							correctInputForFinish = true;
							isFinished = true;
						}
					}
				}else {
					if(evalResult[0] == 0 && evalResult[1] == 0) {
						System.out.print("����");
					}else {
						if(evalResult[0] != 0) {
							System.out.print(evalResult[0] + "��Ʈ����ũ ");
						}
						if(evalResult[1] != 0) {
							System.out.print(evalResult[1] + "��");
						}
					}
					System.out.println();
				}
				
			}
		}
		
	}
}
