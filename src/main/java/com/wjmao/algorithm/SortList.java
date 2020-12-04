package com.wjmao.algorithm;


import java.util.Arrays;

/**
 * 十大排序算法
 */
public class SortList {

    /**
     * 冒泡排序
     * @param arr
     * @return
     */
    public int [] bubbleSort(int []  arr){
        int len = arr.length;
        for(int i=0;i<len;i++){
            for(int j=0;j<len -1 -i;j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序
     * @param arr
     * @return
     */
    public int []  selectionSort(int [] arr){
        int len = arr.length;
        int minIndex,temp;
        for(int i=0;i<len;i++){
            minIndex = i;
            for(int j=i+1;j<len;j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }

    /**
     * 插入排序
     * @param arr
     * @return
     */
    public int []  insertSort(int [] arr) {
        int len = arr.length;
        int preIndex,current;
        for(int i=0;i<len;i++){
            preIndex = i-1;
            current = arr[i];
            while (preIndex >=0 && arr[preIndex] > current){
                arr[preIndex+1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex+1] = current;
        }

        return arr;
    }

    /**
     * 希尔排序
     * @param arr
     * @return
     */
    public int [] shellSort(int [] arr){
        int len = arr.length;
        int  temp,gap = 1;
        while(gap < len/3){ //动态定义间隔序列
           gap  =gap*3+1;
        }
        for(;gap>0;gap=(int)Math.floor(gap/3)){
            for(int i = gap;i<len;i++){
                temp = arr[i];
                int j;
                for(j=i-gap;j >=0 && arr[j]>temp;j-=gap){
                    arr[j+gap] = arr[j];
                }
                arr[j+gap] = temp;
            }
        }
        return arr;
    }

    /**
     * 归并排序
     * @param arr
     * @return
     */
    public static int [] mergeSort(int [] arr){
        if(arr.length < 2)
            return arr;
        int mid = arr.length/2;
        int[] left = Arrays.copyOfRange(arr,0,mid);
        int[] right =  Arrays.copyOfRange(arr,mid,arr.length);
        return merge(mergeSort(left),mergeSort(right));
    }

    private static int [] merge(int [] left,int [] right){
        int [] result = new int [left.length+right.length];
        for (int index = 0,i=0,j=0;index < result.length; index++){
            if(i >= left.length)
                result[index] = right[j++];
            else  if (j >= right.length)
                result[index] = left[i++];
            else if(left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }

    /**
     * 快速排序方法
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int[] QuickSort(int[] array,int start,int end){
        if(array.length < 1 || start < 0 || end >= array.length || start > end ){
            return  null;
        }
        int smallIndex = partition(array,start,end);
        if(smallIndex > start){
            QuickSort(array,start,smallIndex-1);
        }
        if(smallIndex < end){
            QuickSort(array,smallIndex + 1,end);
        }
        return array;
    }


    /**
     * 快速排序算法--partition
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] array,int start,int end){
        int pivot = (int) (start + Math.random()*(end -start +1));
        int smallIndex = start - 1;
        swap(array,pivot,end);
        for(int i = start;i<= end;i++){
            if(array[i] <= array[end]){
                smallIndex++;
                if(i > smallIndex){
                    swap(array,i,smallIndex);
                }
            }
        }
        return smallIndex;
    }


    /**
     * 交换数组内两个元素
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array,int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //声明全局变量，用于记录数组array的长度
    static int len;

    public static int[] heapSort(int[] array){
        len = array.length;
        if(len < 1){
            return array;
        }
        buildMaxHeap(array);

        while (len > 0){
            swap(array,0,len -1);
            len--;
            ajustHeap(array,0);
        }
        return array;
    }


    /**
     * 建立最大堆
     * @param array
     */
    public static void buildMaxHeap(int[] array){
        //从最后一个非叶子节点开始向上构造最大堆
        for(int i=(len/2 -1);i >=0;i--){
            ajustHeap(array,i);
        }
    }

    /**
     * 调整使之成为最大堆
     * @param array
     * @param i
     */
    public static void ajustHeap(int [] array,int i){
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if(i * 2 < len && array[i*2] > array[maxIndex]){
            maxIndex = i*2;
        }
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if(i*2 + 1 < len && array[i * 2 +1] > array[maxIndex]){
            maxIndex = i*2 +1;
        }
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置
        if(maxIndex != i){
            swap(array,maxIndex,i);
            ajustHeap(array,maxIndex);
        }
    }

}
