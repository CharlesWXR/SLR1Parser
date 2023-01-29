package cn.edu.njnu.SLR1.generator.grammarelement;

public class Edge {
	protected int end;
	protected Symbol data;

	public static final char E_EDGE = (char)-1;

	public Edge(int end, Symbol data) {
		this.end = end;
		this.data = data;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Symbol getData() {
		return data;
	}

	public void setData(Symbol data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Edge{" +
				"end=" + end +
				", data=" + data +
				'}';
	}

	public boolean isEEdge() {
		return this.data.equals(Symbol.Empty);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Edge))
			return false;
		Edge o = (Edge)obj;
		return this.data.equals(o.getData()) && (int)this.end == (int)o.end;
	}

	@Override
	public int hashCode() {
		return data.hashCode() * end ^ (data.hashCode() + end);
	}
}
