import java.math.BigDecimal;
import java.util.HashMap;

public class Excel {

	HashMap<Integer, HashMap<Integer, Cell>> map = new HashMap<Integer, HashMap<Integer, Cell>>();
	final static Table sheet = new Table();

	public BigDecimal get(int x, int y) {
		if (map.get(x) == null || map.get(x).get(y) == null) {
			return null;
		}
		return map.get(x).get(y).value;
	}

}
