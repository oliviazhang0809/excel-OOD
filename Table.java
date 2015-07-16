import java.math.BigDecimal;
import java.util.HashMap;

public class Table {
	HashMap<String, Cell> table;

	public Table() {
		this.table = new HashMap<String, Cell>();
	}

	public Cell createCell(String cellName) {
		Cell cell = new Cell(this, cellName);
		table.put(cellName, cell);
		return cell;
	}

	public Cell getCell(String name) {
		Cell cell = table.get(name);
		return cell == null ? new Cell(this, name) : cell;
	}

	public BigDecimal getCellValue(String name) {
		Cell cell = table.get(name);
		return cell.value;
	}
}
