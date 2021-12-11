package ru.job4j.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Testing {
    public static void main(String[] args) {
        Object o1 = new Object();
        System.out.println(o1);
        ReferenceQueue<Object> referenceQueue1 = new ReferenceQueue<>();
        SoftReference<Object> reference = new SoftReference<>(o1, referenceQueue1);
        o1 = null;
        System.gc();
        Object o2 = reference.get();
        if (o2 == null) {
            o2 = referenceQueue1.poll(); /*Получаем объект, в случае его удаления.*/
        }
        System.out.println(o2);

        /*Аналогично с WeakReferences:*/
        Object o3 = new Object();
        System.out.println(o3);
        ReferenceQueue<Object> referenceQueue2 = new ReferenceQueue<>();
        WeakReference<Object> reference2 = new WeakReference<>(o3, referenceQueue2);
        o3 = null;
        System.gc();
        Object o4 = reference2.get();
        if (o4 == null) {
            o4 = referenceQueue2.poll(); /*Получаем объект, в случае его удаления.*/
        }
        System.out.println(o4);
    }
}
