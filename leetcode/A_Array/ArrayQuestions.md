
## 1.Given 2D array calculate the sum of diagonal elements.

Example 
```myArray2D= {{1,2,3},{4,5,6},{7,8,9}};
```

For single diagonal
```java
 public static int sumDiagonalElements(int[][] array) {
        int sum = 0;
        int numRows = array.length;
 
        for (int i = 0; i < numRows; i++) {
            sum += array[i][i];
        }
 
        return sum;
    }
```

For both diagonal
```java
    public static int sumDiagonalElements(int[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i == j) {
                    sum = sum + array[i][j];
                }
                if ((i+j)== (array.length -1)) {
                    sum = sum + array[i][j];
                }
            }
        }
        return sum;
    }
```

##2. Given an array, write a function to get first, second best scores from the array and return it in new array.

Array may contain duplicates.Example
myArray = {84,85,86,87,85,90,85,83,23,45,84,1,2,0}
firstSecond(myArray) // {90, 87}

```java
public class Test {
    public static void main(String[] args) {
        int[] myArray = {84, 85, 86, 87, 85, 90, 85, 83, 23, 45, 84, 1, 2, 0};
        System.out.println(Arrays.toString(findTopTwoScores(myArray)));

    }

    public static int[] findTopTwoScores(int[] array) {
        int arr[] = new int[2];
        int max = array[0];
        int max2 = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] > max2 && array[i] < max)  {
                max2 = array[i];
            }
        }
        arr[0] = max;
        arr[1] = max2;
        return arr;
    }
}
```
