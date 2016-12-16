package net.ucoz.talg.utils;

import java.util.Comparator;

public class MyPriorityQueue<E> extends MyLinkedList<E>{

    private Node head;
    private int size;
    private Comparator<? super E> comparator;

    public MyPriorityQueue(Comparator<? super E> comparator) {
        this.comparator = comparator;
        size = 0;
    }

    /**
     * Добавить элемент в очередь
     * @param element Значение
     */
    public void enqueue(E element) {
        Node newNode = new Node(element);

        if(head == null) {
            head = newNode;
        } else {
            Node node = head;

            while (node.next != null) {
                node = node.next;
            }

            node.next = newNode;
        }

        size++;
    }

    /**
     * Извлечь элемент c максимальным приоритетом из очереди
     * @return E извлеченный элемент
     */
    public E dequeue() {
        if(head == null) {
            return null;
        }

        Node prev = null;
        Node node = head;

        Node maxPrev = null;
        Node max = head;

        while(node != null) {
            if(comparator.compare(max.value, node.value) < 0) {
                maxPrev = prev;
                max = node;
            }
            prev = node;
            node = node.next;
        }

        if(maxPrev == null) {
            head = head.next;
        } else {
            maxPrev.next = max.next;
        }

        size--;

        return max.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
