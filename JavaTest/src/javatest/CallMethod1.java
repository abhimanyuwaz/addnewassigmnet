package javatest;

public class CallMethod1 {
    int val=150;//instance variable, non-static 
    void operation(int val/*local variable*/) 
    {
        this.val =val*10/100;
    }
    public static void main(String args[]) 
	{
        CallMethod1 d = new CallMethod1();
        System.out.println("Before operation value of data is "+d.val);//150
        d.operation(100);
        System.out.println("After operation value of data is "+d.val);//10
    }

}
