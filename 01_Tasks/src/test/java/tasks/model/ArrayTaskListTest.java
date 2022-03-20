package tasks.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTaskListTest {
    private Date data=new Date();
    private Date date1 = new Date(2022, Calendar.MARCH,18);
    private Date date2 = new Date(2022, Calendar.MARCH,21);
    private Date date3 = new Date(2022, Calendar.MARCH,22);
    private Date date4 = new Date(2022, Calendar.MARCH,30);
    private Date date5 = new Date(2022, Calendar.MARCH,31);
    private Date date6 = new Date(2022, Calendar.APRIL,20);
    private ArrayTaskList taskList;
    private Task t1;
    private Task t2;
    private Task t3;
    private Task t4;
    private Task t5;
    private Task t6;
    private Task t7;
    private Task t8;

    @BeforeEach
    void setUp() {
        taskList = new ArrayTaskList();
        t1=new Task(" ",date3); //non valid len(title)<1
        t2=new Task("Lab2",date1);//non valid start date < current date
        t3=new Task("Lab2",date2,date6,1);//valid

        t4=new Task("Lab2",date4,date4,1);//non valid start date = end date
        t5=new Task("L",date3,date5,1);//non valid len(title)=1
        t6=new Task("La",date2);//valid
        t7=new Task("La",date2,date5,1);//valid
        t8=new Task("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",date3);
        //non valid len(title)=255

    }

    @AfterEach
    void tearDown() {
        for(Task t :taskList){
            taskList.remove(t);
        }
    }

    @Test
    void add_incorrect_title_ECP1() {

        try{
            taskList.add(t1);
        } catch (MyException e) {
            assert e.getMessage() == "Title length must be between 1 and 255";
            assert taskList.size()==0;
        }

    }

    @Test
    void add_incorect_date_ECP2(){
        try{
            taskList.add(t2);
        } catch (MyException e) {
            assert e.getMessage()=="Start date shouldn't be < than current date time";
            assert taskList.size()==0;
        }
    }

    @Test
    void add_correct_title_date_ECP3(){
        try{
            taskList.add(t3);
            assert true;
            assert taskList.size()==1;
        } catch (MyException e) {
            e.printStackTrace();

        }
    }

    @Test
    void add_incorrect_date_BVA4(){
        try{
            taskList.add(t4);
        } catch (MyException e) {
            assert e.getMessage()=="Start date shouldn't be > than end date";
            assert taskList.size()==0;
        }
    }

    @Test
    void add_incorrect_title_BVA5(){
        try{
            taskList.add(t5);
        } catch (MyException e) {
            assert e.getMessage() == "Title length must be between 1 and 255";
            assert taskList.size()==0;
        }

    }

    @Test
    void add_correct_title_date_BVA6(){
        try{
            taskList.add(t6);
            assert true;
            assert taskList.size()==1;
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Test
    void add_correct_title_date_BVA7(){
        try{
            taskList.add(t7);
            assert true;
            assert taskList.size()==1;
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Test
    void add_incorrect_title_BVA8(){
        try{
            taskList.add(t8);
        } catch (MyException e) {
            assert e.getMessage() == "Title length must be between 1 and 255";
            System.out.println(taskList.size());
            assert taskList.size()==0;
        }

    }
}