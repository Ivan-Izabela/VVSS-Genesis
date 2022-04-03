package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskTestWBT {

    Task t1;
    Task t2;
    Task t3;
    Task t4;
    Task t5;
    Task t6;
    Date today = new Date(System.currentTimeMillis());
    Date yesterday = new Date(System.currentTimeMillis() - 24*60*60*1000);
    Date tomorrow = new Date(System.currentTimeMillis()+24*60*60*1000);
    Date next_interval=new Date(today.getTime()+30*1000);
    Date next_next_interval=new Date(today.getTime()+30*2000);


    @BeforeEach
    void setUp() {
        t1=new Task("t",today,today,30);
        t2=new Task("t",today);
        t2.setActive(false);
        t3=new Task("t",today);
        t3.setActive(true);
        t4=new Task("t",today,tomorrow,30);
        t4.setActive(true);
        t5= new Task("t",today,tomorrow,30);
        t5.setActive(true);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void WBT_TC1() {
        //curent = end
        assertNull(t1.nextTimeAfter(today));
    }

    @Test
    void WBT_TC2(){
        //curent after end
        assertNull(t1.nextTimeAfter(tomorrow));
    }

    @Test
    void WBT_TC3(){
        //is not repeted and is not active
        assertNull(t2.nextTimeAfter(yesterday));
    }

    @Test
    void WBT_TC4(){
        //is not repeted and curent before time and is active
        assertTrue(t3.nextTimeAfter(new Date(today.getTime()-1)).equals(t3.getTime()));
    }

    @Test
    void WBT_TC5(){
        //is repeted is active curent before start
        assertTrue(t4.nextTimeAfter(yesterday).equals(t4.getStartTime()));
    }


    @Test
    void WBT_TC6(){
        //is repeted is active curent between start and end
        assertTrue(t5.nextTimeAfter(today).equals(next_interval));
    }

    @Test
    void WBT_TC7(){
        //is repeted is active curent between start and end
        assertTrue(t5.nextTimeAfter(new Date(next_interval.getTime() -1 )).equals(next_interval));
    }





}