import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Position {
	public static final int DIM=3;
	public static final int SIZE=DIM*DIM;
	public char turn;
	public char[] board;
	private Map<Integer,Integer>cache=new HashMap<Integer, Integer>();
	
public Position(){
	this.turn='x';
	this.board=new char[SIZE];
	for (int i = 0; i < SIZE; i++) {
		board[i]=' ';
	}
}
public String toString(){
	return new String(board);	
}

public Position(char[] board,char turn){
	this.board=board;
	this.turn=turn;
}
public Position(String str){
	this.board=str.toCharArray();
	this.turn='x';
}
public Position(String str, char turn) {
	this.board=str.toCharArray();
	this.turn=turn;

}

public Position move(int idx) {
	board[idx]= turn;
	turn= turn== 'x'? 'o': 'x';
	return this;
}
public Position unmove(int idx) {
	board[idx]=' ';
	turn= turn== 'x'? 'o': 'x';
	return this;
}
public List<Integer> possibleMoves() {
	List<Integer> list= new ArrayList<>();
	for(int i=0;i<board.length;i++){
	if(board[i]==' ')
		list.add(i);
		}
	return list;
}



public boolean win_line(char turn, int start, int step){
	for (int i = 0; i < 3; i++) {
		if(board[start+ step*1]!=turn){
			return false;
		}
	}
	return true;
}
public boolean isWinFor(char turn) {
	boolean isWin=false;
	for (int i = 0; i < SIZE; i+=DIM) {
		isWin=isWin || lineMatch(turn,i,i+DIM,1);
	}
	for (int i = 0; i < DIM; i++) {
		isWin=isWin || lineMatch(turn,i,SIZE,DIM);
	}
	isWin=isWin||lineMatch(turn,0,SIZE,DIM+1);
	isWin=isWin||lineMatch(turn,DIM-1,SIZE,DIM-1) ;
	
	return isWin;
}
private boolean lineMatch(char turn, int start, int end, int step) {
	for (int i = start; i < end; i+=step) {
		if(board[i]!=turn){
			return false;
		}
	}
	return true;
}
public int code(){
	int value=0;
	for (int i = 0; i < SIZE; i++) {
		value=value*3;
		if(board[i]=='x'){
			value+=1;
		}
		if(board[i]=='o'){
			value+=2;
			
		}
		
	}
	return value;
}
public int minimax() {
	Integer key=code();
	Integer value=cache.get(key);
	if(value!=null)return value;
	if(isWinFor('x')){	return blanks();}
	if(isWinFor('o')){	return -blanks();}
	if(blanks() == 0){ return 0; }
	Integer mm= null;
	List<Integer> list= new ArrayList<>();
	for (Integer idx : possibleMoves()) {
		list.add(move(idx).minimax());
		unmove(idx);
		}
	value= turn=='x'? Collections.max(list):Collections.min(list);
	cache.put(key, value);
	return value;
}

private int blanks() {
	int total=0;
	for (int i = 0; i < SIZE; i++) {
		if(board[i]==' '){
			total++;
		}
		
	}
	return total;
}
public int bestMove() {
	Comparator<Integer> cmp=new Comparator<Integer>() {

		@Override
		public int compare(Integer first, Integer second) {
			int a=move(first).minimax();
			unmove(first);
			int b=move(second).minimax();
			unmove(second);
			return a-b;
		}
	};
	List<Integer> list= possibleMoves();
	return turn== 'x'? Collections.max(list,cmp):Collections.min(list,cmp);
	}

public boolean isGameEnd() {
	return isWinFor('x')||isWinFor('o')||blanks()==0;
}


}
