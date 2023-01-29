package cn.edu.njnu.SLR1.generator.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ArrayUtils {
	private static final int Multiplier = (int)31;

	public static int getHashCode(ArrayList<Integer> list) {
		// May cause many conflicts, but still better than nothing
		int m = Multiplier ^ list.size();
		int res = 16777619;
		for (Integer i : list) {
			res = res ^ (m * i);
		}
		return res;
	}

	public static int contains(ArrayList<Integer> target,
	                          ArrayList<ArrayList<Integer>> collection,
	                          ArrayList<Integer> hashList) {
		if (hashList.size() < collection.size()) {
			for (int i = hashList.size(); i < target.size(); i++) {
				hashList.add(ArrayUtils.getHashCode(collection.get(i)));
			}
		}

		int hashCode = ArrayUtils.getHashCode(target);
		boolean misMatch = false;

		for (int i = 0; i < hashList.size(); i++) {
			if (hashList.get(i) == hashCode) {
				ArrayList<Integer> t = collection.get(i);
				misMatch = false;
				if (t.size() == target.size()) {
					for (int j = 0; j < t.size(); j++) {
						int num = t.get(j);
						if (target.get(j) != num) {
							misMatch = true;
							break;
						}
					}
					if (!misMatch) {
						return i;
					}
				}
			}
		}

		return collection.size();
	}

	public static List<Integer> getDiff(List<Integer> a, List<Integer> b) {
		// return a - b
		return a.stream().filter(i -> !b.contains(i)).collect(Collectors.toList());
	}

	public static <T> List<T> mergeSet(List<T> a, List<T> b) {
		if (null == b)
			return a;
		a.addAll(b);
		return a.stream().distinct().collect(Collectors.toList());
	}
}
