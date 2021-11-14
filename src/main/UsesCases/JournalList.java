package main.UsesCases;

import main.Entity.Journal;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class JournalList implements Iterable<Journal> {

    private final List<Journal> journalList;

    public JournalList(){
        this.journalList = new ArrayList<Journal>();
    }

    public void addGroup(String workID){
        Journal journal = new Journal(workID);
        journalList.add(journal);
    }

    public boolean deleteJournal(String id){
        int index = -1;

        for(int i = 0; i < this.getSize(); i ++){
            if(this.journalList.get(i).getId().equals(id)){
                index = i;
            }
        }
        if(index == -1){
            return false;
        }else{
            this.journalList.remove(this.journalList.get(index));
            return true;
        }
    }

    public Journal

    public int getSize(){
        return journalList.size();
    }

    public void readInput(Journal journal) {
        this.journalList.add(journal);
    }

    // === Iterator Design Pattern ===
    @Override
    public Iterator<Journal> iterator() {
        return new JournalList.JournalListIterator();
    }


    private class JournalListIterator implements Iterator<Journal>{

        private int curr_index = 0;

        @Override
        public boolean hasNext() {
            return curr_index < journalList.size();
        }

        @Override
        public Journal next() {
            Journal journal;

            try {
                journal = journalList.get(curr_index);
            } catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException();
            }
            curr_index ++;
            return journal;
        }

        public void add(Journal journal){
            journalList.add(journal);
        }
    }
}
