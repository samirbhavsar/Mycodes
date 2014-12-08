public class BinarySearch {
 
	int search = 22;
 
	public static void main(String[] args) {
 
		int a[] = { 11, 22, 33, 44, 55, 66, 77, 88, 99, 100 };
 
		BinarySearch search = new BinarySearch();
		int pos = search.search(a, 0, a.length - 1);
 
		if (pos == -1) {
			System.out.println("Search Element Not Found :" + "");
		} else {
			System.out.println("Element found at position : " + (pos + 1));
		}
	}
 
	int search(int a[], int low, int high) {
		int pos = -1;
 
		int mid = (high + low) / 2;
		System.out.println("Mid::" + mid + "High::" + high + "Low::" + low);
 
		if (high >= low) {
			if (a[mid] == search) {
				pos = mid;
			} else if (a[mid] > search) {
				pos = search(a, low, mid - 1);
			} else if (a[mid] < search) {
				pos = search(a, mid + 1, high);
			}
		}
		return pos;
	}
}