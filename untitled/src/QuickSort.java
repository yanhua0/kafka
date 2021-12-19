import java.util.Date;

public class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];

        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];

        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }

    public static void main(String[] args) {
        Date date1=new Date();
        long lDate1 = date1.getTime();
           long lDate2 = date1.getTime()-90*60*1000;
          long diff = (lDate1 < lDate2) ? (lDate2 - lDate1) : (lDate1 - lDate2);
             long day = diff / (24 * 60 * 60 * 1000);
          long hour = diff / (60 * 60 * 1000) - day * 24;
          long min = diff / (60 * 1000) - day * 24 * 60 - hour * 60;
          long sec = diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
       System.out.println("date1 与 date2 相差 " + day + "天" + hour + "小时" + min + "分" + sec + "秒");

    }
}
