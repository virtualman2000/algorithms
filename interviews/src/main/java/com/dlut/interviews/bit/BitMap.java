package com.dlut.interviews.bit;


import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ray on 15-4-7.
 * 使用位来存储int类型数据
 * 由于位存储的性质，所以存储后的数据有去重的效果
 */
public class BitMap {

    /**
     * 一个int数据占32位
     */
    public static final int UNIT = 32;

    /**
     * i / UNIT = i >> SHIFT
     */
    public static final int SHIFT = 5;

    /**
     * MASK = UNIT - 1
     * i % UNIT = i & MASK
     */
    public static final int MASK = 0x1F;

    /**
     * BitMap Container
     */
    private Map<Integer, Integer> container = new TreeMap<Integer, Integer>();

    /**
     *
     * @param i
     * @return
     */
    public int getIndex(int i) {
        if (i < 0) {
            return -((Math.abs(i) >> SHIFT) + 1);
        } else {
            // equals i / UNIT
            return i >> SHIFT;
        }
    }

    /**
     *
     * @param i
     * @return
     */
    public int getMod(int i) {
        // equals abs(i) % UNIT
        return Math.abs(i) & MASK;
    }

    /**
     *
     * @param index
     * @return
     */
    public int getContainerValue(int index) {
        if (!container.containsKey(index)) {
            return 0;
        }
        return container.get(index);
    }

    /**
     *
     * @param i
     */
    public void set(int i) {
//        int index = getIndex(i);
//        int mod = getMod(i);
//        int containerValue = getContainerValue(index);
//        int newValue = getContainerValue(index) | (i << mod);
        container.put(getIndex(i),
                getContainerValue(getIndex(i)) | (1 << getMod(i)));
    }

    /**
     *
     * @param i
     * @return
     */
    public boolean exists(int i) {
//        int index = getIndex(i);
//        int mod = getMod(i);
//        int containerValue = getContainerValue(getIndex(i));

        if ((getContainerValue(getIndex(i)) & (1 << (getMod(i)))) != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param i
     */
    public void remove(int i) {
//        int index = getIndex(i);
//        int mod = getMod(i);
//        int containerValue = getContainerValue(getIndex(i));
//        int newValue = getContainerValue(getIndex(i)) & ~(1 << getMod(i));
        if (getContainerValue(getIndex(i)) == 0) {
            return;
        }
        container.put(getIndex(i),
                getContainerValue(getIndex(i)) & ~(1 << getMod(i)));
    }

    /**
     *
     * @return
     */
    public List<Integer> sort() {
        List<Integer> sortList = new ArrayList<Integer>(container.size() * UNIT);
        for (Integer key : container.keySet()) {
            int value = container.get(key);
            for (int i = 0; i < UNIT; i++) {
                if (((1 << i) & value) != 0) {
                    if (key < 0) {
                        sortList.add(-(Math.abs(key + 1) * UNIT + i));
                    } else {
                        sortList.add(key * UNIT + i);
                    }
                }
            }
        }
        return sortList;
    }

    /**
     * BitMap测试main方法
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {-1,-43,-90,32,31,63,95,734,32,31,89,5,71,98,273,59,817,457,189,238,409,21,384};
        int[] temp = {89,78,90, 95,457};

        BitMap bitMap = new BitMap();

        for (int item : data) {
            bitMap.set(item);
        }
        for (int item : temp) {
            if (bitMap.exists(item)) {
                System.out.println(item);
            }
        }
        System.out.println(bitMap.sort());
    }
}
