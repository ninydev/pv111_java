package org.itstep.classworks.feb;

import org.itstep.entities.Role;
import org.itstep.entities.User;
import org.itstep.ui.Button;
import org.itstep.ui.Clickable;

import java.util.*;

public class Feb_08 implements Runnable
{
    @Override
    public void run() {
        // createCollectionAsInterface();
        queueAvatars();
    }

    private void queueAvatars() {
        ArrayDeque<String> avatarsJobs = new ArrayDeque<>();
        avatarsJobs.push(" User 1");
        avatarsJobs.push(" User 2");
        avatarsJobs.push(" User 3");
        avatarsJobs.push(" User 4");

        // Этот метод перебора к сожалению удаляет (pop - вынуть)
//        while (avatarsJobs.peek() != null) {
//            System.out.println(avatarsJobs.pop());
//        }

        // А этот метод перебора - только перебирает элементы (благодаря итератору)
        for (String el : avatarsJobs) {
            System.out.println(el);
        }

        System.out.println("Size after " + avatarsJobs.size());

    }

    private void createCollections() {

        // Реализация интерфейса List - экземпляр класса ArrayList
        List<String> arrayList = new ArrayList<>();

        // Реалиазция интерфейся очереди - экземпляр класса ArrayDeque
        ArrayDeque<String> stringQueue = new ArrayDeque<>();

        stringQueue.add(" Ass add ");
        stringQueue.push(" As push ");
        stringQueue.addFirst(" As first ");





    }

    private void createArrayListAsInterface() {
        List<String> list = new List<String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public String get(int index) {
                return null;
            }

            @Override
            public String set(int index, String element) {
                return null;
            }

            @Override
            public void add(int index, String element) {

            }

            @Override
            public String remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<String> listIterator() {
                return null;
            }

            @Override
            public ListIterator<String> listIterator(int index) {
                return null;
            }

            @Override
            public List<String> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }

    private void createCollectionAsInterface() {

        Collection<String> collection = new Collection<String>() {


            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };

    }

}
