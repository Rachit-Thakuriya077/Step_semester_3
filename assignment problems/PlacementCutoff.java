
public class PlacementCutoff {

    static int kthHighest(int[] marks, int k) {

        int n = marks.length;

        int target = n - k;

        int low = 0;
        int high = n - 1;

        while (low <= high) {

            int pivotIndex = partition(marks, low, high);

            if (pivotIndex == target) {

                return marks[pivotIndex];

            } else if (pivotIndex > target) {

                high = pivotIndex - 1;

            } else {

                low = pivotIndex + 1;
            }
        }

        return -1;
    }

    static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j] <= pivot) {

                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {

        int[] marks = {72, 95, 60, 88, 79};

        int k = 2;

        int result = kthHighest(marks, k);

        System.out.println(k + "-th Highest Mark: " + result);
    }
}

