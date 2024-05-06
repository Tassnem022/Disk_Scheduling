/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package diskscheduling;

/**
 *
 * @author DELL
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class DiskScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Head pointer: ");
        int head = scanner.nextInt();

        try {
            File file = new File("mydata.txt");
            Scanner fileScanner = new Scanner(file);

            int size = 0;
            while (fileScanner.hasNextInt()) {
                size++;
                fileScanner.nextInt();
            }

            int[] list = new int[size];
            int i = 0;
            fileScanner.close();
            fileScanner = new Scanner(file);
            while (fileScanner.hasNextInt()) {
                list[i] = fileScanner.nextInt();
                i++;
            }
            fileScanner.close();

            fcfs(list, head);
            scan(list, head);
            cscan(list, head);
            clook(list, head);
            sstf(list, head);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static void fcfs(int[] list, int head) {
        int total = 0;
        System.out.println("                           *******************FCFS*******************");
        System.out.print(head + " -> ");
        for (int i = 0; i < list.length; i++) {
            total += Math.abs(head - list[i]);
            if (i != list.length - 1) {
                System.out.print(list[i] + " -> ");
            } else {
                System.out.println(list[i]);
            }
            head = list[i];
        }
        System.out.println("Total Distance = " + total);
    }

    public static void scan(int[] list, int head) {
        int start = 0, end = 100, total = 0;
        int currentHead = head;
        System.out.println("                           *******************SCAN*******************");
        System.out.print(head + " -> ");
        Arrays.sort(list);
        int j = 0;
        // print last half
        for (int i = 0; i < list.length; i++) {
            if (list[i] > head) {
                System.out.print(list[i] + " -> ");
                total += Math.abs(currentHead - list[i]);
                currentHead = list[i];
            } else {
                j = i;
            }
        }
        System.out.print(end + " -> ");
        total += Math.abs(currentHead - end);
        currentHead = end;
        // print first half
        for (j = j - 1; j >= 0; j--) {
            if (j == 0)
                System.out.println(list[j]);
            else
                System.out.print(list[j] + " -> ");
            total += Math.abs(currentHead - list[j]);
            currentHead = list[j];
        }
        System.out.println("Total Distance = " + total);
    }

    public static void cscan(int[] list, int head) {
        int start = 0, end = 100, total = 0;
        int currentHead = head;
        System.out.println("                           *******************CSCAN*******************");
        System.out.print(head + " -> ");
        Arrays.sort(list);
        int j = 0;
        // print last half
        for (int i = 0; i < list.length; i++) {
            if (list[i] > head) {
                System.out.print(list[i] + " -> ");
                total += Math.abs(currentHead - list[i]);
                currentHead = list[i];
            } else {
                j = i + 1; // to know where I stopped
            }
        }
        System.out.print(end + " -> " + start + " -> ");
        total += Math.abs(currentHead - end);
        currentHead = end;
        total += Math.abs(currentHead - start);
        currentHead = start;
        // print first half
        for (int i = 0; i < j; i++) {
            if (i == j - 1)
                System.out.println(list[i]);
            else
                System.out.print(list[i] + " -> ");
            total += Math.abs(currentHead - list[i]);
            currentHead = list[i];
        }
        System.out.println("Total Distance = " + total);
    }

    public static void clook(int[] list, int head) {
        int start = 0, end = 100, total = 0;
        int currentHead = head;
        System.out.println("                           *******************CLOOK*******************");
        System.out.print(head + " -> ");
        Arrays.sort(list);
        int j = 0;
        // print last half
        for (int i = 0; i < list.length; i++) {
            if (list[i] > head) {
                System.out.print(list[i] + " -> ");
                total += Math.abs(currentHead - list[i]);
                currentHead = list[i];
            } else {
                j = i + 1;
            }
        }
        // print first half
        for (int i = 0; i < j; i++) {
            if (i == j - 1)
                System.out.println(list[i]);
            else
                System.out.print(list[i] + " -> ");
            total += Math.abs(currentHead - list[i]);
            currentHead = list[i];
        }
        System.out.println("Total Distance = " + total);
    }

    public static void sstf(int[] list, int head) {
        int total = 0;
        System.out.println("                           *******************SSTF*******************");
        System.out.print(head + " -> ");
        boolean[] flag = new boolean[list.length];
        int temp = head;
        for (int i = 0; i < list.length; i++) {
            int diff = Integer.MAX_VALUE;
            int ele = -1;
            for (int j = 0; j < list.length; j++) {
                if (!flag[j] && Math.abs(list[j] - temp) < diff) {
                    ele = j;
                    diff = Math.abs(list[j] - temp);
                }
            }
            flag[ele] = true;
            total += diff;
            temp = list[ele];
            System.out.print(temp);
            if (i != list.length - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        System.out.println("Total Distance = " + total);
    }
}