package cn.edu.njnu.SLR1.demo.wordscanner;

public interface State {
	State next(char word);

	Object execute(String _content) throws Exception;
}
