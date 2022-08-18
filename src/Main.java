import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class Main {

    public static void insertionSort(int[] arr) {

        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }

    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, l, L, 0, n1);

        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void pigeonholeSort(int[] arr, int n) {
        int min = arr[0];
        int max = arr[0];
        int range, i, j, index;

        for (int a = 0; a < n; a++) {
            if (arr[a] > max)
                max = arr[a];
            if (arr[a] < min)
                min = arr[a];
        }

        range = max - min + 1;
        int[] phole = new int[range];
        Arrays.fill(phole, 0);

        for (i = 0; i < n; i++)
            phole[arr[i] - min]++;

        index = 0;

        for (j = 0; j < range; j++)
            while (phole[j]-- > 0)
                arr[index++] = j + min;
    }

    static void countSort(int[] arr) {
        int max = 0, min = 0;
        if (Arrays.stream(arr).max().isPresent()){
            max = Arrays.stream(arr).max().getAsInt();
            min = Arrays.stream(arr).min().getAsInt();
        }
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[arr.length];
        for (int j : arr) {
            count[j - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) throws IOException {

        int[] records = new int[251281];

        readAll("TrafficFlowDataset.csv",records);

       /* mergeSort(records,0,records.length-1);// this line to sort the data


        for(int i = 0; i < records.length / 2; i++) this piece of code is to reverse the data
        {
            int temp = records[i];
            records[i] = records[records.length - i - 1];
            records[records.length - i - 1] = temp;
        }
*/

        long start;
        long finish;

        //****************INSERTION****************

        double[] insertionResult = new double[10];
        Arrays.fill(insertionResult,0);

        for (int i = 0; i < 10; i++) {

        start = System.currentTimeMillis();
        insertionSort(Arrays.copyOfRange(records,0,512));
        finish = System.currentTimeMillis();
        insertionResult[0]+= finish-start;


        start = System.currentTimeMillis();
        insertionSort(Arrays.copyOfRange(records,0,1024));
        finish = System.currentTimeMillis();
        insertionResult[1]+= finish-start;


        start = System.currentTimeMillis();
        insertionSort(Arrays.copyOfRange(records,0,2048));
        finish = System.currentTimeMillis();
        insertionResult[2]+= finish-start;


        start = System.currentTimeMillis();
        insertionSort(Arrays.copyOfRange(records,0,4096));
        finish = System.currentTimeMillis();
        insertionResult[3]+= finish-start;


        start = System.currentTimeMillis();
        insertionSort(Arrays.copyOfRange(records,0,8192));
        finish = System.currentTimeMillis();
        insertionResult[4]+= finish-start;


        start = System.currentTimeMillis();
        insertionSort(Arrays.copyOfRange(records,0,16384));
        finish = System.currentTimeMillis();
        insertionResult[5]+= finish-start;


        start = System.currentTimeMillis();
        insertionSort(Arrays.copyOfRange(records,0,32768));
        finish = System.currentTimeMillis();
        insertionResult[6]+= finish-start;


        start = System.currentTimeMillis();
        insertionSort(Arrays.copyOfRange(records,0,65536));
        finish = System.currentTimeMillis();
        insertionResult[7]+= finish-start;


        start = System.currentTimeMillis();
        insertionSort(Arrays.copyOfRange(records,0,131072));
        finish = System.currentTimeMillis();
        insertionResult[8]+= finish-start;


        start = System.currentTimeMillis();
        insertionSort(Arrays.copyOfRange(records,0,records.length));
        finish = System.currentTimeMillis();
        insertionResult[9]+= finish-start;

        }


        // *********************MERGE SORT*********************

        double[] mergeSortResult = new double[10];
        Arrays.fill(mergeSortResult,0);

        for (int i = 0; i < 10; i++) {

        start = System.currentTimeMillis();
        mergeSort(Arrays.copyOfRange(records,0,512),0,511);
        finish = System.currentTimeMillis();
        mergeSortResult[0]+= finish-start;


        start = System.currentTimeMillis();
        mergeSort(Arrays.copyOfRange(records,0,1024),0,1023);
        finish = System.currentTimeMillis();
        mergeSortResult[1]+= finish-start;


        start = System.currentTimeMillis();
        mergeSort(Arrays.copyOfRange(records,0,2048),0,2047);
        finish = System.currentTimeMillis();
        mergeSortResult[2]+= finish-start;


        start = System.currentTimeMillis();
        mergeSort(Arrays.copyOfRange(records,0,4096),0,4095);
        finish = System.currentTimeMillis();
        mergeSortResult[3]+= finish-start;


        start = System.currentTimeMillis();
        mergeSort(Arrays.copyOfRange(records,0,8192),0,8191);
        finish = System.currentTimeMillis();
        mergeSortResult[4]+= finish-start;


        start = System.currentTimeMillis();
        mergeSort(Arrays.copyOfRange(records,0,16384),0,16383);
        finish = System.currentTimeMillis();
        mergeSortResult[5]+= finish-start;


        start = System.currentTimeMillis();
        mergeSort(Arrays.copyOfRange(records,0,32768),0,32767);
        finish = System.currentTimeMillis();
        mergeSortResult[6]+= finish-start;


        start = System.currentTimeMillis();
        mergeSort(Arrays.copyOfRange(records,0,65536),0,65535);
        finish = System.currentTimeMillis();
        mergeSortResult[7]+= finish-start;


        start = System.currentTimeMillis();
        mergeSort(Arrays.copyOfRange(records,0,131072),0,131071);
        finish = System.currentTimeMillis();
        mergeSortResult[8]+= finish-start;


        start = System.currentTimeMillis();
        mergeSort(Arrays.copyOfRange(records,0,records.length),0,records.length-1);
        finish = System.currentTimeMillis();
        mergeSortResult[9]+= finish-start;
        }



        // *********************PIGEONHOLE SORT*********************

        double[] pigeonholeResult = new double[10];
        Arrays.fill(pigeonholeResult,0);

        for (int i = 0; i < 10; i++) {

        start = System.currentTimeMillis();
        pigeonholeSort(Arrays.copyOfRange(records,0,512),512);
        finish = System.currentTimeMillis();
        pigeonholeResult[0]+= finish-start;


        start = System.currentTimeMillis();
        pigeonholeSort(Arrays.copyOfRange(records,0,1024),1024);
        finish = System.currentTimeMillis();
        pigeonholeResult[1]+= finish-start;


        start = System.currentTimeMillis();
        pigeonholeSort(Arrays.copyOfRange(records,0,2048),2048);
        finish = System.currentTimeMillis();
        pigeonholeResult[2]+= finish-start;


        start = System.currentTimeMillis();
        pigeonholeSort(Arrays.copyOfRange(records,0,4096),4096);
        finish = System.currentTimeMillis();
        pigeonholeResult[3]+= finish-start;


        start = System.currentTimeMillis();
        pigeonholeSort(Arrays.copyOfRange(records,0,8192),8192);
        finish = System.currentTimeMillis();
        pigeonholeResult[4]+= finish-start;


        start = System.currentTimeMillis();
        pigeonholeSort(Arrays.copyOfRange(records,0,16384),16384);
        finish = System.currentTimeMillis();
        pigeonholeResult[5]+= finish-start;


        start = System.currentTimeMillis();
        pigeonholeSort(Arrays.copyOfRange(records,0,32768),32768);
        finish = System.currentTimeMillis();
        pigeonholeResult[6]+= finish-start;


        start = System.currentTimeMillis();
        pigeonholeSort(Arrays.copyOfRange(records,0,65536),65536);
        finish = System.currentTimeMillis();
        pigeonholeResult[7]+= finish-start;


        start = System.currentTimeMillis();
        pigeonholeSort(Arrays.copyOfRange(records,0,131072),131072);
        finish = System.currentTimeMillis();
        pigeonholeResult[8]+= finish-start;


        start = System.currentTimeMillis();
        pigeonholeSort(Arrays.copyOfRange(records,0,records.length),records.length);
        finish = System.currentTimeMillis();
        pigeonholeResult[9]+= finish-start;


        }

        // *********************COUNT SORT*********************

        double[] countSortResult = new double[10];
        Arrays.fill(countSortResult,0);


        for (int i = 0; i <10 ; i++) {

        start = System.currentTimeMillis();
        countSort(Arrays.copyOfRange(records,0,512));
        finish = System.currentTimeMillis();
        countSortResult[0]+= finish-start;


        start = System.currentTimeMillis();
        countSort(Arrays.copyOfRange(records,0,1024));
        finish = System.currentTimeMillis();
        countSortResult[1]+= finish-start;


        start = System.currentTimeMillis();
        countSort(Arrays.copyOfRange(records,0,2048));
        finish = System.currentTimeMillis();
        countSortResult[2]+= finish-start;


        start = System.currentTimeMillis();
        countSort(Arrays.copyOfRange(records,0,4096));
        finish = System.currentTimeMillis();
        countSortResult[3]+= finish-start;


        start = System.currentTimeMillis();
        countSort(Arrays.copyOfRange(records,0,8192));
        finish = System.currentTimeMillis();
        countSortResult[4]+= finish-start;


        start = System.currentTimeMillis();
        countSort(Arrays.copyOfRange(records,0,16384));
        finish = System.currentTimeMillis();
        countSortResult[5]+= finish-start;


        start = System.currentTimeMillis();
        countSort(Arrays.copyOfRange(records,0,32768));
        finish = System.currentTimeMillis();
        countSortResult[6]+= finish-start;


        start = System.currentTimeMillis();
        countSort(Arrays.copyOfRange(records,0,65536));
        finish = System.currentTimeMillis();
        countSortResult[7]+= finish-start;


        start = System.currentTimeMillis();
        countSort(Arrays.copyOfRange(records,0,131072));
        finish = System.currentTimeMillis();
        countSortResult[8]+= finish-start;


        start = System.currentTimeMillis();
        countSort(Arrays.copyOfRange(records,0,records.length));
        finish = System.currentTimeMillis();
        countSortResult[9]+= finish-start;
        }

        // take average of the results
        divide(insertionResult);
        divide(mergeSortResult);
        divide(pigeonholeResult);
        divide(countSortResult);

        /* get the average results
        System.out.println(Arrays.toString(insertionResult));
        System.out.println(Arrays.toString(mergeSortResult));
        System.out.println(Arrays.toString(pigeonholeResult));
        System.out.println(Arrays.toString(countSortResult));
*/


        // X axis data
        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};

        // Create sample data for linear runtime
        double[][] yAxis = new double[4][10];
        yAxis[0] = mergeSortResult;
        yAxis[1] = insertionResult;
        yAxis[2] = pigeonholeResult;
        yAxis[3] = countSortResult;


        // Save the char as .png and show it
        showAndSaveChart("Tests on Reverse Sorted Data", inputAxis, yAxis);



    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("merge", doubleX, yAxis[0]);
        chart.addSeries("insertion", doubleX, yAxis[1]);
        chart.addSeries("pigeonhole", doubleX, yAxis[2]);
        chart.addSeries("count", doubleX, yAxis[3]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();


    }

    public static void readAll(String filename, int[] records){

        int counter=0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();//skip the first line
            while ((line = br.readLine()) != null) {
                int value = Integer.parseInt(line.split(",")[7]);
                records[counter]=value;
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void divide(double[] arr){

        for (int i = 0; i < arr.length; i++) {
            arr[i]/=10;
        }

    }

}

