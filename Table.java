import java.util.HashMap;

public class Table {
	HashMap<String, Cell> table;

	public Table() {
		this.table = new HashMap<String, Cell>();
	}

	public Cell createCell(int x, int y) {
		StringBuilder sb = new StringBuilder();
		String name = sb.append(coloumToXYZ(y)).append(x).toString();
		Cell cell = new Cell(this);
		cell.name = name;
		table.put(name, cell);
		return cell;
	}

	public String coloumToXYZ(int x) {
		StringBuilder sb = new StringBuilder();
		while (x > 0) {
			x--;
			char tmp = (char) ('A' + x % 26);
			sb.append(tmp);
			x /= 26;
		}
		return sb.reverse().toString();
	}

	public Cell getCell(String name) {
		Cell cell = table.get(name);
		return cell == null ? new Cell(this) : cell;
	}
}
