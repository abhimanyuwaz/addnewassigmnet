package javatest;

public class CallMethod 
{

	
	   int val=150;//instance variable
	    int operation(int val/*local variable*/) 
	    {
	        val =val*10/100;
	        return(val);
	    }
	    public static void main(String args[]) {
	        CallMethod d = new CallMethod();
	        System.out.println("Before operation value of data is "+d.val);//150
	        d.operation(100);
	        System.out.println("After operation value of data is "+d.val);
	    }
}
