package net.ucoz.talg.utils;

/**
 * Created by AGrassi on 08.12.2016.
 */
public class MyLinkedList<E> {

    protected class Node {
        E value;
        Node next;

        protected Node(E value) {
            this.value = value;
        }
    }

    private Node head;
    private int size;

    public MyLinkedList() {
        size = 0;
        head = null;
    }

    /**
     * Добавить элемент в указанную позицию
     * @param element Значение
     * @param index позиция
     * @return true если успешно
     */
    public boolean insertElementAt(int index, E element) {
        if (index > size){
            return false;
        }

        Node node = new Node(element);

        if (size == 0) {
            head = node;
        } else {
            int position = 0;

            Node currentNode = head;
            while (currentNode != null) {
                if (position == index - 1) {
                    if (index < size) {
                        node.next = currentNode.next;
                    }
                    currentNode.next = node;
                    break;
                }

                currentNode = currentNode.next;
                position++;
            }
        }

        size++;
        return true;
    }

    /**
     * Вставить элемент в конец списка
     * @param element Значение
     * @return true если успешно
     */
    public boolean insertElement(E element) {
        return insertElementAt(size, element);
    }

    /**
     * Получить єлемент списка
     * @param index индекс в списке
     * @return E если успешно
     */
    public E getElement(int index) {
        int position = 0;

        Node currentNode = head;
        while (currentNode != null) {
            if (position == index) {
                return currentNode.value;
            }

            position++;
            currentNode = currentNode.next;
        }

        return null;
    }

    /**
     * Удаляет и возвращает элемент из списка
     * @param index индекс в списке
     * @return E если успешно
     */
    public E removeElementAt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int position = 0;
        Node node = head;
        Node prev = null;
        while (node != null) {
            if (position == index) {
                if (prev != null) {
                    prev.next = node.next;
                } else {
                    head = head.next;
                }

                size--;
                return node.value;
            }

            prev = node;
            node = node.next;
            position++;
        }

        return null;
    }

    /**
     * Удаляет и возвращает эоемент из списка
     * @param element значение
     * @return E если успешно
     */
    public E removeElement(E element) {
        return removeElementAt(indexOf(element));
    }

    public int indexOf(E element) {
        if (size == 0) {
            return -1;
        }

        int position = 0;
        Node node = head;
        while (node != null) {
            if (node.value.equals(element)) {
                return position;
            }

            node = node.next;
            position++;
        }
        return -1;
    }

    /**
     * Поиск елемента в списке
     * @param element значение
     * @return true если успешно
     */
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    /**
     * Получить размер списка
     * @return Текущий размер списка
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() { return size == 0; }
}