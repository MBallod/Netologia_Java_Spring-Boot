package ru.netology.mballod.operationhistory.service;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class StorageService<T> implements Serializable {
    protected List<T> values = new ArrayList<>();

    public void add(int num, T newValue) {
        values.add(num, newValue);
    }

    public void add(T newValue) {
        values.add(newValue);
    }

    static public int[] ArrayIntAppend(int[] arr, int a) {  // to StorageService
        int[] array;
        if (arr == null) {
            array = new int[1];
            array[0] = a;
        } else {
            array = Arrays.copyOf(arr, arr.length + 1);
            array[array.length - 1] = a;
        }
        return array;
    }
}