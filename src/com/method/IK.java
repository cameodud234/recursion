package com.method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class IK {

    public static int combination(int n, int k) {
        if(n <= 1 || k == n || k == 0) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    public static int subSetCount(int size) {
        if(size == 0) return 1;
        return 2 * subSetCount(size-1);
    }

    public static int maximumNumberOfSteps(int n) {
        if(n == 0) return 0;
        return maximumNumberOfSteps(n - 2) + 1;
    }

    public static class LinkedListArgs {
        int value;
        LinkedListArgs next;
        public LinkedListArgs(int value){
            this.value = value;
            next = null;
        }
    }

    public static List<Integer> kLargestStreams(List<Integer> arr, int k) {
        if(k > arr.size() || arr.size() == 1) return arr;
        Map<Integer, Integer> number_count = new HashMap<>();
        List<Integer> k_values = new ArrayList<>();

        for(Integer number : arr) {
            if(number_count.containsKey(number)) {
                number_count.put(number, number_count.get(number) + 1);
            }
            else {
                number_count.put(number, 1);
            }
        }

        Queue<List<Integer>> q = new PriorityQueue<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> a, List<Integer> b) {
                if(a.get(1) > b.get(1)) return -1;
                else if(a.get(1) == b.get(1)) return 0;
                else return 1;
            }
        });

        Set<Integer> keys = number_count.keySet();

        for(Integer key : keys) {
            q.add(List.of(key, number_count.get(key)));
        }

        for(int i = 0; i < k; i++) {
            k_values.add(q.poll().get(0));
        }

        return k_values;
    }

    public static ArrayList<Integer> online_median(ArrayList<Integer> stream) {
        // create arraylist
        ArrayList<Integer> medianList = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        // for an element in the stream
        for(int i = 0; i < stream.size(); i++) {
            int currentValue = stream.get(i);
            addToNumberList(numbers, currentValue);
            int currentMedian = getMedian(numbers);
            medianList.add(currentMedian);
        }
        
        return medianList;
    }
    
    public static void addToNumberList(ArrayList<Integer> numbers, int currentValue) {
        numbers.add(currentValue);
        int i = numbers.size() - 2;
        while(i >= 0 && numbers.get(i) > currentValue) {
            Collections.swap(numbers, i , i + 1);
            i--;
        }
    }

    public static int getMedian(ArrayList<Integer> list) {
        int index1 = list.size() / 2;
        if(list.size() % 2 == 0) {
            int index2 = index1 - 1;
            return (list.get(index1) + list.get(index2)) / 2;
        }
        
        return list.get(index1);
        
    }

    public static void createAllNumbersPermutations(List<Integer> slate, int maxLength) {
        createAllNumbersHelper("", maxLength, slate);
    }

    private static void createAllNumbersHelper(String s, int maxLength, List<Integer> slate) {
        if(maxLength == 0) {
            slate.add(Integer.parseInt(s));
        }
        else {
            for(int i = 0; i < 10; i++) {
                createAllNumbersHelper(s + i, maxLength - 1, slate);
            }
        }
    }

    public static void binaryString(List<String> slate, int maxLength) {
        binaryStringHelper("", maxLength, slate);
    }
    private static void binaryStringHelper(String s, int maxLength, List<String> slate) {
        if(maxLength == 0) {
            slate.add(s);
            return;
        }
        else {
            for(int i = 0; i < 2; i++) {
                binaryStringHelper(s + i, maxLength - 1, slate);
            }
        }
    }

    public static void binaryStringCombinations(Set<String> slate, int maxLength) {
        binaryStringCombinationsHelper("", maxLength, slate);
    }

    private static void binaryStringCombinationsHelper(String s, int maxLength, Set<String> slate) {
        if(maxLength == 0) {
            if(!slate.contains(s)) slate.add(s);
        }
        else {
            binaryStringCombinationsHelper(s, maxLength - 1, slate);
            for(int i = 0; i <= 1; i++) {
                binaryStringCombinationsHelper(s + i, maxLength - 1, slate);
            }
        }
    }
    
}